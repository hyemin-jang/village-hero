package kr.pe.villagehero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.villagehero.service.ApplyService;

@RestController
public class ApplyController {

	@Autowired
	private ApplyService service;
	
}
