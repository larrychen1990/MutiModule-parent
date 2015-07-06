package com.MutiModule.common.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilssTest {

	@Test
	public void getFileExtTest() {
		assertEquals(StringUtilss.getFileExt("20150706115555_673!100_100.jpg"), "jpg");
		assertEquals(StringUtilss.getFileExt("C:\\Pictures\\20150706115555_673!100_100.jpg"), "jpg");
	}
	
	@Test
	public void getFileName() {
		assertEquals(StringUtilss.getFileName("20150706115555_673!100_100.jpg"), "20150706115555_673!100_100");
		assertEquals(StringUtilss.getFileName("C:\\Pictures\\20150706115555_673!100_100.jpg"), "20150706115555_673!100_100");
	}
}
