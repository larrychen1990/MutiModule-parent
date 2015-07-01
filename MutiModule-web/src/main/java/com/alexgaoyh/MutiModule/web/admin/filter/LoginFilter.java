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

import com.MutiModule.common.utils.CookieUtilss;

public class LoginFilter implements Filter {

	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        // 获得在下面代码中要用的request,response对象
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        

        // 获得用户请求的URI
        String path = servletRequest.getRequestURI();
        
        // 登陆页面无需过滤 登陆方法无需过滤
        if(path.indexOf("admin/sysmanUser/login") > -1 || path.indexOf("admin/sysmanUser/doLogin") > -1) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        
        //從web.xml裡面，取出context-param鍵值對標註的某個值
        String adminLoginCookieName = request.getServletContext().getInitParameter("adminLoginCookieName");
        
        String loginCookie = CookieUtilss.get(servletRequest, adminLoginCookieName);
        
    	System.out.println("loginCookie = " + loginCookie);

        // 判断如果没有取到员工信息,就跳转到登陆页面
        if (loginCookie == null || "".equals(loginCookie)) {
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
