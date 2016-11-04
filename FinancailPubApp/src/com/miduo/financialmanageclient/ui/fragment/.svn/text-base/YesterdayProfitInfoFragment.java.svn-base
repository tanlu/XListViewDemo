package com.miduo.financialmanageclient.ui.fragment;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.CalendarAssetForApp;
import com.miduo.financialmanageclient.bean.ProfitBean;
import com.miduo.financialmanageclient.common.CommonUtil;
import com.miduo.financialmanageclient.listener.InitHomeDataListener;
import com.miduo.financialmanageclient.ui.HomeActivity;
import com.miduo.financialmanageclient.ui.LoginActivity;
import com.miduo.financialmanageclient.util.FloatUtil;
import com.miduo.financialmanageclient.util.JsonUtils;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.SharePrefUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;

public class YesterdayProfitInfoFragment extends Fragment {
	private LinearLayout tv_01;
	private LinearLayout tv_02;
	private LinearLayout tv_03;
	private View view;
	private LinearLayout point, point2;
	private HomeActivity parentActivity;
	private ImageView iv_bg;
	private RelativeLayout rl_top;
	private TextView tv;
	private TextView yesterday_profit_txt;
	private TextView sum_profit_txt;
	private Map<String, String> map;
	private boolean isLogin;
	private RelativeLayout login_layout, not_login_layout;
	private TextView not_login_txt1, not_login_txt2;
	private ImageView people_head_img, waveView;
	private InitHomeDataListener initHomeDataListener;

	public InitHomeDataListener getInitHomeDataListener() {
		return initHomeDataListener;
	}

