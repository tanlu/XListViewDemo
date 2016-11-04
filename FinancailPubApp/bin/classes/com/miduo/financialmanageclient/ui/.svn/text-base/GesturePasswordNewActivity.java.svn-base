package com.miduo.financialmanageclient.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.UserInfo;
import com.miduo.financialmanageclient.listener.GesturesPasswordListener;
import com.miduo.financialmanageclient.util.ImageDownLoadUtil;
import com.miduo.financialmanageclient.util.JsonUtils;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.SharePrefUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.widget.CircleImageView;
import com.miduo.financialmanageclient.widget.GesturesPasswordViewNew;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

public class GesturePasswordNewActivity extends Activity {
	private CircleImageView headerImg;
	/* 密码输入盘 */
	private GesturesPasswordViewNew large_gesture;
	/* 保存密码的List */
	private List<Integer> list;
	/* 上部显示文字 */
	private TextView top;
	private LoginAsyncTask loginAsyncTask;
	private TextView telText;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication.home_refresh = true;
		setContentView(R.layout.activity_gesture_password_new);

		/* 获取SharedPreferences保存的密码 */
		list = (List<Integer>) SharePrefUtil.getObj(this, SharePrefUtil.GESTURE);

		initView();
		initHeader();
	}

	private void initHeader() {
		// TODO Auto-generated method stub
		String tel = SharePrefUtil.getString(GesturePasswordNewActivity.this, SharePrefUtil.ACCOUNT_MOBILE, "");
		if (!StringUtil.isEmpty(tel)) {
			telText.setText(tel.substring(0, 3) + "****" + tel.substring(7, 11));
		}
		String filePath = SharePrefUtil.getString(GesturePasswordNewActivity.this, SharePrefUtil.ACCOUNT_HEADER, null);
		if (!StringUtil.isEmpty(filePath)) {
			headerImg.setTag(filePath);
			ImageDownLoadUtil.setImageBitmap(headerImg, filePath);
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("输入手势密码"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("输入手势密码"); //
		MobclickAgent.onPause(this);
	}

	/* 输入错误的次数 */
	private int ERROR_SUM = 0;

	private void initView() {
		telText = (TextView) findViewById(R.id.tel_txt);
		headerImg = (CircleImageView) findViewById(R.id.head_circleimg);
		large_gesture = (GesturesPasswordViewNew) findViewById(R.id.gesture);
		top = (TextView) findViewById(R.id.txttop);
		large_gesture.setListener(new GesturesPasswordListener() {

			@Override
			public void getGesturesPassword(List<Integer> lists) {
				if (list.equals(lists)) {// 手势密码输入正确
					// 获取登录状态
					UserInfo userInfo = (UserInfo) SharePrefUtil.getObj(GesturePasswordNewActivity.this,
							SharePrefUtil.USER_INFO);
					if(userInfo==null){
						SharePrefUtil.clearKey(GesturePasswordNewActivity.this);						
						Intent intent = new Intent(GesturePasswordNewActivity.this, LoginActivity.class);
						startActivity(intent);
						finish();
						return;
					}
					if (userInfo.getTestBindIfa() != null && userInfo.getTestBindIfa().intValue() == 1) {						
						Intent intent = new Intent();						
						MyApplication.getInstance().home_index = 0;
						MyApplication.home_refresh = true;
						MyApplication.test_value = true;
						intent.setClass(GesturePasswordNewActivity.this, HomeActivity.class);
						startActivity(intent);
						finish();
					} else {
						String loginName = SharePrefUtil.getString(GesturePasswordNewActivity.this,
								SharePrefUtil.ACCOUNT_MOBILE, "");
						String loginPwd = SharePrefUtil.getString(GesturePasswordNewActivity.this,
								SharePrefUtil.ACCOUNT_PSD, "");
						ProgressDialogUtil.showProgress(GesturePasswordNewActivity.this);
						if (loginAsyncTask != null) {
							loginAsyncTask.cancel(true);
							loginAsyncTask = null;
						}
						loginAsyncTask = new LoginAsyncTask(loginName, loginPwd);
						loginAsyncTask.execute();
					}

				} else {// 手势密码输入错误
					ERROR_SUM++;// 错误次数+1
					if (ERROR_SUM == 5) {
						/**
						 * 错误次数为5次时，设置为无手势密码，并跳转到登录页面
						 */
						SharePrefUtil.clearKey(GesturePasswordNewActivity.this);						
						Intent intent = new Intent(GesturePasswordNewActivity.this, LoginActivity.class);
						startActivity(intent);
						finish();
						return;
					}

					large_gesture.setList(lists, false);
					/**
					 * 输入错误的提示
					 */
					top.setText("密码输入错误，还可以输入" + (5 - ERROR_SUM) + "次");
					TranslateAnimation animation = new TranslateAnimation(0, -20, 0, 0);
					animation.setInterpolator(new OvershootInterpolator());
					animation.setDuration(50);
					animation.setRepeatCount(5);
					animation.setRepeatMode(Animation.REVERSE);
					top.startAnimation(animation);

					new Thread() {
						public void run() {
							try {
								Thread.sleep(400);
								List<Integer> tempList = new ArrayList<Integer>();
								large_gesture.setList(tempList, true);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						};
					}.start();
				}
			}

			@Override
			public void changeGesturesPassword(List<Integer> list) {

			}
		});
	}

	private class LoginAsyncTask extends AsyncTask<Void, Void, String> {

		private String _mobile;
		private String _password;
		private AppException ex;

		public LoginAsyncTask(String mobile, String password) {
			this._mobile = mobile;
			this._password = password;
		}

		@Override
		protected String doInBackground(Void... arg0) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("mobile", _mobile);
			map.put("password", _password);
			try {
				ex = null;
				return WebServiceClient.login(map);
			} catch (AppException e) {
				// TODO Auto-generated catch block
				this.ex = e;
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			ProgressDialogUtil.closeProgress();
			if (null != result) {
				try {
					JSONObject jo = new JSONObject(result);
					int state = jo.getInt("state");
					if (state == ConstantValues.LOGIN_ERROR) {
						String msgStr = jo.getString("msg");
						MDialog.showPsdErrorDialog(GesturePasswordNewActivity.this, msgStr);
						return;
					} else if (state == 1) {
						String data = jo.getString("data");
						UserInfo userInfo = JsonUtils.toBean(data, new TypeToken<UserInfo>() {
						}.getType());
						SharePrefUtil.saveString(GesturePasswordNewActivity.this, SharePrefUtil.ACCOUNT_HEADER,
								userInfo.getAvatars());
						SharePrefUtil.saveObj(GesturePasswordNewActivity.this, SharePrefUtil.USER_INFO, userInfo);
						if (userInfo.getTestBindIfa() != null && userInfo.getTestBindIfa().intValue() == 1) {
							Intent intent = new Intent();
							MyApplication.getInstance().home_index = 0;
							MyApplication.home_refresh = true;
							MyApplication.test_value = true;
							intent.setClass(GesturePasswordNewActivity.this, HomeActivity.class);
							startActivity(intent);
							finish();
						} else {
							Intent intent = new Intent(GesturePasswordNewActivity.this,
									ReplaceFinancailPlannerActivity.class);
							intent.putExtra("is_turn", true);
							startActivity(intent);
							finish();
						}
					} else {
						Intent intent = new Intent(GesturePasswordNewActivity.this,
								ReplaceFinancailPlannerActivity.class);
						intent.putExtra("is_turn", true);
						startActivity(intent);
						finish();
					}
				} catch (Exception e) {
					e.printStackTrace();
					Intent intent = new Intent(GesturePasswordNewActivity.this, ReplaceFinancailPlannerActivity.class);
					intent.putExtra("is_turn", true);
					startActivity(intent);
					finish();
				}
			} else {
				Intent intent = new Intent(GesturePasswordNewActivity.this, ReplaceFinancailPlannerActivity.class);
				intent.putExtra("is_turn", true);
				startActivity(intent);
				finish();
			}
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
	}

}
