package com.example.day04_webservice;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * webservice 
 * ��������ƶ��豸��������������Ӳ��������ƽ̨��һ����
 * �������ṩ�û��ӿڣ�Ȼ�����������ӿ�������ʲô���ܣ���ô����
 * 
 * @author Administrator
 * 
 */
public class MainActivity extends Activity {
	private String namespace = "http://www.36wu.com/";
	private String name = "GetWeather";
	private String AuthKey = "d3b630e80ea04c8d8ef493c31a004af7";
	private String url = "http://web.36wu.com/WeatherService.asmx?WSDL";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		intiView();

	}

	private void intiView() {
		final EditText distance = (EditText) findViewById(R.id.distance);
		Button send = (Button) findViewById(R.id.send);
		send.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				new Thread() {
					public void run() {
						String di = distance.getText().toString();
						// ��ʼWebService��ʹ��
						SoapObject soapObject = new SoapObject(namespace, name);
						// ���ò�������
						soapObject.addProperty("district", di);
						soapObject.addProperty("authkey", AuthKey);
						// �õ�HttpTransportSE����,���÷���url
						HttpTransportSE se = new HttpTransportSE(url);
						// �õ�serializationEnvelope����,����Soap�汾��
						SoapSerializationEnvelope serializationEnvelope = new SoapSerializationEnvelope(
								SoapEnvelope.VER11);
						// ���÷��͸�����������Ϣ
						serializationEnvelope.bodyOut = soapObject;
						// ����֧��.NET���ԣ�php,javaweb,python...
						serializationEnvelope.dotNet = true;
						// ����call����
						try {
							se.call("http://www.36wu.com/GetWeather",
									serializationEnvelope);
							// ��������
							// ���÷��͸�����������Ϣ
							// �õ����������ص�����
							SoapObject soapObject_in = (SoapObject) serializationEnvelope.bodyIn;
							System.out.println("============"
									+ soapObject_in.toString());
							// �õ�GetWeatherResult�ֶ��°�������Ϣ
							SoapObject getWeatherResult = (SoapObject) soapObject_in
									.getProperty("GetWeatherResult");
							SoapObject data = (SoapObject) getWeatherResult
									.getProperty("data");
							System.out.println(data.toString());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					};
				}.start();

			}
		});
	}
}