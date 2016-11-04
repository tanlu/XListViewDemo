package com.miduo.financialmanageclient.ui;

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
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.bean.BankCardInfo;
import com.miduo.financialmanageclient.bean.BankMapEntity;
import com.miduo.financialmanageclient.bean.ReturnMsg;
import com.miduo.financialmanageclient.bean.TranferBean_Data;
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

public class AffirmRolloutTwoActivity extends GesterSetBaseActivity implements
		OnClickListener {
	private int cardnum;
	private RelativeLayout rl_01;
	private LinearLayout card_info_layout;
	private TextView btn_txt;
	private TextView tv_01, tv_02, tv_03, tv_04, tv_05, big_price, small_price,
			msm;
	private TextView org_name_txt, address_txt, card_code_txt, name_txt;
	private ImageView left_img;
	private TextView name;
	private TextView mobile_txt;
	private TextView validate_code_btn;
	private TextView poundage;
	private TextView right_txt;
	private TranferBean_Data tranferBean_Data;

	private int maxTime = 120;
	private int time = maxTime;
	private TextView validate_code_txt;
	private BankMapEntity bankCardInfo;
	private Intent intent;
	private String tel;
	private ConfirmAsyncTask confirmAsyncTask;
		private TextView info_txt;
	private BankCardInfo bankMap;
	private ImageView org_img;
	private String money;
	private String assetNo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_affirm_rollout);

		init();
		initEvent();
		initDate();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("确认转让订单"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("确认转让订单"); //
		MobclickAgent.onPause(this);
	}

	/**
	 * 数据
	 */
	private void initDate() {
		intent = getIntent();

		money = intent.getStringExtra("money1");
		big_price.setText(money);
		// 汉字价格
		String moneyTow = intent.getStringExtra("moneyTow");
		small_price.setText(moneyTow);
		// 手续费
		String shouxufeiTow = intent.getStringExtra("shouxufeiTow");
		poundage.setText(shouxufeiTow);

		assetNo = intent.getStringExtra("assetNo");
		// 转让信息
		tranferBean_Data = (TranferBean_Data) intent
				.getSerializableExtra("infoObject");
		// 银行卡信息
		tv_01.setText(FloatUtil.toPercentage(Double.valueOf(tranferBean_Data
				.getProductRate())));
		tv_02.setText(FloatUtil.toTwoDianStringSeparator(Double
				.valueOf(tranferBean_Data.getHoldPrice())));
		tv_03.setText(FloatUtil.toTwoDianStringSeparator(Double
				.valueOf(tranferBean_Data.getBuyerTotalIn())));
		tv_04.setText(tranferBean_Data.getBuyerHoldDays() + "天");
		tv_05.setText(tranferBean_Data.getRedeemDate());
		info_txt.setText("转让金额将会在" + tranferBean_Data.getStartInterestDate()
				+ "个工作日内汇入您原订单的支付银行卡");
		name.setText(tranferBean_Data.getProductTitle());
		// 指定接收验证码手机
		
		tel = SharePrefUtil.getString(this, SharePrefUtil.CUR_USER_INFO,
				SharePrefUtil.ACCOUNT_MOBILE,
				getResources().getString(R.string.default_value));
		
		mobile_txt.setText(tel);
		bankCardInfo = tranferBean_Data.getBankMap();

		org_name_txt.setText(bankCardInfo.getBankName());
		address_txt.setText(bankCardInfo.getBranchBankAddress());
		card_code_txt.setText(bankCardInfo.getShortBankNo());
		name_txt.setText(bankCardInfo.getPayee());
		org_img.setImageResource(R.drawable.grey_point);
		if (!StringUtil.isEmpty(bankCardInfo.getBankIco())) {
			org_img.setTag(bankCardInfo.getBankIco());
			ImageDownLoadUtil
					.setImageBitmap(org_img, bankCardInfo.getBankIco());

		}

		cardnum = intent.getIntExtra("cardnum", 0);
		if (cardnum == 1 || cardnum == 0) {// 有指定账户
			right_txt.setVisibility(View.GONE);
		} else {

			right_txt.setVisibility(View.VISIBLE);
		}

	}

	/**
	 * 监听
	 */
	private void initEvent() {

		btn_txt.setOnClickListener(this);
		validate_code_btn.setOnClickListener(this);
		right_txt.setOnClickListener(this);
	}

	/**
	 * 初始化
	 */
	private void init() {

		TextView title_txt = (TextView) findViewById(R.id.title_txt);
		title_txt.setText("确认转让订单");
		left_img = (ImageView) findViewById(R.id.left_img);
		left_img.setOnClickListener(this);
		right_txt = (TextView) findViewById(R.id.right_txt);
		right_txt.setText("使用其他账户");

		rl_01 = (RelativeLayout) findViewById(R.id.rl_01);
		card_info_layout = (LinearLayout) findViewById(R.id.card_info_layout);
		btn_txt = (TextView) findViewById(R.id.btn_txt);

		tv_01 = (TextView) findViewById(R.id.tv_01);
		tv_02 = (TextView) findViewById(R.id.tv_02);
		tv_03 = (TextView) findViewById(R.id.tv_03);
		tv_04 = (TextView) findViewById(R.id.tv_04);
		tv_05 = (TextView) findViewById(R.id.tv_05);

		name = (TextView) findViewById(R.id.name);
		big_price = (TextView) findViewById(R.id.big_price);
		small_price = (TextView) findViewById(R.id.small_price);
		mobile_txt = (TextView) findViewById(R.id.mobile_txt);
		org_img = (ImageView) findViewById(R.id.org_img);

		org_name_txt = (TextView) findViewById(R.id.org_name_txt);
		address_txt = (TextView) findViewById(R.id.address_txt);
		card_code_txt = (TextView) findViewById(R.id.card_code_txt);
		name_txt = (TextView) findViewById(R.id.name_txt);
		// 验证码输入框
		validate_code_txt = (TextView) findViewById(R.id.validate_code_txt);
		// 验证码按钮
		validate_code_btn = (TextView) findViewById(R.id.validate_code_btn);
		info_txt = (TextView) findViewById(R.id.info_txt);
		poundage = (TextView) findViewById(R.id.poundage);
		time = maxTime;
		
		validate_code_txt.setBackgroundResource(R.drawable.button_bg_white);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 成功
		case R.id.btn_txt:
			//
			String code = validate_code_txt.getText().toString();
			if (StringUtil.isEmpty(code)) {
				MToast.showToast(this, "请输入短信验证码");
				return;
			}
			ProgressDialogUtil.showProgress(this);
			if (confirmAsyncTask != null) {
				confirmAsyncTask.cancel(true);
				confirmAsyncTask = null;
			}
			confirmAsyncTask = new ConfirmAsyncTask(code);
			confirmAsyncTask.execute();

			break;
		case R.id.left_img:
			finish();
			break;
		case R.id.right_txt:
			// 使用其他卡
			intent.setClass(this, BankCardLstActivity.class);
			startActivityForResult(intent, 1001);
			break;
		case R.id.validate_code_btn:
			// 将数据上传 手机号
			getCode(tel);
			break;
		default:
		}
	}

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
				Map<String, String> map = new HashMap<String, String>();
				map.put("mobile", tel);
				try {
					ex = null;
					return WebServiceClient.getTransferCode(map);
				} catch (AppException e) {
					this.ex = e;
				}
				return null;
			}

			@Override
			protected void onPostExecute(ReturnMsg result) {
				if (null == result) {
					if (ex != null) {
						ex.makeToast(AffirmRolloutTwoActivity.this);
					} else {
						MToast.showToast(AffirmRolloutTwoActivity.this,
								"获取验证码失败！");
					}
					time = 1;
					return;
				} else {
					if (result.getState()==ConstantValues.LOGIN_ERROR) {
					
						MDialog.showPsdErrorDialog(AffirmRolloutTwoActivity.this, result.getMsg());
						time = 1;
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
			BankCardInfo bankCardInfo=(BankCardInfo) data.getSerializableExtra("bank_card");
			org_name_txt.setText(bankCardInfo.getBankName());
			
			
			StringBuffer str = new StringBuffer();
			if (!StringUtil.isEmpty(bankCardInfo.getBankAddress())) {
				str.append(bankCardInfo.getBankAddress());
			}
			if (!StringUtil.isEmpty(bankCardInfo.getBranchBank())) {
				str.append(bankCardInfo.getBranchBank());
			}			
			String address = str.toString();
			address_txt.setText(StringUtil.showStringContent(address));
			card_code_txt.setText(bankCardInfo.getCardShortNo());
			name_txt.setText(bankCardInfo.getRealName());
			org_img.setImageResource(R.drawable.grey_point);
			if (!StringUtil.isEmpty(bankCardInfo.getSmallIco())) {
				org_img.setTag(bankCardInfo.getSmallIco());
				ImageDownLoadUtil
						.setImageBitmap(org_img, bankCardInfo.getSmallIco());

			}

		}
	}

	// 刷新edittext ui
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case 9999:
				time--;
				validate_code_btn.setText(getString(R.string.sendValidate)
						+ time + "秒");
				if (time == 0) {
					handler.sendEmptyMessage(1111);
				}
				break;
			case 1111:
				resetValidateTxt();
				break;
			case 2222:
				Bundle bundle = msg.getData();
				String info = bundle.getString("code_info");

				JSONObject jo = null;
				try {
					jo = new JSONObject(info);
					int state = jo.getInt("state");
					String msg1 = jo.getString("msg");

					if (state == ConstantValues.LOGIN_ERROR) {
						// tost输出
						MDialog.showPsdErrorDialog(AffirmRolloutTwoActivity.this, msg1);
						return;
					} else if(state==1){
						Intent intent =new Intent();
						intent.putExtra("interestDate", tranferBean_Data.getStartInterestDate());
						intent.setClass(AffirmRolloutTwoActivity.this,
								SuccessfulActivity.class);
						startActivity(intent);
					}
				}

				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			default:
				break;
			}
		}
	};


	private class ConfirmAsyncTask extends AsyncTask<Void, Void, String> {

		private String _code;
		private String _password;
		private AppException ex;

		public ConfirmAsyncTask(String code) {
			this._code = code;
		}

		@Override
		protected String doInBackground(Void... arg0) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("mobile", tel);
			map.put("salePrice", money.replace(",",""));
			map.put("smsCode", _code);
			map.put("assetNo", assetNo);
			map.put("bankcardId", tranferBean_Data.getBankcardId());
			try {
				ex = null;
				return WebServiceClient.ConfirmTransfer(map);
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
					ex.makeToast(AffirmRolloutTwoActivity.this);
				} else {
					MToast.showToast(AffirmRolloutTwoActivity.this, "转让失败");
				}
				return;
			} else {
				try {
					Intent intent = new Intent();
					JSONObject jo = new JSONObject(result);
					int state = jo.getInt("state");
					if(state==ConstantValues.LOGIN_ERROR)
					{
						String msg = jo.getString("msg");
						MDialog.showPsdErrorDialog(AffirmRolloutTwoActivity.this, msg);
					}
					else if(state == 1) {
						intent.setClass(AffirmRolloutTwoActivity.this,
								SuccessfulActivity.class);
						// intent.putExtra("redeem_desc",
						// redeemInfo.getRedeemDesc());
						startActivity(intent);
						finish();
					} 
				} catch (Exception e) {
					e.printStackTrace();
					MToast.showToast(AffirmRolloutTwoActivity.this, "数据异常");
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
