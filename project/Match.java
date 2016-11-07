package test;

public class Match {
	private String t1;
	private String t2;
	private String result;

	public Match(String t_1,String t_2, String res)
	{
	this.t1 = t_1;
	this.t2 = t_2;
	this.result = res;
	}

	public String getT1() {
		return t1;
	}

	public void setT1(String t1) {
		this.t1 = t1;
	}

	public String getT2() {
		return t2;
	}

	public void setT2(String t2) {
		this.t2 = t2;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
