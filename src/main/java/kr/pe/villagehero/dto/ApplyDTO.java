package kr.pe.villagehero.dto;

import kr.pe.villagehero.entity.Apply;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApplyDTO {
	
	private long applyId;
	private long errand;
	private long applicant;
	private String message;
	private String createdAt;
	private char matchStatus;
	
	public ApplyDTO(Apply entity) {
		this.applyId=entity.getApplyId();
		this.errand=entity.getErrand().getErrandId();
		this.applicant=entity.getApplicant().getMemberId();
		this.message=entity.getMessage();
		this.createdAt=entity.getCreatedAt();
		this.matchStatus=entity.getMatchStatus();
	}
}
