package com.miduo.financialmanageclient.bean;

import java.io.Serializable;

/**
 * 如何投资页面返回数据（真实数据）
 * 
 * @author huozhenpeng
 * 
 */
public class NInvestmentResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer state;
	private String msg;
	private NInvestementBean data;

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public NInvestementBean getData() {
		return data;
	}

	public void setData(NInvestementBean data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "NInvestmentResult [state=" + state + ", msg=" + msg + ", data="
				+ data + "]";
	}

}
