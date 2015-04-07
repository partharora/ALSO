package com.friskwave.gcj.problems.BogTrust;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.friskwave.gcj.problems.GoogleCodeJam;
import com.friskwave.gcj.problems.BogTrust.domains.BogTrustDomain;
import com.friskwave.utils.LoggerUtil;

/**
 * @author Parth Arora
 * @email arora.parth@gmail.com
 * @desc This is a solution to google code jam problem.
 * The problem definition URL is https://code.google.com/codejam/contest/975485/dashboard#s=p0
 */
public class BogTrust extends GoogleCodeJam{
	
	private static final Logger LOG = LoggerUtil.getInstance();
	
	public BogTrust(){
		
		super();
	}
	
	public BogTrust(String inputFilePath, String outputFilePath){
		
		super(inputFilePath, outputFilePath);
	}
	
	public BogTrust(String inputFilePath, String outputFilePath, int noOfLinesPerUseCase){
		
		super(inputFilePath, outputFilePath, noOfLinesPerUseCase);
	}
	
	@Override
	public void formatUseCases(List<String> fileData, int noOfLinesPerUseCase) {
		
		boolean caseCountFlag = false;
		
		Iterator<String> useCaseItr = fileData.iterator();
		BogTrustDomain btd;
		
		int caseCount = 1;
		
		while(useCaseItr.hasNext()){
			
			if(caseCountFlag==true){
				
				btd = new BogTrustDomain();
				
				for(int i=0;i<noOfLinesPerUseCase;i++){
					
					switch (i){
						
						case 0:
							
							String tempVar = useCaseItr.next().toString();
							String[] tempVarItems = tempVar.split(" ");
							String[] tempVarItems2 = new String[tempVarItems.length-1];
							
							btd.setCaseNumber(caseCount);
							btd.setCaseData(tempVar);
							btd.setCaseItemCount(Integer.parseInt(tempVarItems[0]));
							
							for(int k=1; k<tempVarItems.length; k++){
								
								tempVarItems2[k-1] = tempVarItems[k];
							}
							
							btd.setCaseItems(tempVarItems2);
							
							break;
						default:
							LOG.error("One of the enteries could not be mapped to the domain object. The problem could be with both input data format and/or the program");
					}
				}
				
				super.getUseCaseList().add(btd);
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
		
		int caseNumber = ((BogTrustDomain) caseData).getCaseNumber();
		int caseItemCount = ((BogTrustDomain) caseData).getCaseItemCount();
		int caseSolution = 0;
		String caseDataString = ((BogTrustDomain) caseData).getCaseData();
		String[] caseDataItems = ((BogTrustDomain) caseData).getCaseItems();
		
		// regEx Pattern
		String oRegExPattern = "O";
		String bRegExPattern = "B";
		
		// Pattern Object
		Pattern oCP = Pattern.compile(oRegExPattern);
		Pattern bCP = Pattern.compile(bRegExPattern);
		
		// matcher object
		Matcher oM = oCP.matcher(caseDataString);
		Matcher bM = bCP.matcher(caseDataString);
		
		System.out.println(oM.toString());
		
		//int oChkPoints[]
		// TODO actual solution to the problem
		
		return "Case #" + caseNumber + ": " + caseSolution;
	}
}