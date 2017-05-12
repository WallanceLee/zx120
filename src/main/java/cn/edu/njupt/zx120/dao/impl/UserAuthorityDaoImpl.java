package cn.edu.njupt.zx120.dao.impl;

import cn.edu.njupt.zx120.dao.AbstractDao;
import cn.edu.njupt.zx120.dao.UserAuthorityDao;
import cn.edu.njupt.zx120.model.UserAuthorityModel;
import org.springframework.stereotype.Repository;

/**
 * Created by wallance on 3/18/17.
 */
@Repository("userAuthorityDao")
public class UserAuthorityDaoImpl extends AbstractDao<Integer, UserAuthorityModel> implements UserAuthorityDao {

    @Override
    public void addNewUserRoleAssignment(UserAuthorityModel userAuthorityModel) {
        super.save(userAuthorityModel);
    }

    @Override
    public void cancelUserRoleAssignment(UserAuthorityModel userAuthorityModel) {
        super.delete(userAuthorityModel);
    }
}
