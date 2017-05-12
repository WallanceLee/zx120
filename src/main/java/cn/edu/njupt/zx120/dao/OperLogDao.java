package cn.edu.njupt.zx120.dao;

import cn.edu.njupt.zx120.model.OperLogModel;

import java.util.List;

/**
 * Created by wallance on 4/25/17.
 */
public interface OperLogDao {

    void createOperLog(OperLogModel operLogModel);

    List<OperLogModel> queryAll();

}
