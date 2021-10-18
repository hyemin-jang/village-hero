package kr.pe.villagehero.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.villagehero.dao.ErrandRepository;
import kr.pe.villagehero.dao.MemberRepository;
import kr.pe.villagehero.dto.MemberDTO;
import kr.pe.villagehero.dto.MemberDTO.Get;
import kr.pe.villagehero.entity.Member;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberDAO;
	
	@Autowired
	private ErrandRepository errandDAO;
	
// 	회원가입
	public boolean insertMember(MemberDTO.Join newMember) {
		System.out.println("회원 가입 등록 시도");
		boolean result = false;
		try {
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
			
			memberDAO.save(member);
			result = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	//MemberDTO 클래스 내부 이너클래스인 Get클래스	
	// 로그인 - DB에서 해당 email 가진 회원 조회
	public Get logIn(String email) {			
		Member m = memberDAO.findByEmail(email);
		MemberDTO.Get loginMember = null;
		if (m!=null) {  
			loginMember = new MemberDTO.Get(m);
		} else {  // 없는 회원일때
			return null;
		}
		
		return loginMember;
	}


	public boolean updateMember(long id, MemberDTO.update member) {
		System.out.println("회원 정보 수정 시도");
		Member updateMember = memberDAO.findById(id).get();
		boolean result = false;
		
		try {
			updateMember.setNickname(member.getNickname());
			updateMember.setPhone(member.getPhone());
			updateMember.setAddress(member.getAddress());
			updateMember.setSpecialty1(member.getSpecialty1());
			updateMember.setSpecialty2(member.getSpecialty2());
			updateMember.setSpecialty3(member.getSpecialty3());
		
			memberDAO.save(updateMember);
			result = true;
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean emailCheck(String newEmail) {
		boolean result = true;
		if(memberDAO.findByEmail(newEmail) != null) {
			result = false;
		}
		return result;
	}
	
	public boolean nicknameCheck(String newNickname) {
		boolean result = true;
		if(memberDAO.findByNickname(newNickname) != null) {
			result = false;
		}
		return result;
	}
	
	public boolean phoneCheck(String newPhone) {
		boolean result = true;
		if(memberDAO.findByPhone(newPhone) != null) {
			result = false;
		}
		return result;
	}

	public boolean deleteMember(long id) {
		System.out.println("회원탈퇴시도");
		Member member = memberDAO.findById(id).get();
		boolean result = false;
		try {
			member.setMemberStatus(1);
		
			memberDAO.save(member);
			result = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//게시글 아이디로 회원상태 가져오기
	public int getMemberStatus1(long errandId) {
		int memberStatus = errandDAO.findById(errandId).get().getWriter().getMemberStatus();
		return memberStatus;
	}
}