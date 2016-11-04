package com.miduo.financialmanageclient.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.umeng.analytics.MobclickAgent;

public class BuyConfirmActivity extends BaseActivity implements OnClickListener {

	private ImageView left_img;
	private TextView right_txt;
	private TextView name, zr_date, price, big_price;
	private TextView org_name_txt, address_txt, card_code_txt, name_txt;
	private TextView mobile_txt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buyconfirm);
		init();
		initEvent();
		initDate();

	}

	private void init() {
		left_img = (ImageView) findViewById(R.id.left_img);
		right_txt = (TextView) findViewById(R.id.right_txt);
		TextView title_txt = (TextView) findViewById(R.id.title_txt);
		title_txt.setText("投资转让单");

		name = (TextView) findViewById(R.id.name);
		zr_date = (TextView) findViewById(R.id.zr_date);
		price = (TextView) findViewById(R.id.price);
		big_price = (TextView) findViewById(R.id.big_price);

		org_name_txt = (TextView) findViewById(R.id.org_name_txt);
		address_txt = (TextView) findViewById(R.id.address_txt);
		card_code_txt = (TextView) findViewById(R.id.card_code_txt);
		name_txt = (TextView) findViewById(R.id.name_txt);

		mobile_txt = (TextView) findViewById(R.id.mobile_txt);

	}

	private void initEvent() {
		left_img.setOnClickListener(this);
		right_txt.setOnClickListener(this);
	}

	private void initDate() {
		right_txt.setText("转让协议");
		name.setText("米多财富，米多财富，米多财富米多财富米多财富米多财富米多财富");
		zr_date.setText("2014-13-13  12:34:31");
		price.setText("3,122,221.00");
		big_price.setText("三百二十万两千三百二十四");

		org_name_txt.setText("建设银行");
		address_txt.setText("北京市南二环支行");
		card_code_txt.setText("3918");
		name_txt.setText("赵静远");

		mobile_txt.setText("15600014387");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("购买转让单 确认支付");
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageStart("转购买转让单 确认支付");
		MobclickAgent.onResume(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_img:
			finish();
			break;
		case R.id.right_txt:
			startActivity(new Intent(this, BuyAddBankActivity.class));
			break;
		default:
			break;
		}
	}
}
