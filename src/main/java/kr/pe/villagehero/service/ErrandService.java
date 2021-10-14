package kr.pe.villagehero.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.villagehero.dao.ErrandRepository;
import kr.pe.villagehero.dao.MemberRepository;
import kr.pe.villagehero.dto.ErrandDTO;
import kr.pe.villagehero.entity.Errand;
import kr.pe.villagehero.entity.Member;

@Service
public class ErrandService {

	@Autowired
	private ErrandRepository dao;
	
	@Autowired
	private MemberRepository memberDAO;

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

	public String insertErrand(long id, ErrandDTO newErrand) {
		System.out.println("심부름 요청 등록시도");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
		Date time = new Date();
	
		Member writer = memberDAO.findById(id).get() ; // session에서 받아와야함
    	int pay = newErrand.getPay();
    	String createdAt = dateFormat.format(time);
    	String title = newErrand.getTitle();
    	String content = newErrand.getContent();
    	String category = newErrand.getCategory();
    	String reqLocation = newErrand.getReqLocation();
    	String reqDate = newErrand.getReqDate();
    	char errandStatus = '0';

    	Errand errand = new Errand(writer, pay, createdAt, title, content, category, reqLocation, reqDate, errandStatus);

		dao.save(errand);
		return "심부름 요청 저장 성공";
	}

	// 존재하는 모든 심부름을 가격순(내림차순)으로 return
	public List<ErrandDTO> getAllErrandsPayDes() {
		List<ErrandDTO> all = new ArrayList<ErrandDTO>();
		List<Errand> all2 = (List<Errand>) dao.findAll();

		all2.forEach(v -> all.add(new ErrandDTO(v)));
		all.sort(new PayComparator());
		return all;
	}

	public void updateErrandStatus() {
		// TODO Auto-generated method stub
		
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