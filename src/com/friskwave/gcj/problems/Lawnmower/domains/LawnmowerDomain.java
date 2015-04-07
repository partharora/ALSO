package com.friskwave.gcj.problems.Lawnmower.domains;

public class LawnmowerDomain {
	
	private int caseNumber;
	private int caseRows;
	private int caseColumns;
	private int[][] caseItems;
	
	public int getCaseNumber() {
		return caseNumber;
	}
	
	public void setCaseNumber(int caseNumber) {
		this.caseNumber = caseNumber;
	}
	
	public int[][] getCaseItems() {
		return caseItems;
	}
	
	public void setCaseItems(int[][] caseItems) {
		this.caseItems = caseItems;
	}
	
	public int getCaseRows() {
		return caseRows;
	}
	
	public void setCaseRows(int caseRows) {
		this.caseRows = caseRows;
	}
	
	public int getCaseColumns() {
		return caseColumns;
	}
	
	public void setCaseColumns(int caseColumns) {
		this.caseColumns = caseColumns;
	}
}