package kr.pe.villagehero.controller;

import java.util.List;

import javax.persistence.metamodel.StaticMetamodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import kr.pe.villagehero.dto.ErrandDTO;
import kr.pe.villagehero.service.ErrandService;


@RestController
public class ErrandController {

	@Autowired
	private ErrandService service;	

	@PostMapping("errand")
    public RedirectView insertErrand(ErrandDTO newErrand) {
		service.insertErrand(newErrand);
	
		return new RedirectView("/errandBoard/detail.html");
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