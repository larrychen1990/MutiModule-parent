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

import com.alexgaoyh.MutiModule.util.redis.RedisClient;
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
        //System.out.println(path);
        
        // 从session里取员工工号信息
        String suJson = RedisClient.get(ConstantsUtil.ADMIN_LOGIN_CONSTANTS);

        /*创建类Constants.java，里面写的是无需过滤的页面
        for (int i = 0; i < Constants.NoFilter_Pages.length; i++) {

            if (path.indexOf(Constants.NoFilter_Pages[i]) > -1) {
                chain.doFilter(servletRequest, servletResponse);
                return;
            }
        }*/
        
        // 登陆页面无需过滤 登陆方法无需过滤
        if(path.indexOf("admin/sysmanUser/login") > -1 || path.indexOf("admin/sysmanUser/doLogin") > -1) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 判断如果没有取到员工信息,就跳转到登陆页面
        if (suJson == null || "".equals(suJson)) {
            // 跳转到登陆页面
            servletResponse.sendRedirect(servletRequest.getContextPath() + "/admin/sysmanUser/login");
        } else {
            // 已经登陆,继续此次请求
        	//每次在对admin下的路径进行请求，如果当前状态时已经登陆状态的话，这里这个用户又重新进行访问，可以对过期时间进行重新设定
        	// redis-cli 下执行  ttl 命令，可以查看当前这个键的过期时间 
        	RedisClient.expire(ConstantsUtil.ADMIN_LOGIN_CONSTANTS, ConstantsUtil.EXPIRE_TIME_30_MINUTE);
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }
}
