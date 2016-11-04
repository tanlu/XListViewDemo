package com.miduo.financialmanageclient.ui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.miduo.financialmanageclient.bean.BatchRedeem;
import com.miduo.financialmanageclient.bean.BatchRedeemInfo;
import com.miduo.financialmanageclient.bean.RedeemItemDetail;
import com.miduo.financialmanageclient.bean.ReturnMsg;
import com.miduo.financialmanageclient.util.FloatUtil;
import com.miduo.financialmanageclient.util.JsonUtils;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.SharePrefUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

public class BatchRedeemConfirmActivity extends GesterSetBaseActivity implements OnClickListener {
	private TextView commitTxt, validate_code_btn, order_count_txt;
	private ImageView leftImg;
	private TextView titleTxt;
	private TextView rightTxt;
	private TextView number_amount_txt, china_amount_txt, mobile_txt;
	private EditText validate_code_txt;
	private int maxTime = 120;
	private int time = maxTime;
	private String tel;
	private BatchConfirmAsyncTask batchConfirmAsyncTask;
	private List<RedeemItemDetail> tempLst;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_batch_redeem_confirm);

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
		commitTxt = (TextView) findViewById(R.id.btn_txt);
		validate_code_txt = (EditText) findViewById(R.id.validate_code_txt);
		validate_code_btn = (TextView) findViewById(R.id.validate_code_btn);
		order_count_txt = (TextView) findViewById(R.id.order_count_txt);
		leftImg = (ImageView) findViewById(R.id.left_img);
		titleTxt = (TextView) findViewById(R.id.title_txt);
		rightTxt = (TextView) findViewById(R.id.right_txt);
		titleTxt.setText("确认赎回信息");
		rightTxt.setVisibility(View.GONE);
		number_amount_txt = (TextView) findViewById(R.id.number_amount_txt);
		china_amount_txt = (TextView) findViewById(R.id.china_amount_txt);
		mobile_txt = (TextView) findViewById(R.id.mobile_txt);
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		commitTxt.setOnClickListener(this);
		leftImg.setOnClickListener(this);
		validate_code_btn.setOnClickListener(this);
	}

	private void initData() {
		// TODO Auto-generated method stub
		tempLst = (List<RedeemItemDetail>) getIntent().getSerializableExtra("redeem_lst");
		double amount = getIntent().getDoubleExtra("sum_amout", 0.0d);
		if (tempLst == null || tempLst.size() == 0) {
			return;
		}
		number_amount_txt.setText(FloatUtil.toTwoDianStringSeparator(amount));
		china_amount_txt.setText(StringUtil.number2CNMontrayUnit(new BigDecimal(amount)));
		order_count_txt.setText(String.valueOf(tempLst.size()));
		tel = SharePrefUtil.getString(this, SharePrefUtil.MIDUO_PUB_INFO, SharePrefUtil.ACCOUNT_MOBILE, getResources()
				.getString(R.string.default_value));
		mobile_txt.setText(tel);
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
			ProgressDialogUtil.showProgress(BatchRedeemConfirmActivity.this);
			if (batchConfirmAsyncTask != null) {
				batchConfirmAsyncTask.cancel(true);
				batchConfirmAsyncTask = null;
			}
			batchConfirmAsyncTask = new BatchConfirmAsyncTask(code);
			batchConfirmAsyncTask.execute();
			break;
		case R.id.left_img:
			finish();
			break;
		case R.id.validate_code_btn:
			getCode(tel);
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
						ex.makeToast(BatchRedeemConfirmActivity.this);
					} else {
						MToast.showToast(BatchRedeemConfirmActivity.this, "获取验证码失败！");
					}
					time = 1;
					return;
				} else {
					if (result.getState() == ConstantValues.LOGIN_ERROR) {
						String msgStr = result.getMsg();
						MDialog.showPsdErrorDialog(BatchRedeemConfirmActivity.this, msgStr);
						return;
					} else if (result.getState() != 1) {
						MToast.showToast(BatchRedeemConfirmActivity.this,
								StringUtil.isEmpty(result.getMsg()) ? "获取验证码失败！" : result.getMsg());
						time = 1;
					}
				}
				super.onPostExecute(result);
			}

		}.execute(mobile);
	}

	private class BatchConfirmAsyncTask extends AsyncTask<Void, Void, String> {

		private String _code;
		private String _password;
		private AppException ex;

		public BatchConfirmAsyncTask(String code) {
			this._code = code;
		}

		@Override
		protected String doInBackground(Void... arg0) {
			BatchRedeem info = new BatchRedeem();
			info.setMobile(tel);
			info.setSmsCode(_code);
			info.setSource("android");
			List<BatchRedeemInfo> list = new ArrayList<BatchRedeemInfo>();
			for (RedeemItemDetail item : tempLst) {
				BatchRedeemInfo subItem = new BatchRedeemInfo();
				subItem.setAssetId(item.getAssetId());
				subItem.setBankId(item.getBankId());
				list.add(subItem);
			}
			info.setList(list);
			Map<String, String> map = new HashMap<String, String>();
			try {
				map.put("batch", JsonUtils.toString(info));
				ex = null;
				return WebServiceClient.ConfirmBatchRedeem(map);
			} catch (AppException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			ProgressDialogUtil.closeProgress();
			if (null == result) {
				if (ex != null) {
					ex.makeToast(BatchRedeemConfirmActivity.this);
				} else {
					MToast.showToast(BatchRedeemConfirmActivity.this, "赎回失败");
				}
				return;
			} else {
				try {
					Intent intent = new Intent();
					JSONObject jo = new JSONObject(result);
					int state = jo.getInt("state");
					if (state == 1) {
						intent.setClass(BatchRedeemConfirmActivity.this, RedeemSuccessActivity.class);
						startActivity(intent);
						finish();
					} else {
						String msg = jo.getString("msg");
						MToast.showToast(BatchRedeemConfirmActivity.this, StringUtil.isEmpty(msg) ? "赎回失败" : msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
					MToast.showToast(BatchRedeemConfirmActivity.this, "数据异常");
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
