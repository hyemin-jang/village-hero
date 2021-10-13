package kr.pe.villagehero.dao;

import org.springframework.data.repository.CrudRepository;

import kr.pe.villagehero.dto.MemberDTO;
import kr.pe.villagehero.entity.Member;

public interface MemberRepository extends CrudRepository<Member, Long>{

	Member findByEmail(String email);	
}
