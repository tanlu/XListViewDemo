package com.miduo.financialmanageclient.bean;

import java.io.Serializable;

public class BankCardInfo implements Serializable{
	private boolean isOpen;
	private Integer id;
	private Integer userId;
	private String mobile;
	private Integer bankListId;//银行的id
	private String realName;//姓名
	private String idcard;//身份证
	private String cardShortNo;//后四位卡号
	private String cardNo;//完整卡号
	private String provinceCode;
	private String cityCode;
	private String bankCode;
	private String bankAddress;//开户具体支行
	private Integer status;
	private String bankName;//银行名称
	private String smallIco;//银行名称
	private String largeIco;
	private Integer bankIsDefault;
	private Integer bankCardType;
	private String branchBank;//开户省市区
	private String bankQuotaImg;//限额图片
	private Integer channelType;//1代表网银   2代表移动
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getBankListId() {
		return bankListId;
	}
	public void setBankListId(Integer bankListId) {
		this.bankListId = bankListId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getCardShortNo() {
		return cardShortNo;
	}
	public void setCardShortNo(String cardShortNo) {
		this.cardShortNo = cardShortNo;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankAddress() {
		return bankAddress;
	}
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getSmallIco() {
		return smallIco;
	}
	public void setSmallIco(String smallIco) {
		this.smallIco = smallIco;
	}
	public String getLargeIco() {
		return largeIco;
	}
	public void setLargeIco(String largeIco) {
		this.largeIco = largeIco;
	}
	public Integer getBankIsDefault() {
		return bankIsDefault;
	}
	public void setBankIsDefault(Integer bankIsDefault) {
		this.bankIsDefault = bankIsDefault;
	}
	public Integer getBankCardType() {
		return bankCardType;
	}
	public void setBankCardType(Integer bankCardType) {
		this.bankCardType = bankCardType;
	}
	public String getBranchBank() {
		return branchBank;
	}
	public void setBranchBank(String branchBank) {
		this.branchBank = branchBank;
	}
	public String getBankQuotaImg() {
		return bankQuotaImg;
	}
	public void setBankQuotaImg(String bankQuotaImg) {
		this.bankQuotaImg = bankQuotaImg;
	}
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public Integer getChannelType() {
		return channelType;
	}
	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}

	
}
