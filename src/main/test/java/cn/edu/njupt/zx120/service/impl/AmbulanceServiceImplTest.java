package service.impl;

import cn.edu.njupt.zx120.model.AmbulanceModel;
import cn.edu.njupt.zx120.service.AmbulanceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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
public class AmbulanceServiceImplTest {

    @Autowired
    private AmbulanceService ambulanceService;

    @Test
    public void queryAll() throws Exception {
        List<AmbulanceModel> ambulanceModels = ambulanceService.queryAll();
        for (AmbulanceModel ambulanceModel : ambulanceModels) {
            System.out.println(ambulanceModel);
        }
    }

    @Test
    public void queryByAdminCode() throws Exception {
        List<AmbulanceModel> ambulanceModels = ambulanceService.queryByAdminCode(410100);
        for (AmbulanceModel ambulanceModel : ambulanceModels) {
            System.out.println(ambulanceModel);
        }
    }

    @Test
    public void queryByOrgId() throws Exception {
        List<AmbulanceModel> ambulanceModels = ambulanceService.queryByOrgId("4101000002");
        for (AmbulanceModel ambulanceModel : ambulanceModels) {
            System.out.println(ambulanceModel);
        }
    }

    @Test
    public void addNewAmbulance() throws Exception {

    }

    @Test
    public void setAmbulanceDisabled() throws Exception {
        ambulanceService.setAmbulanceDisabled("1200000");
        queryAll();
    }

    @Test
    public void leaveContainer() throws Exception {

    }

    @Test
    public void arriveContainer() throws Exception {

    }

    @Test
    public void scanResourceInCar() throws Exception {

    }

}