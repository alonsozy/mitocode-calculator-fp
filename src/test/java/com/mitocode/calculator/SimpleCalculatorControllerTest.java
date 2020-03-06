package com.mitocode.calculator;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@WebMvcTest(SimpleCalculatorController.class)
public class SimpleCalculatorControllerTest {
	
	@Autowired
    private MockMvc mvc;
	
	private String urlHello;
	private String urlBadHello;
	private String urlAdd;
	private String urlSub;
	private String urlMul;
	private String urlDiv;
	
	@Before
	public void setUp() {
		urlHello = "/api/whoami";
		urlBadHello = "/whoami";
		urlAdd = "/api/add/num1/num2";
		urlSub = "/api/sub/num1/num2";
		urlMul = "/api/mul/num1/num2";
		urlDiv = "/api/div/num1/num2";
	}
	
	// ENDPOINT: /api/whoami
	@Test
	public void test_whoami1() throws Exception {
	     
		String hostUnk= String.format("Hi %s", System.getenv().getOrDefault("HOSTNAME", "Unknown"));	
		mvc.perform( get(urlHello)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().string(hostUnk));
	}
	
	@Test
	public void test_whoami2() throws Exception {
	     
		String hostUnk= String.format("Hi %s", System.getenv().getOrDefault("HOSTNAME", "Unknown"));
		MvcResult result = mvc.perform( get(urlHello)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andReturn();
		
		String content = result.getResponse().getContentAsString();
		assertTrue(content.equals(hostUnk));
	}
	
	@Test
	public void test_whoami_bad_method() throws Exception {
	     	
		mvc.perform( post(urlHello)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isMethodNotAllowed())
			.andDo(print());
	}
	
	@Test
	public void test_not_found() throws Exception {
	    
		mvc.perform( get(urlBadHello)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
	}
	
	
	// ENDPOINT: /api/add/num1/num2
	@Test
	public void test_add_with_Integers() throws Exception {
	    Integer num1=5,num2=6;
	    String response= String.format("%d + %d = %d", num1, num2, (num1 + num2));
	    
		urlAdd=urlAdd.replaceAll("num1", num1.toString()).replaceAll("num2", num2.toString());
		mvc.perform(
				get(urlAdd)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andExpect(status().isOk())
		.andExpect(content().string(response))
		/*.andDo(print())*/;
	}
	
	@Test
	public void test_add_with_string() throws Exception {
	    
		urlAdd=urlAdd.replaceAll("num1", "a").replaceAll("num2", "b");
		mvc.perform(
				get(urlAdd)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andExpect(status().isBadRequest())
		/*.andDo(print())*/;
	}
	
	
	// ENDPOINT: /api/sub/num1/num2
	
	@Test
	public void test_sub_with_Integers() throws Exception {
	    Integer num1=5,num2=6;
	    String response= String.format("%d - %d = %d", num1, num2, (num1 - num2));
	    
	    urlSub=urlSub.replaceAll("num1", num1.toString()).replaceAll("num2", num2.toString());
		mvc.perform(
				get(urlSub)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andExpect(status().isOk())
		.andExpect(content().string(response))
		/*.andDo(print())*/;
	}
	
	@Test
	public void test_sub_with_string() throws Exception {
	    
		urlAdd=urlAdd.replaceAll("num1", "a").replaceAll("num2", "b");
		mvc.perform(
				get(urlSub)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andExpect(status().isBadRequest())
		/*.andDo(print())*/;
	}
	
	
	
	// ENDPOINT: /api/mul/num1/num2
	
	@Test
	public void test_mul_with_Integers() throws Exception {
	    Integer num1=5,num2=6;
	    String response= String.format("%d x %d = %d", num1, num2, (num1 * num2));
	    
	    urlMul=urlMul.replaceAll("num1", num1.toString()).replaceAll("num2", num2.toString());
		mvc.perform(
				get(urlMul)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andExpect(status().isOk())
		.andExpect(content().string(response))
		/*.andDo(print())*/;
	}
	
	@Test
	public void test_mul_with_string() throws Exception {
	    
		urlMul=urlMul.replaceAll("num1", "a").replaceAll("num2", "b");
		mvc.perform(
				get(urlMul)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andExpect(status().isBadRequest())
		/*.andDo(print())*/;
	}
	
	
	
	// ENDPOINT: /api/div/num1/num2
	
	@Test
	public void test_div_with_Integers() throws Exception {
	    Integer num1=50,num2=2;
	    String response= String.format("%d / %d = %d", num1, num2, (num1 / num2));
	    
	    urlDiv=urlDiv.replaceAll("num1", num1.toString()).replaceAll("num2", num2.toString());
		mvc.perform(
				get(urlDiv)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andExpect(status().isOk())
		.andExpect(content().string(response))
		/*.andDo(print())*/;
	}
	
	@Test
	public void test_div_with_string() throws Exception {
	    
		urlDiv=urlDiv.replaceAll("num1", "a").replaceAll("num2", "b");
		mvc.perform(
				get(urlDiv)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andExpect(status().isBadRequest())
		/*.andDo(print())*/;
	}
	
//	@Test
//	public void test_div_with_zero() throws Exception {
//		Integer num1=50,num2=0;
//	    
//	    urlDiv=urlDiv.replaceAll("num1", num1.toString()).replaceAll("num2", num2.toString());
//		mvc.perform(
//				get(urlDiv)
//				.contentType(MediaType.APPLICATION_JSON)
//				)
//		//.andExpect(  )
//		.andDo(print())
//		  .andExpect(status().is(500));
//		
//	}
	
}
