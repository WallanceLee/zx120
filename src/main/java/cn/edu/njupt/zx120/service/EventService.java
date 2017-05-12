package cn.edu.njupt.zx120.service;

import cn.edu.njupt.zx120.dto.realtime.EventDto;
import cn.edu.njupt.zx120.model.EventModel;

import java.util.List;

/**
 * Created by wallance on 3/14/17.
 */
public interface EventService {

    List<EventModel> queryByResourceTable(String resourceTable);

    List<EventModel> queryByResourceId(String resourceId, String resourceTable);

    List<EventDto> queryAll();

}
