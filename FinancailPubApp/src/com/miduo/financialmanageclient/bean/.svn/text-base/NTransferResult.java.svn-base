package com.miduo.financialmanageclient.bean;

import java.io.Serializable;
import java.util.List;

public class NTransferResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	  /**
     * data : [{"id":"198","productId":"90","productName":"米多利6号","transNo":"ZR11448007842349","transferUserName":"","expertRate":"8.800000","remainTime":"334","grabRemainTime":"57469","discountType":"溢价：","discountPrice":"44848.1786","transferPrice":"55002.4800","transferDueTime":"2015-11-26 02:33:40","proList":[{"attrName":"预期收益率","attrValue":"8.8%"},{"attrName":"产品投向","attrValue":"精选资管计划"},{"attrName":"本金","attrValue":"60000.0000"},{"attrName":"产品期限","attrValue":"19个月"},{"attrName":"产品到期日","attrValue":"2016-10-24 00:00:00"},{"attrName":"剩余期限","attrValue":"334天"},{"attrName":"到期本息合计","attrValue":"107161.6400"}]}]
     * state : 1
     */

	private String msg;
    private int state;
    private List<TransferDataEntity> data;

    public void setState(int state) {
        this.state = state;
    }

    public void setData(List<TransferDataEntity> data) {
        this.data = data;
    }

    public int getState() {
        return state;
    }

    public List<TransferDataEntity> getData() {
        return data;
    }

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

    

   
}
