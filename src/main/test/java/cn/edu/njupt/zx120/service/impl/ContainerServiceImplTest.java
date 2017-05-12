package service.impl;

import cn.edu.njupt.zx120.model.ResourceTableTypeEnum;
import cn.edu.njupt.zx120.service.ContainerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by wallance on 3/18/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
//        "classpath:ehcache.xml",
        "classpath:hibernate.cfg.xml",
        "classpath:spring-bean.xml",
        "classpath:spring-dao.xml",
        "classpath:spring-web.xml"
})
public class ContainerServiceImplTest {

    @Autowired
    private ContainerService containerService;

    @Test
    public void registerResourceToContainer() throws Exception {
//        containerService.registerResourceToContainer("41050002", "测试医院", "医院", "1200004", ResourceTableTypeEnum.AMBULANCE.getType());
    }

    @Test
    public void unregisterResourceFromContainer() throws Exception {
//        containerService.unregisterResourceFromContainer("1200004", ResourceTableTypeEnum.AMBULANCE.getType());
    }

}