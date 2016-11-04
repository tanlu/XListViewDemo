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
 * 支付成功但是已经没有名额了
 * @author huozhenpeng
 *
 */
public class PaymentResultSuccessNoPlaceActivity extends GesterSetBaseActivity implements OnClickListener{

	private ImageView iv_left;
	private TextView text_title;
	private TextView tv_right;
	private TextView tv_iknow;
	private TextView tv_callmiduo;//咨询米多客服
	private TextView tv_tips;//提示信息，分购买和转让
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_paymentsuccessnoplace);
		initView();
		initEvent();
		initData();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();		
		MobclickAgent.onPageStart("支付成功但无名额"); //统计页面
	    MobclickAgent.onResume(this);          //统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();		
		MobclickAgent.onPageEnd("支付成功但无名额"); //  
	    MobclickAgent.onPause(this);
	}

	private void initData() {
		text_title.setText("支付结果");
		if(MyApplication.isBuy)//购买
		{
			tv_tips.setText(getResources().getString(R.string.success_noplace));
		}
		else
		{
			tv_tips.setText(getResources().getString(R.string.transfer_noplace));
		}
	}

	private void initEvent() {
		tv_callmiduo.setOnClickListener(this);
		tv_iknow.setOnClickListener(this);
		
	}

	private void initView() {
		iv_left=(ImageView) this.findViewById(R.id.left_img);
		iv_left.setVisibility(View.INVISIBLE);
		text_title=(TextView) this.findViewById(R.id.title_txt);
		tv_right=(TextView) this.findViewById(R.id.right_txt);	
		tv_right.setVisibility(View.INVISIBLE);
		tv_callmiduo=(TextView) this.findViewById(R.id.tv_callmiduo);
		tv_iknow=(TextView) this.findViewById(R.id.tv_iknow);
		tv_tips=(TextView) this.findViewById(R.id.tv_tips);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_iknow:
			Intent intent=new Intent();
			if(MyApplication.isBuy)
			{
				MyApplication.home_index = 2;
				intent.setClass(this, HomeActivity.class);			
			}
			else
			{
				intent.setClass(this, AssetsListActivity.class);
			}
			MyApplication.activityLists.add(this);
			startActivity(intent);
			break;
		case R.id.tv_callmiduo:
			callPhone("4006666766");
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
