package com.example.adapter;

import java.util.List;




import com.example.model.CommentModel;
import com.example.villagehouse.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



public class CommentAdapter extends BaseAdapter{
   
	private List<CommentModel> list;
	private Context context;


	public CommentAdapter(Context context, List<CommentModel> list) {
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
		if (convertView == null) {
			LayoutInflater iflater = LayoutInflater.from(context);
			convertView = iflater.inflate(R.layout.comment_item, null);
		}
		TextView username = (TextView) convertView.findViewById(R.id.username);
		TextView time = (TextView) convertView
				.findViewById(R.id.time);
		TextView content = (TextView) convertView.findViewById(R.id.content);


		username.setText(list.get(position).getCname());
		time.setText(list.get(position).getCtime());
		content.setText(list.get(position).getCcontent());
		return convertView;
	}

}
