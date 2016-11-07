package project;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class check_1to5 {
	
	HashMap<String,Team> test = new HashMap<>(17);
	Set<String> dic = new HashSet<String>();
	
	public check_1to5(HashMap<String, Team> teams, Set<String> set, String fav)
	{
		test = teams;
		dic = set;
		dic.remove(fav);
	}
	public boolean check_Rule1to5(){
		
	return false;
		
	}

	public boolean check_Rule1(String fav){
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
	public boolean check_Rule2(String fav){
		Set<String> underdog = new HashSet<String>();
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
}
