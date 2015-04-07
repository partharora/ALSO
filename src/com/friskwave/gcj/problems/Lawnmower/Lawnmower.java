package com.friskwave.gcj.problems.Lawnmower;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import sun.io.Converters;

import com.friskwave.gcj.problems.GoogleCodeJam;
import com.friskwave.gcj.problems.Lawnmower.domains.LawnmowerDomain;
import com.friskwave.utils.LoggerUtil;
import com.google.common.primitives.Ints;

/**
 * @author Parth Arora
 * @email arora.parth@gmail.com
 * @desc This is a solution to google code jam problem.
 * The problem definition URL is https://code.google.com/codejam/contest/2270488/dashboard#s=p1
 */
public class Lawnmower extends GoogleCodeJam {
	
	private static final Logger LOG = LoggerUtil.getInstance();
	
	public Lawnmower(){
		
		super();
	}
	
	public Lawnmower(String inputFilePath, String outputFilePath){
		
		super(inputFilePath, outputFilePath);
	}
	
	public Lawnmower(String inputFilePath, String outputFilePath, int noOfLinesPerUseCase){
		
		super(inputFilePath, outputFilePath, noOfLinesPerUseCase);
	}
	
	@Override
	public String computeIndividualCase(Object caseData){
		
		int caseNumber = ((LawnmowerDomain) caseData).getCaseNumber();
		int caseRows  = ((LawnmowerDomain) caseData).getCaseRows();
		int caseColumns  = ((LawnmowerDomain) caseData).getCaseColumns();
		
		String useCaseResult = "YES";
		
		int[][] caseDataArray = ((LawnmowerDomain) caseData).getCaseItems();
		int[][] lawnState = new int[caseRows][caseColumns];
		
		for(int k=0;k<caseRows;k++){
			
			// for current case - initialize lawnState to 100mm for all blocks
			Arrays.fill(lawnState[k], 100);
			
			// mow the lawn row-wise
			int maxRowHeight = Ints.max(caseDataArray[k]);
			
			for(int newCol=0;newCol<caseColumns;newCol++){
				lawnState[k][newCol] = maxRowHeight;
			}
		}
		
		// mow the lawn column-wise
		for(int k=0;k<caseColumns;k++){
			
			int maxColHeight;
			int[] tempColArr = new int[caseRows];
			
			// get max column value
			for(int m=0;m<caseRows;m++){
				
				tempColArr[m] = caseDataArray[m][k];
			}
			
			maxColHeight = Ints.max(tempColArr);
			
			// set mowed values for column
			for(int m=0;m<caseRows;m++){
				
				if(lawnState[m][k] > maxColHeight)lawnState[m][k] = maxColHeight;
			}
		}
		
		// compare the input and computed values
		for(int rows=0;rows<caseRows;rows++){
			
			for(int cols=0;cols<caseColumns;cols++){
				
				if(caseDataArray[rows][cols]!=lawnState[rows][cols]){
					
					useCaseResult = "NO";
					break;
				}
			}
			
			if(useCaseResult == "NO")break;
		}
		
		return "Case #" + caseNumber + ": " + useCaseResult;
	}
	
	@Override
	public void formatUseCases(List<String> fileData, int noOfLinesPerUseCase){
		
		boolean caseCountFlag = false;
		
		Iterator<String> useCaseItr = fileData.iterator();
		
		int caseCount = 1;
		
		while(useCaseItr.hasNext()){
			
			if(caseCountFlag==true){
				
				LawnmowerDomain probDomainObj = new LawnmowerDomain();
				
				String[] lawmDimensions = useCaseItr.next().toString().split(" ");
				String[][] lawnBlockHeightsStr;
				int [][] lawnBlockHeights;
						
				// set case number
				probDomainObj.setCaseNumber(caseCount);
				
				// set lawn dimensions for this use-case
				probDomainObj.setCaseRows(Integer.parseInt(lawmDimensions[0]));
				probDomainObj.setCaseColumns(Integer.parseInt(lawmDimensions[1]));
				
				// initialize lawnBlockHieghts
				lawnBlockHeightsStr = new String[Integer.parseInt(lawmDimensions[0])][Integer.parseInt(lawmDimensions[1])];
				lawnBlockHeights = new int[Integer.parseInt(lawmDimensions[0])][Integer.parseInt(lawmDimensions[1])];
				
				for(int row=0;row<Integer.parseInt(lawmDimensions[0]);row++){
					
					lawnBlockHeightsStr[row] = useCaseItr.next().toString().split(" ");
					
					for(int col=0;col<lawnBlockHeightsStr[row].length;col++){
						lawnBlockHeights[row][col] = Integer.parseInt(lawnBlockHeightsStr[row][col].toString());
					}
				}
				
				probDomainObj.setCaseItems(lawnBlockHeights);
				
				super.getUseCaseList().add(probDomainObj);
				
				caseCount++;
			}
			else{
				useCaseItr.next();
				caseCountFlag = true;
			}
		}
	}
}