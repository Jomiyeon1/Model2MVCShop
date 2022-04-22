package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.compiler.JspRuntimeContext;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class AddPurchaseAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		//int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		//System.out.println("AddpurchaseAction ½ÃÀÛ, productVO  => "+prodNo);
		
		ProductVO productVO = new ProductVO(); 	
		productVO.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
		
		//String userId = request.getParameter("buyerId");
		UserVO userVO = new UserVO();
		userVO.setUserId(request.getParameter("buyerId"));
		
		PurchaseVO purchaseVO = new PurchaseVO();
		purchaseVO.setBuyer(userVO);
		purchaseVO.setPurchaseProd(productVO);
		purchaseVO.setPaymentOption(request.getParameter("paymentOption"));
		purchaseVO.setReceiverName(request.getParameter("receiverName"));
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone"));
		purchaseVO.setDivyAddr(request.getParameter("receiverAddr"));
		purchaseVO.setDivyRequest(request.getParameter("receiverRequest"));
		purchaseVO.setDivyDate(request.getParameter("receiverDate"));
		
		PurchaseService service = new PurchaseServiceImpl();
		service.addPurchase(purchaseVO);
		
		System.out.println("AddpurchaseAction setting ÈÄ, purchaseVO " + purchaseVO);
		
		request.setAttribute("purchaseVO", purchaseVO);

		return "forward:/purchase/addPurchase.jsp";

	}

}
