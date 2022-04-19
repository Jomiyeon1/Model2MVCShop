package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class UpdatePurchaseAction extends Action {

	public UpdatePurchaseAction() {
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		
		//buyer
		UserVO userVO = new UserVO();
		userVO.setUserId(request.getParameter("buyerId"));
		
		PurchaseVO purchaseVO = new PurchaseVO();
		purchaseVO.setTranNo(tranNo);
		purchaseVO.setBuyer(userVO);
		purchaseVO.setPaymentOption("paymentOption");
		purchaseVO.setReceiverName("receiverName");
		purchaseVO.setReceiverPhone("receiverPhone");
		purchaseVO.setDivyAddr("receiverAddr");
		purchaseVO.setDivyRequest("receiverRequest");
		purchaseVO.setDivyDate("divyDate");
		
		System.out.println("updatePurchaseAction.java purchaseVO => " + purchaseVO);
		
		return "redirect:/getPurchase.do?tranNo="+tranNo;
	}

}
