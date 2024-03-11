package project1;

public class CheckImpl implements Check {	
	String out="17:00";
	
	@Override
	public void setCheckin(String time, CheckVO vo) {
		if(!isValid(time)) {
			System.out.println("잘못된 입력입니다.");
			return;
		}
		else if(time.compareTo(out)>=0) { // 17시 이후엔 입실할 수 없음
			System.out.println("17시 이후엔 입실 할 수 없습니다.");
			return;
		}
		vo.setIn(time);
		System.out.println("입실 완료");
	}

	@Override
	public void setCheckout(String time, CheckVO vo) {
		if(vo.getIn()==null) { // 입실하지 않은 경우 퇴실할 수 없음
			System.out.println("입실 하지 않았습니다.");
			return;
		}
		else if(!isValid(time)) {
			System.out.println("잘못된 입력입니다.");
			return;
		}
		else if(vo.getIn().compareTo(time)>0) { // 입실시간 이전에 퇴실할 수 없음
			System.out.println("입실 이전에 퇴실이 불가능합니다.");
			return;
		}
		vo.setOut(time);
		System.out.println("퇴실 완료");		
	}
	
	@Override
	public boolean isValid(String time) { // 올바른 입력인지 확인
		try {
		String s=time.replaceAll("[^0-9|:]", "");
		String[] ss=time.split(":");
		
		if(Integer.parseInt(ss[0])>24||Integer.parseInt(ss[1])>=60) { // 시간이 24시를 넘거나, 분이 60분 이상이면 잘못된 입력
			return false;		
		} else if(Integer.parseInt(ss[0])==24&&Integer.parseInt(ss[1])!=00){ // 24시인 경우, 00분만 허용
			return false;
		}
		
		if(s.equals(time)&&ss[0].length()==2&&ss[1].length()==2) {
			return true;
		}
		
		} catch (NumberFormatException e) { // 숫자가 아닌 경우
			
		} catch (ArrayIndexOutOfBoundsException e) { // : 기준 split으로 시간/분을 나누는데 09:처럼 입력하면 ss[1]이 없으니 에러남
			
		}
		
		return false;
	}
}
