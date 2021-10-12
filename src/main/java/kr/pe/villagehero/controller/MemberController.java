package kr.pe.villagehero.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.villagehero.dao.MemberRepository;
import kr.pe.villagehero.entity.Member;

@RestController
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private MemberRepository dao;
	
	@GetMapping("/select")
	public Member select(Long id) {
		Optional<Member> member = dao.findById(id);
		return member.get();
	}
	
}
