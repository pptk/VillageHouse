package com.example.login;

import com.example.ip.Ipconfig;
import com.example.villagehouse.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * LoginActivity
 * 
 * @fish 2015
 */
public class LoginActivity extends Activity {
	private LoginButton loginButton;
	private Button registButton;
	private EditText usernameEditText, pwdEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		initView();
		intentActivity();
	}

	public void initView() {
		loginButton = (LoginButton) findViewById(R.id.loginButton);
		registButton = (Button) findViewById(R.id.registButton);
		usernameEditText = (EditText) findViewById(R.id.usernameEditText);
		pwdEditText = (EditText) findViewById(R.id.pwdEditText);
		// 登陆
		loginButton.setLogin(this, usernameEditText, pwdEditText,
				Ipconfig.urlstr + "servlet/aLogin");// login

		registButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LoginActivity.this,
						RegistActivity.class);
				startActivity(intent);
			}
		});
	}

	/**
	 * 如果SharedPreferences里面存在账号密码、则直接跳转到主页
	 */
	public void intentActivity() {
		SharedPreferences sp = this.getSharedPreferences("user_tab",
				Context.MODE_PRIVATE);
		String username = sp.getString("username", "");
		String pwd = sp.getString("pwd", "");
		if (!username.equals("") && !pwd.equals("")) {
			loginButton.startLogin();
		} else {
			Log.i("<<<", "SharedPreferences里面是空的");
		}
	}

}
