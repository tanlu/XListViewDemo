package com.miduo.financialmanageclient.ui.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.BankInfo;
import com.miduo.financialmanageclient.util.ImageDownLoadUtil;
import com.miduo.financialmanageclient.util.StringUtil;

public class BankListSelectAdapter extends BaseAdapter {

	private Context context;
	private List<BankInfo> lists;
	private Integer setSelectIndex;

	public BankListSelectAdapter(Context context, List<BankInfo> lists) {
		this.context = context;
		this.lists = lists;
	}

	public Integer getSetSelectIndex() {
		return setSelectIndex;
	}

	public void setSetSelectIndex(Integer setSelectIndex) {
		this.setSelectIndex = setSelectIndex;
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
		ViewHolder holder = null;
		BankInfo item = lists.get(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.item_selectbank, null);
			holder = new ViewHolder();
			holder.tv_bankname = (TextView) convertView.findViewById(R.id.tv_bankname);
			holder.iv_bankicon = (ImageView) convertView.findViewById(R.id.iv_bankicon);
			holder.iv_uparrow = (ImageView) convertView.findViewById(R.id.iv_uparrow);
			holder.current_layout = (LinearLayout) convertView.findViewById(R.id.current_layout);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (setSelectIndex != null && setSelectIndex.intValue() == position) {
			holder.current_layout.setBackgroundColor(Color.parseColor("#f6f6f6"));
		} else {
			holder.current_layout.setBackgroundColor(Color.parseColor("#ffffff"));
		}
		holder.tv_bankname.setText(item.getBankName());
		holder.iv_bankicon.setImageResource(R.drawable.grey_point);
		if (!StringUtil.isEmpty(item.getSmallIco())) {
			holder.iv_bankicon.setTag(item.getSmallIco());
			ImageDownLoadUtil.setImageBitmap(holder.iv_bankicon, item.getSmallIco());					
		}
		return convertView;
	}

	class ViewHolder {
		TextView tv_bankname;
		ImageView iv_bankicon;
		ImageView iv_uparrow;
		LinearLayout current_layout;
	}

}
