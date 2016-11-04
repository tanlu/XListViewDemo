package com.miduo.financialmanageclient.ui.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.AssetForCalendarListEntity;
import com.miduo.financialmanageclient.bean.CalendarResultBean;
import com.miduo.financialmanageclient.util.FloatUtil;
import com.miduo.financialmanageclient.util.StringUtil;
/**
 * 日历adapter
 * @author huozhenpeng
 *
 */
public class CalendarAdapter extends BaseAdapter {

	private Context context;
	private List<CalendarResultBean> lists;
	private AssetForCalendarListEntity calendarBean;
	private OnCalendarClickListener listener;
	private String tips;//日历提示消息内容
	public CalendarAdapter(Context context,List<CalendarResultBean> lists)
	{
		this.context=context;
		this.lists=lists;
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
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public int getItemViewType(int position) {
		if (lists != null) {
			if(TextUtils.isEmpty(lists.get(position).getYear()))
			{
				return 0;//显示具体数据
			}
			else
			{
				return 1;//显示年份
			}
		}
		else
		{
			return 1;
		}
			
	}


	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		int type = getItemViewType(position);
		switch (type) {
		case 0:
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(
						R.layout.item_calendar, null);
				holder = new ViewHolder();
				holder.tv_date=(TextView) convertView.findViewById(R.id.tv_date);
				holder.tv_title=(TextView) convertView.findViewById(R.id.tv_title);
				holder.tv_surplus=(TextView) convertView.findViewById(R.id.tv_surplus);
				holder.tv_proname=(TextView) convertView.findViewById(R.id.tv_proname);
				holder.tv_money=(TextView) convertView.findViewById(R.id.tv_money);
				holder.tv_tips=(TextView) convertView.findViewById(R.id.tv_tips);
				holder.tv_remind=(TextView) convertView.findViewById(R.id.tv_remind);
				convertView.setTag(holder);
			}
			else
			{
				holder=(ViewHolder) convertView.getTag();
			}
			calendarBean=lists.get(position).getData();
			holder.tv_date.setText(calendarBean.getRedeemTimeCon().split("-")[1]+"."+calendarBean.getRedeemTimeCon().split("-")[2]);
//			可赎回：0 到期自动赎回：1 付息：2 开放转让：3
			
			switch (calendarBean.getCalendarAssetState()) {
			case 0:
				tips="可赎回";
				holder.tv_title.setText("可赎回");
				break;
			case 1:
				tips="到期自动赎回";
				holder.tv_title.setText("到期自动赎回");
				break;
			case 2:
				tips="付息";
				holder.tv_title.setText("付息");
				break;
			case 3:
				tips="开放转让";
				holder.tv_title.setText("开放转让");
				break;

			default:
				break;
			}
			holder.tv_remind.setVisibility(View.VISIBLE);
			holder.tv_surplus.setText("还有"+calendarBean.getRedeemRemainderTime()+"天");
			holder.tv_proname.setText(calendarBean.getProductName());
			holder.tv_money.setText(FloatUtil.toTwoDianStringSeparator(calendarBean.getAssetAmount()));
			holder.tv_tips.setVisibility(View.VISIBLE);
			if(calendarBean.getBindBankcard())//已绑卡
			{
				if(TextUtils.isEmpty(calendarBean.getRedeemTimeSlot()))
				{
					holder.tv_tips.setVisibility(View.GONE);
				}
				else
				{
					holder.tv_tips.setTextColor(Color.parseColor("#2da8df"));
					holder.tv_tips.setText(StringUtil.showStringContent(calendarBean.getRedeemTimeSlot()));
				}
				
			}
			else
			{
				holder.tv_tips.setTextColor(Color.parseColor("#f34d4d"));
				holder.tv_tips.setText("请立即为此订单指定收款账号!");
			}
			holder.tv_remind.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(listener!=null)
						listener.onRemindeClick(position,tips,calendarBean);
				}
			});
			if(calendarBean.getRedeemRemainderTime()==0)
			{
				holder.tv_remind.setVisibility(View.GONE);
			}
			break;
		case 1:
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_calendar2, null);
			TextView textview=(TextView) convertView.findViewById(R.id.tv_year);
			textview.setText(lists.get(position).getYear());
			break;

		default:
			break;
		}
		return convertView;
	}
	
	public class ViewHolder
	{
		private TextView tv_date,tv_title,tv_surplus,tv_proname,tv_money,tv_tips,tv_remind;
	}
	
	public interface OnCalendarClickListener
	{
		void onRemindeClick(int position,String tips,AssetForCalendarListEntity calendarBean);
	}
	
	public void setOnCalendarClickListener(OnCalendarClickListener listener)
	{
		this.listener=listener;
	}

}
