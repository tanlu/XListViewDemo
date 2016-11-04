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

public class TransferOrderConfirmActivity extends GesterSetBaseActivity implements OnClickListener,
		OnFocusChangeListener {

	private TextView tv_01, tv_02, tv_03, tv_04, tv_05, my_money, small_price, you_money, right_txt;
	private EditText big_price;
	private Button button;
	private ImageView left_img;
	private TextView name, poundage, min_price, max_price;
	private ImageView delete;
	private float shouxufei;
	private Double startPrice, endPrice;
	private String assetNo;
	private TranferBean_Data product;
	private TransfeAsyncTask transfeAsyncTask;
	private String tempInputMoney;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_confirm_transfe);
		assetNo = getIntent().getStringExtra("assetNo");
		initUI();
		iniEvent();
		initDate();
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

	}

	private void initDate() {
		ProgressDialogUtil.showProgress(TransferOrderConfirmActivity.this);
		if (transfeAsyncTask != null) {
			transfeAsyncTask.cancel(true);
			transfeAsyncTask = null;
		}
		transfeAsyncTask = new TransfeAsyncTask();
		transfeAsyncTask.execute();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button:
			if (product == null) {
				return;
			}
			if (StringUtil.isEmpty(big_price.getText().toString())) {
				MToast.showToast(this, "请输入价格");
				return;
			}
			if (StringUtil.isEmpty(tempInputMoney)) {
				MToast.showToast(this, "请输入价格");
				return;
			}
			if (Double.parseDouble(tempInputMoney) < startPrice || Double.parseDouble(tempInputMoney) > endPrice) {
				MToast.showToast(this, "转让价格超出合理范围，价格建议在" + startPrice + "-" + endPrice + "元之间");
				return;
			}
			Intent intent = new Intent();
			if (product.getBankcardId() == null || product.getBankcardId().equals("0")) {
				if (product.getUserBankCardValidSize() > 0) {
					intent.setClass(this, BankCardLstActivity.class);
					startActivityForResult(intent, 1001);
				} else {
					intent.setClass(this, AddReceiveBankCardActivity.class);
					startActivityForResult(intent, 1001);
				}
			} else {
				goPage();
			}

			break;

		case R.id.right_txt:
			// 协议页面
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
				Log.i("------------------------------------------", "" + "delete");
				big_price.setText("");
				my_money.setText("");
				you_money.setText("");
				tempInputMoney = "";
			}
			break;
		default:
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			BankCardInfo cardInfo = (BankCardInfo) data.getSerializableExtra("bank_card");
			BankMapEntity bankMap = new BankMapEntity();
			bankMap.setBankCardId(cardInfo.getId());
			bankMap.setBankIco(cardInfo.getSmallIco());
			StringBuffer str = new StringBuffer();
			if (!StringUtil.isEmpty(cardInfo.getBankAddress())) {
				str.append(cardInfo.getBankAddress());
			}
			if (!StringUtil.isEmpty(cardInfo.getBranchBank())) {
				str.append(cardInfo.getBranchBank());
			}			
			String address = str.toString();
			bankMap.setBranchBankAddress(StringUtil.showStringContent(address));
			bankMap.setPayee(cardInfo.getRealName());
			bankMap.setShortBankNo(cardInfo.getCardShortNo());
			bankMap.setBankName(cardInfo.getBankName());
			product.setBankMap(bankMap);
			goPage();
		}
	}

	private void goPage() {
		Intent intent = new Intent(this, TransferOrderSubmitActivity.class);
		Bundle bundle = new Bundle();
		product.setTurnMoney(Double.parseDouble(tempInputMoney));
		bundle.putSerializable("product", product);
		bundle.putString("assetNo", assetNo);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	class MyWatcher implements TextWatcher {

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			if (!TextUtils.isEmpty(big_price.getText())) {
				tempInputMoney = big_price.getText().toString();
				formatMoney(tempInputMoney);
				resetValue();
				// 手续费
				double int1 = Double.parseDouble(tempInputMoney);
				shouxufei = (float) (int1 * 0.0025);
				poundage.setText(FloatUtil.toTwoDianStringSeparator(shouxufei));

				// 收益率
				// if (Double.valueOf(tempInputMoney) <= endPrice && startPrice
				// <= Double.valueOf(tempInputMoney)) {
				// // 请求数据，后台返回
				// refreshData();
				// }
				refreshData();

			} else {
				small_price.setText("");
				poundage.setText(getResources().getString(R.string.default_value));
			}
		}

		@Override
		public void afterTextChanged(Editable s) {

		}
	}

	/**
	 * 去逗号，.00
	 * 
	 * @param money
	 */
	private void formatMoney(String money) {
		this.tempInputMoney = money.replace(",", "");
		double a = Double.valueOf(this.tempInputMoney);
		this.tempInputMoney = String.valueOf(a);
	}

	/**
	 * 重新给money赋值，主要是为了防止money大于了totalmoney（数字无限大会崩）
	 */
	private void resetValue() {
		if (tempInputMoney.length() > 10) {
			this.tempInputMoney = tempInputMoney.substring(0, 10);
			big_price.setText(this.tempInputMoney);
			Selection.setSelection((Spannable) big_price.getText(), big_price.getText().length());

		}
		// small_price.setText(StringUtil.number2CNMontrayUnit(BigDecimal.valueOf(Long.valueOf(tempInputMoney))));
		small_price.setText(StringUtil.number2CNMontrayUnit(new BigDecimal(Double.valueOf(tempInputMoney))));
	}

	private void showData() {
		// TODO Auto-generated method stub
		name.setText(StringUtil.showStringContent(product.getProductTitle()));
		tv_01.setText(FloatUtil.toPercentage(Double.valueOf(product.getProductRate())));
		Double money = Double.valueOf(product.getHoldPrice());
		tv_02.setText(FloatUtil.toTwoDianStringSeparator(money));
		tv_03.setText(FloatUtil.toTwoDianStringSeparator(Double.valueOf(product.getBuyerTotalIn())));
		tv_04.setText(StringUtil.showStringContent(product.getBuyerHoldDays() + "天"));
		tv_05.setText(StringUtil.showStringContent(product.getRedeemDate()));

		// 我的收益对方收益 调用请求方法 获取接口
		my_money.setText(FloatUtil.toPercentage(Double.valueOf(product.getSalerRate()) / 100));
		you_money.setText(FloatUtil.toPercentage(Double.valueOf(product.getBuyerRate()) / 100));
		// 价格区间
		startPrice = Double.valueOf(product.getsPrice());
		endPrice = Double.valueOf(product.getePrice());
		min_price.setText(FloatUtil.toTwoDianStringSeparator(startPrice));
		max_price.setText(FloatUtil.toTwoDianStringSeparator(endPrice));
		// 大写金额
		big_price.setHint(FloatUtil.toTwoDianStringSeparator(money));
		// if (money != null) {
		// small_price.setText(StringUtil.number2CNMontrayUnit(new
		// BigDecimal(money)));
		// }
	}

	private class TransfeAsyncTask extends AsyncTask<Void, Void, String> {
		private AppException ex;

		public TransfeAsyncTask() {
		}

		@Override
		protected String doInBackground(Void... arg0) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("assetNo", assetNo);
			try {
				ex = null;
				return WebServiceClient.getTurnInfo(map);
			} catch (AppException e) {
				// TODO Auto-generated catch block
				this.ex = e;
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			ProgressDialogUtil.closeProgress();
			if (null == result) {
				if (ex != null) {
					ex.makeToast(TransferOrderConfirmActivity.this);
				} else {
					MToast.showToast(TransferOrderConfirmActivity.this, "信息获取失败");
				}
				return;
			} else {
				try {
					JSONObject jo = new JSONObject(result);
					int state = jo.getInt("state");
					if (state == ConstantValues.LOGIN_ERROR) {
						String msgStr = jo.getString("msg");
						MDialog.showPsdErrorDialog(TransferOrderConfirmActivity.this, msgStr);
						return;
					} else if (state == 1) {
						String data = jo.getString("data");
						product = JsonUtils.toBean(data, new TypeToken<TranferBean_Data>() {
						}.getType());
						showData();
					} else {
						String msg = jo.getString("msg");
						MToast.showToast(TransferOrderConfirmActivity.this, StringUtil.isEmpty(msg) ? "信息获取失败" : msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
					MToast.showToast(TransferOrderConfirmActivity.this, "数据异常");
				}
			}
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
	}

	private void refreshData() {
		new AsyncTask<String, Integer, String>() {
			private AppException ex;

			@Override
			protected String doInBackground(String... params) {
				try {
					ex = null;
					Map<String, String> map = new HashMap<String, String>();
					map.put("assetNo", assetNo);
					map.put("salePrice", tempInputMoney);
					return WebServiceClient.refreshTurnInfo(map);
				} catch (AppException e) {
					this.ex = e;
				}
				return null;
			}

			@Override
			protected void onPostExecute(String result) {
				if (null == result) {
					if (ex != null) {
						ex.makeToast(TransferOrderConfirmActivity.this);
					} else {
						MToast.showToast(TransferOrderConfirmActivity.this, "刷新失败！");
					}
					return;
				} else {
					try {
						JSONObject jo = new JSONObject(result);
						int state = jo.getInt("state");
						if (state == ConstantValues.LOGIN_ERROR) {
							String msgStr = jo.getString("msg");
							MDialog.showPsdErrorDialog(TransferOrderConfirmActivity.this, msgStr);
							return;
						} else if (state == 1) {
							String data = jo.getString("data");
							TranferBean_Data info = JsonUtils.toBean(data, new TypeToken<TranferBean_Data>() {
							}.getType());
							refresh(info);
						} else {
							String msg = jo.getString("msg");
							MToast.showToast(TransferOrderConfirmActivity.this, StringUtil.isEmpty(msg) ? "刷新失败" : msg);
						}
					} catch (Exception e) {
						e.printStackTrace();
						MToast.showToast(TransferOrderConfirmActivity.this, "数据异常");
					}
				}
				super.onPostExecute(result);
			}

		}.execute();
	}

	private void refresh(TranferBean_Data info) {
		// TODO Auto-generated method stub
		// 我的收益对方收益 调用请求方法 获取接口
		my_money.setText(FloatUtil.toPercentage(Double.valueOf(info.getSalerRate()) / 100));
		you_money.setText(FloatUtil.toPercentage(Double.valueOf(info.getBuyerRate()) / 100));
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.big_price:
			if (hasFocus)// 获得焦点
			{
				if (!TextUtils.isEmpty(tempInputMoney)) {
					formatMoney(tempInputMoney);
					big_price.setText(tempInputMoney);
					Selection.setSelection((Spannable) big_price.getText(), big_price.getText().length());// 定位光标到末尾
				}
			} else// 失去焦点
			{
				if (!TextUtils.isEmpty(tempInputMoney)) {
					formatMoney(tempInputMoney);
					big_price.setText(FloatUtil.toStringSeparator2(Long.valueOf(tempInputMoney)));

				}
			}
			break;

		default:
			break;
		}
	}

}
