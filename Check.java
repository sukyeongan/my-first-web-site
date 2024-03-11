package project1;

public interface Check { // 추가할 메소드 
	public void setCheckin(String time, CheckVO vo);
	public void setCheckout(String time, CheckVO vo);
	public boolean isValid(String time);
}
