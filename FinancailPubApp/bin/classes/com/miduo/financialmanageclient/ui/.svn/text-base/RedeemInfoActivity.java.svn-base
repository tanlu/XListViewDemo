package com.miduo.financialmanageclient.ui;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.bean.BankCardInfo;
import com.miduo.financialmanageclient.bean.RedeemItemDetail;
import com.miduo.financialmanageclient.bean.ReturnMsg;
import com.miduo.financialmanageclient.util.FloatUtil;
import com.miduo.financialmanageclient.util.ImageDownLoadUtil;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.SharePrefUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

public class RedeemInfoActivity extends BaseActivity implements OnClickListener {
	private TextView commitTxt, validate_code_btn;
	private ImageView leftImg;
	private TextView titleTxt;
	private TextView rightTxt;
	private TextView product_name_txt, number_amount_txt, china_amount_txt, org_name_txt, address_txt, card_code_txt,
			name_txt, mobile_txt;
	private EditText validate_code_txt;
	private ImageView org_img;
	private TextView agree_info_txt;
	private TextView info_txt;
	private int maxTime = 120;
	private int time = maxTime;
	private String tel;
	private BankCardInfo cardInfo;
	private boolean hasBank;
	private RedeemItemDetail redeemInfo;
	private SingleConfirmAsyncTask singleConfirmAsyncTask;
	private TextView amount_title_txt, number_amount_txt_1, number_amount_unit_txt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_redeem_info);
		initUi();
		initEvent();
		initData();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("确认赎回信息"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("确认赎回信息"); //
		MobclickAgent.onPause(this);
	}

	private void initUi() {
		// TODO Auto-generated method stub
		leftImg = (ImageView) findViewById(R.id.left_img);
		titleTxt = (TextView) findViewById(R.id.title_txt);
		rightTxt = (TextView) findViewById(R.id.right_txt);
		titleTxt.setText("确认赎回信息");
		rightTxt.setText("使用其他账号");
		product_name_txt = (TextView) findViewById(R.id.product_name_txt);
		number_amount_txt = (TextView) findViewById(R.id.number_amount_txt);
		china_amount_txt = (TextView) findViewById(R.id.china_amount_txt);
		org_name_txt = (TextView) findViewById(R.id.org_name_txt);
		address_txt = (TextView) findViewById(R.id.address_txt);
		card_code_txt = (TextView) findViewById(R.id.card_code_txt);
		name_txt = (TextView) findViewById(R.id.name_txt);
		mobile_txt = (TextView) findViewById(R.id.mobile_txt);
		org_img = (ImageView) findViewById(R.id.org_img);
		validate_code_txt = (EditText) findViewById(R.id.validate_code_txt);
		validate_code_btn = (TextView) findViewById(R.id.validate_code_btn);
		commitTxt = (TextView) findViewById(R.id.btn_txt);
		agree_info_txt = (TextView) findViewById(R.id.agree_info_txt);
		info_txt = (TextView) findViewById(R.id.info_txt);
		amount_title_txt = (TextView) findViewById(R.id.amount_title_txt);
		number_amount_txt_1 = (TextView) findViewById(R.id.number_amount_txt_1);
		number_amount_unit_txt = (TextView) findViewById(R.id.number_amount_unit_txt);
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		commitTxt.setOnClickListener(this);
		leftImg.setOnClickListener(this);
		rightTxt.setOnClickListener(this);
		validate_code_btn.setOnClickListener(this);
		agree_info_txt.setOnClickListener(this);
	}

	private void initData() {
		// TODO Auto-generated method stub
		Bundle bundle = getIntent().getExtras();

		hasBank = bundle.getBoolean("hasbank");
		redeemInfo = (RedeemItemDetail) bundle.getSerializable("redeem_info");
		if (redeemInfo.isByShare()) {
			amount_title_txt.setText("赎回份额");
			number_amount_txt_1.setVisibility(View.GONE);
			number_amount_unit_txt.setVisibility(View.VISIBLE);
		} else {
			amount_title_txt.setText("赎回金额");
			number_amount_txt_1.setVisibility(View.VISIBLE);
			number_amount_unit_txt.setVisibility(View.GONE);
		}

		product_name_txt.setText(StringUtil.showStringContent(redeemInfo.getProdcutTitle()));
		number_amount_txt.setText(FloatUtil.toTwoDianStringSeparator(redeemInfo.getAmount()));
		if (redeemInfo.getAmount() == null) {
			china_amount_txt.setText(getResources().getString(R.string.default_value));
		} else {
			china_amount_txt.setText(StringUtil.number2CNMontrayUnit(new BigDecimal(redeemInfo.getAmount())));
		}
		tel = SharePrefUtil.getString(this, SharePrefUtil.MIDUO_PUB_INFO, SharePrefUtil.ACCOUNT_MOBILE, getResources()
				.getString(R.string.default_value));
		mobile_txt.setText(tel);
		info_txt.setText("赎回金额将会在" + StringUtil.showStringContent(redeemInfo.getRedeemDesc()) + "工作日内汇入您原订单的支付银行卡");
		org_img.setImageResource(R.drawable.grey_point);
		if (hasBank) {// 有指定账户
			rightTxt.setVisibility(View.GONE);
			org_name_txt.setText(StringUtil.showStringContent(redeemInfo.getBankName()));
			address_txt.setText(StringUtil.showStringContent(redeemInfo.getBranchBankAddress()));
			card_code_txt.setText(StringUtil.showStringContent(redeemInfo.getShortBankNo()));
			name_txt.setText(StringUtil.showStringContent(redeemInfo.getPayee()));
			if (!StringUtil.isEmpty(redeemInfo.getBankIco())) {
				org_img.setTag(redeemInfo.getBankIco());
				ImageDownLoadUtil.setImageBitmap(org_img, redeemInfo.getBankIco());
			}
		} else {
			rightTxt.setVisibility(View.VISIBLE);
			cardInfo = (BankCardInfo) bundle.getSerializable("bank_card");
			org_name_txt.setText(StringUtil.showStringContent(cardInfo.getBankName()));
			StringBuffer str = new StringBuffer();
			if (!StringUtil.isEmpty(cardInfo.getBankAddress())) {
				str.append(cardInfo.getBankAddress());
			}
			if (!StringUtil.isEmpty(cardInfo.getBranchBank())) {
				str.append(cardInfo.getBranchBank());
			}			
			String address = str.toString();
			address_txt.setText(StringUtil.showStringContent(address));
			card_code_txt.setText(StringUtil.showStringContent(cardInfo.getCardShortNo()));
			name_txt.setText(StringUtil.showStringContent(cardInfo.getRealName()));
			if (!StringUtil.isEmpty(cardInfo.getSmallIco())) {
				org_img.setTag(cardInfo.getSmallIco());
				ImageDownLoadUtil.setImageBitmap(org_img, cardInfo.getSmallIco());
			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.btn_txt:
			String code = validate_code_txt.getText().toString();
			if (StringUtil.isEmpty(code)) {
				MToast.showToast(this, "请输入短信验证码");
				return;
			}
			ProgressDialogUtil.showProgress(RedeemInfoActivity.this);
			if (singleConfirmAsyncTask != null) {
				singleConfirmAsyncTask.cancel(true);
				singleConfirmAsyncTask = null;
			}
			singleConfirmAsyncTask = new SingleConfirmAsyncTask(code);
			singleConfirmAsyncTask.execute();
			break;
		case R.id.left_img:
			finish();
			break;
		case R.id.right_txt:
			intent.setClass(RedeemInfoActivity.this, BankCardLstActivity.class);
			startActivityForResult(intent, 1001);
			break;
		case R.id.validate_code_btn:
			getCode(tel);
			break;
		case R.id.agree_info_txt:
			break;
		default:
			break;
		}
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case 9999:
				time--;
				validate_code_btn.setText(getString(R.string.sendValidate) + time + "秒");
				if (time == 0) {
					handler.sendEmptyMessage(1111);
				}
				break;
			case 1111:
				resetValidateTxt();
				break;
			default:
				break;
			}
		}
	};

	private void resetValidateTxt() {
		validate_code_btn.setText(R.string.gainValidate);
		validate_code_btn.setEnabled(true);
		validate_code_btn.setBackgroundResource(R.drawable.button_bg_blue);
	}

	private void getCode(String mobile) {
		time = maxTime;
		validate_code_btn.setEnabled(false);
		validate_code_btn.setBackgroundResource(R.drawable.button_bg_gray);
		new Thread() {
			public void run() {
				while (time > 0) {
					handler.sendEmptyMessage(9999);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
		new AsyncTask<String, Integer, ReturnMsg>() {
			private AppException ex;

			@Override
			protected ReturnMsg doInBackground(String... params) {
				try {
					ex = null;
					return WebServiceClient.GetRedeemSms();
				} catch (AppException e) {
					this.ex = e;
				}
				return null;
			}

			@Override
			protected void onPostExecute(ReturnMsg result) {
				if (null == result) {
					if (ex != null) {
						ex.makeToast(RedeemInfoActivity.this);
					} else {
						MToast.showToast(RedeemInfoActivity.this, "获取验证码失败！");
					}
					time = 1;
					return;
				} else {
					if (result.getState() != 1) {
						if (result.getState() == ConstantValues.LOGIN_ERROR) {
							MDialog.showPsdErrorDialog(RedeemInfoActivity.this, result.getMsg());
							return;
						} else {
							MToast.showToast(RedeemInfoActivity.this, StringUtil.isEmpty(result.getMsg()) ? "获取验证码失败！"
									: result.getMsg());
							time = 1;
						}
					}
				}
				super.onPostExecute(result);
			}

		}.execute(mobile);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			// 刷新卡信息
			cardInfo = (BankCardInfo) data.getSerializableExtra("bank_card");
			org_name_txt.setText(StringUtil.showStringContent(cardInfo.getBankName()));
			StringBuffer str = new StringBuffer();
			if (!StringUtil.isEmpty(cardInfo.getBankAddress())) {
				str.append(cardInfo.getBankAddress());
			}
			if (!StringUtil.isEmpty(cardInfo.getBranchBank())) {
				str.append(cardInfo.getBranchBank());
			}			
			String address = str.toString();
			address_txt.setText(StringUtil.showStringContent(address));
			card_code_txt.setText(StringUtil.showStringContent(cardInfo.getCardShortNo()));
			name_txt.setText(StringUtil.showStringContent(cardInfo.getRealName()));
			if (!StringUtil.isEmpty(cardInfo.getSmallIco())) {
				org_img.setTag(cardInfo.getSmallIco());
				ImageDownLoadUtil.setImageBitmap(org_img, cardInfo.getSmallIco());
			}
		}
	}

	private class SingleConfirmAsyncTask extends AsyncTask<Void, Void, String> {

		private String _code;
		private String _password;
		private AppException ex;

		public SingleConfirmAsyncTask(String code) {
			this._code = code;
		}

		@Override
		protected String doInBackground(Void... arg0) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("assetId", String.valueOf(redeemInfo.getAssetId()));
			map.put("mobile", tel);
			map.put("smsCode", _code);
			map.put("source", "android");
			if (hasBank) {
				map.put("bankId", String.valueOf(redeemInfo.getBankId()));
			} else {
				map.put("bankId", String.valueOf(cardInfo.getId()));
			}
			try {
				ex = null;
				return WebServiceClient.ConfirmSingleRedeem(map);
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
					ex.makeToast(RedeemInfoActivity.this);
				} else {
					MToast.showToast(RedeemInfoActivity.this, "赎回失败");
				}
				return;
			} else {
				try {
					Intent intent = new Intent();
					JSONObject jo = new JSONObject(result);
					int state = jo.getInt("state");
					if (state == 1) {
						intent.setClass(RedeemInfoActivity.this, RedeemSuccessActivity.class);
						intent.putExtra("redeem_desc", redeemInfo.getRedeemDesc());
						startActivity(intent);
						finish();
					} else if (state == 2) {
						intent.setClass(RedeemInfoActivity.this, RedeemFailActivity.class);
						startActivity(intent);
						finish();
					} else {
						String msg = jo.getString("msg");
						MToast.showToast(RedeemInfoActivity.this, StringUtil.isEmpty(msg) ? "赎回失败" : msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
					MToast.showToast(RedeemInfoActivity.this, "数据异常");
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
