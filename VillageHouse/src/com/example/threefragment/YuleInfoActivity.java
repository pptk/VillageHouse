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

import com.example.ip.Ipconfig;
import com.example.model.YuleModel;
import com.example.tools.AsynctaskImageLoad;
import com.example.tools.CallbackImpl;
import com.example.villagehouse.R;
import com.lidroid.xutils.BitmapUtils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/*
 *�������е�����ҳ��
 *
 */
public class YuleInfoActivity extends Activity {

	public TextView title, yule_title, phone, phonetext, content;
	public ImageView image;
	public TextView space, title_3;
	private List<YuleModel> list = null;
	private String result;
	private Handler handler;
	public String yid;
	BitmapUtils bitmaputils;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yule_info);
		initView();// ��ȡID

		Intent intent = getIntent();// ��ȡintent
		yid = intent.getStringExtra("y_id");
		String title = intent.getStringExtra("y_title");
		yule_title.setText(title); // �������ڿռ�����ʾ

		initDatas();
	}// oncreate

	private void initView() {
		// TODO Auto-generated method stub
		bitmaputils = new BitmapUtils(this);
		title = (TextView) findViewById(R.id.title);
		yule_title = (TextView) findViewById(R.id.yule_title);
		title_3 = (TextView) findViewById(R.id.title_3);
		space = (TextView) findViewById(R.id.space);
		phone = (TextView) findViewById(R.id.phone);
		phonetext = (TextView) findViewById(R.id.phonetext);
		content = (TextView) findViewById(R.id.content);
		image = (ImageView) findViewById(R.id.image);
	}

	private void initDatas() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				result = send();// �ӷ�������ȡ����
				list = jxJSON(result);
				Message msg = handler.obtainMessage();
				handler.sendMessage(msg);
			}
		}).start();

		handler = new Handler() {
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (list == null) {
					Toast.makeText(YuleInfoActivity.this, "�����쳣",
							Toast.LENGTH_LONG).show();
				} else {
					YuleModel model = list.get(0);
					phonetext.setText(model.getYphone());
					content.setText(model.getYcontent());
					bitmaputils.display(image, model.getYbimage());
				}

				super.handleMessage(msg);
			}
		};
	}

	/*
	 * ����jsons���M
	 */
	private List<YuleModel> jxJSON(String result) {
		List<YuleModel> ym = new ArrayList<YuleModel>();
		if (result != null) {
			try {
				Log.i("JSON = null", result);
				JSONArray jsonarr = new JSONArray(result);
				JSONObject obj = jsonarr.getJSONObject(0);
				String phone = obj.getString("yphone");
				String content = obj.getString("ycontent");
				String pic = obj.getString("ybimage");
				YuleModel yu = new YuleModel(Ipconfig.urlstr + pic, phone,
						content);
				ym.add(yu);
				return ym;

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else {
			Log.i("jxJSON", "û�л�ȡ������");
		}
		return null;
	}

	private String send() {
		String x = null;
		String target = Ipconfig.urlstr + "yuleinfo.jsp";
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpRequest = new HttpPost(target);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("httpclient", "get"));
		params.add(new BasicNameValuePair("y_id", yid));// �����û���
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // ���ñ��뷽ʽ
			HttpResponse httpResponse = httpclient.execute(httpRequest); // ִ��HttpClient����
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // �������ɹ�
				x = EntityUtils.toString(httpResponse.getEntity()).trim(); // ��ȡ���ص��ַ���
				Log.i("send", x);
			} else {
				x = null;
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace(); // ����쳣��Ϣ
		} catch (ClientProtocolException e) {
			e.printStackTrace(); // ����쳣��Ϣ
		} catch (IOException e) {
			e.printStackTrace(); // ����쳣��Ϣ
		}
		return x;
	}// send

}
