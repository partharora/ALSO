package com.friskwave.gcj.problems.Cycles;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.friskwave.gcj.problems.GoogleCodeJam;
import com.friskwave.gcj.problems.BogTrust.domains.BogTrustDomain;
import com.friskwave.gcj.problems.Bullseye.domains.BullseyeDomain;
import com.friskwave.gcj.problems.Cycles.domains.CyclesDomain;
import com.friskwave.gcj.problems.OldMagician.domains.OldMagicianDomain;
import com.friskwave.utils.LoggerUtil;

/**
 * @author Parth Arora
 * @email arora.parth@gmail.com
 * @desc This is a solution to google code jam problem.
 * The problem definition URL is https://code.google.com/codejam/contest/32004/dashboard#s=p2
 */
public class Cycles extends GoogleCodeJam{
	
	private static final Logger LOG = LoggerUtil.getInstance();
	
	public Cycles(){
		
		super();
	}
	
	public Cycles(String inputFilePath, String outputFilePath){
		
		super(inputFilePath, outputFilePath);
	}
	
	public Cycles(String inputFilePath, String outputFilePath, int noOfLinesPerUseCase){
		
		super(inputFilePath, outputFilePath, noOfLinesPerUseCase);
	}
	
	@Override
	public void formatUseCases(List<String> fileData, int noOfLinesPerUseCase) {
		
		boolean caseCountFlag = false;
		
		Iterator<String> useCaseItr = fileData.iterator();
		CyclesDomain cd;
		
		int caseCount = 1;
		
		while(useCaseItr.hasNext()){
			
			if(caseCountFlag==true){
				
				cd = new CyclesDomain();
				String[] currentCaseData = useCaseItr.next().toString().split(" ");
				
				cd.setCaseNumber(caseCount);
				
				super.getUseCaseList().add(cd);
				caseCount++;
			}
			else{
				useCaseItr.next();
				caseCountFlag = true;
			}
		}
	}
	
	@Override
	public String computeIndividualCase(Object caseData) {
		
		// get case information into appropriate variables
		int caseNumber = ((OldMagicianDomain) caseData).getCaseNumber();
		long caseSolution = 0;
		
		return "Case #" + caseNumber + ": " + caseSolution;
	}
}