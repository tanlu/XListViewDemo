package com.miduo.financialmanageclient.ui;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.BankCardInfo;
import com.miduo.financialmanageclient.common.GetBankCardList;
import com.miduo.financialmanageclient.listener.GetCardLstListener;
import com.miduo.financialmanageclient.ui.adapter.BankInfoAdapter;
import com.miduo.financialmanageclient.ui.adapter.BankInfoAdapter.PayClickListener;
import com.miduo.financialmanageclient.ui.adapter.BankInfoAdapter.ViewHolder;
import com.miduo.financialmanageclient.util.MToast;
import com.umeng.analytics.MobclickAgent;

public class BankCardLstActivity extends GesterSetBaseActivity implements OnClickListener {
	private ImageView leftImg;
	private TextView titleTxt;
	private TextView rightTxt;
	private ListView listview;
	private BankInfoAdapter bankCardLstAdapter;
	private List<BankCardInfo> cardLst = new ArrayList<BankCardInfo>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bank_card_lst);
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
		leftImg = (ImageView) findViewById(R.id.left_img);
		titleTxt = (TextView) findViewById(R.id.title_txt);
		rightTxt = (TextView) findViewById(R.id.right_txt);
		titleTxt.setText("确认原支付卡");
		rightTxt.setVisibility(View.GONE);
		listview = (ListView) findViewById(R.id.listview);
		bankCardLstAdapter = new BankInfoAdapter(BankCardLstActivity.this,
				cardLst, 1);
		listview.setAdapter(bankCardLstAdapter);
	}

	private Bundle bundle;

	private void initEvent() {
		// TODO Auto-generated method stub
		leftImg.setOnClickListener(this);

		bankCardLstAdapter.setPayClickListener(new PayClickListener() {

			@Override
			public void onClick(int position) {
				if (position < cardLst.size()) {
					Intent intent = new Intent();
					intent.putExtra("bank_card", cardLst.get(position));
					setResult(RESULT_OK, intent);
					finish();
				} else {
					Intent intent = new Intent(BankCardLstActivity.this,
							AddReceiveBankCardActivity.class);
					startActivityForResult(intent, 1001);
				}
			}
		});
		listview.setOnItemClickListener(new OnItemClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ViewHolder holder = ((ViewHolder) view.getTag());
				BankCardInfo bankInfoBean = cardLst.get(position);
				if (bankInfoBean.getChannelType()!=null && bankInfoBean.getChannelType().intValue()==2)// 满足支付条件
				{

					if (bankInfoBean.isOpen()) {
						holder.card_layout
								.setBackgroundResource(R.drawable.card_item_white_bg);
						holder.ll_satisfy.setVisibility(View.GONE);
						holder.ll_notsatisfy.setVisibility(View.GONE);
						holder.iv_tip.setRotation(0);
						bankInfoBean.setOpen(false);

					} else {
						holder.card_layout
								.setBackgroundResource(R.drawable.card_item_blue_bg);
						holder.ll_satisfy.setVisibility(View.VISIBLE);
						holder.ll_notsatisfy.setVisibility(View.GONE);
						holder.iv_tip.setRotation(180);
						bankInfoBean.setOpen(true);
						for (int i = 0; i < cardLst.size(); i++) {
							if (i != position) {
								cardLst.get(i).setOpen(false);
								bankCardLstAdapter.notifyDataSetChanged();
							}
						}
					}
					listview.setSelection(position);

					bundle = new Bundle();
					bundle.putSerializable("bankinfo", bankInfoBean);

				}
			}
		});
	}

	// 获得银行卡list
	private void initData() {
		// TODO Auto-generated method stub
		GetCardLstListener getCardLstListener = new GetCardLstListener() {

			@Override
			public void refresh(List<BankCardInfo> tempCardLst) {
				// TODO Auto-generated method stub
				cardLst.clear();
				if (tempCardLst != null && tempCardLst.size() > 0) {
					cardLst.addAll(tempCardLst);
				}
				bankCardLstAdapter.notifyDataSetChanged();
			}
		};
		GetBankCardList getBankCardList = new GetBankCardList(
				BankCardLstActivity.this, getCardLstListener);
		getBankCardList.getData();
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			BankCardInfo cardInfo = (BankCardInfo) data
					.getSerializableExtra("bank_card");
			Intent intent = new Intent();

			intent.putExtra("bank_card", cardInfo);

			setResult(RESULT_OK, intent);

			finish();
		}
	}

}
