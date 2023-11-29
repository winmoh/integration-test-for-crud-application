package com.integrationtest.first.integrationtest;

import com.integrationtest.first.integrationtest.Model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootTest(webEnvironment =SpringBootTest.WebEnvironment.RANDOM_PORT )
@ActiveProfiles("test")
class IntegrationTestApplicationTests {

	@LocalServerPort
	private int port;
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
	@Test
	public void addProductTest(){
		Product prd=new Product("short",543,199);
		Product product=restTemplate.postForObject(baseUrl.concat("/addProduct"),prd,Product.class);
		Product prd2=new Product("servette",1000,646);
		Product prrr=HTRepo.save(prd2);
		List<Product> products= HTRepo.findAll();
		System.out.println("products        dddddddddddddddddddddd"+products);


	}

}
