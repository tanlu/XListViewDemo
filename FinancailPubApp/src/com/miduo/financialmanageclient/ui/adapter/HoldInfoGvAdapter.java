package com.miduo.financialmanageclient.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.AssetDetailInfoVo;
import com.miduo.financialmanageclient.util.StringUtil;

public class HoldInfoGvAdapter extends BaseAdapter {
	private List<AssetDetailInfoVo> _lst;
	Context mContext;

	public HoldInfoGvAdapter(Context mContext, List<AssetDetailInfoVo> lst) {
		super();
		this.mContext = mContext;
		this._lst = lst;
	}

	@Override
	public int getCount() {
		return _lst.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder;
		AssetDetailInfoVo item = _lst.get(position);
		if (convertView == null) {
			convertView = View.inflate(mContext, R.layout.hold_fixed_gv, null);
			viewHolder = new ViewHolder();
			viewHolder.name = (TextView) convertView.findViewById(R.id.name);
			viewHolder.jiage = (TextView) convertView.findViewById(R.id.jiage);
			viewHolder.unit = (TextView) convertView.findViewById(R.id.unit);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.name.setText(StringUtil.showStringContent(item.getParamName()));
		viewHolder.jiage.setText(StringUtil.showStringContent(item.getParamValue()));
		if (StringUtil.isEmpty(item.getProductUnit())) {
			viewHolder.unit.setVisibility(View.GONE);
		} else {
			viewHolder.unit.setVisibility(View.VISIBLE);
			viewHolder.unit.setText(StringUtil.showStringContent(item.getProductUnit()));
		}

		return convertView;
	}

	class ViewHolder {
		TextView name, jiage, unit;

	}

}
