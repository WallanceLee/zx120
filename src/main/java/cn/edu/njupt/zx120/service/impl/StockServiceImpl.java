package cn.edu.njupt.zx120.service.impl;

import cn.edu.njupt.zx120.dao.StockDao;
import cn.edu.njupt.zx120.model.StockModel;
import cn.edu.njupt.zx120.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wallance on 3/14/17.
 */
@Service("stockService")
public class StockServiceImpl implements StockService {

    @Autowired
    private StockDao stockDao;

    @Override
    public StockModel queryByRfidTag(String rfidTag) {
        return stockDao.queryByRfidTag(rfidTag);
    }

}
