package com.miduo.financialmanageclient.ui;

import java.math.BigDecimal;

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

public class InvestPlannTwoActivity extends BaseActivity implements OnClickListener {
	private ImageView leftImg;
	private TextView titleTxt;
	private TextView rightTxt;
	private TextView next_txt;
	private EditText income_txt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invest_plann_two);

		initUi();
		initEvent();
		initData();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("如何投资第二题"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
		if (MyApplication.investBean != null) {
			if (MyApplication.investBean.getAftertaxIncome() != null) {
				income_txt.setText(MyApplication.investBean.getAftertaxIncome().toString());
				income_txt.setSelection(income_txt.getText().length());
			}
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("如何投资第二题"); //
		MobclickAgent.onPause(this);
	}

	private void initUi() {
		// TODO Auto-generated method stub
		leftImg = (ImageView) findViewById(R.id.left_img);
		titleTxt = (TextView) findViewById(R.id.title_txt);
		rightTxt = (TextView) findViewById(R.id.right_txt);
		next_txt = (TextView) findViewById(R.id.next_txt);
		income_txt = (EditText) findViewById(R.id.income_txt);
		titleTxt.setText("如何投资");
		rightTxt.setVisibility(View.GONE);
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		leftImg.setOnClickListener(this);
		next_txt.setOnClickListener(this);

		income_txt.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if (!hasFocus) {
					checkIncome();
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
			String income = income_txt.getText().toString();
			if (StringUtil.isEmpty(income)) {
				MyApplication.investBean.setAftertaxIncome(null);
			} else {
				MyApplication.investBean.setAftertaxIncome(new BigDecimal(income));
			}
			finish();
			break;
		case R.id.next_txt:
			if (checkIncome()) {
				MyApplication.investBean.setAftertaxIncome(new BigDecimal(income_txt.getText().toString()));
				Intent intent = new Intent(this, InvestPlannThreeActivity.class);
				startActivity(intent);
			}
			break;
		default:
			break;
		}
	}

	private boolean checkIncome() {
		// TODO Auto-generated method stub
		String income = income_txt.getText().toString();
		if (StringUtil.isEmpty(income)) {
			MToast.showToast(this, "年收入应在0~9999万之间");
			income_txt.requestFocus();
			return false;
		}
		int incomeInt = Integer.valueOf(income);
		if (incomeInt > 9999) {
			MToast.showToast(this, "年收入应在0~9999万之间");
			income_txt.requestFocus();
			return false;
		}
		return true;
	}
}
