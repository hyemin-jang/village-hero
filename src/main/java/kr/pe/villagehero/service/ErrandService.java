package kr.pe.villagehero.service;

import java.security.cert.PKIXRevocationChecker.Option;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.villagehero.dao.ErrandRepository;
import kr.pe.villagehero.dto.ErrandDTO;
import kr.pe.villagehero.entity.Errand;

@Service
public class ErrandService {

	@Autowired
	private ErrandRepository dao;

	// 존재하는 모든 심부름을 DTO객체로 return
	public List<ErrandDTO> getAllErrands() {
		List<ErrandDTO> errandList = new ArrayList<ErrandDTO>();
		List<Errand> errands = (List<Errand>) dao.findAll();

		errands.forEach(v -> errandList.add(new ErrandDTO(v)));

		return errandList;
	}

	public ErrandDTO getOneErrand(long id) {
		ErrandDTO errand = new ErrandDTO(dao.findById(id).get());		
		return errand;
	}

	public String insertErrand(Errand errand) {
		System.out.println("심부름 요청 등록시도");

		dao.save(errand);
		return "성공";

	}

	// 존재하는 모든 심부름을 가격순(내림차순)으로 return
	public List<ErrandDTO> getAllErrandsPayDes() {
		List<ErrandDTO> all = new ArrayList<ErrandDTO>();
		List<Errand> all2 = (List<Errand>) dao.findAll();

		all2.forEach(v -> all.add(new ErrandDTO(v)));
		all.sort(new PayComparator());
		return all;
	}
	
	//writer 값으로 심부름 필터링
	public List<ErrandDTO> getErrandsById(List<Long> ids){
		List<Errand> all2 = (List<Errand>)dao.findAllById(ids);
		List<ErrandDTO> all = new ArrayList<>();
		all2.forEach(v -> all.add(new ErrandDTO(v)));
		return all;
	}
}

class PayComparator implements Comparator<ErrandDTO> {
	@Override
	public int compare(ErrandDTO a, ErrandDTO b) {
		if (a.getPay() > b.getPay())
			return -1;
		if (a.getPay() < b.getPay())
			return 1;
		return 0;
	}
}
