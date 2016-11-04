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

public class InvestPlannThreeActivity extends BaseActivity implements OnClickListener {
	private ImageView leftImg;
	private TextView titleTxt;
	private TextView rightTxt;
	private TextView next_txt;
	private EditText invest_amount_txt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invest_plann_three);

		initUi();
		initEvent();
		initData();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("如何投资第三题"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
		if (MyApplication.investBean != null) {
			if (MyApplication.investBean.getInvestmentFunds() != null) {
				invest_amount_txt.setText(MyApplication.investBean.getInvestmentFunds().toString());
				invest_amount_txt.setSelection(invest_amount_txt.getText().length());
			}
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("如何投资第三题"); //
		MobclickAgent.onPause(this);
	}

	private void initUi() {
		// TODO Auto-generated method stub
		leftImg = (ImageView) findViewById(R.id.left_img);
		titleTxt = (TextView) findViewById(R.id.title_txt);
		rightTxt = (TextView) findViewById(R.id.right_txt);
		next_txt = (TextView) findViewById(R.id.next_txt);
		invest_amount_txt = (EditText) findViewById(R.id.invest_amount_txt);
		titleTxt.setText("如何投资");
		rightTxt.setVisibility(View.GONE);
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		leftImg.setOnClickListener(this);
		next_txt.setOnClickListener(this);

		invest_amount_txt.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if (!hasFocus) {
					checkInvestAmount();
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
			String amount = invest_amount_txt.getText().toString();
			if (StringUtil.isEmpty(amount)) {
				MyApplication.investBean.setInvestmentFunds(null);
			} else {
				MyApplication.investBean.setInvestmentFunds(new BigDecimal(amount));
			}
			finish();
			break;
		case R.id.next_txt:
			if (checkInvestAmount()) {
				MyApplication.investBean.setInvestmentFunds(new BigDecimal(invest_amount_txt.getText().toString()));
				Intent intent = new Intent(this, InvestPlannFourActivity.class);
				startActivity(intent);
			}
			break;
		default:
			break;
		}
	}

	private boolean checkInvestAmount() {
		// TODO Auto-generated method stub
		String investAmount = invest_amount_txt.getText().toString();
		if (StringUtil.isEmpty(investAmount)) {
			MToast.showToast(this, "年收入应在0~9999万之间");
			invest_amount_txt.requestFocus();
			return false;
		}
		int investAmountInt = Integer.valueOf(investAmount);
		if (investAmountInt > 9999) {
			MToast.showToast(this, "年收入应在0~9999万之间");
			invest_amount_txt.requestFocus();
			return false;
		}
		return true;
	}
}
