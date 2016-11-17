package project;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;



public class ChampionShip_Test {
	private Match[][] table = new Match[34][9];
	private int current_day = 0;
	private File_Data files = new File_Data();
	private HashMap<String, Team> teams = new HashMap<>(18);
	static final int DAYS = 34;
	
	public ChampionShip_Test(Integer year, Integer day)
	{
		setCurrent_day(day);
		fill_Table(year, day);
		fillHashMap();
		initScores();
	}
	public void fillHashMap() {
		for (int i = 0; i <= 8; i++) {
			Team t1 = new Team(table[0][i].getT1(), 0, 34 * 3);
			getTeams().put(table[0][i].getT1(), t1);
			Team t2 = new Team(table[0][i].getT2(), 0, 34 * 3);
			getTeams().put(table[0][i].getT2(), t2);
		}
		System.out.println("");
	}

	public void fill_Table(Integer year, Integer day) {
		int k = 0;
		for (int i = 0; i <= 33; i++) {
			for (int j = 0; j <= 8; j++) {
				String[] matches = files.read(k,year,day);
				Match m = new Match(matches[1], matches[2], matches[3]);
				table[i][j] = m;
				k++;
			}
		}
	}

	public void initScores() {
		int k = 0;
		for (int i = 0; i <= 33; i++) {
			for (int j = 0; j <= 8; j++) {
	

				if (table[i][j].getResult().equals("S")) {
					getTeams().get(table[i][j].getT1())
							.setCurrent_Point_Reachable_Point(getTeams().get(table[i][j].getT1()).getCurrent_points() + 3, getTeams().get(table[i][j].getT1()).getReachable_point() );
					getTeams().get(table[i][j].getT2())
					.setCurrent_Point_Reachable_Point(getTeams().get(table[i][j].getT2()).getCurrent_points() +  0 , getTeams().get(table[i][j].getT2()).getReachable_point() - 3 );
				}if (table[i][j].getResult().equals("N")) {
					getTeams().get(table[i][j].getT2())
					.setCurrent_Point_Reachable_Point(getTeams().get(table[i][j].getT2()).getCurrent_points() + 3, getTeams().get(table[i][j].getT2()).getReachable_point() );
					getTeams().get(table[i][j].getT1())
					.setCurrent_Point_Reachable_Point(getTeams().get(table[i][j].getT1()).getCurrent_points() + 0, getTeams().get(table[i][j].getT1()).getReachable_point() - 3 );
				}if(table[i][j].getResult().equals("U")){
					getTeams().get(table[i][j].getT2())
							.setCurrent_points(getTeams().get(table[i][j].getT2()).getCurrent_points() + 1);
					getTeams().get(table[i][j].getT2())
					.setReachable_point(getTeams().get(table[i][j].getT2()).getReachable_point() - 2);
					getTeams().get(table[i][j].getT1())
							.setCurrent_points(getTeams().get(table[i][j].getT1()).getCurrent_points() + 1);
					getTeams().get(table[i][j].getT1())
					.setReachable_point(getTeams().get(table[i][j].getT1()).getReachable_point() -2 );
				}else if(table[i][j].getResult().equals("?")){
				}
			}
		}
	}
	public void set_Reachable()
	{
		for(Team t: getTeams().values())
		{
			t.setReachable_point((DAYS-getCurrent_day())*3);
		}
	}

	public HashMap<String, Team> getTeams() {
		return teams;
	}

	public void setTeams(HashMap<String, Team> teams) {
		this.teams = teams;
	}

	public Match[][] getTable() {
		return table;
	}

	public void setTable(Match[][] table) {
		this.table = table;
	}
	public void print_Table()
	{
	for(int i = 0; i <= 33; i++)
	{
		for(int j = 0; j <= 8; j++)
		{
		System.out.println(table[i][j].getT1() + " " + table[i][j].getT2() + " " + table[i][j].getResult());
		}
		System.out.println();
	}
	}
	public void print_Points()
	{
	for(Team t : teams.values())
		{
		System.out.println(t.getTeam_Name() + " " + t.getCurrent_points() + " " + t.getReachable_point());
		}
	}
	
	
	
	public int getCurrent_day() {
		return current_day;
	}
	public void setCurrent_day(int current_day) {
		this.current_day = current_day;
	}
	public static void main(String args[]) throws IOException {
		Set<String> dic = new HashSet<>(); 
		ChampionShip_Test champ = new ChampionShip_Test(2006, 19);
		//ChampionShip_Test champ2 = new ChampionShip_Test(2006, 32);	
		for(Team t : champ.getTeams().values())
		{
			dic.add(t.getTeam_Name());
		}
		check_1to5 checky = new check_1to5(champ.getTeams(),dic,"VfL Wolfsburg",champ.getTable(), champ.getCurrent_day());
		System.out.println("\n");

		//check_1to5 checky2 = new check_1to5(champ2.getTeams(),dic,"Bayern München",champ2.getTable(), champ2.getCurrent_day());
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\n");
		//champ2.print_Points();
		//System.out.println(checky2.check_Rule1("Energie Cottbus"));
		/**
		 * for(int i = 0; i <= 33; i++){ for(int j = 0; j <= 8; j++){
		 * System.out.println(table[0][j].getT1() + " " + table[0][j].getT2()
		 * " " + table[0][j].getResult()); } }
		 */
		//Tests
		//-----------------------------------------------------------------------------------------------------------------------------
		/**
		for(Team t : champ.getTeams().values())
		{
			if(checky2.check_Rule1(t.getTeam_Name()))
			{
			System.out.println(t.getTeam_Name());	
			}
		}
		*/
	
		 for(int i = 0; i <= 33; i++){ for(int j = 0; j <= 8; j++){
		 System.out.println(champ.getTable()[i][j].getT1() + " " + champ.getTable()[i][j].getT2()
		  + " " + champ.getTable()[i][j].getResult()); } 
		 System.out.println(i);
		 }
		 System.out.println("------------------------------------------------------------------------------------------------------------");
		 checky.check_Rule1("VfL Wolfsburg");
		 System.out.println("------------------------------------------------------------------------------------------------------------");
		 for(int i = 0; i <= 33; i++){ for(int j = 0; j <= 8; j++){
		 System.out.println(champ.getTable()[i][j].getT1() + " " + champ.getTable()[i][j].getT2()
		  + " " + champ.getTable()[i][j].getResult()); } 
		 System.out.println(i);
		 }
		 System.out.println("------------------------------------------------------------------------------------------------------------");
		 champ.print_Points();
		 System.out.println("------------------------------------------------------------------------------------------------------------");
		 checky.check_Rule2("VfL Wolfsburg");
		 
		 for(int i = 0; i <= 33; i++){ for(int j = 0; j <= 8; j++){
			 System.out.println(champ.getTable()[i][j].getT1() + " " + champ.getTable()[i][j].getT2()
			  + " " + champ.getTable()[i][j].getResult()); } 
			 System.out.println(i);
			 }
		 
		 
	}	
	
	
}
