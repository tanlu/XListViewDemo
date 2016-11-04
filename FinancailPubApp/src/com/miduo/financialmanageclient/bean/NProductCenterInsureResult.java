package com.miduo.financialmanageclient.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 产品中心（保险产品）真实数据
 * 
 * @author huozhenpeng
 * 
 */
public class NProductCenterInsureResult {

	/**
	 * data :
	 * [{"id":"114","productName":"泰康爱相随定期寿险","productPy":"","productCode"
	 * :"tkax","productType":"1","productTypeName":"投保类","categoryId":"5",
	 * "categoryName"
	 * :"寿险","createTime":"2015-11-19 11:41:53","isup":"1","ishot":
	 * "0","istransfer"
	 * :"0","orderIndex":"","status":"20","statusName":"在售","crowd"
	 * :"18-50周岁","insuracePeriod"
	 * :"10年、20年、30年期","highlight":"泰康人寿","premium":"1200"
	 * ,"attrList":[{"attrName"
	 * :"投保年龄","attrValue":"18-50周岁"},{"attrName":"参考保费",
	 * "attrValue":"1200"},{"attrName"
	 * :"缴费方式","attrValue":"一次性缴纳或年缴"},{"attrName"
	 * :"保险公司","attrValue":"泰康人寿"},{"attrName"
	 * :"保障范围","attrValue":"因意外或非意外伤害导致的身故"
	 * },{"attrName":"保险期间","attrValue":"10年、20年、30年期"
	 * },{"attrName":"ifa投保规则文字","attrValue"
	 * :""},{"attrName":"产品特色","attrValue":"泰康人寿"
	 * },{"attrName":"","attrValue":"0"
	 * },{"attrName":"","attrValue":"0"},{"attrName"
	 * :"产品的logo图片","attrValue":""}]}] state : 1
	 */

	private int state;
	private List<NProductCenterInsureBean> data;
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setData(List<NProductCenterInsureBean> data) {
		this.data = data;
	}

	public int getState() {
		return state;
	}

	public List<NProductCenterInsureBean> getData() {
		return data;
	}

	@Override
	public String toString() {
		return "NProductCenterInsureResult [state=" + state + ", data=" + data + "]";
	}

	public static class NProductCenterInsureBean implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * id : 114 productName : 泰康爱相随定期寿险 productPy : productCode : tkax
		 * productType : 1 productTypeName : 投保类 categoryId : 5 categoryName :
		 * 寿险 createTime : 2015-11-19 11:41:53 isup : 1 ishot : 0 istransfer : 0
		 * orderIndex : status : 20 statusName : 在售 crowd : 18-50周岁
		 * insuracePeriod : 10年、20年、30年期 highlight : 泰康人寿 premium : 1200
		 * attrList :
		 * [{"attrName":"投保年龄","attrValue":"18-50周岁"},{"attrName":"参考保费"
		 * ,"attrValue"
		 * :"1200"},{"attrName":"缴费方式","attrValue":"一次性缴纳或年缴"},{"attrName"
		 * :"保险公司" ,"attrValue":"泰康人寿"},{"attrName":"保障范围","attrValue":
		 * "因意外或非意外伤害导致的身故"
		 * },{"attrName":"保险期间","attrValue":"10年、20年、30年期"},{"attrName":
		 * "ifa投保规则文字"
		 * ,"attrValue":""},{"attrName":"产品特色","attrValue":"泰康人寿"},{"attrName"
		 * :"","attrValue":"0"},{"attrName":"","attrValue":"0"},{"attrName":
		 * "产品的logo图片","attrValue":""}]
		 */

		private String id;
		private String productName;
		private String productPy;
		private String productCode;
		private String productType;
		private String productTypeName;
		private String categoryId;
		private String categoryName;
		private String createTime;
		private String isup;
		private String ishot;
		private String istransfer;
		private String orderIndex;
		private String status;
		private String statusName;
		private String crowd;
		private String insuracePeriod;
		private String highlight;
		private String premium;
		private List<NInsureValue> attrList;
		private List<InsureFileListEntity> fileList;
		private String shareHref;

		public String getShareHref() {
			return shareHref;
		}

		public void setShareHref(String shareHref) {
			this.shareHref = shareHref;
		}

