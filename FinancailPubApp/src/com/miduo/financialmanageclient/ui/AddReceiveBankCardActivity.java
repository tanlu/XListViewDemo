package com.miduo.financialmanageclient.ui;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.BankCardInfo;
import com.miduo.financialmanageclient.bean.BankInfo;
import com.miduo.financialmanageclient.common.GetBankInfo;
import com.miduo.financialmanageclient.common.SaveCardInfo;
import com.miduo.financialmanageclient.listener.GetBankLstListener;
import com.miduo.financialmanageclient.listener.SaveCardListener;
import com.miduo.financialmanageclient.widget.AddCardInfoView;
import com.umeng.analytics.MobclickAgent;

public class AddReceiveBankCardActivity extends GesterSetBaseActivity implements OnClickListener {
	private ImageView leftImg;
	private TextView titleTxt;
	private TextView rightTxt;
	private TextView btn_txt;
	private AddCardInfoView addCardInfoView;
	private BankCardInfo cardInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_receive_bank_card);
		initUi();
		initEvent();
		initData();
	}

	private void initData() {
		// TODO Auto-generated method stub
		GetBankLstListener getBankLstListener = new GetBankLstListener() {

			@Override
			public void refresh(List<BankInfo> bankLst) {
				// TODO Auto-generated method stub
				addCardInfoView.initBankInfo(bankLst);
			}
		};
		GetBankInfo getBankList = new GetBankInfo(AddReceiveBankCardActivity.this, getBankLstListener);
		getBankList.getData();
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
		btn_txt = (TextView) findViewById(R.id.btn_txt);
		addCardInfoView = (AddCardInfoView) findViewById(R.id.info_layout);
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		leftImg.setOnClickListener(this);
		btn_txt.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.left_img:
			finish();
			break;
		case R.id.btn_txt:
			if (addCardInfoView.checkValue()) {
				// 保存银行卡信息
				cardInfo = addCardInfoView.getValue();

				SaveCardListener saveCardListener = new SaveCardListener() {

					@Override
					public void saveResult(Integer data) {
						// TODO Auto-generated method stub
						if (data != null) {
							cardInfo.setId(data.intValue());
							Intent intent = new Intent();
							intent.putExtra("bank_card", cardInfo);
							setResult(RESULT_OK, intent);
							finish();
							
						}
					}
				};
				SaveCardInfo saveCardInfo = new SaveCardInfo(AddReceiveBankCardActivity.this, saveCardListener,
						cardInfo);
				saveCardInfo.saveData();
			}
			break;
		default:
			break;
		}
	}
}
