package com.miduo.financialmanageclient.ui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
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
import com.miduo.financialmanageclient.bean.BankCardInfo;
import com.miduo.financialmanageclient.bean.NOrderBean;
import com.miduo.financialmanageclient.common.GetBankCardList;
import com.miduo.financialmanageclient.listener.GetCardLstListener;
import com.miduo.financialmanageclient.ui.adapter.BankInfoAdapter;
import com.miduo.financialmanageclient.ui.adapter.BankInfoAdapter.PayClickListener;
import com.miduo.financialmanageclient.ui.adapter.BankInfoAdapter.ViewHolder;
import com.miduo.financialmanageclient.util.FloatUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.umeng.analytics.MobclickAgent;

/**
 * 选择银行卡页面
 * 
 * @author huozhenpeng
 * 
 */
public class SelectedBankCardActivity extends GesterSetBaseActivity implements OnClickListener {

	private ListView listview;
	private List<BankCardInfo> lists;
	private BankInfoAdapter adapter;
	private ImageView left_img;
	private TextView title_txt;
	private TextView right_txt;
	private TextView product_name_txt;// 产品名字
	private TextView number_amount_txt;// 投资金额
	private TextView china_amount_txt;// 投资金额汉字表示
	private NOrderBean nOrderBean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_selectebankcard);
		initView();
		initEvent();
		initData();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("选择银行卡"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("选择银行卡"); //
		MobclickAgent.onPause(this);
	}

	private void initData() {

		title_txt.setText("选择银行卡");
		nOrderBean=(NOrderBean) getIntent().getSerializableExtra("ORDER");
		if(nOrderBean==null)
			return;
	    product_name_txt.setText(nOrderBean.getProductTitle());
	    number_amount_txt.setText(FloatUtil.toTwoDianStringSeparator(nOrderBean.getAmount()));
	    china_amount_txt.setText(StringUtil.number2CNMontrayUnit(BigDecimal
				.valueOf(nOrderBean.getAmount())));
	    

		GetCardLstListener getCardLstListener = new GetCardLstListener() {

			@Override
			public void refresh(List<BankCardInfo> tempCardLst) {
				lists.clear();
				if (tempCardLst != null && tempCardLst.size() > 0) {
					lists.addAll(tempCardLst);
				}
				adapter.notifyDataSetChanged();
			}
		};
		GetBankCardList getBankCardList = new GetBankCardList(SelectedBankCardActivity.this, getCardLstListener);
		getBankCardList.getData();
	}

	private void initEvent() {
		left_img.setOnClickListener(this);
		adapter.setPayClickListener(new PayClickListener() {

			@Override
			public void onClick(int position) {
				if (position < lists.size()) {
					Intent intent = new Intent(SelectedBankCardActivity.this, ConfirmPaymentActivity.class);
					if(nOrderBean!=null)
					{
					intent.putExtra("ORDER", nOrderBean);
					intent.putExtra("BANKCARD", lists.get(position));
					}
					startActivity(intent);
					MyApplication.activityLists.add(SelectedBankCardActivity.this);
				} else {
					Intent intent = new Intent(SelectedBankCardActivity.this, AddBankCardActivity.class);
					intent.putExtra("ORDER", nOrderBean);
					startActivity(intent);
					MyApplication.activityLists.add(SelectedBankCardActivity.this);
				}
			}
		});
		listview.setOnItemClickListener(new OnItemClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				listview.setSelection(position);
				ViewHolder holder = ((ViewHolder) view.getTag());
				BankCardInfo bankInfoBean = lists.get(position);
				if (bankInfoBean.getChannelType()!=null && bankInfoBean.getChannelType().intValue()==2)// 满足支付条件
				{

					if (bankInfoBean.isOpen()) {
						holder.card_layout.setBackgroundResource(R.drawable.card_item_white_bg);
						holder.ll_satisfy.setVisibility(View.GONE);
						holder.ll_notsatisfy.setVisibility(View.GONE);
						holder.iv_tip.setRotation(0);
						bankInfoBean.setOpen(false);

					} else {
						holder.card_layout.setBackgroundResource(R.drawable.card_item_blue_bg);
						holder.ll_satisfy.setVisibility(View.VISIBLE);
						holder.ll_notsatisfy.setVisibility(View.GONE);
						holder.iv_tip.setRotation(180);
						bankInfoBean.setOpen(true);
						for (int i = 0; i < lists.size(); i++) {
							if (i != position) {
								lists.get(i).setOpen(false);
								adapter.notifyDataSetChanged();
							}
						}
					}
					listview.setSelection(position);
				}
			}
		});
	}

	private void initView() {
		title_txt = (TextView) this.findViewById(R.id.title_txt);
		right_txt = (TextView) this.findViewById(R.id.right_txt);
		left_img = (ImageView) this.findViewById(R.id.left_img);
		right_txt.setVisibility(View.INVISIBLE);
		listview = (ListView) this.findViewById(R.id.listview);
		lists = new ArrayList<BankCardInfo>();
		adapter = new BankInfoAdapter(this, lists, 0);
		listview.setAdapter(adapter);
		product_name_txt = (TextView) this.findViewById(R.id.product_name_txt);
		number_amount_txt = (TextView) this.findViewById(R.id.number_amount_txt);
		china_amount_txt = (TextView) this.findViewById(R.id.china_amount_txt);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_img:
			this.finish();
			MyApplication.activityLists.remove(this);
			break;

		default:
			break;
		}
	}
}
