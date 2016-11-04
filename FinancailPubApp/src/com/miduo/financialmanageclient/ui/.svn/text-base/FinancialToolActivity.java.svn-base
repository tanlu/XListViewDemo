package com.miduo.financialmanageclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.umeng.analytics.MobclickAgent;

public class FinancialToolActivity extends GesterSetBaseActivity implements OnClickListener {
	private ImageView leftImg;
	private TextView titleTxt;
	private TextView rightTxt;
	private ImageView invest_btn, insure_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_financial_tool);

		initUi();
		initEvent();
		initData();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("理财工具"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("理财工具"); //
		MobclickAgent.onPause(this);
	}

	private void initUi() {
		// TODO Auto-generated method stub
		leftImg = (ImageView) findViewById(R.id.left_img);
		titleTxt = (TextView) findViewById(R.id.title_txt);
		rightTxt = (TextView) findViewById(R.id.right_txt);
		invest_btn = (ImageView) findViewById(R.id.invest_btn);
		insure_btn = (ImageView) findViewById(R.id.insure_btn);
		titleTxt.setText("理财工具");
		rightTxt.setVisibility(View.GONE);
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		leftImg.setOnClickListener(this);
		invest_btn.setOnClickListener(this);
		insure_btn.setOnClickListener(this);
	}

	private void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.left_img:
			finish();
			break;
		case R.id.invest_btn:
			intent.setClass(this, InvestPlannActivity.class);
			startActivity(intent);
			break;
		case R.id.insure_btn:
			// intent.setClass(this, InsurePlannActivity.class);
			intent.setClass(this, InsurePlannActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
