package kr.pe.villagehero.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import kr.pe.villagehero.dto.ApplyDTO;
import kr.pe.villagehero.dto.ErrandDTO;
import kr.pe.villagehero.dto.MyPageDTO;
import kr.pe.villagehero.service.ApplyService;
import kr.pe.villagehero.service.ErrandService;

@RestController
public class ApplyController {

	@Autowired
	private ApplyService service;
	@Autowired
	private ErrandService errandService;

	// 마이페이지 - 내가 요청한 심부름 내역
	@GetMapping("/mypage/req")
	public List<MyPageDTO.Req> getAllMyReq(Long memberId) {
		return service.getAllMyReq(memberId);
	}

	// 마이페이지 - 내가 수행한 심부름 (applicant=memberId, errand.errand_status=3)
	@GetMapping("/mypage/completion")
	public List<MyPageDTO.Completion> getAllMyCompletion(Long memberId) {
		return service.getAllMyCompletion(memberId);
	}

	// 도와줄게요 (심부름 지원하기)
	@PostMapping("/apply")
	public RedirectView apply(long errandId, long memberId, String message) {

		service.addApply(errandId, memberId, message); // apply 테이블에 지원내역 추가
		errandService.updateErrandStatusToWaiting(errandId); // 해당 심부름 상태 1 (지원자대기중) 으로 변경

		return new RedirectView("myerrands.html");
	}

	// 내 심부름 - 내가 지원한 심부름 목록 로딩
	@GetMapping("myerrands/apply")
	public List<MyPageDTO.MyApply> getAllMyApply(Long memberId) {
		return service.getMyApply(memberId);
	}

	// 심부름 상세페이지에서 모든 지원자 목록 조회
	@GetMapping("applicants")
	public List<ApplyDTO.List> getAllApplicants(long errandId) {
		return service.getAllApplicants(errandId);
	}
	
	//????
	// 심부름 상세페이지에서 로그인한 멤버가 이 심부름에 지원한 내역 조회
	@GetMapping("isApplied")
	public boolean getApplyHistory(long errandId, long memberId){
		return service.getApplyHistory(errandId, memberId);
	}
	
	// 지원 수락하기
	@GetMapping("accept")
	public RedirectView acceptApply(long errandId, long memberId) {
		System.out.println("수락 컨트롤러: " + errandId + memberId);
		service.acceptApply(errandId, memberId);
		errandService.updateErrandStatusToMatched(errandId);

		return new RedirectView("index.html");
	}

	// 내심부름 - 지원 취소
	@GetMapping("applycancel")
	public void cancel(Long memberId, Long errandId, HttpServletResponse response) {

		try {
			boolean result = service.cancel(memberId, errandId);
			if (result) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('정상적으로 취소되었습니다.'); history.back(); </script>");
				out.flush();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
