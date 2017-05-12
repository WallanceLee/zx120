package cn.edu.njupt.zx120.dao.impl;

import cn.edu.njupt.zx120.dao.AbstractDao;
import cn.edu.njupt.zx120.dao.ResourceBindingDao;
import cn.edu.njupt.zx120.model.ResourceBindingModel;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by wallance on 2/24/17.
 */
@Repository("resourceBindingDao")
public class ResourceBindingDaoImpl extends AbstractDao<Integer, ResourceBindingModel> implements ResourceBindingDao {

    @Override
    public List<ResourceBindingModel> queryAllResourceBindings() {
        Session session = getSession();
        session.beginTransaction();
        List<ResourceBindingModel> resourceBindingModels = session
                .createQuery("from ResourceBindingModel resourceBingding", ResourceBindingModel.class)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return resourceBindingModels;
    }

    @Override
    public List<ResourceBindingModel> queryByResourceType(String resourceType) {
        Session session = getSession();
        session.beginTransaction();
        List<ResourceBindingModel> resourceBindingModels = session
                .createQuery("from ResourceBindingModel resourceBinding where resourceBinding.resourceType=:resourceType", ResourceBindingModel.class)
                .setParameter("resourceType", resourceType)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return resourceBindingModels;
    }

    @Override
    public List<ResourceBindingModel> queryByResourceName(String resourceName) {
        Session session = getSession();
        session.beginTransaction();
        List<ResourceBindingModel> resourceBindingModels = session
                .createQuery("from ResourceBindingModel resourceBinding where resourceBinding.resourceName=:resourceName", ResourceBindingModel.class)
                .setParameter("resourceName", resourceName)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return resourceBindingModels;
    }

    @Override
    public List<ResourceBindingModel> queryByResourceTable(String resourceTableName) {
        Session session = getSession();
        session.beginTransaction();
        List<ResourceBindingModel> resourceBindingModels = session
                .createQuery("from ResourceBindingModel resourceBinding where resourceBinding.resourceTableName=:resourceTableName", ResourceBindingModel.class)
                .setParameter("resourceTableName", resourceTableName)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return resourceBindingModels;
    }

    @Override
    public ResourceBindingModel queryByResourceId(String resourceTable, String resourceIdInTable) {
        Session session = getSession();
        session.beginTransaction();
        ResourceBindingModel resourceBindingModel = session
                .createQuery("from ResourceBindingModel resourceBinding " +
                        "where resourceBinding.resourceId=:resourceIdInTable and resourceBinding.resourceTableName=:resourceTable",
                        ResourceBindingModel.class)
                .setParameter("resourceIdInTable", resourceIdInTable)
                .setParameter("resourceTable", resourceTable)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return resourceBindingModel;
    }

    @Override
    public void updateBindingTime(String resourceTable, String resourceIdInTable) {
        Session session = getSession();
        session.beginTransaction();
        session.createQuery("update ResourceBindingModel resourceBindingModel " +
                "set resourceBindingModel.bindTime=:bindTime " +
                "where resourceBindingModel.resourceTableName=:resourceTableName and resourceBindingModel.resourceId=:resourceId")
                .setParameter("bindTime", new Date())
                .setParameter("resourceTableName", resourceTable)
                .setParameter("resourceId", resourceIdInTable)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void rebindResource(String resourceTable, String resourceIdInTable, String resourceTag) {
        Session session = getSession();
        session.beginTransaction();
        session
                .createQuery("update ResourceBindingModel resourceBinding " +
                        "set resourceBinding.tag=:resourceTag " +
                        "where resourceBinding.resourceTableName=:resourceTable and resourceBinding.resourceId=:resourceIdInTable")
                .setParameter("resourceTag", resourceTag)
                .setParameter("resourceTable", resourceTable)
                .setParameter("resourceIdInTable", resourceIdInTable).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void addNewResourceBinding(ResourceBindingModel resourceBinding) {
        super.save(resourceBinding);
    }

    @Override
    public void deleteResourceBinding(ResourceBindingModel resourceBinding) {
        super.delete(resourceBinding);
    }

}
