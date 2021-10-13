package kr.pe.villagehero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.villagehero.dto.ApplyDTO;
import kr.pe.villagehero.service.ApplyService;

@RestController
public class ApplyController {
	
	@Autowired
	private ApplyService service;	

	

	
	// 마이페이지 - 내가 요청한 심부름 (applicant=memberId, errand.errand_status=3)
	@GetMapping("my-req-done")
	public List<ApplyDTO> getAllMyReqDone(Long memberId){
		return service.getAllMyReqDone(memberId);
	}
}
