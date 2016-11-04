package com.miduo.financialmanageclient.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;

import com.miduo.financialmanageclient.R;
import com.umeng.analytics.MobclickAgent;

public class WelcomeActivity extends Activity {
	// 首次使用程序的显示的欢迎图片
	private int[] ids = { R.drawable.webcom_1, R.drawable.webcom_2, R.drawable.webcom_3, R.drawable.webcom_4,
			R.drawable.webcom_4 };
	private ArrayList<View> guides = new ArrayList<View>();
	private ViewPager pager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		initView();
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("欢迎页");
		MobclickAgent.onPause(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("欢迎页");
		MobclickAgent.onResume(this);
	}

	private void initView() {
		LayoutInflater inflater = getLayoutInflater();
		guides = new ArrayList<View>();
		guides.add(inflater.inflate(R.layout.item01, null));
		guides.add(inflater.inflate(R.layout.item02, null));
		guides.add(inflater.inflate(R.layout.item03, null));
		guides.add(inflater.inflate(R.layout.item04, null));
		guides.add(inflater.inflate(R.layout.item04, null));

		WecommPagerAdapter adapter = new WecommPagerAdapter(guides);
		pager = (ViewPager) findViewById(R.id.showwelom_page);
		pager.setAdapter(adapter);
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageSelected(int arg0) {
				if (arg0 == ids.length - 1) {// 到最后一张了
					Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
					startActivity(intent);
					WelcomeActivity.this.finish();
				}
			}

			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			public void onPageScrollStateChanged(int arg0) {
			}

		});

	}

	public class WecommPagerAdapter extends PagerAdapter {

		private List<View> views;

		public WecommPagerAdapter(List<View> views) {
			this.views = views;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(views.get(arg1));
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public int getCount() {
			return views.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(views.get(arg1), 0);
			return views.get(arg1);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {

		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {

		}

	}
}
