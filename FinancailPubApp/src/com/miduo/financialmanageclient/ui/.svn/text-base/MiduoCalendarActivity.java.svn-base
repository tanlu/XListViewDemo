package com.miduo.financialmanageclient.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.bean.AssetForCalendarListEntity;
import com.miduo.financialmanageclient.bean.CalendarResultBean;
import com.miduo.financialmanageclient.bean.DialogBean;
import com.miduo.financialmanageclient.bean.NCalendarBean;
import com.miduo.financialmanageclient.bean.NCalendarResult;
import com.miduo.financialmanageclient.bean.UserAssetInfoForAppVo;
import com.miduo.financialmanageclient.common.SkipUtils;
import com.miduo.financialmanageclient.listener.DialogEventListener;
import com.miduo.financialmanageclient.ui.adapter.CalendarAdapter;
import com.miduo.financialmanageclient.ui.adapter.CalendarAdapter.OnCalendarClickListener;
import com.miduo.financialmanageclient.util.AddCalenderUtil;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

/**
 * 我的米多日历页面
 * 
 * @author huozhenpeng
 * 
 */
public class MiduoCalendarActivity extends GesterSetBaseActivity implements
		OnClickListener {

	private ListView listview;
	private List<CalendarResultBean> lists;
	private CalendarAdapter adapter;
	private ImageView left_img;
	private TextView title_txt;
	private TextView right_txt;
	private Intent intent;
	private NCalendarResult nCalendarResult;
	private List<NCalendarBean> nLists;
	private CalendarResultBean calendarResultBean;
	private String tips1;
	private AssetForCalendarListEntity calendarBean1;
	private SkipUtils sKipUtils;
	private UserAssetInfoForAppVo item;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_miduocaculator);
		initView();
		initEvent();
		initData();
	}

	/**
	 * 包括已购买（固守823、对冲824） 转出中（827）赎回中 三个情况
	 */
	private void initData() {
		// 根据后台返回的数据，重新构造数据（在每年数据的开头添加一条有year但是没有data的数据）

		ProgressDialogUtil.showProgress(this);
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					nCalendarResult = WebServiceClient.getCalendarResult();
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
				if (nCalendarResult != null) {
					if (nCalendarResult.getState() == 1) {
						nLists = nCalendarResult.getData();
						for (int i = 0; i < nLists.size(); i++) {
							calendarResultBean = new CalendarResultBean();
							calendarResultBean.setYear(nLists.get(i)
									.getYearNum());
							calendarResultBean.setData(null);
							lists.add(calendarResultBean);
							for (int j = 0; j < nLists.get(i)
									.getAssetForCalendarList().size(); j++) {
								calendarResultBean = new CalendarResultBean();
								calendarResultBean.setYear(null);
								calendarResultBean.setData(nLists.get(i)
										.getAssetForCalendarList().get(j));
								lists.add(calendarResultBean);
							}
						}
						if (adapter == null) {
							adapter = new CalendarAdapter(
									MiduoCalendarActivity.this, lists);
							listview.setAdapter(adapter);
						} else {
							adapter.notifyDataSetChanged();
						}
					} else {
						if (nCalendarResult.getState() == ConstantValues.LOGIN_ERROR) {
							MDialog.showPsdErrorDialog(
									MiduoCalendarActivity.this,
									nCalendarResult.getMsg());
						}

					}
				}

				break;
			case 0x02:
				break;

			default:
				break;
			}
		};
	};

	private void initView() {
		left_img = (ImageView) this.findViewById(R.id.left_img);
		title_txt = (TextView) this.findViewById(R.id.title_txt);
		title_txt.setText("我的米多日历");
		right_txt = (TextView) this.findViewById(R.id.right_txt);
		right_txt.setVisibility(View.INVISIBLE);
		listview = (ListView) this.findViewById(R.id.listview);
		lists = new ArrayList<CalendarResultBean>();
		intent = new Intent();
		adapter = new CalendarAdapter(MiduoCalendarActivity.this, lists);
		listview.setAdapter(adapter);
	}

	private void initEvent() {
		left_img.setOnClickListener(this);
		adapter.setOnCalendarClickListener(new OnCalendarClickListener() {

			@Override
			public void onRemindeClick(int position, String tips,
					AssetForCalendarListEntity calendarBean) {
				MiduoCalendarActivity.this.tips1 = tips;
				MiduoCalendarActivity.this.calendarBean1 = calendarBean;
				DialogBean data = new DialogBean();
				data.setContent("设置提醒");
				data.setCancel("下次再说");
				data.setSubmit("立即设置");
				DialogEventListener lister = new DialogEventListener() {

					@Override
					public void submit() {
						try {
							if(StringUtil.isEmpty(calendarBean1.getAlarmClockTime())){
								return;
							}
							Integer.valueOf(calendarBean1.getAlarmClockTime());
							AddCalenderUtil.add(MiduoCalendarActivity.this,
									"米多提醒", tips1, calendarBean1
											.getEventStartTime(), calendarBean1
											.getEventEndTime(), Integer
											.valueOf(calendarBean1
													.getAlarmClockTime()));
						} catch (Exception e) {

						}

					}

					@Override
					public void cancel() {

					}
				};
				data.setDialogEvent(lister);
				MDialog.showDialog2(MiduoCalendarActivity.this, data);
			}

		});
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				AssetForCalendarListEntity temp = lists.get(position).getData();
				int assetState =temp.getAssetState();
				int buyStyle = temp.getBuyStyle();
				String orderNo= temp.getAssetNo();
				if (view.getTag() != null) {
					// assetState 0，冻结，1、正常(已购买)、2、赎回中 3、已赎回 4、转让中 5、转入中 6、 转出中
					// 7、已转让 8.退款中 9.退款完成
					// buyStyle 0：固收 1：对冲
					if (sKipUtils == null) {
						sKipUtils = new SkipUtils();
					}
					if(item==null)
					{
						item=new UserAssetInfoForAppVo();
					}
					item.setState(assetState);
					item.setBuyStyle(buyStyle);
					item.setOrderNo(orderNo);
					sKipUtils.skipInstruction(MiduoCalendarActivity.this, item);
				}

			}
		});

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

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("我的米多日历"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("我的米多日历"); //
		MobclickAgent.onPause(this);
	}

}
