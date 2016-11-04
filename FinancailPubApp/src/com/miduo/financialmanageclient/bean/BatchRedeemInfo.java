package com.miduo.financialmanageclient.bean;

import java.io.Serializable;

public class BatchRedeemInfo implements Serializable{
	private Integer assetId;
	private Integer bankId;
	public Integer getAssetId() {
		return assetId;
	}
	public void setAssetId(Integer assetId) {
		this.assetId = assetId;
	}
	public Integer getBankId() {
		return bankId;
	}
	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

}
