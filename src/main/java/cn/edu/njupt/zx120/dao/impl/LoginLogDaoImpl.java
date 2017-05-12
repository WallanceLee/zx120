package cn.edu.njupt.zx120.dao.impl;

import cn.edu.njupt.zx120.dao.AbstractDao;
import cn.edu.njupt.zx120.dao.LoginLogDao;
import cn.edu.njupt.zx120.model.LoginLogModel;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wallance on 4/25/17.
 */
@Repository("loginLogDao")
public class LoginLogDaoImpl extends AbstractDao<Integer, LoginLogModel> implements LoginLogDao {

    @Override
    public void createLoginLog(LoginLogModel loginLogModel) {
        super.save(loginLogModel);
    }

    @Override
    public List<LoginLogModel> queryAll() {
        Session session = getSession();
        session.beginTransaction();
        List<LoginLogModel> loginLogModels = session
                .createQuery("from LoginLogModel loginLog", LoginLogModel.class)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return loginLogModels;
    }

}
