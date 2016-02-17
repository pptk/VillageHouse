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
import org.json.JSONException;
import org.json.JSONObject;

import com.example.adapter.CommentAdapter;
import com.example.ip.Ipconfig;
import com.example.model.CommentModel;
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/*
 * ���ֽ��׵�����ҳ�棬�����listview����ʾ�ظ�����Ϣ��
 */
public class SecondHandDetialActivity extends Activity {
	public TextView title, username, showtime, content;
	public TextView space1, space2, space3, space4, image_space1, image_space2;
	public ImageView image1, image2;
	public ListView lv;
	public TextView input;// ����������۵Ŀؼ�
	private List<CommentModel> list = null; // ���۵�Model
	private String result;
	private Handler handler;
	public String sid;
	public AsynctaskImageLoad loader = new AsynctaskImageLoad();
	public String one = "one";
	public String two = "two";
	public String three = "three";
	public String simage1, simage2, simage3;
	private String log = "";
	private CommentAdapter adapter;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secondhand_info);
		initView();// ��ȡID��
		BitmapUtils bitmaputils = new BitmapUtils(this);
		/*
		 * ��ȡ����һ��activity���ݹ���������
		 */
		Intent intent = getIntent();// ��ȡintent
		sid = intent.getStringExtra("s_id");
		String sname = intent.getStringExtra("s_name");
		String stime = intent.getStringExtra("s_time");
		String scontent = intent.getStringExtra("s_content");
		simage1 = intent.getStringExtra("s_image1");
		simage2 = intent.getStringExtra("s_image2");
		Log.i("sname", sname);
		Log.i("image1", simage1);
		Log.i("image2", simage2);
		// String count= intent.getStringExtra("c_count");
		// Log.i("image",count);
		// ��������ʾ�ڿؼ���
		username.setText(sname);
		showtime.setText(stime);
		content.setText(scontent);
		bitmaputils.display(image1, Ipconfig.urlstr + simage1);
		bitmaputils.display(image2, Ipconfig.urlstr + simage2);

		input.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent2 = new Intent(SecondHandDetialActivity.this,
						SecondCommentActivity.class);
				intent2.putExtra("s_id", sid);
				startActivityForResult(intent2, 0x19);
			}
		});

		// �½�list
		list = new ArrayList<CommentModel>();

		initDatas();

	}

	private void initDatas() {
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
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (result == null) {
					// ���ڵ��ͼƬ��ʾ��ͼ
					image1.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Intent intent1 = new Intent();
							intent1.putExtra("log", one);
							intent1.putExtra("s_image1", simage1);
							intent1.setClass(SecondHandDetialActivity.this,
									BigImageActivity2.class);
							startActivity(intent1);

						}
					});
					image2.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Intent intent2 = new Intent();
							intent2.putExtra("log", two);
							intent2.putExtra("s_image2", simage2);
							intent2.setClass(SecondHandDetialActivity.this,
									BigImageActivity2.class);
							startActivity(intent2);

						}
					});
				} else {

					image1.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Intent intent1 = new Intent();
							intent1.putExtra("log", one);
							intent1.putExtra("s_image1", simage1);
							intent1.setClass(SecondHandDetialActivity.this,
									BigImageActivity2.class);
							startActivity(intent1);

						}
					});
					image2.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Intent intent2 = new Intent();
							intent2.putExtra("log", two);
							intent2.putExtra("s_image2", simage2);
							intent2.setClass(SecondHandDetialActivity.this,
									BigImageActivity2.class);
							startActivity(intent2);

						}
					});

					lv.setAdapter(new CommentAdapter(
							SecondHandDetialActivity.this, list));// ������
					lv.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub

							CommentModel modle = list.get(position);
							String cid = modle.getCid();
							String cname = modle.getCname();
							String ctime = modle.getCtime();
							String ccontent = modle.getCcontent();

						}
					});
				}
				super.handleMessage(msg);
			}
		};

	}

	/*
	 * ����jsons����
	 */
	private List<CommentModel> jxJSON(String replay) {
		List<CommentModel> cm = new ArrayList<CommentModel>();
		if (replay.equals("")) {
			Log.i("jxJSON", "����Ϊ��Ŷ~");
		} else {
			try {
				Log.i("JSON = null", replay);
				JSONArray xx = new JSONArray(replay);// ת����JSOn��ʽ
				for (int i = 0; i < xx.length(); i++) {
					JSONObject jsonobject = xx.getJSONObject(i);
					String cname = jsonobject.getString("cname");
					String ctime = jsonobject.getString("ctime");
					String ccontent = jsonobject.getString("ccontent");
					CommentModel frag = new CommentModel(cname, ctime, ccontent);
					cm.add(frag);
				}
			} catch (JSONException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return cm;
		}
		return null;
	}

	/*
	 * ��ȡ�ؼ�
	 */
	private void initView() {
		title = (TextView) findViewById(R.id.title);
		input = (TextView) findViewById(R.id.input);
		username = (TextView) findViewById(R.id.username);
		showtime = (TextView) findViewById(R.id.showtime);
		content = (TextView) findViewById(R.id.content);
		image1 = (ImageView) findViewById(R.id.image1);
		image2 = (ImageView) findViewById(R.id.image2);
		image_space1 = (TextView) findViewById(R.id.image_space1);
		lv = (ListView) findViewById(R.id.lv);
	}

	/*
	 * send����
	 */
	private String send() {
		// TODO Auto-generated method stub
		String x = null;
		String target = Ipconfig.urlstr + "commentinfo.jsp";
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpRequest = new HttpPost(target);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("httpclient", "get"));
		params.add(new BasicNameValuePair("s_id", sid));// �����û���
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // ���ñ��뷽ʽ
			HttpResponse httpResponse = httpclient.execute(httpRequest); // ִ��HttpClient����
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // �������ɹ�
				x = EntityUtils.toString(httpResponse.getEntity()).trim(); // ��ȡ���ص��ַ���
				Log.i("send", x);
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
		return x;

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0x19 && resultCode == 0x19) { // �ж��Ƿ�Ϊ������Ľ��
			Bundle bundle = data.getExtras(); // ��ȡ���ݵ����ݰ�
			log = bundle.getString("result");
			if ("".equals(log)) {
				Toast.makeText(SecondHandDetialActivity.this, "����ʧ��",
						Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(SecondHandDetialActivity.this, log,
						Toast.LENGTH_LONG).show();
			}
		}
	}
}
