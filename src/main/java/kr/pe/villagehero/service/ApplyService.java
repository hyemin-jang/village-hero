package kr.pe.villagehero.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.villagehero.dao.ApplyRepository;
import kr.pe.villagehero.dao.ErrandRepository;
import kr.pe.villagehero.dao.MemberRepository;
import kr.pe.villagehero.dto.ApplyDTO;
import kr.pe.villagehero.dto.ErrandDTO;
import kr.pe.villagehero.dto.MyPageDTO;
import kr.pe.villagehero.entity.Apply;
import kr.pe.villagehero.entity.Errand;
import kr.pe.villagehero.entity.Member;

@Service
public class ApplyService {

	@Autowired
	private ApplyRepository applyDAO;
	@Autowired
	private MemberRepository memberDAO;
	@Autowired
	private ErrandRepository errandDAO;

	// 존재하는 모든 지원정보들 return
	public List<MyPageDTO.Get> getAllApplies() {
		List<Apply> all = (List<Apply>) applyDAO.findAll();
		List<MyPageDTO.Get> all2 = new ArrayList<>();

		all.forEach(v -> all2.add(new MyPageDTO.Get(v)));

		return all2;
	}

	// 마이페이지 - 내가 요청한 심부름 내역
	public List<MyPageDTO.Req> getAllMyReq(Long memberId) {
		Optional<Member> m = memberDAO.findById(memberId);
		List<MyPageDTO.Req> myReqList = new ArrayList<>();

		m.ifPresent(member -> {
			List<Errand> all = errandDAO.findMyReq(member);
			all.forEach(v -> myReqList
					.add(new MyPageDTO.Req(v.getCreatedAt(), v.getTitle(), v.getCategory(), v.getCompletedAt())));

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
					v.getErrand().getWriter().getNickname(), v.getErrand().getTitle(), v.getErrand().getCategory())));

		});

		return myCompletionList;
	}

	// 도와줄게요 (심부름 지원 등록)

	public void addApply(long errandId, long memberId, String message) {		
		Errand errand = errandDAO.findById(errandId).get();  
		Member applicant = memberDAO.findById(memberId).get();
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
		Date time = new Date();		
		
		applyDAO.save(new Apply(errand, applicant, message, dateFormat.format(time), '0'));		
	}

	public List joinTest(long memberId) {
		List result = new ArrayList<>();
		Optional<Apply> a = applyDAO.findById(memberId);
		a.ifPresent(v -> result.add(v));
		return result;
	}


	// 내 심부름 -> 내가 지원한 심부름 목록
		public List<ErrandDTO> getMyApply(Long memberId) {
			Optional<Member> m = memberDAO.findById(memberId);
			List<ErrandDTO> all = new ArrayList<>();
			m.ifPresent(member -> {
				List<Apply> sub = applyDAO.findMyApply(member);
				for(int i=0;i<sub.size();i++) {
					//리스트에다가 지원목록에 해당하는 심부름 id값들의 심부름 정보들을 all에 저장해야함.
					
				}
			});
			return all;
		}
		
	// 내 심부름 -> 해당 지원목록 취소
	public void cancel(Long memberId, Long errandId) {

		Member m = memberDAO.findById(memberId).get();
		Errand e = errandDAO.findById(errandId).get();
		
		MyPageDTO.MyApply cancel;
		
		Apply sub = applyDAO.findCancelApply(m, e);
		sub.setAppliedAt(sub.getAppliedAt().replace(" 00:00:00", ""));
		sub.setMatchStatus('3');
		applyDAO.save(sub);
//		cancel = new MyPageDTO.MyApply(e.getTitle(), sub.getMatchStatus());
	}

	
	// 심부름 상세페이지에서 모든 지원자들 내역 조회
	public List<ApplyDTO.Form> getAllApplicants(long errandId) {
		List<ApplyDTO.Form> applyList = new ArrayList<ApplyDTO.Form>();
		
		Errand e = errandDAO.findById(errandId).get();
		List<Apply> all = applyDAO.findByErrand(e);
		all.forEach(v -> applyList.add(new ApplyDTO.Form(v.getApplicant().getMemberId(),
														v.getApplicant().getNickname(),
														v.getApplicant().getBirthYear(),
														v.getApplicant().getPhone(),
														v.getApplicant().getSpecialty1(),
														v.getApplicant().getSpecialty2(),
														v.getApplicant().getSpecialty3(),
														v.getApplicant().getScore())));
		return applyList;
	}
}
