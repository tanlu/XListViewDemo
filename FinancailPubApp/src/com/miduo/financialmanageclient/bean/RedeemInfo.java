package com.miduo.financialmanageclient.bean;

import java.io.Serializable;
import java.util.List;

public class RedeemInfo implements Serializable{
	private boolean batch;
	private List<RedeemItemDetail> list;
	private int bankCount;
	public boolean isBatch() {
		return batch;
	}
	public void setBatch(boolean batch) {
		this.batch = batch;
	}
	public List<RedeemItemDetail> getList() {
		return list;
	}
	public void setList(List<RedeemItemDetail> list) {
		this.list = list;
	}
	public int getBankCount() {
		return bankCount;
	}
	public void setBankCount(int bankCount) {
		this.bankCount = bankCount;
	}
	
}
