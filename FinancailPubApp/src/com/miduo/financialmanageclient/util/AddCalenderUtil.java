package com.miduo.financialmanageclient.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract.Reminders;

public class AddCalenderUtil {
	@SuppressLint("NewApi")
	public static void add(Context context, String title, String description, String startTime, String endTime,
			Integer alarmClockTime) {
		if(StringUtil.isEmpty(startTime)){
			return;
		}
		if(StringUtil.isEmpty(endTime)){
			endTime = startTime;
		}
		String calanderURL, calanderEventURL, calanderRemiderURL;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
			calanderURL = "content://com.android.calendar/calendars";
			calanderEventURL = "content://com.android.calendar/events";
			calanderRemiderURL = "content://com.android.calendar/reminders";

		} else {
			calanderURL = "content://calendar/calendars";
			calanderEventURL = "content://calendar/events";
			calanderRemiderURL = "content://calendar/reminders";
		}

		// 获取要出入的gmail账户的id
		String calId = "";
		Cursor userCursor = context.getContentResolver().query(Uri.parse(calanderURL), null, null, null, null);
		if (userCursor.getCount() > 0) {
			userCursor.moveToFirst();
			// userCursor.moveToLast(); //注意：是向最后一个账户添加，开发者可以根据需要改变添加事件 的账户
			calId = userCursor.getString(userCursor.getColumnIndex("_id"));
		} else {
			MToast.showToast(context, "没有日历账户，请先添加账户");
			return;
		}
		ContentValues event = new ContentValues();
		// 事件的标题
		event.put("title", title);
		// 事件的内容
		event.put("description", description);
		// 插入账户
		event.put("calendar_id", calId);
		// 事件的开始结束时间
		Calendar mCalendar = Calendar.getInstance();
		Date startDate = DateUtil.str2Date(startTime, "yyyy-MM-dd HH:mm:ss");
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);
		mCalendar.set(Calendar.YEAR, startCalendar.get(Calendar.YEAR));
		mCalendar.set(Calendar.MONTH, startCalendar.get(Calendar.MONTH));
		mCalendar.set(Calendar.DAY_OF_MONTH, startCalendar.get(Calendar.DAY_OF_MONTH));
		mCalendar.set(Calendar.HOUR_OF_DAY, startCalendar.get(Calendar.HOUR_OF_DAY));
		mCalendar.set(Calendar.MINUTE, startCalendar.get(Calendar.MINUTE));
		long start = mCalendar.getTime().getTime();
		
		Date endDate = DateUtil.str2Date(endTime, "yyyy-MM-dd HH:mm:ss");
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);
		mCalendar.set(Calendar.YEAR, endCalendar.get(Calendar.YEAR));
		mCalendar.set(Calendar.MONTH, endCalendar.get(Calendar.MONTH));
		mCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.get(Calendar.DAY_OF_MONTH));
		mCalendar.set(Calendar.HOUR_OF_DAY, endCalendar.get(Calendar.HOUR_OF_DAY));
		mCalendar.set(Calendar.MINUTE, endCalendar.get(Calendar.MINUTE));
		long end = mCalendar.getTime().getTime();

		event.put("dtstart", start);
		event.put("dtend", end);
		event.put("hasAlarm", 1);
		// 时区，必须有
		event.put("eventTimezone", TimeZone.getDefault().getID().toString());
		// 添加事件
		Uri newEvent = context.getContentResolver().insert(Uri.parse(calanderEventURL), event);
		// 事件提醒的设定
		long id = Long.parseLong(newEvent.getLastPathSegment());
		ContentValues values = new ContentValues();
		values.put("event_id", id);
		// 提前10分钟有提醒
		// values.put("minutes", 10);
		// 提前1天有提醒
		if (alarmClockTime == null) {
			alarmClockTime = 3 * 60 * 60;
		}
		values.put(Reminders.MINUTES, alarmClockTime / 60);
		values.put(Reminders.EVENT_ID, id);
		values.put(Reminders.METHOD, Reminders.METHOD_ALERT);

		ContentResolver cr1 = context.getContentResolver(); // 为刚才新添加的event添加reminder
		cr1.insert(Reminders.CONTENT_URI, values); // 调用这个方法返回值是一个Uri
		MToast.showToast(context, "米多提醒添加成功");
	}
}
