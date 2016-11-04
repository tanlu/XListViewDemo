package com.miduo.financialmanageclient.ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.bean.MRepertory;
import com.miduo.financialmanageclient.bean.RepertoryItem;
import com.miduo.financialmanageclient.bean.UserAssetInfoForAppVo;
import com.miduo.financialmanageclient.common.SkipUtils;
import com.miduo.financialmanageclient.ui.AccumulActivity;
import com.miduo.financialmanageclient.ui.AssetsListActivity;
import com.miduo.financialmanageclient.ui.HomeActivity;
import com.miduo.financialmanageclient.ui.MyInfomationActivity;
import com.miduo.financialmanageclient.ui.PersonCenterActivity;
import com.miduo.financialmanageclient.ui.SingleDayGainActivity;
import com.miduo.financialmanageclient.ui.adapter.BuyedOrderAdapter;
import com.miduo.financialmanageclient.ui.adapter.BuyedOrderAdapter.ResetListView;
import com.miduo.financialmanageclient.util.FloatUtil;
import com.miduo.financialmanageclient.util.ImageDownLoadUtil;
import com.miduo.financialmanageclient.util.JsonUtils;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.SharePrefUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.widget.CircleImageView;
import com.miduo.financialmanageclient.widget.MessageView;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

public class WarehouseFragment extends Fragment implements OnClickListener {
	private HomeActivity parentActivity;
	private View view;
	private BuyedOrderAdapter buyedOrderAdapter;
	private CircleImageView head_circleimg;
	private ImageView msg_img;
	private TextView asset_name_txt, type_amount_txt, yesterday_value_txt, cumulative_value_txt, order_count_txt;
	private ListView order_listview;
	private List<RepertoryItem> lists = new ArrayList<RepertoryItem>();
	private ResetListView resetListView;
	private RelativeLayout product_type_layout, yesterday_layout, cumulative_layout;
	private LinearLayout order_layout;
	private MsgCountAsyncTask msgCountAsyncTask;
	private int notReadCount = 0;
	private MRepertory userRepertoryInfo;
	private LinearLayout nodata_layout;
	private SkipUtils skipUtils;
	private MessageView messageView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_warehouse, null);
		parentActivity = (HomeActivity) getActivity();
		resetListView = new ResetListView() {

			@Override
			public void refresh(int index) {
				boolean flag = lists.get(index).isExtend();
				if (flag) {
					lists.get(index).setExtend(!flag);
					order_layout.setVisibility(View.VISIBLE);
					buyedOrderAdapter.notifyDataSetChanged();
					order_listview.setSelection(0);
					asset_name_txt.setText("总资产");
					type_amount_txt
							.setText(FloatUtil.toTwoDianStringSeparator(userRepertoryInfo.getAssetTotalAmount()));
				} else {
					for (int i = 0; i < lists.size(); i++) {
						if (i == index) {
							lists.get(i).setExtend(!flag);
						} else {
							lists.get(i).setExtend(flag);
						}
					}
					order_layout.setVisibility(View.GONE);
					buyedOrderAdapter.notifyDataSetChanged();
					order_listview.setSelection(index);
					asset_name_txt.setText(lists.get(index).getPcategoryName() + "资产");
					type_amount_txt.setText(FloatUtil.toTwoDianStringSeparator(lists.get(index).getAssetAmount()));
				}
			}

			@Override
			public void detail(int index, int subIndex) {
				// 交易记录页面跳转
//				UserAssetInfoForAppVo subItem = lists.get(index).getUserAssetList().get(subIndex);
//				if (skipUtils == null)
//					skipUtils = new SkipUtils();
//				skipUtils.skipInstruction(parentActivity, subItem);
				Intent intent = new Intent(parentActivity,AssetsListActivity.class);
				startActivity(intent);
			}
		};
		initView();
		initEvent();
		initData();
		return view;
	}

	private void initHeader() {
		String filePath = SharePrefUtil.getString(parentActivity, SharePrefUtil.ACCOUNT_HEADER, null);
		if (!StringUtil.isEmpty(filePath)) {
			head_circleimg.setTag(filePath);
			ImageDownLoadUtil.setImageBitmap(head_circleimg, filePath);
		}
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("主页-米仓"); // 统计页面
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("主页-米仓");
	}

	private void initView() {
		// TODO Auto-generated method stub
		head_circleimg = (CircleImageView) view.findViewById(R.id.head_circleimg);
		msg_img = (ImageView) view.findViewById(R.id.msg_img);
		asset_name_txt = (TextView) view.findViewById(R.id.asset_name_txt);
		type_amount_txt = (TextView) view.findViewById(R.id.type_amount_txt);
		yesterday_value_txt = (TextView) view.findViewById(R.id.yesterday_value_txt);
		cumulative_value_txt = (TextView) view.findViewById(R.id.cumulative_value_txt);
		order_count_txt = (TextView) view.findViewById(R.id.order_count_txt);

		order_listview = (ListView) view.findViewById(R.id.order_listview);
		buyedOrderAdapter = new BuyedOrderAdapter(parentActivity, lists, resetListView);
		order_listview.setAdapter(buyedOrderAdapter);

		product_type_layout = (RelativeLayout) view.findViewById(R.id.product_type_layout);
		yesterday_layout = (RelativeLayout) view.findViewById(R.id.yesterday_layout);
		cumulative_layout = (RelativeLayout) view.findViewById(R.id.cumulative_layout);
		order_layout = (LinearLayout) view.findViewById(R.id.order_layout);
		nodata_layout = (LinearLayout) view.findViewById(R.id.nodata_layout);
		messageView = (MessageView) view.findViewById(R.id.messageview);
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		product_type_layout.setOnClickListener(this);
		yesterday_layout.setOnClickListener(this);
		cumulative_layout.setOnClickListener(this);
		head_circleimg.setOnClickListener(this);
		msg_img.setOnClickListener(this);
		order_layout.setOnClickListener(this);
	}

	private void initData() {
		// TODO Auto-generated method stub
		ProgressDialogUtil.showProgress(parentActivity);
		new AsyncTask<String, Integer, String>() {
			private AppException ex;

			@Override
			protected String doInBackground(String... params) {
				try {
					ex = null;
					Map<String, String> map = new HashMap<String, String>();	
					map.put("assetSource", "android");
					return WebServiceClient.repertoryInfo(map);
				} catch (AppException e) {
					this.ex = e;
				}
				return null;
			}

			@Override
			protected void onPostExecute(String result) {
				ProgressDialogUtil.closeProgress();
				if (null == result) {
					if (ex != null) {
						ex.makeToast(parentActivity);
					} else {
						MToast.showToast(parentActivity, "获取信息失败！");
					}
					initMsg();
					return;
				} else {
					try {
						JSONObject jo = new JSONObject(result);
						int state = jo.getInt("state");
						if (state == ConstantValues.LOGIN_ERROR) {
							String msgStr = jo.getString("msg");
							MDialog.showPsdErrorDialog(parentActivity, msgStr);
							return;
						} else if (state == 1) {
							String data = jo.getString("data");
							userRepertoryInfo = JsonUtils.toBean(data, new TypeToken<MRepertory>() {
							}.getType());
							showData();
						} else {
							String msg = jo.getString("msg");
							MToast.showToast(parentActivity, StringUtil.isEmpty(msg) ? "获取信息失败！" : msg);
							initMsg();
						}
					} catch (Exception e) {
						e.printStackTrace();
						MToast.showToast(parentActivity, "数据异常");
						initMsg();
					}
				}
				super.onPostExecute(result);
			}

		}.execute();
	}

	protected void showData() {
		// TODO Auto-generated method stub
		// 资产名称
		asset_name_txt.setText("总资产");
		// 资产总额
		type_amount_txt.setText(FloatUtil.toTwoDianStringSeparator(userRepertoryInfo.getAssetTotalAmount()));
		// 昨日收益
		yesterday_value_txt.setText(FloatUtil.toTwoDianStringSeparator(userRepertoryInfo.getYesterdayRevenue()));
		// 累计收益
		cumulative_value_txt.setText(FloatUtil.toTwoDianStringSeparator(userRepertoryInfo.getTotalRevenue()));
		// 在投资产数
		order_count_txt.setText(String.valueOf(userRepertoryInfo.getAssetTotalCount()));
		if (userRepertoryInfo.getPcategoryList() == null || userRepertoryInfo.getPcategoryList().size() == 0) {
			nodata_layout.setVisibility(View.VISIBLE);
			order_listview.setVisibility(View.GONE);
			order_layout.setVisibility(View.GONE);
		} else {
			nodata_layout.setVisibility(View.GONE);
			order_listview.setVisibility(View.VISIBLE);
			order_layout.setVisibility(View.VISIBLE);
		}
		lists.addAll(userRepertoryInfo.getPcategoryList());
		buyedOrderAdapter.notifyDataSetChanged();

		initMsg();
	}

	private void initMsg() {
		// TODO Auto-generated method stub
		if (msgCountAsyncTask != null) {
			msgCountAsyncTask.cancel(true);
			msgCountAsyncTask = null;
		}
		msgCountAsyncTask = new MsgCountAsyncTask();
		msgCountAsyncTask.execute();
	}

	private class MsgCountAsyncTask extends AsyncTask<Void, Void, String> {
		private AppException ex;

		public MsgCountAsyncTask() {
		}

		@Override
		protected String doInBackground(Void... arg0) {
			try {
				ex = null;
				return WebServiceClient.notReadCount();
			} catch (AppException e) {
				this.ex = e;
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			if (null == result) {
				parentActivity.getRadioBtn2().setImgBg(R.drawable.f_3_2, R.drawable.f_3_1);
				messageView.setText("");
				initHeader();
				return;
			} else {
				try {
					JSONObject jo = new JSONObject(result);
					int state = jo.getInt("state");
					if (state == ConstantValues.LOGIN_ERROR) {
						String msgStr = jo.getString("msg");
						MDialog.showPsdErrorDialog(parentActivity, msgStr);
						return;
					} else if (state == 1) {
						String data = jo.getString("data");
						if (!StringUtil.isEmpty(data)) {							
							if (Integer.parseInt(data) > 0) {
								notReadCount = Integer.parseInt(data);
								parentActivity.getRadioBtn2().setImgBg(R.drawable.f_4_2, R.drawable.f_4_1);
								messageView.setText(data);
							} else {
								parentActivity.getRadioBtn2().setImgBg(R.drawable.f_3_2, R.drawable.f_3_1);
								messageView.setText("");
							}
						}else{
							messageView.setText("");
							parentActivity.getRadioBtn2().setImgBg(R.drawable.f_3_2, R.drawable.f_3_1);
						}						
					}else{
						parentActivity.getRadioBtn2().setImgBg(R.drawable.f_3_2, R.drawable.f_3_1);
						messageView.setText("");
					}
				} catch (Exception e) {
					parentActivity.getRadioBtn2().setImgBg(R.drawable.f_3_2, R.drawable.f_3_1);
					messageView.setText("");
					e.printStackTrace();
				} finally {
					initHeader();
				}
			}
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.product_type_layout:
			// 点击总资产
			intent.setClass(parentActivity, AssetsListActivity.class);
			startActivity(intent);
			break;
		case R.id.yesterday_layout:
			// 点击昨日收益
			intent.setClass(parentActivity, SingleDayGainActivity.class);
			startActivity(intent);
			break;
		case R.id.cumulative_layout:
			// 点击累计收益
			intent.setClass(getActivity(), AccumulActivity.class);
			startActivity(intent);
			break;
		case R.id.head_circleimg:
			intent.setClass(getActivity(), PersonCenterActivity.class);
			startActivity(intent);
			break;
		case R.id.msg_img:
			intent.putExtra("not_read_count", notReadCount);
			intent.setClass(getActivity(), MyInfomationActivity.class);
			startActivityForResult(intent, 7890);
			break;
		case R.id.order_layout:
			intent.setClass(parentActivity, AssetsListActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 7890) {
			if (resultCode == parentActivity.RESULT_OK) {
				notReadCount = data.getIntExtra("not_read_count", 0);				
				if (notReadCount > 0) {
					parentActivity.getRadioBtn2().setImgBg(R.drawable.f_4_2, R.drawable.f_4_1);
					messageView.setText(String.valueOf(notReadCount));
				} else {
					parentActivity.getRadioBtn2().setImgBg(R.drawable.f_3_2, R.drawable.f_3_1);
					messageView.setText("");
				}
			}
		}
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if (msgCountAsyncTask != null) {
			msgCountAsyncTask.cancel(true);
			msgCountAsyncTask = null;
		}
	}
}
