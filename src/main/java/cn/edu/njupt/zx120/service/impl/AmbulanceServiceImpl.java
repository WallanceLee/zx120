package cn.edu.njupt.zx120.service.impl;

import cn.edu.njupt.zx120.dao.AmbulanceDao;
import cn.edu.njupt.zx120.dao.ContainerDao;
import cn.edu.njupt.zx120.dao.EventDao;
import cn.edu.njupt.zx120.dao.OrganizationDao;
import cn.edu.njupt.zx120.dao.ResourceBindingDao;
import cn.edu.njupt.zx120.dao.StockDao;
import cn.edu.njupt.zx120.dto.admin.AmbulanceBindingInfoDto;
import cn.edu.njupt.zx120.dto.realtime.EventDto;
import cn.edu.njupt.zx120.dto.serviceManage.AmbulanceDynamicDto;
import cn.edu.njupt.zx120.dto.serviceManage.AmbulanceStockDto;
import cn.edu.njupt.zx120.model.*;
import cn.edu.njupt.zx120.service.AmbulanceService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wallance on 2/24/17.
 */
@Service("ambulanceService")
public class AmbulanceServiceImpl implements AmbulanceService {

    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure()
            .build();

    @Autowired
    private SessionFactory sessionFactory =
            new MetadataSources(registry).buildMetadata().buildSessionFactory();

    protected Session getSession() {
        return sessionFactory.openSession();
    }

    @Autowired
    private AmbulanceDao ambulanceDao;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private ResourceBindingDao resourceBindingDao;

    @Autowired
    private StockDao stockDao;

    @Autowired
    private ContainerDao containerDao;

    @Autowired
    private OrganizationDao organizationDao;

    @Override
    public List<AmbulanceModel> queryAll() {
        return ambulanceDao.queryAll();
    }

    @Override
    public List<AmbulanceModel> queryByAdminCode(int adminCode) {
        return ambulanceDao.queryByAdminCode(adminCode);
    }

    @Override
    public List<AmbulanceModel> queryByOrgId(String orgId) {
        return ambulanceDao.queryByOrgId(orgId);
    }

    @Override
    public long queryCountByOrgId(String orgId) {
        Session session = getSession();
        session.beginTransaction();
        long numOfTotalAmbulances = (long) session
                .createQuery("select count(ambulance) " +
                        "from AmbulanceModel ambulance " +
                        "where ambulance.orgId=:orgId")
                .setParameter("orgId", orgId)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return numOfTotalAmbulances;
    }

    @Override
    public long queryAvailableCountByOrgId(String orgId) {
        Session session = getSession();
        session.beginTransaction();
        long numOfAvailableAmbulances = (long)session
                .createQuery("select count(ambulance) " +
                        "from AmbulanceModel ambulance " +
                        "join ResourceBindingModel resourceBinding on resourceBinding.resourceId=ambulance.carId and resourceBinding.resourceTableName=:resourceTable " +
                        "join StockModel stock on stock.rfidTag=resourceBinding.tag " +
                        "where ambulance.orgId=:orgId")
                .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                .setParameter("orgId", orgId)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return numOfAvailableAmbulances;
//        List<AmbulanceModel> ambulanceModels = ambulanceDao.queryByOrgId(orgId);
//        List<AmbulanceModel> availableAmbulanceModels = new ArrayList<>();
//        for (AmbulanceModel ambulanceModel : ambulanceModels) {
//            List<EventModel> eventModels = eventDao.queryByResourceId(ambulanceModel.getCarId(), ResourceTableTypeEnum.AMBULANCE.getType());
//            if (eventModels.size() == 0) {
//                availableAmbulanceModels.add(ambulanceModel);
//                continue;
//            }
//            EventModel eventModel = eventModels.get(eventModels.size() - 1);
//            if (eventModel.getEventType().equals("check_in")) {
//                availableAmbulanceModels.add(ambulanceModel);
//            }
//        }
//        return availableAmbulanceModels;
    }

