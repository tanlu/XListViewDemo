package com.miduo.financialmanageclient.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.StringUtil;
import com.umeng.analytics.MobclickAgent;

public class InsureOneActivity extends BaseActivity implements OnClickListener {
	private ImageView left_img;
	private TextView title_txt;
	private TextView next_txt;
	private EditText nianling;
	private EditText shouru;
	private EditText zhichu;
	private RadioButton peiou_y, peiou_w;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insureone);
		init();
		initEvent();
	}

	private void init() {
		left_img = (ImageView) findViewById(R.id.left_img);
		title_txt = (TextView) findViewById(R.id.title_txt);
		title_txt.setText("如何投保");
		next_txt = (TextView) findViewById(R.id.next_txt);
		TextView right_txt = (TextView) findViewById(R.id.right_txt);
		right_txt.setVisibility(View.GONE);

		nianling = (EditText) findViewById(R.id.nianling);
		shouru = (EditText) findViewById(R.id.shouru);
		zhichu = (EditText) findViewById(R.id.zhichu);
		peiou_y = (RadioButton) findViewById(R.id.peiou_y);
		peiou_w = (RadioButton) findViewById(R.id.peiou_w);
	}

	private void initEvent() {
		left_img.setOnClickListener(this);
		next_txt.setOnClickListener(this);
		nianling.setOnFocusChangeListener(new OnFocusChangeEvent());
		shouru.setOnFocusChangeListener(new OnFocusChangeEvent());
		zhichu.setOnFocusChangeListener(new OnFocusChangeEvent());
	}

	private class OnFocusChangeEvent implements OnFocusChangeListener {
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			// TODO Auto-generated method stub
			if (!hasFocus) {
				switch (v.getId()) {
				case R.id.nianling:
					// 年龄
					if (!checkAge()) {
						MToast.showToast(getApplicationContext(), "测试年龄应介于18~65岁之间");
					}
					break;
				case R.id.shouru:
					// 收入
					if (!checkIncome()) {
						MToast.showToast(getApplicationContext(), "年收入应在0~9999万之间");
					}
					break;
				case R.id.zhichu:
					// 支出
					if (!checkSupport()) {
						MToast.showToast(getApplicationContext(), "年支出应在0~9999万之间");
					}
					break;
				default:
					break;
				}
			}
		}
	}

	private boolean checkAge() {
		String str = nianling.getText().toString();
		if (StringUtil.isEmpty(str)) {
			return false;
		}
		int data = Integer.parseInt(str);
		if (data < 18 || data > 65) {
			return false;
		}
		return true;
	}

	private boolean checkIncome() {
		String str = shouru.getText().toString();
		if (StringUtil.isEmpty(str)) {
			return false;
		}
		int data = Integer.parseInt(str);
		if (data < 0 || data > 9999) {
			return false;
		}
		return true;
	}

	private boolean checkSupport() {
		String str = zhichu.getText().toString();
		if (StringUtil.isEmpty(str)) {
			return false;
		}
		int data = Integer.parseInt(str);
		if (data < 0 || data > 9999) {
			return false;
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_img:
			saveInfo();
			finish();
			break;
		case R.id.next_txt:
			if (!checkAge()) {
				MToast.showToast(getApplicationContext(), "请正确输入信息");
				return;
			} else if (!checkIncome()) {
				MToast.showToast(getApplicationContext(), "请正确输入信息");
				return;
			} else if (!checkSupport()) {
				MToast.showToast(getApplicationContext(), "请正确输入信息");
				return;
			}
			saveInfo();
			Intent intent = new Intent();
			intent.setClass(this, InsureTwoActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("如何投保页面1");
		MobclickAgent.onResume(this);

		if (MyApplication.insureBean != null) {
			if (MyApplication.insureBean.getManAge() != null) {
				nianling.setText(MyApplication.insureBean.getManAge().toString());						
			} else {
				nianling.setText("");
			}
			nianling.requestFocus();
			nianling.setSelection(nianling.getText().length());		

			if (MyApplication.insureBean.getManIncome() != null) {
				shouru.setText(MyApplication.insureBean.getManIncome().toString());
			} else {
				shouru.setText("");
			}
			if (MyApplication.insureBean.getManOutlay() != null) {
				zhichu.setText(MyApplication.insureBean.getManOutlay().toString());
			} else {
				zhichu.setText("");
			}
			if (MyApplication.insureBean.getManIsHasSs()) {
				peiou_y.setChecked(true);
				peiou_w.setChecked(false);
			} else {
				peiou_y.setChecked(false);
				peiou_w.setChecked(true);
			}
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageStart("如何投保页面1");
		MobclickAgent.onPause(this);
	}

	private void saveInfo() {
		String age = nianling.getText().toString();
		String income = shouru.getText().toString();
		String support = zhichu.getText().toString();
		if (StringUtil.isEmpty(age)) {
			MyApplication.insureBean.setManAge(null);
		} else {
			MyApplication.insureBean.setManAge(Integer.valueOf(age));
		}
		if (StringUtil.isEmpty(income)) {
			MyApplication.insureBean.setManIncome(null);
		} else {
			MyApplication.insureBean.setManIncome(Integer.valueOf(income));
		}
		if (StringUtil.isEmpty(support)) {
			MyApplication.insureBean.setManOutlay(null);
		} else {
			MyApplication.insureBean.setManOutlay(Integer.valueOf(support));
		}
		if (peiou_y.isChecked()) {
			MyApplication.insureBean.setManIsHasSs(true);
		} else {
			MyApplication.insureBean.setManIsHasSs(false);
		}
	}

}
