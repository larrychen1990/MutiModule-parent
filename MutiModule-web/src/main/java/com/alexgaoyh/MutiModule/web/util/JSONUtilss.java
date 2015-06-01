package com.alexgaoyh.MutiModule.web.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;

public class JSONUtilss {

	/**
	 * json 化
	 * @param data
	 * @param response
	 * @throws IOException
	 */
	public static void renderJson(Object data, HttpServletResponse response) throws  IOException {
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.write(object2String(data)) ;
		writer.flush();
	}
	
	/**
	 * json化
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static String object2String(Object data) throws IOException {
		ObjectMapper om = new ObjectMapper();
		SimpleFilterProvider filterProvider = new SimpleFilterProvider().setFailOnUnknownId(false);
		om.setFilters(filterProvider);
		return om.writeValueAsString(data);
	}
}