    @Override
    public List<AmbulanceModel> queryOutByOrgId(String orgId) {
        List<AmbulanceModel> ambulanceModels = ambulanceDao.queryByOrgId(orgId);
        List<AmbulanceModel> availableAmbulanceModels = new ArrayList<>();
        for (AmbulanceModel ambulanceModel : ambulanceModels) {
            List<EventModel> eventModels = eventDao.queryByResourceId(ambulanceModel.getCarId(), ResourceTableTypeEnum.AMBULANCE.getType());
            if (eventModels.size() == 0) {
//                availableAmbulanceModels.add(ambulanceModel);
                continue;
            }
            EventModel eventModel = eventModels.get(eventModels.size() - 1);
            if (eventModel.getEventType().equals("check_out")) {
                availableAmbulanceModels.add(ambulanceModel);
            }
        }
        return availableAmbulanceModels;
    }

    @Override
    public void addNewAmbulance(AmbulanceModel ambulanceModel) {
        ambulanceDao.addNewAmbulance(ambulanceModel);
    }

    @Override
    public void setAmbulanceDisabled(String carId) {
        ambulanceDao.setAmbulanceDisabled(carId);
        ambulanceDao.updateLastModifiedTime(carId);
    }

    @Override
    public void leaveContainer(String carId) {

        // delete container item
        ContainerModel containerModel = containerDao.queryByResourceId(carId,
                ResourceTableTypeEnum.AMBULANCE.getType());
        containerDao.deleteItem(containerModel);

        // delete stock item
        StockModel stockModel = stockDao.queryByRfidTag(
                resourceBindingDao.queryByResourceId(
                        ResourceTableTypeEnum.AMBULANCE.getType(), carId).getTag()
        );
        stockDao.deleteItem(stockModel);

        // add event item
        EventModel eventModel = new EventModel();
        eventModel.setEventResourceId(carId);
//        eventModel.setContainerId("");
        eventModel.setEventType(EventTypeEnum.AMBULANCE_LEAVE_CONTAINER.getType());
        eventModel.setEventTime(new Date());
        eventDao.createNewEvent(eventModel);

    }

    @Override
    public void arriveContainer(String carId, int containerId, String containerType, String containerName) {

        // add container item
        ContainerModel containerModel = new ContainerModel();
        containerModel.setResourceTable(ResourceTableTypeEnum.AMBULANCE.getType());
        containerModel.setContainerId(containerId);
        containerModel.setResourceId(carId);
        containerModel.setLastScanTime(new Date());
//        containerModel.setContainerType(containerType);
        containerModel.setContainerName(containerName);
        containerDao.addNewItem(containerModel);

        // add stock item
        StockModel stockModel = new StockModel();
//        stockModel.setContainerId(containerId);
        stockModel.setRfidTag(resourceBindingDao.queryByResourceId(ResourceTableTypeEnum.AMBULANCE.getType(), carId).getTag());
        stockDao.addItem(stockModel);

        // add event item
        EventModel eventModel = new EventModel();
        eventModel.setEventResourceId(carId);
        eventModel.setEventTime(new Date());
//        eventModel.setContainerId(containerId);
        eventModel.setEventType(EventTypeEnum.AMBULANCE_ARRIVE_CONTAINER.getType());
        eventDao.createNewEvent(eventModel);

    }

    // This depends on Reader submodule
    // NOT IMPLEMENTED
    @Override
    public void scanResourceInCar(String carId) {

    }