		public void setId(String id) {
			this.id = id;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public void setProductPy(String productPy) {
			this.productPy = productPy;
		}

		public void setProductCode(String productCode) {
			this.productCode = productCode;
		}

		public void setProductType(String productType) {
			this.productType = productType;
		}

		public void setProductTypeName(String productTypeName) {
			this.productTypeName = productTypeName;
		}

		public void setCategoryId(String categoryId) {
			this.categoryId = categoryId;
		}

		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}

		public void setIsup(String isup) {
			this.isup = isup;
		}

		public void setIshot(String ishot) {
			this.ishot = ishot;
		}

		public void setIstransfer(String istransfer) {
			this.istransfer = istransfer;
		}

		public void setOrderIndex(String orderIndex) {
			this.orderIndex = orderIndex;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public void setStatusName(String statusName) {
			this.statusName = statusName;
		}

		public void setCrowd(String crowd) {
			this.crowd = crowd;
		}

		public void setInsuracePeriod(String insuracePeriod) {
			this.insuracePeriod = insuracePeriod;
		}

		public void setHighlight(String highlight) {
			this.highlight = highlight;
		}

		public void setPremium(String premium) {
			this.premium = premium;
		}

		public void setAttrList(List<NInsureValue> attrList) {
			this.attrList = attrList;
		}

		public String getId() {
			return id;
		}

		public String getProductName() {
			return productName;
		}

		public String getProductPy() {
			return productPy;
		}

		public String getProductCode() {
			return productCode;
		}

		public String getProductType() {
			return productType;
		}

		public String getProductTypeName() {
			return productTypeName;
		}

		public String getCategoryId() {
			return categoryId;
		}

		public String getCategoryName() {
			return categoryName;
		}

		public String getCreateTime() {
			return createTime;
		}

		public String getIsup() {
			return isup;
		}

		public String getIshot() {
			return ishot;
		}

		public String getIstransfer() {
			return istransfer;
		}

		public String getOrderIndex() {
			return orderIndex;
		}

		public String getStatus() {
			return status;
		}

		public String getStatusName() {
			return statusName;
		}

		public String getCrowd() {
			return crowd;
		}

		public String getInsuracePeriod() {
			return insuracePeriod;
		}

		public String getHighlight() {
			return highlight;
		}

		public String getPremium() {
			return premium;
		}

		public List<NInsureValue> getAttrList() {
			return attrList;
		}

		public void setFileList(List<InsureFileListEntity> fileList) {
			this.fileList = fileList;
		}

		public List<InsureFileListEntity> getFileList() {
			return fileList;
		}

		@Override
		public String toString() {
			return "NProductCenterInsureBean [id=" + id + ", productName=" + productName + ", productPy=" + productPy
					+ ", productCode=" + productCode + ", productType=" + productType + ", productTypeName="
					+ productTypeName + ", categoryId=" + categoryId + ", categoryName=" + categoryName
					+ ", createTime=" + createTime + ", isup=" + isup + ", ishot=" + ishot + ", istransfer="
					+ istransfer + ", orderIndex=" + orderIndex + ", status=" + status + ", statusName=" + statusName
					+ ", crowd=" + crowd + ", insuracePeriod=" + insuracePeriod + ", highlight=" + highlight
					+ ", premium=" + premium + ", attrList=" + attrList + "]";
		}

		public static class NInsureValue {
			/**
			 * attrName : 投保年龄 attrValue : 18-50周岁
			 */

			private String attrName;
			private String attrValue;

			public void setAttrName(String attrName) {
				this.attrName = attrName;
			}

			public void setAttrValue(String attrValue) {
				this.attrValue = attrValue;
			}

			public String getAttrName() {
				return attrName;
			}

			public String getAttrValue() {
				return attrValue;
			}

			@Override
			public String toString() {
				return "NInsureValue [attrName=" + attrName + ", attrValue=" + attrValue + "]";
			}

		}

		public static class InsureFileListEntity {
			/**
			 * attrName : 理赔流程pdf attrValue : ["abc"]
			 */

			private String fileName;
			private String url;

			public void setAttrName(String fileName) {
				this.fileName = fileName;
			}

			public void setAttrValue(String url) {
				this.url = url;
			}

			public String getAttrName() {
				return fileName;
			}

			public String getAttrValue() {
				return url;
			}
		}
	}

}
