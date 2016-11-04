package com.miduo.financialmanageclient.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.NPlannerResult;
import com.miduo.financialmanageclient.bean.NPlannerResult.NPlannerBean;
import com.miduo.financialmanageclient.bean.ReplacePlannerResult;
import com.miduo.financialmanageclient.ui.adapter.SelectPlannerAdapter;
import com.miduo.financialmanageclient.ui.adapter.SelectPlannerAdapter.ViewHolder;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.widget.LoadListView;
import com.miduo.financialmanageclient.widget.LoadListView.ILoadListener;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.analytics.MobclickAgent;

/**
 * 选择理财师页面
 * 
 * @author huozhenpeng
 * 
 */
public class SelectFinancialPlannerActivity extends BaseActivity implements
		OnClickListener {

	private LoadListView listview;
	private TextView txt_title;
	private ImageView iv_left;
	private TextView txt_right;
	private SelectPlannerAdapter adapter;
	private List<NPlannerBean> lists;
	private TextView tv_select;
	private ViewHolder holder;
	private NPlannerResult nPlannerResult;
	private Map<String, String> map;
	private ImageLoader imageloader = null;
	private int pageSize = 10;
	private int pageNum = 1;
	private String tips = "正在加载...";
	// 是否从转让进入此页面
	private boolean isTurnFlag = false;
	private ReplacePlannerResult bindResult;
	private Map<String,String> planner_map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_selectfinancialplanner);
		initView();
		initEvent();
		ProgressDialogUtil.showProgress(this);
		initData();
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("选择理财顾问"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("选择理财顾问"); //
		MobclickAgent.onPause(this);
	}

	private void initData() {
		txt_title.setText("选择理财顾问");
		map.clear();
		map.put("pageNum", pageNum + "");
		map.put("pageSize", pageSize + "");
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					nPlannerResult = WebServiceClient.getPlannerResult(map);
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
				ProgressDialogUtil.closeProgress();
				if (nPlannerResult != null) {
					if (nPlannerResult.getState() == 1) {
						if (nPlannerResult.getData().size() < pageSize) {
							tips = "无更多数据";
							listview.gettips().setText(tips);
							handler.sendEmptyMessageDelayed(0x03, 1000);
						}
						lists.addAll(nPlannerResult.getData());
						if (adapter == null) {
							adapter = new SelectPlannerAdapter(
									SelectFinancialPlannerActivity.this, lists,
									imageloader);
							listview.addfootView();
							listview.setAdapter(adapter);
						} else {
							adapter.notifyDataSetChanged();
						}
						handler.sendEmptyMessageDelayed(0x03, 200);
					} else {
						if (nPlannerResult.getState() == ConstantValues.LOGIN_ERROR) {
							MDialog.showPsdErrorDialog(
									SelectFinancialPlannerActivity.this,
									nPlannerResult.getMsg());
						} else {
							MToast.showToast(
									SelectFinancialPlannerActivity.this,
									"获取理财师信息失败");
						}

					}
				}
				break;
			case 0x02:
				ProgressDialogUtil.closeProgress();
				break;
			case 0x03:
				listview.loadComplete();
				adapter.notifyDataSetChanged();
				break;
			case 0x04:
				if(bindResult!=null)
				{
					if(bindResult.getState()==1)
					{
						//跳首页
						Intent intent=new Intent();
						MyApplication.home_refresh = true;
						MyApplication.home_index = 0;// 指定跳到产品中心
						intent.setClass(SelectFinancialPlannerActivity.this, HomeActivity.class);
						startActivity(intent);
						finish();
					}
					else
					{
						MToast.showToast(SelectFinancialPlannerActivity.this, bindResult.getMsg());
					}
					
				}
				else
				{
					MToast.showToast(SelectFinancialPlannerActivity.this, "绑定理财师失败");
				}
				break;

			default:
				break;
			}
		};
	};

	private void initEvent() {
		iv_left.setOnClickListener(this);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				lists.get(position).setIsopen(!lists.get(position).isIsopen());
				for (int i = 0; i < lists.size(); i++) {
					if (i != position)
						lists.get(i).setIsopen(false);
				}
				holder = ((ViewHolder) view.getTag());
				holder.ll_bottom.setVisibility(View.VISIBLE);
				holder.tv_select.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						if (isTurnFlag) {//登录页面进来的
							iv_left.setVisibility(View.INVISIBLE);
							iv_left.setOnClickListener(null);
							planner_map.clear();
							planner_map.put("facode", lists.get(position).getFaCode());
							ProgressDialogUtil.showProgress(SelectFinancialPlannerActivity.this);
							new Thread( new Runnable() {
								public void run() {
									try {
										bindResult=WebServiceClient.getBindPlannerResult(planner_map);
										handler.sendEmptyMessage(0x04);
									} catch (AppException e) {
										handler.sendEmptyMessage(0x02);
										e.printStackTrace();
									}
								}
							}).start();

						} else {
							Intent intent = new Intent();
							intent.putExtra("PLANNER_CODE", lists.get(position)
									.getFaCode());
							SelectFinancialPlannerActivity.this.setResult(
									RESULT_OK, intent);
							SelectFinancialPlannerActivity.this.finish();
						}
					}
				});
				adapter.notifyDataSetChanged();
			}
		});

		listview.setInterface(new ILoadListener() {

			@Override
			public void onLoad() {
				loadMore();
			}
		});
	}

	/**
	 * 加载更多
	 */
	private void loadMore() {
		pageNum++;
		initData();
		
	}

	private void initView() {
		isTurnFlag = getIntent().getBooleanExtra("is_turn", false);
		imageloader = ImageLoader.getInstance();
		imageloader.init(ImageLoaderConfiguration.createDefault(this));
		txt_title = (TextView) this.findViewById(R.id.title_txt);
		txt_right = (TextView) this.findViewById(R.id.right_txt);
		txt_right.setVisibility(View.INVISIBLE);
		iv_left = (ImageView) this.findViewById(R.id.left_img);
		listview = (LoadListView) this.findViewById(R.id.lv_planner);
		lists = new ArrayList<NPlannerBean>();
		map = new HashMap<String, String>();
		planner_map=new HashMap<String,String>();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_img:
			finish();
			break;

		default:
			break;
		}
	}

}
