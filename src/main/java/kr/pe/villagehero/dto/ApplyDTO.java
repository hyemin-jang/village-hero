package kr.pe.villagehero.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import kr.pe.villagehero.entity.Errand;
import kr.pe.villagehero.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
	private String appliedAt;
	private char matchStatus;
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Setter
	@Builder
	public static class Form {
		private long memberId;
		private String nickname;
		private int birthYear;
		private String phone;
		private String specialty1;
		private String specialty2;
		private String specialty3;
		private int score;
	}
	
	
	
}
