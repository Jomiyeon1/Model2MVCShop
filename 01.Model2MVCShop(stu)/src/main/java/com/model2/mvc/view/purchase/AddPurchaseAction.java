package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class AddPurchaseAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		System.out.println("AddpurchaseAction ½ÃÀÛ, productVO  => "+prodNo);
		
		ProductVO productVO = new ProductVO(); 	
		productVO.setProdNo(prodNo);
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdDetail(request.getParameter("prodDetail"));
		productVO.setManuDate(request.getParameter("manuDate"));
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		productVO.setFileName(request.getParameter("fileName"));
		
		
		
		ProductService service=new ProductServiceImpl();
		service.updateProduct(productVO);
		
		
		System.out.println("AddpurchaseAction setting ÈÄ, productVO " + productVO);

		return "redirect:/addPurchaseView.do?prod_no="+prodNo;

	}

}
