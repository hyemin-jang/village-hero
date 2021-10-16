package kr.pe.villagehero.dto;

import kr.pe.villagehero.entity.Errand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ErrandDTO {
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

	// entity 객체를 받아서 DTO로 변환해주는 생성자
	public ErrandDTO(Errand entity) {
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
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Setter
	public static class updateErrand { // 로그인한 정보 넘겨줄때 필요한 DTO 클래스
		private long errandId;
		private String title;
		private String reqLocation;
		private int pay;
		private String category;
		private String reqDate;
		private String content;
	}
}

	
