package kr.pe.villagehero.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.villagehero.dto.MyPageDTO;
import kr.pe.villagehero.service.ApplyService;

@RestController
public class ApplyController {

	@Autowired
	private ApplyService service;	

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
	
	@GetMapping("/mypage/test")
	public List joinTest(long memberId) {
		return service.joinTest(memberId);
	}
	
	//내 심부름 - 내가 지원한 심부름 목록 로딩
	@GetMapping("myerrands/apply")
	public List<MyPageDTO.MyApply> getAllMyApply(Long memberId){
		
		return service.getMyApply(memberId);
	}
}
