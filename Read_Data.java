package project;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Read_Data {
	public HashMap<String, Team>  team = new HashMap<>(18);
	public Set<Team> dic = new HashSet<>(17);
	private Pair players = new Pair<String>("", "");
	public Pair who_Won(String s)
	{ 
		if(s.equals("S")){
		players.setFirst(s);	
		}else if(s.equals("V")){
			
		}else{
			
		}
		
	}
	private void save_Team(String name)
	{
		team.put(name, E);
	}
 }
