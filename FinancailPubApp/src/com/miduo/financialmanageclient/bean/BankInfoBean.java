package com.miduo.financialmanageclient.bean;

import java.io.Serializable;

/**
 * 选择银行卡页面使用
 * 
 * @author huozhenpeng
 * 
 */
public class BankInfoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean isOpen;
	private String isSatisfy;

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public String getIsSatisfy() {
		return isSatisfy;
	}

	public void setIsSatisfy(String isSatisfy) {
		this.isSatisfy = isSatisfy;
	}

	@Override
	public String toString() {
		return "BankInfoBean [isOpen=" + isOpen + ", isSatisfy=" + isSatisfy
				+ "]";
	}

	public BankInfoBean(boolean isOpen, String isSatisfy) {
		super();
		this.isOpen = isOpen;
		this.isSatisfy = isSatisfy;
	}
	public BankInfoBean(String isSatisfy) {
		super();
		this.isSatisfy = isSatisfy;
	}

	

}
