package com.miduo.financialmanageclient.ui;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.DialogBean;
import com.miduo.financialmanageclient.bean.ServerVersionInfo;
import com.miduo.financialmanageclient.bean.TblMobileVersion;
import com.miduo.financialmanageclient.listener.DialogEventListener;
import com.miduo.financialmanageclient.util.FileUtil;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.SharePrefUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

public class MainActivity extends Activity {
	private final int SPLASH_DISPLAY_LENGHT = 1000;
	private MobileUpdateAsyncTask mobileUpdateAsyncTask;
	private DebugAsyncTask debugAsyncTask;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication.home_refresh = true;
		setContentView(R.layout.activity_main);
		MobclickAgent.updateOnlineConfig(this);
		FileUtil.createFileDir();// 创建相关文件夹

//		 if (debugAsyncTask != null) {
//		 debugAsyncTask.cancel(true);
//		 debugAsyncTask = null;
//		 }
//		 debugAsyncTask = new DebugAsyncTask();
//		 debugAsyncTask.execute();
		// 这里来检测版本是否需要更新
		// getVersionCode();
		pageSet();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("引导页"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("引导页"); //
		MobclickAgent.onPause(this);
	}

	private void pageSet() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent();
				/**
				 * 是否是第一次启动APP
				 */
				boolean isFirst = SharePrefUtil.getBoolean(MainActivity.this, SharePrefUtil.MIDUO_PUB_INFO,
						SharePrefUtil.IS_FIRST, true);
				if (isFirst) {
					/**
					 * 跳转到欢迎页，并将 是否是第一次启动APP 置为false
					 */
					SharePrefUtil.saveBoolean(MainActivity.this, SharePrefUtil.MIDUO_PUB_INFO, SharePrefUtil.IS_FIRST,
							false);
					intent.setClass(MainActivity.this, WelcomeActivity.class);
				} else {
					/**
					 * 判断是否登录过
					 */
					boolean isLogin = SharePrefUtil.getBoolean(MainActivity.this, SharePrefUtil.IS_LOGIN, false);
					if (isLogin) {
						// 判断是否有手势密码
						boolean gesture = SharePrefUtil.getBoolean(MainActivity.this, SharePrefUtil.IS_SAVEGESTURE,
								false);
						if (gesture) {
							intent.setClass(MainActivity.this, GesturePasswordNewActivity.class);
						} else {
							intent.setClass(MainActivity.this, SetGestureNewActivity.class);
						}
					} else {
						// 没有登录
						intent.setClass(MainActivity.this, LoginActivity.class);
					}
				}
				startActivity(intent);
				finish();
			}
		}, SPLASH_DISPLAY_LENGHT);
	}

	private class DebugAsyncTask extends AsyncTask<Void, Void, String> {

		public DebugAsyncTask() {
		}

		@Override
		protected String doInBackground(Void... arg0) {
			try {
				return WebServiceClient.debug();
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			if (null == result) {
				// 这里来检测版本是否需要更新
				getVersionCode();
			} else {
				JSONObject jo;
				try {
					jo = new JSONObject(result);
					String state = jo.getString("debug");
					if (state != null && state.equals("1")) {
						Intent intent = new Intent(MainActivity.this, SystemUpgradeTipActivity.class);
						intent.putExtra("desc", jo.getString("desc"));
						startActivity(intent);
						finish();
					} else {
						// 这里来检测版本是否需要更新
						getVersionCode();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					// 这里来检测版本是否需要更新
					getVersionCode();
				}
			}
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
	}

	/**
	 * 获取版本号
	 * 
	 * @return 当前应用的版本号
	 */
	public void getVersionCode() {
		String version = null;
		try {
			PackageManager manager = this.getPackageManager();
			PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
			version = info.versionName;
			if (mobileUpdateAsyncTask != null) {
				mobileUpdateAsyncTask.cancel(true);
				mobileUpdateAsyncTask = null;
			}
			mobileUpdateAsyncTask = new MobileUpdateAsyncTask(version);
			mobileUpdateAsyncTask.execute();
		} catch (Exception e) {
			pageSet();
			e.printStackTrace();
		}
	}

	private class MobileUpdateAsyncTask extends AsyncTask<Void, Void, ServerVersionInfo> {
		private AppException ex;
		private String _version;

		public MobileUpdateAsyncTask(String version) {
			_version = version;
		}

		@Override
		protected ServerVersionInfo doInBackground(Void... arg0) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("mobileName", "android");
			map.put("mobileVersion", _version);
			try {
				return WebServiceClient.mobileUpdate(map);
			} catch (AppException e) {
				this.ex = e;
			}
			return null;
		}

		@Override
		protected void onPostExecute(ServerVersionInfo result) {
			if (null == result) {
				pageSet();
			} else {
				if (result.getState() != null && result.getState() == 1) {
					TblMobileVersion versionInfo = result.getData();
					if (versionInfo != null && versionInfo.getUpdateFlag() != null && versionInfo.getUpdateFlag() != 0
							&& !StringUtil.isEmpty(versionInfo.getUpdateUrl())) {
						// 有升级
						showNoticeDialog(versionInfo.getUpdateUrl(), versionInfo.getUpdateFlag());
					} else {
						// 没有升级
						pageSet();
					}
				} else {
					pageSet();
				}
			}
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
	}

	private void showNoticeDialog(final String updateUrl, Integer updateFlag) {
		// TODO Auto-generated method stub
		DialogBean data = new DialogBean();
		data.setContent("检测到更新");
		data.setCancel("以后再说");
		data.setSubmit("下载");
		DialogEventListener lister = new DialogEventListener() {

			@Override
			public void submit() {
				// TODO Auto-generated method stub
				Uri uri = Uri.parse(updateUrl);
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				finish();
			}

			@Override
			public void cancel() {
				// TODO Auto-generated method stub
				pageSet();
			}
		};
		data.setDialogEvent(lister);
		if (updateFlag == 1) {
			// 非强制升级
			MDialog.showDialog2(this, data);
		} else {
			// 强制升级
			MDialog.showDialog1(this, data);
		}

	}

}
