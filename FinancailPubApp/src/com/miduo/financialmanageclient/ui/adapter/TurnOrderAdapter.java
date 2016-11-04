package com.miduo.financialmanageclient.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.MTransferOrderInfoVo;
import com.miduo.financialmanageclient.util.StringUtil;

public class TurnOrderAdapter extends BaseAdapter {
	private List<MTransferOrderInfoVo> list = null;
	private Context mContext;

	public TurnOrderAdapter(Context mContext, List<MTransferOrderInfoVo> msgLst) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext;
		this.list = msgLst;
	}

	public int getCount() {
		return this.list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder = null;
		MTransferOrderInfoVo item = list.get(position);
		if (convertView == null) {
			convertView = View.inflate(mContext, R.layout.turn_order_item, null);
			viewHolder = new ViewHolder();
			viewHolder.title_txt = (TextView) convertView.findViewById(R.id.title_txt);
			viewHolder.value_txt = (TextView) convertView.findViewById(R.id.value_txt);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.title_txt.setText(StringUtil.showStringContent(item.getParamName()));
		viewHolder.value_txt.setText(StringUtil.showStringContent(item.getParamValue()));
		return convertView;
	}

	final static class ViewHolder {
		TextView title_txt, value_txt;
	}
}
