package com.example.adapter;

import java.util.List;

import com.example.ip.Ipconfig;
import com.example.model.SecondHandModel;
import com.example.villagehouse.R;
import com.lidroid.xutils.BitmapUtils;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondHandAdapter extends BaseAdapter {

	private List<SecondHandModel> list;
	private Context context;

	public SecondHandAdapter(Context context, List<SecondHandModel> list) {
		super();
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BitmapUtils bitmaputils = new BitmapUtils(context);
		if (convertView == null) {
			LayoutInflater iflater = LayoutInflater.from(context);
			convertView = iflater.inflate(R.layout.secondlist_item, null);
		}
		TextView username = (TextView) convertView.findViewById(R.id.username);
		TextView createTime = (TextView) convertView
				.findViewById(R.id.createTime);
		TextView content = (TextView) convertView.findViewById(R.id.content);
		ImageView one = (ImageView) convertView.findViewById(R.id.one);
		ImageView two = (ImageView) convertView.findViewById(R.id.two);

		username.setText(list.get(position).getName());
		createTime.setText(list.get(position).getTime());
		content.setText(list.get(position).getContent());
		bitmaputils.display(one, Ipconfig.urlstr
				+ list.get(position).getImage1());
		bitmaputils.display(two, Ipconfig.urlstr
				+ list.get(position).getImage2());
		return convertView;
	}

}
