package com.log4j.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LogTest2 {

	
	public static void main(String[] args) {
		
		Log logger = LogFactory.getLog(LogTest2.class);
		
		logger.debug("debugµ÷ÊÔ");
		logger.info("info");
		logger.warn("warn");
		logger.error("erroe");
		logger.fatal("fatal");
		
	}

}
