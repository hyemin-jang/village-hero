package kr.pe.villagehero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.pe.villagehero.dto.ErrandDTO;
import kr.pe.villagehero.service.ErrandService;

@RestController
public class ErrandController {
	
	@Autowired
	private ErrandService service;
	
	@GetMapping("errands")
	public ModelAndView getAllErrands(){
		ModelAndView mv = new ModelAndView();
		List<ErrandDTO> errandList = service.getAllErrands();
		
		mv.setViewName("errands");
		mv.addObject("errands", errandList);
		
		return mv;		
	}
	
}
