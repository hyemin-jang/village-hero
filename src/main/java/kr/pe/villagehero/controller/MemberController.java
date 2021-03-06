package kr.pe.villagehero.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.pe.villagehero.dto.MemberDTO;
import kr.pe.villagehero.dto.MemberDTO.Get;
import kr.pe.villagehero.entity.Member;
import kr.pe.villagehero.service.MemberService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class MemberController {

	@Autowired
	private MemberService service;	

	// 회원가입 메소드
	@PostMapping("addMember")
	public void insertMember(MemberDTO.Join newMember, @ApiIgnore HttpServletResponse response) throws Exception {
		String newEmail = newMember.getEmail();
		String newNickname = newMember.getNickname();
		String newPhone = newMember.getPhone();

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		try {
			boolean emailCheck = service.emailCheck(newEmail);
			boolean nicknameCheck = service.nicknameCheck(newNickname);
			boolean phoneCheck = service.phoneCheck(newPhone);

			if (emailCheck == false) {
				out.println("<script>alert('존재하는 이메일입니다.'); history.back(); </script>");
				out.flush();
			} else if (nicknameCheck == false) {
				out.println("<script>alert('존재하는 닉네임입니다.'); history.back(); </script>");
				out.flush();
			} else if (phoneCheck == false) {
				out.println("<script>alert('존재하는 전화번호입니다.'); history.back();</script>");
				out.flush();
			} else {
				boolean result = service.insertMember(newMember);
				if (result == true) {
					out.println("<script>alert('회원가입이 완료되었습니다.'); window.location = \"/index.html\"; </script>");
					out.flush();
				}
			}
		} catch (Exception e) {
			throw new Exception("입력 정보를 확인하세요.");
		}
	}
	
	
	// 로그인 메소드
	 @ApiOperation(value = "로그인", notes = "로그인")
	    @ApiResponses({
	            @ApiResponse(code = 200, message = "OK !!"),
	            @ApiResponse(code = 500, message = "500 에러 발생시 출력 메세지, 가령 Internal Server Error !"),
	            @ApiResponse(code = 404, message = "404 에러 발생시 출력 메세지, Not Found !")
	    })
	@GetMapping("/login")
	public MemberDTO.Get logIn(@ApiIgnore HttpSession session, MemberDTO.Login loginData) {
		MemberDTO.Get member = service.logIn(loginData.getEmail());
		if (member != null) {
			
			// 로그인 성공시
			if (member.getMemberStatus() == 0 && member.getPassword().equals(loginData.getPassword())) {
				session.setAttribute("loginMember", member);

				// 로그인 실패시 (비밀번호 오류)
			} else {
				return null;
			}
		} else {
			return null;
		}
		return member;
	}

	 @ApiIgnore
	@GetMapping("logout")
	public RedirectView logOut(HttpSession session) {
		session.invalidate();
		session = null;
		return new RedirectView("/index.html");
	}

	// 회원정보 수정
	@PutMapping("updateMember")
	public void updateMember(@ApiIgnore HttpSession session, MemberDTO.update member, @ApiIgnore HttpServletResponse response)
			throws Exception {
		MemberDTO.Get loginMember = (Get) session.getAttribute("loginMember");
		long id = loginMember.getMemberId();

		try {
			boolean result = service.updateMember(id, member);
			if (result == true) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('수정이 완료되었습니다.'); window.location = \"/mypage.html\"; </script>");
				out.flush();
			}
		} catch (Exception e) {
			throw new Exception("입력 정보를 확인하세요.");

		}
	}

	// 회원정보 수정후 로그인 멤버 정보 프론트로 보내기
	@GetMapping("getLogInInfo")
	public Optional<Member> getLogInInfo(long memberId) {		
		return service.getLogInInfo(memberId);
	}

	// 회원탈퇴
	@PutMapping("deleteMember/{id}")
	public String deleteMember(@ApiIgnore HttpSession session, @PathVariable long id) throws IOException {
		try {
			service.deleteMember(id);
			session.invalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "탈퇴성공";
	}

	@GetMapping("memberStatus")
	public int getMemberStatus(long errandId) {
		System.out.println("상태 가져오기 시도");
		return service.getMemberStatus1(errandId);
	}

	
	
	@ApiIgnore
	@ExceptionHandler
	public void memberException(HttpServletResponse response, Exception e) {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println("<script>alert('입력 정보를 확인하세요'); window.location = \"/login.html\"; </script>");
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
	}
}