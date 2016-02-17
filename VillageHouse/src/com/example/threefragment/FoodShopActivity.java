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

import com.example.adapter.MenuAdapter;
import com.example.ip.Ipconfig;
import com.example.model.MenuModel;
import com.example.villagehouse.R;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FoodShopActivity extends Activity {

	public TextView myOrderTextView, shopNameTextView, shopAddressTextView,
			shopTimeTextView;
	public ImageView shopImageView;
	public ListView menuListView;
	public String idString;
	private List<MenuModel> list, lists;
	private String result, fname;
	private Handler handler;
	private MenuAdapter adapter;
	private LinearLayout summaryLinearLayout;
	private BitmapUtils bitmapUtils;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.food_info);
		initView();
		Intent intent = getIntent();
		idString = intent.getStringExtra("shopIdString");
		fname = intent.getStringExtra("shopName");
		String fimage = intent.getStringExtra("shopImage");
		String faddress = intent.getStringExtra("shopAddress");
		String ftime = intent.getStringExtra("shopingTime");
		bitmapUtils.display(shopImageView, fimage);
		shopNameTextView.setText(fname);
		shopAddressTextView.setText(faddress);
		shopTimeTextView.setText(ftime);
		list = new ArrayList<MenuModel>();
		lists = new ArrayList<MenuModel>();
		initDatas();

	}// oncreate

	private void initView() {
		bitmapUtils = new BitmapUtils(this);
		myOrderTextView = (TextView) findViewById(R.id.myOrderTextView);
		shopNameTextView = (TextView) findViewById(R.id.shopNameTextView);
		shopAddressTextView = (TextView) findViewById(R.id.shopAddressTextView);
		shopTimeTextView = (TextView) findViewById(R.id.shopTimeTextView);
		shopImageView = (ImageView) findViewById(R.id.shopImageView);
		menuListView = (ListView) findViewById(R.id.menuListView);
		summaryLinearLayout = (LinearLayout) findViewById(R.id.summaryLinearLayout);
	}

	

	private void initDatas() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				result = send();
				list = jxSON(result);
				Message msg = handler.obtainMessage();
				handler.sendMessage(msg);
			}
		}).start();
		handler = new Handler() {
			public void handleMessage(Message msg) {
				if (list == null) {
					Toast.makeText(FoodShopActivity.this, "网络异常",
							Toast.LENGTH_SHORT).show();
				} else {
					adapter = new MenuAdapter(FoodShopActivity.this, list);
					menuListView.setAdapter(adapter);
					menuListView
							.setOnItemClickListener(new OnItemClickListener() {

								@Override
								public void onItemClick(AdapterView<?> parent,
										View view, int position, long id) {
									MenuModel model = list.get(position);
									MenuModel model1 = new MenuModel(model
											.getMname(), model.getMprice());
									lists.add(model1);
								}

							});
				}
				super.handleMessage(msg);
			}
		};
		// 结算
		summaryLinearLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent2 = new Intent();
				Gson gson = new Gson();
				String x = gson.toJson(lists);
				intent2.putExtra("order", x);
				intent2.putExtra("shopName", fname);
				intent2.setClass(FoodShopActivity.this, OrderOkActivity.class);
				startActivity(intent2);
				finish();
			}
		});

		myOrderTextView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent3 = new Intent();
				// intent3.putExtra("o_uname",name);
				intent3.setClass(FoodShopActivity.this, MyOrderActivity.class);
				Log.i(">>>", intent3.toString());
				startActivity(intent3);
			}

		});
	}

	// initdatas
	private List<MenuModel> jxSON(String result) {
		List<MenuModel> menu = new ArrayList<MenuModel>();
		try {
			JSONArray xx = new JSONArray(result);// 转换成JSOn格式
			for (int i = 0; i < xx.length(); i++) {
				JSONObject jsonobject = xx.getJSONObject(i);
				String mname = jsonobject.getString("mname");
				String mprice = jsonobject.getString("mprice");

				MenuModel frag = new MenuModel(mname, mprice);
				menu.add(frag);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menu;
	}

	// get menu
	private String send() {
		String x = null;
		String target = Ipconfig.urlstr + "foodinfo.jsp";
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpRequest = new HttpPost(target);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("httpclient", "get"));
		params.add(new BasicNameValuePair("idString", idString));// 传递用户名
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // 设置编码方式
			HttpResponse httpResponse = httpclient.execute(httpRequest); // 执行HttpClient请求
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 如果请求成功
				x = EntityUtils.toString(httpResponse.getEntity()).trim(); // 获取返回的字符串

				Log.i("send", x);
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
