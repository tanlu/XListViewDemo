package com.miduo.financialmanageclient.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.UserInfo;
import com.miduo.financialmanageclient.listener.GesturesPasswordListener;
import com.miduo.financialmanageclient.util.ImageDownLoadUtil;
import com.miduo.financialmanageclient.util.SharePrefUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.widget.CircleImageView;
import com.miduo.financialmanageclient.widget.GesturesPasswordViewNew;
import com.umeng.analytics.MobclickAgent;

public class SetGestureNewActivity extends Activity {
	private CircleImageView headerImg;
	private GesturesPasswordViewNew large_gesture;
	private int cur = 0;
	private List<Integer> initList;
	private TextView info_txt, repeate_txt;
	private int fromIndex;
	private TextView telText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_gesture_new);
		MyApplication.home_refresh = true;
		fromIndex = getIntent().getIntExtra("order", 0);
		initView();
		initEvent();
		initHeader();
	}

	private void initHeader() {
		// TODO Auto-generated method stub
		String tel = SharePrefUtil.getString(SetGestureNewActivity.this, SharePrefUtil.ACCOUNT_MOBILE, "");
		if (!StringUtil.isEmpty(tel)) {
			telText.setText(tel.substring(0, 3) + "****" + tel.substring(7, 11));
		}
		String filePath = SharePrefUtil.getString(SetGestureNewActivity.this, SharePrefUtil.ACCOUNT_HEADER, null);
		if (!StringUtil.isEmpty(filePath)) {
			headerImg.setTag(filePath);
			ImageDownLoadUtil.setImageBitmap(headerImg, filePath);
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("设置手势密码"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
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
		telText = (TextView) findViewById(R.id.tel_txt);
		headerImg = (CircleImageView) findViewById(R.id.head_circleimg);
		large_gesture = (GesturesPasswordViewNew) findViewById(R.id.large_gesture);
		large_gesture.setListener(listener);
		info_txt = (TextView) findViewById(R.id.info_txt);
		repeate_txt = (TextView) findViewById(R.id.repeate_txt);
		repeate_txt.setVisibility(View.GONE);
		repeate_txt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				info_txt.setText(R.string.gesture_str1);
				repeate_txt.setVisibility(View.GONE);
				cur = 0;
			}
		});
	}

	private GesturesPasswordListener listener = new GesturesPasswordListener() {

		@Override
		public void getGesturesPassword(List<Integer> list) {
			if (cur == 0) {
				List<Integer> tempList = new ArrayList<Integer>();
				if (list.size() < 4) {
					large_gesture.setList(tempList);
					// small_gesture.setList(initList);
					info_txt.setText("至少绘制四个点");
				} else {
					info_txt.setText(R.string.gesture_str2);
					large_gesture.setList(tempList);
					initList = list;
					repeate_txt.setVisibility(View.VISIBLE);
					cur = 1;
				}
			} else {
				if (initList.equals(list)) {
					SharePrefUtil.saveBoolean(SetGestureNewActivity.this, SharePrefUtil.IS_SAVEGESTURE, true);
					SharePrefUtil.saveObj(SetGestureNewActivity.this, SharePrefUtil.GESTURE, initList);
					if (fromIndex == 1) {
						setResult(RESULT_OK);
					} else {
						Intent intent = new Intent();
						UserInfo userInfo = (UserInfo) SharePrefUtil.getObj(SetGestureNewActivity.this,
								SharePrefUtil.USER_INFO);

						if (userInfo.getTestBindIfa() != null && userInfo.getTestBindIfa().intValue() == 1) {
							MyApplication.getInstance().home_index = 0;
							MyApplication.home_refresh = true;
							intent.setClass(SetGestureNewActivity.this, HomeActivity.class);
						} else {
							intent.setClass(SetGestureNewActivity.this, ReplaceFinancailPlannerActivity.class);
							intent.putExtra("is_turn", true);
						}
						startActivity(intent);
						finish();
					}
					finish();
				} else {
					large_gesture.setList(list, false);
					info_txt.setText("与上次输入不一致，请重新输入");
					TranslateAnimation animation = new TranslateAnimation(0, -20, 0, 0);
					animation.setInterpolator(new OvershootInterpolator());
					animation.setDuration(50);
					animation.setRepeatCount(5);
					animation.setRepeatMode(Animation.REVERSE);
					info_txt.startAnimation(animation);

					new Thread() {
						public void run() {
							try {
								Thread.sleep(400);
								List<Integer> tempList = new ArrayList<Integer>();
								large_gesture.setList(tempList, true);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						};
					}.start();
				}
			}
		}

		@Override
		public void changeGesturesPassword(List<Integer> list) {
			// TODO Auto-generated method stub

		}
	};

	private void initEvent() {
	}
}
