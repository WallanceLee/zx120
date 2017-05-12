package dao.impl;

import cn.edu.njupt.zx120.dao.UserDao;
import cn.edu.njupt.zx120.model.AuthorityModel;
import cn.edu.njupt.zx120.model.UserModel;
import cn.edu.njupt.zx120.util.EncryptUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wallance on 3/14/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-dao.xml", "classpath:hibernate.cfg.xml"})
@Transactional
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void queryAll() throws Exception {
        List<UserModel> userModelList = userDao.queryAll();
        for (UserModel userModel : userModelList) {
            System.out.println(userModel);
            for (AuthorityModel authorityModel : userModel.getAuthorities()) {
                System.out.println(authorityModel.getType());
            }
        }
    }

    @Test
    public void queryById() throws Exception {

    }

    @Test
    public void queryByUsername() throws Exception {

    }

    @Test
    public void saveUser() throws Exception {
        UserModel userModel = new UserModel();
        userModel.setUsername("admin");
        userModel.setAccount("liyanxu111");
        userModel.setPassword("123456");
        EncryptUtil encryptUtil = new EncryptUtil();
        encryptUtil.encryptPassword(userModel);
        userDao.saveUser(userModel);
        System.out.println(userDao.queryAll());
    }

    @Test
    public void updatePassword() throws Exception {
    }

}