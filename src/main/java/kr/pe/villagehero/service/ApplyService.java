package kr.pe.villagehero.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.villagehero.dao.ApplyRepository;
import kr.pe.villagehero.dto.ApplyDTO;
import kr.pe.villagehero.entity.Apply;

@Service
public class ApplyService {

	@Autowired
	private ApplyRepository dao;
	
	//존재하는 모든 지원정보들 return
	public List<ApplyDTO> getAllApplies(){
		List<Apply> all = (List<Apply>)dao.findAll();
		List<ApplyDTO> all2 = new ArrayList<ApplyDTO>();
		
		all.forEach(v -> all2.add(new ApplyDTO(v)));
		
		return all2;
	}
}
