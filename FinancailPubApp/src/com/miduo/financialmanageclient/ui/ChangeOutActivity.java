package com.miduo.financialmanageclient.ui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.DialogBean;
import com.miduo.financialmanageclient.bean.MTransferOrderInfoVo;
import com.miduo.financialmanageclient.bean.ReturnMsg;
import com.miduo.financialmanageclient.bean.TransferListView;
import com.miduo.financialmanageclient.common.GetAssetDetail;
import com.miduo.financialmanageclient.listener.DialogEventListener;
import com.miduo.financialmanageclient.listener.GetCompleteListener;
import com.miduo.financialmanageclient.ui.adapter.TurnOrderAdapter;
import com.miduo.financialmanageclient.util.FloatUtil;
import com.miduo.financialmanageclient.util.JsonUtils;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

public class ChangeOutActivity extends GesterSetBaseActivity implements OnClickListener {

	private TextView name, bianhao, right_txt;
	private ListView listview;
	private ImageView left_img;
	private TextView price, big_price, zhekoujia, you_money, my_money;
	private Button confirmBtn;
	private TransferListView detail;
	private TransferCancelAsyncTask transferCancelAsyncTask;
	private TextView discount_type_txt, end_time_txt;
	private TurnOrderAdapter turnOrderAdapter;
	private List<MTransferOrderInfoVo> list = new ArrayList<MTransferOrderInfoVo>();
	private String assetNo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_changeout);
		initUI();
		initEvent();
		initDate();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("我的转出"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("我的转出"); //
		MobclickAgent.onPause(this);
	}

	private void initDate() {
		// TODO Auto-generated method stub
		String status = getIntent().getStringExtra("status");
		// 4转让中.6转出中
		if (status.equals("4")) {
			confirmBtn.setText("取消转让");
			confirmBtn.setEnabled(true);
			confirmBtn.setBackgroundResource(R.drawable.button_bg_blue);
		} else {
			confirmBtn.setText("不可取消，对方已付款");
			confirmBtn.setEnabled(false);
			confirmBtn.setBackgroundResource(R.drawable.button_bg_gray);
		}
		assetNo = getIntent().getStringExtra("assetNo");
		GetCompleteListener getCompleteListener = new GetCompleteListener() {

			@Override
			public void refreshData(String returnMsg) {
				// TODO Auto-generated method stub
				if (!StringUtil.isEmpty(returnMsg)) {
					try {
						detail = JsonUtils.toBean(returnMsg, new TypeToken<TransferListView>() {
						}.getType());
						initInfo();

					} catch (AppException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		};
		GetAssetDetail getAssetDetail = new GetAssetDetail(ChangeOutActivity.this, getCompleteListener, assetNo);
		getAssetDetail.getData();
	}

	/**
	 * 初始化信息
	 */
	private void initInfo() {
		// TODO Auto-generated method stub
		if(detail==null){
			return;
		}
		end_time_txt.setText(StringUtil.showStringContent(detail.getTransferDueTime()));
		name.setText(StringUtil.showStringContent(detail.getProName()));
		bianhao.setText(StringUtil.showStringContent(detail.getTransNo()));
		price.setText(FloatUtil.toTwoDianStringSeparator(detail.getTransferPrice()));
		if (detail.getTransferPrice() != null) {
			big_price.setText(StringUtil.number2CNMontrayUnit(new BigDecimal(detail.getTransferPrice())));
		} else {
			big_price.setText(getResources().getString(R.string.default_value));
		}
		// 0:溢;1:折
		if (detail.getDiscountType() == 0) {
			discount_type_txt.setText("溢价");
		} else {
			discount_type_txt.setText("已折价");
		}
		zhekoujia.setText(FloatUtil.toTwoDianStringSeparator(detail.getDiscountPrice()));
		if (detail.getSalerRate() != null) {
			my_money.setText(FloatUtil.toPercentage(detail.getSalerRate()/100));
		}else{
			my_money.setText("");
		}
		if (detail.getBuyerRate() != null) {
			you_money.setText(FloatUtil.toPercentage(detail.getBuyerRate()/100));
		}else{
			you_money.setText("");
		}
		list.clear();
		if (detail.getProList() != null && detail.getProList().size() > 0) {
			list.addAll(detail.getProList());
		}
		turnOrderAdapter.notifyDataSetChanged();
	}

	private void initEvent() {
		left_img.setOnClickListener(this);
		right_txt.setOnClickListener(this);
		confirmBtn.setOnClickListener(this);
	}

	private void initUI() {
		TextView title_txt = (TextView) findViewById(R.id.title_txt);
		title_txt.setText("我的转出");
		left_img = (ImageView) findViewById(R.id.left_img);
		right_txt = (TextView) findViewById(R.id.right_txt);
		right_txt.setText("产品资料");
		end_time_txt = (TextView) findViewById(R.id.end_time_txt);
		discount_type_txt = (TextView) findViewById(R.id.discount_type_txt);
		name = (TextView) findViewById(R.id.name);
		bianhao = (TextView) findViewById(R.id.bianhao);
		price = (TextView) findViewById(R.id.price);
		big_price = (TextView) findViewById(R.id.big_price);
		zhekoujia = (TextView) findViewById(R.id.zhekoujia);
		my_money = (TextView) findViewById(R.id.my_money);
		you_money = (TextView) findViewById(R.id.you_money);
		listview = (ListView) findViewById(R.id.listview);
		turnOrderAdapter = new TurnOrderAdapter(this, list);
		listview.setAdapter(turnOrderAdapter);
		confirmBtn = (Button) findViewById(R.id.button);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.left_img:
			finish();
			break;
		case R.id.right_txt:
			if (detail == null) {
				return;
			}
			Intent intent = new Intent(this, AgreementActivity.class);
			intent.putExtra("agree_type", 3);
			intent.putExtra("productId", String.valueOf(detail.getProductId()));
			startActivity(intent);
			break;
		case R.id.button:
			DialogBean data = new DialogBean();
			data.setContent("确定取消转让？");
			data.setCancel("取消");
			data.setSubmit("确定");
			DialogEventListener lister = new DialogEventListener() {

				@Override
				public void submit() {
					// TODO Auto-generated method stub
					ProgressDialogUtil.showProgress(ChangeOutActivity.this);
					if (transferCancelAsyncTask != null) {
						transferCancelAsyncTask.cancel(true);
						transferCancelAsyncTask = null;
					}
					transferCancelAsyncTask = new TransferCancelAsyncTask();
					transferCancelAsyncTask.execute();
				}

				@Override
				public void cancel() {
					// TODO Auto-generated method stub

				}
			};
			data.setDialogEvent(lister);
			MDialog.showDialog2(this, data);
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
			finish();
		}
	}

	private class TransferCancelAsyncTask extends AsyncTask<Void, Void, ReturnMsg> {

		private AppException ex;

		public TransferCancelAsyncTask() {
		}

		@Override
		protected ReturnMsg doInBackground(Void... arg0) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("orderNo", detail.getAssetNo());
			try {
				ex = null;
				return WebServiceClient.transferCancel(map);
			} catch (AppException e) {
				// TODO Auto-generated catch block
				this.ex = e;
			}
			return null;
		}

		@Override
		protected void onPostExecute(ReturnMsg result) {
			ProgressDialogUtil.closeProgress();
			if (null == result) {
				if (ex != null) {
					ex.makeToast(ChangeOutActivity.this);
				} else {
					MToast.showToast(ChangeOutActivity.this, "取消转让单失败！");
				}
				return;
			} else {
				if (result.getState() == ConstantValues.LOGIN_ERROR) {
					MDialog.showPsdErrorDialog(ChangeOutActivity.this, result.getMsg());
					return;
				} else if (result.getState() == 1) {
					MToast.showToast(ChangeOutActivity.this, "转让单取消成功");
					MyApplication.getInstance().home_index = 2;
					Intent intent = new Intent(ChangeOutActivity.this, HomeActivity.class);
					startActivity(intent);
					finish();
				} else {
					MToast.showToast(ChangeOutActivity.this,
							StringUtil.isEmpty(result.getMsg()) ? "取消转让单失败！" : result.getMsg());
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
