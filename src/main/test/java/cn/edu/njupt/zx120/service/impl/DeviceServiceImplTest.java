package service.impl;

import cn.edu.njupt.zx120.model.DeviceModel;
import cn.edu.njupt.zx120.service.DeviceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by wallance on 3/18/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
//        "classpath:ehcache.xml",
        "classpath:hibernate.cfg.xml",
        "classpath:spring-bean.xml",
        "classpath:spring-dao.xml",
        "classpath:spring-web.xml"
})
public class DeviceServiceImplTest {

    @Autowired
    private DeviceService deviceService;

    @Test
    public void queryAll() throws Exception {
        List<DeviceModel> deviceModels = deviceService.queryAll();
        for (DeviceModel deviceModel : deviceModels) {
            System.out.println(deviceModel);
        }
    }

    @Test
    public void queryByDepartment() throws Exception {
        List<DeviceModel> deviceModels = deviceService.queryByDepartment("4101002149", "急诊部");
        for (DeviceModel deviceModel : deviceModels) {
            System.out.println(deviceModel);
        }
    }

    @Test
    public void queryByOrgId() throws Exception {
        List<DeviceModel> deviceModels = deviceService.queryByOrgId("4101002149");
        for (DeviceModel deviceModel : deviceModels) {
            System.out.println(deviceModel);
        }
    }

    @Test
    public void updateLastModifyTime() throws Exception {
        deviceService.updateLastModifyTime("1200002");
        deviceService.queryByOrgId("1200002");
    }

    @Test
    public void setCreateTime() throws Exception {

    }

    @Test
    public void createNewDevice() throws Exception {

    }

    @Test
    public void deleteDevice() throws Exception {

    }

    @Test
    public void leaveStock() throws Exception {
        deviceService.leaveStock("1200000");
    }

    @Test
    public void unknownDevice() throws Exception {

    }

    @Test
    public void onAmbulance() throws Exception {

    }

    @Test
    public void returnStock() throws Exception {

    }

}