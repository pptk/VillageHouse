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

public class ConvenienceActivity extends Activity{
    
	public TextView content,name,loudong,time;
	public EditText content_text,name_text,loudong_text,time_text;
	public Button baoxiu_input;
	private String result="";
	private Handler handler;
	public  String username;
	
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.repair_activity);
		
		SharedPreferences sp = getSharedPreferences("actm", MODE_PRIVATE);
		username = sp.getString("uname", "");
		initView();
		initData();
		
	}//oncreate


	private void initData() {
		baoxiu_input.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if("".equals(content_text.getText().toString())||"".equals(name_text.getText().toString())
				   ||"".equals(loudong_text.getText().toString())||"".equals(time_text.getText().toString())					
						)
				{
					Toast.makeText(ConvenienceActivity.this,"����д��������Ϣ",Toast.LENGTH_SHORT).show();
				}
				
				//����һ���µ��̣߳����ڷ��Ͳ���ȡ��Ϣ
				new Thread(new Runnable()
				{
					public void run()
					{
						send();
						Message m=handler.obtainMessage();
						handler.sendMessage(m);
					}

				   }).start();

	         handler = new Handler(){
	       	    public void handleMessage(Message msg){
	              
                   if(result!=null)
                   {
                	   Toast.makeText(ConvenienceActivity.this,result,Toast.LENGTH_SHORT).show();
                   }
	               finish();
	     	       super.handleMessage(msg);	
	            }
	         };			
				
			}//onclick
		
	   });
		
	}


	private void initView() {
        content=(TextView)findViewById(R.id.bx_content);
        name=(TextView)findViewById(R.id.bx_name);
        loudong=(TextView)findViewById(R.id.bx_loudong);
        time=(TextView)findViewById(R.id.bx_showDate);
        content_text=(EditText)findViewById(R.id.content_text);
        name_text=(EditText)findViewById(R.id.name_text);
        loudong_text=(EditText)findViewById(R.id.loudong_text);
        time_text=(EditText)findViewById(R.id.showDate_text);
		baoxiu_input=(Button)findViewById(R.id.baoxiu_input);
	}
	

	private void send() {
		String target = Ipconfig.urlstr+"repairinput.jsp"; // Ҫ�ύ��Ŀ���ַ
		HttpClient httpclient=new DefaultHttpClient();
		HttpPost httpRequest = new HttpPost(target); // ����HttpPost����
		// ��Ҫ���ݵĲ������浽List������
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("httpclient", "send"));
		params.add(new BasicNameValuePair("r_username", username));//����������
		params.add(new BasicNameValuePair("r_content",content_text.getText().toString()));
		params.add(new BasicNameValuePair("r_name",name_text.getText().toString()));
		params.add(new BasicNameValuePair("r_address",loudong_text.getText().toString()));
		params.add(new BasicNameValuePair("r_time",time_text.getText().toString()));
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // ���ñ��뷽ʽ
			HttpResponse httpResponse =httpclient.execute(httpRequest); // ִ��HttpClient����
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
		
}
	
