package com.miduo.financialmanageclient.ui.adapter;

import java.util.List;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.NProductCenterInsureResult.NProductCenterInsureBean.InsureFileListEntity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
/**
 * 保险产品，产品详情底部
 * @author huozhenpeng
 *
 */
public class InsuranceInfoBottomAdapter extends BaseAdapter {

	private List<InsureFileListEntity> lists;
	private Context context;
	public InsuranceInfoBottomAdapter(List<InsureFileListEntity> lists,Context context)
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
		convertView=LayoutInflater.from(context).inflate(R.layout.item_insuranceinfobottom, null);
		TextView tv_name=(TextView) convertView.findViewById(R.id.tv_name);
		tv_name.setText(lists.get(position).getAttrName());
		return convertView;
	}


}
