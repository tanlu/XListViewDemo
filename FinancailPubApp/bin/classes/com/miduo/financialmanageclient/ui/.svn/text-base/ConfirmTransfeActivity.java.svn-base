package com.miduo.financialmanageclient.ui;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.bean.BankCardInfo;
import com.miduo.financialmanageclient.bean.BankMapEntity;
import com.miduo.financialmanageclient.bean.TranferBean_Data;
import com.miduo.financialmanageclient.util.FloatUtil;
import com.miduo.financialmanageclient.util.JsonUtils;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

public class ConfirmTransfeActivity extends GesterSetBaseActivity implements
		OnClickListener, OnFocusChangeListener {
	private Map<String, String> map = new HashMap<String, String>();
	private TextView tv_01, tv_02, tv_03, tv_04, tv_05, my_money, small_price,
			you_money, right_txt;
	private EditText big_price;
	private Button button;
	private ImageView left_img;
	private TextView name, poundage, min_price, max_price;
	private ImageView delete;
	private float shouxufei;
	private String change, money = "10000", money1, moneyTow, shouxufeiTow,
			text;
	// private TextView tran_price;
	private double a;
	private double b;
	private int cardnum;
	private String assetNo;
	private Bundle mBundle;
	private BankMapEntity bankCardInfo;
	private BankCardInfo bankCardInfo_re;
	private TranferBean_Data tranferBean_Data;
	private TransfeAsyncTask transfeAsyncTask;
	private String tRANSFER_INOFO_URL;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_confirm_transfe);

		Intent intent = getIntent();
		assetNo = intent.getStringExtra("assetNo");
		assetNo = "MDL01447759191540";
		initUI();
		iniEvent();
		initDate();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("确认转让价格"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("确认转让价格"); //
		MobclickAgent.onPause(this);
	}

	private void iniEvent() {

		left_img.setOnClickListener(this);
		right_txt.setOnClickListener(this);
		button.setOnClickListener(this);
		delete.setOnClickListener(this);

		big_price.addTextChangedListener(new MyWatcher());
		big_price.setOnFocusChangeListener(this);
	}

	private void initUI() {
		TextView title_txt = (TextView) findViewById(R.id.title_txt);
		title_txt.setText("确认转让价格");

		left_img = (ImageView) findViewById(R.id.left_img);

		right_txt = (TextView) findViewById(R.id.right_txt);
		right_txt.setText("协议");

		name = (TextView) findViewById(R.id.name);
		tv_01 = (TextView) findViewById(R.id.tv_01);
		tv_02 = (TextView) findViewById(R.id.tv_02);
		tv_03 = (TextView) findViewById(R.id.tv_03);
		tv_04 = (TextView) findViewById(R.id.tv_04);
		tv_05 = (TextView) findViewById(R.id.tv_05);
		big_price = (EditText) findViewById(R.id.big_price);
		small_price = (TextView) findViewById(R.id.small_price);

		my_money = (TextView) findViewById(R.id.my_money);
		you_money = (TextView) findViewById(R.id.you_money);
		button = (Button) findViewById(R.id.button);
		delete = (ImageView) findViewById(R.id.delete);
		// 手续费
		poundage = (TextView) findViewById(R.id.poundage);
		min_price = (TextView) findViewById(R.id.min_price);
		max_price = (TextView) findViewById(R.id.max_price);
		big_price.setEnabled(false);
	}

	private void initDate() {
		if (assetNo != null) {
			map.put("assetNo", assetNo);

			transfeAsyncTask = new TransfeAsyncTask(map);
			transfeAsyncTask.execute();
		}

	}

	@Override
	public void onClick(View v) {

		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.button:

			if (Long.valueOf(money) < a || Long.valueOf(money) > b) {
				MToast.showToast(this, "转让价格超出合理范围，价格建议在" + a + "-" + b + "元之间");
				return;
			}
			// 添加银行卡 获取服务器数据 判断 有无
			if (cardnum == 0) {
				intent.setClass(this, AddReceiveBankCardActivity.class);
				startActivityForResult(intent, 1002);
			}
			// 选择绑定的银行卡
			else if (cardnum > 0) {
				if (tranferBean_Data.getBankcardId() == "0") {

					intent.setClass(this, BankCardLstActivity.class);
					startActivityForResult(intent, 1001);
				} else {
					goToAffirmPage();
				}
			}
			// 跳转到
			else if (cardnum == 1) {
				goToAffirmPage();
			}
			break;

		case R.id.right_txt:
			// 协议页面
			Log.i("-------------right_txt", "点我啊");
			big_price.clearFocus();
			Intent intent1 = new Intent();
			intent1.setClass(this, AgreementActivity.class);
			intent1.putExtra("agree_type", 1);
			startActivity(intent1);
			break;
		case R.id.left_img:
			finish();
			break;
		case R.id.delete:
			// 删除输入的金额
			if (!TextUtils.isEmpty(big_price.getText())) {
				Log.i("------------------------------------------", ""
						+ "delete");
				big_price.setText("");
			}
			break;
		default:
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case 1002:
			bankCardInfo_re = (BankCardInfo) data
					.getSerializableExtra("bank_card");
			break;
		case 1001:
			bankCardInfo_re = (BankCardInfo) data
					.getSerializableExtra("bank_card");
			break;

		default:
			break;
		}

		if (resultCode == RESULT_OK) {

			bankCardInfo = new BankMapEntity();
			bankCardInfo.setBranchBankAddress(bankCardInfo_re.getBankAddress());
			bankCardInfo.setBankName(bankCardInfo_re.getBankName());
			bankCardInfo.setPayee(bankCardInfo_re.getRealName());
			bankCardInfo.setBankIco(bankCardInfo_re.getSmallIco());
			bankCardInfo.setBankCardId(Integer.valueOf(bankCardInfo_re
					.getCardShortNo()));
			bankCardInfo.setShortBankNo(String.valueOf(bankCardInfo_re
					.getBankCode()));
			tranferBean_Data.setBankMap(bankCardInfo);
			cardnum++;
			goToAffirmPage();
		}
	}

	private void goToAffirmPage() {
		Intent intent = new Intent();
		intent = new Intent(this, AffirmRolloutTwoActivity.class);
		intent.putExtra("moneyTow", moneyTow);
		intent.putExtra("money1", money1);
		intent.putExtra("shouxufeiTow", shouxufeiTow);
		intent.putExtra("cardnum", cardnum);
		intent.putExtra("assetNo", assetNo);
		intent.putExtra("infoObject", tranferBean_Data);// 资产数据
		startActivity(intent);
	}

	class MyWatcher implements TextWatcher {

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			if (!TextUtils.isEmpty(big_price.getText())) {
				// initDate1();
				money = big_price.getText().toString();
				money1 = big_price.getText().toString();
				formatMoney(money);
				resetValue();
				// 手续费
				long int1 = Long.parseLong(money);
				shouxufei = (float) (int1 * 0.25);
				poundage.setText(shouxufei + "");
				// 传过去的手续费
				shouxufeiTow = poundage.getText().toString();
				// 传过去汉字价格
				moneyTow = small_price.getText().toString();
				// 传过去的转让价格

				// 收益率
				if (Long.valueOf(money) <= b && a <= Long.valueOf(money)) {
					// 请求数据，后台返回
					my_money.setText(tv_01.getText());
					you_money.setText(tv_01.getText());

				} else {
					my_money.setText("");
					you_money.setText("");
				}

			} else {
				small_price.setText("");
			}
		}

		@Override
		public void afterTextChanged(Editable s) {

		}
	}

	/**
	 * 重新给money赋值，主要是为了防止money大于了totalmoney（数字无限大会崩）
	 */
	private void resetValue() {
		if (money.length() > 10) {
			this.money = money.substring(0, 10);
			big_price.setText(this.money);
			Selection.setSelection((Spannable) big_price.getText(), big_price
					.getText().length());

		}
		small_price.setText(StringUtil.number2CNMontrayUnit(BigDecimal
				.valueOf(Long.valueOf(money))));
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

	private String formatprice(String money) {

		return String.valueOf(Double.valueOf(money.replace(",", "")));

	}

	/**
	 * 修改提示或者按钮点击
	 */
	private void changeTip() {
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {

		switch (v.getId()) {
		case R.id.big_price:
			if (hasFocus)// 获得焦点
			{
				if (!TextUtils.isEmpty(money)) {
					formatMoney(money);
					big_price.setText(money);
					Selection.setSelection((Spannable) big_price.getText(),
							big_price.getText().length());// 定位光标到末尾
				}
			} else// 失去焦点
			{
				if (!TextUtils.isEmpty(money)) {
					formatMoney(money);
					big_price.setText(FloatUtil.toStringSeparator2(Long
							.valueOf(money)));
					// 判断是否满足条件
					// 1.必须大于起投金额 2.必修是递增的整数倍3.不能超过最大值

					map.put("salePrice", formatprice(big_price.getText()
							.toString()));
					initDate();

					changeTip();
				}
			}
			break;
		default:
			break;
		}
	}

	// 数值非null
	public String value(String str) {
		return StringUtil.showStringContent(str);
	}

	private String mobile;

	private class TransfeAsyncTask extends AsyncTask<Void, Void, String> {

		private String assetNo;
		private AppException ex;
		private Map<String, String> map;

		public TransfeAsyncTask(Map map) {

			this.map = map;
		}

		@Override
		protected String doInBackground(Void... arg0) {

			try {
				ex = null;
				String str = WebServiceClient.getTransfer(map);
				return str;
			} catch (AppException e) {
				// TODO Auto-generated catch block
				this.ex = e;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			ProgressDialogUtil.closeProgress();
			if (null == result) {
				if (ex != null) {
					ex.makeToast(ConfirmTransfeActivity.this);
				} else {
					MToast.showToast(ConfirmTransfeActivity.this, "请求数据失败");
				}
				return;
			} else {
				try {
					JSONObject jo = new JSONObject(result);
					int state = jo.getInt("state");
				
					if (state == ConstantValues.LOGIN_ERROR) {
						String msg = jo.getString("msg");
						MDialog.showPsdErrorDialog(ConfirmTransfeActivity.this, msg);
					
					} else if(state==1){
						
					

						String data = jo.getString("data");
						tranferBean_Data = JsonUtils.toBean(data,
								new TypeToken<TranferBean_Data>() {
								}.getType());
						System.out.println(tranferBean_Data.toString());

						name.setText(tranferBean_Data.getProductTitle());
						tv_01.setText(FloatUtil.toPercentage(Double
								.valueOf(value(tranferBean_Data
										.getProductRate()))));
						tv_02.setText(FloatUtil.toTwoDianStringSeparator(Double
								.valueOf(value(tranferBean_Data.getHoldPrice()))));
						tv_03.setText(FloatUtil.toTwoDianStringSeparator(Double
								.valueOf(value(tranferBean_Data
										.getBuyerTotalIn()))));
						tv_04.setText(value(tranferBean_Data.getBuyerHoldDays()
								+ "天"));
						tv_05.setText(value(tranferBean_Data.getRedeemDate()));
						// 转让价格
						if(map.size()>1)
						{
							text = tranferBean_Data.getSalerPrice();
						}else
						{
							
							text = tv_02.getText().toString();
						}
						big_price.setText(text);
						big_price.setEnabled(true);
						

						// 我的收益对方收益 调用请求方法 获取接口
						my_money.setText(FloatUtil.toPercentage(Double
								.valueOf(value(tranferBean_Data.getSalerRate()))));
						you_money
								.setText(FloatUtil.toPercentage(Double
										.valueOf(value(tranferBean_Data
												.getBuyerRate()))));
						// 价格区间
						min_price.setText(FloatUtil
								.toTwoDianStringSeparator(Double
										.valueOf(value(tranferBean_Data
												.getsPrice()))));
						max_price.setText(FloatUtil
								.toTwoDianStringSeparator(Double
										.valueOf(value(tranferBean_Data
												.getePrice()))));
						a = Double.valueOf(tranferBean_Data.getsPrice());
						b = Double.valueOf(tranferBean_Data.getePrice());
						// 大写金额
						change = small_price.getText().toString();
						if (!TextUtils.isEmpty(money)) {
							big_price.setText(FloatUtil.toStringSeparator2(Long
									.valueOf(money)));
							small_price.setText(StringUtil
									.number2CNMontrayUnit(BigDecimal
											.valueOf(Long.valueOf(money))));

						}
						mobile = tranferBean_Data.getMobile();
						cardnum=tranferBean_Data.getUserBankCardValidSize();

						

					}
				} catch (Exception e) {
					e.printStackTrace();
					MToast.showToast(ConfirmTransfeActivity.this, "数据异常");
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
