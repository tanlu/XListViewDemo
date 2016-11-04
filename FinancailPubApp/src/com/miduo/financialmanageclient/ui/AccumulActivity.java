package com.miduo.financialmanageclient.ui;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.bean.CumulativeCommissionBean;
import com.miduo.financialmanageclient.bean.SingleCommission;
import com.miduo.financialmanageclient.util.FloatUtil;
import com.miduo.financialmanageclient.util.JsonUtils;
import com.miduo.financialmanageclient.util.MDialog;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.ProgressDialogUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.ws.AppException;
import com.miduo.financialmanageclient.ws.WebServiceClient;
import com.umeng.analytics.MobclickAgent;

public class AccumulActivity extends GesterSetBaseActivity implements OnClickListener {

	private TextView right_txt;
	private ImageView left_img;
	private TextView leiji;
	private TextView yijieqing;
	private ListView lv;
	private List<SingleCommission> incomeDayVoList = new ArrayList<SingleCommission>();
	private MyAdapter myAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accumulative);
		init();
		initEvent();
		initDate();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onPageStart("累计收益"); // 统计页面
		MobclickAgent.onResume(this); // 统计时长
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPageEnd("累计收益"); //
		MobclickAgent.onPause(this);
	}

	private void init() {
		TextView title_txt = (TextView) findViewById(R.id.title_txt);
		title_txt.setText("累计收益");
		left_img = (ImageView) findViewById(R.id.left_img);
		right_txt = (TextView) findViewById(R.id.right_txt);
		right_txt.setVisibility(View.GONE);

		leiji = (TextView) findViewById(R.id.leiji);
		yijieqing = (TextView) findViewById(R.id.yijieqing);

		lv = (ListView) findViewById(R.id.lv);
		myAdapter = new MyAdapter();
		lv.setAdapter(myAdapter);

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

				Intent intent = new Intent(AccumulActivity.this, SingleDayGainActivity.class);
				startActivity(intent);
			}
		});
	}

	private void showData(CumulativeCommissionBean item) {
		// TODO Auto-generated method stub
		if (item == null) {
			return;
		}
		leiji.setText(FloatUtil.toTwoDianStringSeparator(item.getTotalRevenue()));
		yijieqing.setText(FloatUtil.toTwoDianStringSeparator(item.getUsedRevenue()));
		if (item.getIncomeDayVoList() != null && item.getIncomeDayVoList().size() > 0) {
			incomeDayVoList.addAll(item.getIncomeDayVoList());
			myAdapter.notifyDataSetChanged();
		}
	}

	private void initEvent() {
		left_img.setOnClickListener(this);
		right_txt.setOnClickListener(this);

	}

	private void initDate() {
		ProgressDialogUtil.showProgress(AccumulActivity.this);
		new AsyncTask<String, Integer, String>() {
			private AppException ex;

			@Override
			protected String doInBackground(String... params) {
				try {
					ex = null;
					return WebServiceClient.getCumulativeCommission();
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
						ex.makeToast(AccumulActivity.this);
					} else {
						MToast.showToast(AccumulActivity.this, "获取信息失败！");
					}
					return;
				} else {
					try {
						JSONObject jo = new JSONObject(result);
						int state = jo.getInt("state");
						String msg = jo.getString("msg");
						if (state == ConstantValues.LOGIN_ERROR) {
							MDialog.showPsdErrorDialog(AccumulActivity.this, msg);
							return;
						} else if (state == 1) {
							String data = jo.getString("data");
							if (!StringUtil.isEmpty(data)) {
								CumulativeCommissionBean item = JsonUtils.toBean(data,
										new TypeToken<CumulativeCommissionBean>() {
										}.getType());
								showData(item);
							}
						} else {
							MToast.showToast(AccumulActivity.this, StringUtil.isEmpty(msg) ? "获取信息" : msg);
						}
					} catch (Exception e) {
						e.printStackTrace();
						MToast.showToast(AccumulActivity.this, "数据异常");
					}
				}
				super.onPostExecute(result);
			}

		}.execute();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.left_img:
			finish();
			break;

		case R.id.right_txt:
			/*
			 * // 临时跳转 Intent intent = new Intent(this, ChangeOutActivit.class);
			 * startActivity(intent);
			 */
			break;

		default:
			break;
		}
	}

	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return incomeDayVoList.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;
			SingleCommission item = incomeDayVoList.get(position);
			if (convertView == null) {
				convertView = View.inflate(getApplicationContext(), R.layout.accumulative_list, null);
				viewHolder = new ViewHolder();
				viewHolder.date = (TextView) convertView.findViewById(R.id.date);
				viewHolder.money = (TextView) convertView.findViewById(R.id.money);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			viewHolder.date.setText(StringUtil.showStringContent(item.getIcDate()));
			viewHolder.money.setText(FloatUtil.toTwoDianStringSeparator(item.getAmount()));

			return convertView;
		}

	}

	class ViewHolder {
		TextView date, money;

	}
}
