package com.miduo.financialmanageclient.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.AssetDetailInfoVo;
import com.miduo.financialmanageclient.bean.AssetDtailInfo;
import com.miduo.financialmanageclient.bean.BankCardInfo;
import com.miduo.financialmanageclient.bean.DialogBean;
import com.miduo.financialmanageclient.bean.RedeemInfo;
import com.miduo.financialmanageclient.bean.RedeemItemDetail;
import com.miduo.financialmanageclient.bean.UserTradeRecordsVo;
import com.miduo.financialmanageclient.common.CommonUtil;
import com.miduo.financialmanageclient.common.GetAssetDetail;
import com.miduo.financialmanageclient.common.GetRedeemInfo;
import com.miduo.financialmanageclient.listener.CheckIdentyListener;
import com.miduo.financialmanageclient.listener.DialogEventListener;
import com.miduo.financialmanageclient.listener.GetCompleteListener;
import com.miduo.financialmanageclient.listener.ReeemResultListener;
import com.miduo.financialmanageclient.ui.adapter.AssetDetailRecordAdapter;
import com.miduo.financialmanageclient.ui.adapter.HoldInfoGvAdapter;
import com.miduo.financialmanageclient.util.AddCalenderUtil;
import com.miduo.financialmanageclient.util.FloatUtil;
import com.miduo.financialmanageclient.util.JsonUtils;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.widget.LineGridView;
import com.miduo.financialmanageclient.ws.AppException;
import com.umeng.analytics.MobclickAgent;

