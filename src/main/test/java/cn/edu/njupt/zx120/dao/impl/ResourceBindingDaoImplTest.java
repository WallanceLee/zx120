package dao.impl;

import cn.edu.njupt.zx120.dao.ResourceBindingDao;
import cn.edu.njupt.zx120.model.ResourceBindingModel;
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
public class ResourceBindingDaoImplTest {

    @Autowired
    private ResourceBindingDao resourceBindingDao;

    @Test
    public void queryAllResourceBindings() throws Exception {
        List<ResourceBindingModel> resourceBindingModelList
                = resourceBindingDao.queryAllResourceBindings();
        for (ResourceBindingModel resourceBindingModel : resourceBindingModelList) {
            System.out.println(resourceBindingModel);
        }
    }

    @Test
    public void queryByResourceType() throws Exception {

    }

    @Test
    public void queryByResourceName() throws Exception {

    }

    @Test
    public void queryByResourceTable() throws Exception {

    }

    @Test
    public void queryByResourceId() throws Exception {
        ResourceBindingModel resourceBindingModel = resourceBindingDao.queryByResourceId("test", "1");
        System.out.println(resourceBindingModel);
    }


    @Test
    public void updateBindingTime() throws Exception {
        resourceBindingDao.updateBindingTime("test", "1");
        queryAllResourceBindings();
    }

    @Test
    public void rebindResource() throws Exception {
        resourceBindingDao.rebindResource("test", "1", "123456789090");
    }

    @Test
    public void addNewResourceBinding() throws Exception {
        ResourceBindingModel resourceBindingModel = new ResourceBindingModel();
        resourceBindingModel.setBindTime(new Date());
        resourceBindingModel.setNotes("This is a test");
        resourceBindingModel.setResourceId("1");
        resourceBindingModel.setResourceName("test");
        resourceBindingModel.setResourceTableName("test");
        resourceBindingModel.setResourceName("test");
        resourceBindingModel.setTag("1234");
        resourceBindingDao.addNewResourceBinding(resourceBindingModel);
    }

    @Test
    public void deleteResourceBinding() throws Exception {
        resourceBindingDao.deleteResourceBinding(resourceBindingDao.queryByResourceId("test", "1"));
        resourceBindingDao.queryAllResourceBindings();
    }

}