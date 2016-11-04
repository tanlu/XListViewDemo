package com.miduo.financialmanageclient.bean;

import java.io.Serializable;

public class CalendarBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String date;// 日期
	private String title;// 可赎回、自动赎回、还是付息

	public CalendarBean(String date, String title) {
		super();
		this.date = date;
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "CalendarBean [date=" + date + ", title=" + title + "]";
	}

}
