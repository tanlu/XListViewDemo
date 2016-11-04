package com.miduo.financialmanageclient.receiver;

import java.lang.reflect.Method;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;
import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.DialogBean;
import com.miduo.financialmanageclient.bean.MyMessage;
import com.miduo.financialmanageclient.listener.DialogEventListener;
import com.miduo.financialmanageclient.service.GetuiIntentService;
import com.miduo.financialmanageclient.ui.HomeActivity;
import com.miduo.financialmanageclient.util.JsonUtils;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.SharePrefUtil;

/**
 * 
 * @author huozhenpeng www.miduo.com 2015-5-12
 * 
 */
public class GetuiPushReceiver extends BroadcastReceiver {

	private NotificationManager notificationManager;
	private Notification notification;

	// private static int HELLO_ID = 1;

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		Log.d("GetuiSdkDemo", "onReceive() action=" + bundle.getInt("action"));
		switch (bundle.getInt(PushConsts.CMD_ACTION)) {

		case PushConsts.GET_MSG_DATA:
			// 获取透传数据
			// String appid = bundle.getString("appid");
			byte[] payload = bundle.getByteArray("payload");
			Log.e("abc", "---------------------------------");
			String taskid = bundle.getString("taskid");
			String messageid = bundle.getString("messageid");

			// smartPush第三方回执调用接口，actionid范围为90000-90999，可根据业务场景执行
			@SuppressWarnings("unused")
			boolean result = PushManager.getInstance().sendFeedbackMessage(
					context, taskid, messageid, 90001);
			// System.out.println("第三方回执接口调用" + (result ? "成功" : "失败"));

			if (payload != null) {
				String data = new String(payload);
				MyMessage message = ParseJson(data);
				// MyMessage message = new MyMessage();
				// message.setContent(data);
				// message.setTitle("abc");
				if (isBackground(context) || MyApplication.homeActivity == null) {
					sendnotification(message, context);
				} else {
					showDialog(MyApplication.homeActivity, message);
				}

			}
			break;
		case PushConsts.GET_CLIENTID:
			// 获取ClientID(CID)
			// 第三方应用需要将CID上传到第三方服务器，并且将当前用户帐号和CID进行关联，以便日后通过用户帐号查找CID进行消息推送
			String cid = bundle.getString("clientid");
			Log.e("ccccccc", cid);
			// 启动服务，提交服务器，关联uid跟clientid（单独推送使用）
			Intent getuiIntent = new Intent(context, GetuiIntentService.class);
			getuiIntent.putExtra("CLIENTID", cid);
			context.startService(getuiIntent);
			SharePrefUtil.saveBoolean(context, SharePrefUtil.GETUI_REGISTER,
					true);
			break;
		case PushConsts.THIRDPART_FEEDBACK:
			break;

		default:
			break;
		}
	}

	/**
	 * 状态栏显示通知代码
	 * 
	 * @param payload
	 * @param context
	 */
	@SuppressWarnings("deprecation")
	public void sendnotification(MyMessage payload, Context context) {
		wakeUpAndUnlock(context);
		if (notificationManager == null) {
			notificationManager = (NotificationManager) context
					.getSystemService(Context.NOTIFICATION_SERVICE);
		}
		if (notification == null) {
			notification = new Notification(R.drawable.ic_launcher, "信息",
					System.currentTimeMillis());
		}
		notification.defaults = Notification.DEFAULT_ALL;
		notification.flags |= Notification.FLAG_NO_CLEAR;
		notification.flags |= Notification.FLAG_SHOW_LIGHTS;
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		// ③ 定义notification的消息 和 PendingIntent
		CharSequence contentTitle = payload.getTitle();
		CharSequence contentText = payload.getContent();
		Intent notificationIntent = new Intent(context, HomeActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
				notificationIntent, 0);
		notification.setLatestEventInfo(context, contentTitle, contentText,
				contentIntent);
		notificationManager.notify(getNotificationId(context), notification);

	}

	/**
	 * 唤醒屏幕功能代码
	 * 
	 * @param context
	 */
	@SuppressWarnings("deprecation")
	public static void wakeUpAndUnlock(Context context) {
		// KeyguardManager km = (KeyguardManager) context
		// .getSystemService(Context.KEYGUARD_SERVICE);
		// KeyguardManager.KeyguardLock kl = km.newKeyguardLock("unLock");
		// // 解锁
		// kl.disableKeyguard();
		// 获取电源管理器对象
		PowerManager pm = (PowerManager) context
				.getSystemService(Context.POWER_SERVICE);
		// 获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag
		PowerManager.WakeLock wl = pm.newWakeLock(
				PowerManager.ACQUIRE_CAUSES_WAKEUP
						| PowerManager.SCREEN_DIM_WAKE_LOCK, "bright");
		// 点亮屏幕
		wl.acquire();
		// 释放
		wl.release();
		// openStatusBar(context);

	}

	/**
	 * 从配置文件中获取通知id（id为累加，防止重复）
	 * 
	 * @return
	 */
	public int getNotificationId(Context context) {
		int id = SharePrefUtil.getInt(context, "NOTIFICATION_ID", 0);
		int tempid = ++id;
		SharePrefUtil.saveInt(context, "NOTIFICATION_ID", tempid > 1000 ? 0
				: tempid);
		return id;
	}

	/**
	 * 点亮屏幕的同时滑下状态栏
	 */
	public static void openStatusBar(Context context) {
		int currentApiVersion = android.os.Build.VERSION.SDK_INT;
		try {
			Object service = context.getSystemService("statusbar");
			Class<?> statusbarManager = Class
					.forName("android.app.StatusBarManager");
			Method expand = null;
			if (service != null) {
				if (currentApiVersion <= 16) {
					expand = statusbarManager.getMethod("expand");
				} else {
					expand = statusbarManager
							.getMethod("expandNotificationsPanel");
				}
				expand.setAccessible(true);
				expand.invoke(service);
			}

		} catch (Exception e) {
		}

	}

	/**
	 * 解析透传消息
	 * 
	 * @param payload
	 */
	public MyMessage ParseJson(String payload) {
		try {
			return JsonUtils.toBean(payload, new TypeToken<MyMessage>() {
			}.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 判断当前应用是否位于前台
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isBackground(Context context) {
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> tasks = am.getRunningTasks(1);
		if (!tasks.isEmpty()) {
			ComponentName topActivity = tasks.get(0).topActivity;
			if (!topActivity.getPackageName().equals(context.getPackageName())) {
				return true;
			}
		}
		return false;

	}

	/**
	 * 弹出窗口
	 * 
	 * @param context
	 */
	public void showDialog(final Activity context, MyMessage message) {
		MyApplication.message = message;
		DialogBean dialog = new DialogBean();
		dialog.setTitle(message.getTitle());
		dialog.setContent(message.getContent());
		dialog.setSubmit("我知道了");
		dialog.setDialogEvent(new DialogEventListener() {

			@Override
			public void submit() {

				if(context!=null)
				{
					((HomeActivity)context).submitRead();
				}
			}

			@Override
			public void cancel() {

			}
		});
		MDialog.showDialog1(MyApplication.homeActivity, dialog);
	}

}
