package cn.edu.njupt.zx120.service;

import cn.edu.njupt.zx120.model.ErrLogModel;
import cn.edu.njupt.zx120.model.LoginLogModel;
import cn.edu.njupt.zx120.model.OperLogModel;

import java.util.List;

/**
 * Created by wallance on 4/25/17.
 */
public interface LogService {

    void createOperLog(OperLogModel operLogModel);

    List<OperLogModel> queryAllOperLog();

    void createLoginLog(LoginLogModel loginLogModel);

    List<LoginLogModel> queryAllLoginLog();

    void createErrLog(ErrLogModel errLogModel);

    List<ErrLogModel> queryAllErrLog();

}
