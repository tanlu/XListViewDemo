package com.miduo.financialmanageclient.ui;

import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.DialogBean;
import com.miduo.financialmanageclient.listener.DialogEventListener;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.SharePrefUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

public class AccountSafeActivity extends GesterSetBaseActivity implements OnClickListener {
	private ImageView leftImg;
	private TextView titleTxt;
	private TextView rightTxt;
	private TextView btn_txt;
	private RelativeLayout login_psd_layout, gesture_psd_layout;
	private CheckBox set_push_checked;
	private boolean open = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_safe);

		initUi();
		initEvent();
		initData();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("账户安全"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("账户安全"); //
		MobclickAgent.onPause(this);
	}

	private void initUi() {
		// TODO Auto-generated method stub
		leftImg = (ImageView) findViewById(R.id.left_img);
		titleTxt = (TextView) findViewById(R.id.title_txt);
		rightTxt = (TextView) findViewById(R.id.right_txt);
		titleTxt.setText("账户安全");
		rightTxt.setVisibility(View.GONE);
		login_psd_layout = (RelativeLayout) findViewById(R.id.login_psd_layout);
		gesture_psd_layout = (RelativeLayout) findViewById(R.id.gesture_psd_layout);
		btn_txt = (TextView) findViewById(R.id.btn_txt);
		set_push_checked = (CheckBox) findViewById(R.id.set_push_checked);
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		leftImg.setOnClickListener(this);
		login_psd_layout.setOnClickListener(this);
		gesture_psd_layout.setOnClickListener(this);
		btn_txt.setOnClickListener(this);

		set_push_checked.setOnClickListener(this);
	}

	private void initData() {
		// TODO Auto-generated method stub #999999 未开启
		open = SharePrefUtil.getBoolean(this, SharePrefUtil.MSG_ON_OFF, true);
		set_push_checked.setChecked(open);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		DialogBean data;
		DialogEventListener lister;
		Intent intent = new Intent();
		DialogBean dialog = new DialogBean();
		String tel = SharePrefUtil.getString(this, SharePrefUtil.MIDUO_PUB_INFO, SharePrefUtil.ACCOUNT_MOBILE, null);
		switch (v.getId()) {
		case R.id.left_img:
			finish();
			break;
		case R.id.login_psd_layout:
			// 修改登录密码
			intent.setClass(this, FindPsdActivity.class);
			intent.putExtra("tel", tel);
			startActivity(intent);
			break;
		case R.id.gesture_psd_layout:
			// 修改手势密码
			data = new DialogBean();
			data.setTitle("请输入登录密码");
			data.setContent(tel);
			data.setSubmit("确定");
			data.setCancel("取消");
			lister = new DialogEventListener() {

				@Override
				public void submit() {
					// TODO Auto-generated method stub
					// 跳转手势密码设置页面
					Intent intent = new Intent(AccountSafeActivity.this, SetGestureNewActivity.class);
					intent.putExtra("order", 1);
					startActivity(intent);
				}

				@Override
				public void cancel() {
					// TODO Auto-generated method stub

				}
			};
			data.setDialogEvent(lister);
			MDialog.showDialogInput(this, data);
			break;
		case R.id.set_push_checked:
			// 接收推送消息
			open = !open;
			set_push_checked.setChecked(open);
			SharePrefUtil.saveBoolean(this, SharePrefUtil.MSG_ON_OFF, open);
			break;
		case R.id.btn_txt:

			data = new DialogBean();
			data.setContent("您确定要退出当前登录吗？");
			data.setCancel("取消");
			data.setSubmit("确定");
			lister = new DialogEventListener() {

				@Override
				public void submit() {
					// TODO Auto-generated method stub
					loginOff();
				}

				@Override
				public void cancel() {
					// TODO Auto-generated method stub

				}
			};
			data.setDialogEvent(lister);
			MDialog.showDialog2(this, data);
			break;
		default:
			break;
		}
	}

	private void loginOff() {
		new AsyncTask<String, Integer, String>() {
			private AppException ex;

			@Override
			protected String doInBackground(String... params) {
				try {
					ex = null;
					return WebServiceClient.loginoff();
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
						ex.makeToast(AccountSafeActivity.this);
						return;
					}
					Toast.makeText(AccountSafeActivity.this, "退出账户失败！", Toast.LENGTH_SHORT).show();
					return;
				} else {
					try {
						// {"state":1,"msg":null,"data":{"proccess":0.00354,"amount":354.0,"distance":99646.0}}
						JSONObject jo = new JSONObject(result);
						int state = jo.getInt("state");
						if (state == ConstantValues.LOGIN_ERROR) {
							String msg = jo.getString("msg");
							MDialog.showPsdErrorDialog(AccountSafeActivity.this, msg);
							return;
						} else if (state == 1) {
							SharePrefUtil.clearKey(AccountSafeActivity.this);
							Intent intent = new Intent(AccountSafeActivity.this, LoginActivity.class);
							startActivity(intent);
							finish();
						} else {
							String msg = jo.getString("msg");
							Toast.makeText(AccountSafeActivity.this, StringUtil.isEmpty(msg) ? "退出账户失败！" : msg,
									Toast.LENGTH_SHORT).show();
						}
					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(AccountSafeActivity.this, "退出账户失败！", Toast.LENGTH_SHORT).show();
					}
				}
				super.onPostExecute(result);
			}

		}.execute();
	}
}