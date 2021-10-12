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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@SequenceGenerator(name="errand_apply_seq", sequenceName="errand_apply_seq", initialValue=1, allocationSize=1)
public class ErrandApply {
	
	@Id
	@Column(name="errand_apply_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "errand_apply_seq")
	private long errandApplyId;
	
	@ManyToOne
	@JoinColumn(name="errand_id")
	private Errand errandId;
	
	@ManyToOne
	@JoinColumn(name="apply_id")
	private Apply applyId;
	
	private char matching;
}
