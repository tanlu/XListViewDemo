package com.miduo.financialmanageclient.bean;

import java.io.Serializable;
import java.util.List;

public class FileListEntity implements Serializable {
	/**
	 * attrName : 产品说明书pdf attrValue : [{"fileName":"MDL2018-ysht-03","url":
	 * "http://192.168.4.88/group1/M00/00/18/wKgEWFZK4U2ADuVmAAWqJAD8ISA134.pdf"
	 * }]
	 */

	private String attrName;
	private List<AttrValueEntity> attrValue;

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public void setAttrValue(List<AttrValueEntity> attrValue) {
		this.attrValue = attrValue;
	}

	public String getAttrName() {
		return attrName;
	}

	public List<AttrValueEntity> getAttrValue() {
		return attrValue;
	}

}
