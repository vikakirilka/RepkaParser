package org.ITstep;

import java.io.FileWriter;
import java.io.IOException;

public class FileIOManager {
	
	private static final String FILE_PATH = "C:\\Users\\vikul\\Documents\\test";
	
	public static synchronized void writeTextToFile(String text)
	
	public static void writeTextToFile(String text) {
		  FileWriter writer = null;
		  try {
		   writer = new FileWriter(FILE_PATH, true);
		   writer.write(text);
		   writer.flush();
		  } catch (IOException e) {
		   e.printStackTrace();
		  } finally {
		   try {
		    writer.close();
		   } catch (IOException e) {
		    e.printStackTrace();
		   }
		  }
		  
		 }

}
