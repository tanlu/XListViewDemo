package com.miduo.financialmanageclient.bean;

import java.io.Serializable;

public class NBankRateResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * data :
	 * {"callRate":"0.008","commercialRate":"0.01","expertRates":{"data":[
	 * {"amount"
	 * :0,"commission":0.078,"endPrice":0,"id":27,"productId":125,"startPrice"
	 * :0,
	 * "state":1}],"msg":"操作成功！","state":1},"productPeriod":"19","redeemDates"
	 * :10} state : 1
	 */

	private NRateBean data;
	private int state;
	private String msg;
    

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setData(NRateBean data) {
		this.data = data;
	}

	public void setState(int state) {
		this.state = state;
	}

	public NRateBean getData() {
		return data;
	}

	public int getState() {
		return state;
	}

}
