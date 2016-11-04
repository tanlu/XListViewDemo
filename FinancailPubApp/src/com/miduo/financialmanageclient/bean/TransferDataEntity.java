package com.miduo.financialmanageclient.bean;

import java.io.Serializable;
import java.util.List;

public  class TransferDataEntity implements Serializable {
        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		/**
         * id : 198
         * productId : 90
         * productName : 米多利6号
         * transNo : ZR11448007842349
         * transferUserName : 
         * expertRate : 8.800000
         * remainTime : 334
         * grabRemainTime : 57469
         * discountType : 溢价：
         * discountPrice : 44848.1786
         * transferPrice : 55002.4800
         * transferDueTime : 2015-11-26 02:33:40
         * proList : [{"attrName":"预期收益率","attrValue":"8.8%"},{"attrName":"产品投向","attrValue":"精选资管计划"},{"attrName":"本金","attrValue":"60000.0000"},{"attrName":"产品期限","attrValue":"19个月"},{"attrName":"产品到期日","attrValue":"2016-10-24 00:00:00"},{"attrName":"剩余期限","attrValue":"334天"},{"attrName":"到期本息合计","attrValue":"107161.6400"}]
         */

        private String id;
        private String productId;
        private String productName;
        private String transNo;
        private String transferUserName;
        private String expertRate;
        private String remainTime;
        private String grabRemainTime;
        private String discountType;
        private double discountPrice;
        private double transferPrice;
        private String transferDueTime;
        private List<TransferListBean> proList;
        private int state;

        
        public int getState() {
			return state;
		}

		public void setState(int state) {
			this.state = state;
		}

		public void setId(String id) {
            this.id = id;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public void setTransNo(String transNo) {
            this.transNo = transNo;
        }

        public void setTransferUserName(String transferUserName) {
            this.transferUserName = transferUserName;
        }

        public void setExpertRate(String expertRate) {
            this.expertRate = expertRate;
        }

        public void setRemainTime(String remainTime) {
            this.remainTime = remainTime;
        }

        public void setGrabRemainTime(String grabRemainTime) {
            this.grabRemainTime = grabRemainTime;
        }

        public void setDiscountType(String discountType) {
            this.discountType = discountType;
        }

        public void setDiscountPrice(double discountPrice) {
            this.discountPrice = discountPrice;
        }

        public void setTransferPrice(double transferPrice) {
            this.transferPrice = transferPrice;
        }

        public void setTransferDueTime(String transferDueTime) {
            this.transferDueTime = transferDueTime;
        }

        public void setProList(List<TransferListBean> proList) {
            this.proList = proList;
        }

        public String getId() {
            return id;
        }

        public String getProductId() {
            return productId;
        }

        public String getProductName() {
            return productName;
        }

        public String getTransNo() {
            return transNo;
        }

        public String getTransferUserName() {
            return transferUserName;
        }

        public String getExpertRate() {
            return expertRate;
        }

        public String getRemainTime() {
            return remainTime;
        }

        public String getGrabRemainTime() {
            return grabRemainTime;
        }

        public String getDiscountType() {
            return discountType;
        }

        public double getDiscountPrice() {
            return discountPrice;
        }

        public double getTransferPrice() {
            return transferPrice;
        }

        public String getTransferDueTime() {
            return transferDueTime;
        }

        public List<TransferListBean> getProList() {
            return proList;
        }

      
    }