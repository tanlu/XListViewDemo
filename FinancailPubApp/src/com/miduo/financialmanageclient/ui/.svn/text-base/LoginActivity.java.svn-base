package com.miduo.financialmanageclient.ui;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.UserInfo;
import com.miduo.financialmanageclient.util.JsonUtils;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.SharePrefUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

public class LoginActivity extends BaseActivity implements OnClickListener {
	private EditText edtName, edtPwd;
	private TextView login, register, turnin_txt, find_psd_txt;
	private ImageView tel_img, password_img;
	private LoginAsyncTask loginAsyncTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		initEvent();
		initData();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("登录页面"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("登录页面"); //
		MobclickAgent.onPause(this);
	}

	private void initView() {
		// TODO Auto-generated method stub
		edtName = (EditText) findViewById(R.id.edtName);
		edtPwd = (EditText) findViewById(R.id.edtPwd);
		register = (TextView) findViewById(R.id.register);
		turnin_txt = (TextView) findViewById(R.id.turnin_txt);
		login = (TextView) findViewById(R.id.login);
		find_psd_txt = (TextView) findViewById(R.id.find_psd_txt);
		tel_img = (ImageView) findViewById(R.id.tel_img);
		password_img = (ImageView) findViewById(R.id.password_img);
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		register.setOnClickListener(this);
		turnin_txt.setOnClickListener(this);
		login.setOnClickListener(this);
		find_psd_txt.setOnClickListener(this);

		edtName.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if (hasFocus) {
					tel_img.setImageResource(R.drawable.tel_sel);
				} else {
					tel_img.setImageResource(R.drawable.tel_unsel);
				}
			}
		});
		edtPwd.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if (hasFocus) {
					password_img.setImageResource(R.drawable.psd_sel);
				} else {
					password_img.setImageResource(R.drawable.psd_unsel);
				}
			}
		});
	}

	private void initData() {
		// TODO Auto-generated method stub
		String preMobile = SharePrefUtil.getString(this, SharePrefUtil.MIDUO_PUB_INFO, SharePrefUtil.ACCOUNT_MOBILE,
				null);
		if (!StringUtil.isEmpty(preMobile)) {
			edtName.setText(preMobile);
			edtName.setSelection(edtName.getText().length());
			edtPwd.requestFocus();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.find_psd_txt:
			intent.setClass(LoginActivity.this, FindPsdActivity.class);
			startActivity(intent);
			break;
		case R.id.register:
			intent.setClass(LoginActivity.this, RegisterActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.turnin_txt:
			MyApplication.home_refresh = true;
			MyApplication.getInstance().home_index = 0;
			MyApplication.test_value = true;
			intent.setClass(LoginActivity.this, HomeActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.login:
			String loginName = edtName.getText().toString().trim();
			String loginPwd = edtPwd.getText().toString().trim();
			if (TextUtils.isEmpty(loginName) || TextUtils.isEmpty(loginPwd)) {
				MToast.showToast(this, "请填写完整的登录信息");
				return;
			}

			ProgressDialogUtil.showProgress(LoginActivity.this);
			if (loginAsyncTask != null) {
				loginAsyncTask.cancel(true);
				loginAsyncTask = null;
			}
			loginAsyncTask = new LoginAsyncTask(loginName, loginPwd);
			loginAsyncTask.execute();

			// SharePrefUtil.saveString(LoginActivity.this,
			// SharePrefUtil.MIDUO_PUB_INFO,
			// SharePrefUtil.ACCOUNT_MOBILE, loginName);
			// SharePrefUtil.saveString(LoginActivity.this,
			// SharePrefUtil.ACCOUNT_PSD, loginPwd);
			// SharePrefUtil.saveBoolean(LoginActivity.this,
			// SharePrefUtil.IS_LOGIN, true);
			//
			// intent = new Intent(LoginActivity.this,
			// SetGestureNewActivity.class);
			// startActivity(intent);
			// finish();
			break;
		default:
			break;
		}
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
			if (null == result) {
				if (ex != null) {
					ex.makeToast(LoginActivity.this);
				} else {
					MToast.showToast(LoginActivity.this, "登录失败");
				}
				return;
			} else {
				try {
					JSONObject jo = new JSONObject(result);
					int state = jo.getInt("state");
					if (state == 1) {
						SharePrefUtil.saveString(LoginActivity.this, SharePrefUtil.MIDUO_PUB_INFO,
								SharePrefUtil.ACCOUNT_MOBILE, _mobile);
						SharePrefUtil.saveString(LoginActivity.this, SharePrefUtil.ACCOUNT_PSD, _password);
						SharePrefUtil.saveString(LoginActivity.this, SharePrefUtil.ACCOUNT_MOBILE, _mobile);

						SharePrefUtil.saveBoolean(LoginActivity.this, SharePrefUtil.IS_LOGIN, true);

						String data = jo.getString("data");
						UserInfo userInfo = JsonUtils.toBean(data, new TypeToken<UserInfo>() {
						}.getType());
						SharePrefUtil.saveString(LoginActivity.this, SharePrefUtil.ACCOUNT_HEADER,
								userInfo.getAvatars());
						SharePrefUtil.saveObj(LoginActivity.this, SharePrefUtil.USER_INFO, userInfo);
						Intent intent = new Intent(LoginActivity.this, SetGestureNewActivity.class);
						startActivity(intent);
						finish();
					} else {
						String msg = jo.getString("msg");
						MToast.showToast(LoginActivity.this, StringUtil.isEmpty(msg) ? "登录失败" : msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
					MToast.showToast(LoginActivity.this, "数据异常");
				}
			}
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
	}
}
