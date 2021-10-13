package kr.pe.villagehero.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import kr.pe.villagehero.dao.MemberRepository;
import kr.pe.villagehero.dto.ErrandDTO;
import kr.pe.villagehero.entity.Errand;
import kr.pe.villagehero.entity.Member;
import kr.pe.villagehero.service.ErrandService;


@RestController
public class ErrandController {

	@Autowired
	private ErrandService service;	

	@Autowired
	private MemberRepository memberDAO;

	@PostMapping("/errand")
    public String insertErrand(Errand errand) {
		SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
		Date time = new Date();
	
		Member writer = memberDAO.findById(1L).get() ; // session에서 받아와야함
    	int pay = errand.getPay();
    	String createdAt = dateFormat.format(time);
    	String title = errand.getTitle();
    	String content = errand.getContent();
    	String category = errand.getCategory();
    	String reqLocation = errand.getReqLocation();
    	String reqDate = errand.getReqDate();
    	char errandStatus = '0';

    	Errand newErrand = new Errand(writer, pay, createdAt, title, content, category, reqLocation, reqDate, errandStatus);

    	System.out.println(newErrand.getCreatedAt());
        service.insertErrand(newErrand);
        return "저장성공";
    }


	//json객체 배열로 errand 테이블의 모든 값
	@GetMapping("errands")
	public List<ErrandDTO> getAllErrands() {
		return service.getAllErrands();		
	}
	
	@GetMapping("errand")
	public ErrandDTO getOneErrand(long id) {
		return service.getOneErrand(id);
	}
	
	@GetMapping("payerrands")
	public List<ErrandDTO> getAllErrandsPayDesc(){
		List<ErrandDTO> all = service.getAllErrandsPayDes();
		
		return all;
	}

}

