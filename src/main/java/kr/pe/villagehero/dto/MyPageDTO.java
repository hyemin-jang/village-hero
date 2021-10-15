package kr.pe.villagehero.dto;

import javax.persistence.Id;

import kr.pe.villagehero.entity.Apply;
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
	public static class ErrandDTO2{
		private String title;
		private char errandStatus;
	}
	
	//내심부름 -> 내 심부름 내가 지원한 심부름 목록 확인에서 뿌려줄 내용을 위한 DTO
	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Setter
	@Builder
	public static class MyApply{
		private String title;
		private char matchStatus;
	}
	
}
