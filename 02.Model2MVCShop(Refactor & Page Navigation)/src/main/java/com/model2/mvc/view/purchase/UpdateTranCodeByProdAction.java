package com.model2.mvc.view.purchase;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class UpdateTranCodeByProdAction extends Action {

	public UpdateTranCodeByProdAction() {
		// TODO Auto-generated constructor stub
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
		
		
		// web.xml  meta-data �� ���� ��� ���� 
		//�� �������� �������� �Խù���
		int pageSize = Integer.parseInt( getServletContext().getInitParameter("pageSize"));
		// �ϴ� ������ ��ȣ ȭ�鿡 �������� ��
		int pageUnit  =  Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		search.setPageSize(pageSize);
		////////////
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		Purchase purchase = new Purchase();
		purchase.setTranNo(prodNo);
		purchase.setTranCode(request.getParameter("tranCode"));
		System.out.println("updateTranCode_product prodNo => " + prodNo);
		System.out.println("updateTranCode_product tranCode => " + purchase.getTranCode());
		
		PurchaseService service = new PurchaseServiceImpl();
		service.updateTranCode(purchase);
		
		
		
		ProductService service2=new ProductServiceImpl();
		Map<String,Object> map = service2.getProductList(search);

		request.setAttribute("map", map);
		request.setAttribute("search", search);
		
		
		return "forward:/listProduct.do?menu=manage";
	}

}
