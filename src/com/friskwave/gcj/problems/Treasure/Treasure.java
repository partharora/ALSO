package com.friskwave.gcj.problems.Treasure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.friskwave.gcj.problems.GoogleCodeJam;
import com.friskwave.gcj.problems.TicTacToeTomek.domains.TicTacToeTomekDomain;
import com.friskwave.gcj.problems.Treasure.domains.ChestDomain;
import com.friskwave.gcj.problems.Treasure.domains.TreasureDomain;
import com.friskwave.utils.LoggerUtil;

/**
 * @author Parth Arora
 * @email arora.parth@gmail.com
 * @desc This is a solution to google code jam problem.
 * The problem definition URL is https://code.google.com/codejam/contest/2270488/dashboard#s=p3
 */
public class Treasure extends GoogleCodeJam {
	
	private static final Logger LOG = LoggerUtil.getInstance();
	
	public Treasure(){
		
		super();
	}
	
	public Treasure(String inputFilePath, String outputFilePath){
		
		super(inputFilePath, outputFilePath);
	}
	
	public Treasure(String inputFilePath, String outputFilePath, int noOfLinesPerUseCase){
		
		super(inputFilePath, outputFilePath, noOfLinesPerUseCase);
	}
	
	@Override
	public String computeIndividualCase(Object caseData){
		
		int caseNumber = ((TreasureDomain) caseData).getCaseNumber();
		int noOfChestsToOpen = ((TreasureDomain) caseData).getNoOfChestsToOpen();
		HashMap<Integer, Integer> keysAvailable = new HashMap<Integer, Integer>();
		ChestDomain[] useCaseChests = ((TreasureDomain) caseData).getChests();
		
		// result of current use case (order in which the chests should open)
		String useCaseResult = null;
		ArrayList<String> possibleSolutions = new ArrayList<String>();
		
		// TODO computation logic implementation goes here
		for(int chestCnt=0;chestCnt<noOfChestsToOpen;chestCnt++){
			
			
		}
		
		useCaseResult = this.getLexicographicallySmallestSolution(possibleSolutions);
		return "Case #" + caseNumber + ": " + useCaseResult;
	}
	
	private String getLexicographicallySmallestSolution(ArrayList<String> possibleSolutions){
		
		// TODO calculate which of the viable solutions is Lexicographically smallest
		return null;
	}
	
	@Override
	public void formatUseCases(List<String> fileData, int noOfLinesPerUseCase){
		
		boolean caseCountFlag = false;
		
		Iterator<String> useCaseItr = fileData.iterator();
		
		int caseCount = 1;
		
		while(useCaseItr.hasNext()){
			
			if(caseCountFlag==true){
				
				TreasureDomain probDomainObj = new TreasureDomain();
				
				String[] line1 = useCaseItr.next().toString().split(" ");
				String[] initalKeys = useCaseItr.next().toString().split(" ");
				HashMap<Integer, Integer> caseKeys = new HashMap<Integer, Integer>();
				
				probDomainObj.setCaseNumber(caseCount);
				probDomainObj.setNoOfChestsToOpen(Integer.parseInt(line1[1]));
				
				for(int i=0;i<Integer.parseInt(line1[0]);i++){
					
					caseKeys.put(Integer.parseInt(initalKeys[i]), 1);
				}
				
				probDomainObj.setKeysAvailable(caseKeys);
				
				ChestDomain[] cd = new ChestDomain[Integer.parseInt(line1[1])];
				
				for(int m=0;m<Integer.parseInt(line1[1]);m++){
					
					String[] chestData = useCaseItr.next().toString().split(" ");
					int[] keysInChest = new int[chestData.length-2];
					
					cd[m] = new ChestDomain();
					cd[m].setChestNumber(m + 1);
					cd[m].setKeyToOpenChest(Integer.parseInt(chestData[0]));
					
					for(int l=2;l<chestData.length;l++){
						
						keysInChest[l-2] = Integer.parseInt(chestData[l]);
					}
					
					cd[m].setKeysInChest(keysInChest);
				}
				
				probDomainObj.setChests(cd);
				
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