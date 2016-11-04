package com.miduo.financialmanageclient.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.TextView;

import com.miduo.financialmanageclient.R;

/**
 * 滚动加载更多listview
 * 
 * @author huozhenpeng
 * 
 */
public class LoadListView extends ListView implements OnScrollListener {
	View footer;// 底部布局；
	int totalItemCount;// 总数量；
	int lastVisibleItem;// 最后一个可见的item；
	boolean isLoading;// 正在加载；
	ILoadListener iLoadListener;

	public LoadListView(Context context) {
		super(context);
		initView(context);
	}

	public LoadListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public LoadListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}

	/**
	 * 添加底部加载提示布局到listview
	 * 
	 * @param context
	 */
	private void initView(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		footer = inflater.inflate(R.layout.footer_layout, null);
		footer.findViewById(R.id.tv).setVisibility(View.GONE);
		footer.setOnClickListener(null);
		this.setOnScrollListener(this);
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		this.lastVisibleItem = firstVisibleItem + visibleItemCount;
		this.totalItemCount = totalItemCount;
		footer.findViewById(R.id.tv).setVisibility(View.VISIBLE);
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (totalItemCount == lastVisibleItem
				&& scrollState == SCROLL_STATE_IDLE) {
			if (!isLoading) {
				isLoading = true;
				// 加载更多
				iLoadListener.onLoad();
			}
		}
	}

	/**
	 * 加载完毕
	 */
	public void loadComplete() {
		isLoading = false;
		footer.findViewById(R.id.tv).setVisibility(View.GONE);
	}

	public void setInterface(ILoadListener iLoadListener) {
		this.iLoadListener = iLoadListener;
	}

	// 加载更多数据的回调接口
	public interface ILoadListener {
		public void onLoad();
	}

	public TextView gettips() {
		return (TextView) footer.findViewById(R.id.tv).findViewById(R.id.tv);
	}

	public void addfootView() {
		this.addFooterView(footer);
	}

	public void removeFootView() {
		this.removeFooterView(footer);
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		try {
			super.dispatchDraw(canvas);
		} catch (IndexOutOfBoundsException e) {
		}
	}

}
