package com.miduo.financialmanageclient.ui.adapter;

import java.util.List;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.AttrListEntity;
import com.miduo.financialmanageclient.ui.adapter.HoldFixedListAdapter.ListHolder;
import com.miduo.financialmanageclient.util.StringUtil;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ProdunctInfoAdapter extends BaseAdapter {
	private Context mContext;
	private  List<AttrListEntity> attrLis;

	public ProdunctInfoAdapter(Context mContext, List<AttrListEntity> attrLis) {
		this.mContext = mContext;
		this.attrLis = attrLis ;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return attrLis.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		AttrListEntity item = attrLis.get(position);
		ListHolder listholder;
		if (convertView == null) {
			convertView = View.inflate(mContext,
					R.layout.activity_produncinfo_list, null);
			listholder = new ListHolder();
			listholder.list_name = (TextView) convertView.findViewById(R.id.list_name);
			listholder.name_info = (TextView) convertView
					.findViewById(R.id.name_info);
			convertView.setTag(listholder);
		} else {
			listholder = (ListHolder) convertView.getTag();
		}
		listholder.list_name.setText(StringUtil.showStringContent(item.getAttrName()));
		listholder.name_info.setText(StringUtil.showStringContent(item.getAttrValue()));

		return convertView;
	}

	class ListHolder {
		TextView list_name, name_info;

	}

}
