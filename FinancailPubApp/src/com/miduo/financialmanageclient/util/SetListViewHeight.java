package com.miduo.financialmanageclient.util;


import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * ListView嵌套ListView时获取高度
 * @author sunrui
 *
 */
public class SetListViewHeight {
	public static void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(  
	                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),  
	                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)); 
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

}
