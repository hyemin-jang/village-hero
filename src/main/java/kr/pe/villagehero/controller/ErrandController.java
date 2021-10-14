package kr.pe.villagehero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import kr.pe.villagehero.dto.ErrandDTO;
import kr.pe.villagehero.dto.MemberDTO;
import kr.pe.villagehero.dto.MemberDTO.Get;
import kr.pe.villagehero.service.ErrandService;

@RestController
@SessionAttributes({ "loginMember" })
public class ErrandController {

	@Autowired
	private ErrandService service;

	// 심부름 등록 
	@PostMapping("errand")
	public RedirectView insertErrand(Model model, ErrandDTO newErrand) {
		model.getAttribute("loginMember");
		MemberDTO.Get loginMember = (Get) model.getAttribute("loginMember");
		long id = loginMember.getMemberId();

		service.insertErrand(id, newErrand);
		return new RedirectView("/errandBoard/list.html");
	}

	// 모든 심부름 조회

//	@GetMapping("getErrandDetail/{id}")
//	public RedirectView getNewErrand(@PathVariable long id, Model model) {
//		
//
//		return new RedirectView("/errandBoard/detail.html");
//	}

	@GetMapping("getErrandDetail/{id}")
	public RedirectView getNewErrand(@PathVariable long id, RedirectAttributes attr) {
		attr.addAttribute("errandId", id);
		return new RedirectView("/errandBoard/detail.html");		
	}

	@GetMapping("/errandDetail")
	public ErrandDTO errandDetail(long errandId) {
		System.out.println(errandId);
		ErrandDTO errand = service.getOneErrand(errandId);
		return errand;
	}

	// json객체 배열로 errand 테이블의 모든 값
	@GetMapping("errands")
	public List<ErrandDTO> getAllErrands() {
		return service.getAllErrands();
	}

	// 심부름 1개 조회
	@GetMapping("errand")
	public ErrandDTO getOneErrand(long id) {
		return service.getOneErrand(id);
	}

	// 모든 심부름 내림차순 정렬 조회
	@GetMapping("payerrands")
	public List<ErrandDTO> getAllErrandsPayDesc() {
		List<ErrandDTO> all = service.getAllErrandsPayDes();

		return all;
	}
	
}