package com.miduo.financialmanageclient.bean;

import java.io.Serializable;
import java.util.List;

public class TransferListView implements Serializable {
	private Integer transferId;
	private Integer productId;
	private String proName;// 产品名称
	private String transNo;// 转让编号
	private Double transferPrice;// 转让价格
	private int discountType;// 0:溢;1:折
	private Double discountPrice;// 【折/溢出价格】
	private Double salerRate; // 我的收益率
	private Double buyerRate; // 对方收益率
	private String productPeriodDesc; // 产品期限显示desc
	private List<MTransferOrderInfoVo> proList;
	private String agreementLink;// 合同
	private String transferDueTime;
	private String assetNo;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getTransNo() {
		return transNo;
	}

	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	public Double getTransferPrice() {
		return transferPrice;
	}

	public void setTransferPrice(Double transferPrice) {
		this.transferPrice = transferPrice;
	}

	public int getDiscountType() {
		return discountType;
	}

	public void setDiscountType(int discountType) {
		this.discountType = discountType;
	}

	public Double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Double discountPrice) {
		this.discountPrice = discountPrice;
	}

	public Double getSalerRate() {
		return salerRate;
	}

	public void setSalerRate(Double salerRate) {
		this.salerRate = salerRate;
	}

	public Double getBuyerRate() {
		return buyerRate;
	}

	public void setBuyerRate(Double buyerRate) {
		this.buyerRate = buyerRate;
	}

	public String getProductPeriodDesc() {
		return productPeriodDesc;
	}

	public void setProductPeriodDesc(String productPeriodDesc) {
		this.productPeriodDesc = productPeriodDesc;
	}

	public List<MTransferOrderInfoVo> getProList() {
		return proList;
	}

	public void setProList(List<MTransferOrderInfoVo> proList) {
		this.proList = proList;
	}

	public String getAgreementLink() {
		return agreementLink;
	}

	public void setAgreementLink(String agreementLink) {
		this.agreementLink = agreementLink;
	}

	public String getTransferDueTime() {
		return transferDueTime;
	}

	public void setTransferDueTime(String transferDueTime) {
		this.transferDueTime = transferDueTime;
	}

	public Integer getTransferId() {
		return transferId;
	}

	public void setTransferId(Integer transferId) {
		this.transferId = transferId;
	}

	public String getAssetNo() {
		return assetNo;
	}

	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}
	
	

}
