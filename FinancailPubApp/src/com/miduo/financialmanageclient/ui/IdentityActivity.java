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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.DialogBean;
import com.miduo.financialmanageclient.bean.UserInfo;
import com.miduo.financialmanageclient.listener.DialogEventListener;
import com.miduo.financialmanageclient.util.JsonUtils;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.SharePrefUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

public class IdentityActivity extends GesterSetBaseActivity {
	private ImageView leftImg;
	private TextView titleTxt;
	private TextView rightTxt;
	private EditText name_txt, id_txt;
	private TextView btn_txt;
	private IdentifyAsyncTask identifyAsyncTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_identity);

		ProgressDialogUtil.closeProgress();
		init();
		initEvent();
	}

	private void init() {
		// TODO Auto-generated method stub
		leftImg = (ImageView) findViewById(R.id.left_img);
		titleTxt = (TextView) findViewById(R.id.title_txt);
		titleTxt.setText("实名认证");
		rightTxt = (TextView) findViewById(R.id.right_txt);
		rightTxt.setVisibility(View.GONE);

		name_txt = (EditText) findViewById(R.id.name_txt);
		id_txt = (EditText) findViewById(R.id.id_txt);
		btn_txt = (TextView) findViewById(R.id.btn_txt);
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		leftImg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		btn_txt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String loginName = name_txt.getText().toString().trim();
				String loginPwd = id_txt.getText().toString().trim();
				if (TextUtils.isEmpty(loginName) || TextUtils.isEmpty(loginPwd)) {
					MToast.showToast(IdentityActivity.this, "请填写完整的登录信息");
					return;
				}

				ProgressDialogUtil.showProgress(IdentityActivity.this);
				if (identifyAsyncTask != null) {
					identifyAsyncTask.cancel(true);
					identifyAsyncTask = null;
				}
				identifyAsyncTask = new IdentifyAsyncTask(loginName, loginPwd);
				identifyAsyncTask.execute();
			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("实名认证页面");
		MobclickAgent.onPause(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("实名认证页面");
		MobclickAgent.onResume(this);
	}

	private class IdentifyAsyncTask extends AsyncTask<Void, Void, String> {

		private String _realName;
		private String _idCard;
		private AppException ex;

		public IdentifyAsyncTask(String realName, String idCard) {
			this._realName = realName;
			this._idCard = idCard;
		}

		@Override
		protected String doInBackground(Void... arg0) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("realName", _realName);
			map.put("idcard", _idCard);
			try {
				ex = null;
				return WebServiceClient.identify(map);
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
					ex.makeToast(IdentityActivity.this);
				} else {
					MToast.showToast(IdentityActivity.this, "实名认证失败");
				}
				return;
			} else {
				try {
					JSONObject jo = new JSONObject(result);
					int state = jo.getInt("state");
					String msg = jo.optString("msg");
					if (state == 1) {
						String data = jo.getString("data");
						UserInfo userInfoTemp = JsonUtils.toBean(data, new TypeToken<UserInfo>() {
						}.getType());
						if (userInfoTemp != null) {
							UserInfo userInfo = (UserInfo) SharePrefUtil.getObj(IdentityActivity.this,
									SharePrefUtil.USER_INFO);
							userInfo.setUserName(userInfoTemp.getUserName());
							userInfo.setIdCard(userInfoTemp.getIdCard());
							userInfo.setTestBindIfa(userInfoTemp.getTestBindIfa());
							userInfo.setIdentityAuth(1);
							SharePrefUtil.saveObj(IdentityActivity.this, SharePrefUtil.USER_INFO, userInfo);
							setResult(RESULT_OK);
							finish();
						}
					} else if (state == 2) {
						if (!StringUtil.isEmpty(msg)) {
							DialogBean dialog = new DialogBean();
							dialog.setContent(msg);
							dialog.setSubmit("确定");
							dialog.setDialogEvent(new DialogEventListener() {

								@Override
								public void submit() {
								}

								@Override
								public void cancel() {

								}
							});
							MDialog.showDialog1(IdentityActivity.this, dialog);
						}
					} else if (state == 3) {
						if (!StringUtil.isEmpty(msg)) {
							DialogBean dialog = new DialogBean();
							dialog.setContent(msg);
							dialog.setSubmit("确定");
							dialog.setDialogEvent(new DialogEventListener() {

								@Override
								public void submit() {
									SharePrefUtil.clearKey(IdentityActivity.this);
									Intent intent = new Intent(IdentityActivity.this, LoginActivity.class);
									startActivity(intent);
								}

								@Override
								public void cancel() {

								}
							});
							MDialog.showDialog1(IdentityActivity.this, dialog);
						}
					} else {
						MToast.showToast(IdentityActivity.this, StringUtil.isEmpty(msg) ? "实名认证失败" : msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
					MToast.showToast(IdentityActivity.this, "数据异常");
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
