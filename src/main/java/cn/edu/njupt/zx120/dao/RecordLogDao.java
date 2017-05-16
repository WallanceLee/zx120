package cn.edu.njupt.zx120.dao;

import cn.edu.njupt.zx120.model.RecordLogModel;

import java.util.List;

/**
 * Created by wallance on 4/14/17.
 */
public interface RecordLogDao {

    List<RecordLogModel> queryLatestTenRecords();

}
