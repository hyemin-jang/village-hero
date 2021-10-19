package kr.pe.villagehero.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.pe.villagehero.dto.ErrandDTO;
import kr.pe.villagehero.dto.MemberDTO;
import kr.pe.villagehero.dto.MemberDTO.Get;
import kr.pe.villagehero.service.ErrandService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class ErrandController {

	@Autowired
	private ErrandService service;
	
	//심부름 수정
	@PutMapping("updateErrand")
	public void updateWriter(ErrandDTO.updateErrand errand, @ApiIgnore HttpServletResponse response) {
		try {
			boolean result = service.updateErrand(errand);
			if(result == true) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('수정이 완료되었습니다.'); window.location = \"/errandBoard/list.html\"; </script>");
				out.flush();
			}
		} catch(Exception e) {
			e.printStackTrace();
			
		}
	}

	// 심부름 등록 
	@PostMapping("newErrand")
	public void insertErrand(@ApiIgnore HttpSession session, ErrandDTO newErrand, @ApiIgnore HttpServletResponse response) {
		MemberDTO.Get loginMember = (Get) session.getAttribute("loginMember");
		System.out.println(loginMember);  // 로그아웃후에 서버 세션 진짜 없어졌는지 확인
		long memberId = loginMember.getMemberId();
		System.out.println(memberId);
		
		try {
			boolean result = service.insertErrand(memberId, newErrand);
			if(result == true) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('등록이 완료되었습니다.'); window.location = \"/errandBoard/list.html\"; </script>");
				out.flush();
			}
		} catch(Exception e) {
			e.printStackTrace();
			
		}
	}
	
	// 심부름 완료 처리
	@PutMapping("complete")
	public void completeErrand(long errandId) {
		service.completeErrand(errandId);
	}
	
	
	//심부름 삭제
	@DeleteMapping("errandDelete/{id}")
	public String deleteErrand(@PathVariable long id) {
		service.deleteErrand(id);

		return "심부름 삭제성공";
	}

	// 모든 심부름 내역에서 상세페이지로 이동
	@GetMapping("getErrandDetail/{id}")
	public RedirectView getNewErrand(@PathVariable long id, @ApiIgnore RedirectAttributes attr) {
		attr.addAttribute("errandId", id);  // detail.html로 리다이렉트 할때 ?errandId=id 쿼리스트링을 붙인다
		return new RedirectView("/errandBoard/detail.html");		
	}

	
	
	// 심부름 상세페이지 - 1개 심부름 정보 출력
    @ApiOperation(value = "심부름 1개 조회", notes = "API 설명 부분 : 심부름 1개 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 500, message = "500 에러 발생시 출력 메세지, 가령 Internal Server Error !"),
            @ApiResponse(code = 404, message = "404 에러 발생시 출력 메세지, Not Found !")
    })
	@GetMapping("/errandDetail")
	public ErrandDTO errandDetail(@RequestParam long errandId) {
		ErrandDTO errand = service.getOneErrand(errandId);
		return errand;
	}
	
	//수정페이지로 이동
	@GetMapping("getErrandDetail2/{id}")
	public RedirectView getErrandId2(@PathVariable long id, @ApiIgnore RedirectAttributes attr) {
		attr.addAttribute("errandId2", id);
		return new RedirectView("/errandBoard/update.html");		
	}
	
	
	@GetMapping("/errandDetail2")
	public ErrandDTO errandDetail2(long errandId2) {
		System.out.println(errandId2);
		ErrandDTO errand = service.getOneErrand(errandId2);
		return errand;
	}

	// 모든 심부름 조회
	@GetMapping("errands")
	public List<ErrandDTO> getAllErrands() {		
		List<ErrandDTO> all = service.getAllErrands();
		return all;
	}
	
	// 홈화면에서 현재 진행중인 모든 심부름 조회
	@GetMapping("errandsOngoing")
	public List<ErrandDTO> getAllErrandsOngoing() {		
		List<ErrandDTO> all = service.getAllErrandsOngoing();
		return all;
	}

	// 심부름 1개 조회
	@GetMapping("errand")
	public ErrandDTO getOneErrand(long id) {
		return service.getOneErrand(id);
	}

	// 모든 심부름 내림차순 정렬 조회
	@GetMapping("payerrands")
	public List<ErrandDTO> getAllErrandsPayDesc() {
		List<ErrandDTO> all = service.getAllErrandsPayDes();

		return all;
	}
	
	// 모든 심부름 최신순 정렬 조회
	@GetMapping("dateerrands")
	public List<ErrandDTO> getAllErrandsDateDesc() {
		List<ErrandDTO> all = service.getAllErrandsDateDes();

		return all;
	}
	
	//내 심부름 목록 뿌려주기 -> 삭제버튼/count/title/status 뿌려줄 것임.
	@GetMapping("myerrands/req")
	public List<ErrandDTO> getAllMyErrands(Long memberId){

		return service.getAllMyErrands(memberId);
	}
	
}