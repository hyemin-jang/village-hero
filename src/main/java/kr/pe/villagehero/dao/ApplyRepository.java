package kr.pe.villagehero.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import kr.pe.villagehero.entity.Apply;
import kr.pe.villagehero.entity.Errand;
import kr.pe.villagehero.entity.Member;

public interface ApplyRepository extends CrudRepository<Apply, Long>{
		
	// 마이페이지 - 수행 내역 조회
	@Query("select a from Apply a where a.applicant=:member and a.matchStatus=1")
	List<Apply> findMyCompletion(Member member);
	
	// 내 심부름 - 다른사람에게 매칭됐거나 취소된 경우 제외
	@Query("select a from Apply a where a.applicant=:member and a.matchStatus!=2 and a.matchStatus!=3")
	List<Apply> findMyApply(Member member);
	
	// 심부름 상세페이지에서 모든 지원 내역 출력
	List<Apply> findByErrand(Errand e);

	@Transactional
	@Modifying
	@Query(value="update Apply a set a.matchStatus=1 where a.errand=:e and a.applicant=:m", nativeQuery=false)
	void updateMyApplyStatus(Errand e, Member m);

	@Transactional
	@Modifying
	@Query(value="update Apply a set a.matchStatus=2 where a.errand=:e and a.applicant!=:m", nativeQuery=false)
	void updateOtherApplyStatus(Errand e, Member m);
	
	
	@Query("select a from Apply a where a.applicant=:member and a.errand=:errand and a.matchStatus=0")
	Apply findCancelApply(Member member,Errand errand);
	
}
