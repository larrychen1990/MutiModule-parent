package com.alexgaoyh.MutiModule.web.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {

	/**
     * 根据cookieName名称 获取到 cookie的值
     * @param request
     * @param cookieName
     * @return
     */
    public static Cookie getCookie(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if (null == cookies) {
			return null;
		}
		for (Cookie c : cookies) {
			if (c.getName().equals(cookieName)) {
				return c;
			}
		}
		return null;
	}
}
