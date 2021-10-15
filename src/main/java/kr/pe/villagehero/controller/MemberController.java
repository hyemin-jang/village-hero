package kr.pe.villagehero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@SessionAttributes({ "loginMember" }) // loginMember 라는 이름으로 서버 메모리에 client 정보 저장하겠다는 설정
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

	// 로그인 메소드
	/*
	 * @PostMapping("/login") public RedirectView logIn(Model model, MemberDTO.Login
	 * loginData) { MemberDTO.Get member = service.logIn(loginData.getEmail());
	 * RedirectView resultPage = null;
	 * 
	 * // 로그인 성공시 if (member.getPassword().equals(loginData.getPassword())) {
	 * model.addAttribute("loginMember", member); // 세션에 현재 로그인한 회원의 정보 저장
	 * resultPage = new RedirectView("/index.html");
	 * 
	 * // 로그인 실패시 (비밀번호 오류) }else { resultPage = new RedirectView("/login.html");
	 * //restController는 이런식으로 redirect:/ 를 표현해야함. } return resultPage; }
	 */

// 	회원가입 메소드
	@PostMapping("addMember")
	public RedirectView insertMember(MemberDTO.Join newMember) {
		System.out.println(newMember);
		service.insertMember(newMember);

		return new RedirectView("index.html");
	}

//	로그인 메소드
	@GetMapping("/login")
	public MemberDTO.Get logIn(Model model, MemberDTO.Login loginData) {
		System.out.println(" --===== " + loginData);
		MemberDTO.Get member = service.logIn(loginData.getEmail());
		if (member != null) {
			// 로그인 성공시
			if (member.getPassword().equals(loginData.getPassword())) {
				model.addAttribute("loginMember", member); // 세션에 현재 로그인한 회원의 정보 저장
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

	// 세션에 담긴 로그인한 회원의 정보 반환하는 메소드
	@GetMapping("logincheck")
	public Object getLogInSession(Model model) {
		return model.getAttribute("loginMember");
	}

	@GetMapping("logout")
	public RedirectView logOut(SessionStatus session, Model model) {
		System.out.println("헷갈리니까 적은 없애기 전 세션");
		System.out.println(model.getAttribute("loginMember"));
		session.setComplete();
		System.out.println("헷갈리니까 적은 없앤 후 세션");
		model.addAttribute("loginMember", null);
		System.out.println(model.getAttribute("loginMember"));
		return new RedirectView("/index.html");
	}

}