    @Override
    public List<AmbulanceDynamicDto> queryAllDynamicInfo() {

        Session session = getSession();
        session.beginTransaction();
        List<AmbulanceDynamicDto> ambulanceDynamicDtos = session
                .createQuery("select new cn.edu.njupt.zx120.dto.serviceManage.AmbulanceDynamicDto(ambulance.carId, ambulance.carBrand, container.containerName, event.eventType, event.eventTime) " +
                        "from AmbulanceModel ambulance join EventModel event on event.eventResourceId=ambulance.carId " +
                        "join ContainerModel container on container.containerId=event.containerId")
                .getResultList();
        session.getTransaction().commit();
        session.close();

//        // 查询所有的车辆信息
//        List<AmbulanceModel> ambulanceModels = queryLatestTenRecords();
//
//        List<AmbulanceDynamicDto> ambulanceDynamicDtos = new ArrayList<>();
//
//        for (AmbulanceModel ambulanceModel : ambulanceModels) {
//
//            // 从事件表获取该车辆最后一次信息
//            List<EventModel> eventModels = eventDao.queryByResourceId(ambulanceModel.getCarId(),
//                    ResourceTableTypeEnum.AMBULANCE.getType());
//
//            // 如果没有发现事件信息
//            // 设置事件信息为空
//            // 显示时间为当前时间
//            // 如果发现事件信息
//            // 设置为最后一次事件的类型
//            // 显示时间为最后一次事件的时间
//            if (eventModels.size() == 0) {
//                AmbulanceDynamicDto ambulanceDynamicDto = new AmbulanceDynamicDto();
//                // 从车辆信息获取车辆id
//                ambulanceDynamicDto.setCarId(ambulanceModel.getCarId());
//                // 从车辆信息里获取车牌号
//                ambulanceDynamicDto.setCarBrand(ambulanceModel.getCarBrand());
//
//                // 根据相应车辆的RFID标签查询库存表
//                // 如果库存表中存在相应车辆的库存记录
//                // 那么根据记录中的容器id找到容器名称
//                // 如果不存在库存记录
//                // 那么设置位置为nothing
//                ResourceBindingModel resourceBindingModel = resourceBindingDao.queryByResourceId(
//                        ResourceTableTypeEnum.AMBULANCE.getType(), ambulanceModel.getCarId());
//                StockModel stockModel = null;
//                if (resourceBindingModel != null) {
//                    stockModel = stockDao.queryByRfidTag(
//                            resourceBindingModel.getTag());
//                } else
//                    continue;
//                if (stockModel == null) {
//                    ambulanceDynamicDto.setContainerName("nothing");
//                } else {
//                    // 从容器表里查询位置信息
//                    ContainerModel containerModel = containerDao.queryByContainerId(stockModel.getContainerId());
//                    ambulanceDynamicDto.setContainerName(containerModel.getContainerName());
//                }
//                ambulanceDynamicDto.setEventType("nothing");
//                ambulanceDynamicDto.setTime(new Date());
//                ambulanceDynamicDtos.add(ambulanceDynamicDto);
//            }
//            else {
//                for (EventModel eventModel : eventModels) {
//
//                    AmbulanceDynamicDto ambulanceDynamicDto = new AmbulanceDynamicDto();
//                    // 从车辆信息获取车辆id
//                    ambulanceDynamicDto.setCarId(ambulanceModel.getCarId());
//                    // 从车辆信息里获取车牌号
//                    ambulanceDynamicDto.setCarBrand(ambulanceModel.getCarBrand());
//
//                    // 根据相应车辆的RFID标签查询库存表
//                    // 如果库存表中存在相应车辆的库存记录
//                    // 那么根据记录中的容器id找到容器名称
//                    // 如果不存在库存记录
//                    // 那么设置位置为nothing
//                    ResourceBindingModel resourceBindingModel = resourceBindingDao.queryByResourceId(
//                            ResourceTableTypeEnum.AMBULANCE.getType(), ambulanceModel.getCarId());
//                    StockModel stockModel = null;
//                    if (resourceBindingModel != null) {
//                        stockModel = stockDao.queryByRfidTag(
//                                resourceBindingModel.getTag());
//                    } else
//                        continue;
//                    if (stockModel == null) {
//                        ambulanceDynamicDto.setContainerName("nothing");
//                    } else {
//                        // 从容器表里查询位置信息
//                        ContainerModel containerModel = containerDao.queryByContainerId(stockModel.getContainerId());
//                        ambulanceDynamicDto.setContainerName(containerModel.getContainerName());
//                    }
//                    ambulanceDynamicDto.setEventType(eventModel.getEventType());
//                    ambulanceDynamicDto.setTime(eventModel.getEventTime());
//                    ambulanceDynamicDtos.add(ambulanceDynamicDto);
//                }
//            }
//
//        }
        return ambulanceDynamicDtos;
    }

