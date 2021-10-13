package kr.pe.villagehero.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.villagehero.dao.ApplyRepository;
import kr.pe.villagehero.dao.MemberRepository;
import kr.pe.villagehero.dto.ApplyDTO;
import kr.pe.villagehero.entity.Apply;
import kr.pe.villagehero.entity.Member;

@Service
public class ApplyService {

	@Autowired
	private ApplyRepository applyDAO;
	@Autowired
	private MemberRepository memberDAO;

	// 세션에 있는 로그인 회원 아이디로 내 지원내역 조회
	public List<ApplyDTO> getAllMyReqDone(Long memberId) {
		Optional<Member> m = memberDAO.findById(memberId);  
		List<ApplyDTO> myApplications = new ArrayList<ApplyDTO>();
		
		m.ifPresent(member -> {
			System.out.println(member);
			List<Apply> all = applyDAO.findByApplicant(member);		
			all.forEach(v -> myApplications.add(new ApplyDTO(v)));
		});
		
		return myApplications;
		
	}
}
