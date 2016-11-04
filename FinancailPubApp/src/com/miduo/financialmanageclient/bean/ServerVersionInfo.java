package com.miduo.financialmanageclient.bean;

public class ServerVersionInfo {
	private Integer state;
	private TblMobileVersion data;
	private String msg;

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public TblMobileVersion getData() {
		return data;
	}

	public void setData(TblMobileVersion data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
}
