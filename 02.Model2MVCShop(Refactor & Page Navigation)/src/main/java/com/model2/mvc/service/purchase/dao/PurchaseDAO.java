package com.model2.mvc.service.purchase.dao;

// *** 수정 중
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;

public class PurchaseDAO {

	public PurchaseDAO() {
		// TODO Auto-generated constructor stub
	}

	public Purchase findPurchase(int tranNo) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "SELECT * FROM transaction WHERE tran_no = ?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, tranNo);

		ResultSet rs = stmt.executeQuery();

		Purchase purchase = new Purchase();
		User user = new User();
		Product product = new Product();

		// System.out.println("=======");
		while (rs.next()) {

			product.setProdNo(rs.getInt("prod_no"));
			user.setUserId(rs.getString("buyer_id"));
			System.out.println("User, Product");

			purchase.setTranNo(rs.getInt("TRAN_NO"));
			System.out.println("tranNo");
			// object
			purchase.setPurchaseProd(product);
			purchase.setBuyer(user);
			// object
			purchase.setPaymentOption(rs.getString("PAYMENT_OPTION"));
			purchase.setReceiverName(rs.getString("RECEIVER_NAME"));
			purchase.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
			purchase.setDivyAddr(rs.getString("DEMAILADDR"));
			purchase.setDivyRequest(rs.getString("DLVY_REQUEST"));
			purchase.setTranCode(rs.getString("TRAN_STATUS_CODE"));
			purchase.setOrderDate(rs.getDate("ORDER_DATE"));
			purchase.setDivyDate(rs.getString("DLVY_DATE"));

		}

		con.close();

		return purchase;
	}
	//////////////////////// insert

	public void insertPurchase(Purchase purchase) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "insert into transaction values(seq_transaction_tran_no.NEXTVAL,?, ?, ?, ?, ?, ?, ?, ?,to_date(SYSDATE, 'YYYY/MM/DD HH24:MI:SS'),to_date('2012/01/14 10:48:43', 'YYYY/MM/DD HH24:MI:SS'))";

		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setInt(1, purchase.getPurchaseProd().getProdNo());
		stmt.setString(2, purchase.getBuyer().getUserId());
		stmt.setString(3, purchase.getPaymentOption());
		stmt.setString(4, purchase.getReceiverName());
		stmt.setString(5, purchase.getReceiverPhone());
		stmt.setString(6, purchase.getDivyAddr());
		stmt.setString(7, purchase.getDivyRequest());
		stmt.setInt(8, purchase.getTranNo());

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
	public Map<String, Object> getPurchaseList(Search search, String buyer) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Connection con = DBUtil.getConnection();

		String sql = "select\n"
				+ "prod_no, tran_no, buyer_id, receiver_name, receiver_phone, tran_status_code \n"
				+ "from transaction \n" 
				+ "where buyer_id = ? \n"
				+ "ORDER BY tran_no DESC"; // 쿼리 ok
//		if (search.getSearchCondition() != null) {
//			if (search.getSearchCondition().equals("0")) {
//				sql += " where PROD_NO='" + search.getSearchKeyword()
//						+ "'";
//			} else if (search.getSearchCondition().equals("1")) {
//				sql += " where PROD_NAME='" + search.getSearchKeyword()
//						+ "'";
//			}
//		}
//		sql += " order by PROD_NO";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, buyer);
		//System.out.println("listPurchase buyer => " + buyer);
		ResultSet rs = pStmt.executeQuery();
		
		System.out.println("PurchaseDAO::Original SQL :: " + sql);

		// ==> TotalCount GET
		int totalCount = this.getTotalCount(sql);
		System.out.println("ProdcutDAO :: totalCount  :: " + totalCount);

		// ==> CurrentPage 게시물만 받도록 Query 다시구성
		sql = makeCurrentPageSql(sql, search);
		
		ArrayList<Purchase> list = new ArrayList<Purchase>();
		while (rs.next()) {
		Purchase purchase = new Purchase();
		User user = new User();
		Product product = new Product();

		product.setProdNo(rs.getInt("prod_no"));
		user.setUserId(rs.getString("buyer_id"));

		// 수정 중..

		purchase.setTranNo(rs.getInt("TRAN_NO"));
		// object
		purchase.setPurchaseProd(product);
		purchase.setBuyer(user);
		// object
		purchase.setReceiverName(rs.getString("RECEIVER_NAME"));
		purchase.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
		purchase.setTranCode(rs.getString("TRAN_STATUS_CODE"));

		list.add(purchase);
		

	}

	// ==> totalCount 정보 저장
	map.put("totalCount",new Integer(totalCount));
	// ==> currentPage 의 게시물 정보 갖는 List 저장
	map.put("list",list);
	System.out.println("PurchaseDAO list => "+list);

	rs.close();
	pStmt.close();
	con.close();

	return map;

	}

//////////////////////////////////////////////////////////////////////////
	private int getTotalCount(String sql) throws Exception {

		sql = "SELECT COUNT(*) " + "FROM ( " + sql + ") countTable";
		
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		

		int totalCount = 0;
		if (rs.next()) {
			totalCount = rs.getInt(1);
		}

		pStmt.close();
		con.close();
		rs.close();

		return totalCount;
	}
///////////////////////////////////////////////////////
	// 게시판 currentPage Row 만 return
	private String makeCurrentPageSql(String sql, Search search) {
		sql = "SELECT * " + "FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " + " 	FROM (	" + sql
				+ " ) inner_table " + "	WHERE ROWNUM <=" + search.getCurrentPage() * search.getPageSize() + " ) "
				+ "WHERE row_seq BETWEEN " + ((search.getCurrentPage() - 1) * search.getPageSize() + 1) + " AND "
				+ search.getCurrentPage() * search.getPageSize();

		System.out.println("PurchaseDAO :: make SQL :: " + sql);

		return sql;
	}

	public void updatePurchase(Purchase purchase) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "UPDATE transaction set\n"
				+ "payment_option = ?, receiver_name = ?, receiver_phone = ?, demailaddr = ?, dlvy_request = ?, dlvy_date = ?\n"
				+ "WHERE tran_no = ?";

		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setString(1, purchase.getPaymentOption());
		System.out.println(purchase.getPaymentOption());
		stmt.setString(2, purchase.getReceiverName());
		stmt.setString(3, purchase.getReceiverPhone());
		stmt.setString(4, purchase.getDivyAddr());
		stmt.setString(5, purchase.getDivyRequest());
		stmt.setString(6, purchase.getDivyDate().replace("-", ""));
		stmt.setInt(7, purchase.getTranNo());

		stmt.executeUpdate();

		con.close();

	}

	public void updateTranCode(Purchase purchase) throws Exception {
		Connection con = DBUtil.getConnection();

		String sql = "UPDATE transaction SET tran_status_code = ? WHERE tran_no = ?";
//				if(purchase.getTranCode().trim().equals("0")) {
//					sql += "tran_status_code = 1 \n" ; 
//				}else if(purchase.getTranCode().trim().equals("1")) {
//					 sql += "tran_status_code = 2 \n" ;
//				}
//					sql += "WHERE tran_no = ?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, purchase.getTranCode());
		stmt.setInt(2, purchase.getTranNo());

		stmt.executeUpdate();

		con.close();

	}

	// SaleList

}
