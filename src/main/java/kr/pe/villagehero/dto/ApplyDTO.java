package kr.pe.villagehero.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ApplyDTO {
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Setter
	@Builder
	public static class Form {
		private long memberId;
		private String nickname;
		private int age;
		private char gender;
		private String phone;
		private String specialty1;
		private String specialty2;
		private String specialty3;
		private int score;
	}

}
