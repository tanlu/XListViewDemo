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
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class GesturePasswordActivity extends Activity {

	/* 密码输入盘 */
	private GesturesPasswordView large_gesture;
	/* 保存密码的List */
	private List<Integer> list;
	/* 上部显示文字 */
	private TextView top;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gesture_password);

		/* 获取SharedPreferences保存的密码 */
		list = (List<Integer>) SharePrefUtil.getObj(this,SharePrefUtil.GESTURE);

		initView();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();		
		MobclickAgent.onPageStart("输入手势密码"); //统计页面
	    MobclickAgent.onResume(this);          //统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();		
		MobclickAgent.onPageEnd("输入手势密码"); //  
	    MobclickAgent.onPause(this);
	}

//	@Override
//	protected void onPause() {
//		super.onPause();
//		MobclickAgent.onPageEnd("输入手势密码");
//		MobclickAgent.onPause(this);
//	}
//
//	@Override
//	protected void onResume() {
//		super.onResume();
//		MobclickAgent.onPageStart("输入手势密码");
//		MobclickAgent.onResume(this);
//	}

	/* 输入错误的次数 */
	private int ERROR_SUM = 0;

	private void initView() {
		large_gesture = (GesturesPasswordView) findViewById(R.id.gesture);
		top = (TextView) findViewById(R.id.txttop);
		large_gesture.setListener(new GesturesPasswordListener() {

			@Override
			public void getGesturesPassword(List<Integer> lists) {

				if (list.equals(lists)) {// 手势密码输入正确

					// 获取登录状态
					boolean isLogin = SharePrefUtil.getBoolean(
							GesturePasswordActivity.this,
							SharePrefUtil.IS_LOGIN, false);
					Intent intent = new Intent();
					if (isLogin) {
						// 已登录跳转到主页面
						MyApplication.getInstance().home_index = 0;
						intent.setClass(GesturePasswordActivity.this,
								HomeActivity.class);
					} else {
						// 未登录跳转到登录页面
						intent.setClass(GesturePasswordActivity.this,
								LoginActivity.class);
					}
					startActivity(intent);
				} else {// 手势密码输入错误
					ERROR_SUM++;// 错误次数+1
					if (ERROR_SUM == 5) {
						/**
						 * 错误次数为5次时，设置为无手势密码，并跳转到登录页面
						 */
						SharePrefUtil.saveBoolean(GesturePasswordActivity.this,
								SharePrefUtil.IS_SAVEGESTURE, false);
						Intent intent = new Intent();
						intent.setClass(GesturePasswordActivity.this,
								LoginActivity.class);
						startActivity(intent);
						return;
					}

					large_gesture.setList(lists, false);
					/**
					 * 输入错误的提示
					 */
					top.setTextColor(Color.RED);
					top.setText("密码输入错误，还可以输入" + (5 - ERROR_SUM) + "次");
					TranslateAnimation animation = new TranslateAnimation(0, -20, 0, 0);
					animation.setInterpolator(new OvershootInterpolator());
					animation.setDuration(50);
					animation.setRepeatCount(5);
					animation.setRepeatMode(Animation.REVERSE);
					top.startAnimation(animation);
					
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

			@Override
			public void changeGesturesPassword(List<Integer> list) {

			}
		});
	}

}
