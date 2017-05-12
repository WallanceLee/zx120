package cn.edu.njupt.zx120.dao;

import cn.edu.njupt.zx120.model.EventModel;

import java.util.List;

/**
 * Created by wallance on 2/24/17.
 */
public interface EventDao {

    List<EventModel> queryAll();

    void createNewEvent(EventModel eventModel);

    List<EventModel> queryByEventType(String type);

    List<EventModel> queryByResourceId(String resourceId, String resourceTable);

    List<EventModel> queryByResourceTable(String resourceTable);

}
