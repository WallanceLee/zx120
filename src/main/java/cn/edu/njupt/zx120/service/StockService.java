package cn.edu.njupt.zx120.service;

import cn.edu.njupt.zx120.model.StockModel;

/**
 * Created by wallance on 3/14/17.
 */
public interface StockService {

    StockModel queryByRfidTag(String rfidTag);

}
