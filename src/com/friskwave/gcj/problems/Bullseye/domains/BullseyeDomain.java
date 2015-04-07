package com.friskwave.gcj.problems.Bullseye.domains;

public class BullseyeDomain {
	
	private int caseNumber;
	private long paintAvailable;
	private long startingRadius;
	
	public int getCaseNumber() {
		return caseNumber;
	}
	
	public void setCaseNumber(int caseNumber) {
		this.caseNumber = caseNumber;
	}

	public long getPaintAvailable() {
		return paintAvailable;
	}

	public void setPaintAvailable(long paintAvailable) {
		this.paintAvailable = paintAvailable;
	}

	public long getStartingRadius() {
		return startingRadius;
	}

	public void setStartingRadius(long startingRadius) {
		this.startingRadius = startingRadius;
	}
}