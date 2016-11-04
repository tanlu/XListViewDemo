package com.miduo.financialmanageclient.ui;

import android.content.Intent;
import android.net.Uri;
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
 * 支付结果失败页面
 * @author huozhenpeng
 *
 */
public class PaymentResultFailureActivity extends GesterSetBaseActivity implements OnClickListener{

	private ImageView iv_left;
	private TextView text_title;
	private TextView tv_right;
	private TextView tv_repay;
	private TextView tv_callmiduo;//咨询米多客服
	private TextView tv_callbank;//咨询银行客服
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_paymentfailureresult);
		initView();
		initEvent();
		initData();
	}
	
	@Override
	protected void onResume() {
		super.onResume();		
		MobclickAgent.onPageStart("支付失败结果"); //统计页面
	    MobclickAgent.onResume(this);          //统计时长
	}

	@Override
	protected void onPause() {
		super.onPause();		
		MobclickAgent.onPageEnd("累计收益"); //  
	    MobclickAgent.onPause(this);
	}

	private void initData() {
		text_title.setText("支付失败结果");
	}

	private void initEvent() {
		tv_repay.setOnClickListener(this);
		tv_callmiduo.setOnClickListener(this);
		tv_callbank.setOnClickListener(this);
		tv_right.setOnClickListener(this);
		
	}

	private void initView() {
		iv_left=(ImageView) this.findViewById(R.id.left_img);
		iv_left.setVisibility(View.INVISIBLE);
		text_title=(TextView) this.findViewById(R.id.title_txt);
		tv_right=(TextView) this.findViewById(R.id.right_txt);	
		tv_right.setVisibility(View.VISIBLE);
		tv_right.setText("关闭");
		tv_repay=(TextView) this.findViewById(R.id.tv_repay);
		tv_callmiduo=(TextView) this.findViewById(R.id.tv_callmiduo);
		tv_callbank=(TextView) this.findViewById(R.id.tv_callbank);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_repay:
			Intent intent=new Intent(this,ProductPurchaseActivity.class);
			startActivity(intent);
			MyApplication.activityLists.add(this);
			break;
		case R.id.tv_callmiduo:
			callPhone("4006666766");
			break;
		case R.id.tv_callbank:
			callPhone("95566");
			break;
		case R.id.right_txt:
			finish();
			for(int i=0;i<MyApplication.activityLists.size();i++)
			{
				MyApplication.activityLists.get(i).finish();
			}
			break;

		default:
			break;
		}
	}
	/**
	 * 拨打电话
	 * 
	 * @param string
	 *            电话号码
	 */
	private void callPhone(String phoneNumber) {
		Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
				+ phoneNumber));
		startActivity(intent);
	}


}
