package com.miduo.financialmanageclient.ui.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.NPlannerResult.NPlannerBean;
import com.miduo.financialmanageclient.bean.NPlannerResult.NPlannerBean.IfaDiplomaListEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 选择理财顾问页面
 * 
 * @author huozhenpeng
 * 
 */
public class SelectPlannerAdapter extends BaseAdapter {

	private Context context;
	private List<NPlannerBean> lists;
	private List<IfaDiplomaListEntity> diplist;
	private String temp="";
	private ImageLoader imageloader;

	public SelectPlannerAdapter(Context context, List<NPlannerBean> lists ,ImageLoader imageloader) {
		this.context = context;
		this.lists = lists;
		this.imageloader=imageloader;
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
		diplist=lists.get(position).getIfaDiplomaList();
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_selectplanner, null);
			holder = new ViewHolder();
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_location = (TextView) convertView
					.findViewById(R.id.tv_location);
			holder.tv_certification = (TextView) convertView
					.findViewById(R.id.tv_qualifications);
			holder.tv_select = (TextView) convertView
					.findViewById(R.id.tv_select);
			holder.ll_bottom = (LinearLayout) convertView
					.findViewById(R.id.ll_bottom);
			holder.iv_head=(ImageView) convertView.findViewById(R.id.iv_head);
			holder.tv_tips=(TextView) convertView.findViewById(R.id.tv_tips);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if(lists.get(position).isIsopen())
		{
			holder.ll_bottom.setVisibility(View.VISIBLE);
		}
		else
		{
			holder.ll_bottom.setVisibility(View.GONE);
		}
		holder.tv_name.setText(lists.get(position).getRealName());
		for(int i=0;i<diplist.size();i++)
		{
			temp+=(diplist.get(i).getDiplomaName()+"\u3000");
		}
		holder.tv_certification.setText(temp);
		temp="";
		holder.tv_location.setText(lists.get(position).getProvince()+lists.get(position).getCity()+lists.get(position).getArea());
		try {
			imageloader.displayImage(lists.get(position).getAvatar(), holder.iv_head);
		} catch (Exception e) {
			holder.iv_head.setImageResource(R.drawable.ifa_defaulthead);
		}
		holder.tv_tips.setText(lists.get(position).getDescription());
		
		return convertView;
	}

	public class ViewHolder {
		public TextView tv_name, tv_location, tv_certification, tv_select,tv_tips;
		public LinearLayout ll_bottom;
		public ImageView iv_head;
	}


}
