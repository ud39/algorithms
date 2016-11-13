package project;


import java.io.IOException;

public class File_Data {
	
	public String[] read(int k){

<<<<<<< HEAD
		String file_name = "/home/bas/Schreibtisch/Tabelle.txt";
		String [] array = null ;
		
=======
	String[] games;
	public String[] read(int i)
	{
		String file_name = "C:\\Users\\World\\Desktop\\2006\\32.txt";

>>>>>>> test2
		try
		{
		 Read_File file = new Read_File(file_name);
		 String[] aryLines = file.Openfile();
<<<<<<< HEAD
		 array = aryLines[k].split(",");
=======
			 games = (aryLines[i].split(";"));
>>>>>>> test2
		}
		
		
		catch (IOException e)
		{
		System.out.println(e.getMessage());
		
	}
<<<<<<< HEAD
		return array;
	}
	
=======
>>>>>>> test2
}