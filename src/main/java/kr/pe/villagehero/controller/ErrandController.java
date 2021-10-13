package kr.pe.villagehero.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import kr.pe.villagehero.dto.ErrandDTO;
import kr.pe.villagehero.entity.Errand;
import kr.pe.villagehero.service.ErrandService;


@RestController
//@RequestMapping("sessiontracking")  ?? 지워도되나여??
public class ErrandController {

	@Autowired
	private ErrandService service;	

	@PostMapping("errand")
    public Errand insertErrand(Errand errand) {
    
        
        return service.insertErrand(errand);
	}
	
//	@GetMapping("errandDetail")
//	public ErrandDTO redirectDetail(long id) {
//		return service.getOneErrand(id);
//	}
	
	//axios로 값 전달
	//


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
	public List<ErrandDTO> getAllErrandsPayDes(){
		List<ErrandDTO> all = service.getAllErrandsPayDes();
		
		return all;
	}
	
	@RequestMapping("/sessionTest2")
	public String m2(HttpSession session) {
		//1. 로그인폼에서 입력받은 id와 password를 db내에서 비교
		//2. db내에 존재한다면 로그인성공 -> 해당 인스턴스의 고유 회원 id 값을 추출.
		//3. 추출한 회원 id값을 session으로 저장.
		//4. session값으로 내가 등록한 심부름/내가 지원한 심부름 목록 불러오기 가능.
		session.setAttribute("id", "dd");
		System.out.println("m2() -- " + session.getAttribute("id"));
		return "redirect:/session.jsp";
	}

}

