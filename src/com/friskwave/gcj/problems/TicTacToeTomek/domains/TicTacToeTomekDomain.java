package com.friskwave.gcj.problems.TicTacToeTomek.domains;

public class TicTacToeTomekDomain {
	
	private int caseNumber;
	private String [][] caseItems;
	private boolean hasEmptySpace = false;
	
	public int getCaseNumber() {
		return caseNumber;
	}
	
	public void setCaseNumber(int caseNumber) {
		this.caseNumber = caseNumber;
	}
	
	public String[][] getCaseItems() {
		return caseItems;
	}
	
	public void setCaseItems(String[][] caseItems) {
		this.caseItems = caseItems;
	}

	public boolean isHasEmptySpace() {
		return hasEmptySpace;
	}

	public void setHasEmptySpace(boolean hasEmptySpace) {
		this.hasEmptySpace = hasEmptySpace;
	}
}