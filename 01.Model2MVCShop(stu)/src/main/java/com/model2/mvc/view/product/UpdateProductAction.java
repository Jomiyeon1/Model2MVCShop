package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;


public class UpdateProductAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {

		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		System.out.println("updateProductAction ����, productVO  => "+prodNo);
		
		ProductVO productVO = new ProductVO(); 
		
		productVO.setProdNo(prodNo);
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdDetail(request.getParameter("prodDetail"));
		productVO.setManuDate(request.getParameter("manuDate").replace("-", ""));
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		productVO.setFileName(request.getParameter("fileName"));
		
		System.out.println("updateProductAction setting ��, productVO " + productVO);
		
		ProductService service=new ProductServiceImpl();
		service.updateProduct(productVO);
		
		

		return "redirect:/getProduct.do?prodNo="+prodNo;
	}
}