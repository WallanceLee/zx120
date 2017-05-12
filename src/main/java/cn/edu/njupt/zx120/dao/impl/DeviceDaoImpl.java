package cn.edu.njupt.zx120.dao.impl;

import cn.edu.njupt.zx120.dao.AbstractDao;
import cn.edu.njupt.zx120.dao.DeviceDao;
import cn.edu.njupt.zx120.model.DeviceModel;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by wallance on 3/5/17.
 */
@Repository("deviceDao")
public class DeviceDaoImpl extends AbstractDao<Integer, DeviceModel> implements DeviceDao {

    @Override
    public List<DeviceModel> queryAll() {
        Session session = getSession();
        session.beginTransaction();
        List<DeviceModel> deviceModels = session
                .createQuery("from DeviceModel device", DeviceModel.class)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return deviceModels;
    }

    @Override
    public List<DeviceModel> queryByDepartment(String orgId, String department) {
        Session session = getSession();
        session.beginTransaction();
        List<DeviceModel> deviceModels = session
                .createQuery("from DeviceModel device where device.orgId=:orgId and device.department=:department", DeviceModel.class)
                .setParameter("orgId", orgId)
                .setParameter("department", department)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return deviceModels;
    }

    @Override
    public List<DeviceModel> queryByOrgId(String orgId) {
        Session session = getSession();
        session.beginTransaction();
        List<DeviceModel> deviceModels = session
                .createQuery("from DeviceModel device where device.orgId=:orgId", DeviceModel.class)
                .setParameter("orgId", orgId)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return deviceModels;
    }

    @Override
    public DeviceModel queryByDeviceId(String deviceId) {
        Session session = getSession();
        session.beginTransaction();
        DeviceModel deviceModel = session
                .createQuery("from DeviceModel device where device.deviceId=:deviceId", DeviceModel.class)
                .setParameter("deviceId", deviceId)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return deviceModel;
    }

    @Override
    public void updateLastModifyTime(String deviceId) {
        Session session = getSession();
        session.beginTransaction();
        session
                .createQuery("update DeviceModel device set device.lastModifyTime=:lastModifyTime where device.deviceId=:deviceId")
                .setParameter("lastModifyTime", new Date())
                .setParameter("deviceId", deviceId).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void setCreateTime(String deviceId) {
        Session session = getSession();
        session.beginTransaction();
        session
                .createQuery("update DeviceModel device set device.createTime=:createTime where device.deviceId=:deviceId")
                .setParameter("createTime", new Date())
                .setParameter("deviceId", deviceId).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void createNewDevice(DeviceModel deviceModel) {
        super.save(deviceModel);
    }

    @Override
    public void deleteDevice(DeviceModel deviceModel) {
        super.delete(deviceModel);
    }

}
