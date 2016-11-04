package com.miduo.financialmanageclient.ui;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.miduo.financialmanageclient.util.DateUtil;
import com.miduo.financialmanageclient.util.SharePrefUtil;
import com.miduo.financialmanageclient.util.StringUtil;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;

public class BaseDialogActivity extends Activity {
	public static boolean isDialogActive;
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if (!isAppOnForeground()) {
			isDialogActive = false;
			String time = DateUtil.date2Str(new Date());
			SharePrefUtil.saveString(this, SharePrefUtil.MIDUO_PUB_INFO, SharePrefUtil.DIALOG_OUT_TIME, time);
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (!isDialogActive) {
			isDialogActive = true;
			String time = SharePrefUtil.getString(this, SharePrefUtil.MIDUO_PUB_INFO, SharePrefUtil.DIALOG_OUT_TIME, null);
			if (!StringUtil.isEmpty(time)) {
				Date date = DateUtil.str2Date(time);
				Calendar start = Calendar.getInstance();
				start.setTime(date);
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MINUTE, -2);
				if (start.before(cal)) {
					SharePrefUtil.saveString(this, SharePrefUtil.MIDUO_PUB_INFO, SharePrefUtil.DIALOG_OUT_TIME, null);
					SharePrefUtil.saveString(this, SharePrefUtil.MIDUO_PUB_INFO, SharePrefUtil.OUT_TIME, null);
					setResult(1234);
					this.finish();
				} else {
					SharePrefUtil.saveString(this, SharePrefUtil.MIDUO_PUB_INFO, SharePrefUtil.DIALOG_OUT_TIME, null);
				}
			}
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		SharePrefUtil.saveString(this, SharePrefUtil.MIDUO_PUB_INFO, SharePrefUtil.DIALOG_OUT_TIME, null);
		SharePrefUtil.saveString(this, SharePrefUtil.MIDUO_PUB_INFO, SharePrefUtil.OUT_TIME, null);
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
}