    @Override
    public List<AmbulanceStockDto> queryAllStockInfo() {
//        Session session = getSession();
//        session.beginTransaction();
//        List<AmbulanceStockDto> ambulanceStockDtos = session
//                .createQuery("select new cn.edu.njupt.zx120.dto.serviceManage.AmbulanceStockDto(ambulance.carId, ambulance.carBrand, organization.name, container.containerName) " +
//                        "from AmbulanceModel ambulance " +
//                        "join ResourceBindingModel resourceBinding on resourceBinding.resourceId=ambulance.carId and resourceBinding.resourceTableName=:resourceTable " +
//                        "join StockModel stock on stock.rfidTag=resourceBinding.tag " +
//                        "join ContainerModel container on container.containerId=stock.containerId "+
//                        "join OrganizationModel organization on organization.orgId=ambulance.orgId"
//                )
//                .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
//                .getResultList();
//        List<EventDto> eventDtos = session
//                .createNativeQuery("from sys_ambul_info join event_info where event_info.event_id")
//                .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
//                .getResultList();


        // 查询所有的车辆信息
        List<AmbulanceModel> ambulanceModels = queryAll();

        List<AmbulanceStockDto> ambulanceStockDtos = new ArrayList<>();

        for (AmbulanceModel ambulanceModel : ambulanceModels) {

            AmbulanceStockDto ambulanceStockDto = new AmbulanceStockDto();

            ambulanceStockDto.setCarId(ambulanceModel.getCarId());

            ambulanceStockDto.setCarBrand(ambulanceModel.getCarBrand());

            ambulanceStockDto.setDepartment(organizationDao.queryById(ambulanceModel.getOrgId()).getName());

            // 根据相应车辆的RFID标签查询库存表
            // 如果库存表中存在相应车辆的库存记录
            // 那么根据记录中的容器id找到容器名称
            // 如果不存在库存记录
            // 那么设置位置为nothing
            ResourceBindingModel resourceBindingModel = resourceBindingDao.queryByResourceId(
                    ResourceTableTypeEnum.AMBULANCE.getType(), ambulanceModel.getCarId());
            StockModel stockModel = null;
            if (resourceBindingModel != null) {
                stockModel = stockDao.queryByRfidTag(
                        resourceBindingModel.getTag());
            } else
                continue;
            if (stockModel == null) {
                ambulanceStockDto.setContainerName("nothing");
            } else {
                ambulanceStockDto.setContainerName(containerDao.queryByContainerId(stockModel.getContainerId()).getContainerName());
            }

            List<EventModel> eventModels = eventDao.queryByResourceId(ambulanceModel.getCarId(), ResourceTableTypeEnum.AMBULANCE.getType());
            if (eventModels.size() == 0) {
                ambulanceStockDto.setLastPosition(ambulanceStockDto.getContainerName());
                ambulanceStockDto.setTime(new Date());
            } else {
                // 获取最新一次事件
                EventModel latestEvent = eventModels.get(eventModels.size() - 1);
                // 判断最新事件类型
                // 如果是入库
                // 设置位置为容器表中的相应容器名称
                if (latestEvent.getEventType().equals("check_in")) {
                    ambulanceStockDto.setLastPosition(ambulanceStockDto.getContainerName());
                } else
                    // 如果是出库
                    // 需要根据RFID标签查询库存信息
                    // 使用库存表中的容器id得到容器名称
                    if (latestEvent.getEventType().equals("check_out")) {
                        ambulanceStockDto.setLastPosition(containerDao.queryByContainerId(latestEvent.getContainerId()).getContainerName());
                    }
                ambulanceStockDto.setTime(latestEvent.getEventTime());
            }
            ambulanceStockDtos.add(ambulanceStockDto);
        }

        return ambulanceStockDtos;

    }

