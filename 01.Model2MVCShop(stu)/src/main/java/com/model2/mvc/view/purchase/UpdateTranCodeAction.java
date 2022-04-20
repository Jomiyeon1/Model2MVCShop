package com.model2.mvc.view.purchase;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class UpdateTranCodeAction extends Action {

	public UpdateTranCodeAction() {
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		SearchVO searchVO=new SearchVO();
		
		int page=1;
		if(request.getParameter("page") != null)
			page=Integer.parseInt(request.getParameter("page"));
		
		searchVO.setPage(page);
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		
		String pageUnit=getServletContext().getInitParameter("pageSize");
		searchVO.setPageUnit(Integer.parseInt(pageUnit));
		
		
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		
		HttpSession session = request.getSession();
		UserVO user = (UserVO)session.getAttribute("user");
		String buyer = user.getUserId();
		
		System.out.println("GetPurchaseAction 시작, tranNo  => "+tranNo);
		PurchaseService service = new PurchaseServiceImpl();
		PurchaseVO vo = service.getPurchase(tranNo);
		System.out.println("TranCodeAction vo => " + vo);
		
		///////////////////////////////
		// DAO updateTranCode 구현 후 다시만들어야함.
		/////////////////////////////////
		
		if(vo.getTranCode().trim().equals("0") || vo.getTranCode().trim().equals("1")) {
			vo.setTranCode("2");
		}
		
		PurchaseService service2 = new PurchaseServiceImpl();
		HashMap<String,Object> map = service2.getPurchaseList(searchVO, buyer);
		
		request.setAttribute("map", map);
		request.setAttribute("searchVO", searchVO);
		
		System.out.println("TranCodeAction.java map => " + map);
		
		return "forward:/purchase/listPurchase.jsp";
	}

}
