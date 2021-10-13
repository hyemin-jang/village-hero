package kr.pe.villagehero.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.villagehero.dao.MemberRepository;
import kr.pe.villagehero.dto.ErrandDTO;
import kr.pe.villagehero.entity.Errand;
import kr.pe.villagehero.entity.Member;
import kr.pe.villagehero.service.ErrandService;


@RestController
//@RequestMapping("sessiontracking")  ?? 지워도되나여??
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
	public List<ErrandDTO> getAllErrandsPayDes(){
		List<ErrandDTO> all = service.getAllErrandsPayDes();
		
		return all;
	}
	
	@RequestMapping("/sessionTest2")
	public String m2(HttpSession session) {
		//1. 로그인폼에서 입력받은 id와 password를 db내에서 비교
		//2. db내에 존재한다면 로그인성공 -> 해당 인스턴스의 고유 회원 id 값을 추출.
		//3. 추출한 회원 id값을 session으로 저장.
		//4. session값으로 내가 등록한 심부름/내가 지원한 심부름 목록 불러오기 가능.
		session.setAttribute("id", "dd");
		System.out.println("m2() -- " + session.getAttribute("id"));
		return "redirect:/session.jsp";
	}

}

