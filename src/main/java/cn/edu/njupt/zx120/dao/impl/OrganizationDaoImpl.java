package cn.edu.njupt.zx120.dao.impl;

import cn.edu.njupt.zx120.dao.AbstractDao;
import cn.edu.njupt.zx120.dao.OrganizationDao;
import cn.edu.njupt.zx120.model.OrganizationModel;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wallance on 1/22/17.
 */
@Repository("organization")
public class OrganizationDaoImpl extends AbstractDao<Integer, OrganizationModel> implements OrganizationDao {

    @Override
    public List<OrganizationModel> queryAll() {
        Session session = getSession();
        session.beginTransaction();
        List<OrganizationModel> organizationModels = session
                .createQuery("from OrganizationModel org", OrganizationModel.class)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return organizationModels;
    }

    @Override
    public OrganizationModel queryById(String id) {
        Session session = getSession();
        session.beginTransaction();
        OrganizationModel organizationModel = session
                .createQuery("from OrganizationModel org where org.orgId=:orgId", OrganizationModel.class)
                .setParameter("orgId", id)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return organizationModel;
    }

    @Override
    public OrganizationModel queryByName(String name) {
        Session session = getSession();
        session.beginTransaction();
        OrganizationModel organizationModel = session
                .createQuery("from OrganizationModel org where org.name=:name", OrganizationModel.class)
                .setParameter("name", name)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return organizationModel;
    }

    @Override
    public List<OrganizationModel> queryByType(int type) {
        Session session = getSession();
        session.beginTransaction();
        List<OrganizationModel> organizationModels = session
                .createQuery("from OrganizationModel org where org.type=:type", OrganizationModel.class)
                .setParameter("type", type)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return organizationModels;
    }

    @Override
    public void saveOrganization(OrganizationModel organizationModel) {
        super.save(organizationModel);
    }
}
