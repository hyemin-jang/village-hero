package kr.pe.villagehero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.villagehero.dao.MemberRepository;
import kr.pe.villagehero.entity.Member;

@RestController
public class Controller {

	public Controller() {
		System.out.println("controller----");
	}
	
	@Autowired
	private MemberRepository dao;
	
	@GetMapping("searchAll")
	public List<Member> m1(){
		Iterable<Member> all = dao.findAll();
		List<Member> all2 = (List<Member>)all;
		System.out.println("성공?");
		return all2;
	}
}
