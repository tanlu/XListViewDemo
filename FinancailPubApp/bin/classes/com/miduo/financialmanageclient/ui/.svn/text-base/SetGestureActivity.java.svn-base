package com.miduo.financialmanageclient.ui;

import java.util.ArrayList;
import java.util.List;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.listener.GesturesPasswordListener;
import com.miduo.financialmanageclient.util.SharePrefUtil;
import com.miduo.financialmanageclient.widget.GesturesPasswordView;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class SetGestureActivity extends Activity {

	private GesturesPasswordView small_gesture, large_gesture;
	private int cur = 0;
	private int resoure = 0;
	private List<Integer> initList;
	private TextView sjh, jump;
	private int fromIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_gesture);
		fromIndex = getIntent().getIntExtra("order", 0);
		initView();
		initEvent();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();		
		MobclickAgent.onPageStart("设置手势密码"); //统计页面
	    MobclickAgent.onResume(this);          //统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();		
		MobclickAgent.onPageEnd("设置手势密码"); //  
	    MobclickAgent.onPause(this);
	}

	// @Override
	// protected void onPause() {
	// super.onPause();
	// MobclickAgent.onPageEnd("设置手势密码");
	// MobclickAgent.onPause(this);
	// }
	//
	// @Override
	// protected void onResume() {
	// super.onResume();
	// MobclickAgent.onPageStart("设置手势密码");
	// MobclickAgent.onResume(this);
	// }

	private void initView() {
		small_gesture = (GesturesPasswordView) findViewById(R.id.small_gesture);
		small_gesture.setIsDrawLine(false);
		large_gesture = (GesturesPasswordView) findViewById(R.id.large_gesture);
		large_gesture.setListener(listener);
		sjh = (TextView) findViewById(R.id.sjh);

		jump = (TextView) findViewById(R.id.forget);
		if (fromIndex == 1) {
			jump.setVisibility(View.GONE);
		}
		jump.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (cur == 0) {
					if (fromIndex == 0) {
						jump.setVisibility(View.GONE);
					}
					Intent intent = new Intent();
					MyApplication.getInstance().home_index = 0;
					intent.setClass(SetGestureActivity.this, HomeActivity.class);
					startActivity(intent);
					finish();
				} else {
					sjh.setTextColor(Color.parseColor("#d6d6d6"));
					sjh.setText("绘制解锁图案");
					initList = new ArrayList<Integer>();
					small_gesture.setList(initList);
					if (fromIndex == 1)
						jump.setVisibility(View.GONE);
					jump.setText("跳过");
					cur = 0;
				}
			}
		});
	}

	// private int ERROR_SUM = 0;
	private GesturesPasswordListener listener = new GesturesPasswordListener() {

		@Override
		public void getGesturesPassword(List<Integer> list) {
			if (resoure == 0) {
				if (cur == 0) {
					List<Integer> tempList = new ArrayList<Integer>();
					if (list.size() < 4) {
						large_gesture.setList(tempList);
						// small_gesture.setList(initList);
						sjh.setTextColor(Color.RED);
						sjh.setText("至少绘制四个点");
					} else {
						sjh.setTextColor(Color.parseColor("#d6d6d6"));
						sjh.setText("再次绘制解锁图案");
						large_gesture.setList(tempList);
						initList = list;
						small_gesture.setList(initList);
						jump.setVisibility(View.VISIBLE);
						jump.setText("重新输入");
						cur = 1;
					}
				} else {
					if (initList.equals(list)) {
						SharePrefUtil.saveBoolean(SetGestureActivity.this, SharePrefUtil.IS_SAVEGESTURE, true);
						SharePrefUtil.saveObj(SetGestureActivity.this, SharePrefUtil.GESTURE, initList);
						if (fromIndex == 1) {
							setResult(RESULT_OK);
						} else {
							Intent intent = new Intent();
							MyApplication.getInstance().home_index = 0;
							intent.setClass(SetGestureActivity.this, HomeActivity.class);
							startActivity(intent);
						}						
						finish();
					} else {
						large_gesture.setList(list, false);
						sjh.setTextColor(Color.RED);
						sjh.setText("与上次输入不一致，请重新输入");
						TranslateAnimation animation = new TranslateAnimation(0, -20, 0, 0);
						animation.setInterpolator(new OvershootInterpolator());
						animation.setDuration(50);
						animation.setRepeatCount(5);
						animation.setRepeatMode(Animation.REVERSE);
						sjh.startAnimation(animation);

						new Thread() {
							public void run() {
								try {
									Thread.sleep(400);
									List<Integer> tempList = new ArrayList<Integer>();
									large_gesture.setList(tempList);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							};
						}.start();
					}
				}
			}
		}

		@Override
		public void changeGesturesPassword(List<Integer> list) {
			if (cur == 0) {
				small_gesture.setList(list);
			}
		}
	};

	private void initEvent() {
	}

}