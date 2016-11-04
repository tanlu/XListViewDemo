package com.miduo.financialmanageclient.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.DialogBean;
import com.miduo.financialmanageclient.listener.DialogEventListener;
import com.miduo.financialmanageclient.util.MDialog;

public class RedeemFailActivity extends GesterSetBaseActivity {
	private TextView tv_iknow, tv_callmiduo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_redeem_fail);
		tv_iknow = (TextView) findViewById(R.id.tv_iknow);
		tv_callmiduo = (TextView) findViewById(R.id.tv_callmiduo);
		tv_iknow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MyApplication.getInstance().home_index = 2;
				Intent intent = new Intent(RedeemFailActivity.this,HomeActivity.class);			
				startActivity(intent);
			}
		});
		tv_callmiduo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 拨打电话
				DialogBean data = new DialogBean();
				data.setContent("是否拨打客服电话？");
				data.setSubmit("立即拨打");
				data.setCancel("取消");

				DialogEventListener lister2 = new DialogEventListener() {

					@Override
					public void submit() {
						// TODO Auto-generated method stub
						Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:4006666766"));
						startActivity(intent);
					}

					@Override
					public void cancel() {
						// TODO Auto-generated method stub

					}
				};
				data.setDialogEvent(lister2);
				MDialog.showDialog2(RedeemFailActivity.this, data);
			}
		});
	}
}
