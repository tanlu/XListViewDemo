package com.miduo.financialmanageclient.bean;

import java.io.Serializable;

public class MsgBean implements Serializable{
	private String title;
	private String content;
	private String topType;
	private String dateTime;
	private String uuid;
	private String isRead;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTopType() {
		return topType;
	}

	public void setTopType(String topType) {
		this.topType = topType;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	@Override
	public String toString() {
		return "MsgBean [title=" + title + ", content=" + content
				+ ", topType=" + topType + ", dateTime=" + dateTime + ", uuid="
				+ uuid + ", isRead=" + isRead + "]";
	}

}
