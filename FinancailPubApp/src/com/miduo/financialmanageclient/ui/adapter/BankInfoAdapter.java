package com.miduo.financialmanageclient.ui.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.BankCardInfo;
import com.miduo.financialmanageclient.util.ImageDownLoadUtil;
import com.miduo.financialmanageclient.util.StringUtil;

public class BankInfoAdapter extends BaseAdapter {

	private Context context;
	private List<BankCardInfo> lists;
	private PayClickListener listener;
	private int useType = 0;

	public BankInfoAdapter(Context context, List<BankCardInfo> lists, int useType) {
		this.context = context;
		this.lists = lists;
		this.useType = useType;
	}

	@Override
	public int getCount() {
		return lists.size() + 1;
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
		if (lists != null && position < lists.size()) {
			return 0;
		} else {
			return 1;
		}
	}

	@SuppressLint("NewApi")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		int type = getItemViewType(position);
		BankCardInfo item = null;
		if (position < lists.size()) {
			item = lists.get(position);
		}
		switch (type) {
		case 0:
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(R.layout.item_bankinfo, null);
				holder = new ViewHolder();
				holder.org_name_txt = (TextView) convertView.findViewById(R.id.org_name_txt);
				holder.card_code_txt = (TextView) convertView.findViewById(R.id.card_code_txt);
				holder.address_txt = (TextView) convertView.findViewById(R.id.address_txt);
				holder.tv_cercard = (TextView) convertView.findViewById(R.id.tv_cercard);
				holder.tv_bankcard = (TextView) convertView.findViewById(R.id.tv_bankcard);
				holder.tv_bankname = (TextView) convertView.findViewById(R.id.tv_bankname);
				holder.tv_location = (TextView) convertView.findViewById(R.id.tv_location);
				holder.subbank = (TextView) convertView.findViewById(R.id.subbank);
				holder.tv_pay = (TextView) convertView.findViewById(R.id.tv_pay);
				holder.org_img = (ImageView) convertView.findViewById(R.id.org_img);
				holder.iv_tip = (ImageView) convertView.findViewById(R.id.iv_tip);
				holder.ll_notsatisfy = (LinearLayout) convertView.findViewById(R.id.ll_notsatisfy);
				holder.ll_satisfy = (LinearLayout) convertView.findViewById(R.id.ll_satisfy);
				holder.card_layout = (LinearLayout) convertView.findViewById(R.id.card_layout);
				holder.name_txt = (TextView) convertView.findViewById(R.id.name_txt);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			if (useType == 0) {
				holder.tv_pay.setText("选择此卡支付");
			} else {
				holder.tv_pay.setText("确定");
			}
			if (item.getChannelType() != null && item.getChannelType().intValue() == 2) {// 满足
				holder.iv_tip.setVisibility(View.VISIBLE);

				holder.org_name_txt.setTextColor(Color.parseColor("#333333"));
				holder.address_txt.setTextColor(Color.parseColor("#333333"));

				holder.card_code_txt.setTextColor(Color.parseColor("#333333"));

				holder.name_txt.setTextColor(Color.parseColor("#333333"));
				
				holder.tv_cercard.setText(StringUtil.showStringContent(item.getIdcard()));
				holder.tv_bankcard.setText(StringUtil.showStringContent(item.getCardNo()));
				holder.tv_bankname.setText(StringUtil.showStringContent(item.getBankName()));
				holder.tv_location.setText(StringUtil.showStringContent(item.getBankAddress()));
				holder.subbank.setText(StringUtil.showStringContent(item.getBranchBank()));
				holder.tv_pay.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (listener != null) {
							listener.onClick(position);
						}
					}
				});
			} else {// 不满足
				holder.card_layout.setBackgroundResource(R.drawable.card_item_gray_bg);
				holder.ll_satisfy.setVisibility(View.GONE);
				holder.ll_notsatisfy.setVisibility(View.VISIBLE);
				holder.address_txt.setTextColor(Color.parseColor("#666666"));
				holder.org_name_txt.setTextColor(Color.parseColor("#666666"));
				holder.card_code_txt.setTextColor(Color.parseColor("#666666"));
				holder.name_txt.setTextColor(Color.parseColor("#666666"));
				holder.iv_tip.setVisibility(View.INVISIBLE);
				holder.tv_pay.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (listener != null) {
						}
					}
				});
			}

			holder.org_img.setImageResource(R.drawable.grey_point);
			if (!StringUtil.isEmpty(item.getSmallIco())) {
				holder.org_img.setTag(item.getSmallIco());
				ImageDownLoadUtil.setImageBitmap(holder.org_img, item.getSmallIco());
			}
			holder.org_name_txt.setText(StringUtil.showStringContent(item.getBankName()));
			StringBuffer str = new StringBuffer();
			if (!StringUtil.isEmpty(item.getBankAddress())) {
				str.append(item.getBankAddress());
			}
			if (!StringUtil.isEmpty(item.getBranchBank())) {
				str.append(item.getBranchBank());
			}
			
			String address = str.toString();
			holder.address_txt.setText(StringUtil.showStringContent(address));
			holder.card_code_txt.setText(StringUtil.showStringContent(item.getCardShortNo()));
			holder.name_txt.setText(StringUtil.showStringContent(item.getRealName()));
			
			
			if (!lists.get(position).isOpen() && item.getChannelType() != null && item.getChannelType().intValue() == 2) {
				holder.ll_satisfy.setVisibility(View.GONE);
				holder.ll_notsatisfy.setVisibility(View.GONE);
				holder.card_layout.setBackgroundResource(R.drawable.button_bg_white);
				holder.iv_tip.setRotation(0);
			}

			break;
		case 1:
			convertView = LayoutInflater.from(context).inflate(R.layout.addbankcard, null);
			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View view) {
					if (listener != null) {
						listener.onClick(position);
					}
				}
			});
			break;

		default:
			break;
		}

		return convertView;
	}

	public class ViewHolder {
		public TextView org_name_txt, card_code_txt, address_txt, name_txt, tv_cercard, tv_bankcard, tv_bankname,
				tv_location, subbank, tv_pay;
		public ImageView org_img, iv_tip;
		public LinearLayout ll_satisfy, ll_notsatisfy, card_layout;
	}

	public interface PayClickListener {
		void onClick(int position);
	}

	public void setPayClickListener(PayClickListener listener) {
		this.listener = listener;
	}

}
