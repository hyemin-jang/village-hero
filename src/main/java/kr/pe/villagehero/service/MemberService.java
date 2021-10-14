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
	
// 	회원가입
	public Member insertMember(MemberDTO.Join newMember) {
		System.out.println("회원 가입 등록 시도");
		
		String email = newMember.getEmail();
		String password = newMember.getPassword();
		String nickname = newMember.getNickname();
		int birthYear = newMember.getBirthYear();
		int birthMonth = newMember.getBirthMonth();
		int birthDay = newMember.getBirthDay();
		char gender = newMember.getGender();
		String phone = newMember.getPhone();
		String address = newMember.getAddress();
		String specialty1 = newMember.getSpecialty1();
		String specialty2 = newMember.getSpecialty2();
		String specialty3 = newMember.getSpecialty3();
		
		Member member = new Member(email, password, nickname,
								   birthYear, birthMonth, birthDay,
								   gender, phone, address, specialty1,
								   specialty2, specialty3);
		
		dao.save(member);
		return member;
	}

	//MemberDTO 클래스 내부 이너클래스인 Get클래스	
	// 로그인 - DB에서 해당 email 가진 회원 조회
	public Get logIn(String email) {			
		Member m = dao.findByEmail(email);
		MemberDTO.Get loginMember = null;
		if (m!=null) {  
			loginMember = new MemberDTO.Get(m);
		} else {  // 없는 회원일때
			return null;
		}
		
		return loginMember;
	}

}