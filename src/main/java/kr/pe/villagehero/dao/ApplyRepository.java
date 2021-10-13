package kr.pe.villagehero.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import kr.pe.villagehero.entity.Apply;
import kr.pe.villagehero.entity.Member;

public interface ApplyRepository extends CrudRepository<Apply, Long>{
	
	
	List<Apply> findByApplicant(Member member);
	
}
