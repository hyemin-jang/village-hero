package kr.pe.villagehero.dto;

import kr.pe.villagehero.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MemberDTO {
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Setter
	public static class Get {  // 회원의 모든 정보 조회할 때 사용하는 DTO 클래스 
		private long memberId;
		private String email;		
		private String nickname;
		private int age;
		private char gender;
		private String phone;
		private String address;
		private String specialty1;
		private String specialty2;
		private String specialty3;
		private String picture;
		private int score;
		private int memberStatus;
		
		public Get(Member entity) {
			this.memberId=entity.getMemberId();
			this.email=entity.getEmail();			
			this.nickname=entity.getNickname();
			this.age=entity.getAge();
			this.gender=entity.getGender();
			this.phone=entity.getPhone();
			this.address=entity.getAddress();
			this.specialty1=entity.getSpecialty1();
			this.specialty2=entity.getSpecialty2();
			this.specialty3=entity.getSpecialty3();
			this.picture=entity.getPicture();
			this.score=entity.getScore();
			this.memberStatus=entity.getMemberStatus();
		}
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Setter
	public static class Login {  // 로그인한 정보 넘겨줄때 필요한 DTO 클래스
		private String email;	
		private String password;
	}
}