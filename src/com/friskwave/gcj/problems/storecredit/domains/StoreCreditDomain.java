package com.friskwave.gcj.problems.storecredit.domains;

public class StoreCreditDomain {
	
	private int caseNumber;
	private int creditAvailable;
	private int storeItemCount;
	private String itemPriceList;
	
	public int getCaseNumber() {
		return caseNumber;
	}
	
	public void setCaseNumber(int caseNumber) {
		this.caseNumber = caseNumber;
	}
	
	public int getCreditAvailable() {
		return creditAvailable;
	}
	
	public void setCreditAvailable(int creditAvailable) {
		this.creditAvailable = creditAvailable;
	}
	
	public int getStoreItemCount() {
		return storeItemCount;
	}
	
	public void setStoreItemCount(int storeItemCount) {
		this.storeItemCount = storeItemCount;
	}
	
	public String getItemPriceList() {
		return itemPriceList;
	}
	
	public void setItemPriceList(String itemPriceList) {
		this.itemPriceList = itemPriceList;
	}
}