package com.miduo.financialmanageclient.ui;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.ReturnMsg;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.SharePrefUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

public class FindPsdActivity extends BaseActivity implements OnClickListener {
	private ImageView leftImg;
	private TextView titleTxt;
	private TextView rightTxt;
	private EditText tel_txt, identify_code_txt, new_password_txt, confirm_password_txt;
	private TextView identify_code_btn, btn_txt;
	private int maxTime = 120;
	private int time = maxTime;
	private UpdatePwdAsyncTask updatePwdAsyncTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_psd);
		initUi();
		initEvent();
		initData();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("修改或找回登录密码"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("修改或找回登录密码"); //
		MobclickAgent.onPause(this);
	}

	private void initUi() {
		// TODO Auto-generated method stub
		leftImg = (ImageView) findViewById(R.id.left_img);
		titleTxt = (TextView) findViewById(R.id.title_txt);
		rightTxt = (TextView) findViewById(R.id.right_txt);
		titleTxt.setText("修改或找回登录密码");
		rightTxt.setVisibility(View.GONE);

		tel_txt = (EditText) findViewById(R.id.tel_txt);
		identify_code_txt = (EditText) findViewById(R.id.identify_code_txt);
		new_password_txt = (EditText) findViewById(R.id.new_password_txt);
		confirm_password_txt = (EditText) findViewById(R.id.confirm_password_txt);

		identify_code_btn = (TextView) findViewById(R.id.identify_code_btn);
		btn_txt = (TextView) findViewById(R.id.btn_txt);
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		btn_txt.setOnClickListener(this);
		leftImg.setOnClickListener(this);
		identify_code_btn.setOnClickListener(this);
	}

	private void initData() {
		// TODO Auto-generated method stub
		if (getIntent().hasExtra("tel")) {
			tel_txt.setText(getIntent().getStringExtra("tel"));
			tel_txt.setEnabled(false);
			identify_code_txt.requestFocus();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.left_img:
			finish();
			break;
		case R.id.btn_txt:
			checkInfo();
			break;
		case R.id.identify_code_btn:
			String mobile = tel_txt.getText().toString();
			if (!StringUtil.isMobileNO(mobile)) {
				MToast.showToast(FindPsdActivity.this, "请输入有效的手机号码！");
				return;
			}
			getCode(mobile);
			break;
		default:
			break;
		}
	}

	private void checkInfo() {
		// TODO Auto-generated method stub
		String mobile = tel_txt.getText().toString();
		String identifyCode = identify_code_txt.getText().toString().trim();
		String newPassword = new_password_txt.getText().toString().trim();
		String confirmPassword = confirm_password_txt.getText().toString().trim();
		if (TextUtils.isEmpty(mobile) || TextUtils.isEmpty(identifyCode) || TextUtils.isEmpty(newPassword)
				|| TextUtils.isEmpty(confirmPassword)) {
			MToast.showToast(FindPsdActivity.this, "输入不能为空");
			return;
		}
		if (!StringUtil.isMobileNO(mobile)) {
			MToast.showToast(FindPsdActivity.this, "请输入有效的手机号码！");
			return;
		}
		if (newPassword.length() < 6 || newPassword.length() > 18) {
			MToast.showToast(FindPsdActivity.this, "请输入6~18位密码！");
			return;
		}
		if (!newPassword.equals(confirmPassword)) {
			MToast.showToast(FindPsdActivity.this, "密码不一致，请确认！");
			return;
		}
		ProgressDialogUtil.showProgress(FindPsdActivity.this);
		if (updatePwdAsyncTask != null) {
			updatePwdAsyncTask.cancel(true);
			updatePwdAsyncTask = null;
		}
		updatePwdAsyncTask = new UpdatePwdAsyncTask(mobile, identifyCode, newPassword, confirmPassword);
		updatePwdAsyncTask.execute();
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case 9999:
				time--;
				identify_code_btn.setText(getString(R.string.sendValidate) + time + "秒");
				if (time == 0) {
					handler.sendEmptyMessage(1111);
				}
				break;
			case 1111:
				resetValidateTxt();
				break;
			default:
				break;
			}
		}
	};

	private void resetValidateTxt() {
		identify_code_btn.setText(R.string.gainValidate);
		identify_code_btn.setEnabled(true);
		identify_code_btn.setBackgroundResource(R.drawable.button_bg_blue);
	}

	private void getCode(String mobile) {
		time = maxTime;
		identify_code_btn.setEnabled(false);
		identify_code_btn.setBackgroundResource(R.drawable.button_bg_gray);
		new Thread() {
			public void run() {
				while (time > 0) {
					handler.sendEmptyMessage(9999);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
		new AsyncTask<String, Integer, ReturnMsg>() {
			private AppException ex;

			@Override
			protected ReturnMsg doInBackground(String... params) {
				try {
					ex = null;
					Map<String, String> map = new HashMap<String, String>();
					map.put("mobile", params[0]);
					map.put("obtainCode", "1");
					return WebServiceClient.getAppCode(map);
				} catch (AppException e) {
					this.ex = e;
				}
				return null;
			}

			@Override
			protected void onPostExecute(ReturnMsg result) {
				if (null == result) {
					if (ex != null) {
						ex.makeToast(FindPsdActivity.this);
					} else {
						MToast.showToast(FindPsdActivity.this, "获取验证码失败！");
					}
					time = 1;
					return;
				} else {
					if (result.getState() == ConstantValues.LOGIN_ERROR) {
						MDialog.showPsdErrorDialog(FindPsdActivity.this, result.getMsg());
						return;
					} else if (result.getState() != 1) {
						MToast.showToast(FindPsdActivity.this, StringUtil.isEmpty(result.getMsg()) ? "获取验证码失败！"
								: result.getMsg());
						time = 1;
					}
				}
				super.onPostExecute(result);
			}

		}.execute(mobile);
	}

	private class UpdatePwdAsyncTask extends AsyncTask<Void, Void, ReturnMsg> {

		private String _mobile;
		private String _identifyCode;
		private String _newPassword;
		private String _confirmPassword;
		private AppException ex;

		public UpdatePwdAsyncTask(String mobile, String identifyCode, String newPassword, String confirmPassword) {
			this._mobile = mobile;
			this._identifyCode = identifyCode;
			this._newPassword = newPassword;
			this._confirmPassword = confirmPassword;
		}

		@Override
		protected ReturnMsg doInBackground(Void... arg0) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("mobile", _mobile);
			map.put("code", _identifyCode);
			map.put("password", _newPassword);
			map.put("refPwd", _confirmPassword);
			try {
				ex = null;
				return WebServiceClient.updateLoginPsd(map);
			} catch (AppException e) {
				this.ex = e;
			}
			return null;
		}

		@Override
		protected void onPostExecute(ReturnMsg result) {
			ProgressDialogUtil.closeProgress();

			if (null == result) {
				if (ex != null) {
					ex.makeToast(FindPsdActivity.this);
				} else {
					MToast.showToast(FindPsdActivity.this, "找回修改登录密码失败！");
				}
				return;
			} else {
				if (result.getState() == 1) {
					MToast.showToast(FindPsdActivity.this, "登录密码修改成功，请牢记您的登录密码！");
					if (getIntent().hasExtra("tel")) {
						SharePrefUtil.clearKey(FindPsdActivity.this);
					}
					Intent intent = new Intent(FindPsdActivity.this, LoginActivity.class);
					startActivity(intent);
					finish();
				} else {
					MToast.showToast(FindPsdActivity.this,
							StringUtil.isEmpty(result.getMsg()) ? "找回修改登录密码失败！" : result.getMsg());
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
