package com.miduo.financialmanageclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.util.StringUtil;
import com.umeng.analytics.MobclickAgent;

public class SuccessfulActivity extends GesterSetBaseActivity implements OnClickListener {
	private TextView tv_iknow;
	private TextView tv_interestDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_successful);

		init();
		iniEvent();
		initDate();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("转让结果"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("转让结果"); //
		MobclickAgent.onPause(this);
	}

	private void initDate() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		String interestDate = intent.getStringExtra("interestDate");
		tv_interestDate.setText("成交后转让金额将在" + StringUtil.showStringContent(interestDate)
				+ "个工作日内转入您指定的收款账户，您可以在“米仓”-“米多总资产”-“历史订单”中查看已赎回的产品");
	}

	private void iniEvent() {
		// TODO Auto-generated method stub
		tv_iknow.setOnClickListener(this);
	}

	private void init() {
		ImageView left_img = (ImageView) findViewById(R.id.left_img);
		left_img.setVisibility(View.GONE);
		TextView title_txt = (TextView) findViewById(R.id.title_txt);
		title_txt.setText("转让结果");

		TextView right_txt = (TextView) findViewById(R.id.right_txt);
		right_txt.setVisibility(View.GONE);

		tv_iknow = (TextView) findViewById(R.id.tv_iknow);

		tv_interestDate = (TextView) findViewById(R.id.tv_interestDate);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_iknow:
			MyApplication.getInstance().home_index = 2;
			Intent intent = new Intent(this, HomeActivity.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}
	}

}
