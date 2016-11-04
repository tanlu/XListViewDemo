package com.miduo.financialmanageclient.bean;

public class BankInfo {
	private Integer payBankid;
	private Integer bankId;
	private String bankName;
	private String largeIco;
	private String smallIco;
	private String description;
	private Double simpleMaxPrice;
	private Double dayMaxPrice;
	private Double monthMaxprice;
	public Integer getPayBankid() {
		return payBankid;
	}
	public void setPayBankid(Integer payBankid) {
		this.payBankid = payBankid;
	}
	public Integer getBankId() {
		return bankId;
	}
	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getLargeIco() {
		return largeIco;
	}
	public void setLargeIco(String largeIco) {
		this.largeIco = largeIco;
	}
	public String getSmallIco() {
		return smallIco;
	}
	public void setSmallIco(String smallIco) {
		this.smallIco = smallIco;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getSimpleMaxPrice() {
		return simpleMaxPrice;
	}
	public void setSimpleMaxPrice(Double simpleMaxPrice) {
		this.simpleMaxPrice = simpleMaxPrice;
	}
	public Double getDayMaxPrice() {
		return dayMaxPrice;
	}
	public void setDayMaxPrice(Double dayMaxPrice) {
		this.dayMaxPrice = dayMaxPrice;
	}
	public Double getMonthMaxprice() {
		return monthMaxprice;
	}
	public void setMonthMaxprice(Double monthMaxprice) {
		this.monthMaxprice = monthMaxprice;
	}
	
}
