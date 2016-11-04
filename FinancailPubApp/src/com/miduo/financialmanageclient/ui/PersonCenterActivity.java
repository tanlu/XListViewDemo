package com.miduo.financialmanageclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.common.CommonUtil;
import com.miduo.financialmanageclient.util.SocialShareUtil;
import com.umeng.analytics.MobclickAgent;

public class PersonCenterActivity extends GesterSetBaseActivity implements OnClickListener {
	private ImageView leftImg, person_img5_2;
	private TextView titleTxt, person_txt5_2;
	private TextView rightTxt;
	private RelativeLayout my_fa_layout, financial_tool_layout, account_safe_layout, about_layout, identy_layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_center);
		initUi();
		initEvent();
		initData();
	}

	private void initData() {
		// TODO Auto-generated method stub
		if (CommonUtil.checkIdentify(this)) {
			person_img5_2.setVisibility(View.GONE);
			person_txt5_2.setVisibility(View.VISIBLE);
		} else {
			person_img5_2.setVisibility(View.VISIBLE);
			person_txt5_2.setVisibility(View.GONE);
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("个人中心"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("个人中心"); //
		MobclickAgent.onPause(this);
	}

	private void initUi() {
		// TODO Auto-generated method stub
		leftImg = (ImageView) findViewById(R.id.left_img);
		person_img5_2 = (ImageView) findViewById(R.id.person_img5_2);
		person_txt5_2 = (TextView) findViewById(R.id.person_txt5_2);
		titleTxt = (TextView) findViewById(R.id.title_txt);
		rightTxt = (TextView) findViewById(R.id.right_txt);
		titleTxt.setText("个人中心");
		rightTxt.setVisibility(View.GONE);
		my_fa_layout = (RelativeLayout) findViewById(R.id.my_fa_layout);
		financial_tool_layout = (RelativeLayout) findViewById(R.id.financial_tool_layout);
		account_safe_layout = (RelativeLayout) findViewById(R.id.account_safe_layout);
		about_layout = (RelativeLayout) findViewById(R.id.about_layout);
		identy_layout = (RelativeLayout) findViewById(R.id.identy_layout);
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		leftImg.setOnClickListener(this);
		my_fa_layout.setOnClickListener(this);
		financial_tool_layout.setOnClickListener(this);
		account_safe_layout.setOnClickListener(this);
		about_layout.setOnClickListener(this);
		identy_layout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.left_img:
			finish();
			break;
		case R.id.my_fa_layout:
			// 我的理财顾问
			startActivity(new Intent(this, MyFaActivity.class));
			break;
		case R.id.financial_tool_layout:
			// 财务工具
			intent.setClass(this, FinancialToolActivity.class);
			startActivity(intent);
			break;
		case R.id.account_safe_layout:
			// 账户安全
			intent.setClass(this, AccountSafeActivity.class);
			startActivity(intent);
			break;
		case R.id.about_layout:
			// 关于我们
			break;
		case R.id.identy_layout:
			if (CommonUtil.checkIdentify(this)) {
				return;
			}
			intent.setClass(this, IdentityActivity.class);
			startActivityForResult(intent, 1001);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1001 && resultCode == RESULT_OK) {
			person_img5_2.setVisibility(View.GONE);
			person_txt5_2.setVisibility(View.VISIBLE);
		}
	}
}
