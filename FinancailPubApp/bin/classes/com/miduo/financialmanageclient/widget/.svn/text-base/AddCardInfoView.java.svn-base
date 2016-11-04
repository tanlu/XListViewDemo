package com.miduo.financialmanageclient.widget;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;
import com.miduo.financialmanageclient.bean.BankCardInfo;
import com.miduo.financialmanageclient.bean.BankInfo;
import com.miduo.financialmanageclient.bean.UserInfo;
import com.miduo.financialmanageclient.ui.adapter.BankListSelectAdapter;
import com.miduo.financialmanageclient.util.ImageDownLoadUtil;
import com.miduo.financialmanageclient.util.MToast;
import com.miduo.financialmanageclient.util.SharePrefUtil;
import com.miduo.financialmanageclient.util.StringUtil;

public class AddCardInfoView extends LinearLayout {
	private TextView name_txt, id_txt;
	private EditText et_banknumber, address_txt, branch_name_txt;
	private View spinner;
	private BankListSelectAdapter adapter;
	private List<BankInfo> lists = new ArrayList<BankInfo>();
	private TextView spinnerTxt;
	private ImageView spinnerImage;
	private ListView listView;
	private PopupWindow popup;
	private ImageView bank_img;
	private Context mContext;
	private Integer bankId;
	private ImageView bank_info_img;
	private boolean hasOrgImg = false;

	public AddCardInfoView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public AddCardInfoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public AddCardInfoView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	private void init(Context context) {
		// TODO Auto-generated method stub
		mContext = context;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.content_addbankcard, this);
		name_txt = (TextView) view.findViewById(R.id.name_txt);
		id_txt = (TextView) view.findViewById(R.id.id_txt);
		spinner = (View) view.findViewById(R.id.bank_spinner);
		spinnerTxt = (TextView) spinner.findViewById(R.id.et_option);
		spinnerImage = (ImageView) spinner.findViewById(R.id.ib_down_arrow);
		bank_img = (ImageView) spinner.findViewById(R.id.bank_img);
		bank_info_img = (ImageView) findViewById(R.id.bank_info_img);
		bank_info_img.setVisibility(View.GONE);
		et_banknumber = (EditText) view.findViewById(R.id.et_banknumber);
		address_txt = (EditText) view.findViewById(R.id.address_txt);
		branch_name_txt = (EditText) view.findViewById(R.id.branch_name_txt);

		listView = new ListView(context);

		lists = new ArrayList<BankInfo>();
		adapter = new BankListSelectAdapter(context, lists);
		listView.setBackgroundColor(Color.parseColor("#FFFFFF"));
		listView.setVerticalScrollBarEnabled(false);
		listView.setDivider(null);
		listView.setDividerHeight(0);
		listView.setAdapter(adapter);
		adapter.notifyDataSetChanged();

