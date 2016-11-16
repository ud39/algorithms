package project;

import java.io.IOException;
import java.util.HashMap;


import test.File_Data;

public class ChampionShip_Test {
	static Match[][] table = new Match[34][9];
	static int current_day = 0;
	static File_Data files = new File_Data();
	static HashMap<String, Team> teams = new HashMap<>(18);
	static final int DAYS = 34;
	public static final int day = 0;
	
	public static void fillHashMap() {
		for (int i = 0; i <= 8; i++) {
			Team t1 = new Team(table[0][i].getT1(), 0, 34 * 3);
			teams.put(table[0][i].getT1(), t1);
			Team t2 = new Team(table[0][i].getT2(), 0, 34 * 3);
			teams.put(table[0][i].getT2(), t2);
		}
		System.out.println("");
	}

	public static void fill_Table() {
		int k = 0;
		for (int i = 0; i <= 33; i++) {
			for (int j = 0; j <= 8; j++) {
				String[] matches = files.read(k);
				Match m = new Match(matches[1], matches[2], matches[3]);
				table[i][j] = m;
				k++;
			}
		}
	}

	public static void initScores() {
		for (int i = 0; i <= 33; i++) {
			for (int j = 0; j <= 8; j++) {
				if (table[i][j].getResult().equals("?")) {
				
					break;
				}

				if (table[i][j].getResult().equals("S")) {
					teams.get(table[i][j].getT1())
							.setCurrent_points(teams.get(table[i][j].getT1()).getCurrent_points() + 3);
				} else if (table[i][j].getResult().equals("N")) {
					teams.get(table[i][j].getT2())
							.setCurrent_points(teams.get(table[i][j].getT2()).getCurrent_points() + 3);
				} else {
					teams.get(table[i][j].getT2())
							.setCurrent_points(teams.get(table[i][j].getT2()).getCurrent_points() + 1);
					teams.get(table[i][j].getT1())
							.setCurrent_points(teams.get(table[i][j].getT1()).getCurrent_points() + 1);
				}
			}
		}
	}
	public static void set_Reachable()
	{
		for(Team t: teams.values())
		{
			t.setReachable_point((DAYS-day)*3);
		}
	}
	static Match[][] temp = table.clone();


	public static void main(String args[]) throws IOException {
		fill_Table();
		fillHashMap();
		
		/**
		 * for(int i = 0; i <= 33; i++){ for(int j = 0; j <= 8; j++){
		 * System.out.println(table[0][j].getT1() + " " + table[0][j].getT2() +
		 * " " + table[0][j].getResult()); } }
		 */

		// Ermittle Spieltag
		outerloop: for (int i = 0; i <= 33; i++) {
			for (int j = 0; j <= 8; j++) {
				if (table[i][j].getResult().equals("?")) {
					current_day = i+1;
					break outerloop;
				}}}
		
		
		initScores();
		check_1to5.check_Rule1("Hamburger SV");

		for (Team t : teams.values()) {
			System.out.println(t.getTeam_Name() + " " + t.getCurrent_points());

		}

		
		System.out.println("HSV Score:" + teams.get("Hamburger SV").getCurrent_points());
		System.out.println("Spieltag:" + current_day);
	}
}
