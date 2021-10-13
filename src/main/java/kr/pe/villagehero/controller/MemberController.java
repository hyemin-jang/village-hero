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
@SessionAttributes({"loginMember"})  //loginMember 라는 이름으로 서버 메모리에 client 정보 저장하겠다는 설정 
public class MemberController {
	
	@Autowired
	private MemberService service;
		
	/* /http://ip:port/login  요청하면서 id/pw값이 전송이 되면
	 * MemberDTO.Login   객체가 자동 생성
	 * Model에 저장
	 * Model에 addAttribute()에  loginMember 키로 데이터 저장  = 세션에 데이터가 저장되는 원리 
	 * 
	 * 
	 */
	//http://ip:port/login  
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
	
	/*
	 * 
	 */
//	@PostMapping("addUser")
//	public String addUser(    ) {
//		
//	}
	
}