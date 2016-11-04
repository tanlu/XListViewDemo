package com.miduo.financialmanageclient.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.TestRecord;
import com.miduo.financialmanageclient.util.StringUtil;

public class RecordAdapter extends BaseAdapter {

	private Context context;
	private List<TestRecord> list;
	private AppointClickListener listener;
	private boolean isVisible = false;

	public RecordAdapter(Context context, List<TestRecord> list) {
		this.context = context;
		this.list = list;
	}

	public void setAppointClickListener(AppointClickListener listener) {
		this.listener = listener;
	}

	@Override
	public int getItemViewType(int position) {
		return super.getItemViewType(position);
	}

	@Override
	public int getViewTypeCount() {
		return super.getViewTypeCount();
	}

	@Override
	public int getCount() {
		return list.size();
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
		notifyDataSetChanged();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		TestRecord order = list.get(position);
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.record_item, null);
			viewHolder.name_txt = (TextView) convertView.findViewById(R.id.name_txt);
			viewHolder.time_txt = (TextView) convertView.findViewById(R.id.time_txt);
			viewHolder.sub_sel_img = (ImageView) convertView.findViewById(R.id.sub_sel_img);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		if (order != null) {
			if (isVisible) {
				viewHolder.sub_sel_img.setVisibility(View.VISIBLE);
				if (order.isSel()) {
					viewHolder.sub_sel_img.setImageResource(R.drawable.select);
				} else {
					viewHolder.sub_sel_img.setImageResource(R.drawable.un_select);
				}
			} else {
				viewHolder.sub_sel_img.setVisibility(View.GONE);
			}

			viewHolder.name_txt.setText(StringUtil.showStringContent(order.getName()));
			viewHolder.time_txt.setText(StringUtil.showStringContent(order.getDateTime()));

		}
		return convertView;
	}

	final static class ViewHolder {
		TextView name_txt, time_txt;
		ImageView sub_sel_img;
	}

	public interface AppointClickListener {
		void onClick(int position);
	}

}
