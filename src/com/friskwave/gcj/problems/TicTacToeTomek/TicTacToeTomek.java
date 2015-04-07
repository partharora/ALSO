package com.friskwave.gcj.problems.TicTacToeTomek;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.friskwave.gcj.problems.GoogleCodeJam;
import com.friskwave.gcj.problems.TicTacToeTomek.domains.TicTacToeTomekDomain;
import com.friskwave.utils.LoggerUtil;

/**
 * @author Parth Arora
 * @email arora.parth@gmail.com
 * @desc This is a solution to google code jam problem.
 * The problem definition URL is https://code.google.com/codejam/contest/2270488/dashboard#s=p0
 */
public class TicTacToeTomek extends GoogleCodeJam {
	
	private static final Logger LOG = LoggerUtil.getInstance();
	
	private static String[] possibleOutputs = {"X won","O won","Draw","Game has not completed"};
	
	public TicTacToeTomek(){
		
		super();
	}
	
	public TicTacToeTomek(String inputFilePath, String outputFilePath){
		
		super(inputFilePath, outputFilePath);
	}
	
	public TicTacToeTomek(String inputFilePath, String outputFilePath, int noOfLinesPerUseCase){
		
		super(inputFilePath, outputFilePath, noOfLinesPerUseCase);
	}
	
	@Override
	public String computeIndividualCase(Object caseData){
		
		int caseNumber = ((TicTacToeTomekDomain) caseData).getCaseNumber();
		String[][] caseDataArray = ((TicTacToeTomekDomain) caseData).getCaseItems();
		boolean caseHasEmptySpace = ((TicTacToeTomekDomain) caseData).isHasEmptySpace();
		
		String useCaseResult = null;
		
		// check top-left node diagonal
		if(!caseDataArray[0][0].equalsIgnoreCase(".")){
			
			if(caseDataArray[0][0].equalsIgnoreCase("T")){
				
				if(caseDataArray[1][1].equalsIgnoreCase(caseDataArray[2][2]) && caseDataArray[1][1].equalsIgnoreCase(caseDataArray[3][3])){
					
					useCaseResult = getResultFromArray(caseDataArray[1][1]);
				}
			}
			else{
				
				if((caseDataArray[0][0].equalsIgnoreCase(caseDataArray[1][1]) || caseDataArray[1][1].equalsIgnoreCase("T")) &&
					(caseDataArray[0][0].equalsIgnoreCase(caseDataArray[2][2]) || caseDataArray[2][2].equalsIgnoreCase("T")) &&
					(caseDataArray[0][0].equalsIgnoreCase(caseDataArray[3][3]) || caseDataArray[3][3].equalsIgnoreCase("T"))){
					
					useCaseResult = getResultFromArray(caseDataArray[0][0]);
				}
			}
		}
		
		// check top-right node diagonal
		if(!caseDataArray[0][3].equalsIgnoreCase(".")){
			
			if(caseDataArray[0][3].equalsIgnoreCase("T")){
				
				if(caseDataArray[1][2].equalsIgnoreCase(caseDataArray[2][1]) && caseDataArray[1][2].equalsIgnoreCase(caseDataArray[3][0])){
					
					useCaseResult = getResultFromArray(caseDataArray[1][2]);
				}
			}
			else{
				
				if((caseDataArray[0][3].equalsIgnoreCase(caseDataArray[1][2]) || caseDataArray[1][2].equalsIgnoreCase("T")) &&
						(caseDataArray[0][3].equalsIgnoreCase(caseDataArray[2][1]) || caseDataArray[2][1].equalsIgnoreCase("T")) &&
						(caseDataArray[0][3].equalsIgnoreCase(caseDataArray[3][0]) || caseDataArray[3][0].equalsIgnoreCase("T"))){
						
						useCaseResult = getResultFromArray(caseDataArray[0][3]);
				}
			}
		}
		
		// check rows for winner if diagonal check does not return a result
		if(useCaseResult==null){
		
			for(int row=0;row<caseDataArray.length;row++){
				
				// check rows for solution
				if(!caseDataArray[row][0].equalsIgnoreCase(".")){
					
					if(caseDataArray[row][0].equalsIgnoreCase("T")){
						
						if(caseDataArray[row][1].equalsIgnoreCase(caseDataArray[row][2]) && caseDataArray[row][2].equalsIgnoreCase(caseDataArray[row][3])){
							
							useCaseResult = getResultFromArray(caseDataArray[row][1]);
							break;
						}
					}
					else{
						
						if((caseDataArray[row][0].equalsIgnoreCase(caseDataArray[row][1]) || caseDataArray[row][1].equalsIgnoreCase("T")) &&
								(caseDataArray[row][0].equalsIgnoreCase(caseDataArray[row][2]) || caseDataArray[row][2].equalsIgnoreCase("T")) &&
								(caseDataArray[row][0].equalsIgnoreCase(caseDataArray[row][3]) || caseDataArray[row][3].equalsIgnoreCase("T"))){
							
							useCaseResult = getResultFromArray(caseDataArray[row][0]);
							break;
						}
					}
				}
				
				// check columns for solution
				if(!caseDataArray[0][row].equalsIgnoreCase(".")){
					
					if(caseDataArray[0][row].equalsIgnoreCase("T")){
						
						if(caseDataArray[1][row].equalsIgnoreCase(caseDataArray[2][row]) && caseDataArray[2][row].equalsIgnoreCase(caseDataArray[3][row])){
							
							useCaseResult = getResultFromArray(caseDataArray[1][row]);
							break;
						}
					}
					else{
						
						if((caseDataArray[0][row].equalsIgnoreCase(caseDataArray[1][row]) || caseDataArray[1][row].equalsIgnoreCase("T")) &&
								(caseDataArray[0][row].equalsIgnoreCase(caseDataArray[2][row]) || caseDataArray[2][row].equalsIgnoreCase("T")) &&
								(caseDataArray[0][row].equalsIgnoreCase(caseDataArray[3][row]) || caseDataArray[3][row].equalsIgnoreCase("T"))){
							
							useCaseResult = getResultFromArray(caseDataArray[0][row]);
							break;
						}
					}
				}
			}
		}
		
		if(!caseHasEmptySpace && useCaseResult==null)useCaseResult = TicTacToeTomek.possibleOutputs[2];
		else if(caseHasEmptySpace && useCaseResult==null)useCaseResult = TicTacToeTomek.possibleOutputs[3];
		
		return "Case #" + caseNumber + ": " + useCaseResult;
	}
	
