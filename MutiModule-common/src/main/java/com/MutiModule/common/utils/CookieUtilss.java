package com.MutiModule.common.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtilss {
	
	/**
     * 添加一个cookie
     * @param res	HttpServletResponse
     * @param name	cookie名稱
     * @param value	cookie值
     * @param maxAge	cookie有效期
     */
    public static void add(HttpServletResponse res,String name,String value,int maxAge) {
        Cookie cookie = new Cookie(name,value);
        if(maxAge > 0) {
            cookie.setMaxAge(maxAge);
        }
        cookie.setPath("/");
        res.addCookie(cookie);
    }

	/**
     * 获取一個cookie的值
     * @param req
     * @param name
     * @return
     */
    public static String get(HttpServletRequest req,String name) {
        Cookie cookie = getCookie(req,name);
        String cookieVal = (null == cookie) ? null : cookie.getValue();
        return cookieVal;
    }
    
    /**
     * 清除cookie
     * @param req	請求報文
     * @param res	相應報文
     * @param name	cookie名稱
     */
    public static void remove(HttpServletRequest req,HttpServletResponse res,String cookieName) {
        if(null != cookieName) {
            Cookie cookie = new Cookie(cookieName,null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            res.addCookie(cookie);
        }
    }

    /**
     * 根據HttpServletRequest 獲取到某個cookie的值
     * @param req 請求
     * @param name cookie名稱
     * @return
     */
    private static Cookie getCookie(HttpServletRequest req,String name) {
        Map<String,Cookie> cookieMap = _readCookieMap(req);
        if(cookieMap.containsKey(name)) {
            return (Cookie)cookieMap.get(name);
        } else {
            return null;
        }
    }
    
    /**
     * 獲取cookie值數據，放置Map集合中
     * @param req
     * @return
     */
    private static Map<String,Cookie> _readCookieMap(HttpServletRequest req) {
        Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
        Cookie[] cookies = req.getCookies();
        if(null != cookies) {
            for(Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(),cookie);
            }
        }
        return cookieMap;
    }
}
