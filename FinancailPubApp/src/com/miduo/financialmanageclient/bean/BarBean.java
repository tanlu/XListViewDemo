package com.miduo.financialmanageclient.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 用真实数据构造成类似的bean对象
 * 
 * @author huozhenpeng
 * 
 */
public class BarBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private int total;// 数据的最大值，方便算比例
	private List<PerBarBean> datas;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<PerBarBean> getDatas() {
		return datas;
	}

	public void setDatas(List<PerBarBean> datas) {
		this.datas = datas;
	}

	@Override
	public String toString() {
		return "BarBean [total=" + total + ", datas=" + datas + "]";
	}

}
