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
import android.widget.TextView;

public class NewAccountActivity extends GesterSetBaseActivity implements OnClickListener{
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_newaccount);
		
		iniUI();
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();		
		MobclickAgent.onPageStart("新增收款账户"); //统计页面
	    MobclickAgent.onResume(this);          //统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();		
		MobclickAgent.onPageEnd("新增收款账户"); //  
	    MobclickAgent.onPause(this);
	}

	private void iniUI() {
		TextView title_txt = (TextView) findViewById(R.id.title_txt);
		title_txt.setText("新增收款账户");

		TextView right_txt = (TextView) findViewById(R.id.right_txt);
		right_txt.setVisibility(View.GONE);
		
		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button:
			
			break;

		default:
			break;
		}
	}
}
