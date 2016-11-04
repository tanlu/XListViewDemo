package com.miduo.financialmanageclient.ui;

import com.miduo.financialmanageclient.R;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AffirmRolloutActivity extends BaseActivity implements OnClickListener {
	private TextView tv_01;
	private TextView tv_02;
	private TextView tv_03;
	private TextView tv_04;
	private TextView tv_05;
	private TextView btn_txt;
	private ImageView left_img;
	private TextView  org_name_txt, address_txt, card_code_txt,
	name_txt, mobile_txt;
	private TextView msm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_affirm_rollout);
		initUI();
		initEvent();
		initDate();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();		
		MobclickAgent.onPageStart("确认转让订单"); //统计页面
	    MobclickAgent.onResume(this);          //统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();		
		MobclickAgent.onPageEnd("确认转让订单"); //  
	    MobclickAgent.onPause(this);
	}

	private void initEvent() {
		
		left_img.setOnClickListener(this);
		btn_txt.setOnClickListener(this);
	}

	private void initDate() {
		tv_01.setText("9.05" + "%");
		tv_02.setText("3,222,333,22" + "元");
		tv_03.setText("22,333,88" + "元");
		tv_04.setText("432" + "天");
		tv_05.setText("2015-07-19");

		org_name_txt.setText("建设银行");
		address_txt.setText("北京市南二环支行");
		card_code_txt.setText("3918");
		name_txt.setText("赵静远");
		mobile_txt.setText("13810493663");

	}

	private void initUI() {

		TextView title_txt = (TextView) findViewById(R.id.title_txt);
		title_txt.setText("确认转让订单");
		left_img = (ImageView) findViewById(R.id.left_img);
		TextView right_txt = (TextView) findViewById(R.id.right_txt);
		right_txt.setVisibility(View.GONE);
		msm.setVisibility(View.GONE);

		tv_01 = (TextView) findViewById(R.id.tv_01);
		tv_02 = (TextView) findViewById(R.id.tv_02);
		tv_03 = (TextView) findViewById(R.id.tv_03);
		tv_04 = (TextView) findViewById(R.id.tv_04);
		tv_05 = (TextView) findViewById(R.id.tv_05);

		btn_txt = (TextView) findViewById(R.id.btn_txt);
		org_name_txt = (TextView) findViewById(R.id.org_name_txt);
		address_txt = (TextView) findViewById(R.id.address_txt);
		card_code_txt = (TextView) findViewById(R.id.card_code_txt);
		name_txt = (TextView) findViewById(R.id.name_txt);
		mobile_txt = (TextView) findViewById(R.id.mobile_txt);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_txt:
			if (true) {
				//确定转让
				startActivity(new Intent(this, SuccessfulActivity.class));
			} else {
				//新增收款账户页面
				startActivity(new Intent(this, NewAccountActivity.class));
			}
			break;
		case R.id.left_img:
			finish();
			break;

		default:
			break;
		}

	}
}
