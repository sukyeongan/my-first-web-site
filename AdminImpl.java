package project1;

import java.util.ArrayList;
import java.util.List;

public class AdminImpl implements Admin {
	List<CheckVO> list=new ArrayList<CheckVO>();
	
	@Override
	public List<CheckVO> checkList() { // 전체 리스트 반환
		return list;
	}

	@Override
	public void addStd(CheckVO vo) { // 학생 데이터 추가
		list.add(vo);
	}

	@Override
	public boolean deletebyNum(String num) { // 학생 데이터 삭제
		CheckVO vo = findByNum(num);
		
		boolean b = list.remove(vo);
		return b;
	}

	@Override
	public CheckVO findByNum(String num) { // 번호로 찾기
		for(CheckVO vo : list) {
			if (vo.getNum().equals(num)) {
				return vo;
			}
		}
		return null;
	}
}
