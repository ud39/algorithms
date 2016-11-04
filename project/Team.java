package project;

import java.util.Set;

public class Team {
	
	private String team_Name; 
	private int current_points;
	private int reachable_point;
	private Set<Integer> opponents;

	public Team(String name, int cur, int rea, Set opp)
	{
		team_Name = name;
		current_points = cur;
		reachable_point = rea;
		opponents = opp;
	}

	public String getTeam_Name() {
		return team_Name;
	}

	public void setTeam_Name(String team_Name) {
		this.team_Name = team_Name;
	}

	public int getCurrent_points() {
		return current_points;
	}

	public void setCurrent_points(int current_points) {
		this.current_points = current_points;
	}

	public int getReachable_point() {
		return reachable_point;
	}

	public void setReachable_point(int reachable_point) {
		this.reachable_point = reachable_point;
	}

	public Set<Integer> getOpponents() {
		return opponents;
	}

	public void setOpponents(Set<Integer> opponents) {
		this.opponents = opponents;
	}
	
}
