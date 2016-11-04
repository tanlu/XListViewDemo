package com.miduo.financialmanageclient.ui;

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

public class InvestPlannOneActivity extends BaseActivity implements OnClickListener {
	private ImageView leftImg;
	private TextView titleTxt;
	private TextView rightTxt;
	private TextView next_txt;
	private EditText age_txt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invest_plann_one);
		initUi();
		initEvent();
		initData();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("如何投资第一题"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长

		if (MyApplication.investBean != null) {
			if (MyApplication.investBean.getAge() != null) {
				age_txt.setText(MyApplication.investBean.getAge().toString());
				age_txt.setSelection(age_txt.getText().length());
			}
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("如何投资第一题"); //
		MobclickAgent.onPause(this);
	}

	private void initUi() {
		// TODO Auto-generated method stub
		leftImg = (ImageView) findViewById(R.id.left_img);
		titleTxt = (TextView) findViewById(R.id.title_txt);
		rightTxt = (TextView) findViewById(R.id.right_txt);
		next_txt = (TextView) findViewById(R.id.next_txt);
		age_txt = (EditText) findViewById(R.id.age_txt);
		titleTxt.setText("如何投资");
		rightTxt.setVisibility(View.GONE);
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		leftImg.setOnClickListener(this);
		next_txt.setOnClickListener(this);

		age_txt.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if (!hasFocus) {
					checkAge();
				}
			}
		});
	}

	private void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.left_img:
			finish();
			break;
		case R.id.next_txt:
			if (checkAge()) {
				MyApplication.investBean.setAge(Integer.valueOf(age_txt.getText().toString()));
				Intent intent = new Intent(this, InvestPlannTwoActivity.class);
				startActivity(intent);
			}
			break;
		default:
			break;
		}
	}

	private boolean checkAge() {
		// TODO Auto-generated method stub
		String age = age_txt.getText().toString();
		if (StringUtil.isEmpty(age)) {
			MToast.showToast(this, "测算年龄应在18~100岁之间");
			age_txt.requestFocus();
			return false;
		}
		int ageInt = Integer.valueOf(age);
		if (ageInt < 18 || ageInt > 100) {
			MToast.showToast(this, "测算年龄应在18~100岁之间");
			age_txt.requestFocus();
			return false;
		}
		return true;
	}
}
