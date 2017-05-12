package cn.edu.njupt.zx120.dao;

import cn.edu.njupt.zx120.model.ErrLogModel;

import java.util.List;

/**
 * Created by wallance on 4/25/17.
 */
public interface ErrLogDao {

    void createErrLog(ErrLogModel errLogModel);

    List<ErrLogModel> queryAll();

}
