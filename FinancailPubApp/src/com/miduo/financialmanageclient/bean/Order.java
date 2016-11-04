package com.miduo.financialmanageclient.bean;

public class Order {
	private boolean isSel;
	private Double amount;
	private String productName;
	public boolean isSel() {
		return isSel;
	}
	public void setSel(boolean isSel) {
		this.isSel = isSel;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	

}
