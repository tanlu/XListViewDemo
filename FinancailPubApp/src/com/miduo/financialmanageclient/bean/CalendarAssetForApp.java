package com.miduo.financialmanageclient.bean;

public class CalendarAssetForApp {
	private Boolean hasUnReadAsset; // 是否有日历数据(判断是否可以进入日历列表)
	private Boolean hasUnReadAssetThree; // 三天以内有没有日历数据(判断未读红点显示)

	public Boolean getHasUnReadAsset() {
		return hasUnReadAsset;
	}

	public void setHasUnReadAsset(Boolean hasUnReadAsset) {
		this.hasUnReadAsset = hasUnReadAsset;
	}

	public Boolean getHasUnReadAssetThree() {
		return hasUnReadAssetThree;
	}

	public void setHasUnReadAssetThree(Boolean hasUnReadAssetThree) {
		this.hasUnReadAssetThree = hasUnReadAssetThree;
	}

}
