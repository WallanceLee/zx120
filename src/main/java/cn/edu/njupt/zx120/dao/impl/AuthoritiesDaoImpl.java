package cn.edu.njupt.zx120.dao.impl;

import cn.edu.njupt.zx120.dao.AbstractDao;
import cn.edu.njupt.zx120.dao.AuthoritiesDao;
import cn.edu.njupt.zx120.model.AuthorityModel;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wallance on 1/11/17.
 */
@Repository("authorityDao")
public class AuthoritiesDaoImpl extends AbstractDao<Integer, AuthorityModel> implements AuthoritiesDao {

    @Override
    public List<AuthorityModel> queryAll() {
        Session session = getSession();
        session.beginTransaction();
        List<AuthorityModel> authorityModels = session
                .createQuery("from AuthorityModel authority", AuthorityModel.class)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return authorityModels;
    }

    @Override
    public AuthorityModel queryById(int id) {
        Session session = getSession();
        session.beginTransaction();
        AuthorityModel authorityModel = session
                .createQuery("from AuthorityModel authority where authority.id=:id", AuthorityModel.class)
                .setParameter("id", id)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return authorityModel;
    }

    @Override
    public AuthorityModel queryByType(String type) {
        Session session = getSession();
        session.beginTransaction();
        AuthorityModel authorityModel = session
                .createQuery("from AuthorityModel authority where authority.type=:type", AuthorityModel.class)
                .setParameter("type", type)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return authorityModel;
    }

    @Override
    public void addNewAuthority(AuthorityModel authorityModel) {
        super.save(authorityModel);
    }

    @Override
    public void deleteAuthorityByType(String type) {
        AuthorityModel authorityModel = queryByType(type);
        super.delete(authorityModel);
    }
}
