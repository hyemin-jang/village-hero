package kr.pe.villagehero.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import kr.pe.villagehero.dto.MyPageDTO;
import kr.pe.villagehero.entity.Errand;
import kr.pe.villagehero.entity.Member;

public interface ErrandRepository extends CrudRepository<Errand, Long>{
	
	// 마이페이지 - 내가 요청한 최종완료된 심부름 내역
	/*
	 * SELECT e.CREATED_AT, e.TITLE, e.CATEGORY, e.COMPLETED_AT, e.writer, e.ERRAND_STATUS
		FROM errand e
		WHERE e.WRITER =3 AND e.errand_STATUS =3;
	 */
	@Query("select e from Errand e where e.writer=:member and e.errandStatus=3")
	List<Errand> findMyReq(Member member);
	
}