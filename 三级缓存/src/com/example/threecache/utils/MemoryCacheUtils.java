package com.example.threecache.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;


public class MemoryCacheUtils {
	
	private LruCache<String, Bitmap> lruCache;

	public MemoryCacheUtils(){
		int maxSize = (int) (Runtime.getRuntime().maxMemory()/8);
		lruCache = new LruCache<String, Bitmap>(maxSize){
			// 
			@Override
			protected int sizeOf(String key, Bitmap value) {
				
				return value.getRowBytes()*value.getHeight();
			}
		};
	}
	
	public Bitmap getBitmap(String url){
		return lruCache.get(url);
	}
	public void putBitmap(String url,Bitmap bitmap){
		lruCache.put(url, bitmap);
	}
}
