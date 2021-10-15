package kr.pe.villagehero.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import kr.pe.villagehero.dto.ErrandDTO;
import kr.pe.villagehero.dto.MemberDTO;
import kr.pe.villagehero.dto.MemberDTO.Get;
import kr.pe.villagehero.dto.MyPageDTO;
import kr.pe.villagehero.service.ErrandService;

@RestController
public class ErrandController {

	@Autowired
	private ErrandService service;
	
	//심부름 수정
	@PutMapping("updateErrand")
	public String updateWriter(ErrandDTO.updateErrand errand) {
		System.out.println(errand);

		service.updateErrand(errand);
		
		return "성공";
	}

	// 심부름 등록 
	@PostMapping("errand")
	public RedirectView insertErrand(HttpSession session, ErrandDTO newErrand) {
		MemberDTO.Get loginMember = (Get) session.getAttribute("loginMember");
		long id = loginMember.getMemberId();
		
		service.insertErrand(id, newErrand);
		return new RedirectView("/errandBoard/list.html");
	}
	
	//심부름 삭제
	@GetMapping("errandDelete/{id}")
	public RedirectView deleteErrand(@PathVariable long id) {
		service.deleteErrand(id);

		return new RedirectView("/errandBoard/list.html");
	}

	// 모든 심부름 내역에서 상세페이지로 이동
	@GetMapping("getErrandDetail/{id}")
	public RedirectView getNewErrand(@PathVariable long id, RedirectAttributes attr) {
		attr.addAttribute("errandId", id);  // detail.html로 리다이렉트 할때 ?errandId=id 쿼리스트링을 붙인다
		return new RedirectView("/errandBoard/detail.html");		
	}

	// 심부름 상세페이지 - 1개 심부름 정보 출력
	@GetMapping("/errandDetail")
	public ErrandDTO errandDetail(long errandId) {
		ErrandDTO errand = service.getOneErrand(errandId);
		return errand;
	}
	
	//수정페이지로 이동
	@GetMapping("getErrandDetail2/{id}")
	public RedirectView getErrandId2(@PathVariable long id, RedirectAttributes attr) {
		attr.addAttribute("errandId2", id);
		return new RedirectView("/errandBoard/update.html");		
	}
	
	
	@GetMapping("/errandDetail2")
	public ErrandDTO errandDetail2(long errandId2) {
		System.out.println(errandId2);
		ErrandDTO errand = service.getOneErrand(errandId2);
		return errand;
	}

	// 모든 심부름 조회
	@GetMapping("errands")
	public List<ErrandDTO> getAllErrands() {		
		List<ErrandDTO> all = service.getAllErrands();
		return all;
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
	
	//내 심부름 목록 뿌려주기 -> 삭제버튼/count/title/status 뿌려줄 것임.
	@GetMapping("myerrands/req")
	public List<MyPageDTO.ErrandDTO2> getAllMyErrands(Long memberId){

		return service.getAllMyErrands(memberId);
	}
}