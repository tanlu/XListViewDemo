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
 * ����С���ݵ�ʱ�� �ṹ�򵥣��ٶȿ�
 * 
 * Volley,Ƶ���������������ݽ�С���������� OKHttp
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
		// ����
		MyTask myTask = new MyTask(imageView);
		myTask.execute(imag_url);

	}

	// �Լ�����̳�AsyncTask
	class MyTask extends AsyncTask<String, Void, Bitmap> {

		private ImageView imageView1;

		public MyTask(ImageView iamImageView) {
			this.imageView1 = iamImageView;
		}

		private Bitmap bimap;

		// ������д�����߳�doInBackground
		@Override
		protected Bitmap doInBackground(String... params) {
			String url = params[0];
			// ��������
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