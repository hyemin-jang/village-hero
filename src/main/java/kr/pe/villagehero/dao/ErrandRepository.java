package kr.pe.villagehero.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import kr.pe.villagehero.entity.Errand;
import kr.pe.villagehero.entity.Member;

public interface ErrandRepository extends CrudRepository<Errand, Long>{
	
	@Query("select e from Errand e where e.writer=:member and e.errandStatus=3")
	List<Errand> findMyReq(Member member);
	
	@Query("select e from Errand e where e.writer=:member and e.errandStatus!=3 and e.errandStatus!=2")
	List<Errand> findAllMyReq(Member member);

	void update(Errand updateErrand);


}