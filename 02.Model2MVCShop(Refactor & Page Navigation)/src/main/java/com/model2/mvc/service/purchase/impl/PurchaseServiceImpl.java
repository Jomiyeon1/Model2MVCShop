package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;
import com.model2.mvc.service.domain.Purchase;

public class PurchaseServiceImpl implements PurchaseService {

	private ProductDAO productDAO;
	private PurchaseDAO purchaseDAO;
	
	public PurchaseServiceImpl() {
		productDAO = new ProductDAO();
		purchaseDAO = new PurchaseDAO();
	}

	
	public void addPurchase(Purchase purchase) throws Exception {
		purchaseDAO.insertPurchase(purchase);
	}

	
	public Purchase getPurchase(int tranNo) throws Exception {		
		return purchaseDAO.findPurchase(tranNo);
	}

	
	public Map<String, Object> getPurchaseList(Search search, String buyer) throws Exception {		
		return purchaseDAO.getPurchaseList(search, buyer);
	}

	
	public HashMap<String, Object> getSaleList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void updatePurchase(Purchase purchase) throws Exception {
		purchaseDAO.updatePurchase(purchase);
	}

	
	public void updateTranCode(Purchase purchase) throws Exception {
		purchaseDAO.updateTranCode(purchase);
		
	}
	
	

}
