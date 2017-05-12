package cn.edu.njupt.zx120.dao.impl;

import cn.edu.njupt.zx120.dao.AbstractDao;
import cn.edu.njupt.zx120.dao.StockDao;
import cn.edu.njupt.zx120.model.StockModel;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wallance on 3/3/17.
 */
@Repository("stockDao")
public class StockDaoImpl extends AbstractDao<Integer, StockModel> implements StockDao {

    @Override
    public List<StockModel> queryAllStock() {
        Session session = getSession();
        session.beginTransaction();
        List<StockModel> stockModels = session
                .createQuery("from StockModel stock", StockModel.class)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return stockModels;
    }

    @Override
    public StockModel queryByRfidTag(String rfidTag) {
        Session session = getSession();
        session.beginTransaction();
        StockModel stockModel = session
                .createQuery("from StockModel stock where stock.rfidTag=:rfidTag", StockModel.class)
                .setParameter("rfidTag", rfidTag)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return stockModel;
    }

    @Override
    public void addItem(StockModel stockModel) {
        super.save(stockModel);
    }

    @Override
    public void deleteItem(StockModel stockModel) {
        super.delete(stockModel);
    }

    @Override
    public void deleteItemByRfidTag(String rfidTag) {
        Session session = getSession();
        session.beginTransaction();
        session
                .createQuery("delete from StockModel stock where stock.rfidTag=:rfidTag")
                .setParameter("rfidTag", rfidTag).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
