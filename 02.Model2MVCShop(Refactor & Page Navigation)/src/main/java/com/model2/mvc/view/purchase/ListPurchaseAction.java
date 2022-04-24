package com.model2.mvc.view.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;



public class ListPurchaseAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
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
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		System.out.println("addPurchase.jsp UserVO => "+ user);
		

		
		String buyer = user.getUserId();
		System.out.println("ListPurchaseAction buyerId : " + buyer);
		
		
		PurchaseService service=new PurchaseServiceImpl();
		Map<String,Object> map = service.getPurchaseList(search, buyer);
		
		Page resultPage	= 
				new Page( currentPage, ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		

		request.setAttribute("list", map.get("list"));
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("search", search);
		System.out.println("ListPurchaseAction.java map => " + map);
		
		return "forward:/purchase/listPurchase.jsp";
	}
}