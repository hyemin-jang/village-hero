package kr.pe.villagehero.dto;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
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

}
