package kr.pe.villagehero.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.villagehero.dao.ApplyRepository;
import kr.pe.villagehero.dao.ErrandRepository;
import kr.pe.villagehero.dao.MemberRepository;
import kr.pe.villagehero.dto.MyPageDTO;
import kr.pe.villagehero.dto.MyPageDTO.Req;
import kr.pe.villagehero.entity.Apply;
import kr.pe.villagehero.entity.Errand;
import kr.pe.villagehero.entity.Member;

@Service
public class ApplyService {

	@Autowired
	private ApplyRepository dao;
	
	//존재하는 모든 지원정보들 return
	public List<MyPageDTO.Get> getAllApplies(){
		List<Apply> all = (List<Apply>)dao.findAll();
		List<MyPageDTO.Get> all2 = new ArrayList<>();
		
		all.forEach(v -> all2.add(new MyPageDTO.Get(v)));
		
		return all2;
	}
	
	@Autowired
	private ApplyRepository applyDAO;
	@Autowired
	private MemberRepository memberDAO;
	@Autowired
	private ErrandRepository errandDAO;
	
	// 마이페이지 - 내가 요청한 심부름 내역
	public List<MyPageDTO.Req> getAllMyReq(Long memberId) {
		Optional<Member> m = memberDAO.findById(memberId);  
		List<MyPageDTO.Req> myReqList = new ArrayList<>();
		
		m.ifPresent(member -> {
			List<Errand> all = errandDAO.findMyReq(member);	
			all.forEach(v -> myReqList.add(new MyPageDTO.Req(v.getCreatedAt(),
															v.getTitle(),
															v.getCategory(),															
															v.getCompletedAt())));
															       
		});			
		
		return myReqList;
	}

	// 마이페이지 - 내가 수행한 심부름 내역
	public List<MyPageDTO.Completion> getAllMyCompletion(Long memberId) {
		Optional<Member> m = memberDAO.findById(memberId);  
		List<MyPageDTO.Completion> myCompletionList = new ArrayList<>();
		
		m.ifPresent(member -> {
			List<Apply> all = applyDAO.findMyCompletion(member);			
			
			all.forEach(v -> myCompletionList.add(new MyPageDTO.Completion(v.getErrand().getCompletedAt(),
																   v.getErrand().getWriter().getNickname(),
																   v.getErrand().getTitle(),
																   v.getErrand().getCategory())));
															       
		});			
		
		return myCompletionList;
	}

	public List joinTest(long memberId) {
		List result = new ArrayList<>();
		Optional<Apply> a = applyDAO.findById(memberId);
		a.ifPresent(v -> result.add(v));
		return result;
		
	}
	
	//내 심부름 -> 내가 지원한 심부름 목록
	public List<MyPageDTO.MyApply> getMyApply(Long memberId){
		Optional<Member> m = memberDAO.findById(memberId);
		List<MyPageDTO.MyApply> all = new ArrayList<>();
		
		m.ifPresent(member -> {
			List<Apply> sub = applyDAO.findMyApply(member);
			
			sub.forEach(v -> all.add(new MyPageDTO.MyApply(v.getErrand().getTitle(),
															v.getMatchStatus())));
		});
		return all;
	}
}
