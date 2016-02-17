package com.example.login;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
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
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.model.User;
import com.example.villagehouse.MainActivity;

public class LoginButton extends Button implements OnClickListener {
	private EditText user, pwd;
	private Activity activity;
	private Handler handler;
	private String url;
	private User userModel;
	private ProgressDialog pro;

	public LoginButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.setOnClickListener(this);
	}

	// initDatas
	public void setLogin(Activity activity, EditText username, EditText pwd,
			String url) {
		this.activity = activity;
		this.user = username;
		this.pwd = pwd;
		this.url = url;
	}

	// onClick
	@Override
	public void onClick(View v) {
		pro = new ProgressDialog(activity);
		if (!user.getText().toString().equals("")
				&& !pwd.getText().toString().equals("")) {
			pro.show();
			new Thread(new Runnable() {
				@Override
				public void run() {
					String str = login(user.getText().toString(), pwd.getText()
							.toString());
					Message msg = handler.obtainMessage(0, str);
					handler.sendMessage(msg);
				}
			}).start();
			handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					pro.cancel();
					// TODO Auto-generated method stub
					if (!msg.obj.equals("账号或者密码错误") && !msg.obj.equals("")) {
						Toast.makeText(activity, "登陆成功", Toast.LENGTH_LONG)
								.show();
						userModel = jxJson(msg.obj.toString());

						if (userModel != null) {
							saveSharepreference(userModel.getUsername(),
									userModel.getPwd(), userModel.getName(),
									userModel.getHead(), activity);
						}
						startLogin();
					} else if (msg.obj.equals("账号或者密码错误")) {
						Toast.makeText(activity, "登陆失败", Toast.LENGTH_LONG)
								.show();
					} else if (msg.obj.equals("")) {
						Toast.makeText(activity, "服务器相应超时", Toast.LENGTH_LONG)
								.show();
						Log.i(">>>>", "服务器相应超时");
					}

					super.handleMessage(msg);
				}

			};
		} else {
			Toast.makeText(activity, "请完善账号密码信息", Toast.LENGTH_LONG).show();
		}
	}

	public User jxJson(String str) {
		User user;
		if (str.equals("")) {
			return null;
		} else {
			try {
				JSONArray jsy = new JSONArray(str);
				JSONObject job = jsy.getJSONObject(0);
				String username = job.getString("username");
				String pwd = job.getString("pwd");
				String name = job.getString("name");
				String head = job.getString("head");
				user = new User(username, pwd, name, head);
				return user;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	// start Intent
	public void startLogin() {
		Intent intent = new Intent(activity, MainActivity.class);
		activity.startActivity(intent);
		activity.finish();
	}

	// save in SharedPreferences
	public void saveSharepreference(String username, String pwd, String name,
			String head, Activity activity) {
		SharedPreferences sp = activity.getSharedPreferences("user_tab",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("name", name);
		editor.putString("head", head);
		editor.putString("username", username);
		editor.putString("pwd", pwd);
		editor.putString("addressName", name);
		editor.commit();
	}

	// HttpPost login
	public String login(String user, String pwd) {
		String result = null;
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost requet = new HttpPost(url);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", user)); // 用户名
		params.add(new BasicNameValuePair("pwd", pwd)); // 密码
		try {
			requet.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // 设置编码方式
			HttpResponse httpResponse = httpclient.execute(requet); // 执行HttpClient请求
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils.toString(httpResponse.getEntity()).trim();
				return result;
			} else {
				return "";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}

	// 实现注册方法。插入值到数据库。
	public void RegistAdmin(Activity activity, EditText usernameEditText,
			EditText pwdEditText) {
		String usernameString = usernameEditText.getText().toString();// 账号密码
		String pwdString = pwdEditText.getText().toString();

	}
}
