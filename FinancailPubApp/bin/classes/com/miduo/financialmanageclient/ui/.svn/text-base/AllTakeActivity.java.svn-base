package com.miduo.financialmanageclient.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.AssetDtailInfo;
import com.miduo.financialmanageclient.bean.UserTradeRecordsVo;
import com.miduo.financialmanageclient.common.CommonUtil;
import com.miduo.financialmanageclient.common.GetAssetDetail;
import com.miduo.financialmanageclient.listener.GetCompleteListener;
import com.miduo.financialmanageclient.ui.adapter.AssetDetailRecordAdapter;
import com.miduo.financialmanageclient.util.JsonUtils;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.ws.AppException;
import com.umeng.analytics.MobclickAgent;

public class AllTakeActivity extends GesterSetBaseActivity implements OnClickListener {
	private TextView right_txt;
	private ImageView left_img;
	private ListView lv;
	private TextView tv_name;
	private List<UserTradeRecordsVo> tradeRecords = new ArrayList<UserTradeRecordsVo>();
	private AssetDetailRecordAdapter assetAdapter;
	private AssetDtailInfo detail;
	private String assetNo;
	private TextView agree_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_take);

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

	private void init() {
		TextView title_txt = (TextView) findViewById(R.id.title_txt);
		title_txt.setText("资产详情");
		left_img = (ImageView) findViewById(R.id.left_img);
		right_txt = (TextView) findViewById(R.id.right_txt);
		right_txt.setText("产品资料");
		tv_name = (TextView) findViewById(R.id.tv_name);
		agree_btn = (TextView) findViewById(R.id.agree_btn);
		lv = (ListView) findViewById(R.id.lv);
		assetAdapter = new AssetDetailRecordAdapter(this, tradeRecords);
		lv.setAdapter(assetAdapter);

	}

	private void initEvent() {
		left_img.setOnClickListener(this);
		right_txt.setOnClickListener(this);
		agree_btn.setOnClickListener(this);
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
						if(detail==null){
							return;
						}
						tv_name.setText(StringUtil.showStringContent(detail.getProductName()));
						tradeRecords.clear();
						if (detail.getTradeRecords() != null && detail.getTradeRecords().size() > 0) {
							tradeRecords.addAll(detail.getTradeRecords());
						}
						assetAdapter.notifyDataSetChanged();
					} catch (AppException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		GetAssetDetail getAssetDetail = new GetAssetDetail(AllTakeActivity.this, getCompleteListener, assetNo);
		getAssetDetail.getData();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
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
		case R.id.agree_btn:
			String url = ConstantValues.ROOT_IP_BARN + "/agreement/" + assetNo + ".pdf?_cus_sessionid=" + MyApplication.cus_sessionid;
			CommonUtil.downPdf(this, url);
			break;
		default:
			break;
		}

	}

}
