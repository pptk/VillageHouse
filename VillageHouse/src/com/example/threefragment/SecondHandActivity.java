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
import org.json.JSONException;
import org.json.JSONObject;

import com.example.adapter.SecondHandAdapter;
import com.example.ip.Ipconfig;
import com.example.model.SecondHandModel;
import com.example.villagehouse.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
/*
 * 二手交易市场的主页，有显示用的listview和一个发表的textview
 */
public class SecondHandActivity extends Activity{
   
	private ListView lv;
	private TextView input;
	private Handler handler;
	private List<SecondHandModel> list;
	private SecondHandAdapter adapter;
    public String str;
    private String log="";
	private String count="";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secondhand_activity);
		list = new ArrayList<SecondHandModel>();
		lv = (ListView) findViewById(R.id.secondhand_lv);
		
		input=(TextView)findViewById(R.id.input);
		input.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent2=new Intent(SecondHandActivity.this,SecondHandInputActivity.class);
				startActivityForResult(intent2,0x18);
			}
		});
			
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				str = send();
				list = jxJSON(str);
				Log.i("Thread", list.toString());
				Message msg = handler.obtainMessage();
				handler.sendMessage(msg);
			}
		}).start();
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (str.equals("")) {
					Toast.makeText(SecondHandActivity.this, "网络异常", Toast.LENGTH_LONG)
							.show();
				} else {
					adapter = new SecondHandAdapter(SecondHandActivity.this, list);
					lv.setAdapter(adapter);
					lv.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							SecondHandModel  model = list.get(position);
							String sid= model.getSid();
							String sname=model.getName();
							String stime=model.getTime();
							String scontent=model.getContent();
							String simage1= model.getImage1();
							String simage2= model.getImage2();
													
							Intent intent = new Intent();
							intent.putExtra("s_id", sid);
							intent.putExtra("s_name", sname);
							intent.putExtra("s_time", stime);
							intent.putExtra("s_content", scontent);
							intent.putExtra("s_image1", simage1);
							intent.putExtra("s_image2", simage2);
							intent.setClass(SecondHandActivity.this,SecondHandDetialActivity.class);
							startActivity(intent);
							Log.i("这是onItemClick", sid);
						}

					});
				}
				super.handleMessage(msg);
			}
		};
	}

	private String send() {
		String str = null;
		String url=Ipconfig.urlstr+"secondhandlist.jsp";
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost request = new HttpPost(url);
		HttpResponse response = null;
		try {
			response = httpclient.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				str = EntityUtils.toString(response.getEntity()).trim();
				Log.i("send", str);
				return str;
			} else {
				Log.i("没有 访问成功", response.toString());
				return null;
			}
		} catch (Exception e) {

		}
		return null;
	}

	private List<SecondHandModel> jxJSON(String str) {
		List<SecondHandModel> sh = new ArrayList<SecondHandModel>();
		if (str.equals("")) {
			Log.i("jxJSON", "数据为空哦~");
		} else {
			try {
				JSONArray jsArr = new JSONArray(str);
				for (int i = 0; i < jsArr.length(); i++) {
					JSONObject jsobj = jsArr.getJSONObject(i);
					String sid=jsobj.getString("sid");
					String name = jsobj.getString("name");
					String content = jsobj.getString("content");
					String time = jsobj.getString("time");
					String image1 = jsobj.getString("image1");
					String image2 = jsobj.getString("image2");
					sh.add(new SecondHandModel(sid,name,content,image1,image2,time));
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
			return sh;
		}
		return null;
	}
	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0x18&& resultCode == 0x18) { //判断是否为待处理的结果
			Bundle bundle = data.getExtras(); //获取传递的数据包
			log= bundle.getString("result");
			count=bundle.getString("count");
			if("".equals(log)){
			    Toast.makeText(SecondHandActivity.this, "发表失败", Toast.LENGTH_LONG).show();
			}else{
			    Toast.makeText(SecondHandActivity.this,log, Toast.LENGTH_LONG).show();
			}
		}
	}
}
