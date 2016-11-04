package com.miduo.financialmanageclient.bean;

public class PerBarBean {
	private int whiteBar;// 白色柱状图数值
	private int blueBar;// 蓝色柱状图数值
	private int whiteHeight;// 根据比例计算实际显示的高度,在BarPicture中赋值
	private int blueHeight;//
	private String des;// 描述信息

	public PerBarBean(int whiteBar, int blueBar) {
		super();
		this.whiteBar = whiteBar;
		this.blueBar = blueBar;
	}

	public int getWhiteBar() {
		return whiteBar;
	}

	public void setWhiteBar(int whiteBar) {
		this.whiteBar = whiteBar;
	}

	public int getBlueBar() {
		return blueBar;
	}

	public void setBlueBar(int blueBar) {
		this.blueBar = blueBar;
	}

	public int getWhiteHeight() {
		return whiteHeight;
	}

	public void setWhiteHeight(int whiteHeight) {
		this.whiteHeight = whiteHeight;
	}

	public int getBlueHeight() {
		return blueHeight;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public void setBlueHeight(int blueHeight) {
		this.blueHeight = blueHeight;
	}

	public PerBarBean(int whiteBar, int blueBar, String des) {
		super();
		this.whiteBar = whiteBar;
		this.blueBar = blueBar;
		this.des = des;
	}

	@Override
	public String toString() {
		return "PerBarBean [whiteBar=" + whiteBar + ", blueBar=" + blueBar
				+ ", whiteHeight=" + whiteHeight + ", blueHeight=" + blueHeight
				+ ", des=" + des + "]";
	}

}