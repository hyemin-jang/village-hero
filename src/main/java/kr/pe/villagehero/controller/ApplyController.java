package kr.pe.villagehero.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

import kr.pe.villagehero.dto.MemberDTO;
import kr.pe.villagehero.dto.MyPageDTO;
import kr.pe.villagehero.dto.MemberDTO.Get;
import kr.pe.villagehero.service.ApplyService;
import kr.pe.villagehero.service.ErrandService;

@RestController
@SessionAttributes({"loginMember"})
public class ApplyController {

	@Autowired
	private ApplyService service;	
	@Autowired
	private ErrandService errandService;

	// 마이페이지 - 내가 요청한 심부름 내역
	@GetMapping("/mypage/req")
	public List<MyPageDTO.Req> getAllMyReq(Long memberId){
		return service.getAllMyReq(memberId);
	}
	
	// 마이페이지 - 내가 수행한 심부름 (applicant=memberId, errand.errand_status=3)
	@GetMapping("/mypage/completion")
	public List<MyPageDTO.Completion> getAllMyCompletion(Long memberId){
		return service.getAllMyCompletion(memberId);
	}
	
	// 도와줄게요 (심부름 지원하기)
	@PostMapping("/errandBoard/apply")
	public RedirectView apply(Model model, long errandId, String message) {
		System.out.println(errandId);
		model.getAttribute("loginMember");
		MemberDTO.Get loginMember = (Get) model.getAttribute("loginMember");
		long memberId = loginMember.getMemberId();
		
		service.addApply(memberId, message);  // apply 테이블에 지원내역 추가
		errandService.updateErrandStatus();  // 해당 심부름 상태 1 (지원자대기중) 으로 변경
		
		return new RedirectView("");
	}
	
	//내 심부름 - 내가 지원한 심부름 목록 로딩
	@GetMapping("myerrands/apply")
	public List<MyPageDTO.MyApply> getAllMyApply(Long memberId){
		
		return service.getMyApply(memberId);
	}
}
