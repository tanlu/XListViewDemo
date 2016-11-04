package com.miduo.financialmanageclient.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

import org.apache.http.entity.SerializableEntity;

public class TranferBean_Data implements Serializable {

	public BankMapEntity getBankMap() {
		return bankMap;
	}

	@Override
	public String toString() {
		return "TranferBean_Data [productId=" + productId + ", productTitle=" + productTitle + ", productRate="
				+ productRate + ", holdPrice=" + holdPrice + ", buyerHoldDays=" + buyerHoldDays + ", buyerTotalIn="
				+ buyerTotalIn + ", salerPrice=" + salerPrice + ", salerRate=" + salerRate + ", buyerRate=" + buyerRate
				+ ", sPrice=" + sPrice + ", ePrice=" + ePrice + ", redeemDate=" + redeemDate + ", bankcardId="
				+ bankcardId + ", userBankCardValidSize=" + userBankCardValidSize + ", startInterestDate="
				+ startInterestDate + ", mobile=" + mobile + "]";
	}

	private int productId; // 产品id
	private String productTitle;// 产品名称
	private String productRate;// 预期年化收益率
	private String holdPrice;// 项目价值
	private String buyerHoldDays;// 剩余期限
	private String buyerTotalIn;// 剩餘利息
	private String salerPrice;// 转让价格
	private String salerRate;// 我的收益率
	private String buyerRate;// 对方收益率
	private String sPrice;// 建议最低价格
	private String ePrice;// 建议最高价格
	private String redeemDate;// 最近赎回日
	private String bankcardId;// 銀行卡id
	private int userBankCardValidSize;// 用户银行卡张数
	private String startInterestDate;
	private String mobile;
	private BankMapEntity bankMap;
	// 转让的价格
	private Double turnMoney;

	public Double getTurnMoney() {
		return turnMoney;
	}

	public void setTurnMoney(Double turnMoney) {
		this.turnMoney = turnMoney;
	}

	public void setBankMap(BankMapEntity bankMap) {
		this.bankMap = bankMap;
	}

	public String getBankcardId() {
		return bankcardId;
	}

	public void setBankcardId(String bankcardId) {
		this.bankcardId = bankcardId;
	}

	public int getUserBankCardValidSize() {
		return userBankCardValidSize;
	}

	public void setUserBankCardValidSize(int userBankCardValidSize) {
		this.userBankCardValidSize = userBankCardValidSize;
	}

	public String getStartInterestDate() {
		return startInterestDate;
	}

	public void setStartInterestDate(String startInterestDate) {
		this.startInterestDate = startInterestDate;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public String getProductRate() {
		return productRate;
	}

	public void setProductRate(String productRate) {
		this.productRate = productRate;
	}

	public String getHoldPrice() {
		return holdPrice;
	}

	public void setHoldPrice(String holdPrice) {
		this.holdPrice = holdPrice;
	}

	public String getBuyerHoldDays() {
		return buyerHoldDays;
	}

	public void setBuyerHoldDays(String buyerHoldDays) {
		this.buyerHoldDays = buyerHoldDays;
	}

	public String getBuyerTotalIn() {
		return buyerTotalIn;
	}

	public void setBuyerTotalIn(String buyerTotalIn) {
		this.buyerTotalIn = buyerTotalIn;
	}

	public String getSalerPrice() {
		return salerPrice;
	}

	public void setSalerPrice(String salerPrice) {
		this.salerPrice = salerPrice;
	}

	public String getSalerRate() {
		return salerRate;
	}

	public void setSalerRate(String salerRate) {
		this.salerRate = salerRate;
	}

	public String getBuyerRate() {
		return buyerRate;
	}

	public void setBuyerRate(String buyerRate) {
		this.buyerRate = buyerRate;
	}

	public String getsPrice() {
		return sPrice;
	}

	public void setsPrice(String sPrice) {
		this.sPrice = sPrice;
	}

	public String getePrice() {
		return ePrice;
	}

	public void setePrice(String ePrice) {
		this.ePrice = ePrice;
	}

	public String getRedeemDate() {
		return redeemDate;
	}

	public void setRedeemDate(String redeemDate) {
		this.redeemDate = redeemDate;
	}

}
