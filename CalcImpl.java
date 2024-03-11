package project1;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class CalcImpl implements Calc {
	LocalTime start = LocalTime.of(9,0); // 입실 시간 09시 00분
	LocalTime end = LocalTime.of(17,0); // 퇴실 시간 17시 00분
	boolean late = false;
	boolean leave = false;
	
	public void resetTime() {
		start = LocalTime.of(9,0);
		end = LocalTime.of(17,0);
		late = false;
		leave = false;
	}
	
	@Override
	public int calculate(LocalTime st, LocalTime ed) {
		long hour=ChronoUnit.HOURS.between(st, ed);
		return (int) hour;
	}

	@Override
	public void isLate(LocalTime time) {
		if(start.isBefore(time)) {
			late=true;
			start=time;
		}
	}

	@Override
	public void isEarly(LocalTime time) {
		if(time.isBefore(end)) {
			leave=true;
			end=time;
		}
	}

	@Override
	public void setState(CheckVO vo) {
		if(vo.getIn()==null) {
			vo.setCheck("결석");
			return;
		} else if(vo.getOut()==null) {
			vo.setCheck("결석");
			return;
		}
		LocalTime checkintime = StringToTime(vo.getIn());
		LocalTime checkouttime = StringToTime(vo.getOut());

		isLate(checkintime);
		isEarly(checkouttime);
		
		int n=calculate(start, end);
		
		if(n<4) {
			vo.setCheck("결석");
		} else if(n<8&&n>=4) {
			if(late&&leave) {
				vo.setCheck("지각 및 조퇴");
			} else if(late) {
				vo.setCheck("지각");
			} else {
				vo.setCheck("조퇴");
			}
		} else {
			vo.setCheck("출석");
		}	
		resetTime();
		return;
	}
	@Override
	public LocalTime StringToTime(String time) {
		try {
			DateTimeFormatter fmt=DateTimeFormatter.ofPattern("HH:mm");
			LocalTime result=LocalTime.parse(time, fmt);
			return result;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
