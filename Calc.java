package project1;

import java.time.LocalTime;

public interface Calc {
	public int calculate(LocalTime st, LocalTime ed); // 시간 계산
	public void isLate(LocalTime time); // 지각 판정
	public void isEarly(LocalTime time); // 조퇴 판정
	public void setState(CheckVO vo); // 출결상태 입력
	public LocalTime StringToTime(String time); // 문자열을 LocalTime으로 변환
	
}
