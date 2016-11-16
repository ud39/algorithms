package project;

import java.util.Collection;
import java.util.HashMap;

import java.util.HashSet;
import java.util.Set;


public class check_1to5 {

	private static HashMap<String, Team> teams = ChampionShip_Test.teams;
	private static Collection<String> dic = teams.keySet();
	static Set<String> loser = new HashSet<String>();
	static Set<String> underdog = new HashSet<String>();
	static Set<String >win = new HashSet<String>();

	static Match[][] table = ChampionShip_Test.table;
	static int day = ChampionShip_Test.day;

	public check_1to5(HashMap<String, Team> teams, Set<String> set, String fav, Match[][] matches) {
		this.teams = teams;
		this.dic = set;
		this.dic.remove(fav);
		this.table = matches;
	}

	public static boolean check_Rule1(String fav) {
		int p_Max = teams.get(fav).getCurrent_points() + ((33-(day)) * 3);
		for (String s : dic) {
			if (p_Max < teams.get(s).getCurrent_points()) {
				return false;
			}
		}
		setWins(fav);
		return true;

	}

	public static boolean check_Rule2(String fav) {
		
		int p_Max = teams.get(fav).getCurrent_points();
		for (String s : dic) {
			if (p_Max > teams.get(s).getReachable_point()) {
				underdog.add(s);
			}
		}

		for (String s : underdog) {
			setWins(s);
		}
		loser.addAll(underdog);
		return true;
	}

	public static boolean check_Rule3(String fav) {
		

		int p_Max = teams.get(fav).getReachable_point();

		for (String s : dic) {

			if (p_Max == teams.get(s).getCurrent_points()) {
				win.add(s);
			}
		}

		// �berpr�fe, ob 2 Teams (die verlieren m�ssen) gegeneinander spielen
		for (int i = day; i < 34; i++) {
			for (int j = 0; j < 9; j++) {

				if (win.contains(table[i][j].getT1()) && win.contains(table[i][j].getT2())
						|| win.contains(table[i][j].getT2()) && win.contains(table[i][j].getT1())) {
					return false;
				}
			}
		}

		for (String s: win){
			setLosses(s);
		}
		loser.addAll(win);
		return true;
	}

	// Menge win: Mannschaft muss alle Spiele verlieren! -> Teste ob, draw
	// m�glich
	public static boolean check_Rule4(String fav) {

		Set<String> draw = new HashSet<String>();
		int p_Max = teams.get(fav).getReachable_point();

		for (String s : dic) {

			if (p_Max < teams.get(s).getCurrent_points() + 3 && teams.get(s).getReachable_point() - 2 * day <= p_Max) {
				draw.add(s);
			}
		}
		
		for (int i = day; i < 34; i++) {
			for (int j = 0; j < 9; j++) {

				if (win.contains(table[i][j].getT1()) && draw.contains(table[i][j].getT2())
						|| win.contains(table[i][j].getT2()) && draw.contains(table[i][j].getT1())) {
					return false;
				}

			}
		}
		
		for (String s: draw){
			setDraws(s);
		}
		
		loser.addAll(draw);
		return true;
	}

	public static boolean check_Rule5(String fav) {

		if (check_Rule1(fav) == true && check_Rule2(fav) == true && check_Rule3(fav) == true
				&& check_Rule4(fav) == true) {
			return true;
		}
		return false;
	}
	
	public static void set_Rest()
	{
		dic.removeAll(loser);
		
		for (String s: dic){
			setDraws(s);
		}
		
		
	}
	public static void setWins(String t) {

		for (int i = day; i <= 33; i++) {
			for (int j = 0; j <= 8; j++) {

				if (table[i][j].getResult().equals("?")) {

					if (table[i][j].getT1().equals(t)) {
						table[i][j].setResult("S");
						teams.get(table[i][j].getT1())
						.setCurrent_Point_Reachable_Point(teams.get(table[i][j].getT1()).getCurrent_points() + 3 , teams.get(table[i][j].getT1()).getReachable_point() - 3);
						teams.get(table[i][j].getT2()).setReachable_point(teams.get(table[i][j].getT2()).getReachable_point()-3);
						break;
					}
					if (table[i][j].getT2().equals(t)) {
						table[i][j].setResult("N");
						teams.get(table[i][j].getT2())
						.setCurrent_Point_Reachable_Point(teams.get(table[i][j].getT1()).getCurrent_points() + 3 , teams.get(table[i][j].getT1()).getReachable_point() - 3 );
						teams.get(table[i][j].getT2()).setReachable_point(teams.get(table[i][j].getT2()).getReachable_point()-3);
						break;
					}
				}
			}
		}

	}

	public static void setLosses(String t) {
		for (int i = day; i <= 33; i++) {
			for (int j = 0; j <= 8; j++) {

				if (table[i][j].getResult().equals("?")) {

					if (table[i][j].getT1().equals(t)) {
						table[i][j].setResult("N");
						teams.get(table[i][j].getT1())
						.setCurrent_Point_Reachable_Point(teams.get(table[i][j].getT2()).getCurrent_points() + 3 , teams.get(table[i][j].getT1()).getReachable_point() - 3);
						teams.get(table[i][j].getT2()).setReachable_point(teams.get(table[i][j].getT2()).getReachable_point()-3);
						break;
					}
					if (table[i][j].getT2().equals(t)) {
						table[i][j].setResult("S");
						teams.get(table[i][j].getT2())
						.setCurrent_Point_Reachable_Point(teams.get(table[i][j].getT1()).getCurrent_points() + 3 , teams.get(table[i][j].getT1()).getReachable_point() - 3);
						teams.get(table[i][j].getT2()).setReachable_point(teams.get(table[i][j].getT2()).getReachable_point()-3);
						break;
					}
				}
			}
		}
	}
	
	public static void setDraws(String t) {
		for (int i = day; i <= 33; i++) {
			for (int j = 0; j <= 8; j++) {

				if (table[i][j].getResult().equals("?")) {

					if (table[i][j].getT1().equals(t) || table[i][j].getT2().equals(t)) {
						table[i][j].setResult("U");
						teams.get(table[i][j].getT1())
						.setCurrent_Point_Reachable_Point(teams.get(table[i][j].getT1()).getCurrent_points() + 1 , teams.get(table[i][j].getT1()).getReachable_point() - 2);
						teams.get(table[i][j].getT2()).setCurrent_Point_Reachable_Point(teams.get(table[i][j].getT2()).getCurrent_points() + 1 , teams.get(table[i][j].getT2()).getReachable_point() - 2);
						break;
					}
					
				}
			}
		}
	}
	
	
}
