package com.miduo.financialmanageclient.ui.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.UserTradeRecordsVo;
import com.miduo.financialmanageclient.util.FloatUtil;
import com.miduo.financialmanageclient.util.StringUtil;

public class AssetDetailRecordAdapter extends BaseAdapter {

	private Context context;
	private List<UserTradeRecordsVo> lists;

	public AssetDetailRecordAdapter(Context context, List<UserTradeRecordsVo> lists) {
		this.context = context;
		this.lists = lists;
	}

	@Override
	public int getCount() {
		return lists.size();
	}

	@Override
	public Object getItem(int position) {
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		UserTradeRecordsVo item = lists.get(position);
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.activity_alltake_list, null);
			viewHolder = new ViewHolder();
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.name = (TextView) convertView.findViewById(R.id.name);
		viewHolder.date = (TextView) convertView.findViewById(R.id.date);
		viewHolder.money = (TextView) convertView.findViewById(R.id.money);

		StringBuffer str = new StringBuffer();
		if(!StringUtil.isEmpty(item.getTradeTypeName())){
			str.append(item.getTradeTypeName());
		}
		if(!StringUtil.isEmpty(item.getTradeStateName())){
			str.append(item.getTradeStateName());
		}
		viewHolder.name.setText(StringUtil.showStringContent(str.toString()));
		viewHolder.date.setText(StringUtil.showStringContent(item.getTradeDate()));
		viewHolder.money.setText(FloatUtil.toTwoDianStringSeparator(item.getTradeAmount()));
		if (item.getTradeState()!=null && item.getTradeState().intValue()==3) {
			viewHolder.name.setTextColor(Color.parseColor("#f34d4d"));
			viewHolder.date.setTextColor(Color.parseColor("#f34d4d"));
			viewHolder.money.setTextColor(Color.parseColor("#cfcfcf"));
		}else{
			viewHolder.name.setTextColor(Color.parseColor("#333333"));
			viewHolder.date.setTextColor(Color.parseColor("#666666"));
			viewHolder.money.setTextColor(Color.parseColor("#333333"));
		}

		return convertView;
	}

	class ViewHolder {
		TextView name, date, money;
	}

}
