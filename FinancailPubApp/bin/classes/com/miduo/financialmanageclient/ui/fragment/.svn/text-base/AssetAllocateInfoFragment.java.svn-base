package com.miduo.financialmanageclient.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.NInvestementBean;
import com.miduo.financialmanageclient.bean.NInvestmentResult;
import com.miduo.financialmanageclient.ui.HomeActivity;
import com.miduo.financialmanageclient.ui.InvestPlannActivity;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.widget.RingView;

public class AssetAllocateInfoFragment extends Fragment {
	private HomeActivity parentActivity;
	private View view;
	private RingView ringView;
	private LinearLayout name_layout;
	private ImageView waveView;
	private LinearLayout bottom_layout;
	private RelativeLayout ring_center_layout;
	private TextView center_txt;
	private RelativeLayout ring_top_layout;
	private TextView name_txt1, name_txt2, name_txt3, name_txt4;
	private RelativeLayout asset_layout, no_data_layout, realy_data_layout;
	private ImageView iv_icon;
	private TextView tv_text1, tv_text2, tv_test;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_asset_allocate_info, null);
		parentActivity = (HomeActivity) getActivity();
		initView();
		initEvent();
		// initData(); // 加载数据的方法
		return view;
	}

	@SuppressLint("NewApi")
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		// TODO Auto-generated method stub
		// 判断Fragment中的ListView时候存在，判断该Fragment时候已经正在前台显示
		// 通过这两个判断，就可以知道什么时候去加载数据了
		NInvestmentResult result = MyApplication.investmentResult;
		if (no_data_layout == null || realy_data_layout == null) {
			return;
		}
		if (isVisibleToUser && isVisible()) {
			if (result != null && result.getState() != null && result.getState().intValue() == 1) {
				NInvestementBean invest = result.getData();
				if (invest.getDataType() == 1) {
					no_data_layout.setVisibility(View.INVISIBLE);
					realy_data_layout.setVisibility(View.VISIBLE);
					initRing(); // 加载数据的方法
				} else {
					no_data_layout.setVisibility(View.VISIBLE);
					realy_data_layout.setVisibility(View.INVISIBLE);
				}
			} else {
				no_data_layout.setVisibility(View.VISIBLE);
				realy_data_layout.setVisibility(View.INVISIBLE);
			}
		} else {
			if (result != null && result.getState() != null && result.getState().intValue() == 1) {
				NInvestementBean invest = result.getData();
				if (invest.getDataType() == 1) {
					if (ringView != null) {
						ringView.setValueLst(null, null);
					}
				} else {
					no_data_layout.setVisibility(View.VISIBLE);
					realy_data_layout.setVisibility(View.INVISIBLE);
				}
			} else {
				no_data_layout.setVisibility(View.VISIBLE);
				realy_data_layout.setVisibility(View.INVISIBLE);
			}
		}
		super.setUserVisibleHint(isVisibleToUser);
	}

	private void initView() {
		// TODO Auto-generated method stub
		asset_layout = (RelativeLayout) view.findViewById(R.id.asset_layout);
		ring_center_layout = (RelativeLayout) view.findViewById(R.id.ring_center_layout);
		center_txt = (TextView) view.findViewById(R.id.center_txt);
		ring_top_layout = (RelativeLayout) view.findViewById(R.id.ring_top_layout);

		bottom_layout = (LinearLayout) view.findViewById(R.id.bottom_layout);
		waveView = (ImageView) view.findViewById(R.id.waveView);
		name_layout = (LinearLayout) view.findViewById(R.id.name_layout);

		ringView = (RingView) view.findViewById(R.id.ring_view);
		name_txt1 = (TextView) view.findViewById(R.id.name_txt1);
		name_txt2 = (TextView) view.findViewById(R.id.name_txt2);
		name_txt3 = (TextView) view.findViewById(R.id.name_txt3);
		name_txt4 = (TextView) view.findViewById(R.id.name_txt4);

		no_data_layout = (RelativeLayout) view.findViewById(R.id.no_data_layout);
		realy_data_layout = (RelativeLayout) view.findViewById(R.id.realy_data_layout);
		iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
		tv_text1 = (TextView) view.findViewById(R.id.tv_text1);
		tv_text2 = (TextView) view.findViewById(R.id.tv_text2);
		tv_test = (TextView) view.findViewById(R.id.tv_test);
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		asset_layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				NInvestmentResult result = MyApplication.investmentResult;
				if (result != null && result.getState() != null && result.getState().intValue() == 1) {
					NInvestementBean invest = result.getData();
					if (invest.getDataType() == 1) {
						MyApplication.home_refresh = false;
						Intent intent = new Intent(parentActivity, InvestPlannActivity.class);
						startActivity(intent);
					}
				}
			}
		});
		tv_test.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MyApplication.home_refresh = false;
				Intent intent = new Intent(parentActivity, InvestPlannActivity.class);
				startActivity(intent);
			}
		});
	}

	@SuppressLint("NewApi")
	public void changeLocate(int delta, float fraction, float fraction2) {
		NInvestmentResult result = MyApplication.investmentResult;
		if (result == null || result.getState() == null || result.getState().intValue() != 1) {
			return;
		}
		if (view == null) {
			return;
		}
		NInvestementBean invest = result.getData();
		if (invest.getDataType() == 1) {
			// if (delta == 0) {
			// name_layout.setVisibility(View.VISIBLE);
			// bottom_layout.setVisibility(View.VISIBLE);
			// } else {
			// name_layout.setVisibility(View.INVISIBLE);
			// bottom_layout.setVisibility(View.INVISIBLE);
			// }

			ringView.setTranslationY((int) ((getResources().getDimension(R.dimen.px2dp_504) - getResources()
					.getDimension(R.dimen.px2dp_63)) * fraction2));
			ring_center_layout.setTranslationY((int) ((getResources().getDimension(R.dimen.px2dp_504) - getResources()
					.getDimension(R.dimen.px2dp_63)) * fraction2));
			ring_top_layout.setTranslationY((int) ((getResources().getDimension(R.dimen.px2dp_504) - getResources()
					.getDimension(R.dimen.px2dp_63)) * fraction2));
			// 缩小倍数x从1到0.5，y从1到0.3（1表示不缩放，大于1放大，小于1缩小）
			ringView.setScaleX(1 - fraction2 * (1 - 0.7f));
			ringView.setScaleY(1 - fraction2 * (1 - 0.7f));
			ringView.setAlpha(1 - fraction2 * (1 - 0.4f));
			ring_top_layout.setScaleX(1 - fraction2 * (1 - 0.7f));
			ring_top_layout.setScaleY(1 - fraction2 * (1 - 0.7f));
			if (fraction > 0.5) {
				name_layout.setVisibility(View.INVISIBLE);
				center_txt.setVisibility(View.INVISIBLE);
				ring_top_layout.setVisibility(View.INVISIBLE);
			} else {
				name_layout.setVisibility(View.VISIBLE);
				center_txt.setVisibility(View.VISIBLE);
				ring_top_layout.setVisibility(View.VISIBLE);
				center_txt.setAlpha(1 - fraction2 * 3);
				ring_top_layout.setAlpha(1 - fraction2 * 3);
			}
		} else {

			float tempValue = getResources().getDimension(R.dimen.px2dp_128)
					/ getResources().getDimension(R.dimen.px2dp_236);
			tempValue = 1 - (1 - tempValue) * fraction2;
			iv_icon.setScaleX(tempValue);
			iv_icon.setScaleY(tempValue);
			tempValue = (getResources().getDimension(R.dimen.px2dp_410) - getResources().getDimension(R.dimen.px2dp_40))
					* fraction2;
			iv_icon.setTranslationY(tempValue);
			// not_login_txt1.setTranslationY(tempValue);
			// not_login_txt2.setTranslationY(tempValue);
			tempValue = (float) (fraction2 / 0.5);
			tv_text1.setAlpha(1 - tempValue);
			tv_text2.setAlpha(1 - tempValue);
			tv_test.setAlpha(1 - tempValue);
		}		
		bottom_layout.setAlpha(1 - fraction2 * 3);
		if (fraction > 0.3) {
			waveView.setVisibility(View.VISIBLE);
		} else {
			waveView.setVisibility(View.INVISIBLE);
		}
		float alpha = (float) ((fraction2 - 0.3) / (1 - 0.3));
		waveView.setAlpha(alpha);
	}

	public void initData() {
		// TODO Auto-generated method stub
		NInvestmentResult result = MyApplication.investmentResult;
		if (result != null && result.getState() != null && result.getState().intValue() == 1) {
			name_txt1.setText(StringUtil.showStringContent(result.getData().getName1()));
			name_txt2.setText(StringUtil.showStringContent(result.getData().getName2()));
			name_txt3.setText(StringUtil.showStringContent(result.getData().getName3()));
			name_txt4.setText(StringUtil.showStringContent(result.getData().getName4()));
			NInvestementBean temp = result.getData();
			// 现有 60 40 7 20
			float valueLst1[] = { temp.getValueRatio1(), temp.getValueRatio2(), temp.getValueRatio3(),
					temp.getValueRatio4() };
			// 推荐 80 60 20 15
			float valueLst2[] = { temp.getValueRatio5(), temp.getValueRatio6(), temp.getValueRatio7(),
					temp.getValueRatio8() };

			ringView.setValueLst(valueLst1, valueLst2);
		} else {

		}
	}

	private void initRing() {
		NInvestmentResult result = MyApplication.investmentResult;
		if (result != null && result.getState() != null && result.getState().intValue() == 1) {
			NInvestementBean temp = result.getData();
			// 现有 60 40 7 20
			float valueLst1[] = { temp.getValueRatio1(), temp.getValueRatio2(), temp.getValueRatio3(),
					temp.getValueRatio4() };
			// 推荐 80 60 20 15
			float valueLst2[] = { temp.getValueRatio5(), temp.getValueRatio6(), temp.getValueRatio7(),
					temp.getValueRatio8() };

			ringView.setValueLst(valueLst1, valueLst2);
		} else {

		}
	}
}
