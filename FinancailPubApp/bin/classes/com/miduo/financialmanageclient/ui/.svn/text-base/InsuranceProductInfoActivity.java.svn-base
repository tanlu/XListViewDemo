package com.miduo.financialmanageclient.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.NProductCenterInsureResult.NProductCenterInsureBean;
import com.miduo.financialmanageclient.bean.NProductCenterInsureResult.NProductCenterInsureBean.InsureFileListEntity;
import com.miduo.financialmanageclient.bean.NProductCenterInsureResult.NProductCenterInsureBean.NInsureValue;
import com.miduo.financialmanageclient.common.CommonUtil;
import com.miduo.financialmanageclient.ui.adapter.InsuranceInfoBottomAdapter;
import com.miduo.financialmanageclient.ui.adapter.InsuranceProductInfoAdapter;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.SocialShareUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.umeng.analytics.MobclickAgent;

/**
 * 保险产品详情页面
 * 
 * @author huozhenpeng
 * 
 */
public class InsuranceProductInfoActivity extends GesterSetBaseActivity implements OnClickListener{

	private ListView lv_proinfo;
	private ListView lv_bottomaction;
	private InsuranceProductInfoAdapter infoadaper;
	private InsuranceInfoBottomAdapter bottomadapter;
	private List<NInsureValue> lists;
	private List<InsureFileListEntity> bottomLists;
	private ImageView left_img;
	private TextView title_txt;
	private TextView right_txt;
	private NProductCenterInsureBean data;
	private TextView tv_title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_insuranceproductinfo);
		initView();
		initEvent();
		initData();
	}

	private void initData() {
		data=MyApplication.ninsurebean;
		tv_title.setText(data.getProductName());
		lists.addAll(data.getAttrList());
		infoadaper.notifyDataSetChanged();
		
		bottomLists.addAll(data.getFileList());
		bottomadapter.notifyDataSetChanged();
	}

	private void initEvent() {

		left_img.setOnClickListener(this);
		right_txt.setOnClickListener(this);
		lv_bottomaction.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			 CommonUtil.downPdf(InsuranceProductInfoActivity.this, bottomLists.get(position).getAttrValue().toString()+"");
			}
			
		});
	}

	private void initView() {
		left_img=(ImageView) this.findViewById(R.id.left_img);
		title_txt=(TextView) this.findViewById(R.id.title_txt);
		tv_title=(TextView) this.findViewById(R.id.tv_title);
		title_txt.setText("产品详情");
		right_txt=(TextView) this.findViewById(R.id.right_txt);
		right_txt.setText("分享");
		lv_proinfo=(ListView) this.findViewById(R.id.lv_proinfo);
		lv_bottomaction=(ListView) this.findViewById(R.id.lv_bottomaction);
		lists=new ArrayList<NInsureValue>();
		infoadaper=new InsuranceProductInfoAdapter(this, lists);
		lv_proinfo.setAdapter(infoadaper);
		bottomLists=new ArrayList<InsureFileListEntity>();
		bottomadapter=new InsuranceInfoBottomAdapter(bottomLists, this);
		lv_bottomaction.setAdapter(bottomadapter);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_img:
//			MyApplication.index=5;
			finish();
			break;
		case R.id.right_txt:
			//产品特点还没有			
			String url = data.getShareHref();
			String title = "我发现一个给力的保险资产品，快来了解一下吧！";
			StringBuffer str = new StringBuffer();
			String productHighlight = data.getHighlight();
			if (!StringUtil.isEmpty(productHighlight)) {
				productHighlight = productHighlight.replace('@', '，');
				str.append(productHighlight + ":");
			}
			if (!StringUtil.isEmpty(data.getProductName())) {
				str.append(data.getProductName());
			}
			String content = str.toString();
			SocialShareUtil.share(this, url, title, content);
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();		
		MobclickAgent.onPageStart("保险产品详情"); //统计页面
	    MobclickAgent.onResume(this);          //统计时长
	}

	@Override
	protected void onPause() {
		super.onPause();		
		MobclickAgent.onPageEnd("保险产品详情"); //  
	    MobclickAgent.onPause(this);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		/** 使用SSO授权必须添加如下代码 */
		SocialShareUtil.closeShare(requestCode, resultCode, data);
	}
}
