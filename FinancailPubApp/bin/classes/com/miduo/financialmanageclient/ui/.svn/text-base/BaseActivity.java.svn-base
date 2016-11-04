package com.miduo.financialmanageclient.ui;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;

import com.miduo.financialmanageclient.util.DateUtil;
import com.miduo.financialmanageclient.util.SharePrefUtil;
import com.miduo.financialmanageclient.util.StringUtil;

public class BaseActivity extends Activity {
	public InputMethodManager imm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (getCurrentFocus() != null) {
				if (getCurrentFocus().getWindowToken() != null) {
					imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				}
			}
		}
		return super.onTouchEvent(event);
	}

	public static boolean isActive;

	// 程序是否退入后台
	@Override
	protected void onStop() {
		super.onStop();
		boolean isLogin = SharePrefUtil.getBoolean(this, SharePrefUtil.IS_LOGIN, false);
		if (!isLogin) {
			return;
		}
		if (!isAppOnForeground()) {
			isActive = false;
			String time = DateUtil.date2Str(new Date());
			SharePrefUtil.saveString(this, SharePrefUtil.OUT_TIME, time);
		}
	}

	// 程序进入前台
	@Override
	protected void onResume() {
		super.onResume();
		boolean isLogin = SharePrefUtil.getBoolean(this, SharePrefUtil.IS_LOGIN, false);
		if (!isLogin) {
			return;
		}
		if (!isActive) {
			isActive = true;
			String time = SharePrefUtil.getString(this, SharePrefUtil.OUT_TIME, null);
			if (!StringUtil.isEmpty(time)) {
				Date date = DateUtil.str2Date(time);
				Calendar start = Calendar.getInstance();
				start.setTime(date);
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MINUTE, -2);
				if (start.before(cal)) {
					SharePrefUtil.saveString(this, SharePrefUtil.OUT_TIME, null);
					Intent intent = new Intent(this, MainActivity.class);
					startActivity(intent);
					this.finish();
				} else {
					SharePrefUtil.saveString(this, SharePrefUtil.OUT_TIME, null);
				}
			}
		}
	}

	/**
	 * 程序是否在前台运行
	 * 
	 * @return
	 */
	public boolean isAppOnForeground() {
		ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		String packageName = getPackageName();
		List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
		if (appProcesses == null)
			return false;
		for (RunningAppProcessInfo appProcess : appProcesses) {
			if (appProcess.processName.equals(packageName)
					&& appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		SharePrefUtil.saveString(this, SharePrefUtil.OUT_TIME, null);
	}
}
