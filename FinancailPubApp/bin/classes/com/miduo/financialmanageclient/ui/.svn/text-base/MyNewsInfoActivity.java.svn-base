package com.miduo.financialmanageclient.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.MsgBean;
import com.miduo.financialmanageclient.util.StringUtil;
import com.umeng.analytics.MobclickAgent;

public class MyNewsInfoActivity extends GesterSetBaseActivity implements OnClickListener {

	private TextView title_txt,right_txt;
	private ImageView left_img;
	private MsgBean msg;
	private TextView type_txt,name,shijian,content_txt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_mynewsinfo);
		initView();
		initEvent();
		initData();
	}

	private void initData() {
		msg = (MsgBean) getIntent().getSerializableExtra("msg");
		if(msg==null){
			return;
		}
		if(StringUtil.isEmpty(msg.getIsRead()) || msg.getIsRead().equals("1")){
			type_txt.setBackgroundResource(R.drawable.msg_bg_gray);
		}else{
			type_txt.setBackgroundResource(R.drawable.blue_round_bg);
		}	
		type_txt.setText(StringUtil.showStringContent(msg.getTopType()));
		name.setText(StringUtil.showStringContent(msg.getTitle()));
		shijian.setText(StringUtil.showStringContent(msg.getDateTime()));
		content_txt.setText(StringUtil.showStringContent(msg.getContent()));
	}

	private void initView() {
		title_txt = (TextView) this.findViewById(R.id.title_txt);
		title_txt.setText("消息详情");
		left_img = (ImageView) this.findViewById(R.id.left_img);
		right_txt = (TextView) this.findViewById(R.id.right_txt);
		right_txt.setVisibility(View.GONE);
		type_txt = (TextView) this.findViewById(R.id.type_txt);
		name = (TextView) this.findViewById(R.id.name);
		shijian = (TextView) this.findViewById(R.id.shijian);
		content_txt = (TextView) this.findViewById(R.id.content_txt);
	}

	private void initEvent() {
		left_img.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("我的消息详情");
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPageStart("我的消息详情");
		MobclickAgent.onPause(this);
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
