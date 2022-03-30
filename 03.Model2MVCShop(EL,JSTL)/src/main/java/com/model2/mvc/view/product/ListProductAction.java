package com.model2.mvc.view.product;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;


public class ListProductAction extends Action {

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
		
		// Business logic 수행
		ProductService productService = new ProductServiceImpl();
		Map<String , Object> map = productService.getProductList(search);
		
		Page resultPage	= 
					new Page( currentPage, ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		//System.out.println("ListProdctAction resultPage ::"+resultPage);
		
		// Model 과 View 연결
		request.setAttribute("list", map.get("list"));
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("search", search);
		//System.out.println("ListProductAction list => " + map);
		//System.out.println("ListProductAction resultPage => " + resultPage);
		//System.out.println("ListProductAction search => " + search);
		
		return "forward:/product/listProduct.jsp";
	}
}