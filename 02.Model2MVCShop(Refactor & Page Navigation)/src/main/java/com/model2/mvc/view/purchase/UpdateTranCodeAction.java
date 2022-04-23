package com.model2.mvc.view.purchase;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;


public class UpdateTranCodeAction extends Action {

	public UpdateTranCodeAction() {
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Search search =new Search();
		
		int currentPage=1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		System.out.println("ListPriductAction currentPage => "+currentPage);
		search.setCurrentPage(currentPage);
		search.setSearchCondition(request.getParameter("searchCondition"));
		search.setSearchKeyword(request.getParameter("searchKeyword"));
		
		
		// web.xml  meta-data 로 부터 상수 추출 
		//한 페이지당 보여지는 게시물수
		int pageSize = Integer.parseInt( getServletContext().getInitParameter("pageSize"));
		// 하단 페이지 번호 화면에 보여지는 수
		int pageUnit  =  Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		search.setPageSize(pageSize);
		////////////
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));

		Purchase purchase = new Purchase();
		purchase.setTranNo(tranNo);
		purchase.setTranCode(request.getParameter("tranCode"));
		System.out.println("updateTranCode tran_no => " + tranNo);
		System.out.println("updateTranCode tran_no => " + purchase.getTranCode());

		PurchaseService service = new PurchaseServiceImpl();
		service.updateTranCode(purchase);
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String buyer = user.getUserId();
		
		PurchaseService service2 = new PurchaseServiceImpl();
		Map<String,Object> map = service2.getPurchaseList(search, buyer);
		
		request.setAttribute("map", map);
		request.setAttribute("searchVO", search);
		System.out.println("ListPurchaseAction.java map => " + map);

		return "forward:/purchase/listPurchase.jsp";
	}

}
