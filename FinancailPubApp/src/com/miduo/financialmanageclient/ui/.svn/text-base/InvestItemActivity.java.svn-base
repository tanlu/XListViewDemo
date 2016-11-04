package com.miduo.financialmanageclient.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.bean.InVesteResult;
import com.miduo.financialmanageclient.bean.InvestRecordDetail;
import com.miduo.financialmanageclient.bean.InvestResultBean;
import com.miduo.financialmanageclient.util.JsonUtils;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.widget.InvestmentBarPic;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

public class InvestItemActivity extends GesterSetBaseActivity implements OnClickListener {
	private ImageView leftImg;
	private TextView titleTxt;
	private TextView rightTxt;
	private TextView tv_data, time_txt, age_txt, income_txt, invest_amount_txt,
			care_txt, decide_txt;
	private InvestmentBarPic inVinvestmentBarPic;
	private InVesteResult inVesteResult;
	private int tid;
	private SelDetailAsyncTask selDetailAsyncTask;
	private TextView name_txt1, name_txt2, name_txt3, name_txt4;
	private List<InvestResultBean> lists;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invest_item);
		tid = getIntent().getIntExtra("tid", -1);
		initUi();
		initEvent();
		initData();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("投资测试记录详情"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("投资测试记录详情"); //
		MobclickAgent.onPause(this);
	}

	private void initUi() {
		// TODO Auto-generated method stub
		name_txt1 = (TextView) findViewById(R.id.name_txt1);
		name_txt2 = (TextView) findViewById(R.id.name_txt2);
		name_txt3 = (TextView) findViewById(R.id.name_txt3);
		name_txt4 = (TextView) findViewById(R.id.name_txt4);
		leftImg = (ImageView) findViewById(R.id.left_img);
		titleTxt = (TextView) findViewById(R.id.title_txt);
		rightTxt = (TextView) findViewById(R.id.right_txt);
		titleTxt.setText("投资测试记录");
		rightTxt.setVisibility(View.GONE);
		inVinvestmentBarPic = (InvestmentBarPic) this
				.findViewById(R.id.investmentBarPic);

		tv_data = (TextView) findViewById(R.id.tv_data);
		time_txt = (TextView) findViewById(R.id.time_txt);
		age_txt = (TextView) findViewById(R.id.age_txt);
		income_txt = (TextView) findViewById(R.id.income_txt);
		invest_amount_txt = (TextView) findViewById(R.id.invest_amount_txt);
		care_txt = (TextView) findViewById(R.id.care_txt);
		decide_txt = (TextView) findViewById(R.id.decide_txt);
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		leftImg.setOnClickListener(this);
	}

	private void initData() {
		// TODO Auto-generated method stub
		if (tid == -1) {
			return;
		}
		ProgressDialogUtil.showProgress(this);
		if (selDetailAsyncTask != null) {
			selDetailAsyncTask.cancel(true);
			selDetailAsyncTask = null;
		}
		selDetailAsyncTask = new SelDetailAsyncTask();
		selDetailAsyncTask.execute();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.left_img:
			finish();
			break;
		default:
			break;
		}
	}

	private class SelDetailAsyncTask extends AsyncTask<Void, Void, String> {
		private AppException ex;

		public SelDetailAsyncTask() {
		}

		@Override
		protected String doInBackground(Void... arg0) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("tid", String.valueOf(tid));
			try {
				ex = null;
				return WebServiceClient.getRecordDetail(map, 0);
			} catch (AppException e) {
				this.ex = e;
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			ProgressDialogUtil.closeProgress();
			if (null == result) {
				if (ex != null) {
					ex.makeToast(InvestItemActivity.this);
					return;
				}
				MToast.showToast(InvestItemActivity.this, "获取信息失败！");
				return;
			} else {
				try {
					JSONObject jo = new JSONObject(result);
					int state = jo.getInt("state");
					if (state == ConstantValues.LOGIN_ERROR) {
						String msgStr = jo.getString("msg");
						MDialog.showPsdErrorDialog(InvestItemActivity.this, msgStr);
						return;
					} else if (state == 1) {
						String data = jo.getString("data");
						InvestRecordDetail item = JsonUtils.toBean(data,
								new TypeToken<InvestRecordDetail>() {
								}.getType());
						showData(item);
					} else {
						String msg = jo.getString("msg");
						MToast.showToast(InvestItemActivity.this,
								StringUtil.isEmpty(msg) ? "获取信息失败！" : msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
					MToast.showToast(InvestItemActivity.this, "数据异常");
				}
			}
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
	}

	private void showData(InvestRecordDetail item) {
		// TODO Auto-generated method stub
		// 风险系数
		tv_data.setText(String.valueOf(item.getRiskValue()));
		name_txt1.setText(StringUtil.showStringContent(item.getName1()));
		name_txt2.setText(StringUtil.showStringContent(item.getName2()));
		name_txt3.setText(StringUtil.showStringContent(item.getName3()));
		name_txt4.setText(StringUtil.showStringContent(item.getName4()));
		time_txt.setText(StringUtil.showStringContent(item.getDateTime()));
		age_txt.setText(StringUtil.showStringContent(item.getRow1()));
		income_txt.setText(StringUtil.showStringContent(item.getRow2()));
		invest_amount_txt.setText(StringUtil.showStringContent(item.getRow3()));
		care_txt.setText(StringUtil.showStringContent(item.getRow4()));
		decide_txt.setText(StringUtil.showStringContent(item.getRow5()));
		inVesteResult = new InVesteResult();
		inVesteResult.setTotal(100);
		lists = new ArrayList<InvestResultBean>();
		lists.add(new InvestResultBean((int)item.getValue1()+"%", item.getValueRatio1(), "#ffffff"));
		lists.add(new InvestResultBean((int)item.getValue2()+"%", item.getValueRatio2(), "#b7efff"));
		lists.add(new InvestResultBean((int)item.getValue3()+"%", item.getValueRatio3(), "#66dbff"));
		lists.add(new InvestResultBean((int)item.getValue4()+"%", item.getValueRatio4(), "#14c9fe"));
		inVesteResult.setLists(lists);
		inVinvestmentBarPic.setData(inVesteResult);
	}

}
