package kr.pe.villagehero;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import kr.pe.villagehero.controller.MemberController;

@SpringBootTest
class MidProjectApplicationTests {

	@Autowired
	private MemberController memberController;
	private MockMvc mock;
	
	@BeforeEach
	public void init() {
		mock = MockMvcBuilders.standaloneSetup(memberController).build();  
	}
	
	@Test
	void contextLoads() {
		try {
			mock.perform(get("/test").param("id", "1")).andExpect(status().isOk()).andDo(print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
