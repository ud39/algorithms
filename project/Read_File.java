package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Read_File {
	private String path;

	public Read_File(String file_path)
	{
	path = file_path;
	}
	public String[] Openfile() throws IOException
	{
	FileReader fr = new FileReader(path);
	BufferedReader textReader = new BufferedReader(fr);
	int numberOfLines = readlines();
	String[] textData = new String[numberOfLines];
	for(int i = 0; i < numberOfLines;i++)
	{
	textData[i] = textReader.readLine();
	}
	textReader.close();
	return textData;
	}
	private int readlines() throws IOException
	{
		File fileDir = new File(path);
		BufferedReader bf =  new BufferedReader(
				   new InputStreamReader(
		                      new FileInputStream(fileDir), "UTF8"));

		String aLine;
		int numberOfLines = 0;
		while((aLine = bf.readLine()) != null)
		{
			numberOfLines++;
		}
		bf.close();
		return numberOfLines;
	}
}