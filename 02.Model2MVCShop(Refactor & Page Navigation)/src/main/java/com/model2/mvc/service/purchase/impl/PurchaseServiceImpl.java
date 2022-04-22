package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;

import com.model2.mvc.common.Search;
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

	
	public void addPurchase(PurchaseVO purchaseVO) throws Exception {
		purchaseDAO.insertPurchase(purchaseVO);
	}

	
	public PurchaseVO getPurchase(int tranNo) throws Exception {		
		return purchaseDAO.findPurchase(tranNo);
	}

	
	public HashMap<String, Object> getPurchaseList(Search search, String buyer) throws Exception {		
		return purchaseDAO.getPurchaseList(search, buyer);
	}

	
	public HashMap<String, Object> getSaleList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void updatePurchase(PurchaseVO purchaseVO) throws Exception {
		purchaseDAO.updatePurchase(purchaseVO);
	}

	
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
		purchaseDAO.updateTranCode(purchaseVO);
		
	}
	
	

}