public class HoldInfoActivity extends GesterSetBaseActivity implements OnClickListener {
	private LineGridView gv_home;
	private TextView title_txt, right_txt;
	private ListView lv;
	private TextView chanpin_name;
	private TextView fenshu;
	private ImageView left_img, shuhui_img;
	private LinearLayout shuhui;
	private TextView zdzh;
	private TextView shuhui_txt;
	private List<UserTradeRecordsVo> tradeRecords = new ArrayList<UserTradeRecordsVo>();
	private AssetDetailRecordAdapter assetAdapter;
	private AssetDtailInfo detail;
	private String status;
	private HoldInfoGvAdapter holdInfoGvAdapter;
	private List<AssetDetailInfoVo> detailInfoList = new ArrayList<AssetDetailInfoVo>();
	// 单个赎回信息
	private RedeemItemDetail singleRedeemInfo;
	private String assetNo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hold_info);

		init();
		initEvent();
		initDate();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("资产详情"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("资产详情"); //
		MobclickAgent.onPause(this);
	}

	private void initDate() {
		assetNo = getIntent().getStringExtra("assetNo");
		GetCompleteListener getCompleteListener = new GetCompleteListener() {

			@Override
			public void refreshData(String returnMsg) {
				// TODO Auto-generated method stub
				if (!StringUtil.isEmpty(returnMsg)) {
					try {
						detail = JsonUtils.toBean(returnMsg, new TypeToken<AssetDtailInfo>() {
						}.getType());
						initInfo();

					} catch (AppException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		};
		GetAssetDetail getAssetDetail = new GetAssetDetail(HoldInfoActivity.this, getCompleteListener, assetNo);
		getAssetDetail.getData();
	}

	private void initInfo() {
		if(detail==null){
			return;
		}
		chanpin_name.setText(StringUtil.showStringContent(detail.getProductName()));
		fenshu.setText(FloatUtil.toTwoDianStringSeparator(detail.getHoldShare()));
		tradeRecords.clear();
		if (detail.getTradeRecords() != null && detail.getTradeRecords().size() > 0) {
			tradeRecords.addAll(detail.getTradeRecords());
		}
		assetAdapter.notifyDataSetChanged();

		holdInfoGvAdapter = new HoldInfoGvAdapter(this, detailInfoList);
		gv_home.setAdapter(holdInfoGvAdapter);
		detailInfoList.clear();
		if (detail.getDetailInfoList() != null && detail.getDetailInfoList().size() > 0) {
			detailInfoList.addAll(detail.getDetailInfoList());
		}
		holdInfoGvAdapter.notifyDataSetChanged();
		status = getIntent().getStringExtra("status");
		if (status.equals("2")) {
			// 赎回中
			zdzh.setText("查看合同");
			zdzh.setTextColor(Color.parseColor("#2EA7E0"));
			zdzh.setBackgroundResource(R.drawable.button_bg_blue_line);

			shuhui_txt.setText("赎回中");
			shuhui_txt.setTextColor(Color.parseColor("#ffffff"));
			shuhui_img.setVisibility(View.GONE);
			shuhui.setBackgroundResource(R.drawable.button_bg_gray);
			shuhui.setEnabled(false);

		} else {
			// 已购买
			if (detail.isHaveBankcard()) {
				zdzh.setText("查看合同");
				zdzh.setBackgroundResource(R.drawable.button_bg_blue_line);
				zdzh.setTextColor(Color.parseColor("#2EA7E0"));
			} else {
				zdzh.setText("确认原支付卡");
				zdzh.setBackgroundResource(R.drawable.button_bg_blue);
				zdzh.setTextColor(Color.parseColor("#ffffff"));
			}
			if (detail.isCanRedeem()) {
				if (detail.isRedeem()) {
					// 赎回日
					shuhui_img.setVisibility(View.GONE);
					shuhui_txt.setText("赎回");
				} else {
					shuhui_img.setVisibility(View.VISIBLE);
					shuhui_txt.setText("赎回提醒");
				}
				shuhui_txt.setTextColor(Color.parseColor("#2EA7E0"));
				shuhui.setBackgroundResource(R.drawable.button_bg_blue_line);
				shuhui.setEnabled(true);
			} else {
				shuhui_txt.setText("到期自动赎回");
				shuhui_txt.setTextColor(Color.parseColor("#ffffff"));
				shuhui_img.setVisibility(View.GONE);
				shuhui.setBackgroundResource(R.drawable.button_bg_gray);
				shuhui.setEnabled(false);
			}
		}
	}

	private void initEvent() {
		right_txt.setOnClickListener(this);
		left_img.setOnClickListener(this);
		shuhui.setOnClickListener(this);
		zdzh.setOnClickListener(this);
	}

	private void init() {
		title_txt = (TextView) findViewById(R.id.title_txt);
		right_txt = (TextView) findViewById(R.id.right_txt);
		title_txt.setText("资产详情");
		right_txt.setText("产品资料");
		left_img = (ImageView) findViewById(R.id.left_img);
		chanpin_name = (TextView) findViewById(R.id.chanpin_name);
		fenshu = (TextView) findViewById(R.id.fenshu);

		gv_home = (LineGridView) findViewById(R.id.gv_home);
		holdInfoGvAdapter = new HoldInfoGvAdapter(this, detailInfoList);
		gv_home.setAdapter(holdInfoGvAdapter);
		lv = (ListView) findViewById(R.id.lv);
		assetAdapter = new AssetDetailRecordAdapter(this, tradeRecords);
		lv.setAdapter(assetAdapter);
		shuhui = (LinearLayout) findViewById(R.id.shuhui);
		shuhui_img = (ImageView) findViewById(R.id.shuhui_img);
		shuhui_txt = (TextView) findViewById(R.id.shuhui_txt);
		zdzh = (TextView) findViewById(R.id.zdzh);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.shuhui:
			if (detail.isRedeem()) {
				if (CommonUtil.checkIdentify(this)) {
					redeem();
				} else {
					CheckIdentyListener checkIdentyListener = new CheckIdentyListener() {

						@Override
						public void check(boolean flag) {
							// TODO Auto-generated method stub
							if (flag) {
								redeem();
							} else {
								ProgressDialogUtil.closeProgress();
								Intent intent = new Intent();
								intent = new Intent(HoldInfoActivity.this, IdentityActivity.class);
								startActivityForResult(intent, 1005);
							}
						}
					};
					ProgressDialogUtil.showProgress(this);
					CommonUtil.identy(this, checkIdentyListener);
				}
			} else {
				// 赎回
				String message2 = "是否在" + detail.getProductName() + "的最近赎回日" + detail.getStartRedeemDate() + "设置提醒？";

				DialogBean data = new DialogBean();
				data.setContent(message2);
				data.setCancel("下次再说");
				data.setSubmit("立即设置");
				DialogEventListener lister = new DialogEventListener() {

					@Override
					public void submit() {
						// TODO Auto-generated method stub
						AddCalenderUtil.add(HoldInfoActivity.this, "米多提醒", detail.getProductName() + "赎回日期即将到达",
								detail.getStartRedeemDate(), detail.getEndRedeemDate(), detail.getAlarmClockTime());
					}

					@Override
					public void cancel() {
						// TODO Auto-generated method stub

					}
				};
				data.setDialogEvent(lister);
				MDialog.showDialog2(this, data);
			}
			break;
		case R.id.zdzh:
			// 指定账号
			if (status.equals("1") && !detail.isHaveBankcard()) {
				// 指定账号
				DialogBean data = new DialogBean();
				data.setTitle("缺少收款账号信息");
				data.setContent("为了在产品到期/赎回/转让时顺利收款，请尽快指定此订单的收款账号。");
				data.setCancel("取消");
				data.setSubmit("确定");
				DialogEventListener lister = new DialogEventListener() {

					@Override
					public void submit() {
						// TODO Auto-generated method stub
						if (detail.isUserhasBcList()) {
							startActivityForResult(new Intent(HoldInfoActivity.this, BankCardLstActivity.class), 1001);
						} else {
							startActivityForResult(new Intent(HoldInfoActivity.this, AddReceiveBankCardActivity.class),
									1001);
						}

					}

					@Override
					public void cancel() {
						// TODO Auto-generated method stub

					}
				};
				data.setDialogEvent(lister);
				MDialog.showDialog2(this, data);
			} else {
				String url = ConstantValues.ROOT_IP_BARN + "/agreement/" + assetNo + ".pdf?_cus_sessionid=" + MyApplication.cus_sessionid;
				CommonUtil.downPdf(HoldInfoActivity.this, url);
			}
			break;
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
		default:
			break;
		}
	}

	private void redeem() {
		ReeemResultListener geeemResultListener = new ReeemResultListener() {
			@Override
			public void refreshData(RedeemInfo redeemInfo) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				if (redeemInfo.isBatch()) {
					// 批量赎回
					intent = new Intent(HoldInfoActivity.this, BatchRedeemOrderSelActivity.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("redeemInfo", redeemInfo);
					bundle.putInt("asset_id", detail.getAssetId());
					intent.putExtra("redeemInfo", redeemInfo);
					intent.putExtras(bundle);
					startActivity(intent);
				} else {
					// 单个赎回
					List<RedeemItemDetail> list = redeemInfo.getList();
					if (list != null && list.size() > 0) {
						singleRedeemInfo = list.get(0);
						if (singleRedeemInfo.isHasbank()) {
							// 订单有绑定的卡
							intent = new Intent(HoldInfoActivity.this, RedeemInfoActivity.class);
							Bundle bundle = new Bundle();
							bundle.putBoolean("hasbank", true);
							bundle.putSerializable("redeem_info", singleRedeemInfo);
							intent.putExtras(bundle);
							startActivity(intent);
						} else {
							if (redeemInfo.getBankCount() == 0) {
								intent = new Intent(HoldInfoActivity.this, AddReceiveBankCardActivity.class);
								startActivityForResult(intent, 1004);
							} else {
								intent = new Intent(HoldInfoActivity.this, BankCardLstActivity.class);
								startActivityForResult(intent, 1004);
							}
						}
					}
				}

			}
		};
		GetRedeemInfo getRedeemInfo = new GetRedeemInfo(HoldInfoActivity.this, geeemResultListener, detail.getAssetId());
		getRedeemInfo.getData();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 1001:
			if (resultCode == RESULT_OK) {
				zdzh.setText("查看合同");
				zdzh.setTextColor(Color.parseColor("#2EA7E0"));
				zdzh.setBackgroundResource(R.drawable.button_bg_blue_line);
				detail.setHaveBankcard(true);
			}
			break;
		case 1004:
			if (resultCode == RESULT_OK) {
				zdzh.setText("查看合同");
				zdzh.setTextColor(Color.parseColor("#2EA7E0"));
				zdzh.setBackgroundResource(R.drawable.button_bg_blue_line);
				detail.setHaveBankcard(true);
				Intent intent = new Intent(HoldInfoActivity.this, RedeemInfoActivity.class);
				Bundle bundle = new Bundle();
				bundle.putBoolean("hasbank", false);
				bundle.putSerializable("redeem_info", singleRedeemInfo);
				BankCardInfo cardInfo = (BankCardInfo) data.getSerializableExtra("bank_card");
				bundle.putSerializable("bank_card", cardInfo);
				intent.putExtras(bundle);
				startActivity(intent);
			}
			break;

		}

	}

}
