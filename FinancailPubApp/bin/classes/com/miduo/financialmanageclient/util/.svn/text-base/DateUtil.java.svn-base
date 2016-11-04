package com.miduo.financialmanageclient.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.MyApplication;

public class DateUtil {
	private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final String FORMAT_HOUR = "HH:mm:ss";
	private static final String FORMAT_1="yyyy.mm";

	private DateUtil(){
		throw new Error("工具类DateUtil不可实例化");
	}
	
	/**
	 * 字符串转时间
	 * 
	 * @param str
	 * @return
	 */
	public static Date str2Date(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		return str2Date(str, null);
	}

	/**
	 * 字符串转时间
	 * 
	 * @param str
	 * @param format
	 * @return
	 */
	public static Date str2Date(String str, String format) {
		if (str == null || str.length() == 0) {
			return null;
		}
		if (format == null || format.length() == 0) {
			format = FORMAT;
		}
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
			date = sdf.parse(str);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 时间转字符串
	 * 
	 * @param d
	 * @return
	 */
	public static String date2Str(Date d) {// yyyy-MM-dd HH:mm:ss
		if (d == null) {
			return null;
		}
		return date2Str(d, null);
	}

	/**
	 * 时间转字符串
	 * 
	 * @param d
	 * @param format
	 * @return
	 */
	public static String date2Str(Date d, String format) {// yyyy-MM-dd HH:mm:ss
		if (d == null) {
			return null;
		}
		if (format == null || format.length() == 0) {
			format = FORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.getDefault());
		String s = sdf.format(d);
		return s;
	}

	/**
	 * 时间转字符串
	 * 
	 * @param d
	 * @param format
	 * @return
	 */
	public static String date2Hour(Date d, String format) {// hh:mm:ss
		if (d == null) {
			return null;
		}
		if (format == null || format.length() == 0) {
			format = FORMAT_HOUR;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.getDefault());
		String s = sdf.format(d);
		return s;
	}

	/**
	 * 时间转字符串
	 * 
	 * @param d
	 * @param format
	 * @return
	 */
	public static String dateFormatShow(Date date) {// hh:mm:ss
		return dateFormatShow(date, "hh:mm a");
	}

	/**
	 * 时间转字符串
	 * 
	 * @param d
	 * @param type
	 *            当天的时间格式(hh:mm a/HH:mm:ss)
	 * @return
	 */
	public static String dateFormatShow(Date date, String type) {// hh:mm:ss
		if (date == null) {
			return null;
		}
		Date now = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
		String formatNow = sdf1.format(now);
		String formatDate = sdf1.format(date);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = sdf1.format(cal.getTime());
		// LOG.out(yesterday);
		cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -6);
		String lastWeekTime = sdf1.format(cal.getTime());
		try {
			if (formatDate.equals(sdf1.format(now))) {
				return date2Hour(date, type);
			} else if (formatDate.equals(yesterday)) {
				return "昨天";
			} else if (sdf1.parse(formatDate).after(sdf1.parse(formatNow)) || sdf1.parse(formatDate).before(sdf1.parse(lastWeekTime))) {
//				if (typeDate == 1) {
//					SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//					return sdff.format(date);
//				} else
					return formatDate;
			} else {
				cal.setTime(date);
				int day = cal.get(Calendar.DAY_OF_WEEK);
				String weekStr = null;
				switch (day) {
				case 1:
					weekStr = "星期天";
					break;
				case 2:
					weekStr = "星期一";
					break;
				case 3:
					weekStr = "星期二";
					break;
				case 4:
					weekStr = "星期三";
					break;
				case 5:
					weekStr = "星期四";
					break;
				case 6:
					weekStr = "星期五";
					break;
				case 7:
					weekStr = "星期六";
					break;
				default:
					break;
				}
				return weekStr;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 判断当前日期是星期几
	 * 
	 * @param pTime
	 *            需要判断的时间
	 * @return dayForWeek 判断结果
	 * @Exception 发生异常
	 */
	public static String dayForWeek(Date pTime) throws Exception {
		if (pTime == null) {
			return null;
		}
		String time = date2Str(pTime, "yyyy-MM-dd");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
		Calendar c = Calendar.getInstance();
		c.setTime(format.parse(time));
		int day = c.get(Calendar.DAY_OF_WEEK);
		String weekStr = null;
		switch (day) {
		case 1:
			weekStr = "星期天";
			break;
		case 2:
			weekStr = "星期一";
			break;
		case 3:
			weekStr = "星期二";
			break;
		case 4:
			weekStr = "星期三";
			break;
		case 5:
			weekStr = "星期四";
			break;
		case 6:
			weekStr = "星期五";
			break;
		case 7:
			weekStr = "星期六";
			break;
		default:
			break;
		}
		return weekStr;
	}
	
	/**
	 * 时间转字符串
	 * 	 
	 * @return
	 * 一天内显示格式：hh:mm，一天前显示格式：yyyy-mm-dd（不显示时分）
	 */
	public static String orderTimeShow(String time) {// hh:mm:ss
		if (time == null) {
			return MyApplication.instance.getResources().getString(R.string.default_value);
		}
		Date date = str2Date(time);
		Date now = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
		String formatNow = sdf1.format(now);
		String formatDate = sdf1.format(date);
		try {
			if (sdf1.parse(formatDate).before(sdf1.parse(formatNow))){
				return formatDate;
			}else{
				return date2Hour(date, "HH:mm");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return MyApplication.instance.getResources().getString(R.string.default_value);
	}
}

