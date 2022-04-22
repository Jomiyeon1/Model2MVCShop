package com.model2.mvc.service.purchase.vo;

import java.sql.Date;

import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;



public class PurchaseVO {
	
	private User buyer;           // BUYER_ID  		==> 구매자 아이디
	private String divyAddr;        // DEMAILADDR   	==> 배송주소
	private String divyDate;        // DLVY_DATE		==> 배송희망일
	private String divyRequest;     //DLVY_REQUEST	    ==> 배송요청사항
	private Date orderDate;         // ORDER_DATE 		==>주문일
	private String paymentOption;   // PAYMENT_OPTIO    ==>결제방법
	private Product purchaseProd; // PROD_NO  		==> 상품번호
	private String receiverName; 	// RECEIVER_NAME 	==> 수령자 이름
	private String receiverPhone;   // RECEIVER_PHONE   ==> 수령자 전화번호
	private String tranCode;		// TRAN_STATUS_CODE ==> 배송상태
	private int tranNo; 			// TRAN_NO 			==>  주문번호
	
	public PurchaseVO(){
	}
	
	public User getBuyer() {
		return buyer;
	}
	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}
	public String getDivyAddr() {
		return divyAddr;
	}
	public void setDivyAddr(String divyAddr) {
		this.divyAddr = divyAddr;
	}
	public String getDivyDate() {
		return divyDate;
	}
	public void setDivyDate(String divyDate) {
		this.divyDate = divyDate;
	}
	public String getDivyRequest() {
		return divyRequest;
	}
	public void setDivyRequest(String divyRequest) {
		this.divyRequest = divyRequest;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getPaymentOption() {
		return paymentOption;
	}
	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}
	public Product getPurchaseProd() {
		return purchaseProd;
	}
	public void setPurchaseProd(Product purchaseProd) {
		this.purchaseProd = purchaseProd;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public String getTranCode() {
		return tranCode;
	}
	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}
	public int getTranNo() {
		return tranNo;
	}
	public void setTranNo(int tranNo) {
		this.tranNo = tranNo;
	}
	
	@Override
	public String toString() {
		return "PurchaseVO [buyer=" + buyer + ", divyAddr=" + divyAddr
				+ ", divyDate=" + divyDate + ", divyRequest=" + divyRequest
				+ ", orderDate=" + orderDate + ", paymentOption="
				+ paymentOption + ", purchaseProd=" + purchaseProd
				+ ", receiverName=" + receiverName + ", receiverPhone="
				+ receiverPhone + ", tranCode=" + tranCode + ", tranNo="
				+ tranNo + "]";
	}
}