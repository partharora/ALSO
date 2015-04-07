package com.friskwave.thoughtworks.MerchantsGuide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.friskwave.thoughtworks.MerchantsGuide.domains.StrangeLanguage;
import com.friskwave.thoughtworks.utils.FileOperations;
import com.friskwave.utils.NumberOperations;
import com.friskwave.utils.LoggerUtil;

/**
 * @author Parth Arora
 * @email arora.parth@gmail.com
 * @desc This is a solution to a problem that ThoughtWorks asks as part of their interview process.
 * The problem definition URL is https://mail.google.com/mail/ca/u/0/?shva=1#inbox/13f519e2288d20b4 (Not accessible publicly)
 */
public class MerchantsGuide {
	
	private static final Logger LOG = LoggerUtil.getInstance();
	
	FileOperations fo = new FileOperations();
	
	private String solvedOutput = "";
	private List<StrangeLanguage> questionsAndAnswers;
	private List<StrangeLanguage> information;
	private Map<String, Integer> romanArabicMapping;
	private Map<String, StrangeLanguage> easyInfoAccessObject;
	private int invalidInputCount = 0;
	
	public MerchantsGuide(String inputFilePath, String outputFilePath) {
		
		// initialize romanToArabic map
		romanArabicMapping = new HashMap<String, Integer>();
		romanArabicMapping.put("I", 1);
		romanArabicMapping.put("V", 5);
		romanArabicMapping.put("X", 10);
		romanArabicMapping.put("L", 50);
		romanArabicMapping.put("C", 100);
		romanArabicMapping.put("D", 500);
		romanArabicMapping.put("M", 1000);
		
		// read data from input file
		fo.ReadWrite("read",inputFilePath);
		LOG.debug("completed reading data from input file - " + inputFilePath);
		
		// make sense out of input
		this.parseInput(fo.getCases());
		LOG.debug("completed parsing the input read from file");
		
		// compute the output of the problem for given inputs
		this.computeOutput();
		LOG.debug("completed computing the output");
		
		// write the solution to the output file
		fo.setOutputContent(solvedOutput);
		fo.ReadWrite("write", outputFilePath);
		LOG.debug("completed writing the solution to output file - " + outputFilePath);
	}
	
	private void computeOutput() {
		
		Double caseOutput;
		
		Iterator<StrangeLanguage> itr = questionsAndAnswers.iterator();
		boolean creditFlag;
		
		while(itr.hasNext()) {
			
			caseOutput = 0.0;
			creditFlag = false;
			
			StringBuffer romanRepOfStrangeLanguage = new StringBuffer();
			Double caseMultiplicationFactor = 1.0;
			
			// interesting play of words for a variable name :D
			String questionInQuestion = itr.next().getAlienNotation();
			String[] questionInQuestionArr = questionInQuestion.trim().split(" ");
			
			for(int k=0; k<questionInQuestionArr.length - 1; k++) {
				
				if(easyInfoAccessObject.get(questionInQuestionArr[k]).getUnitForValue()=="Credits") {
					caseMultiplicationFactor *= easyInfoAccessObject.get(questionInQuestionArr[k]).getArabicValue();
					creditFlag = true;
				}
				else romanRepOfStrangeLanguage.append(easyInfoAccessObject.get(questionInQuestionArr[k]).getRomanNotation());
			}
			
			caseOutput = caseMultiplicationFactor * NumberOperations.convertRomanToDecimal(romanRepOfStrangeLanguage.toString());
			appendOutput(questionInQuestion.replace("?", "").trim() + " is " + caseOutput.toString(), creditFlag);
		}
		
		if(invalidInputCount>0) {
			
			LOG.warn(invalidInputCount + " inputs in the file could not be parsed, because the input seems to be malformed.");
		}
		
		for(int c=0; c<invalidInputCount; c++) {
			
			appendOutput("Are you sure you don't want to rephrase this statement ?", false);
		}
	}
	
	private void parseInput(List<String> inputData) {
		
		information = new ArrayList<StrangeLanguage>();
		questionsAndAnswers = new ArrayList<StrangeLanguage>();
		easyInfoAccessObject = new HashMap<String, StrangeLanguage>();
		
		// we will store the data with Credits unit in this list and make sense out of it in a different iteration
		List<String> secondIterationData = new ArrayList<String>();
		
		String infoRegExPattern1 = "[\\w\\s]+is[\\s]+[0-9]+[\\s]*Credits[\\s]*";
		String infoRegExPattern2 = "[\\w]+[\\s]+is[\\s]+[\\w]+";
		String questionRegExPattern1 = "how much is[\\s\\w]+\\?";
		String questionRegExPattern2 = "how many Credits is[\\s\\w]+\\?";
		
		StrangeLanguage sl;
		
		Iterator<String> itr = inputData.iterator();
		while(itr.hasNext()) {
			
			String currentInput = itr.next();
			sl = new StrangeLanguage();
			
			String[] questionArr = currentInput.split(" is ");
			
			if(currentInput.matches(questionRegExPattern1)) {
				
				sl.setAlienNotation(questionArr[1].trim());
				questionsAndAnswers.add(sl);
			}
			else if(currentInput.matches(questionRegExPattern2)) {
				
				sl.setAlienNotation(questionArr[1].trim());
				sl.setUnitForValue("Credits");
				questionsAndAnswers.add(sl);
			}
			else if(currentInput.matches(infoRegExPattern1)) {
				
				secondIterationData.add(currentInput);
			}
			else if(currentInput.matches(infoRegExPattern2)){
				
				sl.setAlienNotation(questionArr[0].trim());
				sl.setArabicValue(NumberOperations.convertRomanToDecimal(questionArr[1].trim()));
				sl.setRomanNotation(questionArr[1].trim());
				information.add(sl);
				easyInfoAccessObject.put(questionArr[0].trim(), sl);
			}
			else {
				invalidInputCount++;
				LOG.warn("Couldn't understand input - " + currentInput);
			}
		}
		
		/*
		 * an assumption has been made that when providing information
		 * a combination of alien inputs will be used
		 * only in case where Credits are being specified
		 */
		
		// Making sense out of a combination of Alien inputs.
		Iterator<String> secondItr = secondIterationData.iterator();
		while(secondItr.hasNext()) {
			
			sl = new StrangeLanguage();
			
			String currentInput = secondItr.next();
			String[] identifierAndValue = currentInput.split(" is ");
			
			String[] indetifierArr = identifierAndValue[0].split(" ");
			String[] valueArr = identifierAndValue[1].trim().split(" ");
			
			String romanNotation = "";
			for(int i=0; i<indetifierArr.length - 1; i++) {
				
				StrangeLanguage slTmp = easyInfoAccessObject.get(indetifierArr[i]);
				romanNotation += slTmp.getRomanNotation();
			}
			
			sl.setAlienNotation(indetifierArr[indetifierArr.length - 1]);
			sl.setArabicValue(Integer.parseInt(valueArr[0])/(NumberOperations.convertRomanToDecimal(romanNotation)));
			sl.setUnitForValue("Credits");
			
			information.add(sl);
			easyInfoAccessObject.put(indetifierArr[indetifierArr.length - 1], sl);
		}
	}
	
	private void appendOutput(String caseOutput, boolean creditFlag) {
		
		if(this.solvedOutput=="")this.solvedOutput = caseOutput;
		else this.solvedOutput += "\n" + caseOutput;
		
		if(creditFlag) this.solvedOutput += " Credits"; 
	}
}