package com.miduo.financialmanageclient.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.InVesteResult;
import com.miduo.financialmanageclient.bean.InvestBean;
import com.miduo.financialmanageclient.bean.InvestResultBean;
import com.miduo.financialmanageclient.bean.NInvestementBean;
import com.miduo.financialmanageclient.bean.NInvestmentResult;
import com.miduo.financialmanageclient.common.CommonUtil;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.widget.InvestmentBarPic;
import com.miduo.financialmanageclient.widget.InvestmentBarPic.OnBarClickListener;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

/**
 * 如何投资记录页面
 * 
 * @author huozhenpeng
 * 
 */
public class InvestPlannResultActivity extends BaseActivity implements
		OnClickListener {
	private InvestmentBarPic inVinvestmentBarPic;
	private InVesteResult inVesteResult;
	private List<InvestResultBean> lists;
	private ImageView iv_add;
	private ImageView iv_less;
	private TextView tv_data;
	private ImageView left_img;
	private TextView title_txt;
	private TextView right_txt;
	private TextView tv_retry;
	private TextView tv_check;
	private NInvestmentResult nInvestmentResult;
	private NInvestementBean nInvestementBean;
	private TextView tv_text1;
	private TextView tv_text2;
	private TextView tv_text3;
	private TextView tv_text4;
	private InvestBean bean;
	private HashMap<String, String> map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_invest_plann_result);
		MyApplication.index=0;
		initView();
		initEvent();
		initData();
	}

	private void initData() {
		bean= MyApplication.investBean;
		inVesteResult = new InVesteResult();
		inVesteResult.setTotal(100);
		lists = new ArrayList<InvestResultBean>();
		ProgressDialogUtil.showProgress(this);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					map.clear();
					map.put("age", bean.getAge()+"");
					map.put("aftertaxIncome", bean.getAftertaxIncome()+"");
					map.put("investmentFunds", bean.getInvestmentFunds()+"");
					map.put("carefor", bean.getCarefor()+"");
					map.put("howDo", bean.getHowDo()+"");
					nInvestmentResult=WebServiceClient.getInvestmentResultData(map);
					handler.sendEmptyMessage(0x01);
					
				} catch (AppException e) {
					handler.sendEmptyMessage(0x02);
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void initEvent() {
		inVinvestmentBarPic.setOnBarClickListerer(new OnBarClickListener() {

			@Override
			public void barClick(int index) {
				MyApplication.home_index = 1;
				MyApplication.index=index+1;
				Intent intent=new Intent();
				intent.setClass(InvestPlannResultActivity.this, HomeActivity.class);			
				startActivity(intent);
			}
		});
		iv_add.setOnClickListener(this);
		iv_less.setOnClickListener(this);
		left_img.setOnClickListener(this);
		tv_retry.setOnClickListener(this);
		tv_check.setOnClickListener(this);
	}

	private void initView() {
		inVinvestmentBarPic = (InvestmentBarPic) this
				.findViewById(R.id.investmentBarPic);
		iv_add = (ImageView) this.findViewById(R.id.iv_add1);
		iv_less = (ImageView) this.findViewById(R.id.iv_less1);
		tv_data = (TextView) this.findViewById(R.id.tv_data);
		left_img = (ImageView) this.findViewById(R.id.left_img);
		title_txt = (TextView) this.findViewById(R.id.title_txt);
		title_txt.setText("测试结论");
		right_txt = (TextView) this.findViewById(R.id.right_txt);
		right_txt.setVisibility(View.INVISIBLE);
		tv_retry=(TextView) this.findViewById(R.id.tv_retry);
		tv_check=(TextView) this.findViewById(R.id.tv_check);
		tv_text1=(TextView) this.findViewById(R.id.tv_text1);
		tv_text2=(TextView) this.findViewById(R.id.tv_text2);
		tv_text3=(TextView) this.findViewById(R.id.tv_text3);
		tv_text4=(TextView) this.findViewById(R.id.tv_text4);
		map=new HashMap<String,String>();

	}

	@Override
	public void onClick(View v) {
		Intent intent=new Intent();
		switch (v.getId()) {
		case R.id.iv_add1:
			if (Float.valueOf(tv_data.getText().toString()) < 10) {
				iv_add.setImageResource(R.drawable.add1);
				iv_less.setImageResource(R.drawable.less1);
				tv_data.setText((Float.valueOf(tv_data.getText().toString()) + 0.5f)
						+ "");
				if(Float.valueOf(tv_data.getText().toString()) == 10)
				{
					iv_add.setImageResource(R.drawable.add_gray);
				}
				float data = Float.valueOf(tv_data.getText().toString());
				map.clear();
				map.put("riskValue", data+"");
				requestData();
				
			}
			else
			{
				iv_add.setImageResource(R.drawable.add_gray);
			}

			break;
		case R.id.iv_less1:
			if (Float.valueOf(tv_data.getText().toString()) > 0.5) {
				iv_less.setImageResource(R.drawable.less1);
				iv_add.setImageResource(R.drawable.add1);
				tv_data.setText((Float.valueOf(tv_data.getText().toString()) - 0.5f)
						+ "");
				if (Float.valueOf(tv_data.getText().toString()) == 0.5) {
					iv_less.setImageResource(R.drawable.less_gray);
				}
				float data1 = Float.valueOf(tv_data.getText().toString());
				map.clear();
				map.put("riskValue", data1+"");
				requestData();
			}
			else
			{
				iv_less.setImageResource(R.drawable.less_gray);
			}

			break;
		case R.id.left_img:
			finish();
			break;
		case R.id.tv_retry:			
			intent.setClass(this, FinancialToolActivity.class);
			startActivity(intent);
			break;
		case R.id.tv_check:
			if (!CommonUtil.checkLogin(this)) {
				return;
			}
			MyApplication.home_index = 2;
			Intent intent1=new Intent();
			intent1.setClass(this, HomeActivity.class);			
			startActivity(intent1);
			finish();
			break;

		default:
			break;
		}
	}
	/**
	 * 根据风险系数获取数据
	 */
	private void requestData()
	{
		ProgressDialogUtil.showProgress(this);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					nInvestmentResult=WebServiceClient.getInvestmentRiskesultData(map);
				} catch (AppException e) {
					handler.sendEmptyMessage(0x02);
					e.printStackTrace();
				}
				handler.sendEmptyMessage(0x01);
			}
		}).start();
	}
	private Handler handler=new Handler()
	{
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0x01:
				ProgressDialogUtil.closeProgress();
				if(nInvestmentResult!=null)
				{
					if(nInvestmentResult.getState()==1)//请求成功
					{
						nInvestementBean=nInvestmentResult.getData();
						if(nInvestementBean.getDataType()==1)//真实数据（不会有假数据）
						{
							lists.clear();
							lists.add(new InvestResultBean((int)nInvestementBean.getValue1()+"%", (int)nInvestementBean.getValueRatio1(), "#ffffff"));
							lists.add(new InvestResultBean((int)nInvestementBean.getValue2()+"%", (int)nInvestementBean.getValueRatio2(), "#b7efff"));
							lists.add(new InvestResultBean((int)nInvestementBean.getValue3()+"%", (int)nInvestementBean.getValueRatio3(), "#66dbff"));
							lists.add(new InvestResultBean((int)nInvestementBean.getValue4()+"%", (int)nInvestementBean.getValueRatio4(), "#14c9fe"));
							inVesteResult.setLists(lists);
							inVinvestmentBarPic.setData(inVesteResult);
							tv_text1.setText(nInvestementBean.getName1());
							tv_text2.setText(nInvestementBean.getName2());
							tv_text3.setText(nInvestementBean.getName3());
							tv_text4.setText(nInvestementBean.getName4());
							tv_data.setText(nInvestementBean.getRiskValue()+"");
						}
					}
					else//请求失败
					{
						if(nInvestmentResult.getState()==ConstantValues.LOGIN_ERROR)
						{
							MDialog.showPsdErrorDialog(InvestPlannResultActivity.this, nInvestmentResult.getMsg());
						}
					}
				}
				break;
			case 0x02:
				ProgressDialogUtil.closeProgress();
				break;

			default:
				break;
			}
		};
	};

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("如何投资"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("如何投资"); //
		MobclickAgent.onPause(this);
	}

}