	@Override
	public void formatUseCases(List<String> fileData, int noOfLinesPerUseCase){
		
		boolean caseCountFlag = false;
		
		Iterator<String> useCaseItr = fileData.iterator();
		
		int caseCount = 1;
		
		while(useCaseItr.hasNext()){
			
			if(caseCountFlag==true){
				
				TicTacToeTomekDomain probDomainObj = new TicTacToeTomekDomain();
				String[][] currentCaseItems = new String[4][4];
				
				for(int i=0;i<=noOfLinesPerUseCase;i++){
					
					probDomainObj.setCaseNumber(caseCount);
					String currentLineStr = (useCaseItr.hasNext()) ? useCaseItr.next() : "";
					
					if(!currentLineStr.equalsIgnoreCase("")){
						
						if(currentLineStr.contains(".")){
							
							probDomainObj.setHasEmptySpace(true);
						}
						
						currentCaseItems[i] = currentLineStr.toString().split("(?!^)");
					}
				}
				
				probDomainObj.setCaseItems(currentCaseItems);
				
				super.getUseCaseList().add(probDomainObj);
				
				caseCount++;
			}
			else{
				useCaseItr.next();
				caseCountFlag = true;
			}
		}
	}
	
	private String getResultFromArray(String str){
		
		if(str.equalsIgnoreCase("O")){
			
			return TicTacToeTomek.possibleOutputs[1];
		}
		else if(str.equalsIgnoreCase("X")){
			
			return TicTacToeTomek.possibleOutputs[0];
		}
		else{
			
			return null;
		}
	}
}