package test;

import java.io.IOException;

public class Read_Table {

	String[] games;
	public String[] read(int i)
	{
		String file_name = "C:\\Users\\World\\Desktop\\Tabelle2.txt";

		try
		{
		 Read_File file = new Read_File(file_name);
		 String[] aryLines = file.Openfile();
			 games = (aryLines[i].split(":"));
		}
		catch (IOException e)
		{
		System.out.println(e.getMessage());
		}
		return games;
	}
	public static void main(String args[]) throws IOException{


	}
}
