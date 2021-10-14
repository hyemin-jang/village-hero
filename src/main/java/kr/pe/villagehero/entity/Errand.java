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
@Builder
@Entity
@SequenceGenerator(name="errand_seq", sequenceName="errand_seq", initialValue=1, allocationSize=1)
public class Errand {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="errand_seq")
	@Column(name="errand_id")
	private long errandId;
	
	@ManyToOne
	@JoinColumn(name="writer")
	private Member writer;	
	
	@Column(name="created_at")
	private String createdAt;
	
	private String title;
	
	private String content;
	
	private String category;
	
	@Column(name="req_location")
	private String reqLocation;
	
	@Column(name="req_date")
	private String reqDate;
	
	private int pay;
	
	@Column(name="errand_status")
	private char errandStatus;
	
	@Column(name="completed_at")
	private String completedAt;
	
	
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


	public void setErrandId(long errandId) {
		this.errandId = errandId;
	}


	public void setWriter(Member writer) {
		this.writer = writer;
	}


	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public void setReqLocation(String reqLocation) {
		this.reqLocation = reqLocation;
	}


	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}


	public void setPay(int pay) {
		this.pay = pay;
	}


	public void setErrandStatus(char errandStatus) {
		this.errandStatus = errandStatus;
	}


	public void setCompletedAt(String completedAt) {
		this.completedAt = completedAt;
	}

	



//	@OneToMany(mappedBy = "errandId")
//	List<Apply> applicants = new ArrayList<Apply>();
	
	
	
}