package cn.edu.njupt.zx120.service.impl;

import cn.edu.njupt.zx120.dao.impl.AuthoritiesDaoImpl;
import cn.edu.njupt.zx120.dao.impl.UserDaoImpl;
import cn.edu.njupt.zx120.model.AuthorityModel;
import cn.edu.njupt.zx120.model.UserModel;
import cn.edu.njupt.zx120.service.UserService;
import cn.edu.njupt.zx120.util.EncryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by wallance on 1/13/17.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDaoImpl userDao;


    @Autowired
    private AuthoritiesDaoImpl authoritiesDao;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public List<UserModel> queryAll() {
        logger.info("Query for all user...");
        return userDao.queryAll();
    }

    @Override
    public UserModel queryByAccount(String account) {
        logger.info("Query for user by username: " + account);
        return userDao.queryByAccount(account);
    }

    @Override
    public boolean isUserExisted(UserModel userModel) {
        if (userModel == null)
            return false;
        else {
            UserModel userModelFromDb = queryByAccount(userModel.getUsername());
            return (userModelFromDb != null);
        }
    }

    @Override
    @Transactional
    public void saveUser(UserModel userModel) {
        logger.info("Save UserModel to database...");
        userDao.save(userModel);
    }

    @Override
    @Transactional
    public void deleteUser(UserModel userModel) {
        logger.info("Delete UserModel " + userModel.getUsername() + " from database...");
        userDao.delete(userModel);
    }

    @Override
    public List<AuthorityModel> queryAuthorities(String username) {
        return queryByAccount(username).getAuthorities();
    }

    @Override
    public void updateUserPassword(String account, Map<String, String> info) {
        UserModel userModel = userDao.queryByAccount(account);

        // 用户不存在， 抛出异常
        if (userModel == null) {

        }

        // 旧密码没有输入，抛出未输入旧密码异常
        if (info.get("oldPassword") == null) {

        }

        // 新密码和新密码确认不一致时，抛出异常
        if (!info.get("newPassword").equals(info.get("newPasswordConfirm"))) {

        }

        if (!info.get("oldPassword").equals(info.get("newPassword"))) {

        }

        EncryptUtil encryptUtil = new EncryptUtil();
        userModel.setPassword(info.get("newPassword"));
        encryptUtil.encryptPassword(userModel);
        userDao.updatePassword(account, userModel.getPassword());

    }

    @Override
    public void assignUserRole(String account, String role) {

    }
}
