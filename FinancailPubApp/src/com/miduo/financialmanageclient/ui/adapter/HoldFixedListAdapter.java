package com.miduo.financialmanageclient.ui.adapter;

import com.miduo.financialmanageclient.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HoldFixedListAdapter extends BaseAdapter {
	private String[] list_name, list_date, list_money;
	private Context mContext;

	public HoldFixedListAdapter(Context mContext) {
		super();
		this.mContext = mContext;
		list_name = new String[] { "赎回失败", "产品购买日", "到期自动赎回","赎回失败", "产品购买日", "到期自动赎回" };
		list_date = new String[] { "2015-9-27", "2015-9-27", "2015-9-27","2015-9-27", "2015-9-27", "2015-9-27" };
		list_money = new String[] { "23.32", "23.32", "23.32","23.32", "23.32", "23.32" };
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list_date.length;
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
		// TODO Auto-generated method stub
		ListHolder listholder;
		if (convertView == null) {
			convertView = View.inflate(mContext,
					R.layout.activity_holdfixed_list, null);
			listholder = new ListHolder();
			convertView.setTag(listholder);
		} else {
			listholder = (ListHolder) convertView.getTag();
		}

		listholder.name = (TextView) convertView.findViewById(R.id.name);
		listholder.date = (TextView) convertView.findViewById(R.id.date);
		listholder.money = (TextView) convertView.findViewById(R.id.money);

		listholder.name.setText(list_name[position]);
		listholder.date.setText(list_date[position]);
		listholder.money.setText(list_money[position]);
		if (list_name[position].equals("赎回失败")) {
			listholder.name.setTextColor(android.graphics.Color.RED);
			listholder.date.setTextColor(android.graphics.Color.RED);
			listholder.money.setTextColor(android.graphics.Color.GRAY);
		}

		return convertView;
	}

	class ListHolder {
		TextView name, date, money;

	}

}
