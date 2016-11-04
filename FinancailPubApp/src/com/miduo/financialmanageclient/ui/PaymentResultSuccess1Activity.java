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
import com.umeng.analytics.MobclickAgent;
/**
 * 支付结果成功页面
 * @author huozhenpeng
 *
 */
public class PaymentResultSuccess1Activity extends GesterSetBaseActivity implements OnClickListener{

	private TextView text_title;
	private ImageView iv_left;
	private TextView  right_text;
	private TextView tv_iknow;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_paymentsuccess1);
		initView();
		initEvent();
		initData();
	}
	
	@Override
	protected void onResume() {
		super.onResume();		
		MobclickAgent.onPageStart("支付成功结果"); //统计页面
	    MobclickAgent.onResume(this);          //统计时长
	}

	@Override
	protected void onPause() {
		super.onPause();		
		MobclickAgent.onPageEnd("支付成功结果"); //  
	    MobclickAgent.onPause(this);
	}

	private void initData() {

		text_title.setText("支付结果");
	}

	private void initEvent() {

		tv_iknow.setOnClickListener(this);
	}

	private void initView() {

		text_title=(TextView) this.findViewById(R.id.title_txt);
		iv_left=(ImageView) this.findViewById(R.id.left_img);
		right_text=(TextView) this.findViewById(R.id.right_txt);
		iv_left.setVisibility(View.INVISIBLE);
		right_text.setVisibility(View.INVISIBLE);
		tv_iknow=(TextView) this.findViewById(R.id.tv_iknow);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_iknow:
			MyApplication.finishPurchaseActivity();
			MyApplication.getInstance().home_index = 2;
			Intent intent = new Intent(this,HomeActivity.class);			
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}
	}


}
