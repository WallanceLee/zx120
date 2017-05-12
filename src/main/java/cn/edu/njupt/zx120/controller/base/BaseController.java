package cn.edu.njupt.zx120.controller.base;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wallance on 1/14/17.
 */
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(BaseController.class);

    public Map<String, Object> resultMap(Boolean success, String message, Object entity) {
        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("success", success);
        resultMap.put("message", message);
        resultMap.put("entity", entity);

        return resultMap;
    }

    public Map<String, Object> resultMap(Boolean success, Object entity) {
        return this.resultMap(success, null, entity);
    }

    public Map<String, Object> resultMap(Boolean success, List entity, boolean emptyNotify) {
        String message = null;
        if ((entity == null || entity.size() == 0) && emptyNotify) {
            message = "未查询到相关数据";
        }
        return this.resultMap(success, message, entity);
    }

    // entity为空时提醒
    public Map<String, Object> resultMap(Boolean success, List entity) {
        return resultMap(success, entity, true);
    }

    public Map<String, Object> resultMap(Boolean success, String message) {
        return resultMap(success, message, null);
    }

    @ExceptionHandler
    public void exp(HttpServletResponse response, Exception e) {
        logger.debug("====================异常信息====================");
        if (logger.isErrorEnabled()) {
            logger.error("Controller异常", e);
        }
        logger.debug("====================异常信息====================");
        if (e instanceof UnauthenticatedException || e instanceof UnauthorizedException) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            response.setStatus(9999);
        }
    }
}
