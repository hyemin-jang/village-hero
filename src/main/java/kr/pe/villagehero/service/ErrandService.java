package kr.pe.villagehero.service;

import java.util.ArrayList;
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
	
	public List<ErrandDTO> getAllErrands(){
		List<ErrandDTO> errandList = new ArrayList<ErrandDTO>();
		List<Errand> errands = (List<Errand>) dao.findAll();
		
		errands.forEach(v -> errandList.add(new ErrandDTO(v)));
		
		return errandList;
	}

}
