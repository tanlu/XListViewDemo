package com.miduo.financialmanageclient.ui.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.TransferDataEntity;
import com.miduo.financialmanageclient.util.FloatUtil;

/**
 * 产品中心，转让专区使用
 * 
 * @author huozhenpeng
 * 
 */
public class TransforProductAdapter extends BaseAdapter {

	private Context context;
	private List<TransferDataEntity> lists;
	private int type;
	private TransferDataEntity bean;

	public TransforProductAdapter(Context context,
			List<TransferDataEntity> lists,int type) {
		this.context = context;
		this.lists = lists;
		this.type=type;
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
		bean=lists.get(position);
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_transfer, null);
			holder.tv_discount = (TextView) convertView
					.findViewById(R.id.tv_discount);
			holder.tv_money = (TextView) convertView
					.findViewById(R.id.tv_money);
			holder.tv_percent = (TextView) convertView
					.findViewById(R.id.tv_percent);
			holder.tv_symbol1 = (TextView) convertView
					.findViewById(R.id.tv_symbol1);
			holder.tv_symbol2 = (TextView) convertView
					.findViewById(R.id.tv_symbol2);
			holder.tv_day = (TextView) convertView.findViewById(R.id.tv_day);
			holder.tv_hour = (TextView) convertView.findViewById(R.id.tv_hour);
			holder.tv_proname = (TextView) convertView
					.findViewById(R.id.tv_proname);
			holder.tv_daysymbol = (TextView) convertView
					.findViewById(R.id.tv_daysymbol);
			holder.tv_discounttip=(TextView) convertView.findViewById(R.id.tv_discounttip);
			holder.tv_status=(TextView) convertView.findViewById(R.id.tv_status);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_percent.setTextSize(TypedValue.COMPLEX_UNIT_PX,context.getResources().getDimension(R.dimen.px2dp_38));
		holder.tv_percent.setTextColor(Color.parseColor("#2da8df"));
		holder.tv_symbol1.setTextColor(Color.parseColor("#2da8df"));
		holder.tv_day.setTextSize(TypedValue.COMPLEX_UNIT_PX,context.getResources().getDimension(
				R.dimen.px2sp_38));
		holder.tv_day.setTextColor(Color.parseColor("#2da8df"));
		holder.tv_daysymbol.setTextColor(Color.parseColor("#2da8df"));
		holder.tv_symbol2.setTextColor(Color.parseColor("#2da8df"));
		holder.tv_money.setTextSize(TypedValue.COMPLEX_UNIT_PX,context.getResources().getDimension(
				R.dimen.px2sp_38));
		holder.tv_money.setTextColor(Color.parseColor("#2da8df"));
		switch (type) {
		case 1:// 持有期收益率
			holder.tv_percent.setTextSize(TypedValue.COMPLEX_UNIT_PX,context.getResources().getDimension(
					R.dimen.px2sp_66));
			holder.tv_percent.setTextColor(Color.parseColor("#2da8df"));
			holder.tv_symbol1.setTextColor(Color.parseColor("#2da8df"));
			holder.tv_day.setTextSize(TypedValue.COMPLEX_UNIT_PX,context.getResources().getDimension(
					R.dimen.px2sp_38));
			holder.tv_day.setTextColor(Color.parseColor("#999999"));
			holder.tv_daysymbol.setTextColor(Color.parseColor("#999999"));
			holder.tv_symbol2.setTextColor(Color.parseColor("#999999"));
			holder.tv_money.setTextSize(TypedValue.COMPLEX_UNIT_PX,context.getResources().getDimension(
					R.dimen.px2sp_38));
			holder.tv_money.setTextColor(Color.parseColor("#999999"));
			break;
		case 2:// 剩余期限
			holder.tv_percent.setTextSize(TypedValue.COMPLEX_UNIT_PX,context.getResources().getDimension(
					R.dimen.px2sp_38));
			holder.tv_percent.setTextColor(Color.parseColor("#999999"));
			holder.tv_symbol1.setTextColor(Color.parseColor("#999999"));
			holder.tv_day.setTextSize(TypedValue.COMPLEX_UNIT_PX,context.getResources().getDimension(
					R.dimen.px2sp_66));
			holder.tv_day.setTextColor(Color.parseColor("#2da8df"));
			holder.tv_daysymbol.setTextColor(Color.parseColor("#2da8df"));
			holder.tv_symbol2.setTextColor(Color.parseColor("#999999"));
			holder.tv_money.setTextSize(TypedValue.COMPLEX_UNIT_PX,context.getResources().getDimension(
					R.dimen.px2sp_38));
			holder.tv_money.setTextColor(Color.parseColor("#999999"));
			break;
		case 3:// 转让价格
			holder.tv_percent.setTextSize(TypedValue.COMPLEX_UNIT_PX,context.getResources().getDimension(
					R.dimen.px2sp_38));
			holder.tv_percent.setTextColor(Color.parseColor("#999999"));
			holder.tv_symbol1.setTextColor(Color.parseColor("#999999"));
			holder.tv_day.setTextSize(TypedValue.COMPLEX_UNIT_PX,context.getResources().getDimension(
					R.dimen.px2sp_38));
			holder.tv_day.setTextColor(Color.parseColor("#999999"));
			holder.tv_daysymbol.setTextColor(Color.parseColor("#999999"));
			holder.tv_symbol2.setTextColor(Color.parseColor("#2da8df"));
			holder.tv_money.setTextSize(TypedValue.COMPLEX_UNIT_PX,context.getResources().getDimension(
					R.dimen.px2sp_50));
			holder.tv_money.setTextColor(Color.parseColor("#2da8df"));
			break;
			

		default:
			break;
		}
		holder.tv_discounttip.setText(bean.getDiscountType());
		holder.tv_discount.setText(FloatUtil.toTwoDianStringSeparator(bean.getDiscountPrice()));
//		holder.tv_percent.setText(FloatUtil.toTwoDianString(bean.getExpertRate()/1000));
		holder.tv_percent.setText(FloatUtil.toTwoDianString1(Double.valueOf(bean.getExpertRate().split("%")[0])));
		holder.tv_day.setText(bean.getRemainTime());
		holder.tv_money.setText(FloatUtil.toTwoDianStringSeparator(bean.getTransferPrice()));
		holder.tv_proname.setText(bean.getProductName());
		holder.tv_hour.setText(getCountDown(bean.getGrabRemainTime()));
		switch (bean.getState()) {
//		(0，未转让，1、转让中 2、转让成功 3、转让取消
		case 0:
			holder.tv_status.setBackgroundResource(R.drawable.frame_bg_blue);
			holder.tv_status.setTextColor(Color.parseColor("#2da8df"));
			holder.tv_status.setText("未转让");
			break;
		case 1:
			holder.tv_status.setBackgroundResource(R.drawable.frame_bg_green);
			holder.tv_status.setTextColor(Color.parseColor("#8aba62"));
			holder.tv_status.setText("转让中");
			break;
		default:
			holder.tv_status.setBackgroundResource(R.drawable.frame_bg_gray);
			holder.tv_status.setTextColor(Color.parseColor("#cccccc"));
			holder.tv_status.setText("已转让");
			break;
		}
		return convertView;
	}
	public void setType(int type)
	{
		this.type=type;
	}

	class ViewHolder {
		private TextView tv_discount, tv_money, tv_percent, tv_symbol1,
				tv_symbol2, tv_day, iv_timer, tv_hour, tv_proname,
				tv_daysymbol,tv_discounttip,tv_status;
	}
	public String getCountDown(String time1) {
		long time=0;
		try {
			time=Long.parseLong(time1);
		} catch (Exception e) {
			time=0;
		}
		if(time<=0)
			time=0;
		return time/(60*60*60)+"时"+(time % (1000 * 60 * 60) / (1000 * 60) )+ "分";
//				+ (time % (1000 * 60)) / 1000 + "秒";
	}

}
