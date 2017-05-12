package cn.edu.njupt.zx120.aspect;

import cn.edu.njupt.zx120.dao.UserDao;
import cn.edu.njupt.zx120.model.ErrLogModel;
import cn.edu.njupt.zx120.model.LoginLogModel;
import cn.edu.njupt.zx120.model.OperLogModel;
import cn.edu.njupt.zx120.service.LogService;
import cn.edu.njupt.zx120.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.sql.Timestamp;
import java.util.HashMap;

/**
 * Created by wallance on 4/25/17.
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private LogService logService;

    @Autowired
    private UserDao userDao;

    @Pointcut("execution(* cn.edu.njupt.zx120.controller.admin.AmbulanceController.*(..)) " +
            "|| execution(* cn.edu.njupt.zx120.controller.admin.ContainerController.*(..)) " +
            "|| execution(* cn.edu.njupt.zx120.controller.admin.MemberController.*(..)) " +
            "|| execution(* cn.edu.njupt.zx120.controller.admin.OrganizationController.*(..)) " +
            "|| execution(* cn.edu.njupt.zx120.controller.admin.ReaderController.*(..)) " +
            "|| execution(* cn.edu.njupt.zx120.controller.admin.ResourceBindingController.*(..)) " +
            "|| execution(* cn.edu.njupt.zx120.controller.admin.ResourceInfoController.*(..)) " +
            "|| execution(* cn.edu.njupt.zx120.controller.realtime.RealTimeController.*(..)) " +
            "|| execution(* cn.edu.njupt.zx120.controller.serviceManage.AmbulanceController.*(..)) " +
            "|| execution(* cn.edu.njupt.zx120.controller.serviceManage.MemberController.*(..)) " +
            "|| execution(* cn.edu.njupt.zx120.controller.userAuthority.AuthorityController.*(..)) " +
            "|| execution(* cn.edu.njupt.zx120.controller.userAuthority.UserController.getList(..)) " +
            "|| execution(* cn.edu.njupt.zx120.controller.userAuthority.UserController.addNewMember(..)) " +
            "|| execution(* cn.edu.njupt.zx120.controller.userAuthority.UserController.assignUserRole(..)) " +
            "|| execution(* cn.edu.njupt.zx120.controller.userAuthority.UserController.deleteMember(..)) " +
            "|| execution(* cn.edu.njupt.zx120.controller.userAuthority.UserController.updatePassword(..))")
    public void businessPointcut() {

    }

    @Pointcut("execution(* cn.edu.njupt.zx120.controller.userAuthority.UserController.login(..))")
    public void loginPointcut() {

    }

    @Around("businessPointcut()")
    public Object aroundBusiness(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        long start = System.currentTimeMillis();
        result = point.proceed();
        if (result instanceof HashMap) {
            HashMap resultMap = (HashMap) result;
            if (null != resultMap.get("success") && !(boolean) resultMap.get("success")) {
                afterThrowing(point, new Exception(null != resultMap.get("message") ? resultMap.get("message").toString() : ""));
                return result;
            }
        }
        OperLogModel operLogModel = new OperLogModel();
        acquireBaseLogInfo(operLogModel, start);
        operLogModel.setSpentTime((int) (System.currentTimeMillis() - start));
        operLogModel.setMethodName(point.getTarget().getClass().getName() + "." + point.getSignature().getName());
        logService.createOperLog(operLogModel);

        if (logger.isDebugEnabled()) {
            logger.debug("====================日志模型====================");
            logger.debug(operLogModel.toString());
            logger.debug("====================日志模型====================");
        }

        return result;
    }

    /**
     * 记录登陆日志
     */
    @Around("loginPointcut()")
    public Object aroundLogin(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        long start = System.currentTimeMillis();
        result = point.proceed();
        HashMap resultMap = (HashMap) result;
        LoginLogModel loginLogModel = new LoginLogModel();
        acquireBaseLogInfo(loginLogModel, start);
        if (null != resultMap.get("success") && !(boolean) resultMap.get("success")) {
            loginLogModel.setMessage(null != resultMap.get("message") ? resultMap.get("message").toString() : "登录失败");
        } else {
            loginLogModel.setMessage("登陆成功");
        }
        logService.createLoginLog(loginLogModel);

        if (logger.isDebugEnabled()) {
            logger.debug("====================日志模型====================");
            logger.debug(loginLogModel.toString());
            logger.debug("====================日志模型====================");
        }

        return result;
    }

    /**
     * 操作异常记录
     */
    @AfterThrowing(pointcut = "businessPointcut()", throwing = "e")
    public void afterThrowing(JoinPoint point, Throwable e) throws Exception {
        try {
            ErrLogModel errLogModel = new ErrLogModel();
            acquireBaseLogInfo(errLogModel);
            errLogModel.setException(e.toString());
            errLogModel.setMethodName(point.getTarget().getClass().getName() + "." + point.getSignature().getName());
            logService.createErrLog(errLogModel);

            if (logger.isDebugEnabled()) {
                logger.debug("====================日志模型====================");
                System.out.println(errLogModel);
                logger.debug("====================日志模型====================");
            }

            if (logger.isErrorEnabled()) {
                logger.debug("====================异常信息====================");
                logger.error("出现异常: ", e);
                logger.debug("====================异常信息====================");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void acquireBaseLogInfo(LoginLogModel logModel, long start) {
        logModel.setHost(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getRemoteHost());
        logModel.setAccount(null != SecurityUtils.getSubject().getPrincipal() ? SecurityUtils.getSubject().getPrincipal().toString() : "未登录");
        if (!logModel.getAccount().equals("未登录"))
            logModel.setUsername(userDao.queryByAccount(logModel.getAccount()).getUsername());
        logModel.setLoginTime(new Timestamp(start));
        if (StringUtil.isNullOrEmpty(logModel.getUsername())) {
            logModel.setUsername("无法获取用户信息");
        }
    }

    private void acquireBaseLogInfo(OperLogModel logModel, long start) {
        logModel.setHost(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getRemoteHost());
        logModel.setAccount(null != SecurityUtils.getSubject().getPrincipal() ? SecurityUtils.getSubject().getPrincipal().toString() : "未登录");
        if (!logModel.getAccount().equals("未登录"))
            logModel.setUsername(userDao.queryByAccount(logModel.getAccount()).getUsername());
        logModel.setOccurTime(new Timestamp(start));
        if (StringUtil.isNullOrEmpty(logModel.getAccount())) {
            logModel.setAccount("无法获取用户信息");
        }
    }

    private void acquireBaseLogInfo(ErrLogModel logModel, long start) {
        logModel.setHost(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getRemoteHost());
        logModel.setAccount(null != SecurityUtils.getSubject().getPrincipal() ? SecurityUtils.getSubject().getPrincipal().toString() : "未登录");
        if (!logModel.getAccount().equals("未登录"))
            logModel.setUsername(userDao.queryByAccount(logModel.getAccount()).getUsername());
        logModel.setOccurTime(new Timestamp(start));
        if (StringUtil.isNullOrEmpty(logModel.getAccount())) {
            logModel.setAccount("无法获取用户信息");
        }
    }

    private void acquireBaseLogInfo(ErrLogModel logModel) {
        acquireBaseLogInfo(logModel, System.currentTimeMillis());
    }

    private void acquireBaseLogInfo(OperLogModel logModel) {
        acquireBaseLogInfo(logModel, System.currentTimeMillis());
    }

    private void acquireBaseLogInfo(LoginLogModel logModel) {
        acquireBaseLogInfo(logModel, System.currentTimeMillis());
    }

}
