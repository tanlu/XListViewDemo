package com.miduo.financialmanageclient.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.BarBean;
import com.miduo.financialmanageclient.bean.InsureBean;
import com.miduo.financialmanageclient.bean.NInsuranceBean;
import com.miduo.financialmanageclient.bean.NInsuranceHomeBean;
import com.miduo.financialmanageclient.bean.PerBarBean;
import com.miduo.financialmanageclient.common.CheckTestCount;
import com.miduo.financialmanageclient.listener.HistoryShowListener;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.SharePrefUtil;
import com.miduo.financialmanageclient.widget.BarPictrue;
import com.miduo.financialmanageclient.widget.BarTextView;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

/**
 * 投保首页
 * 
 * @author huozhenpeng
 * 
 */
public class InsurePlannActivity extends GesterSetBaseActivity implements OnClickListener {
	private TextView start_btn, history_txt;
	private ImageView left_img;
	private BarBean barBean;
	private BarPictrue barPicture;
	private BarTextView barTextView;
	private ArrayList<PerBarBean> lists;
	private BarBean barBean2;
	private ArrayList<PerBarBean> lists1;
	private NInsuranceHomeBean nIsuranceHomeResult;
	private NInsuranceBean nIsuranceBean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insure_plann);
		initUi();
		initEvent();
		initData();
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("如何投保首页"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("如何投保首页"); //
		MobclickAgent.onPause(this);
	}

	private void initEvent() {
		start_btn.setOnClickListener(this);
		history_txt.setOnClickListener(this);
		left_img.setOnClickListener(this);
	}

	private void initUi() {
		start_btn = (TextView) findViewById(R.id.start_btn);
		history_txt = (TextView) findViewById(R.id.history_txt);
		left_img = (ImageView) findViewById(R.id.left_img);
		barPicture = (BarPictrue) findViewById(R.id.barPicture);
		barTextView = (BarTextView) findViewById(R.id.barTextView);
	}

	private void initData() {
		barBean = new BarBean();
		lists = new ArrayList<PerBarBean>();

		barBean2 = new BarBean();
		lists1 = new ArrayList<PerBarBean>();

		ProgressDialogUtil.showProgress(this);
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					nIsuranceHomeResult = WebServiceClient.getInsuranceHomeData();
					handler.sendEmptyMessage(0x01);
				} catch (AppException e) {
					handler.sendEmptyMessage(0x02);
					e.printStackTrace();
				}

			}
		}).start();

	}

	private Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			ProgressDialogUtil.closeProgress();
			switch (msg.what) {
			case 0x01:// 投保首页柱子数据返回
				if (nIsuranceHomeResult != null) {
					lists.clear();
					lists1.clear();
					if (nIsuranceHomeResult.getState() == 1)// 请求成功
					{
						nIsuranceBean = nIsuranceHomeResult.getData();
						barBean.setTotal(100);
						lists.add(new PerBarBean((int) nIsuranceBean.getSeftRatio1(), (int) nIsuranceBean
								.getSpouseRatio1(), nIsuranceBean.getName1()));
						lists.add(new PerBarBean((int) nIsuranceBean.getSeftRatio2(), (int) nIsuranceBean
								.getSpouseRatio2(), nIsuranceBean.getName2()));
						lists.add(new PerBarBean((int) nIsuranceBean.getSeftRatio3(), (int) nIsuranceBean
								.getSpouseRatio3(), nIsuranceBean.getName3()));
						lists.add(new PerBarBean((int) nIsuranceBean.getSeftRatio4(), (int) nIsuranceBean
								.getSpouseRatio4(), nIsuranceBean.getName4()));
						barBean.setDatas(lists);
						barPicture.clearData();
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
						barBean2.setDatas(lists1);
						barTextView.setDatas(barBean2);
						initHistoryShow();
					} else// 请求失败
					{
						if(nIsuranceHomeResult.getState()==ConstantValues.LOGIN_ERROR)
						{
							MDialog.showPsdErrorDialog(InsurePlannActivity.this, nIsuranceHomeResult.getMsg());
						}
						else
						{
							handler.sendEmptyMessage(0x02);
						}
						
					}
				} else {
					initHistoryShow();
				}
				break;
			case 0x02:// 异常情况
				// barBean.setTotal(80);
				// lists.add(new PerBarBean(80, 60, "定期寿险"));
				// lists.add(new PerBarBean(60, 40, "意外险"));
				// lists.add(new PerBarBean(20, 7, "重疾险"));
				// lists.add(new PerBarBean(15, 20, "医疗险"));
				// barBean.setDatas(lists);
				// barPicture.setDatas(barBean);
				// new Thread(barPicture).start();
				// lists1.add(new PerBarBean(80, 60, "定期寿险"));
				// lists1.add(new PerBarBean(60, 40, "意外险"));
				// lists1.add(new PerBarBean(20, 7, "重疾险"));
				// lists1.add(new PerBarBean(15, 20, "医疗险"));
				// barBean2.setDatas(lists1);
				// barTextView.setDatas(barBean2);
				initHistoryShow();
				break;

			default:
				break;
			}
		};
	};

	private void initHistoryShow() {
		boolean isLogin = SharePrefUtil.getBoolean(this, SharePrefUtil.IS_LOGIN, false);
		if (!isLogin) {
			history_txt.setVisibility(View.GONE);
		} else {
			HistoryShowListener historyShowListener = new HistoryShowListener() {

				@Override
				public void setIsShow(Integer count) {
					// TODO Auto-generated method stub
					if (count != null && count > 0) {
						history_txt.setVisibility(View.VISIBLE);
					} else {
						history_txt.setVisibility(View.GONE);
					}
				}
			};
			CheckTestCount checkCount = new CheckTestCount(this, historyShowListener, 2);
			checkCount.getData();
		}
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.start_btn:
			MyApplication.insureBean = null;
			MyApplication.insureBean = new InsureBean();
			intent.setClass(this, InsureOneActivity.class);
			startActivity(intent);
			break;
		case R.id.history_txt:
			intent.putExtra("type", 1);
			intent.setClass(this, HistoryProblemResultActivity.class);
			startActivity(intent);
			break;

		case R.id.left_img:
			finish();
		default:
			break;
		}
	}
}
