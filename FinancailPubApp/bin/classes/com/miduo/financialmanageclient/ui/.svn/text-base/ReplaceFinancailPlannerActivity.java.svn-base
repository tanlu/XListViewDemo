package com.miduo.financialmanageclient.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.NPlannerResult;
import com.miduo.financialmanageclient.bean.NPlannerResult.NPlannerBean;
import com.miduo.financialmanageclient.bean.NPlannerTempBean;
import com.miduo.financialmanageclient.bean.NSingelPlannerResult;
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
 * 更换理财师页面
 * 
 * @author huozhenpeng
 * 
 */
public class ReplaceFinancailPlannerActivity extends BaseActivity implements OnClickListener {

	private ImageView left_img;
	private TextView title_txt;
	private TextView right_txt;
	private TextView tv_faselect;
	private LoadListView listview;
	private SelectPlannerAdapter adapter;
	private List<NPlannerBean> lists;
	private List<NPlannerBean> templists;
	private ViewHolder holder;
	private EditText et_plannercode;
	private String value;
	private int index = -1;
	private TextView tv_faname;
	private ImageLoader imageloader = null;
	private NPlannerResult nPlannerResult;
	private Map<String, String> map;
	private String tips = "正在加载...";
	private int pageSize = 10;
	private int pageNum = 1;
	private String interCode;
	private NSingelPlannerResult nSinglePlannerResult;
	private boolean flag;
	private RelativeLayout rl_layout;
	private ImageView iv_arrow;
	private boolean isopen = false;// 记录箭头打开还是关闭
	// 是否从转让进入此页面
	private boolean isTurnFlag = false;
	private Map<String, String> planner_map;
	private ReplacePlannerResult bindResult;
	private String FACode;// 只是FA和SFA的

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_replacefinancailplanner);
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
		map.clear();
		map.put("pageNum", pageNum + "");
		map.put("pageSize", pageSize + "");
		new Thread(new Runnable() {
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

	/**
	 * 根据理财师id查询理财师
	 */
	private void qureyIFAByCode() {
		map.clear();
		map.put("interCode", interCode);
		ProgressDialogUtil.showProgress(this);
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					nSinglePlannerResult = WebServiceClient.getSinglePlannerResult(map);
					handler.sendEmptyMessage(0x04);
				} catch (AppException e) {
					ProgressDialogUtil.closeProgress();
					handler.sendEmptyMessage(0x02);
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void initEvent() {
		rl_layout.setOnClickListener(this);
		left_img.setOnClickListener(this);
		tv_faselect.setOnClickListener(this);
		listview.setInterface(new ILoadListener() {

			@Override
			public void onLoad() {
				loadMore();
			}
		});
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
				listview.setSelection(position);
				index = -1;
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
						if (isTurnFlag) {// 登录页面进来的
							FACode = lists.get(position).getFaCode();
							commitFA();
						} else {
							Intent intent = new Intent();
							intent = new Intent(ReplaceFinancailPlannerActivity.this,
									ConfirmReplaceFinancailPlannerActivity.class);
							MyApplication.new_planner = new NPlannerTempBean(lists.get(position).getRealName(), lists
									.get(position).getFaCode() + "", lists.get(position).getContact(),lists.get(position).getLevelId());
							ReplaceFinancailPlannerActivity.this.startActivity(intent);
						}
					}
				});
				adapter.notifyDataSetChanged();
			}
		});

		et_plannercode.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (et_plannercode.getText().toString().length() > 5) {
					et_plannercode.setText(et_plannercode.getText().toString().substring(0, 5));
					Selection.setSelection((Spannable) et_plannercode.getText(), et_plannercode.getText().length());

				}
				if (et_plannercode.getText().toString().length() >= 5) {
					flag = true;
					lists.clear();
					interCode = et_plannercode.getText().toString();
					qureyIFAByCode();
				} else {
					if (flag) {
						tv_faselect.setVisibility(View.GONE);
						tv_faname.setVisibility(View.GONE);
						lists.clear();
						lists.addAll(templists);
						adapter = null;
						ProgressDialogUtil.showProgress(ReplaceFinancailPlannerActivity.this);
						initData();
						flag = false;
					}
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0x01:
				ProgressDialogUtil.closeProgress();
				if (nPlannerResult != null) {
					if (nPlannerResult.getState() == 1) {
						if (nPlannerResult.getData().size() == 0) {
							tips = "无更多数据";
							listview.gettips().setText(tips);
							handler.sendEmptyMessageDelayed(0x03, 1000);
						}
						lists.addAll(nPlannerResult.getData());
						templists.addAll(nPlannerResult.getData());
						if (adapter == null) {
							adapter = new SelectPlannerAdapter(ReplaceFinancailPlannerActivity.this, lists, imageloader);
							listview.addfootView();
							listview.setAdapter(adapter);
						} else {
							adapter.notifyDataSetChanged();
						}
						handler.sendEmptyMessageDelayed(0x03, 200);
					} else {
						if (nPlannerResult.getState() == ConstantValues.LOGIN_ERROR) {
							MDialog.showPsdErrorDialog(ReplaceFinancailPlannerActivity.this, nPlannerResult.getMsg());
						} else {
							MToast.showToast(ReplaceFinancailPlannerActivity.this, "获取理财师信息失败");
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
				ProgressDialogUtil.closeProgress();
				listview.removeFootView();
				if (nSinglePlannerResult != null) {
					if (nSinglePlannerResult.getState() == 1) {
						if (adapter == null) {
							adapter = new SelectPlannerAdapter(ReplaceFinancailPlannerActivity.this, lists, imageloader);
							listview.setAdapter(adapter);
						} else {
							if (nSinglePlannerResult.getData() == null) {
								return;
							} else {
								if (nSinglePlannerResult.getData().getLevelId() == 2) {// IFA
									tv_faselect.setVisibility(View.GONE);
									tv_faname.setVisibility(View.GONE);
									lists.clear();
									lists.add(nSinglePlannerResult.getData());
									adapter.notifyDataSetChanged();
									listview.setVisibility(View.VISIBLE);
									isopen = true;
									iv_arrow.setRotation(isopen ? 180 : 0);

								} else {
									FACode = nSinglePlannerResult.getData().getFaCode();
									tv_faselect.setVisibility(View.VISIBLE);
									tv_faname.setVisibility(View.VISIBLE);
									String name = nSinglePlannerResult.getData().getRealName();
									char[] chr = null;
									if (!TextUtils.isEmpty(name)) {
										chr = new char[name.length()];
										chr[0] = name.charAt(0);
										for (int i = 1; i < name.length(); i++) {
											chr[i] = '*';
										}
									}
									tv_faname.setText(new String(chr));
								}
							}
						}
					} else {
						if (nSinglePlannerResult.getState() == ConstantValues.LOGIN_ERROR) {
							MDialog.showPsdErrorDialog(ReplaceFinancailPlannerActivity.this,
									nSinglePlannerResult.getMsg());
						} else {
							MToast.showToast(ReplaceFinancailPlannerActivity.this, "获取理财师信息失败");
						}
					}
				}
				break;
			case 0x05:
				ProgressDialogUtil.closeProgress();
				if (bindResult != null) {
					if (bindResult.getState() == 1) {
						// 跳首页
						Intent intent = new Intent();
						MyApplication.home_index = 0;
						MyApplication.home_refresh = true;
						intent.setClass(ReplaceFinancailPlannerActivity.this, HomeActivity.class);
						startActivity(intent);
						finish();
					} else {
						MToast.showToast(ReplaceFinancailPlannerActivity.this, bindResult.getMsg());
					}
				} else {
					MToast.showToast(ReplaceFinancailPlannerActivity.this, "绑定理财师失败");
				}
				break;
			default:
				break;
			}
		};
	};

	private void initView() {
		isTurnFlag = getIntent().getBooleanExtra("is_turn", false);
		imageloader = ImageLoader.getInstance();
		imageloader.init(ImageLoaderConfiguration.createDefault(this));
		left_img = (ImageView) this.findViewById(R.id.left_img);
		left_img.setVisibility(View.VISIBLE);
		title_txt = (TextView) this.findViewById(R.id.title_txt);
		right_txt = (TextView) this.findViewById(R.id.right_txt);
		tv_faselect = (TextView) this.findViewById(R.id.tv_faselect);
		tv_faname = (TextView) this.findViewById(R.id.tv_faname);
		et_plannercode = (EditText) this.findViewById(R.id.et_plannercode);
		right_txt.setVisibility(View.INVISIBLE);
		listview = (LoadListView) this.findViewById(R.id.lv_planner);
		listview.setVisibility(View.GONE);
		lists = new ArrayList<NPlannerBean>();
		templists = new ArrayList<NPlannerBean>();
		map = new HashMap<String, String>();
		rl_layout = (RelativeLayout) this.findViewById(R.id.rl_layout);
		iv_arrow = (ImageView) this.findViewById(R.id.iv_arrow);
		title_txt.setText("选择理财顾问");
		planner_map = new HashMap<String, String>();

	}

	/**
	 * 加载更多
	 */
	private void loadMore() {
		if (et_plannercode.getText().toString().length() >= 5) {

			return;
		}
		pageNum++;
		initData();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_img:
			finish();
			break;
		case R.id.tv_faselect:
			if (!isTurnFlag) {// 登录页面进来的
				if (nSinglePlannerResult != null) {
					Intent intent = new Intent(ReplaceFinancailPlannerActivity.this,
							ConfirmReplaceFinancailPlannerActivity.class);
					MyApplication.new_planner = new NPlannerTempBean(nSinglePlannerResult.getData().getRealName(),
							nSinglePlannerResult.getData().getFaCode(), nSinglePlannerResult.getData().getContact(),nSinglePlannerResult.getData().getLevelId());
					ReplaceFinancailPlannerActivity.this.startActivity(intent);
				}
			} else {
				commitFA();
			}

			break;
		case R.id.rl_layout:
			isopen = !isopen;
			if (isopen) {
				listview.setVisibility(View.VISIBLE);
			} else {
				listview.setVisibility(View.GONE);
			}
			iv_arrow.setRotation(isopen ? 180 : 0);
			break;

		default:
			break;
		}
	}

	/**
	 * 提交FA
	 */
	private void commitFA() {
		planner_map.clear();
		planner_map.put("facode", FACode);
		ProgressDialogUtil.showProgress(ReplaceFinancailPlannerActivity.this);
		new Thread(new Runnable() {
			public void run() {
				try {
					bindResult = WebServiceClient.getBindPlannerResult(planner_map);
					handler.sendEmptyMessage(0x05);
				} catch (AppException e) {
					handler.sendEmptyMessage(0x02);
					e.printStackTrace();
				}
			}
		}).start();
	}

}