		initEvent();
		initData();
	}

	private void initData() {
		// TODO Auto-generated method stub
		UserInfo userInfo = (UserInfo) SharePrefUtil.getObj(mContext, SharePrefUtil.USER_INFO);
		if (userInfo != null) {
			name_txt.setText(StringUtil.showStringContent(userInfo.getUserName()));
			id_txt.setText(StringUtil.showStringContent(userInfo.getIdCard()));
		}
	}

	public void initBankInfo(List<BankInfo> bankLst) {
		// TODO Auto-generated method stub
		lists.clear();
		lists.addAll(bankLst);
		adapter.notifyDataSetChanged();
	}

	private void initEvent() {
		spinnerTxt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popup = new PopupWindow(listView, getResources().getDimensionPixelOffset(R.dimen.px2dp_515),
						getResources().getDimensionPixelOffset(R.dimen.px2dp_400));
				popup.setFocusable(true);
				popup.setBackgroundDrawable(getResources().getDrawable(R.drawable.bank_lst_bg));
				popup.setOutsideTouchable(true);
				popup.showAsDropDown(spinnerTxt, getResources().getDimensionPixelOffset(R.dimen.px2dp_f10),
						getResources().getDimensionPixelOffset(R.dimen.px2dp_10));
			}
		});
		spinnerImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popup = new PopupWindow(listView, getResources().getDimensionPixelOffset(R.dimen.px2dp_515),
						getResources().getDimensionPixelOffset(R.dimen.px2dp_400));
				popup.setFocusable(true);
				popup.setBackgroundDrawable(getResources().getDrawable(R.drawable.bank_lst_bg));
				popup.setOutsideTouchable(true);
				popup.showAsDropDown(spinnerTxt, getResources().getDimensionPixelOffset(R.dimen.px2dp_f10),
						getResources().getDimensionPixelOffset(R.dimen.px2dp_10));
			}
		});
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				BankInfo option = lists.get(arg2);
				spinnerTxt.setText(option.getBankName());
				spinnerTxt.setTextColor(Color.parseColor("#333333"));
				bank_img.setVisibility(View.VISIBLE);
				bank_img.setImageResource(R.drawable.grey_point);
				hasOrgImg = false;
				if (!StringUtil.isEmpty(option.getSmallIco())) {
					bank_img.setTag(option.getSmallIco());
					ImageDownLoadUtil.setImageBitmap(bank_img, option.getSmallIco());
					hasOrgImg = true;
				}
				if (!StringUtil.isEmpty(option.getDescription())) {
					bank_info_img.setTag(option.getDescription());
					ImageDownLoadUtil.setImageBitmap(bank_info_img, option.getDescription());
					bank_info_img.setVisibility(View.VISIBLE);
				} else {
					bank_info_img.setVisibility(View.GONE);
				}
				// bank_info_img
				bankId = option.getBankId();
				adapter.setSetSelectIndex(arg2);
				adapter.notifyDataSetChanged();
				popup.dismiss();
			}
		});
		et_banknumber.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (s.length() > 25) {
					et_banknumber.setText(s.subSequence(0, s.length() - 1));
					et_banknumber.setSelection(25);
					return;
				}
				if (count == 1) {
					if (s.length() == 4) {
						et_banknumber.setText(s + " ");
						et_banknumber.setSelection(5);
					}
					if (s.length() == 9) {
						et_banknumber.setText(s + " ");
						et_banknumber.setSelection(10);
					}
					if (s.length() == 14) {
						et_banknumber.setText(s + " ");
						et_banknumber.setSelection(15);
					}
					if (s.length() == 19) {
						et_banknumber.setText(s + " ");
						et_banknumber.setSelection(20);
					}
					if (s.length() == 24) {
						et_banknumber.setText(s + " ");
						et_banknumber.setSelection(25);
					}
				} else if (count == 0) {
					if (s.length() == 4) {
						et_banknumber.setText(s.subSequence(0, s.length() - 1));
						et_banknumber.setSelection(3);
					}
					if (s.length() == 9) {
						et_banknumber.setText(s.subSequence(0, s.length() - 1));
						et_banknumber.setSelection(8);
					}
					if (s.length() == 14) {
						et_banknumber.setText(s.subSequence(0, s.length() - 1));
						et_banknumber.setSelection(13);
					}
					if (s.length() == 19) {
						et_banknumber.setText(s.subSequence(0, s.length() - 1));
						et_banknumber.setSelection(18);
					}
					if (s.length() == 24) {
						et_banknumber.setText(s.subSequence(0, s.length() - 1));
						et_banknumber.setSelection(23);
					}

				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
	}

	private void addContentTips() {
		// LinearLayout.LayoutParams params=new
		// LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
		// LayoutParams.WRAP_CONTENT);
		// TextView textview1=new TextView(this);
		// textview1.setText("实际支付时，需满足以下条件：");
		// ll_tips.addView(textview1);
		//
		// TextView textview2=new TextView(this);
		// textview1.setText("需开通银联无卡业务");
		// textview1.setCompoundDrawables(left, top, right, bottom);
		// textview1.setc
		// ll_tips.addView(textview1);
	}

	public boolean checkValue() {
		String name = name_txt.getText().toString();
		String id = id_txt.getText().toString();
		String banknumber = et_banknumber.getText().toString();
		String address = address_txt.getText().toString();
		String branch_name = branch_name_txt.getText().toString();
		if (StringUtil.isEmpty(name) || StringUtil.isEmpty(id) || StringUtil.isEmpty(banknumber)
				|| bank_img.VISIBLE == View.INVISIBLE) {
			MToast.showToast(mContext, "请输入必填信息");
			return false;
		}
		if(checkoutCertification(id))
		{
			return true;
		}
		else
		{
			MToast.showToast(mContext, "身份证号有误！");
			return false;
		}
		
	}
	/**
	 * 校验身份证号
	 * 
	 * @return
	 */
	public boolean checkoutCertification(String idNum) {
		// 定义判别用户身份证号的正则表达式（要么是15位，要么是18位，最后一位可以为字母）
		Pattern idNumPattern = Pattern
				.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
		// 通过Pattern获得Matcher
		Matcher idNumMatcher = idNumPattern.matcher(idNum);
		// 判断用户输入是否为身份证号
		if (idNumMatcher.matches()) {
			return true;
		}
		return false;
//		return true;

	}

	public BankCardInfo getValue() {
		String name = name_txt.getText().toString();
		String id = id_txt.getText().toString();
		String banknumber = et_banknumber.getText().toString();
		String address = address_txt.getText().toString();
		String branch_name = branch_name_txt.getText().toString();
		BankCardInfo item = new BankCardInfo();
		// 银行ID
		item.setBankListId(bankId);
		// 姓名
		item.setRealName(name);
		item.setBankName(spinnerTxt.getText().toString());
		// 身份证
		item.setIdcard(id);
		// 银行卡
		String cardNum = banknumber.replaceAll(" ", "");
		item.setCardNo(cardNum);
		item.setCardShortNo(cardNum.substring(cardNum.length()-4));
		// 所在地
		if(!StringUtil.isEmpty(address)){
			item.setBankAddress(address);
		}		
		// 支行名字
		if(!StringUtil.isEmpty(branch_name)){
			item.setBranchBank(branch_name);
		}		
		if (hasOrgImg) {
			item.setSmallIco(bank_img.getTag().toString());
		}
		return item;
	}
}
