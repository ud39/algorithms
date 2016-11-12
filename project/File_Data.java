package test;

import java.io.IOException;

public class File_Data {
	
	public String[] read(int k){

		String file_name = "/home/bas/Schreibtisch/Tabelle.txt";
		String [] array = null ;
		
		try
		{
		 Read_File file = new Read_File(file_name);
		 String[] aryLines = file.Openfile();
		 array = aryLines[k].split(",");
		}
		
		
		catch (IOException e)
		{
		System.out.println(e.getMessage());
		
	}
		return array;
	}
	
}