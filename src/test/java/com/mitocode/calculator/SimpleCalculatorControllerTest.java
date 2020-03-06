package com.mitocode.calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@WebMvcTest(SimpleCalculatorController.class)
public class SimpleCalculatorControllerTest {
	
	@Autowired
    private MockMvc mvc;
	
	//@MockBean
    //private EmployeeService service;
	
	@Test
	public void test_whoami1() throws Exception {
	     
		String hostUnk= new String("Hi Unknown");
		mvc.perform( get("/api/whoami")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().string(hostUnk));
	}
	
	@Test
	public void test_whoami2() throws Exception {
	     
		String hostUnk= new String("Hi Unknown");
		// no se mockean las peticiones 
		MvcResult result = mvc.perform( get("/api/whoami")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andReturn();
		
		String content = result.getResponse().getContentAsString();
		assertTrue(content.equals(hostUnk));
	}
	
}
