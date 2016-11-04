package com.miduo.financialmanageclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Selection;
import android.text.Spannable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.DataEntity;
import com.miduo.financialmanageclient.bean.NRateBean;
import com.miduo.financialmanageclient.bean.Rates;
import com.miduo.financialmanageclient.util.FloatUtil;
import com.miduo.financialmanageclient.util.MToast;
import com.umeng.analytics.MobclickAgent;

/**
 * 收益计算器页面
 * 
 * @author huozhenpeng
 * 
 */
public class EarningsCaculatorActivity extends GesterSetBaseActivity implements
		OnClickListener {
	private ImageView left_img;
	private TextView title_txt;
	private TextView right_txt;
	private SeekBar seekbar;
	private EditText tv_investment;// 投资金额
	private TextView start_amount;// 起投金额
	private TextView surplus_amount;// 剩余金额
	private int start;// 单位元
	private int surplus;// 单位元
	private int seekbar_width;// seekbar宽度
	private int deltaAmount = 10000;// 递增金额
	private int currentProgress;
	private int tempProgress;
	private Handler handler;
	private boolean flag = true;// 记录是前进还是后退了
	private TextView tv_miduoprofit;// 米多收益
	private TextView tv_bankcurrentprofit;// 银行活期收益
	private TextView tv_bankprofit;// 银行理财收益
	private float miduoprofitrate ;// 米多收益率
	private float bankcurrentprofitrate ;// 银行活期收益率
	private float bankprofitrate ;// 银行理财收益率
	private TextView tv_pruchase;// 立即购买
	private long money;
	private boolean issatify;// 是否满足投资条件
	private DataEntity product;
	private TextView tv_miduorate;
	private TextView tv_time;
	private NRateBean nRateBean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_earningscaculator);
		initView();
		initEvent();
		initData();
	}

	private void initData() {
		product = (DataEntity) getIntent().getSerializableExtra("product_info");
		if (product == null)
			return;
		nRateBean = (NRateBean) getIntent().getSerializableExtra("bank_rate");
		if (nRateBean == null) {
			return;
		}
		bankcurrentprofitrate = Float.valueOf(nRateBean.getCallRate());
		bankprofitrate = Float.valueOf(nRateBean.getCommercialRate());
		tv_time.setText(product.getProductPeriodDesc()
				+ product.getUnitProductPeriod());
		seekbar_width = (int) getResources().getDimension(R.dimen.px2dp_666);
		Selection.setSelection((Spannable) tv_investment.getText(),
				tv_investment.getText().length());// 定位光标到末尾
		start = Integer.valueOf(product.getIncreaceTimes());
		tv_investment.setText(FloatUtil.toStringSeparator(start));
		cacuMiduorate(start);
		changeAllProfit();
		start_amount.setText(start + "元");
		surplus = Integer.valueOf(product.getRemainAmount());
		if (surplus % 10000 == 0) {
			surplus_amount.setText(surplus / 10000 + "万元");
		} else {
			surplus_amount.setText(surplus + "元");
		}
		seekbar.setMax(surplus - start);

	}

	private void cacuMiduorate(long money) {
		Rates rates = null;
		miduoprofitrate=-1;
		for (int i = 0; i < nRateBean.getExpertRates().getData().size(); i++) {
			rates = nRateBean.getExpertRates().getData().get(i);
			if (nRateBean.getExpertRates().getData().size() == 1) {
				miduoprofitrate = (float) rates.getCommission();
				break;
			} else {
				if (rates.getStartPrice() <= money
						&& money < rates.getEndPrice()) {
					miduoprofitrate = (float) rates.getCommission();
					break;
				}
			}

		}
		if(miduoprofitrate==-1)
		{
			miduoprofitrate=(float) nRateBean.getExpertRates().getData().get(nRateBean.getExpertRates().getData().size()-1).getCommission();
		}
		tv_miduorate.setText(miduoprofitrate * 100 + "%");
	}

	private void initEvent() {
		left_img.setOnClickListener(this);

		seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				flag = true;// 快速滑动有bug，重新赋下值
				tempProgress = currentProgress % deltaAmount;
				handler.sendEmptyMessage(0x01);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if (fromUser) {
					tv_investment.setText(FloatUtil.toStringSeparator(progress
							/ deltaAmount * deltaAmount + start));
					Selection.setSelection((Spannable) tv_investment.getText(),
							tv_investment.getText().length());// 定位光标到末尾
					cacuMiduorate(formatMoneylong(tv_investment.getText().toString()));
					changeAllProfit();
					currentProgress = progress;
				}

			}
		});
		tv_pruchase.setOnClickListener(this);
		tv_investment.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {
					showTips();
				}

			}
		});
	}

	/**
	 * 投资金额不满足条件时，提示信息
	 */
	private boolean showTips() {
		money = formatMoneylong(tv_investment.getText().toString());
		cacuMiduorate(money);

		if (money < start) {
			issatify = false;
			seekbar.setProgress(0);
			MToast.showToast(EarningsCaculatorActivity.this, start + "元起投！");
		} else if (money > surplus) {
			issatify = false;
			seekbar.setProgress(surplus - start);
			MToast.showToast(EarningsCaculatorActivity.this, "最多可投" + surplus
					+ "万元!");
		} else {
			seekbar.setProgress((int) (money - start));
			if (money % deltaAmount != 0) {
				issatify = false;
				MToast.showToast(EarningsCaculatorActivity.this, "投资金额以"
						+ deltaAmount + "元递增！");
			} else {
				issatify = true;
			}

		}
		tv_investment.setText(FloatUtil.toStringSeparator(money));
		Selection.setSelection((Spannable) tv_investment.getText(),
				tv_investment.getText().length());// 定位光标到末尾
		return issatify;
	}

	private void initView() {
		MyApplication.activityLists.add(this);
		tv_miduorate = (TextView) this.findViewById(R.id.tv_miduorate);
		tv_time = (TextView) this.findViewById(R.id.tv_time);
		tv_pruchase = (TextView) this.findViewById(R.id.tv_pruchase);
		tv_miduoprofit = (TextView) this.findViewById(R.id.tv_miduoprofit);
		tv_bankcurrentprofit = (TextView) this
				.findViewById(R.id.tv_bankcurrentprofit);
		tv_bankprofit = (TextView) this.findViewById(R.id.tv_bankprofit);
		left_img = (ImageView) this.findViewById(R.id.left_img);
		title_txt = (TextView) this.findViewById(R.id.title_txt);
		right_txt = (TextView) this.findViewById(R.id.right_txt);
		right_txt.setVisibility(View.INVISIBLE);
		title_txt.setText("收益计算器");
		seekbar = (SeekBar) this.findViewById(R.id.seekbar);
		tv_investment = (EditText) this.findViewById(R.id.et_investment);
		start_amount = (TextView) this.findViewById(R.id.start_amount);
		surplus_amount = (TextView) this.findViewById(R.id.surplus_amount);
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 0x01:
					tempProgress = tempProgress % deltaAmount;
					if (tempProgress != 0) {
						if (tempProgress <= deltaAmount / 2)// 向后退
						{
							tempProgress -= deltaAmount / 10;// 每次递增量是递增金额的十分之一
							if (tempProgress <= 0) {
								tempProgress = 0;
								flag = true;
							}

						} else// 向前进
						{
							tempProgress += deltaAmount / 10;
							if (tempProgress >= deltaAmount) {
								tempProgress = deltaAmount;
								flag = false;
							}
						}
						seekbar.setProgress(currentProgress / deltaAmount
								* deltaAmount + tempProgress);
						handler.sendEmptyMessage(0x01);
					} else {
						tv_investment.setText(FloatUtil
								.toStringSeparator((currentProgress
										/ deltaAmount + (flag ? 0 : 1))
										* deltaAmount + start));
						Selection.setSelection(
								(Spannable) tv_investment.getText(),
								tv_investment.getText().length());// 定位光标到末尾
						changeAllProfit();
					}
					break;

				default:
					break;
				}
				super.handleMessage(msg);
			}
		};
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_img:
			finish();
			break;
		case R.id.tv_pruchase:
			if (!showTips()) {
				return;
			}
			Intent intent = new Intent(this, ProductPurchaseActivity.class);
			intent.putExtra("MONEY", tv_investment.getText().toString());
			intent.putExtra("product_info", product);
			intent.putExtra("bank_rate", nRateBean);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("收益计算器"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("收益计算器"); //
		MobclickAgent.onPause(this);
	}

	/**
	 * 动态修改米多收益、银行活期收益、银行理财收益数据
	 * 收益金额 ＝ 投资金额 × 收益率 × 今天距最近赎回日（若没有赎回日，则为产品到期日）的天数 ÷ 365
	 */
	public void changeAllProfit() {
		double investment = formatMoney(tv_investment.getText().toString());
		if(nRateBean==null)
			return;
//		int day=nRateBean.getRedeemDates()==0?Integer.parseInt(nRateBean.getProductPeriod()):nRateBean.getRedeemDates();
		int day=Integer.parseInt(nRateBean.getProductPeriod());
		tv_miduoprofit.setText("￥"
				+ FloatUtil.toTwoDianStringSeparator(investment
						* miduoprofitrate * day / 365));
		tv_bankcurrentprofit.setText("￥"
				+ FloatUtil.toTwoDianStringSeparator(investment
						* bankcurrentprofitrate * day / 365));
		tv_bankprofit.setText("￥"
				+ FloatUtil.toTwoDianStringSeparator(investment
						* bankprofitrate * day / 365));

	}

	/**
	 * 去逗号，
	 * 
	 * @param money
	 */
	private double formatMoney(String money) {

		return Double.valueOf(money.replace(",", ""));
	}

	/**
	 * 去逗号，.00
	 * 
	 * @param money
	 */
	private long formatMoneylong(String money) {
		double a = Double.valueOf(money.replace(",", ""));
		return (long) a;
	}

}
