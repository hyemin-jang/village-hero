package kr.pe.villagehero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.villagehero.dao.MemberRepository;
import kr.pe.villagehero.dto.MemberDTO;

@Service
public class MemberService implements MemberServiceInter{	
	
	@Autowired
	MemberRepository memberRepository;
	


}
