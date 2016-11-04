package com.miduo.financialmanageclient.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
/**
 * listview外层有scrollview嵌套时使用
 * @author huozhenpeng
 *
 */
public class CustomerListView extends ListView{

	public CustomerListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomerListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CustomerListView(Context context) {
		super(context);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int specheight=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, specheight);
	}

}
