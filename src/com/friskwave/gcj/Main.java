package com.friskwave.gcj;

import org.apache.log4j.Logger;

import com.friskwave.gcj.problems.Pogo.Pogo;
import com.friskwave.gcj.utils.ApplicationConstants;
import com.friskwave.utils.LoggerUtil;

public class Main {
	
	private static final Logger LOG = LoggerUtil.getInstance();
	
	public static void main(String[] args) {
		
		LOG.debug("Calling ApplicationConstants.initializeConstants() from main() method");
		ApplicationConstants.initializeConstants();
		
		LOG.info("Execution of code to compute soluation for GoogleCodeJam problem starts");
		
		// compute output by calling appropriate class object function
		Pogo smallDataset = new Pogo();
		//Pogo largeDataset = new Pogo(ApplicationConstants.defaultLargeInputFilePath, ApplicationConstants.defaultLargeOutputFilePath,1);
		
		LOG.info("Execution of code to compute soluation for GoogleCodeJam problem ends");
	}
}