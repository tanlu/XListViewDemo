package com.miduo.financialmanageclient.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.miduo.financialmanageclient.application.ConstantValues;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class ImageDownLoadUtil {

	/**
	 * 异步加载图片
	 * 
	 * @param imageView
	 * @param imgUrl
	 */
	public static void setImageBitmap(ImageView imageView, String imgUrl) {
		new ImageDownLoadTask(imageView).execute(imgUrl);
	}

	public static Bitmap getBitmap(String path) throws IOException {
		Bitmap bitmap = null;
		if (ImageCache.getInstance().getCache(path) == null) {
			String fileName = urlToStr(path);
			File file = new File(FileUtil.getPath(fileName,
					ConstantValues.CACHE_TYPE));
			if (file.exists()) {
				return getBitmapFromFile(FileUtil.getPath(fileName,
						ConstantValues.CACHE_TYPE));
			} else {
				bitmap = downImg(path);
			}

		} else {
//			DebugUtil.LogV("ImageCache:", "从内存中取出图片");
			bitmap = ImageCache.getInstance().getCache(path);
		}
		return bitmap;
	}

	private static Bitmap downImg(final String filePath) {
		byte[] data;
		try {
			data = getImage(filePath);
			if (data != null) {
				String fileName = urlToStr(filePath);
				File file = new File(FileUtil.getPath(fileName,
						ConstantValues.CACHE_TYPE));
				FileOutputStream in = new FileOutputStream(file);
				in.write(data, 0, data.length);
				in.close();
				Bitmap mBitmap = BitmapFactory.decodeByteArray(data, 0,
						data.length);
				ImageCache.getInstance().putCache(filePath, mBitmap);
				return mBitmap;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Bitmap getBitmapFromFile(String path) throws IOException {
		try {
			File file = new File(path);
			FileInputStream fin = new FileInputStream(file);
			InputStream in = new BufferedInputStream(fin);
			byte[] data = readStream(in);
			Bitmap mBitmap = BitmapFactory.decodeByteArray(data, 0,
					data.length);
			ImageCache.getInstance().putCache(path, mBitmap);
			return mBitmap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static byte[] getImage(String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5 * 1000);
		conn.setRequestMethod("GET");
		InputStream inStream = conn.getInputStream();
//		DebugUtil.LogV("getResponseCode:", conn.getResponseCode() + "");
		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			return readStream(inStream);
		}
		return null;
	}

	private static byte[] readStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		outStream.close();
		inStream.close();
		return outStream.toByteArray();
	}

	static class ImageDownLoadTask extends AsyncTask<String, Integer, Bitmap> {

		private ImageView imageView;
		private String imgUrl;

		public ImageDownLoadTask(ImageView imageView) {
			this.imageView = imageView;
		}

		@Override
		protected Bitmap doInBackground(String... arg0) {
			imgUrl = arg0[0];
//			DebugUtil.LogV("imgUrl:", imgUrl);
			Bitmap bitmap = null;
			try {
				bitmap = getBitmap(imgUrl);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			if (result != null) {
				if (imageView.getTag() != null
						&& imageView.getTag().equals(imgUrl)) {
					imageView.setImageBitmap(result);
				}
			}
		}
	}

	private static String urlToStr(String url) {
		String result = "";
		for (int i = 0; i < url.length(); i++) {
			char c = url.charAt(i);
			if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')
					|| (c >= '0' && c <= '9')) {
				result += c;
			}
		}
		return result;
	}

}
