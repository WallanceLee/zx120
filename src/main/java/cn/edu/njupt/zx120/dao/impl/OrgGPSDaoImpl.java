package cn.edu.njupt.zx120.dao.impl;

import cn.edu.njupt.zx120.dao.AbstractDao;
import cn.edu.njupt.zx120.dao.OrgGPSDao;
import cn.edu.njupt.zx120.model.OrgGPSModel;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * Created by wallance on 4/14/17.
 */
@Repository("orgGPSDao")
public class OrgGPSDaoImpl extends AbstractDao<Integer, OrgGPSModel> implements OrgGPSDao {

    @Override
    public OrgGPSModel queryByOrgId(String orgId) {
        Session session = getSession();
        session.beginTransaction();
        OrgGPSModel orgGPSModel = session
                .createQuery("from OrgGPSModel orgGPModel where orgGPModel.orgId=:orgId", OrgGPSModel.class)
                .setParameter("orgId", orgId)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return orgGPSModel;
    }

    @Override
    public void saveNewOrgGPS(OrgGPSModel orgGPSModel) {
        super.save(orgGPSModel);
    }

    @Override
    public void updateOrgGPS(String orgId, String longitude, String latitude) {
        Session session = getSession();
        session.beginTransaction();
        session
                .createQuery("update OrgGPSModel orgGPSModel set orgGPSModel.longitude=:longitude, orgGPSModel.latitude=:latitude where orgGPSModel.orgId=:orgId")
                .setParameter("longitude", longitude)
                .setParameter("latitude", latitude)
                .setParameter("orgId", orgId)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

}
