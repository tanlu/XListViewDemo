package com.miduo.financialmanageclient.ui;

import java.io.Serializable;
import java.math.BigDecimal;
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
import com.miduo.financialmanageclient.bean.RedeemInfo;
import com.miduo.financialmanageclient.bean.RedeemItemDetail;
import com.miduo.financialmanageclient.ui.adapter.RedeemOrderAdapter;
import com.miduo.financialmanageclient.util.FloatUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.umeng.analytics.MobclickAgent;

public class BatchRedeemOrderSelActivity extends GesterSetBaseActivity implements OnClickListener {
	private ImageView leftImg, selImg;
	private TextView titleTxt;
	private TextView rightTxt;
	private TextView commitTxt;
	private ListView orderListView;
	private TextView order_count_txt, sum_amout_txt, china_amount_txt;
	private RedeemOrderAdapter redeemOrderAdapter;
	private List<RedeemItemDetail> orderLst = new ArrayList<RedeemItemDetail>();
	private boolean selAll = false;
	private LinearLayout all_layout;
	// 临时变量 记录按钮点击第几次，第一次去绑卡 第二次去
	private int clickCount = 0;
	private Integer assetId;
	private RedeemInfo redeemInfo;
	private int selCount = 0;
	private double sumAmount = 0.0d;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bach_redeem_order_sel);
		initUi();
		initEvent();
		initData();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("批量赎回"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("批量赎回"); //
		MobclickAgent.onPause(this);
	}

	private void initUi() {
		// TODO Auto-generated method stub
		selImg = (ImageView) findViewById(R.id.all_img);
		all_layout = (LinearLayout) findViewById(R.id.all_layout);
		leftImg = (ImageView) findViewById(R.id.left_img);
		titleTxt = (TextView) findViewById(R.id.title_txt);
		rightTxt = (TextView) findViewById(R.id.right_txt);
		titleTxt.setText("批量赎回");
		rightTxt.setVisibility(View.GONE);
		commitTxt = (TextView) findViewById(R.id.btn_txt);
		order_count_txt = (TextView) findViewById(R.id.order_count_txt);
		sum_amout_txt = (TextView) findViewById(R.id.sum_amout_txt);
		china_amount_txt = (TextView) findViewById(R.id.china_amount_txt);
		orderListView = (ListView) findViewById(R.id.order_listview);
		redeemOrderAdapter = new RedeemOrderAdapter(BatchRedeemOrderSelActivity.this, orderLst, false);
		orderListView.setAdapter(redeemOrderAdapter);
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		commitTxt.setOnClickListener(this);
		leftImg.setOnClickListener(this);
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
					if (orderLst.get(arg2).getAmount() != null) {
						sumAmount = sumAmount + orderLst.get(arg2).getAmount().doubleValue();
					}
				} else {
					selCount--;
					if (orderLst.get(arg2).getAmount() != null) {
						sumAmount = sumAmount - orderLst.get(arg2).getAmount().doubleValue();
					}
				}
				sum_amout_txt.setText(FloatUtil.toTwoDianStringSeparator(sumAmount));
				china_amount_txt.setText(StringUtil.number2CNMontrayUnit(new BigDecimal(sumAmount)));
				checkSelCount();
			}
		});
	}

	private void initData() {
		// TODO Auto-generated method stub
		sumAmount = 0;
		selCount = 0;
		Bundle bundle = getIntent().getExtras();
		redeemInfo = (RedeemInfo) bundle.getSerializable("redeemInfo");
		assetId = bundle.getInt("asset_id", -1);

		if (redeemInfo == null || redeemInfo.getList().size() == 0) {
			return;
		}
		commitTxt.setEnabled(false);
		commitTxt.setBackgroundResource(R.drawable.button_bg_gray);
		for (int i = 0; i < redeemInfo.getList().size(); i++) {
			if (redeemInfo.getList().get(i).getAssetId() != null && redeemInfo.getList().get(i).getAssetId().intValue() == assetId) {
				selCount++;
				redeemInfo.getList().get(i).setSel(true);
				order_count_txt.setText(String.valueOf(selCount));
				Double amount = redeemInfo.getList().get(i).getAmount();
				if (amount != null) {
					sumAmount = sumAmount + amount.doubleValue();
					sum_amout_txt.setText(FloatUtil.toTwoDianStringSeparator(sumAmount));
					china_amount_txt.setText(StringUtil.number2CNMontrayUnit(new BigDecimal(sumAmount)));
				}
				commitTxt.setEnabled(true);
				commitTxt.setBackgroundResource(R.drawable.button_bg_blue);
				break;
			}
		}
		orderLst.clear();
		orderLst.addAll(redeemInfo.getList());
		redeemOrderAdapter.notifyDataSetChanged();

		
		selAll = false;
		selImg.setImageResource(R.drawable.un_select);		
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
			List<RedeemItemDetail> tempUnSelLst = new ArrayList<RedeemItemDetail>();
			List<RedeemItemDetail> tempSelLst = new ArrayList<RedeemItemDetail>();
			for (RedeemItemDetail item : orderLst) {
				if (item.isSel()) {
					if (item.isHasbank()) {
						tempSelLst.add(item);
					} else {
						tempUnSelLst.add(item);
					}
				}
			}
			if (tempUnSelLst.size() == 0) {
				intent.setClass(BatchRedeemOrderSelActivity.this, BatchRedeemConfirmActivity.class);
				intent.putExtra("redeem_lst", (Serializable) tempSelLst);
				intent.putExtra("sum_amout", sumAmount);
				startActivity(intent);
			} else {
				intent.setClass(BatchRedeemOrderSelActivity.this, BatchAccountApointActivity.class);
				intent.putExtra("redeem_lst", (Serializable) tempUnSelLst);
				intent.putExtra("card_num", redeemInfo.getBankCount());
				startActivityForResult(intent, 1002);
			}
			break;
		case R.id.left_img:
			finish();
			break;
		case R.id.all_layout:
			selAll = !selAll;
			double temAmount = 0.0d;
			for (int i = 0; i < orderLst.size(); i++) {
				orderLst.get(i).setSel(selAll);
				if (selAll) {
					if (orderLst.get(i).getAmount() != null) {
						temAmount = temAmount + orderLst.get(i).getAmount();
					}
				}
			}
			redeemOrderAdapter.notifyDataSetChanged();
			if (selAll) {
				sumAmount = temAmount;
				selCount = orderLst.size();
				selImg.setImageResource(R.drawable.select);
				commitTxt.setEnabled(true);
				commitTxt.setBackgroundResource(R.drawable.button_bg_blue);
			} else {
				selCount = 0;
				sumAmount = 0.0d;
				commitTxt.setEnabled(false);
				selImg.setImageResource(R.drawable.un_select);
				commitTxt.setBackgroundResource(R.drawable.button_bg_gray);
			}
			order_count_txt.setText(String.valueOf(selCount));
			sum_amout_txt.setText(FloatUtil.toTwoDianStringSeparator(sumAmount));
			china_amount_txt.setText(StringUtil.number2CNMontrayUnit(new BigDecimal(sumAmount)));
			break;
		default:
			break;
		}
	}

	private void checkSelCount() {
		order_count_txt.setText(String.valueOf(selCount));
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

	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			List<RedeemItemDetail> backLst = (List<RedeemItemDetail>) data.getSerializableExtra("back_lst");
			if (backLst != null && backLst.size() > 0) {
				for (int i = 0; i < orderLst.size(); i++) {
					if (orderLst.get(i).isSel() && !orderLst.get(i).isHasbank()) {
						for (int j = 0; j < backLst.size(); j++) {
							if (orderLst.get(i).getAssetId() != null
									&& backLst.get(j).getAssetId() != null
									&& orderLst.get(i).getAssetId().intValue() == backLst.get(j).getAssetId()
											.intValue()) {
								orderLst.get(i).setBankName(backLst.get(j).getBankName());
								orderLst.get(i).setBankId(backLst.get(j).getBankId());
								orderLst.get(i).setShortBankNo(backLst.get(j).getShortBankNo());
								orderLst.get(i).setHasbank(true);
								break;
							}
						}
					}
				}
				redeemOrderAdapter.notifyDataSetChanged();
			}
		}
	}
}
