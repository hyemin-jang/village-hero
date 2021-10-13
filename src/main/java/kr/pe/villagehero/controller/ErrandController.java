package kr.pe.villagehero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.villagehero.dto.ErrandDTO;
import kr.pe.villagehero.entity.Errand;
import kr.pe.villagehero.service.ErrandService;


@RestController
public class ErrandController {

	@Autowired
	private ErrandService service;	

	@PostMapping("errand")
    public Errand insertErrand(Errand errand) {
    
        return service.insertErrand(errand);
	}

	//json객체 배열로 errand 테이블의 모든 값
	@GetMapping("errands")
	public List<ErrandDTO> getAllErrands() {
		return service.getAllErrands();		
	}
	
	@GetMapping("errand")
	public ErrandDTO getOneErrand(long id) {
		return service.getOneErrand(id);
	}
	
	@GetMapping("payerrands")
	public List<ErrandDTO> getAllErrandsPayDesc(){
		List<ErrandDTO> all = service.getAllErrandsPayDes();
		
		return all;
	}

}

