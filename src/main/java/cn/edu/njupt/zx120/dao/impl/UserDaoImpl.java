package cn.edu.njupt.zx120.dao.impl;

import cn.edu.njupt.zx120.dao.AbstractDao;
import cn.edu.njupt.zx120.dao.UserDao;
import cn.edu.njupt.zx120.model.UserModel;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wallance on 1/11/17.
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, UserModel> implements UserDao {

    @Override
    public List<UserModel> queryAll() {
        Session session = getSession();
        session.beginTransaction();
        List<UserModel> userModels = session
                .createQuery("from UserModel user", UserModel.class)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return userModels;

    }

    @Override
    public UserModel queryByAccount(String account) {
        Session session = getSession();
        session.beginTransaction();
        UserModel userModel = session
                .createQuery("from UserModel user where user.account=:account", UserModel.class)
                .setParameter("account", account)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return userModel;
    }

    @Override
    public void saveUser(UserModel userModel) {
        super.save(userModel);
    }

    @Override
    public void updatePassword(String account, String password) {
        Session session = getSession();
        session.beginTransaction();
        session
                .createQuery("update UserModel user set user.password=:password where user.account=:account")
                .setParameter("account", account)
                .setParameter("password", password).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

}
