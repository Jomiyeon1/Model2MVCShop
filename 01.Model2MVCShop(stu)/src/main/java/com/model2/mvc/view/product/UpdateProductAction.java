package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;


public class UpdateProductAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		
		String prodNo = request.getParameter("prodNo");
		int prod_no = Integer.parseInt(prodNo);
		System.out.println("UudateProductActon prodNo => "+prodNo);
		
		ProductVO productVO = new ProductVO(); 
		
		//productVO.setProdNo(prod_no);
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdDetail(request.getParameter("prodDetail"));
		productVO.setManuDate(request.getParameter("manuDate"));
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		productVO.setFileName(request.getParameter("fileName"));
		
		System.out.println("productVO " + productVO);
		
		ProductService service=new ProductServiceImpl();
		service.updateProduct(productVO);
		
		HttpSession session=request.getSession();
		int sessionNo = ((ProductVO)session.getAttribute("prodNo")).getProdNo();
	
		if(String.valueOf(sessionNo).equals(prodNo)){
			session.setAttribute("product", productVO);
		}
		
		return "redirect:/getProduct.do?productNo="+prod_no;
	}
}