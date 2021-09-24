package com.github.gdiazs.sqlinjection.filters;

import com.github.gdiazs.sqlinjection.models.UserSessionModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var httpReq = (HttpServletRequest) servletRequest;
        var session = httpReq.getSession(true);
        var userName = (UserSessionModel) session.getAttribute("userSession");

        if (ObjectUtils.isEmpty(userName)) {
            var httpResp = (HttpServletResponse) servletResponse;
            var redirectPath = httpReq.getContextPath() + "/login";
            httpResp.setHeader("Location", httpResp.encodeRedirectURL(redirectPath));
            httpResp.setStatus(HttpStatus.TEMPORARY_REDIRECT.value());
        }else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
