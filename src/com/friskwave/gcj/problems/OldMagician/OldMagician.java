package com.friskwave.gcj.problems.OldMagician;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.friskwave.gcj.problems.GoogleCodeJam;
import com.friskwave.gcj.problems.BogTrust.domains.BogTrustDomain;
import com.friskwave.gcj.problems.Bullseye.domains.BullseyeDomain;
import com.friskwave.gcj.problems.OldMagician.domains.OldMagicianDomain;
import com.friskwave.utils.LoggerUtil;

/**
 * @author Parth Arora
 * @email arora.parth@gmail.com
 * @desc This is a solution to google code jam problem.
 * The problem definition URL is https://code.google.com/codejam/contest/32004/dashboard#s=p0
 */
public class OldMagician extends GoogleCodeJam{
	
	private static final Logger LOG = LoggerUtil.getInstance();
	
	public OldMagician(){
		
		super();
	}
	
	public OldMagician(String inputFilePath, String outputFilePath){
		
		super(inputFilePath, outputFilePath);
	}
	
	public OldMagician(String inputFilePath, String outputFilePath, int noOfLinesPerUseCase){
		
		super(inputFilePath, outputFilePath, noOfLinesPerUseCase);
	}
	
	@Override
	public void formatUseCases(List<String> fileData, int noOfLinesPerUseCase) {
		
		boolean caseCountFlag = false;
		
		Iterator<String> useCaseItr = fileData.iterator();
		OldMagicianDomain omd;
		
		int caseCount = 1;
		
		while(useCaseItr.hasNext()){
			
			if(caseCountFlag==true){
				
				omd = new OldMagicianDomain();
				String[] currentCaseData = useCaseItr.next().toString().split(" ");
				
				omd.setCaseNumber(caseCount);
				omd.setWhiteBallCount(Long.parseLong(currentCaseData[0]));
				omd.setBlackBallCount(Long.parseLong(currentCaseData[1]));
				
				super.getUseCaseList().add(omd);
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
		long blackBallCount = ((OldMagicianDomain) caseData).getBlackBallCount();
		long whiteBallCount = ((OldMagicianDomain) caseData).getWhiteBallCount();
		
		String caseSolution = "UNKNOWN";
		
		if(blackBallCount%2 != 0)caseSolution="BLACK";
		else caseSolution="WHITE";
		
		return "Case #" + caseNumber + ": " + caseSolution;
	}
}