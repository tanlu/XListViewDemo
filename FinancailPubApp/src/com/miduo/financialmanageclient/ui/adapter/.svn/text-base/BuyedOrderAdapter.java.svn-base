package com.miduo.financialmanageclient.ui.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.RepertoryItem;
import com.miduo.financialmanageclient.bean.SubRepertoryItem;
import com.miduo.financialmanageclient.bean.TypeOrderInfo;
import com.miduo.financialmanageclient.bean.UserAssetInfoForAppVo;
import com.miduo.financialmanageclient.util.FloatUtil;
import com.miduo.financialmanageclient.util.StringUtil;

public class BuyedOrderAdapter extends BaseAdapter {

	private Context context;
	private List<RepertoryItem> lists;
	private ResetListView reset;
	private String blueColor = "#2ea9df";
	private String greyColor = "#666666";

	public BuyedOrderAdapter(Context context, List<RepertoryItem> lists,
			ResetListView resetListView) {
		this.context = context;
		this.lists = lists;
		this.reset = resetListView;
	}

	@Override
	public int getCount() {
		return lists.size();
	}

	@Override
	public RepertoryItem getItem(int arg0) {
		return lists.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(final int arg0, View arg1, ViewGroup arg2) {
		RepertoryItem repertoryItem = lists.get(arg0);
		ViewHolder holder = null;
		if (arg1 == null) {
			arg1 = LayoutInflater.from(context).inflate(
					R.layout.asset_type_info_item, null);
			holder = new ViewHolder();
			holder.type_name_txt = (TextView) arg1
					.findViewById(R.id.type_name_txt);
			holder.order_count_txt = (TextView) arg1
					.findViewById(R.id.order_count_txt);
			holder.type_sum_txt = (TextView) arg1
					.findViewById(R.id.type_sum_txt);
			holder.layout0 = (RelativeLayout) arg1.findViewById(R.id.layout0);
			holder.item_layout = (LinearLayout) arg1
					.findViewById(R.id.item_layout);
			holder.order_count_unit_txt = (TextView) arg1
					.findViewById(R.id.order_count_unit_txt);
			holder.type_sum_unit_txt = (TextView) arg1
					.findViewById(R.id.type_sum_unit_txt);
			arg1.setTag(holder);
		} else {
			holder = (ViewHolder) arg1.getTag();
		}
		holder.type_name_txt.setText(StringUtil.showStringContent(repertoryItem
				.getPcategoryName()));
		if (repertoryItem.getAssetCount() != null) {
			holder.order_count_txt.setText(String.valueOf(repertoryItem
					.getAssetCount()));
		} else {
			holder.order_count_txt.setText(MyApplication.instance
					.getResources().getString(R.string.default_numerical));
		}
		holder.type_sum_txt.setText(FloatUtil
				.toTwoDianStringSeparator(repertoryItem.getAssetAmount()));

		holder.item_layout.removeViews(1,
				holder.item_layout.getChildCount() - 1);
		if (repertoryItem.isExtend()) {
			holder.item_layout.setBackgroundColor(Color.parseColor("#f1f9fe"));
			holder.type_name_txt.setTextColor(Color.parseColor(blueColor));
			holder.order_count_txt.setTextColor(Color.parseColor(blueColor));
			holder.type_sum_txt.setTextColor(Color.parseColor(blueColor));
			holder.order_count_unit_txt.setTextColor(Color
					.parseColor(blueColor));
			holder.type_sum_unit_txt.setTextColor(Color.parseColor(blueColor));
			List<UserAssetInfoForAppVo> lst = repertoryItem.getUserAssetList();
			if (lst != null && lst.size() > 0) {
				for (int i = 0; i < lst.size(); i++) {
					final int subIndex = i;
					UserAssetInfoForAppVo item = lst.get(i);
					View view = LayoutInflater.from(context).inflate(
							R.layout.asset_type_sub_item, null);
					View line = view.findViewById(R.id.top_line);
					TextView product_name_txt = (TextView) view
							.findViewById(R.id.product_name_txt);
					TextView single_amount_txt = (TextView) view
							.findViewById(R.id.single_amount_txt);
					product_name_txt.setText(StringUtil.showStringContent(item
							.getProductName()));					
					single_amount_txt.setText(FloatUtil
							.toTwoDianStringSeparator(item.getTotalIncome()));
					RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) line
							.getLayoutParams();
					if (i > 0) {
						layoutParams.setMargins((int) context.getResources()
								.getDimension(R.dimen.px2dp_60), 0, 0, 0);// 4个参数按顺序分别是左上右下
					} else {
						layoutParams.setMargins(0, 0, 0, 0);// 4个参数按顺序分别是左上右下
					}
					line.setLayoutParams(layoutParams); // mView是控件
					holder.item_layout.addView(view);
					view.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							reset.detail(arg0, subIndex);
						}
					});
				}
			}

		} else {
			holder.item_layout.setBackgroundColor(Color.parseColor("#ffffff"));
			holder.type_name_txt.setTextColor(Color.parseColor(greyColor));
			holder.order_count_txt.setTextColor(Color.parseColor(greyColor));
			holder.type_sum_txt.setTextColor(Color.parseColor(greyColor));
			holder.order_count_unit_txt.setTextColor(Color
					.parseColor(greyColor));
			holder.type_sum_unit_txt.setTextColor(Color.parseColor(greyColor));
		}
		holder.layout0.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				reset.refresh(arg0);
			}
		});
		return arg1;
	}

	private static class ViewHolder {
		private TextView type_name_txt, order_count_txt, type_sum_txt,
				order_count_unit_txt, type_sum_unit_txt;
		private RelativeLayout layout0;
		private LinearLayout item_layout;
	}

	public interface ResetListView {
		public void refresh(int index);

		public void detail(int index, int subIndex);
	}
}
