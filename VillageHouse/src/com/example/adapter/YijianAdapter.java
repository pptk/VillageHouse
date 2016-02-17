package com.example.adapter;

import java.util.List;

import com.example.model.Yijian;
import com.example.villagehouse.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class YijianAdapter extends BaseAdapter {
	private List<Yijian> list;
	private Context context;

	public YijianAdapter(List<Yijian> list, Context context) {
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
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(context);
		convertView = inflater.inflate(R.layout.yijian_item, null);
		TextView tv = (TextView) convertView.findViewById(R.id.textView1);
		tv.setText(list.get(position).getYijian());
		return convertView;
	}

}
