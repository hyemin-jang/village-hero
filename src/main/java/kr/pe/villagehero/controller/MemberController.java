package kr.pe.villagehero.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import kr.pe.villagehero.dao.MemberRepository;
import kr.pe.villagehero.dto.ErrandDTO;
import kr.pe.villagehero.dto.MemberDTO;
import kr.pe.villagehero.dto.MyPageDTO;
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

	// 세션에 담긴 로그인한 회원의 정보 반환하는 메소드
	@GetMapping("logincheck")
	public Object getLogInSession(HttpSession session) {
		return session.getAttribute("loginMember");
	}

	@GetMapping("logout")
	public RedirectView logOut(HttpSession session) {
		System.out.println("헷갈리니까 적은 없애기 전 세션");
		System.out.println(session.getAttribute("loginMember"));
//		session.setComplete();
		System.out.println(session.getAttribute("loginMember"));
		
		session.invalidate();
		session=null;
//		System.out.println(session.getAttribute("loginMember"));
		System.out.println("헷갈리니까 적은 없앤 후 세션");
		return new RedirectView("/index.html");
	}
	/*
	 * 
	 */
//	@PostMapping("addUser")
//	public String addUser(    ) {
//		
//	}
	
	//현재 세션에 저장된 member_id 값으로 내가 등록한 모든 심부름 목록 출력.
	@GetMapping("myerrands")
	public List<ErrandDTO> MyErrands(Model model) {
		List<ErrandDTO> allerrands = service2.getAllErrands();
		List<ErrandDTO>	myerrands = new ArrayList<ErrandDTO>();
		MemberDTO.Get sessiondata= (MemberDTO.Get)model.getAttribute("loginMember");
		
		for(int i=0;i<allerrands.size();i++) {
			if(allerrands.get(i).getWriter()==sessiondata.getMemberId()) {
				myerrands.add(allerrands.get(i));
			}
		}
		return myerrands;
	}
	
	
	//현재 세션에 저장된 member_id 값으로 내가 지원한 모든 심부름 목록(수락대기중) 출력
	@GetMapping("myapply")
	public List<ErrandDTO> myApply(Model model){
		List<MyPageDTO.Get> allapply = service3.getAllApplies();
		List<ErrandDTO>	myapply = new ArrayList<>();
		List<ErrandDTO> allerrand = service2.getAllErrands(); 
		Long num = 0l;
		MemberDTO.Get sessiondata = (MemberDTO.Get)model.getAttribute("loginMember");
		for(int i=0;i<allapply.size();i++) {
			if(sessiondata.getMemberId() == allapply.get(i).getApplicant()) {
				num = allapply.get(i).getErrand();
			}
			for(int j=0;j<allerrand.size();j++) {
				if(num==allerrand.get(i).getErrandId()) {
					myapply.add(allerrand.get(j));
					break;
				}
			}
		}
		return myapply;
	}
	
	//현제 세션에 저장된 회원의 아이디값 뽑기
//	@GetMapping("idCheck")
//	public long insertErrand(Model model) {
//		MemberDTO.Get loginMember = (Get) model.getAttribute("loginMember");
//		long id = loginMember.getMemberId();
//
//		return id;
//	} 

}