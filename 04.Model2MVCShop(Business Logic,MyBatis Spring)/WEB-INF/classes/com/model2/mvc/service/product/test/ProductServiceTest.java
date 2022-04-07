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
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
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

		//==> console Ȯ��
		//System.out.println(user);
		
		//==> API Ȯ��
//		Assert.assertEquals("testProductName", product.getProdName());
//		Assert.assertEquals("PROD_DETAIL", product.getProdDetail());
//		Assert.assertEquals("20220505", product.getManuDate());
//		Assert.assertEquals(1000, product.getPrice());
//		Assert.assertEquals("IMAGE_FILE", product.getFileName());
	}
	//@Test
	public void testGetProduct() throws Exception {
		
		Product product = new Product();
		//==> �ʿ��ϴٸ�...
//		user.setUserId("testUserId");
//		user.setUserName("testUserName");
//		user.setPassword("testPasswd");
//		user.setSsn("1111112222222");
//		user.setPhone("111-2222-3333");
//		user.setAddr("��⵵");
//		user.setEmail("test@test.com");
		
		
		product = productService.getProduct(10001);
		//System.out.println("Test product ===>"+ product);

		//==> console Ȯ��
		//System.out.println(user);
		
		//==> API Ȯ��
		Assert.assertEquals("������", product.getProdName());


		Assert.assertNotNull(productService.getProduct(10001));
	}
	
}