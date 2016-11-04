package com.miduo.financialmanageclient.ui.fragment;

import java.util.ArrayList;

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
import com.miduo.financialmanageclient.bean.BarBean;
import com.miduo.financialmanageclient.bean.NInsuranceBean;
import com.miduo.financialmanageclient.bean.NInsuranceHomeBean;
import com.miduo.financialmanageclient.bean.PerBarBean;
import com.miduo.financialmanageclient.ui.HomeActivity;
import com.miduo.financialmanageclient.ui.InsurePlannActivity;
import com.miduo.financialmanageclient.widget.BarPictrue;
import com.miduo.financialmanageclient.widget.BarTextView;
import com.miduo.financialmanageclient.widget.WaveView;

/**
 * 保险规划页面
 * 
 * @author huozhenpeng
 * 
 */
public class InsurancePlanInfoFragment extends Fragment {
	private HomeActivity parentActivity;
	private RelativeLayout rl_top;
	private View view;
	private BarBean barBean;
	private BarPictrue barPicture;
	private BarTextView barTextView;
	private WaveView waveView;
	private LinearLayout linearBottom;
	private LinearLayout ll_point;
	private LinearLayout ll_point1;
	private TextView tv_top;
	private ImageView imagewave;
	private ImageView iv_bgline;
	private NInsuranceHomeBean nIsuranceHomeResult;
	private NInsuranceBean nIsuranceBean;
	private ArrayList<PerBarBean> lists;
	private ArrayList<PerBarBean> lists1;
	private BarBean barBean2;
	private RelativeLayout insure_layout;
	private ImageView iv_icon;
	private TextView tv_text1;
	private TextView tv_text2;
	private TextView tv_test;
	private RelativeLayout rl_nologin;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_insurance_plan_info, null);
		parentActivity = (HomeActivity) getActivity();
		initView();
		initEvent();
		initData();
		return view;
	}

	private void initView() {
		ll_point = (LinearLayout) view.findViewById(R.id.ll_point);
		ll_point1 = (LinearLayout) view.findViewById(R.id.ll_point1);
		linearBottom = (LinearLayout) view.findViewById(R.id.linear_bottom);
		rl_top = (RelativeLayout) view.findViewById(R.id.rl_top);
		barPicture = (BarPictrue) view.findViewById(R.id.barPicture);
		barTextView = (BarTextView) view.findViewById(R.id.barTextView);
		waveView = (WaveView) view.findViewById(R.id.waveView);
		tv_top = (TextView) view.findViewById(R.id.tv_top);
		imagewave = (ImageView) view.findViewById(R.id.imagewave);
		iv_bgline = (ImageView) view.findViewById(R.id.iv_bgline);
		insure_layout = (RelativeLayout) view.findViewById(R.id.rl_parent);
		iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
		tv_text1 = (TextView) view.findViewById(R.id.tv_text1);
		tv_text2 = (TextView) view.findViewById(R.id.tv_text2);
		tv_test = (TextView) view.findViewById(R.id.tv_test);
		rl_nologin = (RelativeLayout) view.findViewById(R.id.rl_nologin);
	}

	@SuppressLint("NewApi")
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		// TODO Auto-generated method stub
		// 判断Fragment中的ListView时候存在，判断该Fragment时候已经正在前台显示
		// 通过这两个判断，就可以知道什么时候去加载数据了
		if (isVisibleToUser && isVisible()) {
			barPicture.clearData();
			if (barBean.getDatas() == null) {
				initData();
				return;
			} else {
				if (nIsuranceHomeResult.getData().getDataType() == 1) {// 真实数据
					showLogin();
				} else {
					showNoLogin();
				}
			}
			barPicture.setDatas(barBean);
		} else {
			if (barPicture != null)
				barPicture.setDatas(null);
		}
		super.setUserVisibleHint(isVisibleToUser);
	}

	private void initEvent() {
		insure_layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				NInsuranceHomeBean result = MyApplication.insureResult;
				if (result != null && result.getState() == 1) {
					NInsuranceBean insure = result.getData();
					if (insure.getDataType() == 1) {
						MyApplication.home_refresh = false;
						Intent intent = new Intent(parentActivity, InsurePlannActivity.class);
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
				Intent intent = new Intent(parentActivity, InsurePlannActivity.class);
				startActivity(intent);
			}
		});
	}

	private void initData() {
		barBean = new BarBean();
		lists = new ArrayList<PerBarBean>();

		barBean2 = new BarBean();
		lists1 = new ArrayList<PerBarBean>();

		nIsuranceHomeResult = MyApplication.insureResult;
		if (nIsuranceHomeResult != null) {

			lists.clear();
			lists1.clear();
			if (nIsuranceHomeResult.getState() == 1)// 请求成功
			{
				if (nIsuranceHomeResult.getData().getDataType() == 1) {// 真实数据
					showLogin();
					nIsuranceBean = nIsuranceHomeResult.getData();
					barBean.setTotal(100);
					lists.add(new PerBarBean((int) nIsuranceBean.getSeftRatio1(),
							(int) nIsuranceBean.getSpouseRatio1(), nIsuranceBean.getName1()));
					lists.add(new PerBarBean((int) nIsuranceBean.getSeftRatio2(),
							(int) nIsuranceBean.getSpouseRatio2(), nIsuranceBean.getName2()));
					lists.add(new PerBarBean((int) nIsuranceBean.getSeftRatio3(),
							(int) nIsuranceBean.getSpouseRatio3(), nIsuranceBean.getName3()));
					lists.add(new PerBarBean((int) nIsuranceBean.getSeftRatio4(),
							(int) nIsuranceBean.getSpouseRatio4(), nIsuranceBean.getName4()));
					barBean.setDatas(lists);
					barPicture.setDatas(barBean);
					new Thread(barPicture).start();
					lists1.add(new PerBarBean((int) nIsuranceBean.getSelf1(), (int) nIsuranceBean.getSpouse1(),
							nIsuranceBean.getName1()));
					lists1.add(new PerBarBean((int) nIsuranceBean.getSelf2(), (int) nIsuranceBean.getSpouse2(),
							nIsuranceBean.getName2()));
					lists1.add(new PerBarBean((int) nIsuranceBean.getSelf3(), (int) nIsuranceBean.getSpouse3(),
							nIsuranceBean.getName3()));
					lists1.add(new PerBarBean((int) nIsuranceBean.getSelf4(), (int) nIsuranceBean.getSpouse4(),
							nIsuranceBean.getName4()));
					barBean2.setTotal(100);
					barBean2.setDatas(lists1);
					barTextView.setDatas(barBean2);
				} else {
					showNoLogin();
				}

			} else// 请求失败
			{
				showNoLogin();
			}
		} else {
			// showNoLogin();
		}
	}

	/**
	 * 显示没登录状态下的页面
	 */
	private void showNoLogin() {
		rl_nologin.setVisibility(View.VISIBLE);
		rl_top.setVisibility(View.INVISIBLE);
		tv_top.setVisibility(View.INVISIBLE);
	}

	/**
	 * 显示真实数据下的页面
	 */
	private void showLogin() {
		rl_nologin.setVisibility(View.INVISIBLE);
		rl_top.setVisibility(View.VISIBLE);
		tv_top.setVisibility(View.VISIBLE);
	}

	/**
	 * 
	 * @param fraction
	 *            变换因子
	 * @param startValue
	 *            起始值
	 * @param endValue
	 *            结束指
	 * @return
	 */
	public Float evaluate(float fraction, Number startValue, Number endValue) {
		float startFloat = startValue.floatValue();
		return startFloat + fraction * (endValue.floatValue() - startFloat);
	}

	/**
	 * 控制动画效果
	 * 
	 * @param fraction值从0到1
	 *            (向上滑动从0变为1，向下滑动从1变为0)
	 */
	@SuppressLint("NewApi")
	public void changeLocate(float fraction) {
		// float
		// barY=((RelativeLayout.LayoutParams)barPicture.getLayoutParams()).bottomMargin+(barPicture.getMeasuredHeight()/2.0f-barPicture.getMeasuredHeight()*0.3f*0.5f);//0.3与底下的0.7有关系
		if (rl_top == null) {
			return;
		}
		float parentHeight = rl_top.getMeasuredHeight();
		float barTopMargin = ((RelativeLayout.LayoutParams) barPicture.getLayoutParams()).topMargin;
		float barY = parentHeight - barPicture.getBottom() + barPicture.getMeasuredHeight() / 4.0f;
		barPicture.setTranslationY(evaluate(fraction, 0, barY));
		float barX = barPicture.getMeasuredWidth() / 4;
		// barPicture.setTranslationX(evaluate(fraction, 0, barX));
		// 缩小倍数x从1到0.5，y从1到0.3（1表示不缩放，大于1放大，小于1缩小）
		barPicture.setScaleX(1 - fraction * 0.5f);
		barPicture.setScaleY(1 - fraction * 0.5f);
		barPicture.setAlpha(1 - fraction * 0.6f);
//		if (fraction >= 0.5) {
//			// waveView.setVisibility(View.VISIBLE);
//			imagewave.setVisibility(View.VISIBLE);
//		} else {
//			// waveView.setVisibility(View.INVISIBLE);
//			imagewave.setVisibility(View.INVISIBLE);
//		}
		imagewave.setVisibility(View.VISIBLE);
		imagewave.setAlpha(fraction);
		if (fraction >= 0.1) {
			ll_point.setVisibility(View.INVISIBLE);
			ll_point1.setVisibility(View.INVISIBLE);
		} else {
			ll_point.setVisibility(View.VISIBLE);
			ll_point1.setVisibility(View.VISIBLE);
		}
		barTextView.setAlpha(1 - fraction * 8.0f);
		tv_text1.setAlpha(1 - fraction * 8.0f);
		tv_text2.setAlpha(1 - fraction * 8.0f);
		linearBottom.setAlpha(1 - fraction * 3.0f);
		tv_test.setAlpha(1 - fraction * 3.0f);
		tv_top.setTranslationY(evaluate(fraction, 0, getResources().getDimension(R.dimen.px2dp_816)
				- getResources().getDimension(R.dimen.px2dp_288) - tv_top.getBottom()));
		tv_top.setAlpha(1 - fraction * 5.0f);
		iv_icon.setTranslationY(evaluate(fraction, 0,
				getResources().getDimension(R.dimen.px2dp_816) - getResources().getDimension(R.dimen.px2dp_288)
						- tv_top.getBottom() - getResources().getDimension(R.dimen.px2dp_35)));
		iv_icon.setScaleX(1 - fraction * 0.46f);
		iv_icon.setScaleY(1 - fraction * 0.46f);
		iv_bgline.setTranslationY(evaluate(fraction, 0, getResources().getDimension(R.dimen.px2dp_816)
				- getResources().getDimension(R.dimen.px2dp_288) - tv_top.getBottom()));
		iv_bgline.setAlpha(1 - fraction * 2.0f);
	}

}
