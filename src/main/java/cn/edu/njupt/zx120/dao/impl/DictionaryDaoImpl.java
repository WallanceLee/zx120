package cn.edu.njupt.zx120.dao.impl;

import cn.edu.njupt.zx120.dao.AbstractDao;
import cn.edu.njupt.zx120.dao.DictionaryDao;
import cn.edu.njupt.zx120.model.DictionaryModel;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wallance on 4/23/17.
 */
@Repository("dictionaryDao")
public class DictionaryDaoImpl extends AbstractDao<Integer, DictionaryModel> implements DictionaryDao {

    @Override
    public List<DictionaryModel> queryByParam(String param) {
        Session session = getSession();
        session.beginTransaction();
        List<DictionaryModel> dictionaryModels = session.createQuery("from DictionaryModel dic where dic.paramEng=:param", DictionaryModel.class)
                .setParameter("param", param)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return dictionaryModels;
    }

}
