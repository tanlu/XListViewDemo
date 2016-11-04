package com.miduo.financialmanageclient.ui;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.BankCardInfo;
import com.miduo.financialmanageclient.bean.DataEntity;
import com.miduo.financialmanageclient.bean.DialogBean;
import com.miduo.financialmanageclient.bean.NBankRateResult;
import com.miduo.financialmanageclient.bean.NOrderResult;
import com.miduo.financialmanageclient.bean.NRateBean;
import com.miduo.financialmanageclient.bean.NSurplusResult;
import com.miduo.financialmanageclient.bean.Rates;
import com.miduo.financialmanageclient.common.CommonUtil;
import com.miduo.financialmanageclient.listener.DialogEventListener;
import com.miduo.financialmanageclient.util.FloatUtil;
import com.miduo.financialmanageclient.util.JsonUtils;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

/**
 * 产品购买页面
 * 
 * @author huozhenpeng
 * 
 */
public class ProductPurchaseActivity extends GesterSetBaseActivity implements
		OnClickListener, OnFocusChangeListener {

	private ImageView iv_left;
	private TextView tv_center;
	private TextView tv_right;
	private EditText et_money;
	private TextView tv_money;
	private ImageView iv_cancel;
	private TextView tv_commit;
	private String money = "";
	private TextView tv_startamount;
	private TextView tv_incrementamount;
	private TextView tv_totalamount;
	private double startamount;// 以元为单位
	private double incrementamount;// 以元为单位
	private double totalamount;// 以元为单位
	private TextView tv_startunit;
	private TextView tv_starttip;
	private TextView tv_incrementunit;
	private TextView tv_incrementtip;
	private TextView tv_totalunit;
	private TextView tv_totaltip;
	private TextView tv_totalrevenue;
	private LinearLayout ll_parent;
	private boolean canPurchase = true;// 是否满足购买条件
	private String tips;// 不满足条件时的提示信息
	private DataEntity product;
	private TextView tv_totalunitwan;
	private TextView tv_productname;
	private NOrderResult nOrderResult;
	private Map<String, String> map;
	private NSurplusResult nSurplusResult;
	private Map<String, String> sulMap;
	private NRateBean nRateBean;
	private float miduoprofitrate;// 米多收益率
	private String bankResult;
	private LinearLayout ll_profit;
	private boolean flag = false;// 是否是下单失败时的请求额度
	private Map<String, String> profitMap;// 请求银行收益率
	private NBankRateResult nBankRateResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_product_purchase);
		initView();
		initEvent();
		initData();
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("投资"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("投资"); //
		MobclickAgent.onPause(this);
	}

	/**
	 * 拉取数据
	 */
	private void initData() {
		tv_center.setText("投资");
		tv_right.setText("协议");
		product = (DataEntity) getIntent().getSerializableExtra("product_info");
		if (product == null)
			return;
		if (product.getExpertRateDesc().contains("%")) {
			ll_profit.setVisibility(View.VISIBLE);
		} else {
			ll_profit.setVisibility(View.GONE);
		}
		// nRateBean = (NRateBean)
		// getIntent().getSerializableExtra("bank_rate");
		// if (nRateBean == null) {
		// ll_profit.setVisibility(View.GONE);
		// }
		profitMap.clear();
		profitMap.put("productId", product.getId());
		ProgressDialogUtil.showProgress(ProductPurchaseActivity.this);
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					nBankRateResult = WebServiceClient
							.getProductProfit(profitMap);
					handler.sendEmptyMessage(0x06);
				} catch (AppException e) {
					handler.sendEmptyMessage(0x07);
					e.printStackTrace();
				}
			}
		}).start();

	}

	/**
	 * 等拿到服务器产品收益率，再初始化
	 */
	private void initRealData() {
		tv_startamount.setText(FloatUtil.toStringSeparator(Long.valueOf(product
				.getBuyMinLimited())));
		tv_incrementamount.setText(FloatUtil.toStringSeparator(Long
				.valueOf(product.getIncreaceTimes())));
		totalamount = Double.valueOf(product.getRemainAmount());
		startamount = Double.valueOf(product.getBuyMinLimited());
		incrementamount = Double.valueOf(product.getIncreaceTimes());
		if (totalamount % 10000 == 0) {
			tv_totalamount.setText(FloatUtil
					.toStringSeparator(totalamount / 10000));
			tv_totalunitwan.setVisibility(View.VISIBLE);
		} else {
			tv_totalamount.setText(FloatUtil.toStringSeparator(totalamount));
			tv_totalunitwan.setVisibility(View.GONE);
		}
		tv_productname.setText(product.getProductName());
		String temp = getIntent().getStringExtra("MONEY");
		if (!TextUtils.isEmpty(temp)) {
			et_money.setText(temp);
			formatMoney(temp);
			cacuMiduorate(Long.valueOf(this.money));
		} else {
			et_money.setText(FloatUtil.toStringSeparator(Long.valueOf(product
					.getBuyMinLimited())));
			this.money = product.getBuyMinLimited();
			cacuMiduorate(Long.valueOf(this.money));
		}
		changeAllProfit();
		if (!TextUtils.isEmpty(money)) {
			et_money.setText(FloatUtil.toStringSeparator2(Long.valueOf(money)));
			tv_money.setText(StringUtil.number2CNMontrayUnit(BigDecimal
					.valueOf(Long.valueOf(money))));
		}

		handler.sendEmptyMessageDelayed(0x03, 20000);
	}

	private void cacuMiduorate(long money) {
		if (nRateBean == null) {
			ll_profit.setVisibility(View.GONE);
			return;
		}
		Rates rates = null;
		miduoprofitrate = -1;
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
		if (miduoprofitrate == -1
				&& nRateBean.getExpertRates().getData().size() > 0) {
			miduoprofitrate = (float) nRateBean.getExpertRates().getData()
					.get(nRateBean.getExpertRates().getData().size() - 1)
					.getCommission();
		}
		tv_totalrevenue.setText(miduoprofitrate * 100 + "%");
	}

	/**
	 * 设置监听
	 */
	private void initEvent() {
		iv_left.setOnClickListener(this);
		tv_right.setOnClickListener(this);
		et_money.addTextChangedListener(new MyWatcher());
		iv_cancel.setOnClickListener(this);
		et_money.setOnFocusChangeListener(this);
		tv_commit.setOnClickListener(this);
	}

	/**
	 * 初始化View
	 */
	private void initView() {
		MyApplication.isBuy = true;
		ll_profit = (LinearLayout) this.findViewById(R.id.ll_profit);
		ll_profit.setVisibility(View.VISIBLE);
		iv_left = (ImageView) this.findViewById(R.id.left_img);
		tv_center = (TextView) this.findViewById(R.id.title_txt);
		tv_right = (TextView) this.findViewById(R.id.right_txt);
		et_money = (EditText) this.findViewById(R.id.et_money);
		tv_money = (TextView) this.findViewById(R.id.tv_money);
		iv_cancel = (ImageView) this.findViewById(R.id.iv_cancel);
		tv_commit = (TextView) this.findViewById(R.id.tv_commit);
		tv_startamount = (TextView) this.findViewById(R.id.tv_startamount);
		tv_incrementamount = (TextView) this
				.findViewById(R.id.tv_incrementacmount);
		tv_totalamount = (TextView) this.findViewById(R.id.tv_totalamount);
		tv_startunit = (TextView) this.findViewById(R.id.tv_startunit);
		tv_starttip = (TextView) this.findViewById(R.id.tv_starttip);
		tv_incrementunit = (TextView) this.findViewById(R.id.tv_incrementunit);
		tv_incrementtip = (TextView) this.findViewById(R.id.tv_incrementtip);
		tv_totalunit = (TextView) this.findViewById(R.id.tv_totalunit);
		tv_totaltip = (TextView) this.findViewById(R.id.tv_totaltip);
		tv_totalrevenue = (TextView) this.findViewById(R.id.tv_totalrevenue);
		ll_parent = (LinearLayout) this.findViewById(R.id.ll_parent);
		tv_totalunitwan = (TextView) this.findViewById(R.id.tv_totalunitwan);
		tv_productname = (TextView) this.findViewById(R.id.tv_productname);
		map = new HashMap<String, String>();
		sulMap = new HashMap<String, String>();
		profitMap = new HashMap<String, String>();

	}

	class MyWatcher implements TextWatcher {

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			if (!TextUtils.isEmpty(et_money.getText())) {
				money = et_money.getText().toString();
				formatMoney(money);
				resetValue();
			} else {
				ProductPurchaseActivity.this.money = 0 + "";
				tv_money.setText("");
			}
			// 判断是否满足条件
			// 1.必须大于起投金额 2.必修是递增的整数倍3.不能超过最大值
			changeTip();

		}

		@Override
		public void afterTextChanged(Editable s) {

		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_img:
			handler.removeCallbacksAndMessages(null);
			this.finish();
			MyApplication.activityLists.remove(this);
			break;
		case R.id.right_txt:
			et_money.clearFocus();
			CommonUtil.downPdf(ProductPurchaseActivity.this,
					product.getProtocolFile());
			break;
		case R.id.iv_cancel:
			et_money.setText("");
			money = "";
			break;
		case R.id.tv_commit:
			if (canPurchase) {
				commitDataToServier();
			} else {
				MToast.showToast(this, tips);
			}
			break;

		default:
			break;
		}
	}

	/**
	 * 向服务器提交订单
	 */
	private void commitDataToServier() {
		ProgressDialogUtil.showProgress(this);
		map.clear();
		map.put("type", 0 + "");
		map.put("productId", product.getId() + "");
		formatMoney(et_money.getText().toString());
		map.put("amount", this.money);
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					nOrderResult = WebServiceClient.getOrderResult(map);
					handler.sendEmptyMessage(0x01);
				} catch (AppException e) {
					handler.sendEmptyMessage(0x02);
					e.printStackTrace();
				}
			}
		}).start();
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			ProgressDialogUtil.closeProgress();
			switch (msg.what) {
			case 0x01:
				if (nOrderResult != null) {
					if (nOrderResult.getState() == 1) {
						handler.removeCallbacksAndMessages(null);
						if (nOrderResult.getData().getUserBankCardValidSize() > 0)// 有银行卡
						{
							haveBankCard();
						} else// 无银行卡
						{
							noBankCard();
						}
					} else {// 下单失败
						if (nOrderResult.getState() == ConstantValues.LOGIN_ERROR) {
							MDialog.showPsdErrorDialog(
									ProductPurchaseActivity.this,
									nOrderResult.getMsg());
						} else if (nOrderResult.getState() == -4) {// 售罄
							showDialog(nOrderResult.getState(),
									nOrderResult.getMsg());

						} else if (nOrderResult.getState() == -5) {// 超额
							// flag = true;
							showDialog(nOrderResult.getState(),
									nOrderResult.getMsg());
							requestSurplus();

						} else if (nOrderResult.getState() == -6) {// 剩余额度已被预约
							showDialog(nOrderResult.getState(),
									nOrderResult.getMsg());
						} else {
							MToast.showToast(ProductPurchaseActivity.this,
									nOrderResult.getMsg());
						}
					}
				}
				break;
			case 0x02:
				MToast.showToast(ProductPurchaseActivity.this, "订单生成失败！");
				break;
			case 0x03:// 每隔20秒，重新请求剩余额度
				requestSurplus();
				break;
			case 0x04:
				if (nSurplusResult != null) {
					totalamount = (long) nSurplusResult.getData();
					if (totalamount % 10000 == 0) {
						tv_totalamount
								.setText(FloatUtil
										.toStringSeparator((int) (totalamount / 10000)));
						tv_totalunitwan.setVisibility(View.VISIBLE);
					} else {
						tv_totalamount.setText(FloatUtil
								.toStringSeparator((long) totalamount));
						tv_totalunitwan.setVisibility(View.GONE);
					}
					if (flag) {// 无用代码，先留着
						DialogBean dialog = new DialogBean();
						dialog.setTitle("下单失败！");
						if (totalamount == 0) {
							dialog.setContent(ProductPurchaseActivity.this
									.getResources().getString(
											R.string.orderfailed_2));
							dialog.setSubmit("看看别的");
							dialog.setDialogEvent(new DialogEventListener() {

								@Override
								public void submit() {
									MyApplication.finishPurchaseActivity();
								}

								@Override
								public void cancel() {

								}
							});
						} else {
							dialog.setContent(ProductPurchaseActivity.this
									.getResources().getString(
											R.string.orderfailed_1));
							dialog.setSubmit("重新填写投资金额");
						}
						MDialog.showDialog1(ProductPurchaseActivity.this,
								dialog);
					}
					flag = false;
					handler.sendEmptyMessageDelayed(0x03, 20000);
				}
				break;
			case 0x05:
				if (null == bankResult) {
					MToast.showToast(ProductPurchaseActivity.this, "数据异常");
				} else {
					List<BankCardInfo> cardLst = null;
					try {
						JSONObject jo = new JSONObject(bankResult);
						int state = jo.getInt("state");
						if (state == 1) {
							String data = jo.getString("data");
							cardLst = JsonUtils.toBean(data,
									new TypeToken<List<BankCardInfo>>() {
									}.getType());
							// 跳转到确认支付页面
							Intent intent = new Intent(
									ProductPurchaseActivity.this,
									ConfirmPaymentActivity.class);
							if (nOrderResult.getData() != null) {
								intent.putExtra("ORDER", nOrderResult.getData());
								intent.putExtra("BANKCARD", cardLst.get(0));
							}
							startActivity(intent);
							MyApplication.activityLists
									.add(ProductPurchaseActivity.this);

						} else {
							String tip = jo.getString("msg");
							MToast.showToast(ProductPurchaseActivity.this, tip);
						}
					} catch (Exception e) {
						e.printStackTrace();
						MToast.showToast(ProductPurchaseActivity.this, "数据异常");
					} finally {
					}
				}
				break;
			case 0x06:
				if (nBankRateResult != null) {
					nRateBean = nBankRateResult.getData();
					if (nRateBean == null) {
						ll_profit.setVisibility(View.GONE);
					}
				} else {
					ll_profit.setVisibility(View.GONE);
				}
				initRealData();
				break;
			case 0x07:
				initRealData();
				break;
			default:
				break;
			}
		}

	};

	private void requestSurplus() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					sulMap.clear();
					sulMap.put("productId", product.getId() + "");
					// sulMap.put("productId",
					// nOrderResult.getData().getProductId()+"");
					nSurplusResult = WebServiceClient.getNSurplusResult(sulMap);
					handler.sendEmptyMessage(0x04);
				} catch (AppException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	/**
	 * 有银行卡信息
	 */
	private void haveBankCard() {
		// 有卡
		et_money.clearFocus();
		Intent intent = new Intent(this, SelectedBankCardActivity.class);
		intent.putExtra("ORDER", nOrderResult.getData());
		startActivity(intent);
		MyApplication.activityLists.add(this);
	}

	/**
	 * 无卡
	 */
	private void noBankCard() {
		et_money.clearFocus();
		Intent intent = new Intent(this, AddBankCardActivity.class);
		intent.putExtra("ORDER", nOrderResult.getData());
		startActivity(intent);
		MyApplication.activityLists.add(this);
	};

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		switch (v.getId()) {
		case R.id.et_money:
			if (hasFocus)// 获得焦点
			{
				if (!TextUtils.isEmpty(money)) {
					formatMoney(money);
					et_money.setText(money);
					Selection.setSelection((Spannable) et_money.getText(),
							et_money.getText().length());// 定位光标到末尾
				}
			} else// 失去焦点
			{
				if (!TextUtils.isEmpty(money)) {
					formatMoney(money);
					et_money.setText(FloatUtil.toStringSeparator2(Long
							.valueOf(money)));

				}

			}

			break;

		default:
			break;
		}
	}

	/**
	 * 重新给money赋值，主要是为了防止money大于了totalmoney（数字无限大会崩）
	 */
	private void resetValue() {
		if (money.length() > 10) {
			this.money = money.substring(0, 10);
			et_money.setText(this.money);
			Selection.setSelection((Spannable) et_money.getText(), et_money
					.getText().length());

		}
		tv_money.setText(StringUtil.number2CNMontrayUnit(BigDecimal
				.valueOf(Long.valueOf(money))));
	}

	/**
	 * 动态修改米多收益、银行活期收益、银行理财收益数据 收益金额 ＝ 投资金额 × 收益率 × 今天距最近赎回日（若没有赎回日，则为产品到期日）的天数
	 * ÷ 365
	 */
	public void changeAllProfit() {
		if (nRateBean == null)
			return;
		// int day = nRateBean.getRedeemDates() == 0 ?
		// Integer.parseInt(nRateBean
		// .getProductPeriod()) : nRateBean.getRedeemDates();
		Integer day = 0;
		day = nRateBean.getDates();
		if (day == null) {
			day = 0;
		}
		tv_totalrevenue.setText(FloatUtil.toTwoDianStringSeparator(Long
				.valueOf(this.money) * miduoprofitrate * day / 365));
	}

	/**
	 * 修改提示或者按钮点击
	 */
	private void changeTip() {
		long perchaseMoney = Long.valueOf(this.money);
		cacuMiduorate(perchaseMoney);
		changeAllProfit();
		double differenceValue = perchaseMoney - startamount;
		tv_totaltip.setTextColor(Color.parseColor("#999999"));
		tv_totalunit.setTextColor(Color.parseColor("#999999"));
		tv_totalamount.setTextColor(Color.parseColor("#999999"));
		tv_totalunitwan.setTextColor(Color.parseColor("#999999"));
		// tv_commit.setOnClickListener(this);
		canPurchase = true;
		tips = "";
		tv_commit.setBackgroundResource(R.drawable.button_bg_blue);
		tv_startunit.setTextColor(Color.parseColor("#999999"));
		tv_starttip.setTextColor(Color.parseColor("#999999"));
		tv_startamount.setTextColor(Color.parseColor("#999999"));
		tv_incrementunit.setTextColor(Color.parseColor("#999999"));
		tv_incrementtip.setTextColor(Color.parseColor("#999999"));
		tv_incrementamount.setTextColor(Color.parseColor("#999999"));
		if (perchaseMoney > totalamount)// 购买金额超限
		{
			tv_totaltip.setTextColor(Color.parseColor("#f34d4d"));
			tv_totalunit.setTextColor(Color.parseColor("#f34d4d"));
			tv_totalamount.setTextColor(Color.parseColor("#f34d4d"));
			tv_totalunitwan.setTextColor(Color.parseColor("#f34d4d"));
			canPurchase = false;
			if ((int) totalamount % 10000 == 0) {
				tips = "最多可投" + (int) totalamount / 10000 + "万元！";
			} else {
				tips = "最多可投" + (int) totalamount + "元！";
			}
			// tv_commit.setOnClickListener(null);
			// tv_commit.setBackgroundResource(R.drawable.button_bg_gray);
		}
		if (perchaseMoney < startamount)// 购买金额小于起始金额
		{
			tv_startunit.setTextColor(Color.parseColor("#f34d4d"));
			tv_starttip.setTextColor(Color.parseColor("#f34d4d"));
			tv_startamount.setTextColor(Color.parseColor("#f34d4d"));
			canPurchase = false;
			tips = (int) startamount + "元起投！";
			// tv_commit.setOnClickListener(null);
			// tv_commit.setBackgroundResource(R.drawable.button_bg_gray);
		}
		if (perchaseMoney > startamount
				&& differenceValue % incrementamount != 0)// 不是递增的整数倍
		{
			tv_incrementunit.setTextColor(Color.parseColor("#f34d4d"));
			tv_incrementtip.setTextColor(Color.parseColor("#f34d4d"));
			tv_incrementamount.setTextColor(Color.parseColor("#f34d4d"));
			canPurchase = false;
			tips = "投资金额以" + (int) incrementamount + "元递增！";
			// tv_commit.setOnClickListener(null);
			// tv_commit.setBackgroundResource(R.drawable.button_bg_gray);
		}
	}

	/**
	 * 去逗号，.00
	 * 
	 * @param money
	 */
	private void formatMoney(String money) {
		this.money = money.replace(",", "");
		double a = Double.valueOf(this.money);
		this.money = (long) a + "";
	}

	/**
	 * 弹框
	 * 
	 * @param status
	 * @param msg
	 */
	private void showDialog(final int status, String msg) {
		DialogBean dialog = new DialogBean();
		dialog.setTitle("下单失败！");
		switch (status) {
		case -4:// 售罄
			dialog.setSubmit("看看别的");
			break;
		case -5:// 超额
			dialog.setSubmit("重新填写投资金额");
			break;
		case -6:// 剩余额度被预约
			dialog.setSubmit("看看别的");
			break;

		default:
			break;
		}
		dialog.setContent(msg);
		dialog.setDialogEvent(new DialogEventListener() {

			@Override
			public void submit() {
				if (status == -4 || status == -6) {
					MyApplication.finishPurchaseActivity();
				}
			}

			@Override
			public void cancel() {

			}
		});
		MDialog.showDialog1(ProductPurchaseActivity.this, dialog);

	}
}
