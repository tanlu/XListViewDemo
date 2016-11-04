package com.miduo.financialmanageclient.bean;

import java.io.Serializable;
/**
 * 获取最新剩余额度
 * @author huozhenpeng
 *
 */
public class NSurplusResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int state;
	private double data;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public double getData() {
		return data;
	}

	public void setData(double data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "NSurplusResult [state=" + state + ", data=" + data + "]";
	}

}
