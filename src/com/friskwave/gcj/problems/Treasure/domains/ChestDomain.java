package com.friskwave.gcj.problems.Treasure.domains;

public class ChestDomain {
	
	private int chestNumber;
	private int keyToOpenChest;
	private int[] keysInChest;
	
	public int getChestNumber() {
		return chestNumber;
	}
	
	public void setChestNumber(int chestNumber) {
		this.chestNumber = chestNumber;
	}

	public int getKeyToOpenChest() {
		return keyToOpenChest;
	}
	
	public void setKeyToOpenChest(int keyToOpenChest) {
		this.keyToOpenChest = keyToOpenChest;
	}
	
	public int[] getKeysInChest() {
		return keysInChest;
	}
	
	public void setKeysInChest(int[] keysInChest) {
		this.keysInChest = keysInChest;
	}
}