package kr.pe.villagehero.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@SequenceGenerator(name="errand_seq", sequenceName="errand_seq", initialValue=1, allocationSize=1)
public class Errand {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="errand_seq")
	@Column(name="errand_id")
	private long errandId;
	
	@ManyToOne
	@JoinColumn(name="member_id")
	private Member writer;
	
	@Column(name="created_at")
	private Date createdAt;
	
	private String title;
	
	private String content;
	
	private String category;
	
	@Column(name="req_location")
	private String reqLocation;
	
	@Column(name="req_date")
	private Date reqDate;
	
	@Column(name="errand_status")
	private char errandStatus;
	
//	@OneToOne
//	@JoinColumn(name="member_id")
//	private Member hero;
	
//	@OneToMany(mappedBy = "errandId")
//	List<ErrandApply> apllicants = new ArrayList<>();
}
