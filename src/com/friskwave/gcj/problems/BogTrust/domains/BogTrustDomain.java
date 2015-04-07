package com.friskwave.gcj.problems.BogTrust.domains;

public class BogTrustDomain {
	
	private int caseNumber;
	private String caseData;
	private int caseItemCount;
	private String[] caseItems;
	
	public int getCaseNumber() {
		return caseNumber;
	}
	
	public void setCaseNumber(int caseNumber) {
		this.caseNumber = caseNumber;
	}
	
	public String getCaseData() {
		return caseData;
	}
	
	public void setCaseData(String caseData) {
		this.caseData = caseData;
	}

	public int getCaseItemCount() {
		return caseItemCount;
	}

	public void setCaseItemCount(int caseItemCount) {
		this.caseItemCount = caseItemCount;
	}

	public String[] getCaseItems() {
		return caseItems;
	}

	public void setCaseItems(String[] caseItems) {
		this.caseItems = caseItems;
	}
}