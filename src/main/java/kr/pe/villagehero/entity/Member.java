package kr.pe.villagehero.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@SequenceGenerator(name="member_seq", sequenceName="member_seq", initialValue=1, allocationSize=1)
public class Member {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="member_seq")
	@Column(name="member_id")
	private long memberId;
	
	private String email;
	
	private String password;
	
	private String nickname;
	
	private int birthYear;
	
	private int birthMonth;
	
	private int birthDay;
	
	private char gender;
	
	private String phone;
	
	private String address;
	
	private String specialty1;
	
	private String specialty2;
	
	private String specialty3;
	
	private int score;
	
	@Column(name="member_status")
	private int memberStatus;

	public Member(String email, String password, String nickname, int birthYear, int birthMonth, int birthDay,
			char gender, String phone, String address, String specialty1, String specialty2, String specialty3) {
		super();
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.birthYear = birthYear;
		this.birthMonth = birthMonth;
		this.birthDay = birthDay;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
		this.specialty1 = specialty1;
		this.specialty2 = specialty2;
		this.specialty3 = specialty3;
	}
	
//	@OneToMany(mappedBy="memberId")
//	List<Errand> errands = new ArrayList<>();
}
