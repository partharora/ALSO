package com.friskwave.thoughtworks.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.friskwave.utils.LoggerUtil;

/**
 * @author Parth Arora
 * @email arora.parth@gmail.com
 * @desc Generic implemetaion for a file read operation and a file write operation.
 * This implementation was written with a single objective in mind, to help with easy file parsing for
 * "thoughtworks" problems
 */
public class FileOperations {
	
	private static final Logger LOG = LoggerUtil.getInstance();
	
	private List<String> _inpContent = new ArrayList<String>();
	private String _outContent = null;
	
	public FileOperations(){
		
		// Constructor for this class. It does nothing so far.
	}
	
	public void ReadWrite(String IOoperation, String filename){
		
		try{
			if(IOoperation=="read"){
				
				BufferedReader fileReader = new BufferedReader(new FileReader(filename));
				
				String strLine;
				
				while((strLine = fileReader.readLine()) != null){
					
					_inpContent.add(strLine);
				}
				
				LOG.info("Read the input data required to solve the problem");
			}
			else if(IOoperation=="write"){
				
				BufferedWriter out = new BufferedWriter(new FileWriter(filename));
				out.write(this._outContent);
				out.close();
				
				LOG.info("Solution of problem written to file");
			}
			else{
				LOG.warn("Invalid / Insufficient argument provided");
			}
		}
		catch (FileNotFoundException e){
			
			LOG.error("File Not Found" + e);
		}
		catch (IOException e){
			
			LOG.error("IO Exception occured" + e);
		}
	}
	
	public List<String> getCases(){
		
		return _inpContent;
	}
	
	public void setOutputContent(String OutputData){
		
		this._outContent = OutputData;
	}
}