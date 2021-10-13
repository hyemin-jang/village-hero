package kr.pe.villagehero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.villagehero.dao.MemberRepository;

@Service
public class MemberService {	
	
	@Autowired
	private MemberRepository dao;
	


}
