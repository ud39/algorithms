package test;

import java.io.IOException;

public class ChampionShip_Test {
static Match[][] table = new Match[34][9];
int day = 0;
static File_Data files = new File_Data();
public void fill_Table(String[] matches)
{
}
public static void main(String args[]) throws IOException{

	for(int i = 0; i <= 33; i++)
	{
		for(int j = 0; j <= 8; j++){
		String[] matches = files.read(j);
		Match m = new Match(matches[1], matches[2], matches[3]);
		table[i][j] = m;
		}
	}


	System.out.println(table[0][0].getT1());

}
}

