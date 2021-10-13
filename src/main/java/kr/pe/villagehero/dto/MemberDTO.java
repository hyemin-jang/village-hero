package kr.pe.villagehero.dto;

import kr.pe.villagehero.entity.Member;
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
public class MemberDTO {
	
	private long memberId;
	private String email;
	private String password;
	private String password2;
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
	
// 	entity 객체를 받아서 DTO로 변환해주는 생성자	
	public MemberDTO(Member entity) {
		
		this.memberId = entity.getMemberId();
		this.email = entity.getEmail();
		this.password = entity.getPassword();
		this.password2 = entity.getPassword2();
		this.nickname = entity.getNickname();
		this.age = entity.getAge();
		this.gender = entity.getGender();
		this.phone = entity.getPhone();
		this.address = entity.getAddress();
		this.specialty1 = entity.getSpecialty1();
		this.specialty2 = entity.getSpecialty2();
		this.specialty3 = entity.getSpecialty3();
		this.picture = entity.getPicture();
		this.score = entity.getScore();
		this.memberStatus = entity.getMemberStatus();
	}

}
