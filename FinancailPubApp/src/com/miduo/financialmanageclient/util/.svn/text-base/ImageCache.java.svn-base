package com.miduo.financialmanageclient.util;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

@SuppressLint("NewApi")
public class ImageCache {

	private LruCache<String, Bitmap> lru;
	private static ImageCache instance;

	private ImageCache() {
		int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 16);
		lru = new LruCache<String, Bitmap>(maxMemory) {

			@Override
			protected void entryRemoved(boolean evicted, String key,
					Bitmap oldValue, Bitmap newValue) {
				super.entryRemoved(evicted, key, oldValue, newValue);
			}

			@Override
			protected int sizeOf(String key, Bitmap value) {
				return value.getByteCount();
			}

		};
	}

	public static ImageCache getInstance() {
		synchronized (ImageCache.class) {
			if (instance == null) {
				instance = new ImageCache();
			}
		}
		return instance;
	}

	public void putCache(String key, Bitmap value) {
		lru.put(key, value);
	}

	public Bitmap getCache(String key) {
		return lru.get(key);
	}

	public void cleanCache() {
		lru.evictAll();
	}

}
