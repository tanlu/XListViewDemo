package com.miduo.financialmanageclient.ui;

import com.miduo.financialmanageclient.R;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SystemUpgradeTipActivity extends GesterSetBaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_system_upgrade_tip);
		String desc = getIntent().getStringExtra("desc");
		((TextView) findViewById(R.id.desc_txt)).setText(desc);	
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();		
		MobclickAgent.onPageStart("系统升级提示"); //统计页面
	    MobclickAgent.onResume(this);          //统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();		
		MobclickAgent.onPageEnd("系统升级提示"); //  
	    MobclickAgent.onPause(this);
	}
}
