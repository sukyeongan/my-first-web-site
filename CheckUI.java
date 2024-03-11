package project1;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CheckUI {
	public CheckUI(List<CheckVO> list) {
		this.list=list;
	}
	private Check ck=new CheckImpl();
	private List<CheckVO> list;
	private Scanner sc = new Scanner(System.in);	
	
	protected void menu() {
		int ch;
		while (true) {
			System.out.println("\n"+LocalDate.now()+" 입실 및 퇴실");
			try {
				do {
					System.out.print("1.입실 2.퇴실 3.돌아가기 => ");
					ch = sc.nextInt();
				} while (ch < 1 || ch > 3);

				if (ch == 3) {
					return;
				}
				switch (ch) {
				case 1: Checkin(); break;
				case 2: CheckOut(); break;
				}
			} catch (InputMismatchException e) { // 정수 이외를 입력받은 경우
				sc.next();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	protected void Checkin() {
		CheckVO vo=null;
		System.out.println("입실");
		try {
			System.out.print("학생 번호 입력 : ");
			String num=sc.next();
				
			for(CheckVO vo2:list) {
				if(num.equals(vo2.getNum())) {
					vo=vo2;
					break;
				}	
			}
			if(vo==null) {
				System.out.println("저장된 데이터가 없습니다");
				return;
			}
			System.out.print("입실 시간 입력 : ");
			String time=sc.next();

			ck.setCheckin(time, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void CheckOut() {
		CheckVO vo=null;
		System.out.println("퇴실");
		try {
			System.out.print("학생 번호 입력 : ");
			String num=sc.next();
			
			for(CheckVO vo2:list) {
				if(num.equals(vo2.getNum())) {
					vo=vo2;
					break;
				}	
			}
			if(vo==null) {
				System.out.println("저장된 데이터가 없습니다");
				return;
			}				
			System.out.print("퇴실 시간 입력 : ");
			String time=sc.next();
				
			ck.setCheckout(time, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}