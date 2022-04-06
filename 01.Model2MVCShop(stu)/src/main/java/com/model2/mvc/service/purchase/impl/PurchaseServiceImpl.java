package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class PurchaseServiceImpl implements PurchaseService {

	private ProductDAO productDAO;
	private PurchaseDAO purchaseDAO;
	
	public PurchaseServiceImpl() {
		productDAO = new ProductDAO();
		purchaseDAO = new PurchaseDAO();
	}

	
	public PurchaseVO addPurchase(PurchaseVO purchaseVO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	public PurchaseVO getPurchase(int tranNo) throws Exception {		
		return purchaseDAO.findPurchase(tranNo);
	}

	
	public HashMap<String, Object> getPurchaseList(SearchVO searchVO, String buyerId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	public HashMap<String, Object> getSaleList(SearchVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	public PurchaseVO updatePurchase(PurchaseVO purchaseVO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	

}