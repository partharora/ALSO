package com.friskwave.thoughtworks.utils;

import org.apache.log4j.Logger;

import com.friskwave.utils.LoggerUtil;

public class ApplicationConstants {
		
	private static final Logger LOG = LoggerUtil.getInstance();
	
	public static String applicationBasePath;
	public static String defaultInputFilePath;
	public static String defaultOutputFilePath;
	
	public static void initializeConstants(){
		
		LOG.info("Initializing application constants");
		
		try{
			applicationBasePath = System.getProperty("user.dir");
			defaultInputFilePath = applicationBasePath + System.getProperty("file.separator") + "uploads/inputFiles/small.in";
			defaultOutputFilePath = applicationBasePath + System.getProperty("file.separator") + "uploads/outputFiles/small.out";
			
			LOG.info("Application constants initialized successfully");
		}
		catch(Exception IOException){
			
			LOG.error("IO exception - This is most likely to occur because we were not able to fetch the path of the current directory to set applicationBasePath " + IOException);
		}
	}
}