package com.miduo.financialmanageclient.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.InvestBean;
import com.miduo.financialmanageclient.bean.NInvestementBean;
import com.miduo.financialmanageclient.bean.NInvestmentResult;
import com.miduo.financialmanageclient.common.CheckTestCount;
import com.miduo.financialmanageclient.listener.HistoryShowListener;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.SharePrefUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.widget.RingView;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

public class InvestPlannActivity extends GesterSetBaseActivity implements OnClickListener {
	private RingView ringView;
	private TextView start_btn, history_txt;
	private ImageView left_img;
	private InvestHomeDataAsyncTask investHomeDataAsyncTask;
	private TextView name_txt1, name_txt2, name_txt3, name_txt4;
	private NInvestmentResult ringData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invest_plann);
		initUi();
		initEvent();
		initData();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("如何投资首页"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("如何投资首页"); //
		MobclickAgent.onPause(this);
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		start_btn.setOnClickListener(this);
		history_txt.setOnClickListener(this);
		left_img.setOnClickListener(this);
	}

	private void initUi() {
		// TODO Auto-generated method stub
		ringView = (RingView) findViewById(R.id.ring_view);
		start_btn = (TextView) findViewById(R.id.start_btn);
		history_txt = (TextView) findViewById(R.id.history_txt);
		left_img = (ImageView) findViewById(R.id.left_img);
		name_txt1 = (TextView) findViewById(R.id.name_txt1);
		name_txt2 = (TextView) findViewById(R.id.name_txt2);
		name_txt3 = (TextView) findViewById(R.id.name_txt3);
		name_txt4 = (TextView) findViewById(R.id.name_txt4);
	}

	private void initData() {
		// TODO Auto-generated method stub
		ProgressDialogUtil.showProgress(InvestPlannActivity.this);
		if (investHomeDataAsyncTask != null) {
			investHomeDataAsyncTask.cancel(true);
			investHomeDataAsyncTask = null;
		}
		investHomeDataAsyncTask = new InvestHomeDataAsyncTask();
		investHomeDataAsyncTask.execute();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.start_btn:
			MyApplication.investBean = null;
			MyApplication.investBean = new InvestBean();
			intent.setClass(this, InvestPlannOneActivity.class);
			startActivity(intent);
			break;
		case R.id.history_txt:
			intent.putExtra("type", 0);
			intent.setClass(this, HistoryProblemResultActivity.class);
			startActivity(intent);
			break;
		case R.id.left_img:
			finish();
		default:
			break;
		}
	}

	private void showData() {
		// TODO Auto-generated method stub

		if (ringData != null && ringData.getState() != null && ringData.getState().intValue() == 1) {
			name_txt1.setText(StringUtil.showStringContent(ringData.getData().getName1()));
			name_txt2.setText(StringUtil.showStringContent(ringData.getData().getName2()));
			name_txt3.setText(StringUtil.showStringContent(ringData.getData().getName3()));
			name_txt4.setText(StringUtil.showStringContent(ringData.getData().getName4()));
			NInvestementBean temp = ringData.getData();
			// 现有 60 40 7 20
			float valueLst1[] = { temp.getValueRatio1(), temp.getValueRatio2(), temp.getValueRatio3(),
					temp.getValueRatio4() };			
			// 推荐 80 60 20 15
			float valueLst2[] = { temp.getValueRatio5(), temp.getValueRatio6(), temp.getValueRatio7(),
					temp.getValueRatio8() };

			ringView.setValueLst(valueLst1, valueLst2);
		}
		initHistoryShow();
	}

	private class InvestHomeDataAsyncTask extends AsyncTask<Void, Void, NInvestmentResult> {
		private AppException ex;

		@Override
		protected NInvestmentResult doInBackground(Void... arg0) {
			try {
				ex = null;
				return WebServiceClient.getInvestHomeData();
			} catch (AppException e) {
				// TODO Auto-generated catch block
				this.ex = e;
			}
			return null;
		}

		@Override
		protected void onPostExecute(NInvestmentResult result) {
			ProgressDialogUtil.closeProgress();
			ringData = result;
			if (null == result) {
				if (ex != null) {
					ex.makeToast(InvestPlannActivity.this);
				}
				initHistoryShow();
				return;
			} else {
				if (result.getState() != null && result.getState() == ConstantValues.LOGIN_ERROR) {
					MDialog.showPsdErrorDialog(InvestPlannActivity.this, result.getMsg());
					return;
				} else if (result.getState() == null || result.getState().intValue() == 0) {
					if (!StringUtil.isEmpty(result.getMsg())) {
						MToast.showToast(InvestPlannActivity.this, result.getMsg());
					}
					initHistoryShow();
					return;
				}
			}
			showData();
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
	}

	private void initHistoryShow() {
		boolean isLogin = SharePrefUtil.getBoolean(this, SharePrefUtil.IS_LOGIN, false);
		if (!isLogin) {
			history_txt.setVisibility(View.GONE);
		} else {
			HistoryShowListener historyShowListener = new HistoryShowListener() {

				@Override
				public void setIsShow(Integer count) {
					// TODO Auto-generated method stub
					System.out.println("********count****" + (count==null?"0":count));
					if (count != null && count > 0) {
						history_txt.setVisibility(View.VISIBLE);
					} else {
						history_txt.setVisibility(View.GONE);
					}
				}
			};
			CheckTestCount checkCount = new CheckTestCount(this, historyShowListener, 1);
			checkCount.getData();
		}
	}
}
