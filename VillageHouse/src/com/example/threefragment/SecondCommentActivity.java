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

import com.example.ip.Ipconfig;
import com.example.villagehouse.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/*
 * 二手交易评论发布的页面
 */
public class SecondCommentActivity extends Activity{
   
	public TextView title,space1,space2;
	public EditText content;
	public Button comment_button;
	private String result="";
	private Handler handler;
	public String sid;
	public String name;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secondhand_comment);
		//获取上个activity传递过来的值
		Intent intent=getIntent();
		sid=intent.getStringExtra("s_id");

		SharedPreferences sp = getSharedPreferences("actm", MODE_PRIVATE);
		name = sp.getString("uname", "");
		
		title=(TextView)findViewById(R.id.title);
		content=(EditText)findViewById(R.id.comment_miaosu); 
		comment_button=(Button)findViewById(R.id.comment_button);
		comment_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if("".equals(content.getText().toString()))
				{
					Toast.makeText(SecondCommentActivity.this,"请输入要回复的内容！！",Toast.LENGTH_LONG).show();
					return;
				}
	                				
				//创建一个新的线程，用于发送并读取信息
				new Thread(new Runnable()
				{
					public void run()
					{
						send();
						Message m=handler.obtainMessage();
						handler.sendMessage(m);
					}

				}).start();
			}
			
			
			
		});
		
		 handler = new Handler(){
	          	public void handleMessage(Message msg){
                     
	          		Intent intent =getIntent(); // 获取Intent对象
	          		Bundle bundle = new Bundle(); // 实例化传递的数据包
					bundle.putString("result", result);
					intent.putExtras(bundle);
					setResult(0x19, intent); // 设置返回的结果码，并返回调用该Activity的Activity
	                finish();

				    super.handleMessage(msg);	
			       }
			    };
		
	}
	

	public void send() {
		String target = Ipconfig.urlstr+"commentinput.jsp"; // 要提交的目标地址
		HttpClient httpclient=new DefaultHttpClient();
		HttpPost httpRequest = new HttpPost(target); // 创建HttpPost对象
		// 将要传递的参数保存到List集合中
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("httpclient", "send"));
		params.add(new BasicNameValuePair("s_id", sid));//专家编号
		params.add(new BasicNameValuePair("username",name));
		params.add(new BasicNameValuePair("miaosu",content.getText().toString())); //回复情况信息
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // 设置编码方式
			HttpResponse httpResponse =httpclient.execute(httpRequest); // 执行HttpClient请求
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 如果请求成功
				result = EntityUtils.toString(httpResponse.getEntity()).trim(); // 获取返回的字符串
				Log.i("send", result);
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
	}
}
