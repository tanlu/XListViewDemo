package com.miduo.financialmanageclient.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.bean.NAssetsResult;
import com.miduo.financialmanageclient.bean.UserAssetInfoForAppVo;
import com.miduo.financialmanageclient.common.SkipUtils;
import com.miduo.financialmanageclient.ui.adapter.AssetsListAdapter;
import com.miduo.financialmanageclient.ui.adapter.AssetsListAdapter.ViewHolder;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.widget.LoadListView;
import com.miduo.financialmanageclient.widget.LoadListView.ILoadListener;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

/**
 * 米多资产列表
 * 
 * @author huozhenpeng
 * 
 */
public class AssetsListActivity extends GesterSetBaseActivity implements OnClickListener {

	private TextView text_title;
	private TextView right_text;
	private ImageView left_img;
	private LinearLayout ll_first;
	private LinearLayout ll_second;
	private LinearLayout ll_three;
	private int width1_3;// 三分之一屏幕宽度
	private View view;
	private int lastPosition;
	private AssetsListAdapter adapter;
	private LoadListView listview;
	private TextView tv_tobepaid;
	private TextView tv_tobepaidnum;
	private TextView tv_holdin;
	private TextView tv_holdinnum;
	private TextView tv_histroy;
	private TextView tv_histroynum;
	private NAssetsResult nAssetsResult;
	private HashMap<String, String> map;
	private int page = 1;
	private int size = 10;
	private int assetTabNum = 2;
	private List<UserAssetInfoForAppVo> nLists;
	private String tips = "正在加载...";
	private LinearLayout ll_rice_nodata;
	private LinearLayout ll_rice_havedata;
	private SkipUtils skipUtils;
	private TextView tv_noproduct;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_assetslist);
		initView();
		initEvent();
		initData();
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("米多资产列表"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("米多资产列表"); //
		MobclickAgent.onPause(this);
	}

	private void initData() {
		text_title.setText("米多资产列表");
		map.clear();
		ProgressDialogUtil.showProgress(this);
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					map.put("assetTabNum", assetTabNum + "");
					map.put("pageSize", size + "");
					map.put("pageNo", page + "");
					map.put("source", "android");
					nAssetsResult = WebServiceClient.getAssetsResult(map);
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
			ProgressDialogUtil.closeProgress();
			switch (msg.what) {
			case 0x01:
				if (nAssetsResult != null) {
					if (nAssetsResult.getState() == 1) {
						tv_holdinnum.setText(nAssetsResult.getData().getAssetHoldCount() + "笔");
						tv_tobepaidnum.setText(nAssetsResult.getData().getAssetUnPayedCount() + "笔");
						tv_histroynum.setText(nAssetsResult.getData().getAssetHistoryCount() + "笔");
						if (nAssetsResult.getData().getUserAssetList().size() == 0 && page == 1)// 无资产信息
						{
							ll_rice_havedata.setVisibility(View.GONE);
							ll_rice_nodata.setVisibility(View.VISIBLE);
							switch (assetTabNum) {
							case 2:// 持有中
								tv_noproduct.setText("您当前未持有任何产品");
								break;
							case 1:// 待支付
								tv_noproduct.setText("您当前没有待支付产品");
								break;
							case 3:
								tv_noproduct.setText("您当前没有历史订单");
								break;

							default:
								break;
							}
						} else {
							ll_rice_havedata.setVisibility(View.VISIBLE);
							ll_rice_nodata.setVisibility(View.GONE);
							if (nAssetsResult.getData().getUserAssetList().size() < size) {
								tips = "无更多数据";
								listview.gettips().setText(tips);
								handler.sendEmptyMessageDelayed(0x03, 1000);

							}
							nLists.addAll(nAssetsResult.getData().getUserAssetList());
							if (adapter == null) {
								adapter = new AssetsListAdapter(AssetsListActivity.this, nLists);
								listview.addfootView();
								listview.setAdapter(adapter);
								adapter.setThreadStartOrEnd(true);
								adapter.start();
							} else {
								adapter.notifyDataSetChanged();

							}
							handler.sendEmptyMessageDelayed(0x03, 500);
						}
					} else {
						if (nAssetsResult.getState() == ConstantValues.LOGIN_ERROR) {
							MDialog.showPsdErrorDialog(AssetsListActivity.this, nAssetsResult.getMsg());
						} else {
							MToast.showToast(AssetsListActivity.this, nAssetsResult.getMsg());
						}
					}
				}

				break;
			case 0x03:
				listview.loadComplete();
				if (adapter != null) {
					adapter.notifyDataSetChanged();
				}

				break;
			case 0x02:
				break;
			default:
				break;
			}
		};
	};

	private void initEvent() {

		ll_first.setOnClickListener(this);
		ll_second.setOnClickListener(this);
		ll_three.setOnClickListener(this);
		text_title.setOnClickListener(this);
		left_img.setOnClickListener(this);
		listview.setInterface(new ILoadListener() {

			@Override
			public void onLoad() {
				loadMore();
			}
		});
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ViewHolder holder = (ViewHolder) view.getTag();
				if (skipUtils == null)
					skipUtils = new SkipUtils();
				skipUtils.skipInstruction(AssetsListActivity.this, nLists.get(position));
			}
		});

	}

	/**
	 * 加载更多
	 */
	private void loadMore() {
		map.clear();
		page++;
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					map.put("assetTabNum", assetTabNum + "");
					map.put("pageSize", size + "");
					map.put("pageNo", page + "");
					map.put("source", "android");
					nAssetsResult = WebServiceClient.getAssetsResult(map);
					handler.sendEmptyMessage(0x01);
				} catch (AppException e) {
					handler.sendEmptyMessage(0x02);
					e.printStackTrace();
				}

			}
		}).start();

	}

	private void initView() {
		tv_noproduct = (TextView) this.findViewById(R.id.tv_noproduct);
		ll_rice_nodata = (LinearLayout) this.findViewById(R.id.ll_rice_nodata);
		ll_rice_havedata = (LinearLayout) this.findViewById(R.id.ll_rice_havedata);
		nLists = new ArrayList<UserAssetInfoForAppVo>();
		tv_tobepaid = (TextView) this.findViewById(R.id.tv_tobepaid);
		tv_tobepaidnum = (TextView) this.findViewById(R.id.tv_tobepaidnum);
		tv_holdin = (TextView) this.findViewById(R.id.tv_holdin);
		tv_holdinnum = (TextView) this.findViewById(R.id.tv_holdinnum);
		tv_histroy = (TextView) this.findViewById(R.id.tv_history);
		tv_histroynum = (TextView) this.findViewById(R.id.tv_histroynum);
		tv_holdin.setTextColor(Color.parseColor("#2ea7e0"));
		tv_holdinnum.setTextColor(Color.parseColor("#2ea7e0"));
		right_text = (TextView) this.findViewById(R.id.right_txt);
		right_text.setVisibility(View.INVISIBLE);
		text_title = (TextView) this.findViewById(R.id.title_txt);
		left_img = (ImageView) this.findViewById(R.id.left_img);
		ll_first = (LinearLayout) this.findViewById(R.id.ll_first);
		ll_second = (LinearLayout) this.findViewById(R.id.ll_second);
		ll_three = (LinearLayout) this.findViewById(R.id.ll_three);
		view = this.findViewById(R.id.tipsview);
		width1_3 = getWindowManager().getDefaultDisplay().getWidth() / 3;
		listview = (LoadListView) this.findViewById(R.id.listview);
		listview.setAdapter(adapter);
		map = new HashMap<String, String>();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_first:
			initTabColor();
			view.startAnimation(changeLocation(0));
			lastPosition = 0;
			tv_holdin.setTextColor(Color.parseColor("#2ea7e0"));
			tv_holdinnum.setTextColor(Color.parseColor("#2ea7e0"));
			assetTabNum = 2;
			page = 1;
			nLists.clear();
			initData();
			break;

		case R.id.ll_second:
			initTabColor();
			view.startAnimation(changeLocation(1));
			lastPosition = width1_3;
			tv_tobepaid.setTextColor(Color.parseColor("#2ea7e0"));
			tv_tobepaidnum.setTextColor(Color.parseColor("#2ea7e0"));
			assetTabNum = 1;
			page = 1;
			nLists.clear();
			initData();
			break;

		case R.id.ll_three:
			initTabColor();
			view.startAnimation(changeLocation(2));
			lastPosition = width1_3 * 2;
			tv_histroy.setTextColor(Color.parseColor("#2ea7e0"));
			tv_histroynum.setTextColor(Color.parseColor("#2ea7e0"));
			assetTabNum = 3;
			page = 1;
			nLists.clear();
			initData();
			break;
		case R.id.left_img:
			if (adapter != null) {
				adapter.setThreadStartOrEnd(false);
				adapter = null;
			}
			finish();
			break;

		default:
			break;
		}
	}

	private void initTabColor() {
		tv_tobepaid.setTextColor(Color.parseColor("#999999"));
		tv_tobepaidnum.setTextColor(Color.parseColor("#999999"));
		tv_holdin.setTextColor(Color.parseColor("#999999"));
		tv_holdinnum.setTextColor(Color.parseColor("#999999"));
		tv_histroy.setTextColor(Color.parseColor("#999999"));
		tv_histroynum.setTextColor(Color.parseColor("#999999"));
		tips = "正在加载...";
		listview.gettips().setText(tips);
	}

	/**
	 * 修改指示view的位置
	 */
	private TranslateAnimation changeLocation(int index) {
		TranslateAnimation animation = new TranslateAnimation(lastPosition, index * width1_3, 0, 0);
		animation.setFillAfter(true);
		animation.setDuration(500);
		return animation;
	}

}
