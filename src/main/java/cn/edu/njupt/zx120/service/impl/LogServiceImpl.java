package cn.edu.njupt.zx120.service.impl;

import cn.edu.njupt.zx120.dao.ErrLogDao;
import cn.edu.njupt.zx120.dao.LoginLogDao;
import cn.edu.njupt.zx120.dao.OperLogDao;
import cn.edu.njupt.zx120.model.ErrLogModel;
import cn.edu.njupt.zx120.model.LoginLogModel;
import cn.edu.njupt.zx120.model.OperLogModel;
import cn.edu.njupt.zx120.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wallance on 4/25/17.
 */
@Service("logService")
public class LogServiceImpl implements LogService {

    @Autowired
    private OperLogDao operLogDao;

    @Autowired
    private ErrLogDao errLogDao;

    @Autowired
    private LoginLogDao loginLogDao;

    @Override
    public void createOperLog(OperLogModel operLogModel) {
        operLogDao.createOperLog(operLogModel);
    }

    @Override
    public void createLoginLog(LoginLogModel loginLogModel) {
        loginLogDao.createLoginLog(loginLogModel);
    }

    @Override
    public void createErrLog(ErrLogModel errLogModel) {
        errLogDao.createErrLog(errLogModel);
    }

    @Override
    public List<OperLogModel> queryAllOperLog() {
        return operLogDao.queryAll();
    }

    @Override
    public List<LoginLogModel> queryAllLoginLog() {
        return loginLogDao.queryAll();
    }

    @Override
    public List<ErrLogModel> queryAllErrLog() {
        return errLogDao.queryAll();
    }

}
