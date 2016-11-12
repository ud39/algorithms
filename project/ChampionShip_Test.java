package project;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import test.File_Data;

public class ChampionShip_Test {
static Match[][] table = new Match[34][9];
int day = 0;
static File_Data files = new File_Data();
static HashMap<String, Team> teams = new HashMap<>(18);

public static void fillHashMap(){
	for(int i= 0; i <= 8; i++)
	{
		Team t1 = new Team(table[0][i].getT1(),0, 34*3);
		teams.put(table[0][i].getT1(), t1);
		Team t2 = new Team(table[0][i].getT2(),0, 34*3);
		teams.put(table[0][i].getT2(), t2);
	}
	System.out.println("");
}

public static void fill_Table(){
	int k=0; 
	for(int i = 0; i <= 33; i++)
	{
		for(int j = 0; j <= 8; j++){
		String[] matches = files.read(k);
		Match m = new Match(matches[1], matches[2], matches[3]);
		table[i][j] = m;
		k++;
		}
	}	
}


public static void main(String args[]) throws IOException{
fill_Table();
fillHashMap();
System.out.println(teams.get("Hamburger SV").getCurrent_points());
/**
for(int i = 0; i <= 33; i++){
	 for(int j = 0; j <= 8; j++){
			System.out.println(table[0][j].getT1() + " " + table[0][j].getT2() + " " + table[0][j].getResult());
	 }
}
*/

for(int i = 0; i <= 33; i++){
	 for(int j = 0; j <= 8; j++){
		 if(table[i][j].getResult().equals("?")){break;}
		 
		 if(table[i][j].getResult().equals("S")){
			teams.get(table[i][j].getT1()).setCurrent_points(teams.get(table[i][j].getT1()).getCurrent_points() + 3);
		 }else if(table[i][j].getResult().equals("N"))
		 	{
			teams.get(table[i][j].getT2()).setCurrent_points(teams.get(table[i][j].getT2()).getCurrent_points() + 3);
		 	}else
		 	{
		 	teams.get(table[i][j].getT2()).setCurrent_points(teams.get(table[i][j].getT2()).getCurrent_points() + 1);
		 	teams.get(table[i][j].getT1()).setCurrent_points(teams.get(table[i][j].getT1()).getCurrent_points() + 1);
		 	}	 
	 }
	}
int k = 1;
for(Team t: teams.values())
{
	System.out.println(t.getTeam_Name() + " " + t.getCurrent_points()+ " " + k);
	k++;
}
}
}

