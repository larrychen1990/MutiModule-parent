package com.MutiModule.common.utils;

public class PaginationUtil {

	/**
	 * 使用 *Example.java 对应的Criteria 进行数据分页的时候，andIdBetween startValue
	 * @param pageNumber 页面
	 * @param pageSize 一页数据
	 * @return
	 */
	public static Integer startValue(int pageNumber, int pageSize) {
		return ((pageNumber-1)*pageSize);
	}
	
	/**
	 * 使用 *Example.java 对应的Criteria 进行数据分页的时候，andIdBetween endValue
	 * @param pageNumber 页面
	 * @param pageSize 一页数据
	 * @return
	 */
	public static Integer endValue(int pageNumber, int pageSize) {
		return pageNumber*pageSize;
	}
}
