package com.miduo.financialmanageclient.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.google.gson.reflect.TypeToken;
import com.miduo.financialmanageclient.bean.DialogBean;
import com.miduo.financialmanageclient.bean.UserInfo;
import com.miduo.financialmanageclient.listener.CheckIdentyListener;
import com.miduo.financialmanageclient.listener.DialogEventListener;
import com.miduo.financialmanageclient.listener.LoginListener;
import com.miduo.financialmanageclient.ui.LoginActivity;
import com.miduo.financialmanageclient.util.JsonUtils;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.SharePrefUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.ws.AppException;

public class CommonUtil {
	private static Context mContext;

	public static boolean checkLogin(Context context) {
		boolean isLogin = SharePrefUtil.getBoolean(context, SharePrefUtil.IS_LOGIN, false);
		if (!isLogin) {
			mContext = context;
			DialogBean bean = new DialogBean();
			bean.setCancel("取消");
			bean.setSubmit("去登录");
			bean.setContent("抱歉，游客无法访问此功能，请您登录后再继续！");
			bean.setDialogEvent(new DialogEventListener() {

				@Override
				public void submit() {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					intent.setClass(mContext, LoginActivity.class);
					mContext.startActivity(intent);	
					((Activity)mContext).finish();
				}

				@Override
				public void cancel() {
					// TODO Auto-generated method stub

				}
			});
			MDialog.showDialog2(context, bean);
			return false;
		}
		return true;
	}

	public static void downPdf(Context context, String name) {
		if (context == null || StringUtil.isEmpty(name)) {
			return;
		}
		Uri uri = Uri.parse(name);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		context.startActivity(intent);
	}

	public static boolean checkIdentify(Context context) {
		// TODO Auto-generated method stub
		UserInfo userInfo = (UserInfo) SharePrefUtil.getObj(context, SharePrefUtil.USER_INFO);
		if (userInfo != null && userInfo.getIdentityAuth() != null && userInfo.getIdentityAuth().intValue() == 1) {
			return true;
		}
		return false;
	}

	public static void identy(final Context context, final CheckIdentyListener checkIdentyListener) {
		LoginListener listener = new LoginListener() {
			@Override
			public void login(String data) {
				// TODO Auto-generated method stub
				boolean flag = false;
				if (!StringUtil.isEmpty(data)) {
					try {
						UserInfo userInfo = JsonUtils.toBean(data, new TypeToken<UserInfo>() {
						}.getType());
						if (userInfo != null && userInfo.getIdentityAuth() != null
								&& userInfo.getIdentityAuth().intValue() == 1) {
							SharePrefUtil.saveString(context, SharePrefUtil.ACCOUNT_HEADER, userInfo.getAvatars());
							SharePrefUtil.saveObj(context, SharePrefUtil.USER_INFO, userInfo);
							flag = true;
						}
					} catch (AppException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (checkIdentyListener != null) {
					checkIdentyListener.check(flag);
				}
			}
		};
		Login login = new Login(context, listener);
		login.userLogin();
	}
}
