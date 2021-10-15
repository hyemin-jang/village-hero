package kr.pe.villagehero.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.view.RedirectView;

import kr.pe.villagehero.dao.MemberRepository;
import kr.pe.villagehero.dto.MemberDTO;
import kr.pe.villagehero.service.ApplyService;
import kr.pe.villagehero.service.ErrandService;
import kr.pe.villagehero.service.MemberService;

@RestController
//@SessionAttributes({ "loginMember" }) // loginMember 라는 이름으로 서버 메모리에 client 정보 저장하겠다는 설정
public class MemberController {

	@Autowired
	private MemberRepository dao;

	@Autowired
	private MemberService service;

	/*
	 * /http://ip:port/login 요청하면서 id/pw값이 전송이 되면 MemberDTO.Login 객체가 자동 생성 Model에
	 * 저장 Model에 addAttribute()에 loginMember 키로 데이터 저장 = 세션에 데이터가 저장되는 원리
	 * 
	 * 
	 */
	// http://ip:port/login

	@Autowired
	private ErrandService service2;

	@Autowired
	private ApplyService service3;


	// 회원가입 메소드
	@PostMapping("addMember")
	public RedirectView insertMember(MemberDTO.Join newMember) {
		System.out.println(newMember);
		service.insertMember(newMember);

		return new RedirectView("index.html");
	}

	/*
	//로그인 메소드
	@GetMapping("/login")
	public MemberDTO.Get logIn(Model model, MemberDTO.Login loginData) {
		MemberDTO.Get member = service.logIn(loginData.getEmail());

		if (member != null) {
			// 로그인 성공시
			if (member.getPassword().equals(loginData.getPassword())) {
				model.addAttribute("loginMember", member); // 세션에 현재 로그인한 회원의 정보 저장

				// 로그인 실패시 (비밀번호 오류)
			} else {
				return null;
			}
		}else {
			return null;
		}
		return member;
	}
	*/
	
	@GetMapping("/login")
	public MemberDTO.Get logIn(HttpSession session, MemberDTO.Login loginData) {
		MemberDTO.Get member = service.logIn(loginData.getEmail());

		if (member != null) {
			// 로그인 성공시
			if (member.getPassword().equals(loginData.getPassword())) {
				session.setAttribute("loginMember", member); // 세션에 현재 로그인한 회원의 정보 저장

				// 로그인 실패시 (비밀번호 오류)
			} else {
				return null;
			}
		}else {
			return null;
		}
		return member;
	}

	/*
	@GetMapping("logout")
	public RedirectView logOut(SessionStatus session, Model model) {		
		session.setComplete();
		model.addAttribute(null);
		return new RedirectView("/index.html");
	}
	*/
	
	@GetMapping("logout")
	public RedirectView logOut(HttpSession session, Model model) {		
		session.invalidate();
		session = null;
		
		return new RedirectView("/index.html");
	}
}