package com.model2.mvc.service.product;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

public interface ProductDao {
	
	public Product getProduct(int prodNo) throws Exception;
	
	public void addProduct(Product product) throws Exception;
	
	public Map<String, Object> getProductList(Search search) throws Exception;
	
	public int getTotalCount(String sql) throws Exception;
	
	public String makeCurrentPageSql(String sql, Search search);
	
	public void updateProduct(Product product) throws Exception;
	
	

}
