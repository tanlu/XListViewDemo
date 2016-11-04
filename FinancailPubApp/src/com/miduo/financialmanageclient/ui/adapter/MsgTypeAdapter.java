package com.miduo.financialmanageclient.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.MsgBean;
import com.miduo.financialmanageclient.util.StringUtil;

public class MsgTypeAdapter extends BaseAdapter {

	private List<MsgBean> list = null;
	private Context mContext;

	public MsgTypeAdapter(Context mContext, List<MsgBean> msgLst) {
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
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		MsgBean msg = list.get(position);
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.myinfomation_list, null);
			viewHolder.toptype_txt = (TextView) convertView.findViewById(R.id.toptype_txt);
			viewHolder.name = (TextView) convertView.findViewById(R.id.name);
			viewHolder.xiangqing = (TextView) convertView.findViewById(R.id.xiangqing);
			viewHolder.shijian = (TextView) convertView.findViewById(R.id.shijian);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		if (msg != null) {
			viewHolder.toptype_txt.setText(StringUtil.showStringContent(msg.getTopType()));
			if(StringUtil.isEmpty(msg.getIsRead()) || msg.getIsRead().equals("1")){
				viewHolder.toptype_txt.setBackgroundResource(R.drawable.msg_bg_gray);
			}else{
				viewHolder.toptype_txt.setBackgroundResource(R.drawable.blue_round_bg);
			}			
			viewHolder.name.setText(StringUtil.showStringContent(msg.getTitle()));
			if (StringUtil.isEmpty(msg.getDateTime())) {
				viewHolder.shijian.setText(R.string.default_value);
			} else {
				viewHolder.shijian.setText(msg.getDateTime().split(" ")[0]);
			}
			viewHolder.xiangqing.setText(StringUtil.showStringContent(msg.getContent()));
		}
		return convertView;
	}

	final static class ViewHolder {
		TextView toptype_txt, name, xiangqing, shijian;
	}
}
