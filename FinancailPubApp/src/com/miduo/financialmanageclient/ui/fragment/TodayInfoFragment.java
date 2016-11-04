package com.miduo.financialmanageclient.ui.fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.CalendarAssetForApp;
import com.miduo.financialmanageclient.bean.DataEntity;
import com.miduo.financialmanageclient.bean.RedeemItemDetail;
import com.miduo.financialmanageclient.common.CommonUtil;
import com.miduo.financialmanageclient.listener.InitHomeDataListener;
import com.miduo.financialmanageclient.ui.HomeActivity;
import com.miduo.financialmanageclient.ui.MiduoCalendarActivity;
import com.miduo.financialmanageclient.ui.ProdunctInfoActivity;
import com.miduo.financialmanageclient.ui.adapter.ProductAdaper;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.SharePrefUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

public class TodayInfoFragment extends Fragment {
	private HomeActivity parentActivity;
	private View view;
	private ViewPager mViewPager;
	private List<Fragment> fragmentList;
	private ListView listview;
	private int screenHeight;
	private int statusHeight;
	/**
	 * 三个Fragment（页面）
	 */
	private YesterdayProfitInfoFragment yesterdayProfitInfoFragment;
	private InsurancePlanInfoFragment insurancePlanInfoFragment;
	private AssetAllocateInfoFragment assetAllocationInfoFragment;

