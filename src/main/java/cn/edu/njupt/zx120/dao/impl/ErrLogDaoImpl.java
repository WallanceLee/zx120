package cn.edu.njupt.zx120.dao.impl;

import cn.edu.njupt.zx120.dao.AbstractDao;
import cn.edu.njupt.zx120.dao.ErrLogDao;
import cn.edu.njupt.zx120.model.ErrLogModel;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wallance on 4/25/17.
 */
@Repository("errLogDao")
public class ErrLogDaoImpl extends AbstractDao<Integer, ErrLogModel> implements ErrLogDao {

    @Override
    public void createErrLog(ErrLogModel errLogModel) {
        super.save(errLogModel);
    }

    @Override
    public List<ErrLogModel> queryAll() {
        Session session = getSession();
        session.beginTransaction();
        List<ErrLogModel> errLogModels = session
                .createQuery("from ErrLogModel errLog", ErrLogModel.class)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return errLogModels;
    }

}
