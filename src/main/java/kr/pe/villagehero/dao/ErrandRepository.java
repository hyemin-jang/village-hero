package kr.pe.villagehero.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import kr.pe.villagehero.entity.Errand;
import kr.pe.villagehero.entity.Member;

public interface ErrandRepository extends CrudRepository<Errand, Long>{
	
	// 마이페이지 - 내 요청 내역 (완료된 심부름) 조회
	@Query("select e from Errand e where e.writer=:member and e.errandStatus=3")
	List<Errand> findMyReq(Member member);
	
	// 내 심부름 - 내 요청 내역 (진행중) 조회
	@Query("select e from Errand e where e.writer=:member and e.errandStatus!=3")
	List<Errand> findAllMyReq(Member member);

	// 모든 지원가능한 심부름 조회
	@Query("select e from Errand e where e.errandStatus=0 or e.errandStatus=1")
	List<Errand> findAllErrandsOngoing();
}