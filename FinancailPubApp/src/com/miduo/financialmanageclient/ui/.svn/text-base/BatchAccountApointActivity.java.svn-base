package com.miduo.financialmanageclient.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.BankCardInfo;
import com.miduo.financialmanageclient.bean.RedeemItemDetail;
import com.miduo.financialmanageclient.bean.TestRecord;
import com.miduo.financialmanageclient.ui.adapter.RedeemOrderAdapter;
import com.miduo.financialmanageclient.ui.adapter.RedeemOrderAdapter.AppointClickListener;
import com.umeng.analytics.MobclickAgent;

public class BatchAccountApointActivity extends GesterSetBaseActivity implements OnClickListener {
	private ImageView leftImg, selImg;
	private TextView titleTxt;
	private TextView rightTxt;
	private TextView commitTxt;
	private ListView orderListView;
	private RedeemOrderAdapter redeemOrderAdapter;
	private List<RedeemItemDetail> orderLst = new ArrayList<RedeemItemDetail>();
	private boolean selAll = false;
	private LinearLayout all_layout;
	// listview如果点击选择某行的时候，记录点击了那行的数据
	private Integer clickOrderNum;
	private boolean apointFlag = false;
	private int selCount = 0;
	private int cardNum = 0;
	private List<RedeemItemDetail> returnLst = new ArrayList<RedeemItemDetail>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_batch_account_apoint);

		initUi();
		initEvent();
		initData();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("确认原支付卡"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("确认原支付卡"); //
		MobclickAgent.onPause(this);
	}

	private void initUi() {
		// TODO Auto-generated method stub
		selImg = (ImageView) findViewById(R.id.all_img);
		all_layout = (LinearLayout) findViewById(R.id.all_layout);
		leftImg = (ImageView) findViewById(R.id.left_img);
		titleTxt = (TextView) findViewById(R.id.title_txt);
		rightTxt = (TextView) findViewById(R.id.right_txt);
		titleTxt.setText("确认原支付卡");
		rightTxt.setVisibility(View.GONE);
		commitTxt = (TextView) findViewById(R.id.btn_txt);
		orderListView = (ListView) findViewById(R.id.order_listview);
		redeemOrderAdapter = new RedeemOrderAdapter(BatchAccountApointActivity.this, orderLst, true);
		orderListView.setAdapter(redeemOrderAdapter);
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		commitTxt.setOnClickListener(this);
		leftImg.setOnClickListener(this);
		rightTxt.setOnClickListener(this);
		all_layout.setOnClickListener(this);

		orderListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				boolean flag = !(orderLst.get(arg2).isSel());
				orderLst.get(arg2).setSel(flag);
				redeemOrderAdapter.notifyDataSetChanged();
				if (flag) {
					selCount++;
				} else {
					selCount--;
				}
				checkSelCount();
			}
		});

		redeemOrderAdapter.setAppointClickListener(new AppointClickListener() {

			@Override
			public void onClick(int position) {
				// TODO Auto-generated method stub
				clickOrderNum = position;
				Intent intent = new Intent();
				intent.setClass(BatchAccountApointActivity.this, BankCardLstActivity.class);
				startActivityForResult(intent, 1002);
			}
		});
	}

	private void initData() {
		// TODO Auto-generated method stub
		List<RedeemItemDetail> tempLst = (List<RedeemItemDetail>) getIntent().getSerializableExtra("redeem_lst");
		cardNum = getIntent().getIntExtra("card_num", 0);
		if (tempLst == null || tempLst.size() == 0) {
			return;
		}
		for (int i = 0; i < tempLst.size(); i++) {
			tempLst.get(i).setSel(false);
		}
		orderLst.clear();
		orderLst.addAll(tempLst);
		redeemOrderAdapter.notifyDataSetChanged();

		commitTxt.setBackgroundResource(R.drawable.button_bg_gray);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.btn_txt:
			if (selCount == 0) {
				return;
			}
			clickOrderNum = null;
			if (cardNum == 0) {
				intent.setClass(BatchAccountApointActivity.this, AddReceiveBankCardActivity.class);
				startActivityForResult(intent, 1002);
			} else {
				intent.setClass(BatchAccountApointActivity.this, BankCardLstActivity.class);
				startActivityForResult(intent, 1002);
			}
			
			break;
		case R.id.left_img:
			if (apointFlag) {
				setResult(RESULT_OK);
			}
			finish();
			break;
		case R.id.right_txt:
			break;
		case R.id.all_layout:
			selAll = !selAll;
			for (int i = 0; i < orderLst.size(); i++) {
				orderLst.get(i).setSel(selAll);
			}
			redeemOrderAdapter.notifyDataSetChanged();
			if (selAll) {
				selCount = orderLst.size();
				selImg.setImageResource(R.drawable.select);
				commitTxt.setEnabled(true);
				commitTxt.setBackgroundResource(R.drawable.button_bg_blue);
			} else {
				selCount = 0;
				commitTxt.setEnabled(false);
				selImg.setImageResource(R.drawable.un_select);
				commitTxt.setBackgroundResource(R.drawable.button_bg_gray);
			}
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			apointFlag = true;
			BankCardInfo cardInfo = (BankCardInfo) data.getSerializableExtra("bank_card");
			if (clickOrderNum == null) {
				setResult(RESULT_OK);
				for (int i = 0; i < orderLst.size(); i++) {
					orderLst.get(i).setBankId(cardInfo.getId());
					orderLst.get(i).setShortBankNo(cardInfo.getCardShortNo());	
					orderLst.get(i).setBankName(cardInfo.getBankName());
				}
				returnLst.addAll(orderLst);
				orderLst.clear();
				redeemOrderAdapter.notifyDataSetChanged();
				Intent intent = new Intent();
				intent.putExtra("back_lst", (Serializable)returnLst);
				setResult(RESULT_OK, intent);
				finish();
			} else {
				orderLst.get(clickOrderNum).setBankId(cardInfo.getId());
				orderLst.get(clickOrderNum).setShortBankNo(cardInfo.getCardShortNo());	
				orderLst.get(clickOrderNum).setBankName(cardInfo.getBankName());
				returnLst.add(orderLst.get(clickOrderNum));
				orderLst.remove(clickOrderNum.intValue());
				redeemOrderAdapter.notifyDataSetChanged();
				if (orderLst.size() == 0) {					
					Intent intent = new Intent();
					intent.putExtra("back_lst", (Serializable)returnLst);
					setResult(RESULT_OK, intent);
					finish();
				} else {
					checkSelCount();
				}
			}
		}
	}

	private void checkSelCount() {
		if (selCount == 0) {
			commitTxt.setEnabled(false);
			selAll = false;
			selImg.setImageResource(R.drawable.un_select);
			commitTxt.setBackgroundResource(R.drawable.button_bg_gray);
		} else if (selCount == orderLst.size()) {
			selAll = true;
			selImg.setImageResource(R.drawable.select);
			commitTxt.setEnabled(true);
			commitTxt.setBackgroundResource(R.drawable.button_bg_blue);
		} else {
			commitTxt.setEnabled(true);
			selAll = false;
			selImg.setImageResource(R.drawable.un_select);
			commitTxt.setBackgroundResource(R.drawable.button_bg_blue);
		}
	}
}
