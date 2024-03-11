package project1;

import java.time.LocalDate;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AdminUI {
	private Scanner sc = new Scanner(System.in);
	private Admin admin = new AdminImpl();
	private Calc cc=new CalcImpl();
	
	public void mainMenu() {
			int ch;
			while (true) {
				try {
					do {
						System.out.print("1.학생 2.관리자 3.종료 => ");
						ch = sc.nextInt();
					} while (ch < 1 || ch > 3);

					if (ch == 3) {
						System.exit(0);
					}
					switch (ch) {
					case 1:
						new CheckUI(admin.checkList()).menu(); break;
					case 2:
						menu(); break;
					}
				} catch (InputMismatchException e) { // 정수 이외를 입력받은 경우
					sc.next();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	public void menu() {
		System.out.println("\n관리자 메뉴");
		int ch;
		while(true) {
			try {
				do {
					System.out.print("1.학생정보 추가 2.학생정보 삭제 3.학생정보 수정 4.학생출결 전체리스트 5.돌아가기 => ");
					ch = sc.nextInt();
				}while(ch<1||ch>5);
				
				if(ch==5) {
					mainMenu();
				}
				switch(ch) {
				case 1 : insert();break;
				case 2 : delete() ;break;
				case 3 : update(); break;
				case 4 : printList(); break;
				}
			} catch (InputMismatchException e) { // 정수 이외를 입력받은 경우
				sc.next();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void insert() {
		System.out.println("\n학생 정보 등록");
		CheckVO vo = new CheckVO();
		
		try {
			System.out.print("학생번호 : ");
			String num=sc.next();
			System.out.print("이름 : ");
			String name=sc.next();
			
			if(admin.findByNum(num)!=null) {
				System.out.println("이미 등록된 번호입니다.");
				return;
			}
			vo.setNum(num);
			vo.setName(name);
			admin.addStd(vo);
			System.out.println("등록 완료\n");
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	private void delete() {
		System.out.println("\n학생 정보 삭제");
		String num;
		
		try {
			System.out.print("학생 번호 입력 : ");
			num = sc.next();
			
			boolean b = admin.deletebyNum(num);
			
			if(b) {
				System.out.println("삭제 완료");
			} else {
				System.out.println("저장된 데이터가 없습니다");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void update() {
		System.out.println("\n수정");
		String num;
		
		try {
			System.out.println("수정할 학생 번호");
			num = sc.next();
			
			CheckVO vo = admin.findByNum(num);
			
			if(vo == null) {
				System.out.println("저장된 데이터가 없습니다");
				return;
			}
			System.out.print(vo.getName()+" -> 어떤 이름으로 수정 ? ");
			vo.setName(sc.next());
			System.out.println("수정된 이름 : "+vo.getName());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void printList() {
		System.out.println("\n"+LocalDate.now()+" 학생 정보 출력"); // 현재 시스템 시간
		List<CheckVO> list = admin.checkList();
		Collections.sort(list);
		System.out.println("번호\t이름\t입실\t퇴실\t출결");
		for(CheckVO vo : list) {
			cc.setState(vo);
			print(vo);
		}
		System.out.println();
	}
	private void print(CheckVO vo) {
		System.out.print(vo.getNum()+"\t");
		System.out.print(vo.getName()+"\t");
		System.out.print(vo.getIn()==null?"미입력\t":vo.getIn()+"\t"); // 입실/퇴실 시간 null이면 미입력으로 출력
		System.out.print(vo.getOut()==null?"미입력\t":vo.getOut()+"\t");
		System.out.println(vo.getCheck());
	}
}
