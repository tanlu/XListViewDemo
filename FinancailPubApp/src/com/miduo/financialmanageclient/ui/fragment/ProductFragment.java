package com.miduo.financialmanageclient.ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.DataEntity;
import com.miduo.financialmanageclient.bean.NProductCenterInsureResult;
import com.miduo.financialmanageclient.bean.NProductCenterInsureResult.NProductCenterInsureBean;
import com.miduo.financialmanageclient.bean.NProductResult;
import com.miduo.financialmanageclient.bean.NTransferResult;
import com.miduo.financialmanageclient.bean.TransferDataEntity;
import com.miduo.financialmanageclient.common.CommonUtil;
import com.miduo.financialmanageclient.listener.ProductSelectChangeListener;
import com.miduo.financialmanageclient.ui.HomeActivity;
import com.miduo.financialmanageclient.ui.InsuranceProductInfoActivity;
import com.miduo.financialmanageclient.ui.ProdunctInfoActivity;
import com.miduo.financialmanageclient.ui.TransferOrderInfoActivity;
import com.miduo.financialmanageclient.ui.adapter.InsuranceProductAdapter;
import com.miduo.financialmanageclient.ui.adapter.ProductAdaper;
import com.miduo.financialmanageclient.ui.adapter.TransforProductAdapter;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.widget.LoadListView;
import com.miduo.financialmanageclient.widget.LoadListView.ILoadListener;
import com.miduo.financialmanageclient.widget.ProTopView;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

/**
 * 产品中心
 * 
 * @author huozhenpeng
 * 
 */
