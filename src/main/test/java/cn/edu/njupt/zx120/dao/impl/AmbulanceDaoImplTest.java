package dao.impl;

import cn.edu.njupt.zx120.dao.AmbulanceDao;
import cn.edu.njupt.zx120.model.AmbulanceModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by wallance on 3/15/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-dao.xml", "classpath:hibernate.cfg.xml"})
@Transactional
public class AmbulanceDaoImplTest {

    @Autowired
    private AmbulanceDao ambulanceDao;

    @Test
    public void queryById() throws Exception {
        AmbulanceModel ambulanceModel = ambulanceDao.queryById("008");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        Date now = sdf.parse("2017-03-15 16:23:54");
//        Date current = ambulanceModel.getCreateTime();
//        assertSame(now, current);
        System.out.println(ambulanceModel);
    }

    @Test
    public void queryByOrgId() throws Exception {
        List<AmbulanceModel> ambulanceModelList = ambulanceDao.queryByOrgId("4101000002");
        System.out.println(ambulanceModelList);
    }

    @Test
    public void queryByCarBrand() throws Exception {

    }

    @Test
    public void addNewAmbulance() throws Exception {
        AmbulanceModel ambulanceModel = new AmbulanceModel();
        ambulanceModel.setAdminCode(320101);
        ambulanceModel.setCarBrand("苏A-88888");
        ambulanceModel.setCarId("008");
        ambulanceModel.setCarPhone("13312345678");
        ambulanceModel.setCreateTime(new Timestamp(System.currentTimeMillis()));
        ambulanceModel.setFlag(true);
        ambulanceModel.setLastModifiedTime(ambulanceModel.getCreateTime());
        ambulanceModel.setOrgId("4101000002");
        ambulanceModel.setName("01-04");
//        System.out.println(ambulanceModel);

        ambulanceDao.addNewAmbulance(ambulanceModel);
    }

    @Test
    public void setAmbulanceDisabled() throws Exception {

    }

    @Test
    public void queryByAdminCode() throws Exception {

    }

    @Test
    public void queryAll() throws Exception {
        List<AmbulanceModel> ambulanceModelList = ambulanceDao.queryAll();
        for (AmbulanceModel ambulanceModel : ambulanceModelList) {
            System.out.println(ambulanceModel);
        }
    }

    @Test
    public void setCreateTime() throws Exception {

    }

    @Test
    public void updateLastModifiedTime() throws Exception {

    }

    @Test
    public void updateOrdId() throws Exception {

    }

}