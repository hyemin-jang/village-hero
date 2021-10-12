package kr.pe.villagehero.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Builder
@Entity
@SequenceGenerator(name="errand_seq", sequenceName="errand_seq", initialValue=1, allocationSize=1)
public class Errand {

	public Errand(Member writer, int pay, String createdAt, String title, String content, String category,
			String reqLocation, String reqDate, char errandStatus) {
		super();
		this.writer = writer;
		this.pay = pay;
		this.createdAt = createdAt;
		this.title = title;
		this.content = content;
		this.category = category;
		this.reqLocation = reqLocation;
		this.reqDate = reqDate;
		this.errandStatus = errandStatus;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="errand_seq")
	@Column(name="errand_id")
	private long errandId;
	
	@ManyToOne
	@JoinColumn(name="writer")
	private Member writer;
	
	private int pay;
	
	@Column(name="created_at")
	private String createdAt;
	
	private String title;
	
	private String content;
	
	private String category;
	
	@Column(name="req_location")
	private String reqLocation;
	
	@Column(name="req_date")
	private String reqDate;
	
	@Column(name="errand_status")
	private char errandStatus;
	
//	@OneToMany(mappedBy = "errandId")
//	List<Apply> applicants = new ArrayList<Apply>();
	
	
	
}