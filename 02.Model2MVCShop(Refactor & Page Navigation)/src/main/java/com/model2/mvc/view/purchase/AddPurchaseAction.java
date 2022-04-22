package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.compiler.JspRuntimeContext;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.Purchase;


public class AddPurchaseAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		//int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		//System.out.println("AddpurchaseAction ½ÃÀÛ, product  => "+prodNo);
		
		Product product = new Product(); 	
		product.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
		
		//String userId = request.getParameter("buyerId");
		User user = new User();
		user.setUserId(request.getParameter("buyerId"));
		
		Purchase purchase = new Purchase();
		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		purchase.setDivyAddr(request.getParameter("receiverAddr"));
		purchase.setDivyRequest(request.getParameter("receiverRequest"));
		purchase.setDivyDate(request.getParameter("receiverDate"));
		
		PurchaseService service = new PurchaseServiceImpl();
		service.addPurchase(purchase);
		
		System.out.println("AddpurchaseAction setting ÈÄ, purchase " + purchase);
		
		request.setAttribute("purchase", purchase);

		return "forward:/purchase/addPurchase.jsp";

	}

}
