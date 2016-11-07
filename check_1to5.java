package project;

import java.util.HashMap;

import java.util.HashSet;
import java.util.Set;
import project.ChampionShip_Test;


public class check_1to5 {
	
	private static HashMap<String,Team> test = new HashMap<>(17);
	private static Set<String> dic = new HashSet<String>();
	private static Set<String> win = new HashSet<String>();
	private static Set<String> underdog = new HashSet<String>();
	static Match[][] table;
	static int day;
	
	public check_1to5(HashMap<String, Team> teams, Set<String> set, String fav,Match[][] matches)
	{
		this.test = teams;
		this.dic = set;
		this.dic.remove(fav);
		this.table = matches;
	}

	public static boolean check_Rule1(String fav){
		int p_Max = test.get(fav).getReachable_point();
		for(String s : dic) 
		{
		if(p_Max < test.get(s).getCurrent_points())
		{
			return false;
		}
		}
		return true;
		
	}
	//return boolean or set or read rule_2 again(ask Marten) 
	public static boolean check_Rule2(String fav){
		int p_Max = test.get(fav).getReachable_point();
		for(String s : dic)
		{
			if(p_Max > test.get(s).getReachable_point())
			{
			underdog.add(s);	
			}
		}
		return true;
	}
	
	
	public static boolean check_Rule3(String fav) {
		
		int p_Max = test.get(fav).getReachable_point();
		
		for (String s : dic) {
			
			if (p_Max == test.get(s).getCurrent_points()){
				win.add(s);
			}
		}
		
		for (int i = day; i< 34; i++) {
			for (int j = 0; j < 9; j++) {
				
				if(win.contains(table[i][j].getT1()) && win.contains(table[i][j].getT2())
						|| win.contains(table[i][j].getT2()) && win.contains(table[i][j].getT1())){
					return false;
				}
				
			}
		}
		
		return true;
	}
	
	
	// Menge win: Mannschaft muss alle Spiele verlieren! -> Teste ob, draw möglich
	public static boolean check_Rule4(String fav) {
		
		Set<String> draw = new HashSet<String>();
		int p_Max = test.get(fav).getReachable_point();
		
		for (String s : dic) {
			
			if (p_Max < test.get(s).getCurrent_points() + 3 && test.get(s).getReachable_point() - 2 * day <= p_Max){
				draw.add(s);
			}
		}
		
		
		// Überprüfe, ob Mannschaft (die höchtens unentschieden spielen darf) gegen Mannschaft (die verlieren muss) antritt
		
		
		for (int i = day; i< 34; i++) {
			for (int j = 0; j < 9; j++) {
				
				if(win.contains(table[i][j].getT1()) && draw.contains(table[i][j].getT2())
						|| win.contains(table[i][j].getT2()) && draw.contains(table[i][j].getT1())){
					return false;
				}
				
			}
		}
		return true;
	}
	
	
	public static boolean check_Rule5(String fav){

		
		if (check_Rule1(fav) == true && check_Rule2(fav) == true && check_Rule3(fav) == true 
				&& check_Rule4(fav) == true ) {
			return true;
		}
		return false;
	}
	
	
	
	
}
