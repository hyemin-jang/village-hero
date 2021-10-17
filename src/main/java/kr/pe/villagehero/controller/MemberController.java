package kr.pe.villagehero.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import kr.pe.villagehero.dto.ErrandDTO;
import kr.pe.villagehero.dto.MemberDTO;
import kr.pe.villagehero.dto.MemberDTO.Get;
import kr.pe.villagehero.service.MemberService;

@RestController
public class MemberController {

	@Autowired
	private MemberService service;

	/*
	 * /http://ip:port/login 요청하면서 id/pw값이 전송이 되면 MemberDTO.Login 객체가 자동 생성 Model에
	 * 저장 Model에 addAttribute()에 loginMember 키로 데이터 저장 = 세션에 데이터가 저장되는 원리
	 * 
	 * 
	 */
	// http://ip:port/login

	// 회원가입 메소드
	@PostMapping("addMember")
	public RedirectView insertMember(MemberDTO.Join newMember) {
		System.out.println(newMember);
		service.insertMember(newMember);

		return new RedirectView("index.html");
	}
	
	//	로그인 메소드
	@GetMapping("/login")
	public MemberDTO.Get logIn(HttpSession session, MemberDTO.Login loginData) {
		System.out.println(" --===== " + loginData);
		MemberDTO.Get member = service.logIn(loginData.getEmail());
		if (member != null) {
			System.out.println("여기는 오니?");
			// 로그인 성공시
			if (member.getPassword().equals(loginData.getPassword())) {
				System.out.println("여기를 못온느거같애");
				session.setAttribute("loginMember", member);
				System.out.println("----------------------------");

				// 로그인 실패시 (비밀번호 오류)
			} else {
				return null;
			}
		}else {
			return null;
		}
		System.out.println("==================== " + member);
		return member;
	}
	
	@GetMapping("logout")
	public RedirectView logOut(HttpSession session) {
		
		session.invalidate();
		session=null;
		return new RedirectView("/index.html");
	}
	
	@PutMapping("updateMember")
	public RedirectView updateMember(HttpSession session, MemberDTO.update member) {
		MemberDTO.Get loginMember = (Get) session.getAttribute("loginMember");
		long id = loginMember.getMemberId();
		System.out.println(id);
		
		System.out.println("회원정보 수정");
		service.updateMember(id, member);

		return new RedirectView("index.html");
	} 

}