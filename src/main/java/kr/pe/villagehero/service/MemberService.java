package kr.pe.villagehero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.villagehero.dao.MemberRepository;
import kr.pe.villagehero.dto.MemberDTO;
import kr.pe.villagehero.dto.MemberDTO.Get;
import kr.pe.villagehero.entity.Member;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository dao;

	public Get logIn(String email) {	
		System.out.println("service - " + email);
		
		Member m = dao.findByEmail(email);
		System.out.println("service - " + m);
		
		MemberDTO.Get loginMember = new MemberDTO.Get(m);
		return loginMember;
	}	

}
