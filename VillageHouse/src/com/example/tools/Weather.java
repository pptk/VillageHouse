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
	// send()方法
	public static String send(String city) {
		String result = null;
		String target = Ipconfig.weather + city + Ipconfig.weather_key; // 要提交的目标地址
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpRequest = new HttpGet(target); // 创建HttpGet对象
		HttpResponse httpResponse = null;
		try {
			httpResponse = httpclient.execute(httpRequest);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 如果请求成功
				result = EntityUtils.toString(httpResponse.getEntity()).trim(); // 获取返回的字符串
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
