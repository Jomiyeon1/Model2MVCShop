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

public class AddPurchaseViewAction extends Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ProductVO productvo = new ProductVO();
		int tranNo = productvo.getProdNo();

		PurchaseService service = new PurchaseServiceImpl();
		PurchaseVO purchaseVO = service.getPurchase(tranNo);
		System.out.println("addPurchaseActionView purchaseVO:" + purchaseVO);

		request.setAttribute("purchaseVO", purchaseVO);
		System.out.println("addPurchaseViewAction, purchaseVO " + purchaseVO);

		////////////////////////////
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		ProductService service1 = new ProductServiceImpl();
		ProductVO productVO = service1.getProduct(prodNo);
		System.out.println("updateactionView productVO:"+productVO);
		
		request.setAttribute("productVO", productVO);
		////////////////////////////////////////
		return "forward:/purchase/addPurchaseView.jsp";
	}
}
