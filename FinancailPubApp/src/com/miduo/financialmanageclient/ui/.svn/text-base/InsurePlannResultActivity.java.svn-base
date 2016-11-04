package com.miduo.financialmanageclient.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.InVesteResult;
import com.miduo.financialmanageclient.bean.InsureBean;
import com.miduo.financialmanageclient.bean.InvestResultBean;
import com.miduo.financialmanageclient.bean.NInsureanceResult;
import com.miduo.financialmanageclient.bean.NIsuranceBean;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.widget.InvestmentBarPic;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

/**
 * 
 * @author huozhenpeng 投保测试结论页面
 * 
 */
public class InsurePlannResultActivity extends GesterSetBaseActivity implements
		OnClickListener {
	private ImageView leftImg;
	private TextView titleTxt;
	private TextView rightTxt;
	private TextView owner_txt, spouse_txt, child_txt, repeat_test_txt,
			ask_txt;
	// 当前选择的是本人 0 配偶 1 子女 2
	private int currentIndex = 0;
	private InvestmentBarPic investmentBarPic;
	private InvestmentBarPic investmentBarPic2;
	private LinearLayout ll_layout1;
	private LinearLayout ll_layout2;
	private InVesteResult inVesteResult;
	private List<InvestResultBean> lists;// 本人
	private List<InvestResultBean> lists1;// 配偶
	private List<InvestResultBean> lists2;// 子女
	private NInsureanceResult nInsureanceResult;
	private NIsuranceBean nIsuranceBean;
	private InsureBean insureBean;
	private HashMap<String, String> map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_insure_plann_result);
		initUi();
		initEvent();
		initData();
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("投保测试结论"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("投保测试结论"); //
		MobclickAgent.onPause(this);
	}

	private void initUi() {
		leftImg = (ImageView) findViewById(R.id.left_img);
		titleTxt = (TextView) findViewById(R.id.title_txt);
		rightTxt = (TextView) findViewById(R.id.right_txt);
		owner_txt = (TextView) findViewById(R.id.owner_txt);
		spouse_txt = (TextView) findViewById(R.id.spouse_txt);
		child_txt = (TextView) findViewById(R.id.child_txt);
		repeat_test_txt = (TextView) findViewById(R.id.repeat_test_txt);
		ask_txt = (TextView) findViewById(R.id.ask_txt);
		titleTxt.setText("测试结论");
		rightTxt.setVisibility(View.GONE);
		investmentBarPic = (InvestmentBarPic) this
				.findViewById(R.id.investmentBarPic);
		investmentBarPic2 = (InvestmentBarPic) this
				.findViewById(R.id.investmentBarPic2);
		ll_layout1 = (LinearLayout) this.findViewById(R.id.ll_layout1);
		ll_layout2 = (LinearLayout) this.findViewById(R.id.ll_layout2);
	}

	private void initEvent() {
		leftImg.setOnClickListener(this);
		owner_txt.setOnClickListener(this);
		spouse_txt.setOnClickListener(this);
		child_txt.setOnClickListener(this);
		repeat_test_txt.setOnClickListener(this);
		ask_txt.setOnClickListener(this);
		investmentBarPic.setOnClickListener(this);
		investmentBarPic2.setOnClickListener(this);
	}

	private void initData() {
		insureBean = MyApplication.insureBean;
		inVesteResult = new InVesteResult();
		inVesteResult.setTotal(100);
		lists = new ArrayList<InvestResultBean>();
		lists1 = new ArrayList<InvestResultBean>();
		lists2 = new ArrayList<InvestResultBean>();
		ProgressDialogUtil.showProgress(this);
		new Thread(new Runnable() {
			@Override
			public void run() {
				map = new HashMap<String, String>();
				try {
					map.put("manAge", changeIngeger(insureBean.getManAge()));
					map.put("manIncome",
							changeIngeger(insureBean.getManIncome()));
					map.put("manOutlay",
							changeIngeger(insureBean.getManOutlay()));
					map.put("manIndebtMoney",
							changeIngeger(insureBean.getManIndebtMoney()));
					map.put("manInvestMoney",
							changeIngeger(insureBean.getManInvestMoney()));
					map.put("wifeAge", changeIngeger(insureBean.getWifeAge()));
					map.put("wifeIncome",
							changeIngeger(insureBean.getWifeIncome()));
					map.put("wifeOutlay",
							changeIngeger(insureBean.getWifeOutlay()));
					map.put("parentFatherAge",
							changeIngeger(insureBean.getParentFatherAge()));
					map.put("parentMotherAge",
							changeIngeger(insureBean.getParentMotherAge()));
					map.put("parentMaintenance",
							changeIngeger(insureBean.getParentMaintenance()));
					map.put("childAge1",
							changeIngeger(insureBean.getChildAge1()));
					map.put("childAge2",
							changeIngeger(insureBean.getChildAge2()));
					map.put("childAge3",
							changeIngeger(insureBean.getChildAge3()));
					map.put("childNum", changeIngeger(insureBean.getChildNum()));

					map.put("manIsHasSs", insureBean.getManIsHasSs() ? "1"
							: "0");
					map.put("wifeIsHasSs", insureBean.getWifeIsHasSs() ? "1"
							: "0");
					map.put("hasWife", insureBean.isHasWife() ? "1" : "0");
					map.put("hasFather", insureBean.isHasFather() ? "1" : "0");
					map.put("hasMother", insureBean.isHasMother() ? "1" : "0");
					nInsureanceResult = WebServiceClient
							.getInsuranceResult(map);
					handler.sendEmptyMessage(0x01);
				} catch (AppException e) {
					handler.sendEmptyMessage(0x02);
					e.printStackTrace();
				}
			}
		}).start();

	}

	private String changeIngeger(Integer data) {
		if (data == null)
			return "";
		else
			return data + "";

	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0x01:
				ProgressDialogUtil.closeProgress();
				ProgressDialogUtil.closeProgress();
				if (nInsureanceResult != null) {
					if (nInsureanceResult.getState() == 1)// 请求成功
					{
						nIsuranceBean = nInsureanceResult.getData();
						lists.clear();
						lists1.clear();
						lists2.clear();
						lists.add(new InvestResultBean((int) nIsuranceBean
								.getSelf1() + "万", nIsuranceBean
								.getSelfRatio1(), "#ffffff"));
						lists.add(new InvestResultBean((int) nIsuranceBean
								.getSelf2() + "万", nIsuranceBean
								.getSelfRatio2(), "#b7efff"));
						lists.add(new InvestResultBean((int) nIsuranceBean
								.getSelf3() + "万", nIsuranceBean
								.getSelfRatio3(), "#66dbff"));
						lists.add(new InvestResultBean((int) nIsuranceBean
								.getSelf4() + "万", nIsuranceBean
								.getSelfRatio4(), "#14c9fe"));

						lists1.add(new InvestResultBean((int) nIsuranceBean
								.getSpouse1() + "万", nIsuranceBean
								.getSpouseRatio1(), "#ffffff"));
						lists1.add(new InvestResultBean((int) nIsuranceBean
								.getSpouse2() + "万", nIsuranceBean
								.getSpouseRatio2(), "#b7efff"));
						lists1.add(new InvestResultBean((int) nIsuranceBean
								.getSpouse3() + "万", nIsuranceBean
								.getSpouseRatio3(), "#66dbff"));
						lists1.add(new InvestResultBean((int) nIsuranceBean
								.getSpouse4() + "万", nIsuranceBean
								.getSpouseRatio4(), "#14c9fe"));

						lists2.add(new InvestResultBean((int) nIsuranceBean
								.getChild2() + "万", nIsuranceBean
								.getChildRatio2(), "#b7efff"));
						lists2.add(new InvestResultBean((int) nIsuranceBean
								.getChild3() + "万", nIsuranceBean
								.getChildRatio3(), "#66dbff"));
						lists2.add(new InvestResultBean((int) nIsuranceBean
								.getChild4() + "万", nIsuranceBean
								.getChildRatio4(), "#14c9fe"));
						inVesteResult.setLists(lists);
						investmentBarPic.setData(inVesteResult);
					} else {
						if (nInsureanceResult.getState() == ConstantValues.LOGIN_ERROR) {
							MDialog.showPsdErrorDialog(
									InsurePlannResultActivity.this,
									nInsureanceResult.getMsg());
						}
					}
				} else {

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
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.left_img:
			finish();
			break;
		case R.id.owner_txt:
			// 本人
			hideBar();
			ll_layout1.setVisibility(View.VISIBLE);
			investmentBarPic.setVisibility(View.VISIBLE);
			if (currentIndex == 0) {
				return;
			}
			currentIndex = 0;
			owner_txt.setBackgroundResource(R.drawable.bg_blue_right_angle);
			owner_txt.setTextColor(Color.parseColor("#ffffff"));
			spouse_txt.setBackgroundColor(Color.parseColor("#ffffff"));
			spouse_txt.setTextColor(Color.parseColor("#666666"));
			child_txt.setBackgroundColor(Color.parseColor("#ffffff"));
			child_txt.setTextColor(Color.parseColor("#666666"));
			inVesteResult.setLists(lists);
			investmentBarPic.setData(inVesteResult);
			break;
		case R.id.spouse_txt:
			// 配偶
			hideBar();
			ll_layout1.setVisibility(View.VISIBLE);
			investmentBarPic.setVisibility(View.VISIBLE);
			if (currentIndex == 1) {
				return;
			}
			currentIndex = 1;
			owner_txt.setBackgroundColor(Color.parseColor("#ffffff"));
			owner_txt.setTextColor(Color.parseColor("#666666"));
			spouse_txt.setBackgroundResource(R.drawable.bg_blue_right_angle);
			spouse_txt.setTextColor(Color.parseColor("#ffffff"));
			child_txt.setBackgroundColor(Color.parseColor("#ffffff"));
			child_txt.setTextColor(Color.parseColor("#666666"));
			inVesteResult.setLists(lists1);
			investmentBarPic.setData(inVesteResult);
			break;
		case R.id.child_txt:
			// 子女
			hideBar();
			ll_layout2.setVisibility(View.VISIBLE);
			investmentBarPic2.setVisibility(View.VISIBLE);
			if (currentIndex == 2) {
				return;
			}
			currentIndex = 2;
			owner_txt.setBackgroundColor(Color.parseColor("#ffffff"));
			owner_txt.setTextColor(Color.parseColor("#666666"));
			spouse_txt.setBackgroundColor(Color.parseColor("#ffffff"));
			spouse_txt.setTextColor(Color.parseColor("#666666"));
			child_txt.setBackgroundResource(R.drawable.bg_blue_right_angle);
			child_txt.setTextColor(Color.parseColor("#ffffff"));
			inVesteResult.setLists(lists2);
			investmentBarPic2.getViewTreeObserver().addOnGlobalLayoutListener(
					new OnGlobalLayoutListener() {

						@Override
						public void onGlobalLayout() {
							investmentBarPic2.setData(inVesteResult);
						}
					});

			break;
		case R.id.repeat_test_txt:
			// 重新测试
			intent.setClass(this, FinancialToolActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.ask_txt:
		case R.id.investmentBarPic:
		case R.id.investmentBarPic2:
			// 看看产品
			MyApplication.home_index = 1;// 指定跳到产品中心
			MyApplication.index = 5;// 指定跳刀保险产品选项
			intent.setClass(this, HomeActivity.class);
			startActivity(intent);
			finish();
			break;
		default:
			break;
		}
	}

	private void hideBar() {
		ll_layout1.setVisibility(View.GONE);
		ll_layout2.setVisibility(View.GONE);
		investmentBarPic.setVisibility(View.GONE);
		investmentBarPic2.setVisibility(View.GONE);

	}

}
