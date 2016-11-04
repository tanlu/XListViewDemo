package com.example.day04_asynctask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * 请求小数据的时候 结构简单，速度快
 * 
 * Volley,频繁的网络请求，数据较小的网络请求 OKHttp
 * 
 * @author Administrator
 * 
 */
public class MainActivity extends Activity {
	private String imag_url = "http://img.tuku.cn/file_big/201504/83e0e5df87024855a2976690e7dd1188.jpg";
	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		imageView = (ImageView) findViewById(R.id.imageView);
		// 调用
		MyTask myTask = new MyTask(imageView);
		myTask.execute(imag_url);

	}

	// 自己的类继承AsyncTask
	class MyTask extends AsyncTask<String, Void, Bitmap> {

		private ImageView imageView1;

		public MyTask(ImageView iamImageView) {
			this.imageView1 = iamImageView;
		}

		private Bitmap bimap;

		// 必须重写，子线程doInBackground
		@Override
		protected Bitmap doInBackground(String... params) {
			String url = params[0];
			// 访问网络
			try {
				URL url1 = new URL(url);
				HttpURLConnection con = (HttpURLConnection) url1
						.openConnection();
				con.setConnectTimeout(3000);
				if (con.getResponseCode() == 200) {
					InputStream inputStream = con.getInputStream();
					bimap = BitmapFactory.decodeStream(inputStream);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bimap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			imageView1.setImageBitmap(result);
		}

	}
}
