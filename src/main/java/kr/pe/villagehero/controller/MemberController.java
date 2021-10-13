package kr.pe.villagehero.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

import kr.pe.villagehero.dto.ApplyDTO;
import kr.pe.villagehero.dto.ErrandDTO;
import kr.pe.villagehero.dto.MemberDTO;
import kr.pe.villagehero.service.ApplyService;
import kr.pe.villagehero.service.ErrandService;
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
	
	@Autowired
	private ErrandService service2;
	
	@Autowired
	private ApplyService service3;
	
	@PostMapping("login")
	public RedirectView logIn(Model model, MemberDTO.Login loginData) {
		
		MemberDTO.Get loginMember = service.logIn(loginData.getEmail());  
		System.out.println("controller - " + loginMember.getNickname());
		
		model.addAttribute("loginMember", loginMember);		// 세션에 현재 로그인한 회원의 정보 저장
		
//		return "redirect:mypage.html";  ?????
		return new RedirectView("/index.html");//restController는 이런식으로 redirect:/ 를 표현해야함.		
		
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
		List<ApplyDTO.Get> allapply = service3.getAllApplies();
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
}