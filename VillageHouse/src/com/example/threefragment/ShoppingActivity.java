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
import org.json.JSONObject;



import com.example.adapter.FoodListViewAdapter;
import com.example.adapter.ShoppingAdapter;
import com.example.ip.Ipconfig;
import com.example.model.FoodModel;
import com.example.model.ShoppingModel;
import com.example.villagehouse.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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

public class ShoppingActivity extends Activity{
    
	private ListView shopping_lv;
	private TextView title;
	private List<ShoppingModel> list;
	private Handler handler;
	private ShoppingAdapter adapter;
	private String x;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shopping_activity);
		
		title=(TextView)findViewById(R.id.title);
	    shopping_lv=(ListView)findViewById(R.id.shopping_lv);
	    
	    list=new ArrayList<ShoppingModel>();
	    new Thread(new Runnable(){
			@Override
			public void run() {
			   x=send();
			   list=jxJSON(x);
			   Message msg=handler.obtainMessage();
			   handler.sendMessage(msg);
			}

		}).start();
		
		handler=new Handler(){
			
			public void handleMessage(Message msg){
				if(x.equals(""))
				{
					Toast.makeText(ShoppingActivity.this,"网络异常",Toast.LENGTH_SHORT).show();
				}else{
					adapter=new ShoppingAdapter(ShoppingActivity.this,list);
					shopping_lv.setAdapter(adapter);
					shopping_lv.setOnItemClickListener(new OnItemClickListener(){

						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							ShoppingModel model=list.get(position);
							String sphone=model.getSphone();
							Intent intent=new Intent();
                            intent.setAction(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:"+sphone));
							startActivity(intent);
							Log.i("这是onItemClick", sphone);
						}
						
					});
				}
				super.handleMessage(msg);
			}
		};
		
	}//oncreate
	
	
	
	
	
	
	private List<ShoppingModel> jxJSON(String x) {
		List<ShoppingModel> sp=new ArrayList<ShoppingModel>();
		   if(x!=null)
		   {
			   try{
				    JSONArray jsonarr=new JSONArray(x);
				    for(int i=0;i<jsonarr.length();i++)
				    {
				    	JSONObject obj=jsonarr.getJSONObject(i);
				    	String sid=obj.getString("sid");
				    	String sname=obj.getString("sname");
				    	String sphone=obj.getString("sphone");
				    	String sprice=obj.getString("sprice");
				    	String simage=obj.getString("simage");
				    	ShoppingModel sm=new ShoppingModel(sid,Ipconfig.urlstr+simage,sname,sphone,sprice);
				    	sp.add(sm);
				    }
			   }catch(Exception e)
			   {
				   e.printStackTrace();
			   }
		   }else{
			   Log.i("jxJSON", "没有获取到数据");
		   }
		   return sp;
	}
	

	private String send() {
		String str=null;
		String url=Ipconfig.urlstr+"shopping.jsp";
		HttpClient httpclient=new DefaultHttpClient();
		HttpPost request=new HttpPost(url);
		HttpResponse response;
		Log.i("send(", "try外面");
		try{
			Log.i("send(", "执行请求1");
			response = httpclient.execute(request);//执行请求
			Log.i("response", response.toString());
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  //如果请求成功
				str = EntityUtils.toString(response.getEntity()).trim();
				Log.i("send(", str);
				return str;
			} else {
				str = "请求失败";
				Log.i("send(", "请求失败");
				return str;

			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return str;
	}
}
