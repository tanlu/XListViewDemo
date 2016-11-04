package com.miduo.financialmanageclient.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import com.miduo.financialmanageclient.util.JsonUtils;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.widget.LineGridView;
import com.miduo.financialmanageclient.ws.AppException;
import com.umeng.analytics.MobclickAgent;

/**
 * 固收详情
 * 
 * @author de
 * 
 */
public class HoldFixedActivity extends GesterSetBaseActivity implements OnClickListener {
	private LineGridView gv_home;

	private ListView lv;
	private ImageView left_img;
	private TextView title_txt, right_txt, chanpin_name, daoqiri;
	private TextView zdsk;
	private LinearLayout shuhui, zhuanrang;
	private View space_view;
	private ImageView shuhui_img, zhuanrang_img;
	private TextView shuhui_txt, zhuanrang_txt;
	private List<UserTradeRecordsVo> tradeRecords = new ArrayList<UserTradeRecordsVo>();
	private AssetDetailRecordAdapter assetAdapter;
	private AssetDtailInfo detail;
	private String status;
	private HoldInfoGvAdapter holdInfoGvAdapter;
	private List<AssetDetailInfoVo> detailInfoList = new ArrayList<AssetDetailInfoVo>();
	private int cardnum = 0;
	private String assetNo;
	// 单个赎回信息
	private RedeemItemDetail singleRedeemInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hold_fixed);
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
		GetAssetDetail getAssetDetail = new GetAssetDetail(HoldFixedActivity.this, getCompleteListener, assetNo);
		getAssetDetail.getData();
	}

	private void initInfo() {
		// TODO Auto-generated method stub
		if(detail==null){
			return;
		}
		chanpin_name.setText(StringUtil.showStringContent(detail.getProductName()));
		daoqiri.setText(StringUtil.showStringContent(detail.getProductEndDate()));
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
			zdsk.setText("查看合同");
			zdsk.setTextColor(Color.parseColor("#2EA7E0"));
			zdsk.setBackgroundResource(R.drawable.button_bg_blue_line);

			shuhui_txt.setText("赎回中");
			shuhui_txt.setTextColor(Color.parseColor("#ffffff"));
			shuhui_img.setVisibility(View.GONE);
			shuhui.setBackgroundResource(R.drawable.button_bg_gray);
			zhuanrang_img.setVisibility(View.GONE);
			zhuanrang_txt.setText("转让");
			zhuanrang_txt.setTextColor(Color.parseColor("#ffffff"));
			zhuanrang.setBackgroundResource(R.drawable.button_bg_gray);

			shuhui.setEnabled(false);
			zhuanrang.setEnabled(false);

		} else {
			// 已购买
			if (detail.isHaveBankcard()) {
				zdsk.setText("查看合同");
				zdsk.setBackgroundResource(R.drawable.button_bg_blue_line);
				zdsk.setTextColor(Color.parseColor("#2EA7E0"));
			} else {
				zdsk.setText("确认原支付卡");
				zdsk.setBackgroundResource(R.drawable.button_bg_blue);
				zdsk.setTextColor(Color.parseColor("#ffffff"));
			}
			if (detail.isCanRedeem() && detail.isCanTransfer()) {
				// 可以赎回，可以转让 （赎回转让合同三个按钮）
				if (detail.isRedeem()) {
					// 赎回日
					shuhui_img.setVisibility(View.GONE);
					shuhui_txt.setText("赎回");
				} else {
					shuhui_img.setVisibility(View.VISIBLE);
					shuhui_txt.setText("赎回提醒");
				}

				if (detail.isTransfer()) {
					zhuanrang_img.setVisibility(View.GONE);
					zhuanrang_txt.setText("转让");
				} else {
					zhuanrang_img.setVisibility(View.VISIBLE);
					zhuanrang_txt.setText("转让提醒");
				}

				shuhui_txt.setTextColor(Color.parseColor("#2EA7E0"));
				zhuanrang_txt.setTextColor(Color.parseColor("#2EA7E0"));
				shuhui.setBackgroundResource(R.drawable.button_bg_blue_line);
				zhuanrang.setBackgroundResource(R.drawable.button_bg_blue_line);
				shuhui.setEnabled(true);
				zhuanrang.setEnabled(true);
			} else if (detail.isCanRedeem() && !detail.isCanTransfer()) {
				// 可以赎回，不可转让 （赎回合同三个按钮）
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
				zhuanrang.setVisibility(View.GONE);
				space_view.setVisibility(View.GONE);
			} else if (!detail.isCanRedeem() && !detail.isCanTransfer()) {
				// 不可赎回不可转让（到期自动赎回，合同两个钮）
				shuhui_txt.setText("到期自动赎回");
				shuhui_txt.setTextColor(Color.parseColor("#ffffff"));
				shuhui_img.setVisibility(View.GONE);
				shuhui.setBackgroundResource(R.drawable.button_bg_gray);
				shuhui.setEnabled(false);

				zhuanrang.setVisibility(View.GONE);
				space_view.setVisibility(View.GONE);
			} else if (!detail.isCanRedeem() && detail.isCanTransfer()) {
				// 不可赎回可转让（转让，合同两个钮）
				if (detail.isTransfer()) {
					zhuanrang_img.setVisibility(View.GONE);
					zhuanrang_txt.setText("转让");
				} else {
					zhuanrang_img.setVisibility(View.VISIBLE);
					zhuanrang_txt.setText("转让提醒");
				}
				zhuanrang_txt.setTextColor(Color.parseColor("#2EA7E0"));
				shuhui.setBackgroundResource(R.drawable.button_bg_blue_line);
				zhuanrang.setEnabled(true);
				shuhui.setVisibility(View.GONE);
				space_view.setVisibility(View.GONE);
			}

		}
	}

	private void initEvent() {
		right_txt.setOnClickListener(this);
		left_img.setOnClickListener(this);
		shuhui.setOnClickListener(this);
		zhuanrang.setOnClickListener(this);
		zdsk.setOnClickListener(this);
	}

	private void init() {
		left_img = (ImageView) findViewById(R.id.left_img);
		right_txt = (TextView) findViewById(R.id.right_txt);
		title_txt = (TextView) findViewById(R.id.title_txt);
		title_txt.setText("资产详情");
		right_txt.setText("产品资料");
		chanpin_name = (TextView) findViewById(R.id.chanpin_name);
		gv_home = (LineGridView) findViewById(R.id.gv_home);
		holdInfoGvAdapter = new HoldInfoGvAdapter(this, detailInfoList);
		gv_home.setAdapter(holdInfoGvAdapter);
		lv = (ListView) findViewById(R.id.lv);
		assetAdapter = new AssetDetailRecordAdapter(this, tradeRecords);
		lv.setAdapter(assetAdapter);
		daoqiri = (TextView) findViewById(R.id.daoqiri);

		shuhui = (LinearLayout) findViewById(R.id.shuhui);
		zhuanrang = (LinearLayout) findViewById(R.id.zhuanrang);
		space_view = (View) findViewById(R.id.space_view);
		zdsk = (TextView) findViewById(R.id.zdsk);
		shuhui_img = (ImageView) findViewById(R.id.shuhui_img);
		zhuanrang_img = (ImageView) findViewById(R.id.zhuanrang_img);
		shuhui_txt = (TextView) findViewById(R.id.shuhui_txt);
		zhuanrang_txt = (TextView) findViewById(R.id.zhuanrang_txt);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.right_txt:
			/* startActivity(new Intent(this, HoldInfoActivity.class)); */
			if (detail == null) {
				return;
			}
			intent = new Intent(this, AgreementActivity.class);
			intent.putExtra("agree_type", 3);
			intent.putExtra("productId", String.valueOf(detail.getProductId()));
			startActivity(intent);
			break;
		case R.id.left_img:
			finish();
			break;
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
								intent = new Intent(HoldFixedActivity.this, IdentityActivity.class);
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
						Log.i("----------", "立即设置");
						AddCalenderUtil.add(HoldFixedActivity.this, "米多提醒", detail.getProductName() + "赎回日期即将到达",
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
		case R.id.zhuanrang:
			if (detail.isTransfer()) {
				if (CommonUtil.checkIdentify(this)) {
					turnOff();
				} else {
					CheckIdentyListener checkIdentyListener = new CheckIdentyListener() {

						@Override
						public void check(boolean flag) {
							// TODO Auto-generated method stub
							ProgressDialogUtil.closeProgress();
							if (flag) {
								turnOff();
							} else {
								Intent intent = new Intent();
								intent = new Intent(HoldFixedActivity.this, IdentityActivity.class);
								startActivityForResult(intent, 1006);
							}
						}
					};
					ProgressDialogUtil.showProgress(this);
					CommonUtil.identy(this, checkIdentyListener);
				}
			} else {
				// 转让
				String message3 = "你须持有订单满30天才能转让，最近可转让日为" + detail.getTransferDate() + "是否设置提醒？";
				DialogBean data = new DialogBean();
				data.setContent(message3);
				data.setCancel("下次再说");
				data.setSubmit("立即设置");
				DialogEventListener lister = new DialogEventListener() {

					@Override
					public void submit() {
						// TODO Auto-generated method stub
						Log.i("----------", "立即设置");
						AddCalenderUtil.add(HoldFixedActivity.this, "米多提醒", detail.getProductName() + "转让日期即将到达",
								detail.getTransferDate(), detail.getTransferDate(), detail.getAlarmClockTime());
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
		case R.id.zdsk:
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
							startActivityForResult(new Intent(HoldFixedActivity.this, BankCardLstActivity.class), 1001);
						} else {
							startActivityForResult(
									new Intent(HoldFixedActivity.this, AddReceiveBankCardActivity.class), 1001);
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
				// 查看合同
				// http://wwwtest.miduo.com/ucenter/agreement/MDL01449209551133.pdf
				String url = ConstantValues.ROOT_IP_BARN + "/agreement/" + assetNo + ".pdf?_cus_sessionid="
						+ MyApplication.cus_sessionid;
				Log.e("abc", url);
				CommonUtil.downPdf(HoldFixedActivity.this, url);
			}
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
					intent = new Intent(HoldFixedActivity.this, BatchRedeemOrderSelActivity.class);
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
							intent = new Intent(HoldFixedActivity.this, RedeemInfoActivity.class);
							Bundle bundle = new Bundle();
							bundle.putBoolean("hasbank", true);
							bundle.putSerializable("redeem_info", singleRedeemInfo);
							intent.putExtras(bundle);
							startActivity(intent);
						} else {
							if (redeemInfo.getBankCount() == 0) {
								intent = new Intent(HoldFixedActivity.this, AddReceiveBankCardActivity.class);
								startActivityForResult(intent, 1004);
							} else {
								intent = new Intent(HoldFixedActivity.this, BankCardLstActivity.class);
								startActivityForResult(intent, 1004);
							}
						}
					}
				}

			}
		};
		GetRedeemInfo getRedeemInfo = new GetRedeemInfo(HoldFixedActivity.this, geeemResultListener,
				detail.getAssetId());
		getRedeemInfo.getData();
	}

	private void turnOff() {
		Intent intent = new Intent(HoldFixedActivity.this, TransferOrderConfirmActivity.class);
		intent.putExtra("assetNo", assetNo);
		startActivity(intent);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 1001:
			if (resultCode == RESULT_OK) {
				zdsk.setText("查看合同");
				zdsk.setTextColor(Color.parseColor("#2EA7E0"));
				zdsk.setBackgroundResource(R.drawable.button_bg_blue_line);
				detail.setHaveBankcard(true);
			}
			break;
		case 1004:
			if (resultCode == RESULT_OK) {
				zdsk.setText("查看合同");
				zdsk.setTextColor(Color.parseColor("#2EA7E0"));
				zdsk.setBackgroundResource(R.drawable.button_bg_blue_line);
				detail.setHaveBankcard(true);
				Intent intent = new Intent(HoldFixedActivity.this, RedeemInfoActivity.class);
				Bundle bundle = new Bundle();
				bundle.putBoolean("hasbank", false);
				bundle.putSerializable("redeem_info", singleRedeemInfo);
				BankCardInfo cardInfo = (BankCardInfo) data.getSerializableExtra("bank_card");
				bundle.putSerializable("bank_card", cardInfo);
				intent.putExtras(bundle);
				startActivity(intent);
			}
			break;
		case 1005:
			if (resultCode == RESULT_OK) {
				ProgressDialogUtil.showProgress(this);
				redeem();
			}
			break;
		case 1006:
			if (resultCode == RESULT_OK) {
				turnOff();
			}
			break;
		default:
			break;

		}
	}

}
