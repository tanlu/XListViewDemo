package com.miduo.financialmanageclient.bean;

import java.io.Serializable;

public class NRateBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * callRate : 0.008 commercialRate : 0.01 expertRates :
	 * {"data":[{"amount":0,
	 * "commission":0.078,"endPrice":0,"id":27,"productId":125
	 * ,"startPrice":0,"state":1}],"msg":"操作成功！","state":1} productPeriod : 19
	 * redeemDates : 10
	 */

	private String callRate;
	private String commercialRate;
	private ExpertRatesEntity expertRates;
	private String productPeriod;
	private int redeemDates;
	private Integer dates;

	public Integer getDates() {
		return dates;
	}

	public void setDates(Integer dates) {
		this.dates = dates;
	}

	public void setCallRate(String callRate) {
		this.callRate = callRate;
	}

	public void setCommercialRate(String commercialRate) {
		this.commercialRate = commercialRate;
	}

	public void setExpertRates(ExpertRatesEntity expertRates) {
		this.expertRates = expertRates;
	}

	public void setProductPeriod(String productPeriod) {
		this.productPeriod = productPeriod;
	}

	public void setRedeemDates(int redeemDates) {
		this.redeemDates = redeemDates;
	}

	public String getCallRate() {
		return callRate;
	}

	public String getCommercialRate() {
		return commercialRate;
	}

	public ExpertRatesEntity getExpertRates() {
		return expertRates;
	}

	public String getProductPeriod() {
		return productPeriod;
	}

	public int getRedeemDates() {
		return redeemDates;
	}
}