package cn.edu.njupt.zx120.service.impl;

import cn.edu.njupt.zx120.dao.ContainerDao;
import cn.edu.njupt.zx120.dao.DeviceDao;
import cn.edu.njupt.zx120.dao.EventDao;
import cn.edu.njupt.zx120.dao.ResourceBindingDao;
import cn.edu.njupt.zx120.dao.StockDao;
import cn.edu.njupt.zx120.dto.serviceManage.DeviceDynamicDto;
import cn.edu.njupt.zx120.dto.serviceManage.DeviceStockDto;
import cn.edu.njupt.zx120.model.ContainerModel;
import cn.edu.njupt.zx120.model.ContainerTypeEnum;
import cn.edu.njupt.zx120.model.DeviceModel;
import cn.edu.njupt.zx120.model.EventModel;
import cn.edu.njupt.zx120.model.EventTypeEnum;
import cn.edu.njupt.zx120.model.ResourceTableTypeEnum;
import cn.edu.njupt.zx120.model.StockModel;
import cn.edu.njupt.zx120.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wallance on 3/5/17.
 */
@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceDao deviceDao;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private ContainerDao containerDao;

    @Autowired
    private StockDao stockDao;

    @Autowired
    private ResourceBindingDao resourceBindingDao;

    // 查询所有设备信息
    @Override
    public List<DeviceModel> queryAll() {
        return deviceDao.queryAll();
    }

    // 根据机构id和部门名称查询设备信息
    @Override
    public List<DeviceModel> queryByDepartment(String orgId, String department) {
        return deviceDao.queryByDepartment(orgId, department);
    }

    // 根据机构id查询设备信息
    @Override
    public List<DeviceModel> queryByOrgId(String orgId) {
        return deviceDao.queryByOrgId(orgId);
    }

    // 更新最后修改时间
    @Override
    public void updateLastModifyTime(String id) {
        deviceDao.updateLastModifyTime(id);
    }

    // 设置创建时间
    @Override
    public void setCreateTime(String id) {
        deviceDao.setCreateTime(id);
    }

    // 录入新的设备
    @Override
    public void createNewDevice(DeviceModel deviceModel) {
        deviceDao.createNewDevice(deviceModel);
    }

    // 删除设备
    // 事件表中记录相关信息
    @Override
    public void deleteDevice(DeviceModel deviceModel) {
        deviceDao.deleteDevice(deviceModel);
    }

    @Override
    // 添加事务支持
    // 防止只删除了一个表的数据
    @Transactional
    public void leaveStock(String deviceId) {

        // delete container item
        ContainerModel containerModel = containerDao.queryByResourceId(deviceId, ResourceTableTypeEnum.DEVICE.getType());
        // 该设备不在容器中
        // 抛出设备不在容器异常
        if (containerModel == null) {

        }
        containerDao.deleteItem(containerModel);

        // delete stock item
        StockModel stockModel = stockDao.queryByRfidTag(
                resourceBindingDao.queryByResourceId(ResourceTableTypeEnum.DEVICE.getType(), deviceId).getTag()
        );
        // 该设备不在库存数据表中
        // 抛出数倍不在库存异常
        if (stockModel == null) {

        }
        stockDao.deleteItem(stockModel);

        // add event record
        EventModel eventModel = new EventModel();
        eventModel.setEventResourceId(deviceId);
        eventModel.setEventTime(new Date());
//        eventModel.setContainerId("");
        eventModel.setEventResourceTable(ResourceTableTypeEnum.DEVICE.getType());
        eventModel.setEventType(EventTypeEnum.DEVICE_LEAVE_STOCK.getType());
        eventDao.createNewEvent(eventModel);

    }

    // 设备处于未知状态
    @Override
    @Transactional
    public void unknownDevice(String deviceId) {

        // delete container item
        ContainerModel containerModel = containerDao.queryByResourceId(deviceId, ResourceTableTypeEnum.DEVICE.getType());
        // 该设备不在容器中
        // 抛出设备不在容器异常
        if (containerModel == null) {

        }
        containerDao.deleteItem(containerModel);

        // delete stock item
        StockModel stockModel = stockDao.queryByRfidTag(
                resourceBindingDao.queryByResourceId(ResourceTableTypeEnum.DEVICE.getType(), deviceId).getTag()
        );
        // 该设备不在库存数据表中
        // 抛出数倍不在库存异常
        if (stockModel == null) {

        }
        stockDao.deleteItem(stockModel);

        // add event record
        EventModel eventModel = new EventModel();
        eventModel.setEventResourceId(deviceId);
        eventModel.setEventTime(new Date());
//        eventModel.setContainerId("");
        eventModel.setEventResourceTable(ResourceTableTypeEnum.DEVICE.getType());
        eventModel.setEventType(EventTypeEnum.DEVICE_UNKNOWN.getType());
        eventDao.createNewEvent(eventModel);

    }

    @Override
    @Transactional
    public void onAmbulance(String deviceId, String carId) {

        // delete container item
        ContainerModel containerModel = containerDao.queryByResourceId(deviceId, ResourceTableTypeEnum.DEVICE.getType());
        // 该设备不在容器中
        // 抛出设备不在容器异常
        if (containerModel == null) {

        }
        containerDao.deleteItem(containerModel);

        // add new container item
//        containerModel.setContainerType(ContainerTypeEnum.AMBULANCE.getType());
        containerModel.setLastScanTime(new Date());
//        containerModel.setContainerId(carId);
        containerModel.setResourceTable(ResourceTableTypeEnum.AMBULANCE.getType());
        containerDao.addNewItem(containerModel);

        // delete stock item
        StockModel stockModel = stockDao.queryByRfidTag(
                resourceBindingDao.queryByResourceId(ResourceTableTypeEnum.DEVICE.getType(), deviceId).getTag()
        );
        // 该设备不在库存数据表中
        // 抛出数倍不在库存异常
        if (stockModel == null) {

        }
        stockDao.deleteItem(stockModel);

        // add event record
        EventModel eventModel = new EventModel();
        eventModel.setEventResourceId(deviceId);
        eventModel.setEventTime(new Date());
//        eventModel.setContainerId(carId);
        eventModel.setEventResourceTable(ResourceTableTypeEnum.DEVICE.getType());
        eventModel.setEventType(EventTypeEnum.DEVICE_ON_AMBULANCE.getType());
        eventDao.createNewEvent(eventModel);
    }

    @Override
    @Transactional
    public void returnStock(String deviceId, String containerId) {

        // delete car container item
        ContainerModel containerModel = containerDao.queryByResourceId(deviceId, ResourceTableTypeEnum.DEVICE.getType());
        // 该设备不在容器中
        // 抛出设备不在容器异常
        if (containerModel == null) {

        }
        containerDao.deleteItem(containerModel);

        // add container item
//        containerModel.setContainerType("");
        containerModel.setResourceTable(ResourceTableTypeEnum.DEVICE.getType());
//        containerModel.setContainerId(deviceId);
        containerModel.setLastScanTime(new Date());
        containerDao.addNewItem(containerModel);

        // add stock item
        StockModel stockModel = new StockModel();
        stockModel.setRfidTag(
                resourceBindingDao.queryByResourceId(ResourceTableTypeEnum.DEVICE.getType(), deviceId).getTag()
        );
//        stockModel.setContainerId(containerId);
        stockDao.addItem(stockModel);

        // add event record
        EventModel eventModel = new EventModel();
        eventModel.setEventResourceId(deviceId);
        eventModel.setEventTime(new Date());
//        eventModel.setContainerId(containerId);
        eventModel.setEventResourceTable(ResourceTableTypeEnum.DEVICE.getType());
        eventModel.setEventType(EventTypeEnum.DEVICE_ARRIVE_STOCK.getType());
        eventDao.createNewEvent(eventModel);
    }

    @Override
    public List<DeviceDynamicDto> queryAllDynamicInfo() {

        List<DeviceModel> deviceModels = deviceDao.queryAll();
        List<DeviceDynamicDto> deviceDynamicDtos = new ArrayList<>();

        for (DeviceModel deviceModel : deviceModels) {
            List<EventModel> eventModels = eventDao.queryByResourceId(deviceModel.getDeviceId(), ResourceTableTypeEnum.DEVICE.getType());
            if (eventModels.size() == 0)
                continue;
            for (EventModel eventModel : eventModels) {
                DeviceDynamicDto deviceDynamicDto = new DeviceDynamicDto();

            }
        }

        return null;
    }

    @Override
    public List<DeviceStockDto> queryAllStockInfo() {
        return null;
    }
}
