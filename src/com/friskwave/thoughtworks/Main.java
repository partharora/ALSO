package com.friskwave.thoughtworks;

import org.apache.log4j.Logger;

import com.friskwave.thoughtworks.MerchantsGuide.MerchantsGuide;
import com.friskwave.thoughtworks.utils.ApplicationConstants;
import com.friskwave.utils.LoggerUtil;

public class Main {
	
	private static final Logger LOG = LoggerUtil.getInstance();
	
	public static void main(String[] args) {
		
		LOG.debug("Initializing constansts..");
		ApplicationConstants.initializeConstants();
		
		LOG.info("Execution of code to compute soluation for ThoughtWorks problem starts");
		
		// compute output by calling appropriate class object function
		MerchantsGuide smallDataset = new MerchantsGuide(ApplicationConstants.defaultInputFilePath, ApplicationConstants.defaultOutputFilePath);
		
		LOG.info("Execution of code to compute soluation for ThoughtWorks problem ends");
	}
}