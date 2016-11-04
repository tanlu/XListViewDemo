package com.miduo.financialmanageclient.ui.adapter;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.miduo.financialmanageclient.bean.NPlannerInfo.NPlannerBean.IfaDiplomaListEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MypagerAdapter extends PagerAdapter {

	Context mContext;
	private List<IfaDiplomaListEntity> list;
	private List<ImageView> imageLists;
	private ImageLoader imageloader = null;

	public MypagerAdapter(Context mContext, List<IfaDiplomaListEntity> list2,ImageLoader imageloader,List<ImageView> imageLists) {
		super();
		this.list = list2;
		this.mContext = mContext;
		this.imageloader=imageloader;
		this.imageLists=imageLists;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		imageloader.displayImage(list.get(position).getAttachment(), imageLists.get(position));
		container.addView(imageLists.get(position));
		return imageLists.get(position);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(imageLists.get(position));
	}

}
