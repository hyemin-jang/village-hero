package kr.pe.villagehero.controller;

import java.util.Optional;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;
import kr.pe.villagehero.dao.MemberRepository;
import kr.pe.villagehero.entity.Member;
import kr.pe.villagehero.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {
	
	private static final org.jboss.logging.Logger Logger = LoggerFactory.logger(MemberController.class);
	@Autowired
	private MemberRepository dao;
	
	@Autowired
	private MemberService memberService;
	
//	회원가입 페이지 이동
	@RequestMapping(value="join", method=RequestMethod.GET)
	public void memberJoin() {
		Logger.info("회원가입 페이지 이동");
	}
	
//	로그인 페이지 이동
	@RequestMapping(value="login", method=RequestMethod.GET)
	public void memberJoin() {
		Logger.info("회원가입 페이지 이동");
	}
	
//	회원가입
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public void 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/select")
	public Member select(Long id) {
		Optional<Member> member = dao.findById(id);
		return member.get();
	}
	
	@PostMapping("/create")
	public String create() {
		dao.save(new Member(5, "hm@gmail.com", "123", "123", "hm", 20, 'F', "010-0000-0000", "seoul", "aa", null, null, null, 0, 0));
		return "redirect:address.html";
	}
}
