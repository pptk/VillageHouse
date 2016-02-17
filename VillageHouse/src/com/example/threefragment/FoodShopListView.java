package com.example.threefragment;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.adapter.FoodListViewAdapter;
import com.example.ip.Ipconfig;
import com.example.model.FoodModel;
import com.example.view.listview.XListView;
import com.example.view.listview.XListView.IXListViewListener;
import com.example.villagehouse.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class FoodShopListView extends Activity implements IXListViewListener {

	private XListView foodListView;
	private List<FoodModel> list;
	private Handler handler;
	private FoodListViewAdapter adapter;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.foodshoping_activity);
		foodListView = (XListView) findViewById(R.id.foodListView);
		foodListView.setPullLoadEnable(true);// ���ü̳�IXListViewListener,���������δ��ɵķ�����
		foodListView.setXListViewListener(this);
		list = new ArrayList<FoodModel>();

		new Thread(new Runnable() {
			@Override
			public void run() {
				String x = send();
				list = jxJSON(x);
				Message msg = handler.obtainMessage(0, x);
				handler.sendMessage(msg);
			}
		}).start();

		handler = new Handler() {

			public void handleMessage(Message msg) {
				if (msg.obj.equals("")) {
					Toast.makeText(FoodShopListView.this, "�����쳣",
							Toast.LENGTH_SHORT).show();
				} else {
					adapter = new FoodListViewAdapter(FoodShopListView.this,
							list);
					foodListView.setAdapter(adapter);
					foodListView
							.setOnItemClickListener(new OnItemClickListener() {

								@Override
								public void onItemClick(AdapterView<?> parent,
										View view, int position, long id) {
									FoodModel model = list.get(position - 1);
									// get shop
									String sid = model.getFid();
									String simage = model.getFimage();
									String sname = model.getFname();
									String saddress = model.getFaddress();
									String stime = model.getFtime();
									Intent intent = new Intent();
									intent.putExtra("shopIdString", sid);
									intent.putExtra("shopImage", simage);
									intent.putExtra("shopName", sname);
									intent.putExtra("shopAddress", saddress);
									intent.putExtra("shopingTime", stime);
									Log.i("FoodShopListView 87", sid + simage
											+ sname + saddress + stime);
									intent.setClass(FoodShopListView.this,
											FoodShopActivity.class);
									startActivity(intent);
								}

							});
				}
				super.handleMessage(msg);
			}
		};

	}// oncreate

	private List<FoodModel> jxJSON(String foodinfo) {
		List<FoodModel> fd = new ArrayList<FoodModel>();
		if (foodinfo != null) {
			try {
				JSONArray jsonarr = new JSONArray(foodinfo);
				for (int i = 0; i < jsonarr.length(); i++) {
					JSONObject obj = jsonarr.getJSONObject(i);
					String fid = obj.getString("fid");
					String fname = obj.getString("fname");
					String fprofile = obj.getString("fprofile");
					String fsend = obj.getString("fsend");
					String faddress = obj.getString("faddress");
					String ftime = obj.getString("ftime");
					String fimage = obj.getString("fimage");
					FoodModel fm = new FoodModel(fid, Ipconfig.urlstr + fimage,
							fname, fprofile, fsend, faddress, ftime);
					fd.add(fm);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Log.i("jxJSON", "û�л�ȡ������");
		}
		return fd;
	}

	private String send() {
		String str = null;
		String url = Ipconfig.urlstr + "food.jsp";
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost request = new HttpPost(url);
		HttpResponse response;
		Log.i("send(", "try����");
		try {
			Log.i("send(", "ִ������1");
			response = httpclient.execute(request);// ִ������
			// Log.i("response", response.toString());
			Log.i("response",
					String.valueOf(response.getStatusLine().getStatusCode())
							.toString());
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // ������ɹ�
				str = EntityUtils.toString(response.getEntity()).trim();
				Log.i("send(", str);
				return str;
			} else {
				str = "";
				return str;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	@Override
	public void onRefresh() {
		Log.i("����ˢ�·���", "?>>>>>");
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Toast.makeText(this, "��ʱû��������!", Toast.LENGTH_LONG).show();
		letStop();
	}

	@Override
	public void onLoadMore() {
		Log.i("���Ǹ������ݷ���", "?>>>>>");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Toast.makeText(this, "ľ�и�������Ŷ!", Toast.LENGTH_LONG).show();
		letStop();
	}

	/**
	 * ֹͣˢ�·�����
	 */
	public void letStop() {
		foodListView.stopRefresh();
		foodListView.stopLoadMore();
		foodListView.setRefreshTime("�ո�");
	}

}
