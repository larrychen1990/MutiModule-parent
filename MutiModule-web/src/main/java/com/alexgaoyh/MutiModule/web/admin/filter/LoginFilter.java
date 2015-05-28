package com.alexgaoyh.MutiModule.web.admin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alexgaoyh.MutiModule.web.util.ConstantsUtil;

public class LoginFilter implements Filter {

	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        // 获得在下面代码中要用的request,response,session对象
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        

        // 获得用户请求的URI
        String path = servletRequest.getRequestURI();
        
        // 登陆页面无需过滤 登陆方法无需过滤
        if(path.indexOf("admin/sysmanUser/login") > -1 || path.indexOf("admin/sysmanUser/doLogin") > -1) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }

        Object sessionObj = servletRequest.getSession().getAttribute(ConstantsUtil.ADMIN_LOGIN_CONSTANTS);
        
        // 判断如果没有取到员工信息,就跳转到登陆页面
        if (sessionObj == null || "".equals(sessionObj)) {
        	// 跳转到登陆页面
            servletResponse.sendRedirect(servletRequest.getContextPath() + "/admin/sysmanUser/login");
        } else {
            // 已经登陆,继续此次请求
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }
    
}
