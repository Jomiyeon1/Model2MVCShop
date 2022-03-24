package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;


public class GetProductAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		String prod_no=request.getParameter("prod_no");
		
		ProductService service=new ProductServiceImpl();
		ProductVO vo=service.getProduct(Integer.parseInt(prod_no));
		
		request.setAttribute("vo", vo);

		return "forward:/user/readUser.jsp";
	}
}