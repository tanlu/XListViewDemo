package com.miduo.financialmanageclient.ui.adapter;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.DataEntity;
import com.miduo.financialmanageclient.util.FloatUtil;
import com.miduo.financialmanageclient.util.StringUtil;
import com.miduo.financialmanageclient.widget.WaveTextView;

public class ProductAdaper extends BaseAdapter {

	private Context context;
	private List<DataEntity> list;

	public ProductAdaper(Context context, List<DataEntity> list) {
		this.context = context;
		this.list = list;
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
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		DataEntity product = list.get(position);
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.product_item, null);
			viewHolder.surplus_unit = (TextView) convertView.findViewById(R.id.surplus_unit_txt);
			viewHolder.name_txt = (TextView) convertView.findViewById(R.id.name_txt);
			viewHolder.profit_value_txt = (TextView) convertView.findViewById(R.id.profit_value_txt);
			viewHolder.profit_unit_txt = (TextView) convertView.findViewById(R.id.profit_unit_txt);
			viewHolder.term_value_txt = (TextView) convertView.findViewById(R.id.term_value_txt);
			viewHolder.term_unit_txt = (TextView) convertView.findViewById(R.id.term_unit_txt);
			viewHolder.qishou_txt = (TextView) convertView.findViewById(R.id.qishou_txt);
			viewHolder.shuhui_txt = (TextView) convertView.findViewById(R.id.shuhui_txt);
			viewHolder.surplus_value_txt = (TextView) convertView.findViewById(R.id.surplus_value_txt);

			viewHolder.sale_already_txt = (WaveTextView) convertView.findViewById(R.id.sale_already_txt);
			viewHolder.term_title_txt = (TextView) convertView.findViewById(R.id.term_title_txt);
			viewHolder.profit_title_txt = (TextView) convertView.findViewById(R.id.profit_title_txt);
			new Thread(viewHolder.sale_already_txt).start();
			viewHolder.salestate = (ImageView) convertView.findViewById(R.id.salestate);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		if (product != null) {
			viewHolder.name_txt.setText(product.getProductName());
			viewHolder.qishou_txt.setText(product.getProductHighlight().split("@")[0]);
			if (product.getProductHighlight().split("@").length == 2) {

				viewHolder.shuhui_txt.setText(product.getProductHighlight().split("@")[1]);
			}
			if (!TextUtils.isEmpty(product.getRemainAmount())) {

				if (Double.parseDouble(product.getRemainAmount()) % 10000 == 0) {
					viewHolder.surplus_value_txt.setText(FloatUtil.toZeroDianStringSeparator(Double.parseDouble(product
							.getRemainAmount()) / 10000));
					viewHolder.surplus_unit.setVisibility(View.VISIBLE);
				} else {
					viewHolder.surplus_value_txt.setText(FloatUtil.toZeroDianStringSeparator(Double.parseDouble(product
							.getRemainAmount())));
					viewHolder.surplus_unit.setVisibility(View.GONE);
				}
			} else {
				viewHolder.surplus_value_txt.setText("0");
				viewHolder.surplus_unit.setVisibility(View.GONE);
			}
			if (!TextUtils.isEmpty(product.getSalePercent())) {
				viewHolder.sale_already_txt.setText((int) (Float.parseFloat(product.getSalePercent()) * 100));
			} else {
				viewHolder.sale_already_txt.setText(0);
			}
			if (product.getPageStyleType().equals("40")) {
				viewHolder.term_title_txt.setText("封闭期");
				viewHolder.term_value_txt.setText(product.getClosedPeriod());
				viewHolder.term_unit_txt.setText(product.getUnitClosedPeriod());
			} else {
				viewHolder.term_title_txt.setText("投资期限");
				viewHolder.term_value_txt.setText(product.getProductPeriodDesc());
				viewHolder.term_unit_txt.setText(product.getUnitProductPeriod());
			}
			if (product.getExpertRateDesc().contains("%")) {
				viewHolder.profit_unit_txt.setVisibility(View.VISIBLE);
				viewHolder.profit_value_txt.setText(product.getExpertRateDesc().split("%")[0]);
				viewHolder.profit_unit_txt.setText("%");
			} else {
				viewHolder.profit_value_txt.setText(product.getExpertRateDesc());
				viewHolder.profit_unit_txt.setVisibility(View.INVISIBLE);
			}
			viewHolder.profit_title_txt.setText(product.getExpertRateName());
			if (Integer.parseInt(product.getIshot()) == 1) {
				viewHolder.salestate.setImageResource(R.drawable.hot);
				switch (Integer.parseInt(product.getStatus())) {
				case 10:// 预售
					viewHolder.salestate.setImageResource(R.drawable.yu);
					break;
				case 30:// 售罄
					viewHolder.salestate.setImageResource(R.drawable.qing);
					break;

				default:
					break;
				}
			} else {
				switch (Integer.parseInt(product.getStatus())) {
				case 10:// 预售
					viewHolder.salestate.setImageResource(R.drawable.yu);
					break;
				case 20:// 在售
					viewHolder.salestate.setImageResource(R.drawable.zai);
					break;
				case 30:// 售罄
					viewHolder.salestate.setImageResource(R.drawable.qing);
					break;

				default:
					break;
				}
			}
		}
		return convertView;
	}

	final static class ViewHolder {
		TextView name_txt, profit_value_txt, profit_unit_txt, term_value_txt, term_unit_txt, qishou_txt, shuhui_txt,
				surplus_value_txt, profit_title_txt, term_title_txt, surplus_unit;
		WaveTextView sale_already_txt;
		ImageView salestate;
	}

}
