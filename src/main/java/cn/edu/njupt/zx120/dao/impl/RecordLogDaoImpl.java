package cn.edu.njupt.zx120.dao.impl;

import cn.edu.njupt.zx120.dao.AbstractDao;
import cn.edu.njupt.zx120.dao.RecordLogDao;
import cn.edu.njupt.zx120.model.RecordLogModel;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wallance on 4/14/17.
 */
@Repository("recordLogDao")
public class RecordLogDaoImpl extends AbstractDao<Integer, RecordLogModel> implements RecordLogDao {

    @Override
    public List<RecordLogModel> queryAll() {
        Session session = getSession();
        session.beginTransaction();
        List<RecordLogModel> recordLogModels = session
                .createQuery("from RecordLogModel recordLogModel order by recordLogModel.storeTime desc", RecordLogModel.class)
                .setMaxResults(10)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return recordLogModels;
    }

}
