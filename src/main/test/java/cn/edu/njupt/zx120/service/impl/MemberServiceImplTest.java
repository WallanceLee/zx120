package service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
public class MemberServiceImplTest {
    @Test
    public void queryAll() throws Exception {

    }

    @Test
    public void queryById() throws Exception {

    }

    @Test
    public void queryByName() throws Exception {

    }

    @Test
    public void queryByType() throws Exception {

    }

    @Test
    public void queryByOrdId() throws Exception {

    }

    @Test
    public void queryByDepartment() throws Exception {

    }

    @Test
    public void queryPhoneNumberByName() throws Exception {

    }

    @Test
    public void queryPhoneNumberById() throws Exception {

    }

    @Test
    public void cancelMember() throws Exception {

    }

    @Test
    public void createAccountBasedOnMemberModel() throws Exception {

    }

    @Test
    public void createNewMember() throws Exception {

    }

    @Test
    public void deleteMember() throws Exception {

    }

    @Test
    public void onRest() throws Exception {

    }

    @Test
    public void hasArrivedOffice() throws Exception {

    }

    @Test
    public void onCar() throws Exception {

    }

}