package com.agilepet.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * 
 * @author harold
 *
 */
public class Logger {

	
public static void logData(String data){
	
	  BufferedWriter writer = null;
      try {
          //create a temporary file
          File logFile = new File("C:/Users/Administrator/Desktop/data/results.txt");

          // This will output the full path where the file will be written to...
          System.out.println(logFile.getCanonicalPath());

          writer = new BufferedWriter(new FileWriter(logFile, true));
          writer.write(data+"\n");
      } catch (Exception e) {
          e.printStackTrace();
      } finally {
          try {
              // Close the writer regardless of what happens...
              writer.close();
          } catch (Exception e) {
          }
      }
	
}
	

	
}
