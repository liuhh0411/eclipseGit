package com.log4j.test;

import org.apache.log4j.Logger;

public class LogTest {

	
	public static void main(String[] args) {
		
		Logger logger = Logger.getLogger(LogTest.class);
		
		logger.debug("debugµ÷ÊÔ");
		logger.info("info");
		logger.warn("warn");
		logger.error("erroe");
		logger.fatal("fatal");
		
	}

}
