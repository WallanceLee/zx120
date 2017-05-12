package cn.edu.njupt.zx120.dao;

import cn.edu.njupt.zx120.model.UserAuthorityModel;

/**
 * Created by wallance on 3/18/17.
 */
public interface UserAuthorityDao {

    void addNewUserRoleAssignment(UserAuthorityModel userAuthorityModel);

    void cancelUserRoleAssignment(UserAuthorityModel userAuthorityModel);

}
