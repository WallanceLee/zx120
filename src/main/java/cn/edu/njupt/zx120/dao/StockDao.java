package cn.edu.njupt.zx120.dao;

import cn.edu.njupt.zx120.model.StockModel;

import java.util.List;

/**
 * Created by wallance on 3/3/17.
 */
public interface StockDao {

    List<StockModel> queryAllStock();

    StockModel queryByRfidTag(String rfidTag);

    void addItem(StockModel stockModel);

    void deleteItemByRfidTag(String rfidTag);

    void deleteItem(StockModel stockModel);

}
