package project;


import java.io.IOException;

public class File_Data {

	String[] games;
	public String[] read(int i)
	{
		String file_name = "C:\\Users\\World\\Desktop\\2006\\32.txt";

		try
		{
		 Read_File file = new Read_File(file_name);
		 String[] aryLines = file.Openfile();
			 games = (aryLines[i].split(";"));
		}
		catch (IOException e)
		{
		System.out.println(e.getMessage());
		}
		return games;
	}
}