public class ProductFragment extends Fragment implements OnClickListener,
		ILoadListener {
	private HomeActivity parentActivity;
	private View view;
	private ImageView left_img;
	private TextView title_txt;
	private TextView right_txt;
	private LoadListView lv_product;
	private ProductAdaper productAdaper;
	private List<DataEntity> productLst;
	private Intent intent;
	private LinearLayout ll_transfer;
	private LoadListView lv_transer;
	private TransforProductAdapter transferProductAdapter;
	private List<TransferDataEntity> transList;
	private ProTopView protopview;
	private LinearLayout ll_layout1, ll_layout2, ll_layout3;
	private TextView tv_1, tv_2, tv_3;
	private ImageView iv_1, iv_2, iv_3;
	private boolean flag1 = true, flag2 = true, flag3 = true;// 箭头使用
	private LoadListView lv_insurance;
	private InsuranceProductAdapter insuranceProductAdapter;
	private List<NProductCenterInsureBean> insuranceList;// 保险产品使用
	private boolean isselected1 = true, isselected2 = true, isselected3 = true;
	private Map<String, String> map;
	private NProductResult nProductResult;
	private NProductCenterInsureResult nProductCenterInsureResult;
	private NTransferResult nTransferResult;
	private int page = 1;
	private int size = 10;
	private int flag;// 1,2,3,4,5,6,7
	private String tips = "正在加载...";
	private int orderType = 1;// 1.倒序2.正序
	private int orderKind = 1;// 1.创建日期2.持有收益率3.转让价格4.剩余期限

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_product, null);
		parentActivity = (HomeActivity) getActivity();
		initView();
		initEvent();
		initData();
		return view;
	}

	private void initData() {
		// 请求热销数据
		if (MyApplication.index == 0) {
			ProgressDialogUtil.showProgress(parentActivity);
			page = 1;
			productLst.clear();
			flag = 1;
			miduohotsell();
		}
	}

	private void initView() {
		lv_insurance = (LoadListView) view.findViewById(R.id.lv_insurance);
		left_img = (ImageView) view.findViewById(R.id.left_img);
		left_img.setVisibility(View.GONE);
		title_txt = (TextView) view.findViewById(R.id.title_txt);
		right_txt = (TextView) view.findViewById(R.id.right_txt);
		ll_layout1 = (LinearLayout) view.findViewById(R.id.ll_layout1);
		ll_layout2 = (LinearLayout) view.findViewById(R.id.ll_layout2);
		ll_layout3 = (LinearLayout) view.findViewById(R.id.ll_layout3);
		tv_1 = (TextView) view.findViewById(R.id.tv_1);
		tv_2 = (TextView) view.findViewById(R.id.tv_2);
		tv_3 = (TextView) view.findViewById(R.id.tv_3);
		iv_1 = (ImageView) view.findViewById(R.id.iv_1);
		iv_2 = (ImageView) view.findViewById(R.id.iv_2);
		iv_3 = (ImageView) view.findViewById(R.id.iv_3);
		ll_layout1.setOnClickListener(this);
		ll_layout2.setOnClickListener(this);
		ll_layout3.setOnClickListener(this);
		protopview = (ProTopView) view.findViewById(R.id.protopview);
		right_txt.setVisibility(View.INVISIBLE);
		lv_product = (LoadListView) view.findViewById(R.id.lv_product);
		productLst = new ArrayList<DataEntity>();

		title_txt.setText("产品中心");
		ll_transfer = (LinearLayout) view.findViewById(R.id.ll_transfer);
		lv_transer = (LoadListView) view.findViewById(R.id.lv_transfer);
		transList = new ArrayList<TransferDataEntity>();
		insuranceList = new ArrayList<NProductCenterInsureBean>();

		map = new HashMap<String, String>();
	}

	private void initEvent() {
		lv_product.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (!CommonUtil.checkLogin(parentActivity)) {
					return;
				}
				intent = new Intent(parentActivity, ProdunctInfoActivity.class);
				intent.putExtra("product_info", productLst.get(position));
				parentActivity.startActivity(intent);
			}
		});
		lv_transer.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (!CommonUtil.checkLogin(parentActivity)) {
					return;
				}
				intent = new Intent(parentActivity,
						TransferOrderInfoActivity.class);
				intent.putExtra("transfer_info", transList.get(position));
				parentActivity.startActivity(intent);
			}
		});
		lv_insurance.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (!CommonUtil.checkLogin(parentActivity)) {
					return;
				}
				MyApplication.index = 5;
				Intent intent = new Intent(parentActivity,
						InsuranceProductInfoActivity.class);
				MyApplication.ninsurebean = insuranceList.get(position);
				startActivity(intent);

			}
		});
		protopview
				.setProductSelectChangeListener(new ProductSelectChangeListener() {

					@Override
					public void onIndexChange(int index) {
						if(MyApplication.index==index){
							return;
						}
						ll_transfer.setVisibility(View.GONE);
						lv_product.setVisibility(View.VISIBLE);
						lv_transer.setVisibility(View.GONE);
						lv_insurance.setVisibility(View.GONE);
						tips = "正在加载...";
						lv_product.gettips().setText(tips);
						
						switch (index) {
						case 0:// 米多热销							
							MyApplication.index = 0;
							ProgressDialogUtil.showProgress(parentActivity);
							page = 1;
							productLst.clear();
							flag = 1;
							miduohotsell();
							break;
						case 10:// 高流动性类
							ProgressDialogUtil.showProgress(parentActivity);
							MyApplication.index = 1;
							page = 1;
							productLst.clear();
							flag = 2;
							highflow();
							break;
						case 20:// 固定收益类
							ProgressDialogUtil.showProgress(parentActivity);
							MyApplication.index = 2;
							page = 1;
							productLst.clear();
							flag = 3;
							fixedincome();
							break;
						case 30:// 浮动收益类
							ProgressDialogUtil.showProgress(parentActivity);
							MyApplication.index = 3;
							page = 1;
							productLst.clear();
							flag = 4;
							floatingprofit();
							break;
						case 40:// 另类投资
							ProgressDialogUtil.showProgress(parentActivity);
							MyApplication.index = 4;
							page = 1;
							productLst.clear();
							flag = 5;
							alternativeasset();
							break;
						case 50:// 保险产品
							ProgressDialogUtil.showProgress(parentActivity);
							MyApplication.index = 5;
							page = 1;
							flag = 6;
							lv_product.setVisibility(View.GONE);
							lv_transer.setVisibility(View.GONE);
							lv_insurance.setVisibility(View.VISIBLE);
							insuranceList.clear();
							insuranceproduct();
							break;
						case 60:// 转让专区
							ProgressDialogUtil.showProgress(parentActivity);
							MyApplication.index = 6;
							page = 1;
							flag = 7;
							transList.clear();
							isselected1 = true;
							isselected2 = true;
							isselected3 = true;
							if (transferProductAdapter != null) {
								transferProductAdapter = null;
								lv_transer.removeFootView();
							}
							transferproduct();
							break;

						default:
							break;
						}
					}

				});
		protopview.setIndex(MyApplication.index);
		// MyApplication.index = 0;

		lv_product.setInterface(this);
		lv_insurance.setInterface(this);
		lv_transer.setInterface(this);

	}

	/**
	 * 加载更多
	 */
	protected void loadMore() {
		switch (flag) {
		case 1:
			miduohotsell();
			break;
		case 2:
			highflow();
			break;
		case 3:
			fixedincome();
			break;
		case 4:
			floatingprofit();
			break;
		case 5:
			alternativeasset();
			break;
		case 6:
			insuranceproduct();
			break;
		case 7:
			transferLoad();
			break;

		default:
			break;
		}
	}

	/**
	 * 保险产品
	 */
	protected void insuranceproduct() {
		map.clear();
		map.put("page", page + "");
		map.put("size", size + "");
		map.put("categoryId", "");
		map.put("ishot", "");
		map.put("productType", 1 + "");
		requestInsureData();
	}

	/**
	 * 另类投资
	 */
	protected void alternativeasset() {
		map.clear();
		map.put("page", page + "");
		map.put("size", size + "");
		map.put("categoryId", 4 + "");
		map.put("ishot", "");
		map.put("productType", 0 + "");
		requestData();
	}

	/**
	 * 浮动收益
	 */
	protected void floatingprofit() {
		map.clear();
		map.put("page", page + "");
		map.put("size", size + "");
		map.put("categoryId", 3 + "");
		map.put("ishot", "");
		map.put("productType", 0 + "");
		requestData();
	}

	/**
	 * 固定收益
	 */
	protected void fixedincome() {
		map.clear();
		map.put("page", page + "");
		map.put("size", size + "");
		map.put("categoryId", 2 + "");
		map.put("ishot", "");
		map.put("productType", 0 + "");
		requestData();
	}

	/**
	 * 高流动性
	 */
	protected void highflow() {
		map.clear();
		map.put("page", page + "");
		map.put("size", size + "");
		map.put("categoryId", 1 + "");
		map.put("ishot", "");
		map.put("productType", 0 + "");
		requestData();
	}

	/**
	 * 米多热销
	 */
	private void miduohotsell() {
		map.clear();
		map.put("page", page + "");
		map.put("size", size + "");
		map.put("categoryId", "");
		map.put("ishot", 1 + "");
		map.put("productType", 0 + "");
		requestData();
	}

	/**
	 * 转让
	 */
	private void transferLoad() {
		map.clear();
		map.put("orderKind", orderKind + "");
		map.put("orderType", orderType + "");
		map.put("page", page + "");
		map.put("size", size + "");
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					nTransferResult = WebServiceClient.getTransferProduct(map);
					handler.sendEmptyMessage(0x06);
				} catch (AppException e) {
					ProgressDialogUtil.closeProgress();
					handler.sendEmptyMessage(0x02);
					e.printStackTrace();
				}
			}
		}).start();
	}

	/**
	 * 请求服务器数据
	 */
	private void requestData() {
		lv_product.gettips().setText(tips);
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					nProductResult = WebServiceClient.getProductResult(map);
					handler.sendEmptyMessage(0x01);
				} catch (AppException e) {
					ProgressDialogUtil.closeProgress();
					handler.sendEmptyMessage(0x02);
					e.printStackTrace();
				}
			}
		}).start();
	}

	/**
	 * 请求保险数据
	 */
	private void requestInsureData() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					nProductCenterInsureResult = WebServiceClient
							.getInsureProductResult(map);
					handler.sendEmptyMessage(0x04);
				} catch (AppException e) {
					ProgressDialogUtil.closeProgress();
					handler.sendEmptyMessage(0x02);
					e.printStackTrace();
				}
			}
		}).start();
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			ProgressDialogUtil.closeProgress();
			switch (msg.what) {
			case 0x01:
				if (nProductResult != null) {
					if (nProductResult.getState() == 1)// 成功
					{
						if (nProductResult.getData().size() == 0) {
							tips = "无更多数据";
							lv_product.gettips().setText(tips);
							handler.sendEmptyMessageDelayed(0x03, 1000);
						}
						productLst.addAll(nProductResult.getData());
						if (productAdaper == null) {
							productAdaper = new ProductAdaper(parentActivity,
									productLst);
							lv_product.addfootView();
							lv_product.setAdapter(productAdaper);
						} else {
							productAdaper.notifyDataSetChanged();
						}
						handler.sendEmptyMessageDelayed(0x03, 200);

					} else {
						if (nProductResult.getState() == ConstantValues.LOGIN_ERROR) {
							MDialog.showPsdErrorDialog(parentActivity,
									nProductResult.getMsg());
						} else {
							MToast.showToast(parentActivity, "获取产品信息失败");
						}
					}
				}

				break;
			case 0x02:
				tips = "";
				if (productAdaper != null) {
					lv_product.gettips().setText(tips);
					productAdaper.notifyDataSetChanged();
					handler.sendEmptyMessageDelayed(0x03, 200);
				}
				if (insuranceProductAdapter != null) {
					lv_insurance.gettips().setText(tips);
					insuranceProductAdapter.notifyDataSetChanged();
					handler.sendEmptyMessageDelayed(0x05, 200);
				}
				if (transferProductAdapter != null) {
					lv_transer.gettips().setText(tips);
					transferProductAdapter.notifyDataSetChanged();
					handler.sendEmptyMessageDelayed(0x07, 200);
				}
				break;
			case 0x03:
				lv_product.loadComplete();
				productAdaper.notifyDataSetChanged();
				break;
			case 0x04:
				if (nProductCenterInsureResult != null) {
					if (nProductCenterInsureResult.getState() == 1)// 成功
					{
						if (nProductCenterInsureResult.getData().size() == 0) {
							tips = "无更多数据";
							lv_insurance.gettips().setText(tips);
							handler.sendEmptyMessageDelayed(0x05, 1000);
						}
						insuranceList.addAll(nProductCenterInsureResult
								.getData());
						if (insuranceProductAdapter == null) {

							lv_insurance.addfootView();
							insuranceProductAdapter = new InsuranceProductAdapter(
									parentActivity, insuranceList);
							lv_insurance.setAdapter(insuranceProductAdapter);
						} else {
							insuranceProductAdapter.notifyDataSetChanged();
						}
						handler.sendEmptyMessageDelayed(0x05, 200);

					} else {
						if (nProductCenterInsureResult.getState() == ConstantValues.LOGIN_ERROR) {
							MDialog.showPsdErrorDialog(parentActivity,
									nProductCenterInsureResult.getMsg());
						} else {
							MToast.showToast(parentActivity, "获取产品信息失败");
						}
					}
				}
				break;
			case 0x05:
				lv_insurance.loadComplete();
				insuranceProductAdapter.notifyDataSetChanged();
				break;
			case 0x06:
				if (nTransferResult != null) {
					if (nTransferResult.getState() == 1) {
						if (nTransferResult.getData().size() == 0) {
							tips = "无更多数据";
							lv_transer.gettips().setText(tips);
							handler.sendEmptyMessageDelayed(0x07, 1000);
						}
						transList.addAll(nTransferResult.getData());
						if (transferProductAdapter == null) {
							Log.e("abc", "aaaaaa+aaaaaa");
							transferProductAdapter = new TransforProductAdapter(
									parentActivity, transList, 0);
							lv_transer.addfootView();
							lv_transer.setAdapter(transferProductAdapter);
						} else {
							transferProductAdapter.notifyDataSetChanged();
						}
						handler.sendEmptyMessageDelayed(0x07, 200);
					} else {
						if (nTransferResult.getState() == ConstantValues.LOGIN_ERROR) {
							MDialog.showPsdErrorDialog(parentActivity,
									nTransferResult.getMsg());
						} else {
							MToast.showToast(parentActivity, "获取产品信息失败");
						}
					}
				}

				break;
			case 0x07:
				lv_transer.loadComplete();
				transferProductAdapter.notifyDataSetChanged();
				break;
			default:
				break;
			}
		};
	};

	/**
	 * 转让专区
	 */
	protected void transferproduct() {
		transList.clear();
		ll_layout1.setBackgroundResource(R.drawable.frame_bg_gray);
		ll_layout2.setBackgroundResource(R.drawable.frame_bg_gray);
		ll_layout3.setBackgroundResource(R.drawable.frame_bg_gray);
		tv_1.setTextColor(Color.parseColor("#999999"));
		tv_2.setTextColor(Color.parseColor("#999999"));
		tv_3.setTextColor(Color.parseColor("#999999"));
		iv_1.setVisibility(View.GONE);
		iv_2.setVisibility(View.GONE);
		iv_3.setVisibility(View.GONE);
		iv_1.setRotation(0);
		iv_2.setRotation(0);
		iv_3.setRotation(0);
		lv_product.setVisibility(View.GONE);
		lv_insurance.setVisibility(View.GONE);
		lv_transer.setVisibility(View.VISIBLE);
		ll_transfer.setVisibility(View.VISIBLE);
		map.clear();
		map.put("orderKind", orderKind + "");
		map.put("orderType", orderType + "");
		map.put("page", page + "");
		map.put("size", size + "");
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					nTransferResult = WebServiceClient.getTransferProduct(map);
					handler.sendEmptyMessage(0x06);
				} catch (AppException e) {
					handler.sendEmptyMessage(0x02);
					e.printStackTrace();
				}
			}
		}).start();
		// transferProductAdapter.setType(0);
		// transferProductAdapter.notifyDataSetChanged();
	}

	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("主页-产品"); // 统计页面
	}

	@Override
	public void onPause() {
		Log.e("abc", "onpause");
		super.onPause();
		MobclickAgent.onPageEnd("主页-产品");
	}

	@Override
	public void onClick(View v) {
		ll_layout1.setBackgroundResource(R.drawable.frame_bg_gray);
		ll_layout2.setBackgroundResource(R.drawable.frame_bg_gray);
		ll_layout3.setBackgroundResource(R.drawable.frame_bg_gray);
		tv_1.setTextColor(Color.parseColor("#999999"));
		tv_2.setTextColor(Color.parseColor("#999999"));
		tv_3.setTextColor(Color.parseColor("#999999"));
		switch (v.getId()) {
		case R.id.ll_layout1:
			// iv_1.setVisibility(View.VISIBLE);
			ll_layout1.setBackgroundResource(R.drawable.frame_bg_blue);
			tv_1.setTextColor(Color.parseColor("#2da8df"));
			if (isselected1) {
				transList.clear();
				iv_1.setVisibility(View.GONE);
				iv_2.setVisibility(View.GONE);
				iv_3.setVisibility(View.GONE);
				transferProductAdapter.setType(1);
				iv_1.setRotation(flag1 ? 0 : 180);
				flag2 = true;
				flag3 = true;
				flag1 = !flag1;

				isselected1 = false;
				isselected2 = true;
				isselected3 = true;
				// 请求数据
				orderKind = 2;
				orderType = 1;
				page = 1;
				tips = "正在加载...";
				lv_transer.gettips().setText(tips);
				ProgressDialogUtil.showProgress(parentActivity);
				transferLoad();
			}
			break;
		case R.id.ll_layout2:
			ll_layout2.setBackgroundResource(R.drawable.frame_bg_blue);
			tv_2.setTextColor(Color.parseColor("#2da8df"));
			if (isselected2) {
				transList.clear();
				// iv_2.setVisibility(View.VISIBLE);
				iv_2.setVisibility(View.GONE);
				iv_1.setVisibility(View.GONE);
				iv_3.setVisibility(View.GONE);
				transferProductAdapter.setType(2);
				iv_2.setRotation(flag2 ? 0 : 180);
				flag2 = !flag2;
				flag1 = true;
				flag3 = true;
				isselected2 = false;
				isselected1 = true;
				isselected3 = true;
				orderKind = 4;
				orderType = 2;
				page = 1;
				tips = "正在加载...";
				lv_transer.gettips().setText(tips);
				ProgressDialogUtil.showProgress(parentActivity);
				transferLoad();
			}

			break;
		case R.id.ll_layout3:
			ll_layout3.setBackgroundResource(R.drawable.frame_bg_blue);
			tv_3.setTextColor(Color.parseColor("#2da8df"));
			if (isselected3) {
				transList.clear();
				// iv_3.setVisibility(View.VISIBLE);
				iv_3.setVisibility(View.GONE);
				iv_1.setVisibility(View.GONE);
				iv_2.setVisibility(View.GONE);
				transferProductAdapter.setType(3);
				iv_3.setRotation(flag3 ? 0 : 180);
				flag3 = !flag3;
				flag1 = true;
				flag2 = true;

				isselected1 = true;
				isselected2 = true;
				isselected3 = false;
				orderKind = 3;
				orderType = 2;
				page = 1;
				tips = "正在加载...";
				lv_transer.gettips().setText(tips);
				ProgressDialogUtil.showProgress(parentActivity);
				transferLoad();
			}

			break;

		default:
			break;

		}
		transferProductAdapter.notifyDataSetChanged();

	}

	@Override
	public void onLoad() {
		page++;
		loadMore();
	}

	@Override
	public void onStop() {
		MyApplication.index = 0;
		super.onStop();
	}

}
