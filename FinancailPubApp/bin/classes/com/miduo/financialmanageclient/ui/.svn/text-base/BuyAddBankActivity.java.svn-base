package com.miduo.financialmanageclient.ui;

import com.miduo.financialmanageclient.R;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class BuyAddBankActivity extends GesterSetBaseActivity implements OnClickListener{

	private ImageView left_img;
	private TextView right_txt;
	private TextView name, zr_date, price, big_price;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buyaddbank);
		init();
		initEvent();
		initDate();
	}

	private void init() {
		left_img = (ImageView) findViewById(R.id.left_img);
		right_txt = (TextView) findViewById(R.id.right_txt);
		TextView title_txt = (TextView) findViewById(R.id.title_txt);
		title_txt.setText("添加银行卡");
		
		name = (TextView) findViewById(R.id.name);
		zr_date = (TextView) findViewById(R.id.zr_date);
		price = (TextView) findViewById(R.id.price);
		big_price = (TextView) findViewById(R.id.big_price);
		
	}

	private void initEvent() {
		left_img.setOnClickListener(this);
	}

	private void initDate() {
		right_txt.setText("使用已有账户");
		
		name.setText("米多财富，米多财富，米多财富米多财富米多财富米多财富米多财富");
		zr_date.setText("2014-13-13  12:34:31");
		price.setText("3,122,221.00");
		big_price.setText("三百二十万两千三百二十四");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("购买转让单 添加银行卡");
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageStart("购买转让单 添加银行卡");
		MobclickAgent.onResume(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_img:
			finish();
			break;

		default:
			break;
		}
	}

}
