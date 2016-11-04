package com.miduo.financialmanageclient.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.bean.InVesteResult;
import com.miduo.financialmanageclient.bean.InsureRecordDetail;
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

public class InsureItemActivity extends GesterSetBaseActivity implements OnClickListener {
	private ImageView leftImg;
	private TextView titleTxt;
	private TextView rightTxt;
	private TextView owner_txt, spouse_txt, child_txt;
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
	private int tid;
	private SelDetailAsyncTask selDetailAsyncTask;
	private TextView name_txt1, name_txt2, name_txt3, name_txt4, time_txt;
	private TextView row1_txt, row2_txt, row3_txt, row4_txt, child_info_txt,
			parent_info_txt, family_info_txt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insure_item);
		tid = getIntent().getIntExtra("tid", -1);
		initUi();
		initEvent();
		initData();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("投保测试记录详情"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("投保测试记录详情"); //
		MobclickAgent.onPause(this);
	}

	private void initUi() {
		// TODO Auto-generated method stub
		time_txt = (TextView) findViewById(R.id.time_txt);
		name_txt1 = (TextView) findViewById(R.id.name_txt1);
		name_txt2 = (TextView) findViewById(R.id.name_txt2);
		name_txt3 = (TextView) findViewById(R.id.name_txt3);
		name_txt4 = (TextView) findViewById(R.id.name_txt4);
		leftImg = (ImageView) findViewById(R.id.left_img);
		titleTxt = (TextView) findViewById(R.id.title_txt);
		rightTxt = (TextView) findViewById(R.id.right_txt);
		titleTxt.setText("投保测试记录");
		rightTxt.setVisibility(View.GONE);
		owner_txt = (TextView) findViewById(R.id.owner_txt);
		spouse_txt = (TextView) findViewById(R.id.spouse_txt);
		child_txt = (TextView) findViewById(R.id.child_txt);

		investmentBarPic = (InvestmentBarPic) this
				.findViewById(R.id.investmentBarPic);
		investmentBarPic2 = (InvestmentBarPic) this
				.findViewById(R.id.investmentBarPic2);
		ll_layout1 = (LinearLayout) this.findViewById(R.id.ll_layout1);
		ll_layout2 = (LinearLayout) this.findViewById(R.id.ll_layout2);

		row1_txt = (TextView) findViewById(R.id.row1_txt);
		row2_txt = (TextView) findViewById(R.id.row2_txt);
		row3_txt = (TextView) findViewById(R.id.row3_txt);
		row4_txt = (TextView) findViewById(R.id.row4_txt);
		child_info_txt = (TextView) findViewById(R.id.child_info_txt);
		parent_info_txt = (TextView) findViewById(R.id.parent_info_txt);
		family_info_txt = (TextView) findViewById(R.id.family_info_txt);
	}

	private void initEvent() {
		leftImg.setOnClickListener(this);
		owner_txt.setOnClickListener(this);
		spouse_txt.setOnClickListener(this);
		child_txt.setOnClickListener(this);
	}

	private void initData() {
		lists = new ArrayList<InvestResultBean>();
		lists1 = new ArrayList<InvestResultBean>();
		lists2 = new ArrayList<InvestResultBean>();
		inVesteResult = new InVesteResult();
		inVesteResult.setTotal(100);
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
			// 刷新数据
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
			// 刷新数据
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
			// 刷新数据
			inVesteResult.setLists(lists2);
			investmentBarPic2.getViewTreeObserver().addOnGlobalLayoutListener(
					new OnGlobalLayoutListener() {

						@Override
						public void onGlobalLayout() {
							investmentBarPic2.setData(inVesteResult);
						}
					});

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

	private void showData(InsureRecordDetail item) {
		name_txt1.setText(StringUtil.showStringContent(item.getName1()));
		name_txt2.setText(StringUtil.showStringContent(item.getName2()));
		name_txt3.setText(StringUtil.showStringContent(item.getName3()));
		name_txt4.setText(StringUtil.showStringContent(item.getName4()));
		time_txt.setText(StringUtil.showStringContent(item.getDateTime()));
		row1_txt.setText(StringUtil.showStringContent(item.getRow1()));
		row2_txt.setText(StringUtil.showStringContent(item.getRow2()));
		row3_txt.setText(StringUtil.showStringContent(item.getRow3()));
		row4_txt.setText(StringUtil.showStringContent(item.getRow4()));
		child_info_txt.setText(StringUtil.showStringContent(item.getRow5()));
		parent_info_txt.setText(StringUtil.showStringContent(item.getRow6()));
		family_info_txt.setText(StringUtil.showStringContent(item.getRow7()));
		lists.clear();
		lists1.clear();
		lists2.clear();
		lists.clear();
		lists1.clear();
		lists2.clear();
		lists.add(new InvestResultBean((int)item.getSelf1() + "万", item
				.getSelfRatio1(), "#ffffff"));
		lists.add(new InvestResultBean((int)item.getSelf2() + "万", item
				.getSelfRatio2(), "#b7efff"));
		lists.add(new InvestResultBean((int)item.getSelf3() + "万", item
				.getSelfRatio3(), "#66dbff"));
		lists.add(new InvestResultBean((int)item.getSelf4() + "万", item
				.getSelfRatio4(), "#14c9fe"));

		lists1.add(new InvestResultBean((int)item.getSpouse1() + "万", item
				.getSpouseRatio1(), "#ffffff"));
		lists1.add(new InvestResultBean((int)item.getSpouse2() + "万", item
				.getSpouseRatio2(), "#b7efff"));
		lists1.add(new InvestResultBean((int)item.getSpouse3() + "万", item
				.getSpouseRatio3(), "#66dbff"));
		lists1.add(new InvestResultBean((int)item.getSpouse4() + "万", item
				.getSpouseRatio4(), "#14c9fe"));

		lists2.add(new InvestResultBean((int)item.getChild2() + "万", item
				.getChildRatio2(), "#b7efff"));
		lists2.add(new InvestResultBean((int)item.getChild3() + "万", item
				.getChildRatio3(), "#66dbff"));
		lists2.add(new InvestResultBean((int)item.getChild4() + "万", item
				.getChildRatio4(), "#14c9fe"));
		inVesteResult.setLists(lists);
		investmentBarPic.setData(inVesteResult);
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
				return WebServiceClient.getRecordDetail(map, 1);
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
					ex.makeToast(InsureItemActivity.this);
					return;
				}
				MToast.showToast(InsureItemActivity.this, "获取信息失败！");
				return;
			} else {
				try {
					JSONObject jo = new JSONObject(result);
					int state = jo.getInt("state");
					if (state == ConstantValues.LOGIN_ERROR) {
						String msgStr = jo.getString("msg");
						MDialog.showPsdErrorDialog(InsureItemActivity.this, msgStr);
						return;
					} else if (state == 1) {
						String data = jo.getString("data");
						InsureRecordDetail item = JsonUtils.toBean(data,
								new TypeToken<InsureRecordDetail>() {
								}.getType());
						showData(item);
					} else {
						String msg = jo.getString("msg");
						MToast.showToast(InsureItemActivity.this,
								StringUtil.isEmpty(msg) ? "获取信息失败！" : msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
					MToast.showToast(InsureItemActivity.this, "数据异常");
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
