package kr.pe.villagehero.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import kr.pe.villagehero.entity.Member;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long>{
	
	Member findByEmail(String email);

	Member findByNickname(String newNickname);

	Member findByPhone(String newPhone);	
}
