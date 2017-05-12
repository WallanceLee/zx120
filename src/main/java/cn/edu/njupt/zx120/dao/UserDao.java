package cn.edu.njupt.zx120.dao;

import cn.edu.njupt.zx120.model.UserModel;

import java.util.List;

/**
 * Created by wallance on 1/11/17.
 */
public interface UserDao {

    List<UserModel> queryAll();

    UserModel queryByAccount(String account);

    void saveUser(UserModel userModel);

    void updatePassword(String account, String password);

}
