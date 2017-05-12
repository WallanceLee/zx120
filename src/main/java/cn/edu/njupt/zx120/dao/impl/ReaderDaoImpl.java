package cn.edu.njupt.zx120.dao.impl;

import cn.edu.njupt.zx120.dao.AbstractDao;
import cn.edu.njupt.zx120.dao.ReaderDao;
import cn.edu.njupt.zx120.model.ReaderModel;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by wallance on 1/17/17.
 */
@Repository("readerDao")
public class ReaderDaoImpl extends AbstractDao<Integer, ReaderModel> implements ReaderDao {

    @Override
    public List<ReaderModel> queryAll() {
        Session session = getSession();
        session.beginTransaction();
        List<ReaderModel> readerModels = session
                .createQuery("from ReaderModel reader", ReaderModel.class)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return readerModels;
    }

    @Override
    public ReaderModel queryById(int id) {
        Session session = getSession();
        session.beginTransaction();
        ReaderModel readerModel = session
                .createQuery("from ReaderModel reader where id=:id", ReaderModel.class)
                .setParameter("id", id)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return readerModel;
    }

    @Override
    public void updateIpAddress(int id, String ipAddress) {
        Session session = getSession();
        session.beginTransaction();
        session
                .createQuery("update ReaderModel reader set reader.ip=:ip where reader.id=:id")
                .setParameter("ip", ipAddress)
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateContainerId(int id, long containerId) {
        Session session = getSession();
        session.beginTransaction();
        session
                .createQuery("update ReaderModel reader set reader.bindContainerId=:containerId where reader.id=:id")
                .setParameter("containerId", containerId)
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateNotes(int id, String notes) {
        Session session = getSession();
        session.beginTransaction();
        session
                .createQuery("update ReaderModel reader set reader.note=:note where reader.id=:id")
                .setParameter("id", id)
                .setParameter("note", notes).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void activeReader(int id) {
        Session session = getSession();
        session.beginTransaction();
        session
                .createQuery("update ReaderModel reader set reader.activeFlag=1 where reader.id=:id")
                .setParameter("id", id).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void setActiveTime(int id, Date activeTime) {
        Session session = getSession();
        session.beginTransaction();
        session
                .createQuery("update ReaderModel reader set reader.activeTime=:activeTime where reader.id=:id")
                .setParameter("activeTime", activeTime)
                .setParameter("id", id).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void shutdownReader(int id) {
        Session session = getSession();
        session.beginTransaction();
        session
                .createQuery("update ReaderModel reader set reader.activeFlag=0 where reader.id=:id")
                .setParameter("id", id).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void setShutdownTime(int id, Date shutdownTime) {
        Session session = getSession();
        session.beginTransaction();
        session
                .createQuery("update ReaderModel reader set reader.shutdownTime=:shutdownTime where reader.id=:id")
                .setParameter("shutdownTime", shutdownTime)
                .setParameter("id", id).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
