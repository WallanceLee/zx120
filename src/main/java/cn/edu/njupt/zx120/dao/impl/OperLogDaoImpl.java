package cn.edu.njupt.zx120.dao.impl;

import cn.edu.njupt.zx120.dao.AbstractDao;
import cn.edu.njupt.zx120.dao.OperLogDao;
import cn.edu.njupt.zx120.model.OperLogModel;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wallance on 4/25/17.
 */
@Repository("operLogDao")
public class OperLogDaoImpl extends AbstractDao<Integer, OperLogModel> implements OperLogDao {

    @Override
    public void createOperLog(OperLogModel operLogModel) {
        super.save(operLogModel);
    }

    @Override
    public List<OperLogModel> queryAll() {
        Session session = getSession();
        session.beginTransaction();
        List<OperLogModel> operLogModels = session
                .createQuery("from OperLogModel operLog", OperLogModel.class)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return operLogModels;
    }

}
