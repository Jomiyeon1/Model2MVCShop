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
		
		
		ProductVO productVO = new ProductVO();
		int tranNo = productVO.getProdNo();
		// int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		// productVO.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
		PurchaseVO purchaseVO = new PurchaseVO();
		

		System.out.println("addProductAction => " + purchaseVO);

		PurchaseService service = new PurchaseServiceImpl();
		service.addPurchase(purchaseVO);

		request.setAttribute("purchaseVO", purchaseVO);

		return "forward:/purchase/addPurchaseView.jsp";
	}

}
