package com.example.threecache.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.ListView;

public class ImageCacheUtils {

	private MemoryCacheUtils memoryCacheUtils;
	private LocalCacheUtils localCacheUtils;
	private NetCacheUtils netCacheUtils;

	public ImageCacheUtils() {
		memoryCacheUtils = new MemoryCacheUtils();
		localCacheUtils = new LocalCacheUtils(memoryCacheUtils);
		netCacheUtils = new NetCacheUtils(memoryCacheUtils,localCacheUtils);
	}

	public void display(ImageView imageView, String url, ListView lv_photo_list) {
		
		Bitmap bitmap = null;
		bitmap = memoryCacheUtils.getBitmap(url);
		if (bitmap != null) {
			imageView.setImageBitmap(bitmap);
			System.out.println("从内存中取数据");
			return;
		}
		bitmap = localCacheUtils.getBitmap(url);
		if (bitmap != null) {
			imageView.setImageBitmap(bitmap);
			System.out.println("从本地取数据");
			return;
		}
		netCacheUtils.display(url, imageView,lv_photo_list);
		System.out.println("从网络取数据");
	}
}
