package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class UpdatePurchaseViewAction extends Action {

	public UpdatePurchaseViewAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		System.out.println("updatePurchaseViewAction tranNo => " + tranNo);
		
		PurchaseService service = new PurchaseServiceImpl();
		PurchaseVO purchaseVO = service.getPurchase(tranNo);
		
		request.setAttribute("purchaseVO", purchaseVO);
		System.out.println("UpdatePurchaseViewAction purchaseVO => " + purchaseVO);
		
		return "forward:/purchase/updatePurchase.jsp";
	}

}
