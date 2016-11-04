package com.miduo.financialmanageclient.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.bean.NSingleGainResult;
import com.miduo.financialmanageclient.bean.NSingleGainResult.ScrollPerBarBean;
import com.miduo.financialmanageclient.bean.NSingleGainResult.ScrollPerBarBean.SingleGainBean;
import com.miduo.financialmanageclient.ui.adapter.SingleGainAdapter;
import com.miduo.financialmanageclient.util.FloatUtil;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.widget.ScrollBarPic;
import com.miduo.financialmanageclient.widget.ScrollBarPic.OnClickListener;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

/**
 * 单日收益
 * 
 * @author huozhenpeng
 * 
 */
public class SingleDayGainActivity extends GesterSetBaseActivity implements
		View.OnClickListener {

	private TextView title_txt;
	private ImageView left_img;
	private TextView right_txt;
	private ListView listview;
	private SingleGainAdapter adapter;
	private List<SingleGainBean> datas;
	private ScrollBarPic scrollBarPic;
	private NSingleGainResult scrollBarBean;
	private TextView tv_money;
	private Map<String, String> map;
	private ScrollPerBarBean tempPerBarBean;
	private LinearLayout tv_content;
	private LinearLayout ll_nodata;
	private LinearLayout ll_havedata;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_singleday_gain);
		initView();
		initEvent();
		initData();
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("单日收益"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("单日收益"); //
		MobclickAgent.onPause(this);
	}

	private void initData() {
		title_txt.setText("单日收益");
		right_txt.setText("累计收益");
		scrollBarPic = (ScrollBarPic) this.findViewById(R.id.scrollBarPic);
		ProgressDialogUtil.showProgress(this);
		map = new HashMap<String, String>();
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					scrollBarBean = WebServiceClient.getSingleGainResult(map);
					handler.sendEmptyMessage(0x01);
				} catch (AppException e) {
					handler.sendEmptyMessage(0x02);
					e.printStackTrace();
				}
			}
		}).start();

	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0x01:
				ProgressDialogUtil.closeProgress();
				if (scrollBarBean != null) {
					if (scrollBarBean.getState() == 1) {
						if (scrollBarBean.getData().size() == 0)// 无单日收益
						{
							ll_havedata.setVisibility(View.GONE);
							ll_nodata.setVisibility(View.VISIBLE);

						} else {
							ll_havedata.setVisibility(View.VISIBLE);
							ll_nodata.setVisibility(View.GONE);
							scrollBarBean.setTotal(100);
							for (int i = 0; i < scrollBarBean.getData().size(); i++) {
								tempPerBarBean = scrollBarBean.getData().get(i);
								tempPerBarBean
										.setIcDate(tempPerBarBean.getIcDate()
												.split("-")[1]
												+ "-"
												+ tempPerBarBean.getIcDate()
														.split("-")[2]);
							}
							scrollBarPic.setDatas(scrollBarBean);
							tv_content.setVisibility(View.VISIBLE);
							tv_money.setText(FloatUtil
									.toTwoDianStringSeparator(scrollBarBean
											.getData()
											.get(scrollBarBean.getData().size() - 1)
											.getAmount()));
							datas.addAll(scrollBarBean.getData()
									.get(scrollBarBean.getData().size() - 1)
									.getAppAssetUserIncomeJsonVos());
							if (adapter == null) {

								adapter = new SingleGainAdapter(datas,
										SingleDayGainActivity.this);
								listview.setAdapter(adapter);
							} else {
								adapter.notifyDataSetChanged();
							}
						}

					}
					else
					{
						if(scrollBarBean.getState()==ConstantValues.LOGIN_ERROR)
						{
							MDialog.showPsdErrorDialog(SingleDayGainActivity.this, scrollBarBean.getMsg());
						}
						else
						{
							MToast.showToast(SingleDayGainActivity.this,scrollBarBean.getMsg() );
						}
					}
				}
				break;
			case 0x02:
				ProgressDialogUtil.closeProgress();
				break;

			default:
				break;
			}

		};
	};

	private void initEvent() {
		title_txt.setOnClickListener(this);
		left_img.setOnClickListener(this);
		scrollBarPic.setOnClickListener(new OnClickListener() {

			@Override
			public void onclick(int i) {
				tv_money.setText(FloatUtil
						.toTwoDianStringSeparator(scrollBarBean.getData()
								.get(i).getAmount()));
				datas.clear();
				datas.addAll(scrollBarBean.getData().get(i)
						.getAppAssetUserIncomeJsonVos());
				adapter.notifyDataSetChanged();
			}
		});
	}

	private void initView() {
		ll_nodata = (LinearLayout) this.findViewById(R.id.ll_nodata);
		ll_havedata = (LinearLayout) this.findViewById(R.id.ll_havedata);
		tv_content = (LinearLayout) this.findViewById(R.id.tv_content);
		tv_content.setVisibility(View.GONE);
		tv_money = (TextView) this.findViewById(R.id.tv_money);
		title_txt = (TextView) this.findViewById(R.id.title_txt);
		left_img = (ImageView) this.findViewById(R.id.left_img);
		right_txt = (TextView) this.findViewById(R.id.right_txt);
		right_txt.setVisibility(View.INVISIBLE);
		listview = (ListView) this.findViewById(R.id.listview);
		scrollBarPic = (ScrollBarPic) this.findViewById(R.id.scrollBarPic);
		datas = new ArrayList<SingleGainBean>();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_img:
			finish();
			break;
		case R.id.right_txt:
			Intent intent = new Intent(this, AssetsListActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
}
