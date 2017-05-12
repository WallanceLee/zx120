package cn.edu.njupt.zx120.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wallance on 2/22/17.
 */
public class CacheFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        final String requestUrl = request.getRequestURL().toString();
        if (requestUrl.endsWith(".html") || requestUrl.endsWith(".json")) {
            response.addHeader("Pragma", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            response.addHeader("Cache-Control", "pre-check=0, post-check=0");
            response.setDateHeader("Expires", 0);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
