package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.ProductDao;



/*
 *	FileName :  UserServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Test
	public void testAddProduct() throws Exception {
		
		Product product = new Product();
		product.setProdName("123testProductName");
		product.setProdDetail("PROD_DETAIL");
		product.setManuDate("20220505");
		product.setPrice(1000);
		product.setFileName("IMAGE_FILE");
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println(product);
		
		productService.addProduct(product);
		
		
		//product = productService.getProduct(10008);

		//==> console 확인
		//System.out.println(user);
		
		//==> API 확인
//		Assert.assertEquals("testProductName", product.getProdName());
//		Assert.assertEquals("PROD_DETAIL", product.getProdDetail());
//		Assert.assertEquals("20220505", product.getManuDate());
//		Assert.assertEquals(1000, product.getPrice());
//		Assert.assertEquals("IMAGE_FILE", product.getFileName());
	}
	//@Test
	public void testGetProduct() throws Exception {
		
		Product product = new Product();
		//==> 필요하다면...
//		user.setUserId("testUserId");
//		user.setUserName("testUserName");
//		user.setPassword("testPasswd");
//		user.setSsn("1111112222222");
//		user.setPhone("111-2222-3333");
//		user.setAddr("경기도");
//		user.setEmail("test@test.com");
		
		
		product = productService.getProduct(10001);
		//System.out.println("Test product ===>"+ product);

		//==> console 확인
		//System.out.println(user);
		
		//==> API 확인
		Assert.assertEquals("자전거", product.getProdName());


		Assert.assertNotNull(productService.getProduct(10001));
	}
	
}