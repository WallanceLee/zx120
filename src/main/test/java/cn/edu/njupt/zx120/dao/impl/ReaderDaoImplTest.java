package dao.impl;

import cn.edu.njupt.zx120.dao.ReaderDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by wallance on 3/16/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-dao.xml", "classpath:hibernate.cfg.xml"})
@Transactional
public class ReaderDaoImplTest {

    @Autowired
    private ReaderDao readerDao;

    @Test
    public void queryAll() throws Exception {

    }

    @Test
    public void queryById() throws Exception {

    }

    @Test
    public void updateIpAddress() throws Exception {

    }

    @Test
    public void updateContainerId() throws Exception {

    }

    @Test
    public void updateNotes() throws Exception {

    }

    @Test
    public void activeReader() throws Exception {

    }

    @Test
    public void setActiveTime() throws Exception {

    }

    @Test
    public void shutdownReader() throws Exception {

    }

    @Test
    public void setShutdownTime() throws Exception {

    }

}