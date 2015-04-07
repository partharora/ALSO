package com.friskwave.gcj.utils;

import org.apache.log4j.Logger;

import com.friskwave.utils.LoggerUtil;

public class ApplicationConstants {
		
	private static final Logger LOG = LoggerUtil.getInstance();
	
	public static String applicationBasePath;
	public static String defaultSmallInputFilePath;
	public static String defaultSmallOutputFilePath;
	public static String defaultLargeInputFilePath;
	public static String defaultLargeOutputFilePath;
	
	public static void initializeConstants(){
		
		LOG.info("Initializing application constants");
		
		try{
			applicationBasePath = System.getProperty("user.dir");
			defaultSmallInputFilePath = applicationBasePath + System.getProperty("file.separator") + "uploads/inputFiles/small.in";
			defaultSmallOutputFilePath = applicationBasePath + System.getProperty("file.separator") + "uploads/outputFiles/small.out";
			defaultLargeInputFilePath = applicationBasePath + System.getProperty("file.separator") + "uploads/inputFiles/large.in";
			defaultLargeOutputFilePath = applicationBasePath + System.getProperty("file.separator") + "uploads/outputFiles/large.out";
			
			LOG.info("Application constants initialized successfully");
		}
		catch(Exception IOException){
			
			LOG.error("IO exception - This is most likely to occur because we were not able to fetch the path of the current directory to set applicationBasePath " + IOException);
		}
	}
}