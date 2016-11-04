package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
		FileReader file_to_read = new FileReader(path);
		BufferedReader bf = new BufferedReader(file_to_read);

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
