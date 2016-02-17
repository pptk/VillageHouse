package com.example.threefragment;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.adapter.OrderAdapter;
import com.example.ip.Ipconfig;
import com.example.model.OrderModel;
import com.example.villagehouse.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MyOrderActivity extends Activity {

	private ListView myOrderListView;
	private List<OrderModel> list;
	private String name;
	private String result = "";
	private Handler handler;
	private OrderAdapter adapter;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myorder_activity);

		SharedPreferences sp = getSharedPreferences("user_tab", MODE_PRIVATE);
		name = sp.getString("name", "");// 获取用户名
		Log.i("username", name);

		myOrderListView = (ListView) findViewById(R.id.myOrderListView);// 最外层的ListView

		list = new ArrayList<OrderModel>();
		initDatas();
	}

	private void initDatas() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				result = send(name);// 根据用户名去获取String形式
				list = jxSON(result);// 转换成list
				Message msg = handler.obtainMessage();
				handler.sendMessage(msg);
			}
		}).start();

		handler = new Handler() {
			public void handleMessage(Message msg) {
				if (list == null) {
					Toast.makeText(MyOrderActivity.this, "网络异常",
							Toast.LENGTH_SHORT).show();
				} else {
					adapter = new OrderAdapter(MyOrderActivity.this, list);
					myOrderListView.setAdapter(adapter);
				}
				super.handleMessage(msg);
			}// handlemessage
		};

	}

	// 解析list

	private List<OrderModel> jxSON(String result) {
		List<OrderModel> order = new ArrayList<OrderModel>();
		if (result.equals("")) {
			Toast.makeText(MyOrderActivity.this, "你目前还木有订单哦~",
					Toast.LENGTH_LONG).show();
			return null;
		} else {
			try {
				JSONArray xx = new JSONArray(result);// 转换成JSOn格式
				for (int i = 0; i < xx.length(); i++) {
					JSONObject jsonobject = xx.getJSONObject(i);
					String userName = jsonobject.getString("userName");// 名字
					String shopName = jsonobject.getString("shopName");// 店铺
					String orderMenu = jsonobject.getString("orderMenu");// menu
					// list
					String telphone = jsonobject.getString("telphone");// 电话
					String orderTime = jsonobject.getString("orderTime");// 时间

					OrderModel frag = new OrderModel(userName, shopName,
							orderMenu, telphone, orderTime);
					order.add(frag);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return order;
		}
	}

	// 获取list
	private String send(String str) {
		String x = null;
		String target = Ipconfig.urlstr + "myorder.jsp";
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpRequest = new HttpPost(target);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", str));// 传递用户名
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // 设置编码方式
			HttpResponse httpResponse = httpclient.execute(httpRequest); // 执行HttpClient请求
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 如果请求成功
				x = EntityUtils.toString(httpResponse.getEntity()).trim(); // 获取返回的字符串
				Log.i("MyOnclickActivity send", x);
			} else {
				result = "fail";
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace(); // 输出异常信息
		} catch (ClientProtocolException e) {
			e.printStackTrace(); // 输出异常信息
		} catch (IOException e) {
			e.printStackTrace(); // 输出异常信息
		}
		return x;
	}

}
