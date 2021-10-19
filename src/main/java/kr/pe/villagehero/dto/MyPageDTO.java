package kr.pe.villagehero.dto;

import javax.persistence.Id;

import kr.pe.villagehero.entity.Apply;
import kr.pe.villagehero.entity.Errand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class MyPageDTO {
	
	// 마이페이지에서 apply 내역을 바탕으로 심부름 정보들을 출력해야 해서 만든 DTO
	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Setter
	@Builder
	public static class Completion {  
		private long errandId;
		private String completedAt;
		private String writer;
		private String title;
		private String category;
	}
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Setter
	@Builder
	public static class Req {
		private long errandId;
		private String createdAt;
		private String title;
		private String category;
		private String completedAt;
	}
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Setter
	@Builder
	public static class Get {
		private long applyId;
		private long errand;
		private long applicant;
		private String message;
		private String appliedAt;
		private char matchStatus;
		
		public Get(Apply entity) {
			this.applyId=entity.getApplyId();
			this.errand=entity.getErrand().getErrandId();
			this.applicant=entity.getApplicant().getMemberId();
			this.message=entity.getMessage();
			this.appliedAt=entity.getAppliedAt();
			this.matchStatus=entity.getMatchStatus();
		}
	}
	
	//내심부름 -> 내 심부름 요청목록 확인에서 뿌려줄 내용을 위한 DTO
	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Setter
	@Builder
	public static class MyErrand{
		private String title;
		private char errandStatus;
	}
	
	//내심부름 -> 내 심부름 내가 지원한 심부름 목록 확인에서 뿌려줄 내용을 위한 DTO
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Setter
	public static class MyApply{
		private long errandId;
		private long writerId;
		private String writer;
		private int pay;
		private String createdAt;
		private String title;
		private String content;
		private String category;
		private String reqLocation;
		private String reqDate;
		private char errandStatus;
		private char matchStatus;//심부름에 걸려있는 지원자의 매칭상태를 표기해줄것.
		
		public MyApply(Errand entity,Apply apply) {
			this.errandId = entity.getErrandId();
			this.writerId = entity.getWriter().getMemberId();
			this.writer = entity.getWriter().getNickname();
			this.pay = entity.getPay();
			this.createdAt = entity.getCreatedAt();
			this.title = entity.getTitle();
			this.content = entity.getContent();
			this.category = entity.getCategory();
			this.reqLocation = entity.getReqLocation();
			this.reqDate = entity.getReqDate();
			this.errandStatus = entity.getErrandStatus();
			this.matchStatus= apply.getMatchStatus();
		}
	}
	
}
