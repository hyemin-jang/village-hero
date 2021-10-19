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
public class ErrandDTO implements Comparable<ErrandDTO> {
	private long errandId;
	private long writerId;
	private String writer;
	private int writerStatus;
	private int pay;
	private String createdAt;
	private String title;
	private String content;
	private String category;
	private String reqLocation;
	private String reqDate;
	private String completedAt;
	private char errandStatus;

	// entity 객체를 받아서 DTO로 변환해주는 생성자
	public ErrandDTO(Errand entity) {
		this.errandId = entity.getErrandId();
		this.writerId = entity.getWriter().getMemberId();
		this.writer = entity.getWriter().getNickname();
		this.writerStatus = entity.getWriter().getMemberStatus();
		this.pay = entity.getPay();
		this.createdAt = entity.getCreatedAt();
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.category = entity.getCategory();
		this.reqLocation = entity.getReqLocation();
		this.reqDate = entity.getReqDate();
		this.completedAt = entity.getCompletedAt();
		this.errandStatus = entity.getErrandStatus();
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Setter
	public static class newErrand {   // 심부름 등록용 DTO
		private long writerId;
		private int pay;
		private String title;
		private String content;
		private String category;
		private String reqLocation;
		private String reqDate;
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Setter
	public static class updateErrand { // 심부름 수정용 DTO 클래스
		private long errandId;
		private long writerId;
		private String title;
		private String reqLocation;
		private int pay;
		private String category;
		private String reqDate;
		private String content;
	}



	@Override
	public int compareTo(ErrandDTO o) {
		int compareResult = this.createdAt.compareTo(o.createdAt);
        if (compareResult > 0) {
            return -1;
        } else if (compareResult < 0) {
            return 1;
        }
        return 0;
	}

}

	
