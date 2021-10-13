package kr.pe.villagehero.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

import kr.pe.villagehero.dto.MyPageDTO;
import kr.pe.villagehero.dto.ErrandDTO;
import kr.pe.villagehero.dto.MemberDTO;
import kr.pe.villagehero.dto.MemberDTO.Get;
import kr.pe.villagehero.service.ApplyService;
import kr.pe.villagehero.service.ErrandService;
import kr.pe.villagehero.service.MemberService;

@RestController
@SessionAttributes({"loginMember"})
public class MemberController {
	
	@Autowired
	private MemberService service;
		
	@Autowired
	private ErrandService service2;
	
	@Autowired
	private ApplyService service3;
	
	// 로그인 메소드
	@PostMapping("/login")
	public RedirectView logIn(Model model, MemberDTO.Login loginData) {		
		MemberDTO.Get member = service.logIn(loginData.getEmail()); 
		RedirectView resultPage = null;
				
		// 로그인 성공시
		if (member.getPassword().equals(loginData.getPassword())) {  
			model.addAttribute("loginMember", member); // 세션에 현재 로그인한 회원의 정보 저장
			resultPage = new RedirectView("/index.html");
			
		// 로그인 실패시 (비밀번호 오류)
		}else {	
			resultPage =  new RedirectView("/login.html"); //restController는 이런식으로 redirect:/ 를 표현해야함.		
		}		
		return resultPage;
	}
	
	
	// 세션에 담긴 로그인한 회원의 정보 반환하는 메소드
	@GetMapping("logincheck")
	public Object getLogInSession(Model model) {
		return model.getAttribute("loginMember");
	}
	
	
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
}
