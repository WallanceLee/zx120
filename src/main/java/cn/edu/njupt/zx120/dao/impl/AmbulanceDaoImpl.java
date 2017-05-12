package cn.edu.njupt.zx120.dao.impl;

import cn.edu.njupt.zx120.dao.AbstractDao;
import cn.edu.njupt.zx120.dao.AmbulanceDao;
import cn.edu.njupt.zx120.model.AmbulanceModel;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by wallance on 2/20/17.
 */
@Repository("ambulanceDao")
public class AmbulanceDaoImpl extends AbstractDao<Integer, AmbulanceModel> implements AmbulanceDao {

    @Override
    public AmbulanceModel queryById(String carId) {
        Session session = getSession();
        session.beginTransaction();
        AmbulanceModel ambulanceModel = session
                .createQuery("from AmbulanceModel ambulance where ambulance.carId=:id", AmbulanceModel.class)
                .setParameter("id", carId)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return ambulanceModel;
    }

    @Override
    public List<AmbulanceModel> queryByOrgId(String orgId) {
        Session session = getSession();
        session.beginTransaction();
        List<AmbulanceModel> ambulanceModels = session
                .createQuery("from AmbulanceModel ambulance where ambulance.orgId=:orgId", AmbulanceModel.class)
                .setParameter("orgId", orgId)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return ambulanceModels;
    }

    @Override
    public AmbulanceModel queryByCarBrand(String carBrand) {
        Session session = getSession();
        session.beginTransaction();
        AmbulanceModel ambulanceModel = session
                .createQuery("from AmbulanceModel ambulance where ambulance.carBrand=:carBrand", AmbulanceModel.class)
                .setParameter("carBrand", carBrand)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return ambulanceModel;
    }

    @Override
    public void addNewAmbulance(AmbulanceModel ambulanceModel) {
        super.save(ambulanceModel);
    }

    @Override
    public void setAmbulanceDisabled(String carId) {
        Session session = getSession();
        session.beginTransaction();
        session
                .createQuery("update AmbulanceModel ambulance set ambulance.flag=0 where ambulance.carId=:carId")
                .setParameter("carId", carId).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<AmbulanceModel> queryByAdminCode(int adminCode) {
        Session session = getSession();
        session.beginTransaction();
        List<AmbulanceModel> ambulanceModels = session
                .createQuery("from AmbulanceModel ambulance where ambulance.adminCode=:adminCode", AmbulanceModel.class)
                .setParameter("adminCode", adminCode)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return ambulanceModels;
    }

    @Override
    public List<AmbulanceModel> queryAll() {
        Session session = getSession();
        session.beginTransaction();
        List<AmbulanceModel> ambulanceModels = session
                .createQuery("from AmbulanceModel ambulance", AmbulanceModel.class)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return ambulanceModels;
    }

    @Override
    public void setCreateTime(String carId) {
        Session session = getSession();
        session.beginTransaction();
        session
                .createQuery("update AmbulanceModel ambulance set ambulance.createTime=:createTime where ambulance.carId=:carId")
                .setParameter("createTime", new Date())
                .setParameter("carId", carId).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateLastModifiedTime(String carId) {
        Session session = getSession();
        session.beginTransaction();
        session
                .createQuery("update AmbulanceModel ambulance set ambulance.lastModifiedTime=:lastModifiedTime where ambulance.carId=:carId")
                .setParameter("lastModifiedTime", new Date())
                .setParameter("carId", carId).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateOrdId(String carId, String orgId) {
        Session session = getSession();
        session.beginTransaction();
        session
                .createQuery("update AmbulanceModel ambulance set ambulance.orgId=:orgId where ambulance.carId=:carId")
                .setParameter("orgId", orgId)
                .setParameter("carId", carId).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

}
