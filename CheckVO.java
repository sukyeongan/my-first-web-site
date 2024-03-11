package project1;

public class CheckVO implements Comparable<CheckVO>{ 
	private String num;  
	private String name;
	private String in; // 입실
	private String out; // 퇴실
	private String check; // 출결상태
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIn() {
		return in;
	}
	public void setIn(String in) {
		this.in = in;
	}
	public String getOut() {
		return out;
	}
	public void setOut(String out) {
		this.out = out;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	@Override
	public int compareTo(CheckVO o) {
		return num.compareTo(o.getNum());
	}
}
