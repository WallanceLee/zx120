package cn.edu.njupt.zx120.service.impl;

import cn.edu.njupt.zx120.dao.AmbulanceDao;
import cn.edu.njupt.zx120.dao.ContainerDao;
import cn.edu.njupt.zx120.dao.EventDao;
import cn.edu.njupt.zx120.dao.MemberDao;
import cn.edu.njupt.zx120.dto.realtime.EventDto;
import cn.edu.njupt.zx120.model.EventModel;
import cn.edu.njupt.zx120.model.ResourceTableTypeEnum;
import cn.edu.njupt.zx120.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wallance on 3/14/17.
 */
@Service("eventService")
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDao eventDao;

    @Autowired
    private ContainerDao containerDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private AmbulanceDao ambulanceDao;

    @Override
    public List<EventModel> queryByResourceTable(String resourceTable) {
        return eventDao.queryByResourceTable(resourceTable);
    }

    @Override
    public List<EventModel> queryByResourceId(String resourceId, String resourceTable) {
        return eventDao.queryByResourceId(resourceId, resourceTable);
    }

    @Override
    public List<EventDto> queryAll() {
        List<EventDto> eventDtos = new ArrayList<>();
        List<EventModel> eventModels = eventDao.queryAll();
        int index = 0;
        for (int i = 0; i < eventModels.size(); i++) {
//        for (int i = eventModels.size() - 1; i >= eventModels.size() - 11; i++) {
            EventModel currentEventModel = eventModels.get(i);
            // 在eventDtos里寻找是否之前记录过相同的事件类型、事件时间、容器名称
            // 如果三者都相同，说明属于同一事件
            boolean foundSameEvent = false;
            for (EventDto eventDto : eventDtos) {
                if (eventDto.getEventTime().equals(currentEventModel.getEventTime())
                        && eventDto.getContainerName().equals(containerDao.queryByContainerId(currentEventModel.getContainerId()))
                        && eventDto.getEventType().equals(currentEventModel.getEventType())) {
                    foundSameEvent = true;
                    if (currentEventModel.getEventResourceTable().equals(ResourceTableTypeEnum.MEMBER.getType())) {
                        eventDto.getMemberNames().add(memberDao.queryById(currentEventModel.getEventResourceId()).getName());
                    } else if (currentEventModel.getEventResourceTable().equals(ResourceTableTypeEnum.AMBULANCE.getType())) {
                        eventDto.getAmbulanceBrands().add(ambulanceDao.queryById(currentEventModel.getEventResourceId()).getCarBrand());
                    }
                }
            }

            if (!foundSameEvent) {
                EventDto eventDto = new EventDto();
                eventDto.setEventId(index++);
                if (currentEventModel.getEventResourceTable().equals(ResourceTableTypeEnum.MEMBER.getType())) {
                    eventDto.getMemberNames().add(memberDao.queryById(currentEventModel.getEventResourceId()).getName());
                } else if (currentEventModel.getEventResourceTable().equals(ResourceTableTypeEnum.AMBULANCE.getType())) {
                    eventDto.getAmbulanceBrands().add(ambulanceDao.queryById(currentEventModel.getEventResourceId()).getCarBrand());
                }
                eventDto.setEventType(currentEventModel.getEventType());
                eventDto.setEventTime(currentEventModel.getEventTime());
                eventDto.setContainerName(containerDao.queryByContainerId(currentEventModel.getContainerId()).getContainerName());
                eventDtos.add(eventDto);
            }
        }
        return eventDtos;
    }

}
