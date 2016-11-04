package com.miduo.financialmanageclient.ui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.BankCardInfo;
import com.miduo.financialmanageclient.bean.BankInfo;
import com.miduo.financialmanageclient.bean.NOrderBean;
import com.miduo.financialmanageclient.common.GetBankInfo;
import com.miduo.financialmanageclient.common.SaveCardInfo;
import com.miduo.financialmanageclient.listener.GetBankLstListener;
import com.miduo.financialmanageclient.listener.SaveCardListener;
import com.miduo.financialmanageclient.ui.adapter.BankListSelectAdapter;
import com.miduo.financialmanageclient.util.FloatUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.widget.AddCardInfoView;
import com.umeng.analytics.MobclickAgent;

/**
 * 添加银行卡页面
 * 
 * @author huozhenpeng
 * 
 */
public class AddBankCardActivity extends GesterSetBaseActivity implements OnClickListener {

	private TextView title_txt;
	private TextView right_txt;
	private ImageView left_img;
	private List<BankInfo> lists;
	private BankListSelectAdapter adapter;
	private TextView tv_nextstep;
	private AddCardInfoView addCardInfoView;
	private TextView tv_money;// 投资金额
	private NOrderBean nOrderBean;
	private TextView tv_proname;
	private TextView tv_moneytext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_addbankcard);
		initView();
		initEvent();
		initData();
	}

	private void initData() {

		title_txt.setText("添加银行卡");
		nOrderBean=(NOrderBean) getIntent().getSerializableExtra("ORDER");
		if(nOrderBean==null)
			return;
		tv_proname.setText(nOrderBean.getProductTitle());
		tv_money.setText(FloatUtil.toTwoDianStringSeparator(nOrderBean.getAmount()));
		tv_moneytext.setText(StringUtil.number2CNMontrayUnit(BigDecimal
				.valueOf(nOrderBean.getAmount())));
		GetBankLstListener getBankLstListener = new GetBankLstListener() {

			@Override
			public void refresh(List<BankInfo> bankLst) {
				addCardInfoView.initBankInfo(bankLst);
			}
		};
		GetBankInfo getBankList = new GetBankInfo(AddBankCardActivity.this, getBankLstListener);
		getBankList.getData();
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("添加银行卡"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("添加银行卡"); //
		MobclickAgent.onPause(this);
	}

	private void initEvent() {
		left_img.setOnClickListener(this);
		tv_nextstep.setOnClickListener(this);
	}

	private void initView() {
		title_txt = (TextView) this.findViewById(R.id.title_txt);
		right_txt = (TextView) this.findViewById(R.id.right_txt);
		right_txt.setVisibility(View.INVISIBLE);
		addCardInfoView = (AddCardInfoView) this.findViewById(R.id.addCardInfoView);
		left_img = (ImageView) this.findViewById(R.id.left_img);
		lists = new ArrayList<BankInfo>();
		tv_nextstep = (TextView) this.findViewById(R.id.tv_nextstep);
		tv_money = (TextView) this.findViewById(R.id.tv_money);
		tv_proname=(TextView) this.findViewById(R.id.tv_proname);
		tv_moneytext=(TextView) this.findViewById(R.id.tv_moneytext);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_img:
			this.finish();
			break;
		case R.id.tv_nextstep:
			if (addCardInfoView.checkValue()) {
				// 保存银行卡信息
				final BankCardInfo cardInfo = addCardInfoView.getValue();

				SaveCardListener saveCardListener = new SaveCardListener() {

					@Override
					public void saveResult(Integer data) {
						if (data != null) {
							Intent intent = new Intent(AddBankCardActivity.this, ConfirmPaymentActivity.class);
						    cardInfo.setId(data.intValue());
							intent.putExtra("BANKCARD", cardInfo);
							intent.putExtra("ORDER", nOrderBean);
							startActivity(intent);
							finish();
						}
					}
				};
				SaveCardInfo saveCardInfo = new SaveCardInfo(AddBankCardActivity.this, saveCardListener, cardInfo);
				saveCardInfo.saveData();
			}

			break;

		default:
			break;
		}
	}

}
