package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;


public class UpdateProductAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {

		//System.out.println("updateProductAction prodNo => " + request.getParameter("prodNo"));
		
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		System.out.println("updateProductAction ????, productVO  => "+prodNo);
		
		Product product = new Product(); 	
		product.setProdNo(prodNo);
		product.setProdName(request.getParameter("prodName"));
		product.setProdDetail(request.getParameter("prodDetail"));
		product.setManuDate(request.getParameter("manuDate"));
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		product.setFileName(request.getParameter("fileName"));
		
		
		
		ProductService service=new ProductServiceImpl();
		service.updateProduct(product);
		
		
		System.out.println("updateProductAction setting ??, product " + product);

		return "redirect:/getProduct.do?prodNo="+prodNo;
	}
}