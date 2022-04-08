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
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class ProductServiceTest {

	// ==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
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

		// ==> console Ȯ��
		System.out.println("***************" + product);

		// ==> API Ȯ��
		Assert.assertEquals("test1ProductName", product.getProdName());
		Assert.assertEquals("PROD_DETAIL", product.getProdDetail());
		Assert.assertEquals("20220505", product.getManuDate());
		Assert.assertEquals(1000, product.getPrice());
		Assert.assertEquals("IMAGE_FILE", product.getFileName());
	}

	// @Test
	public void testGetProduct() throws Exception {

		Product product = new Product();
		// ==> �ʿ��ϴٸ�...
//		user.setUserId("testUserId");
//		user.setUserName("testUserName");
//		user.setPassword("testPasswd");
//		user.setSsn("1111112222222");
//		user.setPhone("111-2222-3333");
//		user.setAddr("��⵵");
//		user.setEmail("test@test.com");

		product = productService.getProduct(10001);
		// System.out.println("Test product ===>"+ product);

		// ==> console Ȯ��
		// System.out.println(product);

		// ==> API Ȯ��
		Assert.assertEquals("������", product.getProdName());

		Assert.assertNotNull(productService.getProduct(10001));
	}

	// @Test
	public void testUpdateProduct() throws Exception {

		Product product = productService.getProduct(10001);
		Assert.assertNotNull(product);

		Assert.assertEquals("������", product.getProdName());
		Assert.assertEquals("������ ���ƿ�~", product.getProdDetail());
		Assert.assertEquals(10000, product.getPrice());
		Assert.assertEquals("AHlbAAAAvetFNwAA.jpg", product.getFileName());

		product.setPrice(20000);
		product.setProdDetail("���̵� 1ȸ");

		productService.updateProduct(product);

		product = productService.getProduct(10001);
		Assert.assertNotNull(product);

		// ==> console Ȯ��
		System.out.println("*************" + product);

		// ==> API Ȯ��
		Assert.assertEquals(20000, product.getPrice());
		Assert.assertEquals("���̵� 1ȸ", product.getProdDetail());

	}

	//@Test
	public void testGetProductListAll() throws Exception {

		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		Map<String, Object> map = productService.getProductList(search);

		List<Object> list = (List<Object>) map.get("list");
		Assert.assertEquals(3, list.size());

		// ==> console Ȯ��
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

		// ==> console Ȯ��
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

		// ==> console Ȯ��
		// System.out.println(list);

		Integer totalCount = (Integer) map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");

		search.setSearchCondition("0");
		search.setSearchKeyword("" + System.currentTimeMillis());
		map = productService.getProductList(search);

		list = (List<Object>) map.get("list");
		Assert.assertEquals(0, list.size());
		
		
		// ==> console Ȯ��
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
		 	search.setSearchKeyword("������");
		 	Map<String,Object> map = productService.getProductList(search);
		 	
		 	List<Object> list = (List<Object>)map.get("list");
		 	Assert.assertEquals(1, list.size());
		 	
			//==> console Ȯ��
		 	System.out.println(list);
		 	
		 	Integer totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 	
		 	System.out.println("=======================================");
		 	
		 	search.setSearchCondition("1");
		 	search.setSearchKeyword(""+System.currentTimeMillis());
		 	map = productService.getProductList(search);
		 	
		 	list = (List<Object>)map.get("list");
		 	Assert.assertEquals(0, list.size());
		 	
			//==> console Ȯ��
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
		 	
			//==> console Ȯ��
		 	System.out.println("1. ==> "+list);
		 	
		 	Integer totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 	
		 	System.out.println("=======================================");
		 	
		 	search.setSearchCondition("1");
		 	search.setSearchKeyword(""+System.currentTimeMillis());
		 	map = productService.getProductList(search);
		 	
		 	list = (List<Object>)map.get("list");
		 	Assert.assertEquals(0, list.size());
		 	
			//==> console Ȯ��
		 	System.out.println("2. ==>"+list);
		 	
		 	totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 }
		 

}