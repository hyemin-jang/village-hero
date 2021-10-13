package kr.pe.villagehero.dao;

import org.springframework.data.repository.CrudRepository;

import kr.pe.villagehero.entity.Errand;

public interface ErrandRepository extends CrudRepository<Errand, Long>{
	
}