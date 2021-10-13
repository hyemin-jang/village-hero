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

	// 마이페이지 - 내가 요청한 심부름 내역 (등록일-errand.createdAt, 심부름 제목-errand.title, 분류-errand.category, 도와준 히어로-applicant.nickname, 완료일)
	public List<ApplyDTO.ErrandInfo> getAllMyReqDone(Long memberId) {
		Optional<Member> m = memberDAO.findById(memberId);  
		List<ApplyDTO.ErrandInfo> myReqList = new ArrayList<>();
		
		m.ifPresent(member -> {
			System.out.println(member);
			List<Apply> all = applyDAO.findByApplicant(member);			
			
			all.forEach(v -> myReqList.add(new ApplyDTO.ErrandInfo(v.getErrand().getWriter().getNickname(),
																   v.getErrand().getCreatedAt(),
																   v.getErrand().getTitle(),
																   v.getErrand().getCategory(),
															       v.getApplicant().getNickname())));
		});			
		
		return myReqList;
		
	}
}
