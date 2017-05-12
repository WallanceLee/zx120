package cn.edu.njupt.zx120.dao;

import cn.edu.njupt.zx120.model.LoginLogModel;

import java.util.List;

/**
 * Created by wallance on 4/25/17.
 */
public interface LoginLogDao {

    void createLoginLog(LoginLogModel loginLogModel);

    List<LoginLogModel> queryAll();

}
