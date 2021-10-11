package kr.pe.villagehero.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@SequenceGenerator(name="apply_seq", sequenceName="apply_seq", initialValue=1, allocationSize=1)
public class ApplyEntity {
	
	@Id
	@Column(name="apply_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apply_seq")
	private long applyId;
	
	@OneToOne
	@JoinColumn(name="member_id")
	private MemberEntity applicant;
	
	private String message;
	
	@OneToMany(mappedBy = "applyId")
	List<ErrandApplyEntity> errands = new ArrayList<>();
}
