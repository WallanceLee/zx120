package dao.impl;

import cn.edu.njupt.zx120.dao.ContainerDao;
import cn.edu.njupt.zx120.model.ContainerModel;
import cn.edu.njupt.zx120.model.ContainerTypeEnum;
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
public class ContainerDaoImplTest {

    @Autowired
    private ContainerDao containerDao;

    @Test
    public void queryByContainerId() throws Exception {
//        List<ContainerModel> containerModelList = containerDao.queryByContainerId("41050001");
//        System.out.println(containerModelList);
//        for (ContainerModel containerModel : containerModelList)
//            System.out.println(containerModel);
    }

    @Test
    public void queryByResourceId() throws Exception {
        ContainerModel containerModel = containerDao.queryByResourceId("1200000", ResourceTableTypeEnum.AMBULANCE.getType());
        System.out.println(containerModel);
    }

    @Test
    public void addNewItem() throws Exception {
        ContainerModel containerModel = new ContainerModel();
//        containerModel.setContainerId("41050000");
//        containerModel.setContainerType("医院");
        containerModel.setLastScanTime(new Date());
        containerModel.setResourceTable(ResourceTableTypeEnum.MEMBER.getType());
//        containerModel.setContainerName("测试医院");
//        containerModel.setResourceId("41020199");
        containerDao.addNewItem(containerModel);
    }

    @Test
    public void deleteItem() throws Exception {
        ContainerModel containerModel = new ContainerModel();
//        containerModel.setContainerId("41050000");
//        containerModel.setContainerType("医院");
        containerModel.setLastScanTime(new Date());
        containerModel.setResourceTable(ResourceTableTypeEnum.MEMBER.getType());
        containerModel.setContainerName("测试医院");
        containerModel.setResourceId("41020199");
        containerDao.deleteItem(containerModel);
    }

    @Test
    public void updateResourceContainer() throws Exception {

    }

    @Test
    public void updateResourceLastModifiedTime() throws Exception {
        containerDao.updateResourceLastScanTime("41050000", "sys_ambul_info", "1200000");
    }

    @Test
    public void updateContainerLstScanTime() throws Exception {
        containerDao.updateContainerLastScanTime("41050000");
    }

}