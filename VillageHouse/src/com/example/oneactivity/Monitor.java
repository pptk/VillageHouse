package com.example.oneactivity;

import java.io.IOException;
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
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.YijianAdapter;
import com.example.ip.Ipconfig;
import com.example.model.Yijian;
import com.example.villagehouse.R;

public class Monitor extends Activity {
	private Button findButton;
	private EditText searchEditText;
	private ListView lv;
	private List<Yijian> list;
	private Handler handlerFirst, handlerSecond;
	private YijianAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.monitor_activity);

		initView();
		initDatas();// ������桢ˢ�����ݷ���
		findButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!searchEditText.getText().toString().equals("")) {
					new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							String str = PostYijian(searchEditText.getText().toString());
							list = JxJson(str);
							Message msg = handlerFirst.obtainMessage();
							handlerFirst.sendMessage(msg);
						}
					}).start();
				}else{
					Toast.makeText(Monitor.this, "������һЩ����Ŷ~��", Toast.LENGTH_LONG).show();
				}
				handlerFirst = new Handler() {
					@Override
					public void handleMessage(Message msg) {
						// TODO Auto-generated method stub
						super.handleMessage(msg);
						// ��������
						Log.i("����", ">>>>>>>>>>>>>>>>>>>>");
						adapter = new YijianAdapter(list, Monitor.this);
						lv.setAdapter(adapter);
					}
				};

			}
		});
	}

	// ˢ������
	private void initDatas() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String str = PostYijian("pxh");
				Log.i("str>>>>>>>>", str);
				list = JxJson(str);// �������󷽷�
				Message msg = handlerSecond.obtainMessage();
				handlerSecond.sendMessage(msg);
			}
		}).start();
		handlerSecond = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				adapter = new YijianAdapter(list, Monitor.this);
				lv.setAdapter(adapter);
			}
		};
	}

	// post��������
	private String PostYijian(String str) {
		String result = null;
		HttpClient client = new DefaultHttpClient();
		HttpPost httpRequest = new HttpPost(Ipconfig.urlstr
				+ "/servlet/YijianServlet"); // ����HttpPost����
		// ��Ҫ���ݵĲ������浽List������
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("str", str)); // �û���
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // ���ñ��뷽ʽ
			HttpResponse sponse = client.execute(httpRequest);
			if (sponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils.toString(sponse.getEntity()).trim(); // ��ȡ���ص��ַ���
				Log.i("Result>>>>>>>>>>>>", result);
				return result;
			} else {
				return result = "ʧ��";
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private List<Yijian> JxJson(String str) {
		List<Yijian> list = new ArrayList<Yijian>();
		if (!(str.equals(""))) {
			try {
				JSONArray jsy = new JSONArray(str);
				for (int i = 0; i < jsy.length(); i++) {
					JSONObject job = jsy.getJSONObject(i);
					String x = job.getString("yijian");
					Yijian yj = new Yijian();
					yj.setYijian(x);
					list.add(yj);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		} else {
			Log.i("JSON JX", "NULL");
			return null;
		}
	}

	private void initView() {
		findButton = (Button) findViewById(R.id.findButton);
		searchEditText = (EditText) findViewById(R.id.searchEditText);
		lv = (ListView) findViewById(R.id.lv);
	}
}
