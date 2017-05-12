package cn.edu.njupt.zx120.dao.impl;

import cn.edu.njupt.zx120.dao.AbstractDao;
import cn.edu.njupt.zx120.dao.ContainerDao;
import cn.edu.njupt.zx120.model.ContainerModel;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by wallance on 2/24/17.
 */
@Repository("containerDao")
public class ContainerDaoImpl extends AbstractDao<Integer, ContainerModel> implements ContainerDao {

    @Override
    public List<ContainerModel> queryAll() {
        Session session = getSession();
        session.beginTransaction();
        List<ContainerModel> containerModels = session
                .createQuery("from ContainerModel container", ContainerModel.class)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return containerModels;
    }

    @Override
    public ContainerModel queryByContainerId(long containerId) {
        Session session = getSession();
        session.beginTransaction();
        ContainerModel containerModel = session
                .createQuery("from ContainerModel container where container.containerId=:containerId", ContainerModel.class)
                .setParameter("containerId", containerId)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return containerModel;
    }

    @Override
    public ContainerModel queryByResourceId(String resourceId, String resourceTable) {
        Session session = getSession();
        session.beginTransaction();
        ContainerModel containerModel = session
                .createQuery("from ContainerModel container where container.resourceId=:resourceId and container.resourceTable=:resourceTable", ContainerModel.class)
                .setParameter("resourceId", resourceId)
                .setParameter("resourceTable", resourceTable)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return containerModel;
    }

    @Override
    public void addNewItem(ContainerModel containerModel) {
        super.save(containerModel);
    }

    @Override
    public void deleteItem(ContainerModel containerModel) {
        super.delete(containerModel);
    }

    @Override
    public void updateResourceContainer(String containerId, String resourceTable, String resourceId) {
        Session session = getSession();
        session.beginTransaction();
        session
                .createQuery("update ContainerModel container set container.containerId=:containerId where container.resourceId=:resourceId and container.resourceTable=:resourceTable", ContainerModel.class)
                .setParameter("containerId", containerId)
                .setParameter("resourceTable", resourceTable)
                .setParameter("resourceId", resourceId).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateContainerLastScanTime(String containerId) {
        Session session = getSession();
        session.beginTransaction();
        session
                .createQuery("update ContainerModel container set container.lastScanTime=:lastScanTime where container.containerId=:containerId")
                .setParameter("containerId", containerId)
                .setParameter("lastScanTime", new Date())
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateResourceLastScanTime(String containerId, String resourceTable, String resourceId) {
        Session session = getSession();
        session.beginTransaction();
        session
                .createQuery("update ContainerModel container " +
                        "set container.lastScanTime=:lastScanTime " +
                        "where containerId=:containerId " +
                        "and container.resourceId=:resourceId and container.resourceTable=:resourceTable")
                .setParameter("containerId", containerId)
                .setParameter("resourceId", resourceId)
                .setParameter("resourceTable", resourceTable)
                .setParameter("lastScanTime", new Date())
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

}
