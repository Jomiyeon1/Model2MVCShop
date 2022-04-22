package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

public class AddPurchaseViewAction extends Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int prodNo = Integer.parseInt(request.getParameter("prod_no"));
		System.out.println("AddPurchaseViewAction ½ÃÀÛ, productVO  => "+prodNo);
		
		ProductService service=new ProductServiceImpl();
		ProductVO vo = service.getProduct(prodNo);
		
		request.setAttribute("vo", vo);
		System.out.println("AddPurchaseViewAction = > " + vo );
		
		////////////////////////////////////////
		return "forward:/purchase/addPurchaseView.jsp";
	}
}