	public void setInitHomeDataListener(InitHomeDataListener initHomeDataListener) {
		this.initHomeDataListener = initHomeDataListener;
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			String result = "";
			switch (msg.what) {
			case 100:
				if (MyApplication.productResult != null
						&& MyApplication.productResult.getState() == ConstantValues.LOGIN_ERROR) {
					ProgressDialogUtil.closeProgress();
					MDialog.showPsdErrorDialog(parentActivity, MyApplication.productResult.getMsg());
					return;
				}
				if (isLogin) {
					initProfit();
				} else {
					initInvest();
				}
				break;
			case 200:
				result = (String) msg.obj;
				if (StringUtil.isEmpty(result)) {
					MyApplication.profitBean = null;
				} else {
					try {
						JSONObject jo = new JSONObject(result);
						int state = jo.getInt("state");
						String msgStr = jo.getString("msg");
						if (state == ConstantValues.LOGIN_ERROR) {
							ProgressDialogUtil.closeProgress();
							MDialog.showPsdErrorDialog(parentActivity, msgStr);
							return;
						} else if (state == 1) {
							String data = jo.getString("data");
							if (!StringUtil.isEmpty(data)) {
								MyApplication.profitBean = JsonUtils.toBean(data, new TypeToken<ProfitBean>() {
								}.getType());
								yesterday_profit_txt.setText(FloatUtil
										.toTwoDianStringSeparator(MyApplication.profitBean.getYesterdayRevenue()));
								sum_profit_txt.setText(FloatUtil.toTwoDianStringSeparator(MyApplication.profitBean
										.getTotalRevenue()));
							} else {
								MyApplication.profitBean = null;
							}
						} else {
							MyApplication.profitBean = null;
						}
					} catch (Exception e) {
						MyApplication.profitBean = null;
						e.printStackTrace();
					}
				}
				initInvest();
				break;
			case 300:
				if (MyApplication.investmentResult != null
						&& MyApplication.investmentResult.getState() == ConstantValues.LOGIN_ERROR) {
					ProgressDialogUtil.closeProgress();
					MDialog.showPsdErrorDialog(parentActivity, MyApplication.investmentResult.getMsg());
					return;
				}
				initInsure();
				break;
			case 400:
				if (MyApplication.insureResult != null
						&& MyApplication.insureResult.getState() == ConstantValues.LOGIN_ERROR) {
					MDialog.showPsdErrorDialog(parentActivity, MyApplication.insureResult.getMsg());
					return;
				}
				boolean isLogin = SharePrefUtil.getBoolean(parentActivity, SharePrefUtil.IS_LOGIN, false);
				if(isLogin){
					initCalendar();
				}else{
					if (initHomeDataListener != null) {
						initHomeDataListener.setData();
					}
				}		
				break;
			case 500:
				ProgressDialogUtil.closeProgress();
				result = (String) msg.obj;
				if (StringUtil.isEmpty(result)) {
					MyApplication.calendarAssetForApp = null;
				} else {
					try {
						JSONObject jo = new JSONObject(result);
						int state = jo.getInt("state");
						String msgStr = jo.getString("msg");
						if (state == ConstantValues.LOGIN_ERROR) {
							ProgressDialogUtil.closeProgress();
							MDialog.showPsdErrorDialog(parentActivity, msgStr);
							return;
						} else if (state == 1) {
							String data = jo.getString("data");
							if (!StringUtil.isEmpty(data)) {
								MyApplication.calendarAssetForApp = JsonUtils.toBean(data,
										new TypeToken<CalendarAssetForApp>() {
										}.getType());
							} else {
								MyApplication.calendarAssetForApp = null;
							}
						} else {
							MyApplication.calendarAssetForApp = null;
						}
					} catch (Exception e) {
						MyApplication.calendarAssetForApp = null;
						e.printStackTrace();
					}
				}
				if (initHomeDataListener != null) {
					initHomeDataListener.setData();
				}
				break;
			default:
				break;
			}
		}
	};

	private void initCalendar() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			@Override
			public void run() {
				String result = "";
				try {
					result = WebServiceClient.checkCalendarData();
				} catch (AppException e) {
					e.printStackTrace();
				} finally {
					Message msg = new Message();
					msg.what = 500;
					msg.obj = result;
					handler.sendMessage(msg);
				}
			}
		}).start();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_yesterday_profit_info, null);
		parentActivity = (HomeActivity) getActivity();
		initView();
		initEvent();
		isLogin = SharePrefUtil.getBoolean(parentActivity, SharePrefUtil.IS_LOGIN, false);
		if (isLogin) {
			login_layout.setVisibility(View.VISIBLE);
			not_login_layout.setVisibility(View.GONE);
		} else {
			login_layout.setVisibility(View.GONE);
			not_login_layout.setVisibility(View.VISIBLE);
		}
		// 首页 昨日收益数据
		MyApplication.profitBean = null;
		// 首页热销产品
		MyApplication.productResult = null;
		// 首页投资信息
		MyApplication.investmentResult = null;
		// 首页投保信息
		MyApplication.insureResult = null;
		initProduct();
		return view;
	}

	private void initProduct() {
		// TODO Auto-generated method stub
		ProgressDialogUtil.showProgress(parentActivity);
		map = new HashMap<String, String>();
		map.put("page", "1");
		map.put("size", "100");
		map.put("categoryId", "");
		map.put("ishot", 1 + "");
		map.put("productType", 0 + "");
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					MyApplication.productResult = WebServiceClient.getProductResult(map);
				} catch (AppException e) {
					MyApplication.productResult = null;
					e.printStackTrace();
				} finally {
					handler.sendEmptyMessage(100);
				}
			}
		}).start();
	}

	private void initProfit() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				String result = "";
				try {
					result = WebServiceClient.getUserRevenue();
				} catch (AppException e) {
					e.printStackTrace();
				} finally {
					Message msg = new Message();
					msg.what = 200;
					msg.obj = result;
					handler.sendMessage(msg);
				}
			}
		}).start();
	}

	private void initInvest() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					MyApplication.investmentResult = WebServiceClient.getInvestHomeData();
				} catch (AppException e) {
					MyApplication.investmentResult = null;
					e.printStackTrace();
				} finally {
					handler.sendEmptyMessage(300);
				}
			}
		}).start();
	}

	private void initInsure() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					MyApplication.insureResult = WebServiceClient.getInsuranceHomeData();
				} catch (AppException e) {
					MyApplication.insureResult = null;
					e.printStackTrace();
				} finally {
					handler.sendEmptyMessage(400);
				}
			}
		}).start();
	}

	private void initView() {
		not_login_txt1 = (TextView) view.findViewById(R.id.not_login_txt1);
		not_login_txt2 = (TextView) view.findViewById(R.id.not_login_txt2);
		people_head_img = (ImageView) view.findViewById(R.id.people_head_img);
		waveView = (ImageView) view.findViewById(R.id.waveView);
		login_layout = (RelativeLayout) view.findViewById(R.id.login_layout);
		not_login_layout = (RelativeLayout) view.findViewById(R.id.not_login_layout);
		rl_top = (RelativeLayout) view.findViewById(R.id.rl_top);
		// 三个子view
		tv_01 = (LinearLayout) view.findViewById(R.id.tv_01);
		tv_02 = (LinearLayout) view.findViewById(R.id.tv_02);
		tv_03 = (LinearLayout) view.findViewById(R.id.tv_03);
		tv = (TextView) view.findViewById(R.id.tv);

		point = (LinearLayout) view.findViewById(R.id.point);
		point2 = (LinearLayout) view.findViewById(R.id.point2);
		iv_bg = (ImageView) view.findViewById(R.id.iv_bg);

		yesterday_profit_txt = (TextView) view.findViewById(R.id.yesterday_profit_txt);
		sum_profit_txt = (TextView) view.findViewById(R.id.sum_profit_txt);

	}

	/**
	 * 控制动画效果
	 * 
	 * @param fraction值从0到1
	 *            (向上滑动从0变为1，向下滑动从1变为0)
	 */
	@SuppressLint("NewApi")
	public void changeLocation(float fraction) {
		if (isLogin) {
			if (rl_top == null) {
				return;
			}
			// tv_01.setTranslationY((getResources().getDimension(R.dimen.px2dp_406)
			// - getResources().getDimension(
			// R.dimen.px2dp_100))
			// * fraction);
			// yesterday_profit_txt.setScaleX(1
			// - (getResources().getDimension(R.dimen.px2dp_110) -
			// getResources().getDimension(R.dimen.px2sp_40))
			// * fraction / getResources().getDimension(R.dimen.px2dp_110));
			// yesterday_profit_txt.setScaleY(1
			// - (getResources().getDimension(R.dimen.px2dp_110) -
			// getResources().getDimension(R.dimen.px2sp_40))
			// * fraction / getResources().getDimension(R.dimen.px2dp_110));
			// tv_01.setScaleX(1
			// - (getResources().getDimension(R.dimen.px2dp_110) -
			// getResources().getDimension(R.dimen.px2sp_40))
			// * fraction / getResources().getDimension(R.dimen.px2dp_110));
			// tv_01.setScaleY(1
			// - (getResources().getDimension(R.dimen.px2dp_110) -
			// getResources().getDimension(R.dimen.px2sp_40))
			// * fraction / getResources().getDimension(R.dimen.px2dp_110));
			tv.setTranslationY((getResources().getDimension(R.dimen.px2dp_572) - getResources().getDimension(
					R.dimen.px2dp_120))
					* fraction);
			// tv.setTranslationX(-(getResources().getDimension(R.dimen.px2dp_288)
			// - getResources().getDimension(
			// R.dimen.px2dp_200))
			// * fraction);
			tv.setTranslationX(-(tv.getWidth() / 2 + (getResources().getDimension(R.dimen.px2sp_40) - getResources()
					.getDimension(R.dimen.px2sp_26)) * 2) * fraction);
			tv.setScaleX((getResources().getDimension(R.dimen.px2sp_40) - getResources().getDimension(R.dimen.px2sp_26))
					* fraction / getResources().getDimension(R.dimen.px2sp_26) + 1);
			tv.setScaleY((getResources().getDimension(R.dimen.px2sp_40) - getResources().getDimension(R.dimen.px2sp_26))
					* fraction / getResources().getDimension(R.dimen.px2sp_26) + 1);
			tv_01.setTranslationY((getResources().getDimension(R.dimen.px2dp_406) - getResources().getDimension(
					R.dimen.px2dp_90))
					* fraction);
			tv_01.setTranslationX((tv_01.getWidth() / 2 + getResources().getDimension(R.dimen.px2dp_30)) * fraction);
			// tv.setTranslationY(getResources().getDimension(R.dimen.px2dp_50)*
			// fraction);
			float temp = 1
					- (getResources().getDimension(R.dimen.px2dp_110) - getResources().getDimension(R.dimen.px2sp_40))
					* fraction / getResources().getDimension(R.dimen.px2dp_110);
			yesterday_profit_txt.setScaleX(temp);
			yesterday_profit_txt.setScaleY(temp);
			yesterday_profit_txt.setTranslationX(-yesterday_profit_txt.getWidth()
					* (getResources().getDimension(R.dimen.px2dp_110) - getResources().getDimension(R.dimen.px2dp_70))
					* fraction / getResources().getDimension(R.dimen.px2dp_110));
			yesterday_profit_txt.setTranslationY(getResources().getDimension(R.dimen.px2dp_24) * fraction);
			// 标记
			if (fraction > 0.5) {
				tv_03.setVisibility(View.INVISIBLE);
				point.setVisibility(View.INVISIBLE);
			} else {
				tv_03.setVisibility(View.VISIBLE);
				point.setVisibility(View.VISIBLE);
				point.setAlpha((float) (1 - fraction / 0.5));
				tv_03.setAlpha((float) (1 - fraction / 0.5));
			}
			iv_bg.setTranslationY(getResources().getDimension(R.dimen.px2dp_30) * fraction);

		} else {
			if (fraction > 0.5) {
				waveView.setVisibility(View.VISIBLE);
			} else {
				waveView.setVisibility(View.INVISIBLE);
			}
			float alpha = (float) ((fraction - 0.5) / (1 - 0.5));
			waveView.setAlpha(alpha);
			float tempValue = getResources().getDimension(R.dimen.px2dp_128)
					/ getResources().getDimension(R.dimen.px2dp_236);
			tempValue = 1 - (1 - tempValue) * fraction;
			people_head_img.setScaleX(tempValue);
			people_head_img.setScaleY(tempValue);
			tempValue = (getResources().getDimension(R.dimen.px2dp_434) - getResources().getDimension(R.dimen.px2dp_40))
					* fraction;
			people_head_img.setTranslationY(tempValue);
			// not_login_txt1.setTranslationY(tempValue);
			// not_login_txt2.setTranslationY(tempValue);
			tempValue = (float) (fraction / 0.5);
			not_login_txt1.setAlpha(1 - tempValue);
			not_login_txt2.setAlpha(1 - tempValue);

			// 标记
			point2.setAlpha(1 - fraction * 3);
			if (fraction >= 0.5) {
				point2.setVisibility(View.INVISIBLE);
			} else {
				point2.setVisibility(View.VISIBLE);
			}
		}

	}

	private void initEvent() {
		rl_top.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (isLogin) {
					parentActivity.getRadioBtn2().performClick();
				}
			}
		});
		not_login_txt2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(parentActivity, LoginActivity.class));
			}
		});
	}
}
