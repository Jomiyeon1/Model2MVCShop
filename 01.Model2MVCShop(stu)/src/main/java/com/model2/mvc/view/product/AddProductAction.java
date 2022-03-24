package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;


public class AddProductAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		ProductVO productVO = new ProductVO();

		productVO.setProdName(request.getParameter("PROD_NAME"));
		productVO.setProdDetail(request.getParameter("PROD_DETAIL"));
		productVO.setManuDate(request.getParameter("MANUFACTURE_DAY"));
		// price에서 nullpointerException
		productVO.setPrice(Integer.parseInt(request.getParameter("PRICE").trim()));
		productVO.setFileName(request.getParameter("IMAGE_FILE"));

		
		System.out.println(productVO);
		
		ProductService service=new ProductServiceImpl();
		service.addProduct(productVO);
		
		// ?? 맞나?? 알아보고 고치기.
		return "redirect:/index.jsp";
	}
}