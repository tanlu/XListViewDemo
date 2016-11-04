package com.miduo.financialmanageclient.bean;

import java.io.Serializable;
/**
 * 投保结果页面
 * @author huozhenpeng
 *
 */
public class NInsureanceResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int state;
	private String msg;
	private NIsuranceBean data;
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
	public NIsuranceBean getData() {
		return data;
	}
	public void setData(NIsuranceBean data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "NInsureanceResult [state=" + state + ", msg=" + msg + ", data="
				+ data + "]";
	}
	


}
