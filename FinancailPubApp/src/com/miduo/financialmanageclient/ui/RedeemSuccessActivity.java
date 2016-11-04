package com.miduo.financialmanageclient.ui;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.util.StringUtil;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class RedeemSuccessActivity extends GesterSetBaseActivity implements OnClickListener {
	private TextView confirmTxt;
	private ImageView leftImg;
	private TextView titleTxt;
	private TextView rightTxt;
	private TextView commite_txt, wait_txt, success_txt;
	private ImageView commite_img, wait_img, success_img;
	private View wait_line, success_line;
	private String completeColor = "#2ea7e0";
	private String waitColor = "#999999";
	private String lineCompleteColor = "#2ea7e0";
	private String lineWaitColor = "#cbcbcb";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_redeem_success);
		initUi();
		initEvent();
		initData();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("赎回结果"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("赎回结果"); //
		MobclickAgent.onPause(this);
	}

	private void initUi() {
		// TODO Auto-generated method stub

		leftImg = (ImageView) findViewById(R.id.left_img);
		leftImg.setVisibility(View.GONE);
		titleTxt = (TextView) findViewById(R.id.title_txt);
		rightTxt = (TextView) findViewById(R.id.right_txt);
		titleTxt.setText("赎回结果");
		rightTxt.setVisibility(View.GONE);
		confirmTxt = (TextView) findViewById(R.id.btn_txt);

		commite_txt = (TextView) findViewById(R.id.commite_txt);
		wait_txt = (TextView) findViewById(R.id.wait_txt);
		success_txt = (TextView) findViewById(R.id.success_txt);
		wait_line = (View) findViewById(R.id.wait_line);
		success_line = (View) findViewById(R.id.success_line);

		commite_img = (ImageView) findViewById(R.id.commite_img);
		wait_img = (ImageView) findViewById(R.id.wait_img);
		success_img = (ImageView) findViewById(R.id.success_img);

	}

	private void initEvent() {
		// TODO Auto-generated method stub
		confirmTxt.setOnClickListener(this);
		leftImg.setOnClickListener(this);
	}

	private void initData() {
		// TODO Auto-generated method stub
		commite_txt.setTextColor(Color.parseColor(completeColor));
		commite_img.setImageResource(R.drawable.blue_commite);

		wait_txt.setTextColor(Color.parseColor(waitColor));
		wait_img.setImageResource(R.drawable.grey_wait);
		wait_line.setBackgroundColor(Color.parseColor(lineWaitColor));

		success_txt.setTextColor(Color.parseColor(waitColor));
		success_img.setImageResource(R.drawable.grey_success);
		success_line.setBackgroundColor(Color.parseColor(lineWaitColor));
		if (getIntent().hasExtra("redeem_desc")) {
			String redeem_desc = getIntent().getStringExtra("redeem_desc");
			wait_txt.setText("米多平台正在焦急等待银行对账信息，成功赎回后您的资金将在" + StringUtil.showStringContent(redeem_desc)
					+ "个工作日内到达您的指定的收款账号");
		} else {
			wait_txt.setText("米多平台正在焦急等待银行对账信息，成功赎回后资金将汇入您的银行卡");
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_txt:
			MyApplication.getInstance().home_index = 2;
			Intent intent = new Intent(RedeemSuccessActivity.this, HomeActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.left_img:
			finish();
			break;
		default:
			break;
		}
	}
}
