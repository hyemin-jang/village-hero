package kr.pe.villagehero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

import kr.pe.villagehero.dto.MemberDTO;
import kr.pe.villagehero.service.MemberService;

@RestController
@SessionAttributes({"loginMember"})
public class MemberController {
	
	@Autowired
	private MemberService service;
		
	@PostMapping("login")
	public RedirectView logIn(Model model, MemberDTO.Login loginData) {
		
		MemberDTO.Get loginMember = service.logIn(loginData.getEmail());  
		System.out.println("controller - " + loginMember.getNickname());
		
		model.addAttribute("loginMember", loginMember);		// 세션에 현재 로그인한 회원의 정보 저장
		
//		return "redirect:mypage.html";  ?????
		return new RedirectView("/mypage.html");		
		
	}
	
	
	// 세션에 담긴 로그인한 회원의 정보 반환하는 메소드
	@GetMapping("logincheck")
	public Object getLogInSession(Model model) {
		return model.getAttribute("loginMember");
	}
	
}
