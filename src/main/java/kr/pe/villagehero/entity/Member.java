package kr.pe.villagehero.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
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
	
	@Column(name="member_status")
	private char memberStatus;
	
//	@OneToMany(mappedBy="memberId")
//	List<Errand> errands = new ArrayList<>();
}
