package kr.pe.villagehero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

import kr.pe.villagehero.dto.ErrandDTO;
import kr.pe.villagehero.dto.MemberDTO;
import kr.pe.villagehero.dto.MemberDTO.Get;
import kr.pe.villagehero.service.ErrandService;

@RestController
@SessionAttributes({"loginMember"})
public class ErrandController {

	@Autowired
	private ErrandService service;

	@PostMapping("errand")
	public RedirectView insertErrand(Model model, ErrandDTO newErrand) {
		MemberDTO.Get loginMember = (Get) model.getAttribute("loginMember");
		long id = loginMember.getMemberId();
		
		service.insertErrand(id, newErrand);
		
		return new RedirectView("/errandBoard/detail.html");
	}
	
	@GetMapping("errandDetail")
	public ErrandDTO getNewErrand(long id) {
		return service.getOneErrand(id);
	}

	// json객체 배열로 errand 테이블의 모든 값
	@GetMapping("errands")
	public List<ErrandDTO> getAllErrands() {
		return service.getAllErrands();
	}

	@GetMapping("errand")
	public ErrandDTO getOneErrand(long id) {
		return service.getOneErrand(id);
	}

	@GetMapping("payerrands")
	public List<ErrandDTO> getAllErrandsPayDesc() {
		List<ErrandDTO> all = service.getAllErrandsPayDes();

		return all;
	}

}