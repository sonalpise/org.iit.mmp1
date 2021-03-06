package org.iit.util;

import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class Logger {
	/** sev = Severity (E | W | I) **/

	public static void log(String sev, String message) {
		System.out.println(">> " + sev + " :: " + message);
	} // log String

	public static void log(String sev, HashMap<String, List<String>> map) {
		System.out.println(">> " + sev + " :: ");
		for (String key : map.keySet())
			System.out.println(key + ": " + map.get(key));
	} // log HashMap<Key, List>

	public static void logKV(String sev, HashMap<String, String> map) {
		System.out.println(">> " + sev + " :: ");
		for (String key : map.keySet())
			System.out.println(key + ": " + map.get(key));
	} // logKV HashMap<Key, String>

	public static void log(String sev, Sheet sheet) {
		System.out.println(">> " + sev + " :: ");
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			Row currentRow = sheet.getRow(i);
			for (int j = 0; j < currentRow.getLastCellNum(); j++) {
				System.out.print(currentRow.getCell(j));
			} // loop columns
			System.out.println();
		} // loop rows
	} // log Sheet

} // Logger

// My Notes

//Java program to demonstrate 
//Logger.log(Level level, String msg)  method 
/*
import java.util.logging.Level; 
import java.util.logging.Logger; 
public class GFG { 
 public static void main(String[] args) 
 { 
     // Create a Logger 
     Logger logger 
         = Logger.getLogger( 
             GFG.class.getName()); 
     // log messages using log(Level level, String msg) 
     logger.log(Level.INFO, "This is message 1"); 
     logger.log(Level.WARNING, "This is message 2"); 
 } 
} */


