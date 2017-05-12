package dao.impl;

import cn.edu.njupt.zx120.dao.DeviceDao;
import cn.edu.njupt.zx120.model.DeviceModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by wallance on 3/16/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-dao.xml", "classpath:hibernate.cfg.xml"})
@Transactional
public class DeviceDaoImplTest {

    @Autowired
    private DeviceDao deviceDao;

    @Test
    public void queryAll() throws Exception {
        List<DeviceModel> deviceModels = deviceDao.queryAll();
        for (DeviceModel deviceModel : deviceModels) {
            System.out.println(deviceModel);
        }
    }

    @Test
    public void queryByDepartment() throws Exception {

    }

    @Test
    public void queryByOrgId() throws Exception {
        List<DeviceModel> deviceModels = deviceDao.queryByOrgId("4101002149");
        for (DeviceModel deviceModel : deviceModels) {
            System.out.println(deviceModel);
        }
    }

    @Test
    public void queryByDeviceId() throws Exception {

    }

    @Test
    public void updateLastModifyTime() throws Exception {
        deviceDao.updateLastModifyTime("1200000");
    }

    @Test
    public void setCreateTime() throws Exception {
        deviceDao.setCreateTime("1200001");
    }

    @Test
    public void createNewDevice() throws Exception {
        DeviceModel deviceModel = new DeviceModel();
        deviceModel.setCreateTime(new Date());
        deviceModel.setDeviceName("担架");
        deviceModel.setDeviceId("1200012");
        deviceModel.setDepartment("器材室");
        deviceModel.setLastModifyTime(deviceModel.getCreateTime());
        deviceModel.setOrgId("4101002149");
        deviceDao.createNewDevice(deviceModel);
    }

    @Test
    public void deleteDevice() throws Exception {
        DeviceModel deviceModel = deviceDao.queryByDeviceId("1200005");
        deviceDao.deleteDevice(deviceModel);
    }

}