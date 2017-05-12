package cn.edu.njupt.zx120.service;

import cn.edu.njupt.zx120.dto.serviceManage.DeviceDynamicDto;
import cn.edu.njupt.zx120.dto.serviceManage.DeviceStockDto;
import cn.edu.njupt.zx120.model.DeviceModel;

import java.util.List;
import java.util.Date;

/**
 * Created by wallance on 3/5/17.
 */
public interface DeviceService {

    List<DeviceModel> queryAll();

    List<DeviceModel> queryByDepartment(String orgId, String department);

    List<DeviceModel> queryByOrgId(String orgId);

    void updateLastModifyTime(String id);

    void setCreateTime(String id);

    void createNewDevice(DeviceModel deviceModel);

    void deleteDevice(DeviceModel deviceModel);

    void leaveStock(String deviceId);

    void unknownDevice(String deviceId);

    void onAmbulance(String deviceId, String carId);

    void returnStock(String deviceId, String containerId);

    List<DeviceDynamicDto> queryAllDynamicInfo();

    List<DeviceStockDto> queryAllStockInfo();

}
