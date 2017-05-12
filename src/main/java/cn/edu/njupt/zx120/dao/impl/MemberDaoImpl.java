package cn.edu.njupt.zx120.dao.impl;

import cn.edu.njupt.zx120.dao.AbstractDao;
import cn.edu.njupt.zx120.dao.MemberDao;
import cn.edu.njupt.zx120.model.MemberModel;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.util.Date;
import java.util.List;

/**
 * Created by wallance on 1/22/17.
 */
@Repository("memberDao")
public class MemberDaoImpl extends AbstractDao<Integer, MemberModel> implements MemberDao {

    @Override
    public List<MemberModel> queryAll() {
        Session session = getSession();
        session.beginTransaction();
        List<MemberModel> memberModels = session
                .createQuery("from MemberModel memberModel", MemberModel.class)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return memberModels;
    }

    // We use memberModel as variable name because member may be an keyword of hibernate
    @Override
    public MemberModel queryById(String id) {
        Session session = getSession();
        session.beginTransaction();
        MemberModel memberModel = session
                .createQuery("from MemberModel memberModel where memberModel.memberId=:memberId", MemberModel.class)
                .setParameter("memberId", id)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return memberModel;
    }

    @Override
    public MemberModel queryByName(String name) {
        Session session = getSession();
        session.beginTransaction();
        MemberModel memberModel = session
                .createQuery("from MemberModel memberModel where memberModel.name=:name", MemberModel.class)
                .setParameter("name", name)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return memberModel;
    }

    @Override
    public List<MemberModel> queryByOrgId(String orgId) {
        Session session = getSession();
        session.beginTransaction();
        List<MemberModel> memberModels = session
                .createQuery("from MemberModel memberModel where memberModel.orgId=:orgId", MemberModel.class)
                .setParameter("orgId", orgId)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return memberModels;
    }

    @Override
    public List<MemberModel> queryByDepartment(String department) {
        Session session = getSession();
        session.beginTransaction();
        List<MemberModel> memberModels = session
                .createQuery("from MemberModel memberModel where memberModel.department=:department", MemberModel.class)
                .setParameter("department", department)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return memberModels;
    }

    @Override
    public void updateOrgId(String id, String orgId) {
        Session session = getSession();
        session.beginTransaction();
        session
                .createQuery("update MemberModel memberModel set memberModel.orgId=:orgId where memberModel.memberId=:id", MemberModel.class)
                .setParameter("id", id)
                .setParameter("orgId", orgId).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateDepartment(String id, String department) {
        Session session = getSession();
        session.beginTransaction();
        session
                .createQuery("update MemberModel memberModel set memberModel.department=:department where memberModel.memberId=:id", MemberModel.class)
                .setParameter("id", id)
                .setParameter("department", department).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateLastModifiedTime(String id, Date lastModifiedTime) {
        Session session = getSession();
        session.beginTransaction();
        session
                .createQuery("update MemberModel memberModel set memberModel.lastModifiedTime=:lastModifiedTime where memberModel.memberId=:id", MemberModel.class)
                .setParameter("id", id)
                .setParameter("lastModifiedTime", lastModifiedTime).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteMember(MemberModel memberModel) {
        delete(memberModel);
    }

    @Override
    public List<MemberModel> queryByType(int typeId) {
        Session session = getSession();
        session.beginTransaction();
        List<MemberModel> memberModels = session
                .createQuery("from MemberModel memberModel where memberModel.type=:typeId", MemberModel.class)
                .setParameter("typeId", typeId)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return memberModels;
    }

    @Override
    public void cancelMember(String id) {
        MemberModel member = queryById(id);
        member.setFlag(false);
        Session session = getSession();
        session.beginTransaction();
        session
                .createQuery("update MemberModel memberModel set memberModel.flag=0 where memberModel.memberId=:id", MemberModel.class)
                .setParameter("id", id);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void createNewMember(MemberModel newMember) {
        super.save(newMember);
    }
}
