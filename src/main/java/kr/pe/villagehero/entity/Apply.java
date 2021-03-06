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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@SequenceGenerator(name="apply_seq", sequenceName="apply_seq", initialValue=1, allocationSize=1)
public class Apply {
	
	@Id
	@Column(name="apply_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apply_seq")
	private long applyId;
	
	@ManyToOne
	@JoinColumn(name="errand_id")
	private Errand errand;
	
	@ManyToOne
	@JoinColumn(name="member_id")
	private Member applicant;
	
	private String message;
	
	@Column(name="applied_at")
	private String appliedAt;
	
	@Column(name="match_status")
	private char matchStatus;
	
	public Apply(Errand errand, Member applicant, String message, String appliedAt, char matchStatus) {
		this.errand = errand;
		this.applicant = applicant;
		this.message = message;
		this.appliedAt = appliedAt;
		this.matchStatus = matchStatus;
	}
	
//	@OneToMany(mappedBy = "applyId")
//	List<ErrandApply> errands = new ArrayList<>();
}
