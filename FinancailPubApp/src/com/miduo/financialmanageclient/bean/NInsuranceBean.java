package com.miduo.financialmanageclient.bean;

import java.io.Serializable;

/**
 * 投保首页data(正式)
 * 
 * @author huozhenpeng
 * 
 */
public class NInsuranceBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int dataType;
	private String name1;
	private String name2;
	private String name3;
	private String name4;
	private double self1;
	private double self2;
	private double self3;
	private double self4;
	private double seftRatio1;
	private double seftRatio2;
	private double seftRatio3;
	private double seftRatio4;
	private double spouse1;
	private double spouse2;
	private double spouse3;
	private double spouse4;
	private double spouseRatio1;
	private double spouseRatio2;
	private double spouseRatio3;
	private double spouseRatio4;

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getName3() {
		return name3;
	}

	public void setName3(String name3) {
		this.name3 = name3;
	}

	public String getName4() {
		return name4;
	}

	public void setName4(String name4) {
		this.name4 = name4;
	}

	public double getSpouse1() {
		return spouse1;
	}

	public void setSpouse1(double spouse1) {
		this.spouse1 = spouse1;
	}

	public double getSpouse2() {
		return spouse2;
	}

	public void setSpouse2(double spouse2) {
		this.spouse2 = spouse2;
	}

	public double getSpouse3() {
		return spouse3;
	}

	public void setSpouse3(double spouse3) {
		this.spouse3 = spouse3;
	}

	public double getSpouse4() {
		return spouse4;
	}

	public void setSpouse4(double spouse4) {
		this.spouse4 = spouse4;
	}

	public double getSeftRatio1() {
		return seftRatio1;
	}

	public void setSeftRatio1(double seftRatio1) {
		this.seftRatio1 = seftRatio1;
	}

	public double getSeftRatio2() {
		return seftRatio2;
	}

	public void setSeftRatio2(double seftRatio2) {
		this.seftRatio2 = seftRatio2;
	}

	public double getSeftRatio3() {
		return seftRatio3;
	}

	public void setSeftRatio3(double seftRatio3) {
		this.seftRatio3 = seftRatio3;
	}

	public double getSeftRatio4() {
		return seftRatio4;
	}

	public void setSeftRatio4(double seftRatio4) {
		this.seftRatio4 = seftRatio4;
	}

	public double getSpouseRatio1() {
		return spouseRatio1;
	}

	public void setSpouseRatio1(double spouseRatio1) {
		this.spouseRatio1 = spouseRatio1;
	}

	public double getSpouseRatio2() {
		return spouseRatio2;
	}

	public void setSpouseRatio2(double spouseRatio2) {
		this.spouseRatio2 = spouseRatio2;
	}

	public double getSpouseRatio3() {
		return spouseRatio3;
	}

	public void setSpouseRatio3(double spouseRatio3) {
		this.spouseRatio3 = spouseRatio3;
	}

	public double getSpouseRatio4() {
		return spouseRatio4;
	}

	public void setSpouseRatio4(double spouseRatio4) {
		this.spouseRatio4 = spouseRatio4;
	}

	public double getSelf1() {
		return self1;
	}

	public void setSelf1(double self1) {
		this.self1 = self1;
	}

	public double getSelf2() {
		return self2;
	}

	public void setSelf2(double self2) {
		this.self2 = self2;
	}

	public double getSelf3() {
		return self3;
	}

	public void setSelf3(double self3) {
		this.self3 = self3;
	}

	public double getSelf4() {
		return self4;
	}

	public void setSelf4(double self4) {
		this.self4 = self4;
	}

	@Override
	public String toString() {
		return "NInsuranceBean [dataType=" + dataType + ", name1=" + name1
				+ ", name2=" + name2 + ", name3=" + name3 + ", name4=" + name4
				+ ", self1=" + self1 + ", self2=" + self2 + ", self3=" + self3
				+ ", self4=" + self4 + ", seftRatio1=" + seftRatio1
				+ ", seftRatio2=" + seftRatio2 + ", seftRatio3=" + seftRatio3
				+ ", seftRatio4=" + seftRatio4 + ", spouse1=" + spouse1
				+ ", spouse2=" + spouse2 + ", spouse3=" + spouse3
				+ ", spouse4=" + spouse4 + ", spouseRatio1=" + spouseRatio1
				+ ", spouseRatio2=" + spouseRatio2 + ", spouseRatio3="
				+ spouseRatio3 + ", spouseRatio4=" + spouseRatio4 + "]";
	}

}
