package com.example.threefragment;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import com.example.adapter.YuleAdapter;
import com.example.ip.Ipconfig;
import com.example.model.YuleModel;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class EntertainmentActivity extends Activity implements
		IXListViewListener {
	private Button searchButton;
	private XListView yuleListView;
	private List<YuleModel> yuleModellist;
	private Handler handlerFirst, handlerSecond;
	private YuleAdapter adapter;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yule_activity);

		searchButton = (Button) findViewById(R.id.search_btn);
		yuleListView = (XListView) findViewById(R.id.yule_lv);
		// ���ü��ظ����ˢ�¹��ܡ�
		yuleListView.setXListViewListener(this);
		yuleListView.setPullLoadEnable(true);
		
		yuleModellist = new ArrayList<YuleModel>();
		initData();
		searchButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						String txt = ((EditText) findViewById(R.id.findEditText))
								.getText().toString();
						Log.i("txt", txt);
						String str = send(txt);
						yuleModellist = JxJson(str);
						Message msg = handlerFirst.obtainMessage();
						handlerFirst.sendMessage(msg);
					}
				}).start();
				handlerFirst = new Handler() {
					@Override
					public void handleMessage(Message msg) {
						// TODO Auto-generated method stub
						super.handleMessage(msg);
						// ��������
						Log.i("����", ">>>>>>>>>>>>>>>>>>>>");
						adapter = new YuleAdapter(EntertainmentActivity.this,
								yuleModellist);
						yuleListView.setAdapter(adapter);
					}
				};

			}
		});
	}// oncreate

	private void initData() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String str = send("");
				Log.i("str>>>>>>>>", str);
				yuleModellist = JxJson(str);// �������󷽷�
				Message msg = handlerSecond.obtainMessage();
				handlerSecond.sendMessage(msg);
			}
		}).start();
		handlerSecond = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				adapter = new YuleAdapter(EntertainmentActivity.this,
						yuleModellist);
				yuleListView.setAdapter(adapter);
				yuleListView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						YuleModel model = yuleModellist.get(position);
						String yid = model.getYid();
						Intent intent = new Intent();
						intent.putExtra("y_id", yid);
						intent.setClass(EntertainmentActivity.this,
								YuleInfoActivity.class);
						startActivity(intent);
						Log.i("����onItemClick", yid);
					}
				});
			}
		};
	}

	private List<YuleModel> JxJson(String str) {
		List<YuleModel> yl = new ArrayList<YuleModel>();
		if (str != null) {
			try {
				JSONArray jsonarr = new JSONArray(str);
				for (int i = 0; i < jsonarr.length(); i++) {
					JSONObject obj = jsonarr.getJSONObject(i);
					String yid = obj.getString("yid");
					String yname = obj.getString("ytitle");
					String yprofile = obj.getString("yprofile");
					String yimage = obj.getString("yimage");
					YuleModel ym = new YuleModel(yid, Ipconfig.urlstr + yimage,
							yname, yprofile);
					yl.add(ym);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Log.i("jxJSON", "û�л�ȡ������");
		}
		return yl;
	}

	private String send(String typea) {
		String result = null;
		String url = Ipconfig.urlstr + "yule.jsp";
		Log.i("url", url.toString());
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost request = new HttpPost(url);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("typea", typea));// ������������
		try {
			request.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // ���ñ��뷽ʽ
			Log.i("type", typea);
			HttpResponse response = httpclient.execute(request);// ִ������
			Log.i("response?>>>>>>>>>>>>>>>>>>>",
					String.valueOf(response.getStatusLine().getStatusCode()));
			if (response.getStatusLine().getStatusCode() == 200) { // �������ɹ�
				result = EntityUtils.toString(response.getEntity()).trim();
				Log.i("send(", result);
				return result;
			} else {
				result = "����ʧ��";
				Log.i("result", "����ʧ��");
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
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
		yuleListView.stopRefresh();
		yuleListView.stopLoadMore();
		yuleListView.setRefreshTime("�ո�");
	}
}
