package cn.edu.njupt.zx120.service;

import cn.edu.njupt.zx120.model.AuthorityModel;
import cn.edu.njupt.zx120.model.UserModel;

import java.util.List;
import java.util.Map;

/**
 * Created by wallance on 1/13/17.
 */
public interface UserService {

    List<UserModel> queryAll();

    UserModel queryByAccount(String account);

    List<AuthorityModel> queryAuthorities(String account);

    boolean isUserExisted(UserModel userModel);

    void saveUser(UserModel userModel);

    void deleteUser(UserModel userModel);

    void updateUserPassword(String account, Map<String, String> info);

    void assignUserRole(String account, String role);

}
