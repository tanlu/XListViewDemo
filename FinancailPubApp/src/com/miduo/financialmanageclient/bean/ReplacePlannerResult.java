package com.miduo.financialmanageclient.bean;

import java.io.Serializable;

public class ReplacePlannerResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int state;
	private String msg;
	private UserInfo data;
	

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public UserInfo getData() {
		return data;
	}

	public void setData(UserInfo data) {
		this.data = data;
	}

}
