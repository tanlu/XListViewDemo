package com.miduo.financialmanageclient.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.AttrListEntity;
import com.miduo.financialmanageclient.bean.DataEntity;
import com.miduo.financialmanageclient.bean.NBankRateResult;
import com.miduo.financialmanageclient.bean.NRateBean;
import com.miduo.financialmanageclient.common.CommonUtil;
import com.miduo.financialmanageclient.listener.CheckIdentyListener;
import com.miduo.financialmanageclient.ui.adapter.ProdunctInfoAdapter;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.SocialShareUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.widget.PercentageView;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

public class ProdunctInfoActivity extends GesterSetBaseActivity implements OnClickListener {

	private ImageView left_img;
	private TextView right_txt;
	private TextView name;
	private TextView baifenbi_txt, baifenbi_unit_txt;
	private TextView money;
	private ListView lv;
	private Context mContext;
	private Button chanpinxiangqing;
	private ImageView jisuanqi;
	private Button touzi;
	private DataEntity product;
	private ImageView salestate_img;
	private PercentageView percentageView;
	private List<AttrListEntity> attrList = new ArrayList<AttrListEntity>();
	private ProdunctInfoAdapter produnctInfoAdapter;
	private NBankRateResult nBankRateResult;
	private Map<String, String> map;
	private NRateBean nRateBean;
	private RelativeLayout relativeLayout1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.activity_produnct_info);
		init();
		initEvent();
		initDate();
	}

	private void init() {
		MyApplication.activityLists.add(this);
		map = new HashMap<String, String>();
		left_img = (ImageView) findViewById(R.id.left_img);
		right_txt = (TextView) findViewById(R.id.right_txt);
		TextView title_txt = (TextView) findViewById(R.id.title_txt);
		title_txt.setText("产品详情");
		right_txt.setText("分享");
		name = (TextView) findViewById(R.id.name);
		salestate_img = (ImageView) findViewById(R.id.salestate_img);
		money = (TextView) findViewById(R.id.money);
		baifenbi_txt = (TextView) findViewById(R.id.baifenbi_txt);
		baifenbi_unit_txt = (TextView) findViewById(R.id.baifenbi_unit_txt);
		lv = (ListView) findViewById(R.id.lv);
		produnctInfoAdapter = new ProdunctInfoAdapter(mContext, attrList);
		lv.setAdapter(produnctInfoAdapter);
		chanpinxiangqing = (Button) findViewById(R.id.chanpinxiangqing);
		jisuanqi = (ImageView) findViewById(R.id.jisuanqi);
		touzi = (Button) findViewById(R.id.touzi);
		percentageView = (PercentageView) findViewById(R.id.percentageView);
		relativeLayout1 = (RelativeLayout) findViewById(R.id.relativeLayout1);
	}

	private void initEvent() {
		left_img.setOnClickListener(this);
		right_txt.setOnClickListener(this);
		chanpinxiangqing.setOnClickListener(this);
		jisuanqi.setOnClickListener(this);
		touzi.setOnClickListener(this);
		relativeLayout1.setOnClickListener(this);
	}

	private void initDate() {
		product = (DataEntity) getIntent().getSerializableExtra("product_info");
		if (product == null) {
			return;
		}
		name.setText(StringUtil.showStringContent(product.getProductName()));
		if (!StringUtil.isEmpty(product.getIshot()) && product.getIshot().equals("1")) {
			salestate_img.setImageResource(R.drawable.hot);
			if (product.getStatus().equals("30")) {
				// 售磬
				touzi.setText("已售罄");
				salestate_img.setImageResource(R.drawable.qing);
				touzi.setBackgroundResource(R.drawable.button_bg_gray);
				touzi.setOnClickListener(null);
			} else {
				if (product.getStatus().equals("10")) {
					// 预售
					touzi.setBackgroundResource(R.drawable.button_bg_gray);
					salestate_img.setImageResource(R.drawable.yu);
					touzi.setText("我要投资");
					touzi.setOnClickListener(null);

				} else {
					if (product.getIsOnlinePay() == 0)// 线下
					{
						
						touzi.setText("联系理财顾问购买");
						touzi.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								Intent intent = new Intent(ProdunctInfoActivity.this, MyFaActivity.class);
								ProdunctInfoActivity.this.startActivity(intent);
							}
						});
					} else// 线上
					{
						touzi.setText("我要投资");

					}
				}
			}
		} else {
			if (!StringUtil.isEmpty(product.getStatus())) {
				if (product.getStatus().equals("10")) {
					// 预售
					salestate_img.setImageResource(R.drawable.yu);
					touzi.setBackgroundResource(R.drawable.button_bg_gray);
					touzi.setText("我要投资");
					touzi.setOnClickListener(null);

				} else if (product.getStatus().equals("20")) {
					// 在售
					touzi.setText("我要投资");
					salestate_img.setImageResource(R.drawable.zai);
					if (product.getIsOnlinePay() == 0)// 线下
					{
						touzi.setText("联系理财顾问购买");
						touzi.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								Intent intent = new Intent(ProdunctInfoActivity.this, MyFaActivity.class);
								ProdunctInfoActivity.this.startActivity(intent);
							}
						});
					} else// 线上
					{
						touzi.setText("我要投资");
					}

				} else if (product.getStatus().equals("30")) {
					// 售磬
					touzi.setText("已售罄");
					salestate_img.setImageResource(R.drawable.qing);
					touzi.setBackgroundResource(R.drawable.button_bg_gray);
					touzi.setOnClickListener(null);
				} else {
					salestate_img.setVisibility(View.GONE);
				}
			} else {
				salestate_img.setVisibility(View.GONE);
			}
		}
		String amountStr = product.getRemainAmount();
		if (!StringUtil.isEmpty(amountStr)) {
			money.setText(String.valueOf((int) (Double.parseDouble(amountStr) / 10000)));
		} else {
			money.setText(getString(R.string.default_value));
		}
		if (!StringUtil.isEmpty(product.getSalePercent())) {
			double progress = Double.parseDouble(product.getSalePercent()) * 100;
			if (progress < 1 && progress > 0) {
				percentageView.setPercentage(1);
			} else {
				percentageView.setPercentage((float) Math.floor(progress));
			}
		}
		if (!StringUtil.isEmpty(product.getExpertRateDesc())) {
			if (product.getExpertRateDesc().indexOf("%") == -1) {
				baifenbi_unit_txt.setVisibility(View.GONE);
				baifenbi_txt.setText(product.getExpertRateDesc());
			} else {
				baifenbi_unit_txt.setVisibility(View.VISIBLE);
				baifenbi_txt.setText(product.getExpertRateDesc().split("%")[0]);
			}
		} else {
		}
		if (product.getAttrList() != null && product.getAttrList().size() > 0) {
			attrList.addAll(product.getAttrList());
			produnctInfoAdapter.notifyDataSetChanged();
		}
		jisuanqi.setVisibility(View.GONE);
		if (product.getCategoryId().equals("2"))// 固定收益
		{
			ProgressDialogUtil.showProgress(this);
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						map.clear();
						map.put("productId", product.getId() + "");
						nBankRateResult = WebServiceClient.getNBankRateResult(map);
						handler.sendEmptyMessage(0x01);
					} catch (AppException e) {
						ProgressDialogUtil.closeProgress();
						e.printStackTrace();
					}

				}
			}).start();
		}
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			ProgressDialogUtil.closeProgress();
			switch (msg.what) {
			case 0x01:
				if (nBankRateResult != null) {
					if (nBankRateResult.getState() == 1) {
						nRateBean = nBankRateResult.getData();
						if (nRateBean != null) {
							if (!TextUtils.isEmpty(nRateBean.getCallRate())
									&& !TextUtils.isEmpty(nRateBean.getCommercialRate())
									&& nRateBean.getExpertRates() != null) {
								jisuanqi.setVisibility(View.VISIBLE);
							} else {
								jisuanqi.setVisibility(View.GONE);
							}
						}
					} else {
						if (nBankRateResult.getState() == ConstantValues.LOGIN_ERROR) {
							MDialog.showPsdErrorDialog(ProdunctInfoActivity.this, nBankRateResult.getMsg());
						} else {
						}
					}
				} else {
					jisuanqi.setVisibility(View.GONE);
					// touzi.setOnClickListener(null);
					// MToast.showToast(ProdunctInfoActivity.this, "产品利率获取失败");
				}
				break;

			default:
				break;
			}
		};
	};

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("产品详情页");
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPageStart("产品详情页");
		MobclickAgent.onResume(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.left_img:
			finish();
			break;
		case R.id.right_txt:
			// 分享
			String url = product.getShareHref();
			String title = "我发现一个给力的投资产品，快来了解一下吧！";
			StringBuffer str = new StringBuffer();
			String productHighlight = product.getProductHighlight();
			if (!StringUtil.isEmpty(productHighlight)) {
				productHighlight = productHighlight.replace('@', '，');
				str.append(productHighlight + ":");
			}
			if (!StringUtil.isEmpty(product.getProductName())) {
				str.append(product.getProductName());
			}
			String content = str.toString();
			SocialShareUtil.share(this, url, title, content);
			break;
		case R.id.chanpinxiangqing:
			intent = new Intent(this, AgreementActivity.class);
			intent.putExtra("agree_type", 3);
			intent.putExtra("productId", product.getId());
			startActivity(intent);
			break;
		case R.id.jisuanqi:
			intent = new Intent(this, EarningsCaculatorActivity.class);
			intent.putExtra("product_info", product);
			intent.putExtra("bank_rate", nRateBean);
			startActivity(intent);
			break;
		case R.id.touzi:
			if (CommonUtil.checkIdentify(this)) {
				toNext(true);
			} else {
				CheckIdentyListener checkIdentyListener = new CheckIdentyListener() {

					@Override
					public void check(boolean flag) {
						// TODO Auto-generated method stub
						ProgressDialogUtil.closeProgress();
						if (flag) {
							toNext(true);
						} else {
							toNext(false);
						}
					}
				};
				ProgressDialogUtil.showProgress(this);
				CommonUtil.identy(this, checkIdentyListener);
			}
			// TODO Auto-generated method stub
			break;
		case R.id.relativeLayout1:
			intent = new Intent(this, AgreementActivity.class);
			intent.putExtra("agree_type", 3);
			intent.putExtra("productId", product.getId());
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	private void toNext(boolean flag) {
		// TODO Auto-generated method stub
		MyApplication.isBuy = true;
		Intent intent = new Intent();
		if (flag) {
			intent = new Intent(ProdunctInfoActivity.this, ProductPurchaseActivity.class);
			intent.putExtra("product_info", product);
			intent.putExtra("bank_rate", nRateBean);
			startActivity(intent);
		} else {
			intent = new Intent(ProdunctInfoActivity.this, IdentityActivity.class);
			startActivityForResult(intent, 1001);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		/** 使用SSO授权必须添加如下代码 */
		SocialShareUtil.closeShare(requestCode, resultCode, data);
		if (requestCode == 1001 && resultCode == RESULT_OK) {
			toNext(true);
		}
	}

}
