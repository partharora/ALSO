package com.friskwave.gcj.problems.reversewords;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.friskwave.gcj.problems.GoogleCodeJam;
import com.friskwave.gcj.problems.reversewords.domains.ReverseWordDomain;

import com.friskwave.utils.LoggerUtil;

/**
 * @author Parth Arora
 * @email arora.parth@gmail.com
 * @desc This is a solution to google code jam problem.
 * The problem definition URL is https://code.google.com/codejam/contest/351101/dashboard#s=p1
 */
public class ReverseWords extends GoogleCodeJam {
	
	private static final Logger LOG = LoggerUtil.getInstance();
	
	public ReverseWords(){
		
		super();
	}
	
	public ReverseWords(String inputFilePath, String outputFilePath){
		
		super(inputFilePath, outputFilePath);
	}
	
	public ReverseWords(String inputFilePath, String outputFilePath, int noOfLinesPerUseCase){
		
		super(inputFilePath, outputFilePath, noOfLinesPerUseCase);
	}
	
	@Override
	public String computeIndividualCase(Object caseData) {
		
		ReverseWordDomain rwd = (ReverseWordDomain) caseData;
		String [] caseStrArr = rwd.getCaseWordListArr();
		StringBuffer outputString = new StringBuffer();
		
		boolean addSpaceFlag = false;
		
		for(int i=caseStrArr.length-1;i>=0;i--){
			
			if(addSpaceFlag==false){
				
				outputString.append(caseStrArr[i]);
				addSpaceFlag = true;
			}
			else{
				outputString.append(" " + caseStrArr[i]);
			}
		}
		
		return "Case #" + rwd.getCaseNumber() + ": " + outputString.toString();
	}

	@Override
	public void formatUseCases(List<String> fileData, int noOfLinesPerUseCase) {
		
		boolean caseCountFlag = false;
		
		Iterator<String> useCaseItr = fileData.iterator();
		ReverseWordDomain rwd;
		
		int caseCount = 1;
		
		while(useCaseItr.hasNext()){
			
			if(caseCountFlag==true){
				
				rwd = new ReverseWordDomain();
				
				for(int i=0;i<noOfLinesPerUseCase;i++){
					
					switch (i){
						
						case 0:
							
							String currentCaseStr = useCaseItr.next().toString();
							
							rwd.setCaseNumber(caseCount);
							rwd.setCaseWordList(currentCaseStr);
							
							if(currentCaseStr.indexOf(" ")!=-1)rwd.setCaseWordListArr(currentCaseStr.split(" "));
							else {
								
								String [] temp = new String[1];
								temp[0] = currentCaseStr;
								
								rwd.setCaseWordListArr(temp);
							}
							
							break;
						default:
							LOG.error("One of the enteries could not be mapped to the domain object. The problem could be with both input data format and/or the program");
					}
				}
				
				super.getUseCaseList().add(rwd);
				caseCount++;
			}
			else{
				useCaseItr.next();
				caseCountFlag = true;
			}
		}
	}
}