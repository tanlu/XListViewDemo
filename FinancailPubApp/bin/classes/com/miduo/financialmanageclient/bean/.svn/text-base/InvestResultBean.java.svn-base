package com.miduo.financialmanageclient.bean;

import java.io.Serializable;

/**
 * 如何投资结果页面的柱子所需bean
 * 
 * @author huozhenpeng
 * 
 */
public class InvestResultBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String descripion;// 柱子上面的描述
	private double actualData;// 柱子的数据
	private int actualHeight;// 柱子实际高度
	private int leftX, rightX, topY;// 记录左右上边距
	private String color;
	

	public InvestResultBean(String descripion, double actualData, String color) {
		super();
		this.descripion = descripion;
		this.actualData = actualData;
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescripion() {
		return descripion;
	}

	public void setDescripion(String descripion) {
		this.descripion = descripion;
	}

	public double getActualData() {
		return actualData;
	}

	public void setActualData(int actualData) {
		this.actualData = actualData;
	}

	public int getActualHeight() {
		return actualHeight;
	}

	public void setActualHeight(int actualHeight) {
		this.actualHeight = actualHeight;
	}

	public int getLeftX() {
		return leftX;
	}

	public void setLeftX(int leftX) {
		this.leftX = leftX;
	}

	public int getRightX() {
		return rightX;
	}

	public void setRightX(int rightX) {
		this.rightX = rightX;
	}

	public int getTopY() {
		return topY;
	}

	public void setTopY(int topY) {
		this.topY = topY;
	}

	@Override
	public String toString() {
		return "InvestResultBean [descripion=" + descripion + ", actualData="
				+ actualData + ", actualHeight=" + actualHeight + ", leftX="
				+ leftX + ", rightX=" + rightX + ", topY=" + topY + "]";
	}

}
