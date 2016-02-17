package com.example.threefragment;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.amap.api.location.f;
import com.example.ip.Ipconfig;
import com.example.model.MenuModel;
import com.example.villagehouse.R;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class OrderOkActivity extends Activity {

	private Button submitOrderButton;// �ύ��ť
	private ArrayList<MenuModel> list;// �˵���list
	private Handler handler;// ���Ͷ���������������Ϣ
	private String result = "";// ��ȡsend�����Ľ��
	private String order = "";// ��ȡintent����������Ϣ
	private ListView orderListView;// ��ʾ������listview
	private String shopName;
	private PopupWindow addressPopupWindw;
	private TextView addressTextView;
	private EditText CustomerEditText, customerAddressEditText,
			customerTelphoneEditText;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_ok);

		initView();

		list = jxJson(order);
		initDatas();
		initPopupWidow();
		submitOrder();
	}

	// show Order ListView
	private void initDatas() {
		List<Map<String, Object>> listitems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("foodName", list.get(i).getMname());
			map.put("foodPrice", list.get(i).getMprice());
			listitems.add(map);
		}
		SimpleAdapter adapter = new SimpleAdapter(this, listitems,
				R.layout.order_ok_item,
				new String[] { "foodName", "foodPrice" }, new int[] {
						R.id.foodNameTextView, R.id.foodPriceTextView });

		orderListView.setAdapter(adapter);
	}

	// submit Order
	private void submitOrder() {
		// �ύ������
		submitOrderButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// ����һ���µ��̣߳����ڷ��Ͳ���ȡ��Ϣ
				new Thread(new Runnable() {
					public void run() {
						send();
						Message m = handler.obtainMessage();
						handler.sendMessage(m);
					}

				}).start();
			}
		});
		handler = new Handler() {
			public void handleMessage(Message msg) {
				if (result.equals("yes")) {
					Toast.makeText(OrderOkActivity.this, "�ύ�����ɹ�",
							Toast.LENGTH_LONG).show();
					finish();
				} else {
					Toast.makeText(OrderOkActivity.this, "�ύ����ʧ��",
							Toast.LENGTH_LONG).show();
					finish();
				}
				super.handleMessage(msg);
			}
		};

	}

	// initView
	private void initView() {
		Intent intent = getIntent();
		order = intent.getStringExtra("order");
		shopName = intent.getStringExtra("shopName");
		submitOrderButton = (Button) findViewById(R.id.submitOrderButton);
		orderListView = (ListView) findViewById(R.id.orderListView);
		addressTextView = (TextView) findViewById(R.id.address);
		addressPopupWindw = new PopupWindow();
		addressTextView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i("137", "137");
				// TODO Auto-generated method stub
				if (addressPopupWindw.isShowing()) {
					addressPopupWindw.dismiss();
				} else {
					addressPopupWindw.showAtLocation(
							findViewById(R.id.linearlayout), Gravity.CENTER, 0,
							0);
					// addressPopupWindw.showAsDropDown(findViewById(R.id.title));
				}
			}
		});
	}

	private void initPopupWidow() {

		SharedPreferences sp = getSharedPreferences("user_tab", MODE_PRIVATE);
		String username = sp.getString("addressName", "");// ��ȡ�û���
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.address_popupwindow, null);
		CustomerEditText = (EditText) layout
				.findViewById(R.id.CustomerEditText);
		CustomerEditText.setText(username);
		customerAddressEditText = (EditText) layout
				.findViewById(R.id.customerAddressEditText);
		customerTelphoneEditText = (EditText) layout
				.findViewById(R.id.customerTelphoneEditText);

		// customerAddress = customerAddressEditText.getText().toString();
		// customerTelphone = customerTelphoneEditText.getText().toString();
		Button addressIsOkButton = (Button) layout
				.findViewById(R.id.addressIsOkButton);
		Button defaultAddressButton = (Button) layout
				.findViewById(R.id.defaultAddressButton);
		defaultAddressButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				addressPopupWindw.dismiss();
				Toast.makeText(OrderOkActivity.this, "����ΪĬ�ϵ�ַ�ɹ�",
						Toast.LENGTH_LONG).show();
				saveAddress(CustomerEditText.getText().toString(),
						customerAddressEditText.getText().toString(),
						customerTelphoneEditText.getText().toString());
			}
		});
		addressIsOkButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!CustomerEditText.getText().toString().equals("")
						&& !customerAddressEditText.getText().toString()
								.equals("")
						&& !customerTelphoneEditText.getText().toString()
								.equals("")) {
					addressPopupWindw.dismiss();
				} else {
					Toast.makeText(OrderOkActivity.this, "���ݲ���Ϊ��",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		addressPopupWindw = new PopupWindow(layout);
		addressPopupWindw.setFocusable(true);

		addressPopupWindw.setWidth(LayoutParams.MATCH_PARENT);
		addressPopupWindw.setHeight(LayoutParams.WRAP_CONTENT);
		// ����popupwindow�����Ļ�����ط���ʧ
		addressPopupWindw.setBackgroundDrawable(this.getResources()
				.getDrawable(R.drawable.bg_pop));// ���ñ���ͼƬ�������ڲ��������ã�Ҫͨ������������
		addressPopupWindw.setOutsideTouchable(true);
	}

	public void saveAddress(String name, String address, String telphone) {
		SharedPreferences sp = getSharedPreferences("user_tab", MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("addressName", name);
		editor.putString("address", address);
		editor.putString("telphone", telphone);
		editor.commit();
	}

	// send submit order service
	public void send() {
		String target = Ipconfig.urlstr + "order.jsp";
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpRequest = new HttpPost(target);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("ordermenu", order));// �˵�
		params.add(new BasicNameValuePair("shopName", shopName));// ����
		params.add(new BasicNameValuePair("username", CustomerEditText
				.getText().toString()));// ����
		params.add(new BasicNameValuePair("telphone", customerTelphoneEditText
				.getText().toString()));// ����
		Log.i("telphone", customerTelphoneEditText.getText().toString());
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // ���ñ��뷽ʽ
			HttpResponse httpResponse = httpclient.execute(httpRequest); // ִ��HttpClient����
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // �������ɹ�
				result = EntityUtils.toString(httpResponse.getEntity()).trim(); // ��ȡ���ص��ַ���
				Log.i("send", result);
			} else {
				result = "fail";
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace(); // ����쳣��Ϣ
		} catch (ClientProtocolException e) {
			e.printStackTrace(); // ����쳣��Ϣ
		} catch (IOException e) {
			e.printStackTrace(); // ����쳣��Ϣ
		}
	}

	// ����json
	public ArrayList<MenuModel> jxJson(String str) {
		ArrayList<MenuModel> list = new ArrayList<MenuModel>();
		if (!str.equals("")) {
			try {
				JSONArray jsy = new JSONArray(str);
				for (int i = 0; i < jsy.length(); i++) {
					JSONObject job = (JSONObject) jsy.get(i);
					String food = job.getString("mname");
					String price = job.getString("mprice");
					MenuModel model = new MenuModel(food, price);
					list.add(model);
				}
				return list;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} else {
			Toast.makeText(OrderOkActivity.this, "����ʧ��", Toast.LENGTH_LONG)
					.show();
		}
		return null;
	}
}
