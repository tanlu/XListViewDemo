package com.miduo.financialmanageclient.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.NProductCenterInsureResult.NProductCenterInsureBean.NInsureValue;


public class InsuranceProductInfoAdapter extends BaseAdapter{

	private List<NInsureValue> lists;
	private Context context;
	
	public InsuranceProductInfoAdapter(Context context,List<NInsureValue> lists)
	{
		this.lists=lists;
		this.context=context;
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
		convertView=LayoutInflater.from(context).inflate(R.layout.item_insuranceinfo, null);
		TextView textname=(TextView) convertView.findViewById(R.id.tv_name);
		TextView textvalue=(TextView) convertView.findViewById(R.id.tv_value);
		textname.setText(lists.get(position).getAttrName());
		textvalue.setText(lists.get(position).getAttrValue());
		return convertView;
	}


}
