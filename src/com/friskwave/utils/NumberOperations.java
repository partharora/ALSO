package com.friskwave.utils;

import java.util.HashMap;

/**
 * @author Parth Arora
 * @email arora.parth@gmail.com
 * @desc This class contains generic implementations for certain mathematical
 * operations that might come of use in multiple places.
 */
public class NumberOperations {
	
	public static final long HCF(long p, long q) {
		
		if (q == 0) {
			return p;
		}
		
		return HCF(q, p % q);
	}
	
	public static final HashMap<Integer,Long> getPrimeFactors(long number) {
		
		long n = number;
		
		HashMap<Integer,Long> factors = new HashMap<Integer,Long>();
		
		int  k = 0;
		for (long i = 2; i <= n; i++) {
			while (n % i == 0) {
				factors.put(k++,i);
				n /= i;
			}
		}
		
		return factors;
	}
	
	public static Double convertRomanToDecimal(String romanValue) {
		
		Double arabicValue = 0.0;
		
		romanValue = romanValue.toUpperCase();
		
		for(int x = 0;x<romanValue.length();x++) {
		
			switch(romanValue.charAt(x)) {
				
				case 'M':
					arabicValue += 1000;
					break;
				
				case 'D':
					arabicValue += 500;
					break;
				
				case 'C':
					arabicValue += 100;
					break;
				
				case 'L':
					arabicValue += 50;
					break;
				
				case 'X':
					arabicValue += 10;
					break;
				
				case 'V':
					arabicValue += 5;
					break;
				
				case 'I':
					arabicValue += 1;
					break;
			}
		}
		
		if(romanValue.contains("IV")) {
			arabicValue-=2;
		}
		if(romanValue.contains("IX")) {
			arabicValue-=2;
		}
		if(romanValue.contains("XL")) {
			arabicValue-=20;
		}
		if(romanValue.contains("XC")) {
			arabicValue-=20;
		}
		if(romanValue.contains("CD")) {
			arabicValue-=200;
		}
		if(romanValue.contains("CM")) {
			arabicValue-=200;
		}
		
		return arabicValue;
	}
}