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
	// 마이페이지 - 요청 내역 조회
	/*SELECT e.CREATED_AT, e.TITLE, e.CATEGORY, a.MEMBER_ID, e.COMPLETED_AT, e.writer, a.MATCH_STATUS 
	FROM APPLY a 
	JOIN ERRAND e 
	on a.ERRAND_ID = e.ERRAND_ID 
	WHERE e.WRITER =멤버아이디  AND a.MATCH_STATUS =1;*/
	// 마이페이지 - 요청 내역 조회		
//	@Query("select a from Apply a where a.errand.memberId=:member")
//	List<Apply> findMyReq(Member member);
	
	// 마이페이지 - 수행 내역 조회
	@Query("select a from Apply a where a.applicant=:member and a.matchStatus=1")
	List<Apply> findMyCompletion(Member member);
	

	@Query("select a from Apply a where a.applicant=:member and a.matchStatus!=3")
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
