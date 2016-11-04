package com.miduo.financialmanageclient.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.StringUtil;
import com.umeng.analytics.MobclickAgent;

public class InsureTwoActivity extends BaseActivity implements OnClickListener {
	private ImageView left_img;
	private TextView title_txt;
	private TextView next_txt;
	private RadioButton peiou_you;
	private RadioButton peiou_wu;
	private RadioButton shebao_you;
	private RadioButton shebao_wu;
	private EditText nianling;
	private EditText shouru;
	private String str1;
	private String str2;
	private String str3;
	private EditText zhichu;
	private int a = 0, b = 0, c = 0, data, data1, data2;
	private RelativeLayout rl_1;
	private RelativeLayout rl_2;
	private RelativeLayout rl_3;
	private LinearLayout rl_4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insuretwo);
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
		rl_1 = (RelativeLayout) findViewById(R.id.rl_1);
		rl_2 = (RelativeLayout) findViewById(R.id.rl_2);
		rl_3 = (RelativeLayout) findViewById(R.id.rl_3);
		rl_4 = (LinearLayout) findViewById(R.id.rl_4);

		// 有
		peiou_you = (RadioButton) findViewById(R.id.peiou_you);
		shebao_you = (RadioButton) findViewById(R.id.shebao_you);
		// 无
		peiou_wu = (RadioButton) findViewById(R.id.peiou_wu);
		shebao_wu = (RadioButton) findViewById(R.id.shebao_wu);

	}

	private void initEvent() {
		left_img.setOnClickListener(this);
		next_txt.setOnClickListener(this);
		nianling.setOnFocusChangeListener(new OnFocusChangeEvent());
		shouru.setOnFocusChangeListener(new OnFocusChangeEvent());
		zhichu.setOnFocusChangeListener(new OnFocusChangeEvent());
		peiou_wu.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// 选中
				if (isChecked) {
					rl_1.setVisibility(View.GONE);
					rl_2.setVisibility(View.GONE);
					rl_3.setVisibility(View.GONE);
					rl_4.setVisibility(View.GONE);
					nianling.setText("");
					shouru.setText("");
					zhichu.setText("");
					shebao_you.setChecked(true);
				} else {
					rl_1.setVisibility(View.VISIBLE);
					rl_2.setVisibility(View.VISIBLE);
					rl_3.setVisibility(View.VISIBLE);
					rl_4.setVisibility(View.VISIBLE);
				}
			}
		});
	}

	private class OnFocusChangeEvent implements OnFocusChangeListener {
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			// TODO Auto-generated method stub
			if (!hasFocus) {
				if(peiou_wu.isChecked()){
					return;
				}
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
			if (peiou_you.isChecked()) {
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
			}
			saveInfo();
			Intent intent = new Intent();
			intent.setClass(this, InsureThreeActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	private void saveInfo() {
		// TODO Auto-generated method stub
		if (peiou_you.isChecked()) {
			String age = nianling.getText().toString();
			String income = shouru.getText().toString();
			String support = zhichu.getText().toString();
			if (StringUtil.isEmpty(age)) {
				MyApplication.insureBean.setWifeAge(null);
			} else {
				MyApplication.insureBean.setWifeAge(Integer.valueOf(age));
			}
			if (StringUtil.isEmpty(income)) {
				MyApplication.insureBean.setWifeIncome(null);
			} else {
				MyApplication.insureBean.setWifeIncome(Integer.valueOf(income));
			}
			if (StringUtil.isEmpty(support)) {
				MyApplication.insureBean.setWifeOutlay(null);
			} else {
				MyApplication.insureBean.setWifeOutlay(Integer.valueOf(support));
			}
			if (shebao_you.isChecked()) {
				MyApplication.insureBean.setWifeIsHasSs(true);
			} else {
				MyApplication.insureBean.setWifeIsHasSs(false);
			}
			MyApplication.insureBean.setHasWife(true);
		} else {
			MyApplication.insureBean.setWifeAge(null);
			MyApplication.insureBean.setWifeIncome(null);
			MyApplication.insureBean.setWifeOutlay(null);
			MyApplication.insureBean.setWifeIsHasSs(false);
			MyApplication.insureBean.setHasWife(false);
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("如何投保页面2");
		MobclickAgent.onResume(this);

		if (MyApplication.insureBean != null) {
			if (MyApplication.insureBean.isHasWife()) {
				rl_1.setVisibility(View.VISIBLE);
				rl_2.setVisibility(View.VISIBLE);
				rl_3.setVisibility(View.VISIBLE);
				rl_4.setVisibility(View.VISIBLE);
				if (MyApplication.insureBean.getWifeAge() != null) {
					nianling.setText(MyApplication.insureBean.getWifeAge().toString());
				} else {
					nianling.setText("");
				}
				nianling.requestFocus();
				nianling.setSelection(nianling.getText().length());	

				if (MyApplication.insureBean.getWifeIncome() != null) {
					shouru.setText(MyApplication.insureBean.getWifeIncome().toString());
				} else {
					shouru.setText("");
				}
				if (MyApplication.insureBean.getWifeOutlay() != null) {
					zhichu.setText(MyApplication.insureBean.getWifeOutlay().toString());
				} else {
					zhichu.setText("");
				}
				if (MyApplication.insureBean.getWifeIsHasSs()) {
					shebao_you.setChecked(true);
					shebao_wu.setChecked(false);
				} else {
					shebao_you.setChecked(false);
					shebao_wu.setChecked(true);
				}
				peiou_you.setChecked(true);
			}else {
				rl_1.setVisibility(View.GONE);
				rl_2.setVisibility(View.GONE);
				rl_3.setVisibility(View.GONE);
				rl_4.setVisibility(View.GONE);
				nianling.setText("");
				shouru.setText("");
				zhichu.setText("");
				shebao_you.setChecked(true);
				peiou_wu.setChecked(true);
			}
		} 
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageStart("如何投保页面2");
		MobclickAgent.onPause(this);
	}
}
