package com.miduo.financialmanageclient.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.StringUtil;
import com.umeng.analytics.MobclickAgent;

public class InsureFiveActivity extends BaseActivity implements OnClickListener {
	private ImageView left_img;
	private TextView title_txt;
	private TextView next_txt;
	private EditText ketouzichan;
	private EditText fuzhai;
	private int data1, data2, a = 0, b = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insurefive);
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

		ketouzichan = (EditText) findViewById(R.id.ketouzichan);
		fuzhai = (EditText) findViewById(R.id.fuzhai);
	}

	private void initEvent() {
		left_img.setOnClickListener(this);
		next_txt.setOnClickListener(this);
		ketouzichan.setOnFocusChangeListener(new OnFocusChangeEvent());
		fuzhai.setOnFocusChangeListener(new OnFocusChangeEvent());
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_img:
			saveInfo();
			finish();
			break;
		case R.id.next_txt:
			if (!checkMoney(ketouzichan.getText().toString())) {
				MToast.showToast(getApplicationContext(), "请正确输入信息");
				return;
			} else if (!checkMoney(fuzhai.getText().toString())) {
				MToast.showToast(getApplicationContext(), "请正确输入信息");
				return;
			}
			saveInfo();
			Intent intent = new Intent();
			intent.setClass(this, InsurePlannResultActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	private class OnFocusChangeEvent implements OnFocusChangeListener {
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			// TODO Auto-generated method stub
			if (!hasFocus) {
				switch (v.getId()) {
				case R.id.ketouzichan:
					// 可投资资产
					if (!checkMoney(ketouzichan.getText().toString())) {
						MToast.showToast(getApplicationContext(), "可投资资产应在0~9999万之间");
					}
					break;
				case R.id.fuzhai:
					// 负债
					if (!checkMoney(fuzhai.getText().toString())) {
						MToast.showToast(getApplicationContext(), "负债应在0~9999万之间");
					}
					break;
				default:
					break;
				}
			}
		}
	}

	private boolean checkMoney(String money) {
		if (StringUtil.isEmpty(money)) {
			return false;
		}
		int data = Integer.parseInt(money);
		if (data < 0 || data > 9999) {
			return false;
		}
		return true;
	}

	private void saveInfo() {
		// TODO Auto-generated method stub
		String zichan = ketouzichan.getText().toString();
		String dept = fuzhai.getText().toString();
		if (StringUtil.isEmpty(zichan)) {
			MyApplication.insureBean.setManInvestMoney(null);
		} else {
			MyApplication.insureBean.setManInvestMoney(Integer.valueOf(zichan));
		}
		if (StringUtil.isEmpty(dept)) {
			MyApplication.insureBean.setManIndebtMoney(null);
		} else {
			MyApplication.insureBean.setManIndebtMoney(Integer.valueOf(dept));
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("如何投保页面5");
		MobclickAgent.onResume(this);

		if (MyApplication.insureBean != null) {
			if (MyApplication.insureBean.getManInvestMoney() != null) {
				ketouzichan.setText(MyApplication.insureBean.getManInvestMoney().toString());
			} else {
				ketouzichan.setText("");
			}

			if (MyApplication.insureBean.getManIndebtMoney() != null) {
				fuzhai.setText(MyApplication.insureBean.getManIndebtMoney().toString());
			} else {
				fuzhai.setText("");
			}
			ketouzichan.requestFocus();
			ketouzichan.setSelection(ketouzichan.getText().length());	
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageStart("如何投保页面5");
		MobclickAgent.onPause(this);
	}
}
