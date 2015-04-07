package com.friskwave.gcj.problems.storecredit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.friskwave.gcj.problems.GoogleCodeJam;
import com.friskwave.gcj.problems.storecredit.domains.StoreCreditDomain;
import com.friskwave.gcj.problems.storecredit.domains.StoreCreditItemDomain;
import com.friskwave.utils.LoggerUtil;

/**
 * @author Parth Arora
 * @email arora.parth@gmail.com
 * @desc This is a solution to google code jam problem.
 * The problem definition URL is https://code.google.com/codejam/contest/351101/dashboard#s=p0
 */
public class StoreCredit extends GoogleCodeJam {
	
	private static final Logger LOG = LoggerUtil.getInstance();
	
	public StoreCredit(){
		
		super();
	}
	
	public StoreCredit(String inputFilePath, String outputFilePath){
		
		super(inputFilePath, outputFilePath);
	}
	
	public StoreCredit(String inputFilePath, String outputFilePath, int noOfLinesPerUseCase){
		
		super(inputFilePath, outputFilePath, noOfLinesPerUseCase);
	}
	
	@Override
	public String computeIndividualCase(Object caseData){
		
		int caseNumber = ((StoreCreditDomain) caseData).getCaseNumber();
		int caseCredit = ((StoreCreditDomain) caseData).getCreditAvailable();
		int currentItemCount = ((StoreCreditDomain) caseData).getStoreItemCount();
		String[] currentItemPriceList = ((StoreCreditDomain) caseData).getItemPriceList().split(" ");
		
		List<StoreCreditItemDomain> alCIP_Asc = new ArrayList<StoreCreditItemDomain>();
		List<StoreCreditItemDomain> alCIP_Desc = new ArrayList<StoreCreditItemDomain>();
		
		StoreCreditItemDomain outputObject1, outputObject2;
		
		for(int k=0;k<currentItemPriceList.length;k++){
			
			StoreCreditItemDomain scid = new StoreCreditItemDomain();
			scid.setItemIndex(k+1);
			scid.setItemValue(Integer.parseInt(currentItemPriceList[k]));
			
			alCIP_Asc.add(scid);
			alCIP_Desc.add(scid);
		}
		
		Collections.sort(alCIP_Asc, new StoreCreditComparator(true));
		Collections.sort(alCIP_Desc, new StoreCreditComparator(false));
		
		Iterator<StoreCreditItemDomain> ascSortedEntries = alCIP_Asc.iterator();
		Iterator<StoreCreditItemDomain> descSortedEntries = alCIP_Desc.iterator();
		
		outputObject1 = ascSortedEntries.next();
		outputObject2 = descSortedEntries.next();
		
		while(ascSortedEntries.hasNext()){
			
			if((outputObject1.getItemValue() + outputObject2.getItemValue())==caseCredit){
				
				if((outputObject1.getItemIndex()!=outputObject2.getItemIndex())){
					break;
				}
				else {
					outputObject2 = descSortedEntries.next();
				}
			}
			else if((outputObject1.getItemValue() + outputObject2.getItemValue()) > caseCredit){
				
				outputObject2 = descSortedEntries.next();
			}
			else if((outputObject1.getItemValue() + outputObject2.getItemValue()) < caseCredit){
				
				outputObject1 = ascSortedEntries.next();
			}
		}
		
		if(outputObject1.getItemIndex() > outputObject2.getItemIndex())return "Case #" + caseNumber + ": " + outputObject2.getItemIndex() + " " + outputObject1.getItemIndex();
		else return "Case #" + caseNumber + ": " + outputObject1.getItemIndex() + " " + outputObject2.getItemIndex();
	}
	
	@Override
	public void formatUseCases(List<String> fileData, int noOfLinesPerUseCase){
		
		boolean caseCountFlag = false;
		
		Iterator<String> useCaseItr = fileData.iterator();
		StoreCreditDomain scd;
		
		int caseCount = 1;
		
		while(useCaseItr.hasNext()){
			
			if(caseCountFlag==true){
				
				scd = new StoreCreditDomain();
				
				for(int i=0;i<noOfLinesPerUseCase;i++){
					
					switch (i){
						
						case 0:
							scd.setCaseNumber(caseCount);
							scd.setCreditAvailable(Integer.parseInt(useCaseItr.next().toString()));
							break;
						case 1:
							scd.setStoreItemCount(Integer.parseInt(useCaseItr.next().toString()));
							break;
						case 2:
							scd.setItemPriceList(useCaseItr.next().toString());
							break;
						default:
							LOG.error("One of the enteries could not be mapped to the domain object. The problem could be with both input data format and/or the program");
					}
				}
				
				super.getUseCaseList().add(scd);
				caseCount++;
			}
			else{
				useCaseItr.next();
				caseCountFlag = true;
			}
		}
	}
	
	class StoreCreditComparator implements Comparator<Object> {
		
		private boolean sortOrder;
		
		public StoreCreditComparator(){
			
			this(true);
		}
		
		public StoreCreditComparator(boolean sortOrder){
			
			this.sortOrder = sortOrder;
		}
		
		@Override
		public int compare(Object scid1, Object scid2) {
			
			if(this.sortOrder==true)return ((Integer)((StoreCreditItemDomain) scid1).getItemValue()).compareTo((Integer)((StoreCreditItemDomain) scid2).getItemValue());
			else return ((Integer)((StoreCreditItemDomain) scid2).getItemValue()).compareTo((Integer)((StoreCreditItemDomain) scid1).getItemValue());
		}
	}
}