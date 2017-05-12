package dao.impl;

import cn.edu.njupt.zx120.dao.EventDao;
import cn.edu.njupt.zx120.model.EventModel;
import cn.edu.njupt.zx120.model.EventTypeEnum;
import cn.edu.njupt.zx120.model.ResourceTableTypeEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by wallance on 3/16/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-dao.xml", "classpath:hibernate.cfg.xml"})
@Transactional
public class EventDaoImplTest {

    @Autowired
    private EventDao eventDao;

    @Test
    public void createNewEvent() throws Exception {
        EventModel eventModel = new EventModel();
        eventModel.setEventType(EventTypeEnum.MEMBER_REST.getType());
        eventModel.setEventResourceId("41020199");
        eventModel.setEventTime(new Date());
        eventModel.setEventResourceTable(ResourceTableTypeEnum.MEMBER.getType());
//        eventModel.setContainerId("");
        eventModel.setEventDescription("This is a test.");
        eventDao.createNewEvent(eventModel);
    }

    @Test
    public void queryByEventType() throws Exception {
        List<EventModel> eventModels = eventDao.queryByEventType(EventTypeEnum.MEMBER_IN_OFFICE.getType());
        for (EventModel eventModel : eventModels) {
            System.out.println(eventModel);
        }
    }

    @Test
    public void queryByResourceId() throws Exception {
        List<EventModel> eventModels = eventDao.queryByResourceId("41020199", "sys_member_info");
        for (EventModel eventModel : eventModels) {
            System.out.println(eventModel);
        }
    }

    @Test
    public void queryByResourceTable() throws Exception {
        List<EventModel> eventModels = eventDao.queryByResourceTable("sys_member_info");
        for (EventModel eventModel : eventModels) {
            System.out.println(eventModel);
        }
    }

}