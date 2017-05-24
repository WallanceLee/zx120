package cn.edu.njupt.zx120.service.impl;

import cn.edu.njupt.zx120.dao.*;
import cn.edu.njupt.zx120.dto.admin.AmbulanceBindingInfoDto;
import cn.edu.njupt.zx120.dto.serviceManage.AmbulanceDynamicDto;
import cn.edu.njupt.zx120.dto.serviceManage.AmbulanceStockDto;
import cn.edu.njupt.zx120.dto.serviceManage.AmbulanceStockDto2;
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
                .createQuery("select new cn.edu.njupt.zx120.dto.serviceManage.AmbulanceDynamicDto(ambulance.carId, ambulance.carBrand, " +
                        "container.containerName, event.eventType, event.eventTime) " +
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
    public List<AmbulanceDynamicDto> queryDynamicInfoByCondition(Long containerId, String carBrand,
                                                                 Date startTime, Date endTime,
                                                                 Integer startIndex, Integer pageSize) {
        Session session = getSession();
        session.beginTransaction();
        final int defaultStartIndex = 0;
        final int defaultPageSize = 50;
        int conditionCode = ((containerId != null ? 1 : 0) << 3)
                + ((carBrand != null ? 1 : 0) << 2)
                + ((startTime != null ? 1 : 0) << 1)
                + (endTime != null ? 1 : 0);
        // 按照containerId carBrand startTime endTime查找
        // 按照startIndex pageSize分页查询
        List<AmbulanceDynamicDto> ambulanceDynamicDtos = null;
        switch (conditionCode) {
            case 0:
                ambulanceDynamicDtos = session
                        .createQuery("select new cn.edu.njupt.zx120.dto.serviceManage.AmbulanceDynamicDto(ambulance.carId, ambulance.carBrand, container.containerName, event.eventType, event.eventTime) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setFirstResult(startIndex == null ? defaultStartIndex : startIndex)
                        .setMaxResults(pageSize == null ? defaultPageSize : pageSize)
                        .getResultList();
                break;
            case 1:
                ambulanceDynamicDtos = session
                        .createQuery("select new cn.edu.njupt.zx120.dto.serviceManage.AmbulanceDynamicDto(ambulance.carId, ambulance.carBrand, container.containerName, event.eventType, event.eventTime) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where event.eventTime <= :endTime")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("endTime", endTime)
                        .setFirstResult(startIndex == null ? defaultStartIndex : startIndex)
                        .setMaxResults(pageSize == null ? defaultPageSize : pageSize)
                        .getResultList();
                break;
            case 2:
                ambulanceDynamicDtos = session
                        .createQuery("select new cn.edu.njupt.zx120.dto.serviceManage.AmbulanceDynamicDto(ambulance.carId, ambulance.carBrand, container.containerName, event.eventType, event.eventTime) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where event.eventTime >= :startTime")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("startTime", startTime)
                        .setFirstResult(startIndex == null ? defaultStartIndex : startIndex)
                        .setMaxResults(pageSize == null ? defaultPageSize : pageSize)
                        .getResultList();
                break;
            case 3:
                ambulanceDynamicDtos = session
                        .createQuery("select new cn.edu.njupt.zx120.dto.serviceManage.AmbulanceDynamicDto(ambulance.carId, ambulance.carBrand, container.containerName, event.eventType, event.eventTime) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where event.eventTime >= :startTime " +
                                "and event.eventTime <= :endTime")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("startTime", startTime)
                        .setParameter("endTime", endTime)
                        .setFirstResult(startIndex == null ? defaultStartIndex : startIndex)
                        .setMaxResults(pageSize == null ? defaultPageSize : pageSize)
                        .getResultList();
                break;
            case 4:
                ambulanceDynamicDtos = session
                        .createQuery("select new cn.edu.njupt.zx120.dto.serviceManage.AmbulanceDynamicDto(ambulance.carId, ambulance.carBrand, container.containerName, event.eventType, event.eventTime) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where ambulance.carBrand=:carBrand")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("carBrand", carBrand)
                        .setFirstResult(startIndex == null ? defaultStartIndex : startIndex)
                        .setMaxResults(pageSize == null ? defaultPageSize : pageSize)
                        .getResultList();
                break;
            case 5:
                ambulanceDynamicDtos = session
                        .createQuery("select new cn.edu.njupt.zx120.dto.serviceManage.AmbulanceDynamicDto(ambulance.carId, ambulance.carBrand, container.containerName, event.eventType, event.eventTime) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where ambulance.carBrand=:carBrand " +
                                "and event.eventTime <= :endTime")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("carBrand", carBrand)
                        .setParameter("endTime", endTime)
                        .setFirstResult(startIndex == null ? defaultStartIndex : startIndex)
                        .setMaxResults(pageSize == null ? defaultPageSize : pageSize)
                        .getResultList();
                break;
            case 6:
                ambulanceDynamicDtos = session
                        .createQuery("select new cn.edu.njupt.zx120.dto.serviceManage.AmbulanceDynamicDto(ambulance.carId, ambulance.carBrand, container.containerName, event.eventType, event.eventTime) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where ambulance.carBrand=:carBrand " +
                                "and event.eventTime >= :startTime")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("carBrand", carBrand)
                        .setParameter("startTime", startTime)
                        .setFirstResult(startIndex == null ? defaultStartIndex : startIndex)
                        .setMaxResults(pageSize == null ? defaultPageSize : pageSize)
                        .getResultList();
                break;
            case 7:
                ambulanceDynamicDtos = session
                        .createQuery("select new cn.edu.njupt.zx120.dto.serviceManage.AmbulanceDynamicDto(ambulance.carId, ambulance.carBrand, container.containerName, event.eventType, event.eventTime) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where ambulance.carBrand=:carBrand " +
                                "and event.eventTime >= :startTime and event.eventTime <= :endTime")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("carBrand", carBrand)
                        .setParameter("startTime", startTime)
                        .setParameter("endTime", endTime)
                        .setFirstResult(startIndex == null ? defaultStartIndex : startIndex)
                        .setMaxResults(pageSize == null ? defaultPageSize : pageSize)
                        .getResultList();
                break;
            case 8:
                ambulanceDynamicDtos = session
                        .createQuery("select new cn.edu.njupt.zx120.dto.serviceManage.AmbulanceDynamicDto(ambulance.carId, ambulance.carBrand, container.containerName, event.eventType, event.eventTime) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where container.containerId=:containerId")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("containerId", containerId)
                        .setFirstResult(startIndex == null ? defaultStartIndex : startIndex)
                        .setMaxResults(pageSize == null ? defaultPageSize : pageSize)
                        .getResultList();
                break;
            case 9:
                ambulanceDynamicDtos = session
                        .createQuery("select new cn.edu.njupt.zx120.dto.serviceManage.AmbulanceDynamicDto(ambulance.carId, ambulance.carBrand, container.containerName, event.eventType, event.eventTime) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where container.containerId=:containerId " +
                                "and event.eventTime <= :endTime")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("containerId", containerId)
                        .setParameter("endTime", endTime)
                        .setFirstResult(startIndex == null ? defaultStartIndex : startIndex)
                        .setMaxResults(pageSize == null ? defaultPageSize : pageSize)
                        .getResultList();
                break;
            case 10:
                ambulanceDynamicDtos = session
                        .createQuery("select new cn.edu.njupt.zx120.dto.serviceManage.AmbulanceDynamicDto(ambulance.carId, ambulance.carBrand, container.containerName, event.eventType, event.eventTime) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where container.containerId=:containerId " +
                                "and event.eventTime >= :startTime")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("containerId", containerId)
                        .setParameter("startTime", startTime)
                        .setFirstResult(startIndex == null ? defaultStartIndex : startIndex)
                        .setMaxResults(pageSize == null ? defaultPageSize : pageSize)
                        .getResultList();
                break;
            case 11:
                ambulanceDynamicDtos = session
                        .createQuery("select new cn.edu.njupt.zx120.dto.serviceManage.AmbulanceDynamicDto(ambulance.carId, ambulance.carBrand, container.containerName, event.eventType, event.eventTime) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where container.containerId=:containerId " +
                                "and event.eventTime >= :startTime " +
                                "and event.eventTime <= :endTime")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("containerId", containerId)
                        .setParameter("startTime", startTime)
                        .setParameter("endTime", endTime)
                        .setFirstResult(startIndex == null ? defaultStartIndex : startIndex)
                        .setMaxResults(pageSize == null ? defaultPageSize : pageSize)
                        .getResultList();
                break;
            case 12:
                ambulanceDynamicDtos = session
                        .createQuery("select new cn.edu.njupt.zx120.dto.serviceManage.AmbulanceDynamicDto(ambulance.carId, ambulance.carBrand, container.containerName, event.eventType, event.eventTime) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where container.containerId=:containerId " +
                                "and ambulance.carBrand=:carBrand")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("containerId", containerId)
                        .setParameter("carBrand", carBrand)
                        .setFirstResult(startIndex == null ? defaultStartIndex : startIndex)
                        .setMaxResults(pageSize == null ? defaultPageSize : pageSize)
                        .getResultList();
                break;
            case 13:
                ambulanceDynamicDtos = session
                        .createQuery("select new cn.edu.njupt.zx120.dto.serviceManage.AmbulanceDynamicDto(ambulance.carId, ambulance.carBrand, container.containerName, event.eventType, event.eventTime) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where container.containerId=:containerId " +
                                "and ambulance.carBrand=:carBrand " +
                                "and event.eventTime<=:endTime")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("containerId", containerId)
                        .setParameter("carBrand", carBrand)
                        .setParameter("endTime", endTime)
                        .setFirstResult(startIndex == null ? defaultStartIndex : startIndex)
                        .setMaxResults(pageSize == null ? defaultPageSize : pageSize)
                        .getResultList();
                break;
            case 14:
                ambulanceDynamicDtos = session
                        .createQuery("select new cn.edu.njupt.zx120.dto.serviceManage.AmbulanceDynamicDto(ambulance.carId, ambulance.carBrand, container.containerName, event.eventType, event.eventTime) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where container.containerId=:containerId " +
                                "and ambulance.carBrand=:carBrand " +
                                "and event.eventTime>=:startTime")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("containerId", containerId)
                        .setParameter("carBrand", carBrand)
                        .setParameter("startTime", startTime)
                        .setFirstResult(startIndex == null ? defaultStartIndex : startIndex)
                        .setMaxResults(pageSize == null ? defaultPageSize : pageSize)
                        .getResultList();
                break;
            case 15:
                ambulanceDynamicDtos = session
                        .createQuery("select new cn.edu.njupt.zx120.dto.serviceManage.AmbulanceDynamicDto(ambulance.carId, ambulance.carBrand, container.containerName, event.eventType, event.eventTime) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where container.containerId=:containerId " +
                                "and ambulance.carBrand=:carBrand " +
                                "and event.eventTime>=:startTime " +
                                "and event.eventTime<=:endTime")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("containerId", containerId)
                        .setParameter("carBrand", carBrand)
                        .setParameter("startTime", startTime)
                        .setParameter("endTime", endTime)
                        .setFirstResult(startIndex == null ? defaultStartIndex : startIndex)
                        .setMaxResults(pageSize == null ? defaultPageSize : pageSize)
                        .getResultList();
                break;
        }
        session.getTransaction().commit();
        session.close();
        return ambulanceDynamicDtos;
    }

    @Override
    public long queryDynamicInfoTotalByCondition(Long containerId, String carBrand,
                                                 Date startTime, Date endTime,
                                                 Integer startIndex, Integer pageSize) {
        Session session = getSession();
        session.beginTransaction();
        int conditionCode = ((containerId != null ? 1 : 0) << 3)
                + ((carBrand != null ? 1 : 0) << 2)
                + ((startTime != null ? 1 : 0) << 1)
                + (endTime != null ? 1 : 0);
        // 按照containerId carBrand startTime endTime查找
        // 按照startIndex pageSize分页查询
        long total = 0;
        switch (conditionCode) {
            case 0:
                total = (long) session
                        .createQuery("select count(event) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .uniqueResult();
                break;
            case 1:
                total = (long) session
                        .createQuery("select count(event) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where event.eventTime <= :endTime")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("endTime", endTime)
                        .uniqueResult();
                break;
            case 2:
                total = (long) session
                        .createQuery("select count(event) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where event.eventTime >= :startTime")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("startTime", startTime)
                        .uniqueResult();
                break;
            case 3:
                total = (long) session
                        .createQuery("select count(event) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where event.eventTime >= :startTime " +
                                "and event.eventTime <= :endTime")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("startTime", startTime)
                        .setParameter("endTime", endTime)
                        .uniqueResult();
                break;
            case 4:
                total = (long) session
                        .createQuery("select count(event) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where ambulance.carBrand=:carBrand")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("carBrand", carBrand)
                        .uniqueResult();
                break;
            case 5:
                total = (long) session
                        .createQuery("select count(event) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where ambulance.carBrand=:carBrand " +
                                "and event.eventTime <= :endTime")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("carBrand", carBrand)
                        .setParameter("endTime", endTime)
                        .uniqueResult();
                break;
            case 6:
                total = (long) session
                        .createQuery("select count(event) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where ambulance.carBrand=:carBrand " +
                                "and event.eventTime >= :startTime")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("carBrand", carBrand)
                        .setParameter("startTime", startTime)
                        .uniqueResult();
                break;
            case 7:
                total = (long) session
                        .createQuery("select count(event) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where ambulance.carBrand=:carBrand " +
                                "and event.eventTime >= :startTime and event.eventTime <= :endTime")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("carBrand", carBrand)
                        .setParameter("startTime", startTime)
                        .setParameter("endTime", endTime)
                        .uniqueResult();
                break;
            case 8:
                total = (long) session
                        .createQuery("select count(event) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where container.containerId=:containerId")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("containerId", containerId)
                        .uniqueResult();
                break;
            case 9:
                total = (long) session
                        .createQuery("select count(event) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where container.containerId=:containerId " +
                                "and event.eventTime <= :endTime")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("containerId", containerId)
                        .setParameter("endTime", endTime)
                        .uniqueResult();
                break;
            case 10:
                total = (long) session
                        .createQuery("select count(event) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where container.containerId=:containerId " +
                                "and event.eventTime >= :startTime")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("containerId", containerId)
                        .setParameter("startTime", startTime)
                        .uniqueResult();
                break;
            case 11:
                total = (long) session
                        .createQuery("select count(event) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where container.containerId=:containerId " +
                                "and event.eventTime >= :startTime " +
                                "and event.eventTime <= :endTime")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("containerId", containerId)
                        .setParameter("startTime", startTime)
                        .setParameter("endTime", endTime)
                        .uniqueResult();
                break;
            case 12:
                total = (long) session
                        .createQuery("select count(event) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where container.containerId=:containerId " +
                                "and ambulance.carBrand=:carBrand")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("containerId", containerId)
                        .setParameter("carBrand", carBrand)
                        .uniqueResult();
                break;
            case 13:
                total = (long) session
                        .createQuery("select count(event) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where container.containerId=:containerId " +
                                "and ambulance.carBrand=:carBrand " +
                                "and event.eventTime<=:endTime")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("containerId", containerId)
                        .setParameter("carBrand", carBrand)
                        .setParameter("endTime", endTime)
                        .uniqueResult();
                break;
            case 14:
                total = (long) session
                        .createQuery("select count(event) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where container.containerId=:containerId " +
                                "and ambulance.carBrand=:carBrand " +
                                "and event.eventTime>=:startTime")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("containerId", containerId)
                        .setParameter("carBrand", carBrand)
                        .setParameter("startTime", startTime)
                        .uniqueResult();
                break;
            case 15:
                total = (long) session
                        .createQuery("select count(event) " +
                                "from AmbulanceModel ambulance " +
                                "join EventModel event on ambulance.carId=event.eventResourceId " +
                                "and event.eventResourceTable=:resourceTable " +
                                "join ContainerModel container on container.containerId=event.containerId " +
                                "where container.containerId=:containerId " +
                                "and ambulance.carBrand=:carBrand " +
                                "and event.eventTime>=:startTime " +
                                "and event.eventTime<=:endTime")
                        .setParameter("resourceTable", ResourceTableTypeEnum.AMBULANCE.getType())
                        .setParameter("containerId", containerId)
                        .setParameter("carBrand", carBrand)
                        .setParameter("startTime", startTime)
                        .setParameter("endTime", endTime)
                        .uniqueResult();
                break;
        }
        session.getTransaction().commit();
        session.close();
        return total;
    }

    @Override
    public List<AmbulanceStockDto2> queryStock2InfoByCondition(String orgId,
                                                              Date startTime, Date endTime,
                                                              Integer startIndex, Integer pageSize) {
        Session session = getSession();
        session.beginTransaction();

        List<AmbulanceStockDto2> ambulanceStockDto2s = new ArrayList<>();

        int conditionCode = ((orgId != null ? 1 : 0) << 2)
                + ((startTime != null ? 1 : 0) << 1)
                + ((endTime != null ? 1 : 0));

        OrganizationModel queriedOrganizationModel = null;
        List<OrganizationModel> queriedOrganizationModels = null;

        int defaultStartIndex = 0;
        int defaultPageSize = 50;

        if (orgId == null) {
            queriedOrganizationModels = organizationDao.queryAll();
        }

        switch (conditionCode) {

            case 0:
                ambulanceStockDto2s = session
                        .createQuery("select new cn.edu.njupt.zx120.dto.serviceManage.AmbulanceStockDto2(ambulanceStockDetail.containerOrg, ambulanceStockDetail.containerOrgName, " +
                                "sum(case when ambulanceStockDetail.containerOrg=ambulanceStockDetail.ambulanceOrg then 1 else 0 end), " +
                                "sum(case when ambulanceStockDetail.containerOrg!=ambulanceStockDetail.ambulanceOrg then 1 else 0 end), " +
                                "(select sum(case when ambulance.carId!=null then 1 else 0 end)-count(ambulanceStockDetail) " +
                                    "from OrganizationModel org " +
                                    "left join AmbulanceModel ambulance on ambulance.orgId=org.orgId), " +
                                "event.eventTime) " +
                                "from AmbulanceStockDetailModel ambulanceStockDetail " +
                                "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
                                "group by ambulanceStockDetail.triggerEvent, ambulanceStockDetail.containerId")
                        .getResultList();
//                for (OrganizationModel organizationModel : queriedOrganizationModels) {
//                    // 按照机构查询
//                    List<Long> remainNumbers = session
//                            .createQuery("select isnull(count(ambulanceStockDetail),0) " +
//                                    "from AmbulanceStockDetailModel ambulanceStockDetail " +
//                                    "where ambulanceStockDetail.ambulanceOrg=:orgId and ambulanceStockDetail.ambulanceOrg=ambulanceStockDetail.containerOrg " +
//                                    "group by ambulanceStockDetail.triggerEvent", Long.class)
//                            .setParameter("orgId", organizationModel.getOrgId())
////                            .setFirstResult(startIndex == null ? defaultStartIndex : startIndex)
////                            .setMaxResults(pageSize == null ? defaultPageSize : pageSize)
//                            .getResultList();
//                    long orgTotal = (long) session
//                            .createQuery("select isnull(count(ambulance),0) " +
//                                    "from AmbulanceModel ambulance " +
//                                    "where ambulance.orgId=:orgId")
//                            .setParameter("orgId", organizationModel.getOrgId())
//                            .uniqueResult();
//                    List<Long> visitedNumber = session
//                            .createQuery("select isnull(count(ambulanceStockDetail),0) " +
//                                    "from AmbulanceStockDetailModel ambulanceStockDetail " +
//                                    "where ambulanceStockDetail.ambulanceOrg=:orgId and ambulanceStockDetail.ambulanceOrg!=ambulanceStockDetail.containerOrg " +
//                                    "group by ambulanceStockDetail.triggerEvent", Long.class)
//                            .setParameter("orgId", organizationModel.getOrgId())
////                            .setFirstResult(startIndex == null ? defaultStartIndex : startIndex)
////                            .setMaxResults(pageSize == null ? defaultPageSize : pageSize)
//                            .getResultList();
////                    List<Long> stockTotals = session
////                            .createQuery("select count(ambulanceStockDetail) " +
////                                    "from AmbulanceStockDetailModel ambulanceStockDetail " +
////                                    "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
////                                    "group by event.eventId", Long.class)
////                            .getResultList();
//                    List<Date> timeOfStocks = session
//                            .createQuery("select event.eventTime " +
//                                    "from AmbulanceStockDetailModel ambulanceStockDetail " +
//                                    "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
//                                    "where ambulanceStockDetail.ambulanceOrg=:orgId " +
//                                    "group by event.eventId", Date.class)
//                            .setParameter("orgId", organizationModel.getOrgId())
//                            .getResultList();
//                    for (int i = 0; i < remainNumbers.size(); i++) {
//                        AmbulanceStockDto2 ambulanceStockDto2 = new AmbulanceStockDto2();
//                        ambulanceStockDto2.setOrgId(organizationModel.getOrgId());
//                        ambulanceStockDto2.setDepartment(organizationModel.getName());
//                        ambulanceStockDto2.setRemainNumber(remainNumbers.get(i));
//                        ambulanceStockDto2.setOutNumber(orgTotal - ambulanceStockDto2.getRemainNumber());
//                        ambulanceStockDto2.setVisitedNumber(visitedNumber.get(i));
//                        ambulanceStockDto2.setTime(timeOfStocks.get(i));
//                        ambulanceStockDto2s.add(ambulanceStockDto2);
//                    }
//
//                }
                break;
            case 1:
                ambulanceStockDto2s = session
                        .createQuery("select new cn.edu.njupt.zx120.dto.serviceManage.AmbulanceStockDto2(ambulanceStockDetail.containerOrg, ambulanceStockDetail.containerOrgName, " +
                                "sum(case when ambulanceStockDetail.containerOrg=ambulanceStockDetail.ambulanceOrg then 1 else 0 end), " +
                                "sum(case when ambulanceStockDetail.containerOrg!=ambulanceStockDetail.ambulanceOrg then 1 else 0 end), " +
                                "(select sum(case when ambulance.carId!=null then 1 else 0 end)-count(ambulanceStockDetail) " +
                                "from OrganizationModel org " +
                                "left join AmbulanceModel ambulance on ambulance.orgId=org.orgId), " +
                                "event.eventTime) " +
                                "from AmbulanceStockDetailModel ambulanceStockDetail " +
                                "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
                                "where event.eventTime<=:endTime " +
                                "group by ambulanceStockDetail.triggerEvent, ambulanceStockDetail.containerId")
                        .setParameter("endTime", endTime)
                        .getResultList();
//                for (OrganizationModel organizationModel : queriedOrganizationModels) {
//                    List<Long> remainNumbers = session
//                            .createQuery("select count(ambulanceStockDetail) " +
//                                    "from AmbulanceStockDetailModel ambulanceStockDetail " +
//                                    "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
//                                    "where ambulanceStockDetail.ambulanceOrg=:orgId and event.eventTime<=:endTime and ambulanceStockDetail.ambulanceOrg=ambulanceStockDetail.containerOrg " +
//                                    "group by event.eventId", Long.class)
//                            .setParameter("orgId", organizationModel.getOrgId())
//                            .setParameter("endTime", endTime)
//                            .getResultList();
//                    long orgTotal = (long) session
//                            .createQuery("select count(ambulance) " +
//                                    "from AmbulanceModel ambulance " +
//                                    "where ambulance.orgId=:orgId")
//                            .setParameter("orgId", organizationModel.getOrgId())
//                            .uniqueResult();
////                    List<Long> stockTotals = session
////                            .createQuery("select count(ambulanceStockDetail) " +
////                                    "from AmbulanceStockDetailModel ambulanceStockDetail " +
////                                    "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId and event.eventTime<=:endTime " +
////                                    "group by event.eventId", Long.class)
////                            .setParameter("endTime", endTime)
////                            .getResultList();
//                    List<Date> timeOfStocks = session
//                            .createQuery("select event.eventTime " +
//                                    "from AmbulanceStockDetailModel ambulanceStockDetail " +
//                                    "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
//                                    "where event.eventTime<=:endTime " +
//                                    "group by event.eventId", Date.class)
//                            .setParameter("endTime", endTime)
//                            .getResultList();
//                    for (int i = 0; i < remainNumbers.size(); i++) {
//                        AmbulanceStockDto2 ambulanceStockDto2 = new AmbulanceStockDto2();
//                        ambulanceStockDto2.setOrgId(organizationModel.getOrgId());
//                        ambulanceStockDto2.setDepartment(organizationModel.getName());
//                        ambulanceStockDto2.setRemainNumber(remainNumbers.get(i));
//                        ambulanceStockDto2.setOutNumber(orgTotal - ambulanceStockDto2.getRemainNumber());
////                        ambulanceStockDto2.setVisitedNumber(stockTotals.get(i) - ambulanceStockDto2.getRemainNumber());
//                        ambulanceStockDto2.setTime(timeOfStocks.get(i));
//                        ambulanceStockDto2s.add(ambulanceStockDto2);
//                    }
//
//                }
                break;
            case 2:
                ambulanceStockDto2s = session
                        .createQuery("select new cn.edu.njupt.zx120.dto.serviceManage.AmbulanceStockDto2(ambulanceStockDetail.containerOrg, ambulanceStockDetail.containerOrgName, " +
                                "sum(case when ambulanceStockDetail.containerOrg=ambulanceStockDetail.ambulanceOrg then 1 else 0 end), " +
                                "sum(case when ambulanceStockDetail.containerOrg!=ambulanceStockDetail.ambulanceOrg then 1 else 0 end), " +
                                "(select sum(case when ambulance.carId!=null then 1 else 0 end)-count(ambulanceStockDetail) " +
                                "from OrganizationModel org " +
                                "left join AmbulanceModel ambulance on ambulance.orgId=org.orgId), " +
                                "event.eventTime) " +
                                "from AmbulanceStockDetailModel ambulanceStockDetail " +
                                "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
                                "where event.eventTime>=:startTime " +
                                "group by ambulanceStockDetail.triggerEvent, ambulanceStockDetail.containerId")
                        .setParameter("startTime", startTime)
                        .getResultList();
//                for (OrganizationModel organizationModel : queriedOrganizationModels) {
//                    List<Long> remainNumbers = session
//                            .createQuery("select count(ambulanceStockDetail) " +
//                                    "from AmbulanceStockDetailModel ambulanceStockDetail " +
//                                    "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId and ambulanceStockDetail.ambulanceOrg=ambulanceStockDetail.containerOrg " +
//                                    "where ambulanceStockDetail.ambulanceOrg=:orgId and event.eventTime>=:startTime " +
//                                    "group by event.eventId", Long.class)
//                            .setParameter("startTime", startTime)
//                            .setParameter("orgId", organizationModel.getOrgId())
//                            .getResultList();
//                    long orgTotal = (long) session
//                            .createQuery("select count(ambulance) " +
//                                    "from AmbulanceModel ambulance " +
//                                    "where ambulance.orgId=:orgId")
//                            .setParameter("orgId", organizationModel.getOrgId())
//                            .uniqueResult();
//                    List<Date> timeOfStocks = session
//                            .createQuery("select event.eventTime " +
//                                    "from AmbulanceStockDetailModel ambulanceStockDetail " +
//                                    "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
//                                    "where event.eventTime>=:startTime and ambulanceStockDetail.ambulanceOrg=:orgId " +
//                                    "group by event.eventId", Date.class)
//                            .setParameter("startTime", startTime)
//                            .setParameter("orgId", organizationModel.getOrgId())
//                            .getResultList();
////                    List<Long> stockTotals = session
////                            .createQuery("select count(ambulanceStockDetail) " +
////                                    "from AmbulanceStockDetailModel ambulanceStockDetail " +
////                                    "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId and event.eventTime>=:startTime " +
////                                    "group by event.eventId")
////                            .setParameter("startTime", startTime)
////                            .getResultList();
//
//                    for (int i = 0; i < remainNumbers.size(); i++) {
//                        AmbulanceStockDto2 ambulanceStockDto2 = new AmbulanceStockDto2();
//                        ambulanceStockDto2.setOrgId(organizationModel.getOrgId());
//                        ambulanceStockDto2.setDepartment(organizationModel.getName());
//                        ambulanceStockDto2.setRemainNumber(remainNumbers.get(i));
//                        ambulanceStockDto2.setOutNumber(orgTotal - ambulanceStockDto2.getRemainNumber());
////                        ambulanceStockDto2.setVisitedNumber(stockTotals.get(i) - ambulanceStockDto2.getRemainNumber());
//                        ambulanceStockDto2.setTime(timeOfStocks.get(i));
//                        ambulanceStockDto2s.add(ambulanceStockDto2);
//                    }
//
//                }
                break;
            case 3:
                ambulanceStockDto2s = session
                        .createQuery("select new cn.edu.njupt.zx120.dto.serviceManage.AmbulanceStockDto2(ambulanceStockDetail.containerOrg, ambulanceStockDetail.containerOrgName, " +
                                "sum(case when ambulanceStockDetail.containerOrg=ambulanceStockDetail.ambulanceOrg then 1 else 0 end), " +
                                "sum(case when ambulanceStockDetail.containerOrg!=ambulanceStockDetail.ambulanceOrg then 1 else 0 end), " +
                                "(select sum(case when ambulance.carId!=null then 1 else 0 end)-count(ambulanceStockDetail) " +
                                "from OrganizationModel org " +
                                "left join AmbulanceModel ambulance on ambulance.orgId=org.orgId), " +
                                "event.eventTime) " +
                                "from AmbulanceStockDetailModel ambulanceStockDetail " +
                                "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
                                "where event.eventTime>=:startTime and event.eventTime<=:endTime " +
                                "group by ambulanceStockDetail.triggerEvent, ambulanceStockDetail.containerId")
                        .setParameter("startTime", startTime)
                        .setParameter("endTime", endTime)
                        .getResultList();
//                for (OrganizationModel organizationModel : queriedOrganizationModels) {
//                    List<Long> remainNumbers = session
//                            .createQuery("select count(ambulanceStockDetail) " +
//                                    "from AmbulanceStockDetailModel ambulanceStockDetail " +
//                                    "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId and ambulanceStockDetail.ambulanceOrg=ambulanceStockDetail.containerOrg " +
//                                    "where ambulanceStockDetail.ambulanceOrg=:orgId and event.eventTime>=:startTime and event.eventTime<=:endTime " +
//                                    "group by event.eventId", Long.class)
//                            .setParameter("orgId", organizationModel.getOrgId())
//                            .setParameter("startTime", startTime)
//                            .setParameter("endTime", endTime)
//                            .getResultList();
//                    long orgTotal = (long) session
//                            .createQuery("select count(ambulance) " +
//                                    "from AmbulanceModel ambulance " +
//                                    "where ambulance.orgId=:orgId")
//                            .setParameter("orgId", organizationModel.getOrgId())
//                            .uniqueResult();
////                    List<Long> stockTotals = session
////                            .createQuery("select count(ambulanceStockDetail) " +
////                                    "from AmbulanceStockDetailModel ambulanceStockDetail " +
////                                    "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
////                                    "where event.eventTime>=:startTime and event.eventTime<=:endTime " +
////                                    "group by event.eventId", Long.class)
////                            .setParameter("startTime", startTime)
////                            .setParameter("endTime", endTime)
////                            .getResultList();
//                    List<Date> timeOfStocks = session
//                            .createQuery("select event.eventTime " +
//                                    "from AmbulanceStockDetailModel ambulanceStockDetail " +
//                                    "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
//                                    "where event.eventTime>=:startTime and event.eventTime<=:endTime and ambulanceStockDetail.ambulanceOrg=:orgId " +
//                                    "group by event.eventId", Date.class)
//                            .setParameter("startTime", startTime)
//                            .setParameter("endTime", endTime)
//                            .setParameter("orgId", organizationModel.getOrgId())
//                            .getResultList();
//                    for (int i = 0; i < remainNumbers.size(); i++) {
//                        AmbulanceStockDto2 ambulanceStockDto2 = new AmbulanceStockDto2();
//                        ambulanceStockDto2.setOrgId(organizationModel.getOrgId());
//                        ambulanceStockDto2.setDepartment(organizationModel.getName());
//                        ambulanceStockDto2.setRemainNumber(remainNumbers.get(i));
//                        ambulanceStockDto2.setOutNumber(orgTotal - ambulanceStockDto2.getRemainNumber());
////                        ambulanceStockDto2.setVisitedNumber(stockTotals.get(i) - ambulanceStockDto2.getRemainNumber());
//                        ambulanceStockDto2.setTime(timeOfStocks.get(i));
//                        ambulanceStockDto2s.add(ambulanceStockDto2);
//                    }

//                }
                break;
            case 4: {
                ambulanceStockDto2s = session
                        .createQuery("select new cn.edu.njupt.zx120.dto.serviceManage.AmbulanceStockDto2(ambulanceStockDetail.containerOrg, ambulanceStockDetail.containerOrgName, " +
                                "sum(case when ambulanceStockDetail.containerOrg=ambulanceStockDetail.ambulanceOrg then 1 else 0 end), " +
                                "sum(case when ambulanceStockDetail.containerOrg!=ambulanceStockDetail.ambulanceOrg then 1 else 0 end), " +
                                "(select sum(case when ambulance.carId!=null then 1 else 0 end)-count(ambulanceStockDetail) " +
                                "from OrganizationModel org " +
                                "left join AmbulanceModel ambulance on ambulance.orgId=org.orgId), " +
                                "event.eventTime) " +
                                "from AmbulanceStockDetailModel ambulanceStockDetail " +
                                "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
                                "where ambulanceStockDetail.ambulanceOrg=:orgId " +
                                "group by ambulanceStockDetail.triggerEvent, ambulanceStockDetail.containerId")
                        .setParameter("orgId", orgId)
                        .getResultList();
//                List<Long> remainNumbers = session
//                        .createQuery("select count(ambulanceStockDetail) " +
//                                "from AmbulanceStockDetailModel ambulanceStockDetail " +
//                                "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId and ambulanceStockDetail.ambulanceOrg=ambulanceStockDetail.containerOrg " +
//                                "where ambulanceStockDetail.ambulanceOrg=:orgId " +
//                                "group by event.eventId", Long.class)
//                        .setParameter("orgId", orgId)
////                            .setFirstResult(startIndex == null ? defaultStartIndex : startIndex)
////                            .setMaxResults(pageSize == null ? defaultPageSize : pageSize)
//                        .getResultList();
//                long orgTotal = (long) session
//                        .createQuery("select count(ambulance) " +
//                                "from AmbulanceModel ambulance " +
//                                "where ambulance.orgId=:orgId")
//                        .setParameter("orgId", orgId)
//                        .uniqueResult();
////                List<Long> stockTotals = session
////                        .createQuery("select count(ambulanceStockDetail) " +
////                                "from AmbulanceStockDetailModel ambulanceStockDetail " +
////                                "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
////                                "group by event.eventId")
////                        .getResultList();
//                List<Date> timeOfStocks = session
//                        .createQuery("select event.eventTime " +
//                                "from AmbulanceStockDetailModel ambulanceStockDetail " +
//                                "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
//                                "where ambulanceStockDetail.ambulanceOrg=:orgId " +
//                                "group by event.eventId", Date.class)
//                        .setParameter("orgId", orgId)
//                        .getResultList();
//                for (int i = 0; i < remainNumbers.size(); i++) {
//                    AmbulanceStockDto2 ambulanceStockDto2 = new AmbulanceStockDto2();
//                    ambulanceStockDto2.setOrgId(orgId);
//                    ambulanceStockDto2.setDepartment(organizationDao.queryById(orgId).getName());
//                    ambulanceStockDto2.setRemainNumber(remainNumbers.get(i));
//                    ambulanceStockDto2.setOutNumber(orgTotal - ambulanceStockDto2.getRemainNumber());
////                    ambulanceStockDto2.setVisitedNumber(stockTotals.get(i) - ambulanceStockDto2.getRemainNumber());
//                    ambulanceStockDto2.setTime(timeOfStocks.get(i));
//                    ambulanceStockDto2s.add(ambulanceStockDto2);
//                }
            }
                break;
            case 5: {
                ambulanceStockDto2s = session
                        .createQuery("select new cn.edu.njupt.zx120.dto.serviceManage.AmbulanceStockDto2(ambulanceStockDetail.containerOrg, ambulanceStockDetail.containerOrgName, " +
                                "sum(case when ambulanceStockDetail.containerOrg=ambulanceStockDetail.ambulanceOrg then 1 else 0 end), " +
                                "sum(case when ambulanceStockDetail.containerOrg!=ambulanceStockDetail.ambulanceOrg then 1 else 0 end), " +
                                "(select sum(case when ambulance.carId!=null then 1 else 0 end)-count(ambulanceStockDetail) " +
                                "from OrganizationModel org " +
                                "left join AmbulanceModel ambulance on ambulance.orgId=org.orgId), " +
                                "event.eventTime) " +
                                "from AmbulanceStockDetailModel ambulanceStockDetail " +
                                "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
                                "where ambulanceStockDetail.ambulanceOrg=:orgId and event.eventTime<=:endTime " +
                                "group by ambulanceStockDetail.triggerEvent, ambulanceStockDetail.containerId")
                        .setParameter("orgId", orgId)
                        .setParameter("endTime", endTime)
                        .getResultList();
//                List<Long> remainNumbers = session
//                        .createQuery("select count(ambulanceStockDetail) " +
//                                "from AmbulanceStockDetailModel ambulanceStockDetail " +
//                                "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
//                                "where ambulanceStockDetail.ambulanceOrg=:orgId and event.eventTime<=:endTime and ambulanceStockDetail.ambulanceOrg=ambulanceStockDetail.containerOrg " +
//                                "group by event.eventId", Long.class)
//                        .setParameter("orgId", orgId)
//                        .setParameter("endTime", endTime)
//                        .getResultList();
//                long orgTotal = (long) session
//                        .createQuery("select count(ambulance) " +
//                                "from AmbulanceModel ambulance " +
//                                "where ambulance.orgId=:orgId")
//                        .setParameter("orgId", orgId)
//                        .uniqueResult();
////                List<Long> stockTotals = session
////                        .createQuery("select count(ambulanceStockDetail) " +
////                                "from AmbulanceStockDetailModel ambulanceStockDetail " +
////                                "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId and event.eventTime<=:endTime " +
////                                "group by event.eventId", Long.class)
////                        .setParameter("endTime", endTime)
////                        .getResultList();
//                List<Date> timeOfStocks = session
//                        .createQuery("select event.eventTime " +
//                                "from AmbulanceStockDetailModel ambulanceStockDetail " +
//                                "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
//                                "where event.eventTime<=:endTime and ambulanceStockDetail.ambulanceOrg=:orgId " +
//                                "group by event.eventId", Date.class)
//                        .setParameter("endTime", endTime)
//                        .setParameter("orgId", orgId)
//                        .getResultList();
//                for (int i = 0; i < remainNumbers.size(); i++) {
//                    AmbulanceStockDto2 ambulanceStockDto2 = new AmbulanceStockDto2();
//                    ambulanceStockDto2.setOrgId(orgId);
//                    ambulanceStockDto2.setDepartment(organizationDao.queryById(orgId).getName());
//                    ambulanceStockDto2.setRemainNumber(remainNumbers.get(i));
//                    ambulanceStockDto2.setOutNumber(orgTotal - ambulanceStockDto2.getRemainNumber());
////                    ambulanceStockDto2.setVisitedNumber(stockTotals.get(i) - ambulanceStockDto2.getRemainNumber());
//                    ambulanceStockDto2.setTime(timeOfStocks.get(i));
//                    ambulanceStockDto2s.add(ambulanceStockDto2);
//                }
            }
                break;
            case 6: {
                ambulanceStockDto2s = session
                        .createQuery("select new cn.edu.njupt.zx120.dto.serviceManage.AmbulanceStockDto2(ambulanceStockDetail.containerOrg, ambulanceStockDetail.containerOrgName, " +
                                "sum(case when ambulanceStockDetail.containerOrg=ambulanceStockDetail.ambulanceOrg then 1 else 0 end), " +
                                "sum(case when ambulanceStockDetail.containerOrg!=ambulanceStockDetail.ambulanceOrg then 1 else 0 end), " +
                                "(select sum(case when ambulance.carId!=null then 1 else 0 end)-count(ambulanceStockDetail) " +
                                "from OrganizationModel org " +
                                "left join AmbulanceModel ambulance on ambulance.orgId=org.orgId), " +
                                "event.eventTime) " +
                                "from AmbulanceStockDetailModel ambulanceStockDetail " +
                                "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
                                "where ambulanceStockDetail.ambulanceOrg=:orgId and event.eventTime>=:startTime " +
                                "group by ambulanceStockDetail.triggerEvent, ambulanceStockDetail.containerId")
                        .setParameter("orgId", orgId)
                        .setParameter("startTime", startTime)
                        .getResultList();
//                List<Long> remainNumbers = session
//                        .createQuery("select count(ambulanceStockDetail) " +
//                                "from AmbulanceStockDetailModel ambulanceStockDetail " +
//                                "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
//                                "where ambulanceStockDetail.ambulanceOrg=:orgId and event.eventTime>=:startTime and ambulanceStockDetail.ambulanceOrg=ambulanceStockDetail.containerOrg " +
//                                "group by event.eventId", Long.class)
//                        .setParameter("startTime", startTime)
//                        .setParameter("orgId", orgId)
//                        .getResultList();
//                long orgTotal = (long) session
//                        .createQuery("select count(ambulance) " +
//                                "from AmbulanceModel ambulance " +
//                                "where ambulance.orgId=:orgId")
//                        .setParameter("orgId", orgId)
//                        .uniqueResult();
////                List<Long> stockTotals = session
////                        .createQuery("select count(ambulanceStockDetail) " +
////                                "from AmbulanceStockDetailModel ambulanceStockDetail " +
////                                "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId and event.eventTime>=:startTime " +
////                                "group by event.eventId", Long.class)
////                        .setParameter("startTime", startTime)
////                        .getResultList();
//                List<Date> timeOfStocks = session
//                        .createQuery("select event.eventTime " +
//                                "from AmbulanceStockDetailModel ambulanceStockDetail " +
//                                "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
//                                "where event.eventTime>=:startTime and ambulanceStockDetail.ambulanceOrg=:orgId " +
//                                "group by event.eventId", Date.class)
//                        .setParameter("startTime", startTime)
//                        .setParameter("orgId", orgId)
//                        .getResultList();
//                for (int i = 0; i < remainNumbers.size(); i++) {
//                    AmbulanceStockDto2 ambulanceStockDto2 = new AmbulanceStockDto2();
//                    ambulanceStockDto2.setOrgId(orgId);
//                    ambulanceStockDto2.setDepartment(organizationDao.queryById(orgId).getName());
//                    ambulanceStockDto2.setRemainNumber(remainNumbers.get(i));
//                    ambulanceStockDto2.setOutNumber(orgTotal - ambulanceStockDto2.getRemainNumber());
////                    ambulanceStockDto2.setVisitedNumber(stockTotals.get(i) - ambulanceStockDto2.getRemainNumber());
//                    ambulanceStockDto2.setTime(timeOfStocks.get(i));
//                    ambulanceStockDto2s.add(ambulanceStockDto2);
//                }
            }
                break;
            case 7: {
                ambulanceStockDto2s = session
                        .createQuery("select new cn.edu.njupt.zx120.dto.serviceManage.AmbulanceStockDto2(ambulanceStockDetail.containerOrg, ambulanceStockDetail.containerOrgName, " +
                                "sum(case when ambulanceStockDetail.containerOrg=ambulanceStockDetail.ambulanceOrg then 1 else 0 end), " +
                                "sum(case when ambulanceStockDetail.containerOrg!=ambulanceStockDetail.ambulanceOrg then 1 else 0 end), " +
                                "(select sum(case when ambulance.carId!=null then 1 else 0 end)-count(ambulanceStockDetail) " +
                                "from OrganizationModel org " +
                                "left join AmbulanceModel ambulance on ambulance.orgId=org.orgId), " +
                                "event.eventTime) " +
                                "from AmbulanceStockDetailModel ambulanceStockDetail " +
                                "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
                                "where ambulanceStockDetail.ambulanceOrg=:orgId and event.eventTime>=:startTime and event.eventTime<=:endTime " +
                                "group by ambulanceStockDetail.triggerEvent, ambulanceStockDetail.containerId")
                        .setParameter("orgId", orgId)
                        .setParameter("startTime", startTime)
                        .setParameter("endTime", endTime)
                        .getResultList();
//                List<Long> remainNumbers = session
//                        .createQuery("select count(ambulanceStockDetail) " +
//                                "from AmbulanceStockDetailModel ambulanceStockDetail " +
//                                "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
//                                "where ambulanceStockDetail.ambulanceOrg=:orgId and event.eventTime>=:startTime and event.eventTime<=:endTime and ambulanceStockDetail.ambulanceOrg=ambulanceStockDetail.containerOrg " +
//                                "group by event.eventId", Long.class)
//                        .setParameter("orgId", orgId)
//                        .setParameter("startTime", startTime)
//                        .setParameter("endTime", endTime)
//                        .getResultList();
//                long orgTotal = (long) session
//                        .createQuery("select count(ambulance) " +
//                                "from AmbulanceModel ambulance " +
//                                "where ambulance.orgId=:orgId")
//                        .setParameter("orgId", orgId)
//                        .uniqueResult();
////                List<Long> stockTotals = session
////                        .createQuery("select count(ambulanceStockDetail) " +
////                                "from AmbulanceStockDetailModel ambulanceStockDetail " +
////                                "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
////                                "where event.eventTime>=:startTime and event.eventTime<=:endTime " +
////                                "group by event.eventId", Long.class)
////                        .setParameter("startTime", startTime)
////                        .setParameter("endTime", endTime)
////                        .getResultList();
//                List<Date> timeOfStocks = session
//                        .createQuery("select event.eventTime " +
//                                "from AmbulanceStockDetailModel ambulanceStockDetail " +
//                                "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
//                                "where event.eventTime>=:startTime and event.eventTime<=:endTime and ambulanceStockDetail.ambulanceOrg=:orgId " +
//                                "group by event.eventId", Date.class)
//                        .setParameter("startTime", startTime)
//                        .setParameter("endTime", endTime)
//                        .setParameter("orgId", orgId)
//                        .getResultList();
//                for (int i = 0; i < remainNumbers.size(); i++) {
//                    AmbulanceStockDto2 ambulanceStockDto2 = new AmbulanceStockDto2();
//                    ambulanceStockDto2.setOrgId(orgId);
//                    ambulanceStockDto2.setDepartment(orgId);
//                    ambulanceStockDto2.setRemainNumber(remainNumbers.get(i));
//                    ambulanceStockDto2.setOutNumber(orgTotal - ambulanceStockDto2.getRemainNumber());
////                    ambulanceStockDto2.setVisitedNumber(stockTotals.get(i) - ambulanceStockDto2.getRemainNumber());
//                    ambulanceStockDto2.setTime(timeOfStocks.get(i));
//                    ambulanceStockDto2s.add(ambulanceStockDto2);
//                }
            }
            break;

        }

        return ambulanceStockDto2s;
    }

    @Override
    public long queryStock2InfoTotalByCondition(String orgId,
                                               Date startTime, Date endTime,
                                               Integer startIndex, Integer pageSize) {
        Session session = getSession();
        session.beginTransaction();
        long total = 0;
        int conditionCode = ((orgId != null ? 1 : 0) << 2)
                + ((startTime != null ? 1 : 0) << 1)
                + ((endTime != null ? 1 : 0));

        List<OrganizationModel> organizationModels = null;

        if (orgId == null) {
            organizationModels = organizationDao.queryAll();
        }

        switch (conditionCode) {
            case 0:
                for (OrganizationModel organizationModel : organizationModels) {
                    total += session
                            .createQuery("select count(ambulanceStockDetail) " +
                                    "from AmbulanceStockDetailModel ambulanceStockDetail " +
                                    "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
                                    "where ambulanceStockDetail.ambulanceOrg=:orgId " +
                                    "group by event.eventId, ambulanceStockDetail.containerId", Long.class)
                            .setParameter("orgId", organizationModel.getOrgId())
                            .getResultList().size();
                }
                break;
            case 1:
                for (OrganizationModel organizationModel : organizationModels) {
                    total += session
                            .createQuery("select count(ambulanceStockDetail) " +
                                    "from AmbulanceStockDetailModel ambulanceStockDetail " +
                                    "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
                                    "where event.eventTime<=:endTime and ambulanceStockDetail.ambulanceOrg=:orgId " +
                                    "group by event.eventId", Long.class)
                            .setParameter("endTime", endTime)
                            .setParameter("orgId", organizationModel.getOrgId())
                            .getResultList().size();
                }
                break;
            case 2:
                for (OrganizationModel organizationModel : organizationModels) {
                    total += session
                            .createQuery("select count(ambulanceStockDetail) " +
                                    "from AmbulanceStockDetailModel ambulanceStockDetail " +
                                    "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
                                    "where event.eventTime>=:startTime and ambulanceStockDetail.ambulanceOrg=:orgId " +
                                    "group by event.eventId", Long.class)
                            .setParameter("startTime", startTime)
                            .setParameter("orgId", organizationModel.getOrgId())
                            .getResultList().size();
                }
                break;
            case 3:
                for (OrganizationModel organizationModel : organizationModels) {
                    total += session
                            .createQuery("select count(ambulanceStockDetail) " +
                                    "from AmbulanceStockDetailModel ambulanceStockDetail " +
                                    "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
                                    "where event.eventTime>=:startTime and event.eventTime<=:endTime and ambulanceStockDetail.ambulanceOrg=:orgId " +
                                    "group by event.eventId ", Long.class)
                            .setParameter("startTime", startTime)
                            .setParameter("endTime", endTime)
                            .setParameter("orgId", organizationModel.getOrgId())
                            .getResultList().size();
                }
                break;
            case 4:
                total = session
                        .createQuery("select count(ambulanceStockDetail) " +
                                "from AmbulanceStockDetailModel ambulanceStockDetail " +
                                "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
                                "where ambulanceStockDetail.ambulanceOrg=:orgId " +
                                "group by event.eventId", Long.class)
                        .setParameter("orgId", orgId)
                        .getResultList().size();
                break;
            case 5:
                total = session
                        .createQuery("select count(ambulanceStockDetail) " +
                                "from AmbulanceStockDetailModel ambulanceStockDetail " +
                                "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
                                "where ambulanceStockDetail.ambulanceOrg=:orgId and event.eventTime<=:endTime " +
                                "group by event.eventId", Long.class)
                        .setParameter("orgId", orgId)
                        .setParameter("endTime", endTime)
                        .getResultList().size();
                break;
            case 6:
                total = session
                        .createQuery("select count(ambulanceStockDetail) " +
                                "from AmbulanceStockDetailModel ambulanceStockDetail " +
                                "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
                                "where ambulanceStockDetail.ambulanceOrg=:orgId and event.eventTime>=:startTime " +
                                "group by event.eventId", Long.class)
                        .setParameter("orgId", orgId)
                        .setParameter("startTime", startTime)
                        .getResultList().size();
                break;
            case 7:
                total = session
                        .createQuery("select count(ambulanceStockDetail) " +
                                "from AmbulanceStockDetailModel ambulanceStockDetail " +
                                "join EventModel event on ambulanceStockDetail.triggerEvent=event.eventId " +
                                "where ambulanceStockDetail.ambulanceOrg=:orgId " +
                                "and event.eventTime>=:startTime and event.eventTime<=:endTime " +
                                "group by event.eventId", Long.class)
                        .setParameter("orgId", orgId)
                        .setParameter("startTime", startTime)
                        .setParameter("endTime", endTime)
                        .getResultList().size();
                break;
        }
        session.getTransaction().commit();
        session.close();
        return total;
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
