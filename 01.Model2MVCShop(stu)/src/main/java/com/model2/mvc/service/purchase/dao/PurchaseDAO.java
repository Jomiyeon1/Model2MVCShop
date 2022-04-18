package com.model2.mvc.service.purchase.dao;

// *** 수정 중
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class PurchaseDAO {

	public PurchaseDAO() {
		// TODO Auto-generated constructor stub
	}
	
	// 수정 중..
	public PurchaseVO findPurchase(int tranNo) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "SELECT * FROM transaction WHERE tran_no = ?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, tranNo);

		ResultSet rs = stmt.executeQuery();

		PurchaseVO purchaseVO = new PurchaseVO();
		UserVO userVO = new UserVO();
		ProductVO productVO = new ProductVO();
		
		//System.out.println("=======");
		while (rs.next()) {
			
			productVO.setProdNo(rs.getInt("prod_no"));
			userVO.setUserId(rs.getString("buyer_id"));
			System.out.println("UserVO, ProductVO");
			
			purchaseVO.setTranNo(rs.getInt("TRAN_NO"));
			System.out.println("tranNo");
			// object 
			purchaseVO.setPurchaseProd(productVO);
			purchaseVO.setBuyer(userVO);
			// object
			purchaseVO.setPaymentOption(rs.getString("PAYMENT_OPTION"));
			purchaseVO.setReceiverName(rs.getString("RECEIVER_NAME"));
			purchaseVO.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
			purchaseVO.setDivyAddr(rs.getString("DEMAILADDR"));
			purchaseVO.setDivyRequest(rs.getString("DLVY_REQUEST"));
			purchaseVO.setTranCode(rs.getString("TRAN_STATUS_CODE"));
			purchaseVO.setOrderDate(rs.getDate("ORDER_DATE"));
			purchaseVO.setDivyDate(rs.getString("DLVY_DATE"));
			
		}
		
		con.close();

		return purchaseVO;
	}
	////////////////////////insert
	
		public void insertPurchase(PurchaseVO purchaseVO) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "insert into transaction values(seq_transaction_tran_no.NEXTVAL,?, ?, ?, ?, ?, ?, ?, ?,to_date(SYSDATE, 'YYYY/MM/DD HH24:MI:SS'),to_date('2012/01/14 10:48:43', 'YYYY/MM/DD HH24:MI:SS'))";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setInt(1, purchaseVO.getPurchaseProd().getProdNo());
		stmt.setString(2, purchaseVO.getBuyer().getUserId());
		stmt.setString(3, purchaseVO.getPaymentOption());
		stmt.setString(4, purchaseVO.getReceiverName());
		stmt.setString(5, purchaseVO.getReceiverPhone());
		stmt.setString(6, purchaseVO.getDivyAddr());
		stmt.setString(7, purchaseVO.getDivyRequest());
		stmt.setInt(8, purchaseVO.getTranNo());
				
//		PROD_NO
//		BUYER_ID 
//		PAYMENT_OPTION
//		RECEIVER_NAME 
//		RECEIVER_PHONE
//		DEMAILADDR
//		DLVY_REQUEST
//		TRAN_STATUS_CODE
		
		stmt.executeUpdate();
		
		con.close();
	}

		// OK
	public HashMap<String,Object> getPurchaseList(SearchVO searchVO, String buyer) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "select\n"
				+ "t.prod_no, t.tran_no, u.user_id, t.RECEIVER_NAME, t.receiver_phone, t.TRAN_STATUS_CODE\n"
				+ "from users u, transaction t, product p\n"
				+ "where u.user_id = t.buyer_id\n"
				+ "AND p.prod_no = t.prod_no\n"
				+ "AND t.buyer_id = ?"; // 쿼리 ok
//		if (searchVO.getSearchCondition() != null) {
//			if (searchVO.getSearchCondition().equals("0")) {
//				sql += " where PROD_NO='" + searchVO.getSearchKeyword()
//						+ "'";
//			} else if (searchVO.getSearchCondition().equals("1")) {
//				sql += " where PROD_NAME='" + searchVO.getSearchKeyword()
//						+ "'";
//			}
//		}
//		sql += " order by PROD_NO";

		PreparedStatement stmt = 
			con.prepareStatement(	sql,
														ResultSet.TYPE_SCROLL_INSENSITIVE,
														ResultSet.CONCUR_UPDATABLE);
		stmt.setString(1, buyer);
		ResultSet rs = stmt.executeQuery();

		rs.last();
		int total = rs.getRow();
		System.out.println("로우의 수:" + total);

		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("count", new Integer(total));

		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());
		
		/////
		System.out.println("============================");
		System.out.println("============================");
		ArrayList<PurchaseVO> list = new ArrayList<PurchaseVO>();
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				PurchaseVO purchaseVO = new PurchaseVO();
				UserVO userVO = new UserVO();
				ProductVO productVO = new ProductVO();
				
				productVO.setProdNo(rs.getInt("prod_no"));
				userVO.setUserId(rs.getString("user_id"));
				
				//수정 중..
				
				purchaseVO.setTranNo(rs.getInt("TRAN_NO"));
				// object 
				purchaseVO.setPurchaseProd(productVO);
				purchaseVO.setBuyer(userVO);
				// object
				purchaseVO.setReceiverName(rs.getString("RECEIVER_NAME"));
				purchaseVO.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
				purchaseVO.setTranCode(rs.getString("TRAN_STATUS_CODE"));

				list.add(purchaseVO);
				if (!rs.next())
					break;
			}
		}
		System.out.println("list.size() : "+ list.size());
		map.put("list", list);
		//System.out.println("PurchaseDAO list = > " + list);
		System.out.println("map().size() : "+ map.size());

		con.close();
			
		return map;
	}

	public void updatePurchase(PurchaseVO purchaseVO) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "update product set PROD_NAME=?,PROD_DETAIL=?,MANUFACTURE_DAY=?,PRICE=?,IMAGE_FILE=? where PROD_NO=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		//stmt.setInt(1, productVO.getProdNo());
//		stmt.setString(1, productVO.getProdName());
//		stmt.setString(2, productVO.getProdDetail());
//		stmt.setString(3, productVO.getManuDate());
//		stmt.setInt(4, productVO.getPrice());
//		stmt.setString(5, productVO.getFileName());		
//		stmt.setInt(6, productVO.getProdNo());
		
		stmt.executeUpdate();
		
		con.close();
	}
	
	//SaleList
	//updateTranCode

}