    @Override
    public List<AmbulanceBindingInfoDto> queryAllBindingInfo() {
        List<AmbulanceBindingInfoDto> ambulanceBindingInfoDtos = new ArrayList<>();

        List<ResourceBindingModel> resourceBindingModels = resourceBindingDao.queryByResourceTable(ResourceTableTypeEnum.AMBULANCE.getType());

        for (ResourceBindingModel resourceBindingModel : resourceBindingModels) {
            AmbulanceBindingInfoDto ambulanceBindingInfoDto = new AmbulanceBindingInfoDto();
            AmbulanceModel ambulanceModel = ambulanceDao.queryById(resourceBindingModel.getResourceId());
            ambulanceBindingInfoDto.setCarBrand(ambulanceModel.getCarBrand());
            ambulanceBindingInfoDto.setCarId(ambulanceModel.getCarId());
            ambulanceBindingInfoDto.setOrgName(organizationDao.queryById(ambulanceModel.getOrgId()).getName());
            ambulanceBindingInfoDto.setRfidTag(resourceBindingModel.getTag());
            ambulanceBindingInfoDto.setTime(resourceBindingModel.getBindTime());
            ambulanceBindingInfoDtos.add(ambulanceBindingInfoDto);
        }

        return ambulanceBindingInfoDtos;
    }

    @Override
    public List<AmbulanceBindingInfoDto> queryAllUnboundInfo() {

        List<AmbulanceBindingInfoDto> ambulanceBindingInfoDtos = new ArrayList<>();
        List<AmbulanceModel> ambulanceModels = ambulanceDao.queryAll();

        for (AmbulanceModel ambulanceModel : ambulanceModels) {
            ResourceBindingModel resourceBindingModel = resourceBindingDao.queryByResourceId(ResourceTableTypeEnum.AMBULANCE.getType(), ambulanceModel.getCarId());
            if (resourceBindingModel == null) {
                AmbulanceBindingInfoDto ambulanceBindingInfoDto = new AmbulanceBindingInfoDto();
                ambulanceBindingInfoDto.setTime(null);
                ambulanceBindingInfoDto.setRfidTag(null);
                ambulanceBindingInfoDto.setOrgName(organizationDao.queryById(ambulanceModel.getOrgId()).getName());
                ambulanceBindingInfoDto.setCarId(ambulanceModel.getCarId());
                ambulanceBindingInfoDto.setCarBrand(ambulanceModel.getCarBrand());
                ambulanceBindingInfoDtos.add(ambulanceBindingInfoDto);
            }
        }

        return ambulanceBindingInfoDtos;

    }

    @Override
    public List<AmbulanceBindingInfoDto> queryAllTodayBindingInfo() {
        List<AmbulanceBindingInfoDto> ambulanceBindingInfoDtos = new ArrayList<>();

        List<ResourceBindingModel> resourceBindingModels = resourceBindingDao.queryByResourceTable(ResourceTableTypeEnum.AMBULANCE.getType());

        for (ResourceBindingModel resourceBindingModel : resourceBindingModels) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            if (!format.format(resourceBindingModel.getBindTime()).equals(format.format(new Date())))
                continue;
            AmbulanceBindingInfoDto ambulanceBindingInfoDto = new AmbulanceBindingInfoDto();
            AmbulanceModel ambulanceModel = ambulanceDao.queryById(resourceBindingModel.getResourceId());
            ambulanceBindingInfoDto.setCarBrand(ambulanceModel.getCarBrand());
            ambulanceBindingInfoDto.setCarId(ambulanceModel.getCarId());
            ambulanceBindingInfoDto.setOrgName(organizationDao.queryById(ambulanceModel.getOrgId()).getName());
            ambulanceBindingInfoDto.setRfidTag(resourceBindingModel.getTag());
            ambulanceBindingInfoDto.setTime(resourceBindingModel.getBindTime());
            ambulanceBindingInfoDtos.add(ambulanceBindingInfoDto);
        }

        return ambulanceBindingInfoDtos;
    }
}
