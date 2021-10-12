package kr.pe.villagehero.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import kr.pe.villagehero.dao.ErrandRepository;
import kr.pe.villagehero.dto.ErrandDTO;
import kr.pe.villagehero.entity.Errand;

@Service
public class ErrandService {

	@Autowired
	private ErrandRepository dao;
	
	//존재하는 모든 심부름을 DTO객체로 return
	public List<ErrandDTO> getAllErrands(){
		List<ErrandDTO> errandList = new ArrayList<ErrandDTO>();
		List<Errand> errands = (List<Errand>) dao.findAll();
		
		errands.forEach(v -> errandList.add(new ErrandDTO(v)));
		
		return errandList;
	}
	
	//존재하는 모든 심부름을 가격순(내림차순)으로 return
	public List<ErrandDTO> getAllErrandsPayDes(){
		List<ErrandDTO> all = new ArrayList<ErrandDTO>();
		List<Errand> all2 = (List<Errand>)dao.findAll();
		
		all2.forEach(v -> all.add(new ErrandDTO(v)));
		all.sort(new PayComparator());
		return all;
	}
	
	//현재 저장된 세션값(로그인회원id번호)으로 내가 등록한 심부름 목록 출력
	

}

class PayComparator implements Comparator<ErrandDTO>{
	@Override
	public int compare(ErrandDTO a,ErrandDTO b){
		if(a.getPay()>b.getPay()) return -1;
		if(a.getPay()<b.getPay()) return 1;
		return 0;
	}
}

