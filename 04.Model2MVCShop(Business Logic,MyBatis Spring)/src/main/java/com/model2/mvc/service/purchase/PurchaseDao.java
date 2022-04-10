package com.model2.mvc.service.purchase;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseDao {
	
	public Purchase getPurchase(int tranNo) throws Exception;
	
	public List<Purchase> getPurchaseList(Search search, String buyerId) throws Exception;
	
	public List<Purchase> getSaleList(Search search) throws Exception;
	
	public void insertPurchase(Purchase purchase) throws Exception;
	
	public void updatePurchase(Purchase purchase) throws Exception;
	
	//public void TranCode(Purchase purchase) throws Exception;

}
