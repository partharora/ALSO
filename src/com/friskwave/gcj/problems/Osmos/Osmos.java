package com.friskwave.gcj.problems.Osmos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
import com.friskwave.gcj.problems.Osmos.domains.OsmosDomain;
import com.friskwave.gcj.problems.storecredit.domains.StoreCreditItemDomain;
import com.friskwave.utils.LoggerUtil;

/**
 * @author Parth Arora
 * @email arora.parth@gmail.com
 * @desc This is a solution to google code jam problem.
 * The problem definition URL is https://code.google.com/codejam/contest/32004/dashboard#s=p2
 */
public class Osmos extends GoogleCodeJam{
	
	private static final Logger LOG = LoggerUtil.getInstance();
	
	public Osmos(){
		
		super();
	}
	
	public Osmos(String inputFilePath, String outputFilePath){
		
		super(inputFilePath, outputFilePath);
	}
	
	public Osmos(String inputFilePath, String outputFilePath, int noOfLinesPerUseCase){
		
		super(inputFilePath, outputFilePath, noOfLinesPerUseCase);
	}
	
	@Override
	public void formatUseCases(List<String> fileData, int noOfLinesPerUseCase) {
		
		boolean caseCountFlag = false;
		
		Iterator<String> useCaseItr = fileData.iterator();
		OsmosDomain od;
		
		int caseCount = 1;
		
		while(useCaseItr.hasNext()){
			
			if(caseCountFlag==true){
				
				od = new OsmosDomain();
				String[] currentCaseData = useCaseItr.next().toString().split(" ");
				
				od.setCaseNumber(caseCount);
				od.setMoteSize(Long.parseLong(currentCaseData[0]));
				od.setMoteCount(Long.parseLong(currentCaseData[1]));
				
				String[] motesAvailable = useCaseItr.next().toString().split(" ");
				ArrayList<Long> tempMotesAvailable = new ArrayList<Long>();
				
				for(int i=0;i<motesAvailable.length;i++){
					
					tempMotesAvailable.add(Long.parseLong(motesAvailable[i]));
				}
				
				od.setMotesAvailable(tempMotesAvailable);
				
				super.getUseCaseList().add(od);
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
		int caseNumber = ((OsmosDomain) caseData).getCaseNumber();
		long caseMoteSize = ((OsmosDomain) caseData).getMoteSize();
		long computedCaseMoteSize = caseMoteSize;
		long caseMoteCount = ((OsmosDomain) caseData).getMoteCount();
		long caseSolution = 0;
		long caseSolution1 = 0;
		long caseSolution2 = 0;
		
		long[] removedElements = new long[(int) (caseMoteSize/2 + 1)];
		int k = 0;
		
		ArrayList<Long> caseMotesAvailable = ((OsmosDomain) caseData).getMotesAvailable();
		Collections.sort(caseMotesAvailable);
		
		Iterator<Long> itr = caseMotesAvailable.iterator();
		
		if(caseMoteSize>1){
			
			while(itr.hasNext()){
				
				long nextMote = itr.next();
				
				if(nextMote<computedCaseMoteSize){
					
					computedCaseMoteSize += nextMote;
					//itr.remove();
				}
				else{
					
					
					caseSolution1 = sigma2Kminus1(computedCaseMoteSize,nextMote,caseSolution);
					
					removedElements[k] = caseMotesAvailable.get(caseMotesAvailable.size() - k - 2);k++;
					if(!Arrays.asList(removedElements).contains(nextMote)){
						caseSolution2++;
					}
						
					caseSolution2 = sigma2Kminus1(computedCaseMoteSize,nextMote,caseSolution);
				}
			}
		}
		else{
			caseSolution = caseMoteCount;
		}
		
		if(caseSolution>caseMoteCount)caseSolution = caseMoteCount;
		
		caseSolution = Math.min(caseSolution, Math.min(caseSolution1, caseSolution2));
		
		return "Case #" + caseNumber + ": " + caseSolution;
	}
	
	public long sigma2Kminus1(long startValue, long maxValue, long lastIterationCnt){
		
		if(startValue>maxValue)return lastIterationCnt;
		else{
			startValue += startValue - 1;
			lastIterationCnt++;
			return sigma2Kminus1(startValue, maxValue, lastIterationCnt);
		}
	}
}