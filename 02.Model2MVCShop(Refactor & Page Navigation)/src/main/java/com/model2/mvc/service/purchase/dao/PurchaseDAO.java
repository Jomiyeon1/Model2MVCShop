package com.model2.mvc.service.purchase.dao;

// *** 수정 중
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.domain.User;

public class PurchaseDAO {

	public PurchaseDAO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public PurchaseVO findPurchase(int tranNo) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "SELECT * FROM transaction WHERE tran_no = ?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, tranNo);

		ResultSet rs = stmt.executeQuery();

		PurchaseVO purchaseVO = new PurchaseVO();
		User userVO = new User();
		Product productVO = new Product();
		
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
	public HashMap<String,Object> getPurchaseList(Search search, String buyer) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "select\n"
				+ "t.prod_no, t.tran_no, u.user_id, t.RECEIVER_NAME, t.receiver_phone, t.TRAN_STATUS_CODE\n"
				+ "from users u, transaction t, product p\n"
				+ "where u.user_id = t.buyer_id\n"
				+ "AND p.prod_no = t.prod_no\n"
				+ "AND t.buyer_id = ?\n"
				+ "ORDER BY TRAN_NO DESC"; // 쿼리 ok
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

		rs.absolute(search.getPage() * search.getPageUnit() - search.getPageUnit()+1);
		System.out.println("searchVO.getPage():" + search.getPage());
		System.out.println("searchVO.getPageUnit():" + search.getPageUnit());
		
		/////
		System.out.println("============================");
		System.out.println("============================");
		ArrayList<PurchaseVO> list = new ArrayList<PurchaseVO>();
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				PurchaseVO purchaseVO = new PurchaseVO();
				User userVO = new User();
				Product productVO = new Product();
				
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

		String sql = "UPDATE transaction set\n"
					+ "payment_option = ?, receiver_name = ?, receiver_phone = ?, demailaddr = ?, dlvy_request = ?, dlvy_date = ?\n"
					+ "WHERE tran_no = ?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, purchaseVO.getPaymentOption());
		System.out.println(purchaseVO.getPaymentOption());
		stmt.setString(2, purchaseVO.getReceiverName());
		stmt.setString(3, purchaseVO.getReceiverPhone());
		stmt.setString(4, purchaseVO.getDivyAddr());
		stmt.setString(5, purchaseVO.getDivyRequest());
		stmt.setString(6, purchaseVO.getDivyDate().replace("-", ""));
		stmt.setInt(7, purchaseVO.getTranNo());
		
		stmt.executeUpdate();
		
		con.close();
		

	}
	
	
	//updateTranCode => 수정중
	public void updateTranCode(PurchaseVO purchaseVO)throws Exception {
		Connection con = DBUtil.getConnection();
		
		String sql = "UPDATE transaction SET tran_status_code = ? WHERE tran_no = ?";
//				if(purchaseVO.getTranCode().trim().equals("0")) {
//					sql += "tran_status_code = 1 \n" ; 
//				}else if(purchaseVO.getTranCode().trim().equals("1")) {
//					 sql += "tran_status_code = 2 \n" ;
//				}
//					sql += "WHERE tran_no = ?";
					
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, purchaseVO.getTranCode());
		stmt.setInt(2, purchaseVO.getTranNo());
		
		stmt.executeUpdate();
		
		con.close();
		
	}
	
	//SaleList

}
