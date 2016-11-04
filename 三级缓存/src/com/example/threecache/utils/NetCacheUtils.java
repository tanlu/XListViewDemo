package com.example.threecache.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.ListView;

public class NetCacheUtils {
	private ExecutorService pool;
	private MyHandler handler;
	
	private final int RESCODE_SUCCESS = 1;
	private MemoryCacheUtils memoryCacheUtils;
	private LocalCacheUtils localCacheUtils;
	private ListView lv_photo_list;
	public NetCacheUtils(MemoryCacheUtils memoryCacheUtils, LocalCacheUtils localCacheUtils){
		pool = Executors.newFixedThreadPool(5);
		this.memoryCacheUtils = memoryCacheUtils;
		this.localCacheUtils = localCacheUtils;
	}
	public void display(String url,ImageView imageView, ListView lv_photo_list){
		pool.execute(new ImageRunnable(url,imageView));
		handler = new MyHandler();
		this.lv_photo_list = lv_photo_list;
	}
	
	class ImageRunnable implements Runnable{

		private String mUrl;
		private ImageView mImageView;
		private int position;
		public ImageRunnable(String url, ImageView imageView) {
			this.mUrl = url;
			this.mImageView = imageView;
			this.position = (Integer) imageView.getTag();
		}

		@Override
		public void run() {
			//
			try {
				Thread.sleep(3000);
				HttpURLConnection conn = (HttpURLConnection) new URL(mUrl).openConnection();
				conn.connect();//
				int resCode = conn.getResponseCode();
				if(resCode == 200){
					InputStream is = conn.getInputStream();
					Bitmap bitmap = BitmapFactory.decodeStream(is);
					// 
					Message.obtain(handler, RESCODE_SUCCESS, new Result(position, bitmap)).sendToTarget();
					// 
					memoryCacheUtils.putBitmap(mUrl, bitmap);
					localCacheUtils.putBitmap(mUrl, bitmap);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	class Result {
		public ImageView imageView;
		public Bitmap bitmap;
		private int position;
		public Result(ImageView imageView, Bitmap bitmap) {
			this.imageView = imageView;
			this.bitmap = bitmap;
		}
		public Result(int position, Bitmap bitmap) {
			this.position = position;
			this.bitmap = bitmap;
		}
	}
	
	class MyHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			
			if(msg.what == RESCODE_SUCCESS){
				Result res = (Result) msg.obj;
				// 
				ImageView imageView = (ImageView) lv_photo_list.findViewWithTag(res.position);
				if(imageView!=null){
					imageView.setImageBitmap(res.bitmap);
				}
			}
		}
	}
}
