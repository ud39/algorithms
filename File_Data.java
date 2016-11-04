package test;

import java.io.IOException;

public class File_Data {

	public static void main(String args[]) throws IOException{

		String file_name = "/home/bas/Downloads/ex01.txt";

		try
		{
		 Read_File file = new Read_File(file_name);
		 String[] aryLines = file.Openfile();
		 for(int i = 0 ; i < aryLines.length;i++)
		 {
			 System.out.println(aryLines[i]);
		 }
		}
		catch (IOException e)
		{
		System.out.println(e.getMessage());
		}
	}
}