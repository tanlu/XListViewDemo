package com.miduo.financialmanageclient.ui;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.BankCardInfo;
import com.miduo.financialmanageclient.bean.NOrderBean;
import com.miduo.financialmanageclient.bean.ReturnMsg;
import com.miduo.financialmanageclient.util.FloatUtil;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.analytics.MobclickAgent;

/**
 * 确认支付页面
 * 
 * @author huozhenpeng
 * 
 */
public class ConfirmPaymentActivity extends BaseActivity implements
		OnClickListener {

	private ImageView left_img;
	private TextView title_txt;
	private TextView right_txt;
	private TextView btn_txt;
	private TextView product_name_txt;// 产品名字
	private TextView number_amount_txt;// 投资金额
	private TextView china_amount_txt;// 投资金额的汉字表示
	private TextView org_name_txt;// 银行名称
	private ImageView org_img;// 银行图标
	private TextView card_code_txt;// 银行卡号(取后四位)
	private TextView name_txt;// 持卡人姓名
	private EditText txt2;// 预留手机号
	private EditText validate_code_txt;// 验证码
	private TextView validate_code_btn;// 获取验证码按钮
	private TextView agree_info_txt;
	private NOrderBean nOrderBean;
	private BankCardInfo backCardInfo;
	private TextView address_txt;
	private ImageLoader imageloader = null;
	private int maxTime = 120;
	private int time = maxTime;
	private Map<String,String> map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_confirmpayment);
		initView();
		initEvent();
		initData();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("确认支付"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("确认支付"); //
		MobclickAgent.onPause(this);
	}

	private void initData() {

		title_txt.setText("确认支付");
		nOrderBean=(NOrderBean) getIntent().getSerializableExtra("ORDER");
		if(nOrderBean==null)
			return;
	    product_name_txt.setText(nOrderBean.getProductTitle());
	    number_amount_txt.setText(FloatUtil.toTwoDianStringSeparator(nOrderBean.getAmount()));
	    china_amount_txt.setText(StringUtil.number2CNMontrayUnit(BigDecimal
				.valueOf(nOrderBean.getAmount())));
	    
	    backCardInfo=(BankCardInfo)getIntent().getSerializableExtra("BANKCARD");
	    if(backCardInfo==null)
	    	return;
	    org_name_txt.setText(backCardInfo.getBankName());
	    StringBuffer str = new StringBuffer();
	    if (!StringUtil.isEmpty(backCardInfo.getBankAddress())) {
			str.append(backCardInfo.getBankAddress());
		}
	    if (!StringUtil.isEmpty(backCardInfo.getBranchBank())) {
			str.append(backCardInfo.getBranchBank());
		}		
		String address = str.toString();
		address_txt.setText(StringUtil.showStringContent(address));
	    card_code_txt.setText(backCardInfo.getCardShortNo());
	    name_txt.setText(backCardInfo.getRealName());
	    imageloader.displayImage(backCardInfo.getSmallIco(), org_img);
	    
	    
		if (MyApplication.isBuy) {
			btn_txt.setText("确认支付");
			agree_info_txt.setVisibility(View.GONE);
		} else {
			btn_txt.setText("同意协议并投资");
			agree_info_txt.setVisibility(View.VISIBLE);
		}
	}

	private void initEvent() {
		btn_txt.setOnClickListener(this);
		left_img.setOnClickListener(this);
		validate_code_btn.setOnClickListener(this);
		right_txt.setOnClickListener(this);
		agree_info_txt.setOnClickListener(this);
	}

	private void initView() {
		imageloader = ImageLoader.getInstance();
		imageloader.init(ImageLoaderConfiguration.createDefault(this));
		left_img = (ImageView) this.findViewById(R.id.left_img);
		title_txt = (TextView) this.findViewById(R.id.title_txt);
		right_txt = (TextView) this.findViewById(R.id.right_txt);
		right_txt.setText("使用其他账号");
		btn_txt = (TextView) this.findViewById(R.id.btn_txt);
		product_name_txt=(TextView) this.findViewById(R.id.product_name_txt);
		number_amount_txt=(TextView) this.findViewById(R.id.number_amount_txt);
		china_amount_txt=(TextView) this.findViewById(R.id.china_amount_txt);
		org_name_txt=(TextView) this.findViewById(R.id.org_name_txt);
		org_img=(ImageView) this.findViewById(R.id.org_img);
		card_code_txt=(TextView) this.findViewById(R.id.card_code_txt);
		name_txt=(TextView) this.findViewById(R.id.name_txt);
		txt2=(EditText) this.findViewById(R.id.txt2);
		validate_code_txt=(EditText) this.findViewById(R.id.validate_code_txt);
		validate_code_btn=(TextView) this.findViewById(R.id.validate_code_btn);		
		agree_info_txt=(TextView) this.findViewById(R.id.agree_info_txt);
		address_txt=(TextView) this.findViewById(R.id.address_txt);
		map=new HashMap<String,String>();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_txt:
//			if(!TextUtils.isEmpty(txt2.getText().toString())&&!TextUtils.isEmpty(validate_code_txt.getText().toString()))
//			{
//				map.clear();
//				map.put("mobile", txt2.getText().toString());
//				map.put("orderNo", nOrderBean.getOrderNo());
//				map.put("bankcardId", backCardInfo.getId()+"");
//				map.put("smsCode", validate_code_txt.getText().toString());
//				new Thread(new Runnable() {
//					
//					@Override
//					public void run() {
//						try {
//							WebServiceClient.confirmPay(map);
//						} catch (AppException e) {
//							e.printStackTrace();
//						}
//					}
//				}).start();
				Intent intent1=new Intent(ConfirmPaymentActivity.this,ConfirmPayWebViewActivity.class);
				intent1.putExtra("mobile", txt2.getText().toString());
				intent1.putExtra("orderNo", nOrderBean.getOrderNo());
				intent1.putExtra("bankcardId", backCardInfo.getId()+"");
				intent1.putExtra("smsCode", validate_code_txt.getText().toString());
				intent1.putExtra("url", nOrderBean.getUrl());
				MyApplication.activityLists.add(this);
				startActivity(intent1);
//				finish();
				
//			}
			// 支付成功了，买成了 507 PaymentResultSuccess1Activity
			// 支付失败 508 PaymentResultFailureActivity
			// 银行没给支付结果，信息延迟 510 PaymentResultFailureDelayActivity
			// （转让）支付成功，但转让单已经被转出了 PaymentResultSuccessNoPlaceActivity
			// (支付失败 319 507 508 509 510 )
			// (购买)支付成功，但已无名额 PaymentResultSuccessNoPlaceActivity
			break;
		case R.id.left_img:
			this.finish();
			MyApplication.activityLists.remove(this);
			break;
		case R.id.validate_code_btn:// 获取验证码
			if (!StringUtil.isMobileNO(txt2.getText().toString())) {
				MToast.showToast(this, "请输入有效的手机号码！");
				return;
			}
			getCode(txt2.getText().toString());
			break;
		case R.id.right_txt:
			Intent intent = new Intent(this, SelectedBankCardActivity.class);
			intent.putExtra("ORDER", nOrderBean);
			startActivity(intent);
			break;
		case R.id.agree_info_txt:
			Intent intent2 = new Intent(this, AgreementActivity.class);
			intent2.putExtra("agree_type", 2);
			startActivity(intent2);
			break;
		default:
			break;
		}
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
					Map<String, String> map = new HashMap<String, String>();
					map.put("mobile", params[0]);
					return WebServiceClient.GetRedeemSms1(map);
				} catch (AppException e) {
					this.ex = e;
				}
				return null;
			}

			@Override
			protected void onPostExecute(ReturnMsg result) {
				if (null == result) {
					if (ex != null) {
						ex.makeToast(ConfirmPaymentActivity.this);
					} else {
						MToast.showToast(ConfirmPaymentActivity.this, "获取验证码失败！");
					}
					time = 1;
					return;
				} else {
					if (result.getState() != 1) {
						MToast.showToast(ConfirmPaymentActivity.this,
								StringUtil.isEmpty(result.getMsg()) ? "获取验证码失败！" : result.getMsg());
						time = 1;
					}
				}
				super.onPostExecute(result);
			}

		}.execute(mobile);
	}
	
	private Handler handler=new Handler(){
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
		};
	};
	private void resetValidateTxt() {
		validate_code_btn.setText(R.string.gainValidate);
		validate_code_btn.setEnabled(true);
		validate_code_btn.setBackgroundResource(R.drawable.button_bg_blue);
	}
}
