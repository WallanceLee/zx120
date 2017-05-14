package cn.edu.njupt.zx120.shiro.filter;

import cn.edu.njupt.zx120.shiro.token.TokenUtil;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wallance on 2/22/17.
 */
public class RestAuthFilter extends AccessControlFilter {

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String token = request.getHeader("token");
        Subject subject = tokenUtil.getSubject(token);

        if (subject != null) {
            response.addHeader("token", tokenUtil.createAndSaveToken(subject));
            ThreadContext.unbindSubject();
            ThreadContext.bind(subject);
            return true;
        } else {
            token = request.getParameter("t");
            if (token != null && (subject = tokenUtil.getTempSubject(token)) != null) {
                ThreadContext.bind(subject);
                return true;
            }
        }

        onLoginFail(response);
        return false;
    }

    private void onLoginFail(ServletResponse servletResponse) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

}
