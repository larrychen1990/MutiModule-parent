package com.MutiModule.common.utils;


public class ImageUtilssTest {

	//@Test
	public void addImgTextTest() {
		try {
			
			ImageUtilss.cutImage(100, "C:\\Users\\Public\\Pictures\\Sample Pictures\\test.jpg", "C:\\Users\\Public\\Pictures\\Sample Pictures\\test!w100.jpg");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
	}
}
