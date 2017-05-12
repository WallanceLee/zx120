package dao.impl;

import cn.edu.njupt.zx120.dao.OrganizationDao;
import cn.edu.njupt.zx120.model.OrgTypeEnum;
import cn.edu.njupt.zx120.model.OrganizationModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by wallance on 3/15/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-dao.xml", "classpath:hibernate.cfg.xml"})
@Transactional
public class OrganizationDaoImplTest {

    @Autowired
    private OrganizationDao organizationDao;

    @Test
    public void queryAll() throws Exception {
        List<OrganizationModel> organizationModels = organizationDao.queryAll();
        System.out.println(organizationModels);
    }

    @Test
    public void queryById() throws Exception {
        OrganizationModel organizationModel = organizationDao.queryById("4101002148");
        System.out.println(organizationModel);
    }

    @Test
    public void queryByType() throws Exception {
        List<OrganizationModel> organizationModels = organizationDao.queryByType(OrgTypeEnum.FIRST_AID.getTypeId());
        System.out.println(organizationModels.size());
        List<OrganizationModel> organizationModelList = organizationDao.queryByType(OrgTypeEnum.HOSPITAL.getTypeId());
        System.out.println(organizationModelList.size());
    }

    @Test
    public void saveOrganization() throws Exception {
        OrganizationModel organizationModel = new OrganizationModel();
        organizationModel.setOrgId("2");
        organizationModel.setName("1231423412");
        organizationModel.setType(OrgTypeEnum.FIRST_AID.getTypeId());
        organizationModel.setOrgCode("3421321");
        organizationModel.setAdminCode(321);
        organizationModel.setScheduleFlag((byte)0);
        organizationModel.setOrgLevel((short)1);
        organizationModel.setAddress("rewrwerw");
        organizationModel.setPhoneNumber("123422");
        organizationModel.setOnlineFlag((byte)1);
        organizationModel.setCreateTime(new Timestamp((new Date()).getTime()));
        organizationModel.setLastModifyTime(new Timestamp((new Date()).getTime()));
        organizationDao.saveOrganization(organizationModel);
    }

}