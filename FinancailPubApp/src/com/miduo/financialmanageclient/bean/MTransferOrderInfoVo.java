package com.miduo.financialmanageclient.bean;

import java.io.Serializable;

public class MTransferOrderInfoVo implements Serializable{
	private String paramName;
	private String paramValue;
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	

}
