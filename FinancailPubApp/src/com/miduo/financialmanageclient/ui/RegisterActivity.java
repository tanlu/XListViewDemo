package com.miduo.financialmanageclient.ui;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.ReturnMsg;
import com.miduo.financialmanageclient.bean.UserInfo;
import com.miduo.financialmanageclient.util.JsonUtils;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.SharePrefUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

public class RegisterActivity extends BaseActivity implements OnClickListener {
	private EditText telTxt, graphicCodeTxt, identifyCodeTxt, newPasswordTxt, confirmPasswordTxt, invitate_code_txt;
	private ImageView tel_img, pic_validate_img, code_validate_img, new_password_img, confirm_password_img,
			invitate__img, graphic_code_img;
	private TextView identifyCodeBtn, login_txt, sel_ifa_txt;
	private TextView saveTxt;
	private int maxTime = 120;
	private int time = maxTime;
	private TextView register_agree_txt;
	private Handler pic_hdl;
	private RegisterAsyncTask registerAsyncTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		pic_hdl = new PicHandler();

		initUi();
		initEvent();
		initData();
	}

	class PicHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			// String s = (String)msg.obj;
			// ptv.setText(s);
			ProgressDialogUtil.closeProgress();
			Bitmap myimg = (Bitmap) msg.obj;
			if (myimg == null) {
				graphic_code_img.setScaleType(ScaleType.FIT_CENTER);
				graphic_code_img.setImageResource(R.drawable.default_pic);
			} else {
				graphic_code_img.setScaleType(ScaleType.FIT_XY);
				graphic_code_img.setImageBitmap(myimg);
			}
		}
	}

	private void initData() {
		// TODO Auto-generated method stub
		ProgressDialogUtil.showProgress(RegisterActivity.this);
		new Thread() {
			public void run() {
				Bitmap img = null;
				try {
					img = WebServiceClient.getUrlImage();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Message msg = pic_hdl.obtainMessage();
				msg.what = 0;
				msg.obj = img;
				pic_hdl.sendMessage(msg);
			};
		}.start();

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("注册"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("注册"); //
		MobclickAgent.onPause(this);
	}

	private void initUi() {
		// TODO Auto-generated method stub
		telTxt = (EditText) findViewById(R.id.tel_txt);
		graphicCodeTxt = (EditText) findViewById(R.id.graphic_code_txt);
		identifyCodeTxt = (EditText) findViewById(R.id.identify_code_txt);
		newPasswordTxt = (EditText) findViewById(R.id.new_password_txt);
		confirmPasswordTxt = (EditText) findViewById(R.id.confirm_password_txt);
		invitate_code_txt = (EditText) findViewById(R.id.invitate_code_txt);

		tel_img = (ImageView) findViewById(R.id.tel_img);
		pic_validate_img = (ImageView) findViewById(R.id.pic_validate_img);
		code_validate_img = (ImageView) findViewById(R.id.code_validate_img);
		new_password_img = (ImageView) findViewById(R.id.new_password_img);
		confirm_password_img = (ImageView) findViewById(R.id.confirm_password_img);
		invitate__img = (ImageView) findViewById(R.id.invitate__img);
		graphic_code_img = (ImageView) findViewById(R.id.graphic_code_img);

		identifyCodeBtn = (TextView) findViewById(R.id.identify_code_btn);
		login_txt = (TextView) findViewById(R.id.login_txt);
		sel_ifa_txt = (TextView) findViewById(R.id.sel_ifa_txt);

		saveTxt = (TextView) findViewById(R.id.register_txt);
		login_txt = (TextView) findViewById(R.id.login_txt);
		register_agree_txt = (TextView) findViewById(R.id.agree_txt);
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		telTxt.setOnFocusChangeListener(new FocusChangeEvent(0));
		graphicCodeTxt.setOnFocusChangeListener(new FocusChangeEvent(1));
		identifyCodeTxt.setOnFocusChangeListener(new FocusChangeEvent(2));
		newPasswordTxt.setOnFocusChangeListener(new FocusChangeEvent(3));
		confirmPasswordTxt.setOnFocusChangeListener(new FocusChangeEvent(4));
		invitate_code_txt.setOnFocusChangeListener(new FocusChangeEvent(5));
		saveTxt.setOnClickListener(this);
		identifyCodeBtn.setOnClickListener(this);
		sel_ifa_txt.setOnClickListener(this);
		login_txt.setOnClickListener(this);
		register_agree_txt.setOnClickListener(this);
		graphic_code_img.setOnClickListener(this);
	}

	class FocusChangeEvent implements OnFocusChangeListener {
		int index = 0;

		FocusChangeEvent(int index) {
			this.index = index;
		}

		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			// TODO Auto-generated method stub
			if (hasFocus) {
				switch (index) {
				case 0:
					tel_img.setImageResource(R.drawable.tel_sel);
					break;
				case 1:
					pic_validate_img.setImageResource(R.drawable.pic_volidate_sel);
					break;
				case 2:
					code_validate_img.setImageResource(R.drawable.code_validate_sel);
					break;
				case 3:
					new_password_img.setImageResource(R.drawable.psd_sel);
					break;
				case 4:
					confirm_password_img.setImageResource(R.drawable.psd_sel);
					break;
				case 5:
					invitate__img.setImageResource(R.drawable.invitate_sel);
					break;
				default:
					break;
				}
			} else {
				switch (index) {
				case 0:
					tel_img.setImageResource(R.drawable.tel_unsel);
					break;
				case 1:
					pic_validate_img.setImageResource(R.drawable.pic_volidate_unsel);
					break;
				case 2:
					code_validate_img.setImageResource(R.drawable.code_validate_unsel);
					break;
				case 3:
					new_password_img.setImageResource(R.drawable.psd_unsel);
					break;
				case 4:
					confirm_password_img.setImageResource(R.drawable.psd_unsel);
					break;
				case 5:
					invitate__img.setImageResource(R.drawable.invitate_unsel);
					break;
				default:
					break;
				}
			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.register_txt:
			checkInfo();
			break;
		case R.id.identify_code_btn:
			String mobile = telTxt.getText().toString();
			String graphicCode = graphicCodeTxt.getText().toString();
			if (!StringUtil.isMobileNO(mobile)) {
				MToast.showToast(RegisterActivity.this, "请输入有效的手机号码！");
				return;
			}
			if (TextUtils.isEmpty(graphicCode)) {
				MToast.showToast(RegisterActivity.this, "请输入正确的图文验证码！");
				return;
			}
			getCode(mobile, graphicCode);
			break;
		case R.id.sel_ifa_txt:
			intent.setClass(RegisterActivity.this, SelectFinancialPlannerActivity.class);
			startActivityForResult(intent, 0x01);
			break;
		case R.id.login_txt:
			intent.setClass(RegisterActivity.this, LoginActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.agree_txt:
			intent.setClass(RegisterActivity.this, AgreementActivity.class);
			intent.putExtra("agree_type", 0);
			startActivity(intent);
			break;
		case R.id.graphic_code_img:
			initData();
			break;
		default:
			break;
		}
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case 9999:
				time--;
				identifyCodeBtn.setText(getString(R.string.sendValidate) + time + "秒");
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
		identifyCodeBtn.setText(getString(R.string.gainValidate));
		identifyCodeBtn.setEnabled(true);
		identifyCodeBtn.setBackgroundResource(R.drawable.button_bg_blue);
	}

	private void getCode(String mobile, String picCode) {
		time = maxTime;
		identifyCodeBtn.setEnabled(false);
		identifyCodeBtn.setBackgroundResource(R.drawable.button_bg_gray);
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
					map.put("checkcode", params[1]);
					map.put("obtainCode", "0");
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
						ex.makeToast(RegisterActivity.this);
					} else {
						MToast.showToast(RegisterActivity.this, "获取验证码失败！");
					}
					time = 1;
					return;
				} else {
					if (result.getState() != 1) {
						MToast.showToast(RegisterActivity.this, StringUtil.isEmpty(result.getMsg()) ? "获取验证码失败！"
								: result.getMsg());
						time = 1;
					}
				}
				super.onPostExecute(result);
			}

		}.execute(mobile, picCode);
	}

	private void checkInfo() {
		// TODO Auto-generated method stub
		String mobile = telTxt.getText().toString();
		String graphicCode = graphicCodeTxt.getText().toString().trim();
		String identifyCode = identifyCodeTxt.getText().toString().trim();
		String newPassword = newPasswordTxt.getText().toString().trim();
		String confirmPassword = confirmPasswordTxt.getText().toString().trim();
		String invitateCode = invitate_code_txt.getText().toString().trim();
		if (TextUtils.isEmpty(mobile) || TextUtils.isEmpty(graphicCode) || TextUtils.isEmpty(identifyCode)
				|| TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(confirmPassword)
				|| TextUtils.isEmpty(invitateCode)) {
			MToast.showToast(RegisterActivity.this, "输入不能为空");
			return;
		}
		if (!StringUtil.isMobileNO(mobile)) {
			MToast.showToast(RegisterActivity.this, "请输入有效的手机号码！");
			return;
		}
		if (newPassword.length() < 6 || newPassword.length() > 18) {
			MToast.showToast(RegisterActivity.this, "请输入6~18位密码！");
			return;
		}
		if (!newPassword.equals(confirmPassword)) {
			MToast.showToast(RegisterActivity.this, "密码不一致，请确认！");
			return;
		}
		ProgressDialogUtil.showProgress(RegisterActivity.this);
		if (registerAsyncTask != null) {
			registerAsyncTask.cancel(true);
			registerAsyncTask = null;
		}
		registerAsyncTask = new RegisterAsyncTask(mobile, identifyCode, newPassword, confirmPassword, invitateCode);
		registerAsyncTask.execute();

	}

	private class RegisterAsyncTask extends AsyncTask<Void, Void, String> {

		private String _mobile;
		private String _code;
		private String _password;
		private String _refPwd;
		private String _ifaCode;
		private AppException ex;

		public RegisterAsyncTask(String mobile, String code, String password, String refPwd, String ifaCode) {
			this._mobile = mobile;
			this._code = code;
			this._password = password;
			this._refPwd = refPwd;
			this._ifaCode = ifaCode;
		}

		@Override
		protected String doInBackground(Void... arg0) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("mobile", _mobile);
			map.put("code", _code);
			map.put("password", _password);
			map.put("refPwd", _refPwd);
			map.put("ifaCode", _ifaCode);
			map.put("source", "3");

			try {
				ex = null;
				return WebServiceClient.register(map);
			} catch (AppException e) {
				this.ex = e;
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			ProgressDialogUtil.closeProgress();
			if (null == result) {
				if (ex != null) {
					ex.makeToast(RegisterActivity.this);
				} else {
					MToast.showToast(RegisterActivity.this, "注册失败！");
				}
				return;
			} else {
				try {
					JSONObject jo = new JSONObject(result);
					int state = jo.getInt("state");
					if (state == 1) {
						MToast.showToast(RegisterActivity.this, "注册成功");
						SharePrefUtil.saveString(RegisterActivity.this, SharePrefUtil.MIDUO_PUB_INFO,
								SharePrefUtil.ACCOUNT_MOBILE, _mobile);
						SharePrefUtil.saveString(RegisterActivity.this, SharePrefUtil.ACCOUNT_PSD, _password);
						SharePrefUtil.saveBoolean(RegisterActivity.this, SharePrefUtil.IS_LOGIN, true);
						SharePrefUtil.saveString(RegisterActivity.this, SharePrefUtil.ACCOUNT_MOBILE, _mobile);
						String data = jo.getString("data");
						if (!StringUtil.isEmpty(data)) {
							UserInfo userInfo = JsonUtils.toBean(data, new TypeToken<UserInfo>() {
							}.getType());
							userInfo.setIdentityAuth(0);
							SharePrefUtil.saveString(RegisterActivity.this, SharePrefUtil.ACCOUNT_HEADER,
									userInfo.getAvatars());
							SharePrefUtil.saveObj(RegisterActivity.this, SharePrefUtil.USER_INFO, userInfo);
						}
						Intent intent = new Intent(RegisterActivity.this, SetGestureNewActivity.class);
						startActivity(intent);
						finish();
						
						
						
						
						
						
					} else {
						String msg = jo.getString("msg");
						MToast.showToast(RegisterActivity.this, StringUtil.isEmpty(msg) ? "注册失败" : msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
					MToast.showToast(RegisterActivity.this, "数据异常");
				}
			}
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			invitate_code_txt.setText(data.getStringExtra("PLANNER_CODE"));
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}
