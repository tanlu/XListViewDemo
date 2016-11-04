package com.miduo.financialmanageclient.ui;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.BankCardInfo;
import com.miduo.financialmanageclient.bean.DialogBean;
import com.miduo.financialmanageclient.bean.MyTranserResult;
import com.miduo.financialmanageclient.bean.NOrderResult;
import com.miduo.financialmanageclient.bean.TransferDataEntity;
import com.miduo.financialmanageclient.bean.TransferListBean;
import com.miduo.financialmanageclient.common.CommonUtil;
import com.miduo.financialmanageclient.listener.CheckIdentyListener;
import com.miduo.financialmanageclient.listener.DialogEventListener;
import com.miduo.financialmanageclient.util.FloatUtil;
import com.miduo.financialmanageclient.util.JsonUtils;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.SocialShareUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

public class TransferOrderInfoActivity extends GesterSetBaseActivity implements
		OnClickListener {
	private ImageView left_img;
	private TextView right_txt, name, zr_date, price, big_price, zhekoujia,
			trans_shouyilv,shuhui;

	private Button zdzh;
	private ListView listView;
	private List<TransferListBean> list;
	private TextView premium;
	private Map<String, String> map;
	private TransferDataEntity transferData;
	private NOrderResult nOrderResult;
	private String bankResult;
	private MyTranserResult myTranserResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transferorderinfo);
		init();
		initEvent();
		initDate();
	}

	private void init() {
		left_img = (ImageView) findViewById(R.id.left_img);
		right_txt = (TextView) findViewById(R.id.right_txt);
		TextView title_txt = (TextView) findViewById(R.id.title_txt);
		title_txt.setText("转让单详情");
		name = (TextView) findViewById(R.id.name);
		shuhui = (TextView) findViewById(R.id.shuhui);
		zr_date = (TextView) findViewById(R.id.zr_date);
		price = (TextView) findViewById(R.id.price);
		premium = (TextView) findViewById(R.id.premium);
		big_price = (TextView) findViewById(R.id.big_price);
		zhekoujia = (TextView) findViewById(R.id.zhekoujia);
		trans_shouyilv = (TextView) findViewById(R.id.trans_shouyilv);
		zdzh = (Button) findViewById(R.id.zdzh);
		listView = (ListView) findViewById(R.id.product_info_lv);
		map = new HashMap<String, String>();

	}

	private void initEvent() {
		left_img.setOnClickListener(this);
		right_txt.setOnClickListener(this);
		zdzh.setOnClickListener(this);
		shuhui.setOnClickListener(this);
	}

	private void initDate() {
		Intent intent = getIntent();
		transferData = (TransferDataEntity) intent
				.getSerializableExtra("transfer_info");
		if(transferData==null)
			return;
		switch (transferData.getState()) {
//		转让状态(0，未转让，1、转让中 2、转让成功 3、转让取消 )
		case 0:
			

			// 无卡
			// intent.setClass(this, AddBankCardActivity.class);
			// startActivity(intent);
			break;
		case 1:
			zdzh.setBackgroundResource(R.drawable.button_bg_gray);
			zdzh.setText("转让中");
			zdzh.setOnClickListener(null);
			break;
		default:
			zdzh.setBackgroundResource(R.drawable.button_bg_gray);
			zdzh.setText("已转让");
			zdzh.setOnClickListener(null);
			break;
		}
		right_txt.setVisibility(View.GONE);
		name.setText(transferData.getProductName());
		zr_date.setText(transferData.getTransferDueTime());
		double num = transferData.getTransferPrice();
		premium.setText(transferData.getDiscountType());
		price.setText(FloatUtil.toTwoDianStringSeparator(num));
		big_price.setText(StringUtil.number2CNMontrayUnit(BigDecimal
				.valueOf(num)));
		zhekoujia.setText(FloatUtil.toTwoDianString(transferData
				.getDiscountPrice()));
		// trans_shouyilv.setText(FloatUtil.toPercentage(transferData.getExpertRate()));
		trans_shouyilv.setText(transferData.getExpertRate());
		list = transferData.getProList();
		MyAdapter adapter = new MyAdapter();
		listView.setAdapter(adapter);

	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.left_img:
			finish();
			break;
		case R.id.shuhui:
			intent = new Intent(this, AgreementActivity.class);
			intent.putExtra("agree_type", 3);
			intent.putExtra("productId", transferData.getProductId());
			startActivity(intent);
			break;
		case R.id.zdzh:			
			if(transferData==null)
				return;
			MyApplication.isBuy = false;
			ProgressDialogUtil.showProgress(this);
			map.clear();
			map.put("type", 1 + "");
			map.put("productId", transferData.getProductId());
			map.put("orderNo", transferData.getTransNo());
			map.put("amount", transferData.getTransferPrice() + "");
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						//判断该转让单是否是自己的
						myTranserResult=WebServiceClient.getIsSelfTransferOrder(map);
						handler.sendEmptyMessage(0x06);
					} catch (AppException e) {
						handler.sendEmptyMessage(0x02);
						e.printStackTrace();
					}
				}
			}).start();
			break;
		default:
			break;
		}
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {	
			switch (msg.what) {
			case 0x01:
				ProgressDialogUtil.closeProgress();
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
					} else {
						if (nOrderResult.getState() == ConstantValues.LOGIN_ERROR) {
							MDialog.showPsdErrorDialog(
									TransferOrderInfoActivity.this,
									nOrderResult.getMsg());
						} else {
							MToast.showToast(TransferOrderInfoActivity.this,
									nOrderResult.getMsg());
							DialogBean dialog = new DialogBean();
							dialog.setTitle("下单失败！");
							dialog.setContent(TransferOrderInfoActivity.this
									.getResources().getString(
											R.string.orderfailed_2));
							dialog.setSubmit("您手慢了，此转让单已经成功转让了！");
							dialog.setDialogEvent(new DialogEventListener() {

								@Override
								public void submit() {
									TransferOrderInfoActivity.this.finish();
								}

								@Override
								public void cancel() {

								}
							});
							MDialog.showDialog1(TransferOrderInfoActivity.this,
									dialog);
						}

					}
				}
				break;
			case 0x02:
				ProgressDialogUtil.closeProgress();
				MToast.showToast(TransferOrderInfoActivity.this, "请求异常");
				break;
			case 0x05:
				if (null == bankResult) {
					MToast.showToast(TransferOrderInfoActivity.this, "数据异常");
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
									TransferOrderInfoActivity.this,
									ConfirmPaymentActivity.class);
							if (nOrderResult.getData() != null) {
								intent.putExtra("ORDER", nOrderResult.getData());
								intent.putExtra("BANKCARD", cardLst.get(0));
							}
							startActivity(intent);
							MyApplication.activityLists
									.add(TransferOrderInfoActivity.this);

						} else {
							String tip = jo.getString("msg");
							if (state == ConstantValues.LOGIN_ERROR) {
								MDialog.showPsdErrorDialog(
										TransferOrderInfoActivity.this, tip);
							} else {
								MToast.showToast(
										TransferOrderInfoActivity.this, tip);
							}

						}
					} catch (Exception e) {
						e.printStackTrace();
						MToast.showToast(TransferOrderInfoActivity.this, "数据异常");
					} finally {
					}
				}
				break;
			case 0x06:
				if(myTranserResult!=null)
				{
					if(myTranserResult.getState()==1)//不是自己的,下单
					{
						//实名认证
						checkIdentify(false);					
					}
					else
					{
						ProgressDialogUtil.closeProgress();
						DialogBean dialog = new DialogBean();
						dialog.setTitle("注意！");
							dialog.setContent("您自己的转让单可以在米仓中取消转让，您是否要购买自己的转让单？");
							dialog.setCancel("取\u3000消");
							dialog.setSubmit("确\u3000定");
							dialog.setDialogEvent(new DialogEventListener() {

								@Override
								public void submit() {
									//实名认证
									checkIdentify(true);
								}

								@Override
								public void cancel() {

								}
							});
							MDialog.showDialog2(TransferOrderInfoActivity.this,
									dialog);
						}
				}
				else
				{
					MToast.showToast(TransferOrderInfoActivity.this, "投资失败！");
				}
				break;

			default:
				break;
			}
		};
	};
	
	private void checkIdentify(boolean isOwerOrder){
		if(isOwerOrder){
			ProgressDialogUtil.showProgress(this);			
		}
		if (CommonUtil.checkIdentify(this)) {
			//下单
			takeOrder();
		} else {
			CheckIdentyListener checkIdentyListener = new CheckIdentyListener() {

				@Override
				public void check(boolean flag) {
					// TODO Auto-generated method stub					
					if (flag) {
						//下单
						takeOrder();
					} else {
						ProgressDialogUtil.closeProgress();
						Intent intent = new Intent();
						intent = new Intent(TransferOrderInfoActivity.this, IdentityActivity.class);
						startActivityForResult(intent, 1001);
					}
				}
			};			
			CommonUtil.identy(this, checkIdentyListener);
		}
	}
	
	/**
	 * 下单
	 */
	private void takeOrder(){
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
	
	

	/**
	 * 有银行卡信息
	 */
	private void haveBankCard() {
		// 有卡
		Intent intent = new Intent(this, SelectedBankCardActivity.class);
		intent.putExtra("ORDER", nOrderResult.getData());
		startActivity(intent);
		MyApplication.activityLists.add(this);
	}

	/**
	 * 无卡
	 */
	private void noBankCard() {
		Intent intent = new Intent(this, AddBankCardActivity.class);
		intent.putExtra("ORDER", nOrderResult.getData());
		startActivity(intent);
		MyApplication.activityLists.add(this);
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		/** 使用SSO授权必须添加如下代码 */
		SocialShareUtil.closeShare(requestCode, resultCode, data);
		if (requestCode == 1001 && resultCode == RESULT_OK) {
			ProgressDialogUtil.showProgress(this);
			takeOrder();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("转让单详情");
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPageStart("转让单详情");
		MobclickAgent.onResume(this);
	}

	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = View.inflate(getApplicationContext(),
						R.layout.proinfo_item, null);
				holder.text01 = (TextView) convertView.findViewById(R.id.lv_01);
				holder.text02 = (TextView) convertView.findViewById(R.id.lv_02);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			TransferListBean transferListBean = list.get(position);
			holder.text01.setText(transferListBean.getAttrName());
			holder.text02.setText(transferListBean.getAttrValue());
			return convertView;
		}

	}

	class ViewHolder {
		public TextView text01;
		public TextView text02;
	}
}
