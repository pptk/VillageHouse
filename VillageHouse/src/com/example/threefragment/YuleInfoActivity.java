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
 *娱乐休闲的详情页面
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
		initView();// 获取ID

		Intent intent = getIntent();// 获取intent
		yid = intent.getStringExtra("y_id");
		String title = intent.getStringExtra("y_title");
		yule_title.setText(title); // 将标题在空间中显示

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
				result = send();// 从服务器获取数据
				list = jxJSON(result);
				Message msg = handler.obtainMessage();
				handler.sendMessage(msg);
			}
		}).start();

		handler = new Handler() {
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (list == null) {
					Toast.makeText(YuleInfoActivity.this, "网络异常",
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
	 * 解析jsons数組
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
			Log.i("jxJSON", "没有获取到数据");
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
		params.add(new BasicNameValuePair("y_id", yid));// 传递用户名
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // 设置编码方式
			HttpResponse httpResponse = httpclient.execute(httpRequest); // 执行HttpClient请求
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 如果请求成功
				x = EntityUtils.toString(httpResponse.getEntity()).trim(); // 获取返回的字符串
				Log.i("send", x);
			} else {
				x = null;
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace(); // 输出异常信息
		} catch (ClientProtocolException e) {
			e.printStackTrace(); // 输出异常信息
		} catch (IOException e) {
			e.printStackTrace(); // 输出异常信息
		}
		return x;
	}// send

}
