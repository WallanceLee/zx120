package dao.impl;

import cn.edu.njupt.zx120.dao.StockDao;
import cn.edu.njupt.zx120.model.StockModel;
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
@ContextConfiguration(locations = {
        "classpath:spring-dao.xml", "classpath:hibernate.cfg.xml"
})
@Transactional
public class StockDaoImplTest {

    @Autowired
    private StockDao stockDao;

    @Test
    public void queryAllStock() throws Exception {
        List<StockModel> stockModels = stockDao.queryAllStock();
        for (StockModel stockModel : stockModels) {
            System.out.println(stockModel);
        }
    }

    @Test
    public void queryByRfidTag() throws Exception {
        StockModel stockModel = stockDao.queryByRfidTag("tag11200000");
        System.out.println(stockModel);
    }

    @Test
    public void addItem() throws Exception {
        StockModel stockModel = new StockModel();
//        stockModel.setContainerId("100000001");
        stockModel.setRfidTag("tag1000999");
        stockDao.addItem(stockModel);
    }

    @Test
    public void deleteItem() throws Exception {
        StockModel stockModel = new StockModel();
        stockModel.setId(7);
//        stockModel.setContainerId("100000001");
        stockModel.setRfidTag("tag1000999");
        stockDao.deleteItem(stockModel);
    }

    @Test
    public void deleteItemByRfid() throws Exception {
        stockDao.deleteItemByRfidTag("tag1000999");
    }

}