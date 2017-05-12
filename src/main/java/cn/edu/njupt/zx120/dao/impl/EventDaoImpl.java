package cn.edu.njupt.zx120.dao.impl;

import cn.edu.njupt.zx120.dao.AbstractDao;
import cn.edu.njupt.zx120.dao.EventDao;
import cn.edu.njupt.zx120.model.EventModel;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wallance on 2/24/17.
 */
@Repository("eventDao")
public class EventDaoImpl extends AbstractDao<Integer, EventModel> implements EventDao {

    @Override
    public void createNewEvent(EventModel eventModel) {
        super.save(eventModel);
    }

    @Override
    public List<EventModel> queryAll() {
        Session session = getSession();
        session.beginTransaction();
        List<EventModel> eventModels = session
                .createQuery("from EventModel event order by event.eventTime desc ", EventModel.class)
                .setMaxResults(10)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return eventModels;
    }

    @Override
    public List<EventModel> queryByEventType(String type) {
        Session session = getSession();
        session.beginTransaction();
        List<EventModel> eventModels = session
                .createQuery("from EventModel event where event.eventType=:type", EventModel.class)
                .setParameter("type", type)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return eventModels;
    }

    @Override
    public List<EventModel> queryByResourceId(String resourceId, String resourceTable) {
        Session session = getSession();
        session.beginTransaction();
        List<EventModel> eventModels = session
                .createQuery("from EventModel event where event.eventResourceId=:resourceId and event.eventResourceTable=:resourceTable", EventModel.class)
                .setParameter("resourceId", resourceId)
                .setParameter("resourceTable", resourceTable)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return eventModels;
    }

    @Override
    public List<EventModel> queryByResourceTable(String resourceTable) {
        Session session = getSession();
        session.beginTransaction();
        List<EventModel> eventModels = session
                .createQuery("from EventModel event where event.eventResourceTable=:resourceTable", EventModel.class)
                .setParameter("resourceTable", resourceTable)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return eventModels;
    }

}
