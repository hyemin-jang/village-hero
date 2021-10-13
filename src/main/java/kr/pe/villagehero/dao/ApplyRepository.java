package kr.pe.villagehero.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import kr.pe.villagehero.entity.Apply;
import kr.pe.villagehero.entity.Member;

public interface ApplyRepository extends CrudRepository<Apply, Long>{
	
	List<Apply> findByApplicant(Member applicant);	
	
}
