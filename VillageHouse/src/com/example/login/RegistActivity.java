package com.example.login;

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

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ip.Ipconfig;
import com.example.villagehouse.R;

public class RegistActivity extends Activity {
	private Button registButton;
	private EditText usernameEditText, pwdEditText;
	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.regist);

		usernameEditText = (EditText) findViewById(R.id.usernameEditText);
		pwdEditText = (EditText) findViewById(R.id.pwdEditText);
		registButton = (Button) findViewById(R.id.registButton);
		registButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!usernameEditText.getText().toString().equals("")
						&& !pwdEditText.getText().toString().equals("")) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							String flag = regist(usernameEditText.getText()
									.toString(), pwdEditText.getText()
									.toString());
							Message msg = handler.obtainMessage(0, flag);
							handler.sendMessage(msg);
						}
					}).start();
					handler = new Handler() {
						public void handleMessage(android.os.Message msg) {
							if (msg.obj.equals("yes")) {
								Toast.makeText(RegistActivity.this, "注册成功",
										Toast.LENGTH_LONG).show();
								finish();
							}
						};
					};
				} else {
					Toast.makeText(RegistActivity.this, "账号密码不能为空",
							Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	public String regist(String username, String pwd) {
		String flag = null;
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost request = new HttpPost(Ipconfig.urlstr + "servlet/aRegist");
		HttpResponse response = null;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", username)); // 用户名
		params.add(new BasicNameValuePair("pwd", pwd)); // 密码
		try {
			request.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // 设置编码方式
			response = httpclient.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				flag = EntityUtils.toString(response.getEntity()).trim();
				return flag;
			} else {
				return flag = "注册失败";
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "网络异常";
	}
}
