package com.MutiModule.logback;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	
	Logger logger = LoggerFactory.getLogger(AppTest.class);   
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		
//		logger.debug("AppTest.testApp");
//		logger.info("AppTest.testApp");
//		logger.warn("AppTest.testApp");
//		logger.error("AppTest.testApp");
		
		assertTrue(true);
	}
}
