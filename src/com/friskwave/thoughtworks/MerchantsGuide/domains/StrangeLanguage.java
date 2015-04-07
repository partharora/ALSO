package com.friskwave.thoughtworks.MerchantsGuide.domains;

public class StrangeLanguage {
	
	private String alienNotation;
	private String romanNotation;
	private Double arabicValue;
	private String unitForValue;
	
	public String getAlienNotation() {
		return alienNotation;
	}
	
	public void setAlienNotation(String alienNotation) {
		this.alienNotation = alienNotation;
	}
	
	public String getRomanNotation() {
		return romanNotation;
	}

	public void setRomanNotation(String romanNotation) {
		this.romanNotation = romanNotation;
	}

	public Double getArabicValue() {
		return arabicValue;
	}
	
	public void setArabicValue(Double arabicValue) {
		this.arabicValue = arabicValue;
	}
	
	public String getUnitForValue() {
		return unitForValue;
	}
	
	public void setUnitForValue(String unitForValue) {
		this.unitForValue = unitForValue;
	}
}