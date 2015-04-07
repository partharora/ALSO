package com.friskwave.utils;

import org.apache.log4j.Logger;

/**
 * @author Parth Arora
 * @email arora.parth@gmail.com
 * @desc A generic implementation to get Logger object for any class.
 * This reduces the possibility of developer error of initializing the logger with an incorrect namespace.
 * This required log4j.jar, but can be customized to be used with any other logger
 */
public class LoggerUtil{
	
	private static final Logger LOG = Logger.getLogger(LoggerUtil.class);
	
	public static Logger getInstance(){
		
		try{
			
			String callingClassName = Thread.currentThread().getStackTrace()[2].getClassName();
			return Logger.getLogger(callingClassName);
		}
		catch (Exception e){
			
			LOG.error("Could not retrieve appropriate class name to initilization logger " + e);
			return Logger.getLogger("DefaultLogger");
		}
	}
}