package dao.impl;

import cn.edu.njupt.zx120.dao.MemberDao;
import cn.edu.njupt.zx120.model.MemberModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by wallance on 3/16/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-dao.xml", "classpath:hibernate.cfg.xml"})
@Transactional
public class MemberDaoImplTest {

    @Autowired
    private MemberDao memberDao;

    @Test
    public void queryAll() throws Exception {
        List<MemberModel> memberModels = memberDao.queryAll();
        for (MemberModel memberModel : memberModels) {
            System.out.println(memberModel);
        }
    }

    @Test
    public void queryById() throws Exception {
        MemberModel memberModel = memberDao.queryById("41020050");
        System.out.println(memberModel);
    }

    @Test
    public void queryByName() throws Exception {
        MemberModel memberModel = memberDao.queryByName("测试人员0050");
        System.out.println(memberModel);
    }

    @Test
    public void queryByOrgId() throws Exception {
        List<MemberModel> memberModels = memberDao.queryByOrgId("4101002092");
        for (MemberModel memberModel : memberModels) {
            System.out.println(memberModel);
        }
    }

    @Test
    public void queryByDepartment() throws Exception {
        List<MemberModel> memberModels = memberDao.queryByDepartment("测试急救科室8");
        for (MemberModel memberModel : memberModels) {
            System.out.println(memberModel);
        }
    }

    @Test
    public void updateOrgId() throws Exception {

    }

    @Test
    public void updateDepartment() throws Exception {

    }

    @Test
    public void updateLastModifiedTime() throws Exception {

    }

    @Test
    public void deleteMember() throws Exception {

    }

    @Test
    public void queryByType() throws Exception {

    }

    @Test
    public void cancelMember() throws Exception {

    }

    @Test
    public void createNewMember() throws Exception {

    }

}