package com.MutiModule.common.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class PropertiesUtilssTest {

	@Test
	public void testPropertiesUtils() {
		assertEquals(PropertiesUtilss.getProperties("test.properties", "key1"), "value1");// value1
		assertNull(PropertiesUtilss.getProperties("test.properties", "key3"));// null
		assertNull(PropertiesUtilss.getProperties("test1.properties", "key1"));//null
	}
}
