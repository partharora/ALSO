package com.friskwave.gcj.problems;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.friskwave.gcj.utils.ApplicationConstants;
import com.friskwave.gcj.utils.FileOperations;
import com.friskwave.utils.LoggerUtil;

abstract public class GoogleCodeJam{
	
	private static final Logger LOG = LoggerUtil.getInstance();
	
	private List<Object> useCaseList = new ArrayList<Object>();
	private int noOfLinesPerUseCase;
	
	private String solvedOutput = "";
	
	FileOperations fo = new FileOperations();
	
	public GoogleCodeJam(){
		
		this(ApplicationConstants.defaultSmallInputFilePath, ApplicationConstants.defaultSmallOutputFilePath, 1);
	}
	
	public GoogleCodeJam(String inputFilePath, String outputFilePath){
		
		this(inputFilePath, outputFilePath, 1);
	}
	
	public GoogleCodeJam(String inputFilePath, String outputFilePath, int noOfLinesPerUseCase){
		
		LOG.info("Starting GoogleCodeJam Problem computation");
		
		// set number of lines per useCase
		this.noOfLinesPerUseCase = noOfLinesPerUseCase;
		
		// read data from input file
		fo.ReadWrite("read",inputFilePath);
		
		// get parsed use-cases
		this.formatUseCases(fo.getCases(), this.noOfLinesPerUseCase);
		
		// compute the output of the problem for given inputs
		this.computeOutput();
		
		// write the solution to the output file
		fo.setOutputContent(solvedOutput);
		fo.ReadWrite("write", outputFilePath);
	}
	
	private void appendCaseSolution(String caseSolution){
		
		if(this.solvedOutput.equals("")){
			this.solvedOutput = caseSolution;
		}
		else{
			this.solvedOutput += "\n" + caseSolution;
		}
	}
	
	private void computeOutput(){
		
		Iterator<Object> computeCase = useCaseList.iterator();
		
		while(computeCase.hasNext()){
			
			String currentSolution;
			Object caseDataObject = computeCase.next();
			
			currentSolution = computeIndividualCase(caseDataObject);
			this.appendCaseSolution(currentSolution);
		}
	}
	
	public List<Object> getUseCaseList(){
		
		return this.useCaseList;
	}
	
	public void setUseCaseList(List<Object> useCaseList){
		
		this.useCaseList = useCaseList;
	}
	
	public int getNoOfLinesPerUseCase() {
		return noOfLinesPerUseCase;
	}

	public void setNoOfLinesPerUseCase(int noOfLinesPerUseCase) {
		this.noOfLinesPerUseCase = noOfLinesPerUseCase;
	}
	
	/**
	 * @author Parth Arora
	 * @email arora.parth@gmail.com
	 * 
	 * @desc This method is supposed to populate the useCaseListObject for further computation to take place
	 * @param fileData
	 * @param noOfLinesPerGroup
	 */
	abstract public void formatUseCases(List<String> fileData, int noOfLinesPerUseCase);

	/**
	 * @author Parth Arora
	 * @email arora.parth@gmail.com
	 * 
	 * @desc This method should be overridden based on the problem statement
	 * @param caseData
	 * @return output result string in google code jam output format for the computed case
	 */
	abstract public String computeIndividualCase(Object caseData);
}