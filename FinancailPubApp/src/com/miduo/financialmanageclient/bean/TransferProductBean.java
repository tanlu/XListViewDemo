package com.miduo.financialmanageclient.bean;

import java.io.Serializable;

public class TransferProductBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private int type;//1  2   3


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}
	
}
