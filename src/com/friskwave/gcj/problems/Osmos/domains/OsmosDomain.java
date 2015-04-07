package com.friskwave.gcj.problems.Osmos.domains;

import java.util.ArrayList;

public class OsmosDomain {
	
	private int caseNumber;
	private long moteSize;
	private long moteCount;
	private ArrayList<Long> motesAvailable;
	
	public int getCaseNumber() {
		return caseNumber;
	}
	
	public void setCaseNumber(int caseNumber) {
		this.caseNumber = caseNumber;
	}

	public long getMoteSize() {
		return moteSize;
	}

	public void setMoteSize(long moteSize) {
		this.moteSize = moteSize;
	}

	public long getMoteCount() {
		return moteCount;
	}

	public void setMoteCount(long moteCount) {
		this.moteCount = moteCount;
	}

	public ArrayList<Long> getMotesAvailable() {
		return motesAvailable;
	}

	public void setMotesAvailable(ArrayList<Long> motesAvailable) {
		this.motesAvailable = motesAvailable;
	}
}