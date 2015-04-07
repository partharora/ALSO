package com.friskwave.gcj.problems.Pogo;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.friskwave.gcj.problems.GoogleCodeJam;
import com.friskwave.gcj.problems.Pogo.domains.PogoDomain;
import com.friskwave.utils.LoggerUtil;

/**
 * @author Parth Arora
 * @email arora.parth@gmail.com
 * @desc This is a solution to google code jam problem.
 * The problem definition URL is https://code.google.com/codejam/contest/2437488/dashboard#s=p1
 */
public class Pogo extends GoogleCodeJam{
	
	private static final Logger LOG = LoggerUtil.getInstance();
	
	public Pogo(){
		
		super();
	}
	
	public Pogo(String inputFilePath, String outputFilePath){
		
		super(inputFilePath, outputFilePath);
	}
	
	public Pogo(String inputFilePath, String outputFilePath, int noOfLinesPerUseCase){
		
		super(inputFilePath, outputFilePath, noOfLinesPerUseCase);
	}
	
	@Override
	public void formatUseCases(List<String> fileData, int noOfLinesPerUseCase) {
		
		boolean caseCountFlag = false;
		
		Iterator<String> useCaseItr = fileData.iterator();
		PogoDomain pd;
		
		int caseCount = 1;
		
		while(useCaseItr.hasNext()){
			
			if(caseCountFlag==true){
				
				pd = new PogoDomain();
					
				String axisProvided = useCaseItr.next().toString();
				String[] axisToAchieve = axisProvided.split(" ");
				
				pd.setCaseNumber(caseCount);
				pd.setCaseData(axisProvided);
				pd.setCaseX(Long.parseLong(axisToAchieve[0]));
				pd.setCaseY(Long.parseLong(axisToAchieve[1]));
				
				super.getUseCaseList().add(pd);
				
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
		
		int caseNumber = ((PogoDomain) caseData).getCaseNumber();
		Long expectedAxisX = ((PogoDomain) caseData).getCaseX();
		Long expectedAxisY = ((PogoDomain) caseData).getCaseY();
		
		Long distanceFromStart = (long) Math.ceil(calculateDistance(0, 0, expectedAxisX, expectedAxisX));
		Long noOfSteps = 1l;
		Long currentAxisX = 0l;
		Long currentAxisY = 0l;
		
		String caseSolution = "";
		
		while((currentAxisX!=expectedAxisX || currentAxisY!=expectedAxisY) && noOfSteps<500){
			
			String moveOutput = makeAmove(noOfSteps, distanceFromStart, currentAxisX, currentAxisY, expectedAxisX, expectedAxisY);
			
			if(moveOutput!=null && moveOutput!=""){
				
				if(moveOutput=="E"){
					currentAxisX = currentAxisX + noOfSteps;
				}
				else if(moveOutput=="W"){
					currentAxisX = currentAxisX - noOfSteps;
				}
				else if(moveOutput=="N"){
					currentAxisY = currentAxisY + noOfSteps;
				}
				else if(moveOutput=="S"){
					currentAxisY = currentAxisY - noOfSteps;
				}
				
				//caseSolution += moveOutput + ": X-(" + currentAxisX + ") Y-(" + currentAxisY + "),";
				caseSolution += moveOutput;
				noOfSteps++;
			}
			else{
				LOG.error("Invalid move made for case with 'X': " + expectedAxisX + ", 'Y': " + expectedAxisY);
			}
		}
		
		return "Case #" + caseNumber + ": " + caseSolution;
	}
	
	public double calculateDistance(long x1, long y1, long x2, long y2){
		
		double distance = Math.sqrt(Math.pow((x2-x1), 2)+Math.pow((y2-y1), 2));
		return distance;
	}
	
	public String makeAmove(long jumpLength, long distanceFromStart, long x1, long y1, long x2, long y2){
		
		long eastDistance = (long) Math.floor(calculateDistance(x1+jumpLength,y1,x2,y2));
		long westDistance = (long) Math.floor(calculateDistance(x1-jumpLength,y1,x2,y2));
		long northDistance = (long) Math.floor(calculateDistance(x1,y1+jumpLength,x2,y2));
		long southDistance = (long) Math.floor(calculateDistance(x1,y1-jumpLength,x2,y2));
		
		long minDistance = Math.min(Math.min(Math.min(eastDistance, westDistance), northDistance), southDistance);
		
		if(minDistance<=distanceFromStart){
			
			if(minDistance==eastDistance)return "E";
			else if(minDistance==westDistance)return "W";
			else if(minDistance==northDistance)return "N";
			else if(minDistance==southDistance)return "S";
		}
		else{
			
			LOG.error("Error in usecase with expected final position at 'X': " + x2 + ", 'Y': " + y2);
		}
		
		return "'NULL'";
	}
	
	public double[] solveForSumOfN(long n){
		
		double smallerRoot = 0;
		double largerRoot = 0;
		
		double[] returnVal = new double[2];
		
		if((1 - (4 * (-2 * n))) >= 0){
			
			smallerRoot = -1 - Math.sqrt((1 - (4 * (-2 * n))));
			largerRoot = -1 + Math.sqrt((1 - (4 * (-2 * n))));
		}
		
		returnVal[0] = smallerRoot;
		returnVal[1] = largerRoot;
		
		
		System.out.println(returnVal[0] + ", " + returnVal[1]);
		return returnVal;
	}
}