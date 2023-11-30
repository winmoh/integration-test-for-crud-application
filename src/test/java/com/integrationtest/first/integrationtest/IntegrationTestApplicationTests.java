package com.integrationtest.first.integrationtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integrationtest.first.integrationtest.Model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;


@SpringBootTest(webEnvironment =SpringBootTest.WebEnvironment.RANDOM_PORT )
@ActiveProfiles("test")
@AutoConfigureMockMvc
class IntegrationTestApplicationTests {

	@LocalServerPort
	private int port;
	@Autowired
	private MockMvc mvc;
	private static String baseUrl="http://localhost:";
	private static RestTemplate restTemplate;

	@Autowired
	private h2TestRepository HTRepo;

	@BeforeAll
	public  static void init(){
		restTemplate=new RestTemplate();

	}

	@BeforeEach
	public void setUp(){
		baseUrl=baseUrl.concat(this.port+"").concat("/api/products");

	}

	//integration test for adding product using restTepmlate (need for starting a server)
	@Test
	public void addProductTest(){
		Product prd=new Product("short",543,199);
		Product product=restTemplate.postForObject(baseUrl.concat("/addProduct"),prd,Product.class);

		assertEquals("short",prd.getName());
		assertEquals(1,HTRepo.findAll().size());



	}


	//integration test  for adding product process using MockMvc
	@Test
	public void addProductTest2() throws Exception {
		ObjectMapper objMp=new ObjectMapper();
		Product prd=Product.builder().name("casquette")
				.quantity(1000)
				.price(40).build();
		MvcResult res=mvc.perform(post("/api/products/addProduct")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objMp.writeValueAsString(prd)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name",is("casquette")))
				.andExpect(jsonPath("$.quantity",is(1000)))
				.andExpect(jsonPath("$.price",is(40.0)))
				.andReturn();

		System.out.println(res.getResponse());


	}
}
