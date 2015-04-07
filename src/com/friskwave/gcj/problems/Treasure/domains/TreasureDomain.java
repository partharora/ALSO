package com.friskwave.gcj.problems.Treasure.domains;

import java.util.HashMap;

public class TreasureDomain {
	
	private int caseNumber;
	private int noOfChestsToOpen;
	private HashMap<Integer, Integer> keysAvailable;
	private ChestDomain[] chests;
	
	public int getCaseNumber() {
		return caseNumber;
	}
	
	public void setCaseNumber(int caseNumber) {
		this.caseNumber = caseNumber;
	}

	public int getNoOfChestsToOpen() {
		return noOfChestsToOpen;
	}

	public void setNoOfChestsToOpen(int noOfChestsToOpen) {
		this.noOfChestsToOpen = noOfChestsToOpen;
	}

	public HashMap<Integer, Integer> getKeysAvailable() {
		return keysAvailable;
	}

	public void setKeysAvailable(HashMap<Integer, Integer> keysAvailable) {
		this.keysAvailable = keysAvailable;
	}

	public ChestDomain[] getChests() {
		return chests;
	}

	public void setChests(ChestDomain[] chests) {
		this.chests = chests;
	}
}