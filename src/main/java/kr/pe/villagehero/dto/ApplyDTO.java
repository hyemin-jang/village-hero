package kr.pe.villagehero.dto;

import kr.pe.villagehero.entity.Apply;
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
	public static class ErrandInfo {  // 마이페이지에서 apply 내역을 바탕으로 심부름 정보들을 출력해야 해서 만든 DTO
		private String writer;
		private String createdAt;
		private String title;
		private String category;
		private String hero;
	}
	
}
