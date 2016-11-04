package com.miduo.financialmanageclient.bean;

import java.io.Serializable;

/**
 * 日历结果页面，重新构造后的数据（应该包括两部分 年份、数据。）这两部分只可能一部分有数据
 * 
 * @author huozhenpeng
 * 
 */
public class CalendarResultBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String year;// 年份
	private AssetForCalendarListEntity  data;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public AssetForCalendarListEntity getData() {
		return data;
	}

	public void setData(AssetForCalendarListEntity data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "CalendarResultBean [year=" + year + ", data=" + data + "]";
	}

}
