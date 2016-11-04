package com.miduo.financialmanageclient.ui.adapter;

import java.math.BigDecimal;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.RedeemItemDetail;
import com.miduo.financialmanageclient.bean.TestRecord;
import com.miduo.financialmanageclient.util.FloatUtil;
import com.miduo.financialmanageclient.util.StringUtil;

public class RedeemOrderAdapter extends BaseAdapter {

	private Context context;
	private List<RedeemItemDetail> list;
	private boolean showBtn;
	private AppointClickListener listener;

	public RedeemOrderAdapter(Context context, List<RedeemItemDetail> list, boolean showBtn) {
		this.context = context;
		this.list = list;
		this.showBtn = showBtn;
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
		RedeemItemDetail order = list.get(position);
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.redeem_order_item, null);
			viewHolder.product_name_txt = (TextView) convertView.findViewById(R.id.product_name_txt);
			viewHolder.amount_txt = (TextView) convertView.findViewById(R.id.amount_txt);
			viewHolder.china_amount_txt = (TextView) convertView.findViewById(R.id.china_amount_txt);
			viewHolder.btn_txt = (TextView) convertView.findViewById(R.id.btn_txt);
			viewHolder.sel_img = (ImageView) convertView.findViewById(R.id.sel_img);
			viewHolder.appoint_layout = (LinearLayout) convertView.findViewById(R.id.appoint_layout);
			viewHolder.org_layout = (RelativeLayout) convertView.findViewById(R.id.org_layout);
			viewHolder.org_name_txt = (TextView) convertView.findViewById(R.id.org_name_txt);
			viewHolder.org_code_txt = (TextView) convertView.findViewById(R.id.org_code_txt);
			viewHolder.no_code_txt = (TextView) convertView.findViewById(R.id.no_code_txt);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		if (order != null) {
			if (order.isSel()) {
				viewHolder.sel_img.setImageResource(R.drawable.select);
			} else {
				viewHolder.sel_img.setImageResource(R.drawable.un_select);
			}
			if (showBtn) {
				viewHolder.org_layout.setVisibility(View.GONE);
				viewHolder.appoint_layout.setVisibility(View.VISIBLE);
				viewHolder.btn_txt.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (listener != null) {
							listener.onClick(position);
						}
					}
				});

			} else {
				viewHolder.org_layout.setVisibility(View.VISIBLE);
				if (order.isHasbank()) {
					viewHolder.org_name_txt.setVisibility(View.VISIBLE);
					viewHolder.org_code_txt.setVisibility(View.VISIBLE);
					viewHolder.no_code_txt.setVisibility(View.GONE);
					viewHolder.org_name_txt.setText(StringUtil.showStringContent(order.getBankName()));
					viewHolder.org_code_txt.setText(StringUtil.showStringContent(order.getShortBankNo()));
				} else {
					viewHolder.org_name_txt.setVisibility(View.GONE);
					viewHolder.org_code_txt.setVisibility(View.GONE);
					viewHolder.no_code_txt.setVisibility(View.VISIBLE);
				}
				viewHolder.appoint_layout.setVisibility(View.GONE);
			}
			viewHolder.product_name_txt.setText(StringUtil.showStringContent(order.getProdcutTitle()));
			viewHolder.amount_txt.setText(FloatUtil.toTwoDianStringSeparator(order.getAmount()));
			if (order.getAmount() != null) {
				viewHolder.china_amount_txt.setText(StringUtil.number2CNMontrayUnit(new BigDecimal(order.getAmount())));
			} else {
				viewHolder.china_amount_txt.setText(context.getResources().getString(R.string.default_value));
			}

		}
		return convertView;
	}

	final static class ViewHolder {
		TextView product_name_txt, amount_txt, china_amount_txt;
		ImageView sel_img;
		TextView btn_txt;
		LinearLayout appoint_layout;
		RelativeLayout org_layout;
		TextView org_name_txt, org_code_txt, no_code_txt;
	}

	public interface AppointClickListener {
		void onClick(int position);
	}

}
