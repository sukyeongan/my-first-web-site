package project1;

import java.util.List;

public interface Admin {
	public List<CheckVO> checkList(); // 전체 리스트 반환
	public void addStd(CheckVO vo); // 학생 데이터 추가
	public CheckVO findByNum(String num); // 번호로 찾기
	public boolean deletebyNum(String num); // 학생 데이터 삭제
}
