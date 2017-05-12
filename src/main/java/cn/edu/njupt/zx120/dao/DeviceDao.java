package cn.edu.njupt.zx120.dao;

import cn.edu.njupt.zx120.model.DeviceModel;

import java.util.Date;
import java.util.List;

/**
 * Created by wallance on 3/5/17.
 */
public interface DeviceDao {

    List<DeviceModel> queryAll();

    List<DeviceModel> queryByDepartment(String orgId, String department);

    List<DeviceModel> queryByOrgId(String orgId);

    DeviceModel queryByDeviceId(String deviceId);

    void updateLastModifyTime(String deviceId);

    void setCreateTime(String deviceId);

    void createNewDevice(DeviceModel deviceModel);

    void deleteDevice(DeviceModel deviceModel);

}
