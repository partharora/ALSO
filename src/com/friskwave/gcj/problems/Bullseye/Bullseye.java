package com.friskwave.gcj.problems.Bullseye;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.friskwave.gcj.problems.GoogleCodeJam;
import com.friskwave.gcj.problems.BogTrust.domains.BogTrustDomain;
import com.friskwave.gcj.problems.Bullseye.domains.BullseyeDomain;
import com.friskwave.utils.LoggerUtil;

/**
 * @author Parth Arora
 * @email arora.parth@gmail.com
 * @desc This is a solution to google code jam problem.
 * The problem definition URL is https://code.google.com/codejam/contest/2418487/dashboard#s=p0
 */
public class Bullseye extends GoogleCodeJam{
	
	private static final Logger LOG = LoggerUtil.getInstance();
	
	public Bullseye(){
		
		super();
	}
	
	public Bullseye(String inputFilePath, String outputFilePath){
		
		super(inputFilePath, outputFilePath);
	}
	
	public Bullseye(String inputFilePath, String outputFilePath, int noOfLinesPerUseCase){
		
		super(inputFilePath, outputFilePath, noOfLinesPerUseCase);
	}
	
	@Override
	public void formatUseCases(List<String> fileData, int noOfLinesPerUseCase) {
		
		boolean caseCountFlag = false;
		
		Iterator<String> useCaseItr = fileData.iterator();
		BullseyeDomain bed;
		
		int caseCount = 1;
		
		while(useCaseItr.hasNext()){
			
			if(caseCountFlag==true){
				
				bed = new BullseyeDomain();
				String[] currentCaseData = useCaseItr.next().toString().split(" ");
				
				bed.setCaseNumber(caseCount);
				bed.setStartingRadius(Long.parseLong(currentCaseData[0]));
				bed.setPaintAvailable(Long.parseLong(currentCaseData[1]));
				
				super.getUseCaseList().add(bed);
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
		
		int caseNumber = ((BullseyeDomain) caseData).getCaseNumber();
		long startingRadius = ((BullseyeDomain) caseData).getStartingRadius();
		long paintAvailable = ((BullseyeDomain) caseData).getPaintAvailable();
		long caseSolution = 0;
		boolean isComputable = true;
		
		while(paintAvailable>0){
			
			if((startingRadius * 2) <= paintAvailable){
				
				if(isComputable){
					paintAvailable -= (startingRadius * 2) + 1;
					isComputable=false;
				}
				else paintAvailable -= (startingRadius * 2);
				
				caseSolution++;
				startingRadius+=2;
			}
			else break;
		}
		
		return "Case #" + caseNumber + ": " + caseSolution;
	}
}