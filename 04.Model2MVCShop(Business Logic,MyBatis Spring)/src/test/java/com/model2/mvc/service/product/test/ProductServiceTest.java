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
import com.model2.mvc.service.domain.User;
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

	// ==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	// @Test
	public void testAddProduct() throws Exception {

		Product product = new Product();
		product.setProdName("test1ProductName");
		product.setProdDetail("PROD_DETAIL");
		product.setManuDate("20220505");
		product.setPrice(1000);
		product.setFileName("IMAGE_FILE");
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println(product);

		productService.addProduct(product);

		product = productService.getProduct(10000);

		// ==> console 확인
		System.out.println("***************" + product);

		// ==> API 확인
		Assert.assertEquals("test1ProductName", product.getProdName());
		Assert.assertEquals("PROD_DETAIL", product.getProdDetail());
		Assert.assertEquals("20220505", product.getManuDate());
		Assert.assertEquals(1000, product.getPrice());
		Assert.assertEquals("IMAGE_FILE", product.getFileName());
	}

	// @Test
	public void testGetProduct() throws Exception {

		Product product = new Product();
		// ==> 필요하다면...
//		user.setUserId("testUserId");
//		user.setUserName("testUserName");
//		user.setPassword("testPasswd");
//		user.setSsn("1111112222222");
//		user.setPhone("111-2222-3333");
//		user.setAddr("경기도");
//		user.setEmail("test@test.com");

		product = productService.getProduct(10001);
		// System.out.println("Test product ===>"+ product);

		// ==> console 확인
		// System.out.println(product);

		// ==> API 확인
		Assert.assertEquals("자전거", product.getProdName());

		Assert.assertNotNull(productService.getProduct(10001));
	}

	// @Test
	public void testUpdateProduct() throws Exception {

		Product product = productService.getProduct(10001);
		Assert.assertNotNull(product);

		Assert.assertEquals("자전거", product.getProdName());
		Assert.assertEquals("자전거 좋아요~", product.getProdDetail());
		Assert.assertEquals(10000, product.getPrice());
		Assert.assertEquals("AHlbAAAAvetFNwAA.jpg", product.getFileName());

		product.setPrice(20000);
		product.setProdDetail("라이딩 1회");

		productService.updateProduct(product);

		product = productService.getProduct(10001);
		Assert.assertNotNull(product);

		// ==> console 확인
		System.out.println("*************" + product);

		// ==> API 확인
		Assert.assertEquals(20000, product.getPrice());
		Assert.assertEquals("라이딩 1회", product.getProdDetail());

	}

	//@Test
	public void testGetProductListAll() throws Exception {

		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		Map<String, Object> map = productService.getProductList(search);

		List<Object> list = (List<Object>) map.get("list");
		Assert.assertEquals(3, list.size());

		// ==> console 확인
		// System.out.println(list);

		Integer totalCount = (Integer) map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");

		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("0");
		search.setSearchKeyword("");
		map = productService.getProductList(search);

		list = (List<Object>) map.get("list");
		Assert.assertEquals(3, list.size());

		// ==> console 확인
		 System.out.println(list);

		totalCount = (Integer) map.get("totalCount");
		System.out.println("*******" + totalCount);
	}

	//@Test
	public void testGetProductListByProdNo() throws Exception {

		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("0");
		search.setSearchKeyword("10000");
		Map<String, Object> map = productService.getProductList(search);

		List<Object> list = (List<Object>) map.get("list");
		Assert.assertEquals(1, list.size());

		// ==> console 확인
		// System.out.println(list);

		Integer totalCount = (Integer) map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");

		search.setSearchCondition("0");
		search.setSearchKeyword("" + System.currentTimeMillis());
		map = productService.getProductList(search);

		list = (List<Object>) map.get("list");
		Assert.assertEquals(0, list.size());
		
		
		// ==> console 확인
		 System.out.println("********"+list);

		totalCount = (Integer) map.get("totalCount");
		System.out.println("******" + totalCount);
	}
	
		//@Test
		 public void testGetProductListByProductName() throws Exception{
			 
		 	Search search = new Search();
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	search.setSearchCondition("1");
		 	search.setSearchKeyword("자전거");
		 	Map<String,Object> map = productService.getProductList(search);
		 	
		 	List<Object> list = (List<Object>)map.get("list");
		 	Assert.assertEquals(1, list.size());
		 	
			//==> console 확인
		 	System.out.println(list);
		 	
		 	Integer totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 	
		 	System.out.println("=======================================");
		 	
		 	search.setSearchCondition("1");
		 	search.setSearchKeyword(""+System.currentTimeMillis());
		 	map = productService.getProductList(search);
		 	
		 	list = (List<Object>)map.get("list");
		 	Assert.assertEquals(0, list.size());
		 	
			//==> console 확인
		 	System.out.println(list);
		 	
		 	totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 }
		 
		@Test
		 public void testGetProductListByProductPrice() throws Exception{
			 
		 	Search search = new Search();
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	search.setSearchCondition("2");
		 	search.setSearchKeyword("20000");
		 	Map<String,Object> map = productService.getProductList(search);
		 	
		 	List<Object> list = (List<Object>)map.get("list");
		 	Assert.assertEquals(1, list.size());
		 	
			//==> console 확인
		 	System.out.println("1. ==> "+list);
		 	
		 	Integer totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 	
		 	System.out.println("=======================================");
		 	
		 	search.setSearchCondition("1");
		 	search.setSearchKeyword(""+System.currentTimeMillis());
		 	map = productService.getProductList(search);
		 	
		 	list = (List<Object>)map.get("list");
		 	Assert.assertEquals(0, list.size());
		 	
			//==> console 확인
		 	System.out.println("2. ==>"+list);
		 	
		 	totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 }
		 

}