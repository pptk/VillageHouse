package com.example.tools;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.example.ip.Ipconfig;

public class Weather {
	// send()����
	public static String send(String city) {
		String result = null;
		String target = Ipconfig.weather + city + Ipconfig.weather_key; // Ҫ�ύ��Ŀ���ַ
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpRequest = new HttpGet(target); // ����HttpGet����
		HttpResponse httpResponse = null;
		try {
			httpResponse = httpclient.execute(httpRequest);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // �������ɹ�
				result = EntityUtils.toString(httpResponse.getEntity()).trim(); // ��ȡ���ص��ַ���
			} else {
				result = "fail";
			}
		} catch (ClientProtocolException e) {
			// TODO: handle exception\
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return result;
	}
}
