package com.miduo.financialmanageclient.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.NProductCenterInsureResult.NProductCenterInsureBean;

/**
 * 产品中心，保险产品使用
 * 
 * @author huozhenpeng
 * 
 */
public class InsuranceProductAdapter extends BaseAdapter {

	private Context context;
	private List<NProductCenterInsureBean> lists;
	private NProductCenterInsureBean bean;

	public InsuranceProductAdapter(Context context,
			List<NProductCenterInsureBean> lists) {
		this.context = context;
		this.lists = lists;
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
		bean = lists.get(position);
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_insurance, null);
			holder.tv_insurancename = (TextView) convertView
					.findViewById(R.id.tv_insurancename);
			holder.tv_day = (TextView) convertView.findViewById(R.id.tv_day);
			holder.tv_old = (TextView) convertView.findViewById(R.id.tv_center);
			holder.tv_money = (TextView) convertView
					.findViewById(R.id.tv_money);
			holder.tv_tips = (TextView) convertView.findViewById(R.id.tv_tips);
			holder.tv_insuranceflag = (TextView) convertView
					.findViewById(R.id.tv_insuranceflag);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_insurancename.setText(bean.getProductName());
		holder.tv_day.setText(bean.getCrowd());
		holder.tv_old.setText(bean.getInsuracePeriod());
		holder.tv_money.setText(bean.getPremium());
		holder.tv_tips.setText(bean.getHighlight());
		holder.tv_insuranceflag.setText(bean.getCategoryName());
		if ("寿险".equals(bean.getCategoryName())) {
			holder.tv_insuranceflag.setBackgroundResource(R.drawable.insure_bg_pink);
		} else if ("意外险".equals(bean.getCategoryName())) {
			holder.tv_insuranceflag.setBackgroundResource(R.drawable.insure_bg_green);
		} else if ("医疗险".equals(bean.getCategoryName())) {
			holder.tv_insuranceflag.setBackgroundResource(R.drawable.insure_bg_blue);
		} else if ("重疾险".equals(bean.getCategoryName())) {
			holder.tv_insuranceflag.setBackgroundResource(R.drawable.insure_bg_yellow);
		} else {
			holder.tv_insuranceflag.setBackgroundResource(R.drawable.insure_bg_gray);
		}
		return convertView;
	}

	public class ViewHolder {
		public TextView tv_insurancename, tv_day, tv_old, tv_money, tv_tips,
				tv_insuranceflag;

	}

}