	private ProductAdaper productAdaper;
	private List<DataEntity> productLst = new ArrayList<DataEntity>();
	private View textview;
	private int currentIndex = 0;
	private float fraction2;
	private MyFrageStatePagerAdapter pageAdapter;
	private RelativeLayout calendar_layout;
	private ImageView calendar_msg_img;
	private TextView canlendar_txt;
	private RedeemItemDetail singleRedeemInfo;
	private MsgCountAsyncTask msgCountAsyncTask;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_today_info, null);
		parentActivity = (HomeActivity) getActivity();
		initView();
		return view;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("主页-今日"); // 统计页面
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("主页-今日");
	}

	private void initData() {
		// TODO Auto-generated method stub
		assetAllocationInfoFragment.initData();
		productLst.clear();
		if (MyApplication.productResult != null && MyApplication.productResult.getData() != null
				&& MyApplication.productResult.getData().size() > 0) {
			productLst.addAll(MyApplication.productResult.getData());

			listview.setOnScrollListener(new OnScrollListener() {

				@Override
				public void onScrollStateChanged(AbsListView view, int scrollState) {

				}

				@Override
				public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
					// TODO Auto-generated method stub
					changeLocate();
				}
			});

			// mViewPager.setOnTouchListener(new OnTouchListener() {
			//
			// @Override
			// public boolean onTouch(View v, MotionEvent event) {
			// // TODO Auto-generated method stub
			// if (delta == 0) {
			// return false;
			// } else {
			// return true;
			// }
			// }
			// });

			listview.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()) {
					case MotionEvent.ACTION_UP:
						if (0 < fraction2 && fraction2 <= 0.5) {
							listview.smoothScrollBy(
									(int) ((getResources().getDimension(R.dimen.px2dp_816) - getResources()
											.getDimension(R.dimen.px2dp_288)) * -fraction2), 500);

						} else if (0.5 < fraction2 && fraction2 < 1) {
							listview.smoothScrollBy(
									(int) ((getResources().getDimension(R.dimen.px2dp_816) - getResources()
											.getDimension(R.dimen.px2dp_288)) * (1 - fraction2)), 500);

						}
						break;

					default:
						break;
					}
					return false;
				}
			});
		} else {
			listview.setOnScrollListener(null);
			listview.setOnTouchListener(null);
		}
		addFootView(productLst.size());
		listview.setAdapter(productAdaper);
		productAdaper.notifyDataSetChanged();
		boolean isLogin = SharePrefUtil.getBoolean(parentActivity, SharePrefUtil.IS_LOGIN, false);
		if (isLogin) {
			CalendarAssetForApp calendar = MyApplication.calendarAssetForApp;
			if (calendar == null) {
				calendar_layout.setOnClickListener(null);
				calendar_msg_img.setVisibility(View.GONE);
				return;
			}
			if (calendar.getHasUnReadAssetThree() != null && calendar.getHasUnReadAssetThree().booleanValue()) {
				calendar_msg_img.setVisibility(View.VISIBLE);
			} else {
				calendar_msg_img.setVisibility(View.GONE);
			}
			if (calendar.getHasUnReadAsset() != null && calendar.getHasUnReadAsset().booleanValue()) {
				calendar_layout.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						MyApplication.home_refresh = false;
						Intent intent = new Intent(parentActivity, MiduoCalendarActivity.class);
						startActivity(intent);
					}
				});

			} else {
				calendar_layout.setOnClickListener(null);
			}
			initMsg();
		} else {
			calendar_msg_img.setVisibility(View.GONE);
			calendar_layout.setOnClickListener(null);
			ProgressDialogUtil.closeProgress();
		}
		
	}

	private void initMsg() {
		// TODO Auto-generated method stub
		if (msgCountAsyncTask != null) {
			msgCountAsyncTask.cancel(true);
			msgCountAsyncTask = null;
		}
		msgCountAsyncTask = new MsgCountAsyncTask();
		msgCountAsyncTask.execute();
	}

	private void addFootView(int size) {
		int divisor = (int) ((screenHeight - getResources().getDimension(R.dimen.px2dp_288)
				- getResources().getDimension(R.dimen.px2dp_120) - statusHeight) / getResources().getDimension(
				R.dimen.px2dp_300));
		float reminder = (screenHeight - getResources().getDimension(R.dimen.px2dp_288)
				- getResources().getDimension(R.dimen.px2dp_120) - statusHeight)
				% getResources().getDimension(R.dimen.px2dp_300);
		View footView = new View(parentActivity);
		int needHeight = 0;
		if (size > divisor) {
			// 什么都不需要做
		} else {
			needHeight = (int) (((divisor - size) * getResources().getDimension(R.dimen.px2dp_300)) + reminder);
			AbsListView.LayoutParams footParams = new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, needHeight);
			footView.setLayoutParams(footParams);
			footView.setOnClickListener(null);
			listview.addFooterView(footView);
		}

	}

	private void initView() {
		// TODO Auto-generated method stub
		WindowManager wm = parentActivity.getWindowManager();
		screenHeight = wm.getDefaultDisplay().getHeight();
		statusHeight = getStatusBarHeight();
		calendar_layout = (RelativeLayout) view.findViewById(R.id.calendar_layout);
		mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
		mViewPager.setOffscreenPageLimit(3);
		fragmentList = new ArrayList<Fragment>();
		yesterdayProfitInfoFragment = new YesterdayProfitInfoFragment();
		yesterdayProfitInfoFragment.setInitHomeDataListener(new InitHomeDataListener() {

			@Override
			public void setData() {
				// TODO Auto-generated method stub
				System.out.println("********初始化首页的产品数据*********");
				initData();
			}
		});
		assetAllocationInfoFragment = new AssetAllocateInfoFragment();
		insurancePlanInfoFragment = new InsurancePlanInfoFragment();
		fragmentList.add(yesterdayProfitInfoFragment);
		fragmentList.add(assetAllocationInfoFragment);
		fragmentList.add(insurancePlanInfoFragment);
		pageAdapter = new MyFrageStatePagerAdapter(getChildFragmentManager());
		mViewPager.setAdapter(pageAdapter);
		mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());// 页面变化时的监听器
		mViewPager.setCurrentItem(currentIndex);// 设置当前显示标签页为第一页

		listview = (ListView) view.findViewById(R.id.listview);
		textview = new View(parentActivity);
		AbsListView.LayoutParams lp = new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, (int) getResources()
				.getDimension(R.dimen.px2dp_816));
		textview.setLayoutParams(lp);
		textview.setBackgroundColor(Color.parseColor("#00000000"));
		listview.addHeaderView(textview);
		productAdaper = new ProductAdaper(parentActivity, productLst);
		// listview.setAdapter(productAdaper);

		canlendar_txt = (TextView) view.findViewById(R.id.canlendar_txt);
		Calendar ca = Calendar.getInstance();
		int day = ca.get(Calendar.DATE);// 获取日
		canlendar_txt.setText(day + "");
		calendar_msg_img = (ImageView) view.findViewById(R.id.calendar_msg_img);

		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (position >= 1) {
					if (!CommonUtil.checkLogin(parentActivity)) {
						return;
					}
					MyApplication.home_refresh = false;
					Intent intent = new Intent(parentActivity, ProdunctInfoActivity.class);
					intent.putExtra("product_info", productLst.get(position - 1));
					parentActivity.startActivity(intent);
				}
			}
		});
	}

	@SuppressLint("NewApi")
	private void changeLocate() {
		// TODO Auto-generated method stub
		int delta = textview.getBottom() - (int) getResources().getDimension(R.dimen.px2dp_816);
		if (textview.getBottom() <= (int) getResources().getDimension(R.dimen.px2dp_288)) {
			delta = (int) getResources().getDimension(R.dimen.px2dp_288)
					- (int) getResources().getDimension(R.dimen.px2dp_816);
		}
		if (textview.getBottom() >= (int) getResources().getDimension(R.dimen.px2dp_816)) {
			delta = 0;
		}
		// if (textview.getBottom() <= (int) getResources().getDimension(
		// R.dimen.px2dp_816)
		// && textview.getBottom() >= (int) getResources()
		// .getDimension(R.dimen.px2dp_288)) {
		// mViewPager.layout(0, delta, mViewPager.getMeasuredWidth(), (int)
		// getResources().getDimension(R.dimen.px2dp_816)
		// + delta);

		mViewPager.layout(0, delta, mViewPager.getMeasuredWidth(), (int) getResources().getDimension(R.dimen.px2dp_816)
				+ delta);

		float fraction = -delta * 1.0f / (int) getResources().getDimension(R.dimen.px2dp_816);
		fraction2 = -delta
				/ (getResources().getDimension(R.dimen.px2dp_816) - getResources().getDimension(R.dimen.px2dp_288));

		yesterdayProfitInfoFragment.changeLocation(fraction2);
		insurancePlanInfoFragment.changeLocate(fraction2);
		assetAllocationInfoFragment.changeLocate(delta, fraction, fraction2);

		calendar_layout.setAlpha(1 - fraction2);
	}

	public int getStatusBarHeight() {
		int result = 0;
		int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}

	public class MyFrageStatePagerAdapter extends FragmentPagerAdapter {

		public MyFrageStatePagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return fragmentList.get(arg0);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return fragmentList.size();
		}

	}

	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			currentIndex = arg0;
			if (productLst != null && productLst.size() > 0) {
				changeLocate();
			}
		}
	}

	private class MsgCountAsyncTask extends AsyncTask<Void, Void, String> {
		private AppException ex;

		public MsgCountAsyncTask() {
		}

		@Override
		protected String doInBackground(Void... arg0) {
			try {
				ex = null;
				return WebServiceClient.notReadCount();
			} catch (AppException e) {
				this.ex = e;
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			if (null == result) {
				return;
			} else {
				try {
					JSONObject jo = new JSONObject(result);
					int state = jo.getInt("state");
					if (state == ConstantValues.LOGIN_ERROR) {
						String msgStr = jo.getString("msg");
						MDialog.showPsdErrorDialog(parentActivity, msgStr);
						return;
					} else if (state == 1) {
						String data = jo.getString("data");
						if (!StringUtil.isEmpty(data)) {
							if (Integer.parseInt(data) > 0) {
								parentActivity.getRadioBtn2().setImgBg(R.drawable.f_4_2, R.drawable.f_4_1);
							} else {
								parentActivity.getRadioBtn2().setImgBg(R.drawable.f_3_2, R.drawable.f_3_1);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
	}

}
