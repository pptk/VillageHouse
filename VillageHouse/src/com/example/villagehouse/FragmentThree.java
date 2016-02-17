package com.example.villagehouse;



import com.example.threefragment.EntertainmentActivity;
import com.example.threefragment.FoodShopListView;
import com.example.threefragment.ShoppingActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * shoping inside �������С���ʳ���ճ���Ʒ
 * 
 * @author pxh
 */
public class FragmentThree extends Fragment {
	
	private LinearLayout yule,meishi,liangyou;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i("FragmentThree", "����FragmentThree");
		return inflater.inflate(R.layout.fragment_3, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
        initView();
	}

	
	private void initView(){
		 
	    yule=(LinearLayout)getView().findViewById(R.id.lin2);
	    meishi=(LinearLayout)getView().findViewById(R.id.lin5);
	    liangyou=(LinearLayout)getView().findViewById(R.id.lin8);
	    
	    MyOnclickListener mOnclickListener = new MyOnclickListener();
	    yule.setOnClickListener(mOnclickListener);//�������еĵ���¼�
	    meishi.setOnClickListener(mOnclickListener);//��ʳ�ĵ���¼�
	    liangyou.setOnClickListener(mOnclickListener);//���͵ĵ���¼�
	    
	    
	}//initView
	
	private class MyOnclickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int mID = v.getId();
			switch(mID){
			   case R.id.lin2:
				   Intent intent=new Intent(getActivity(), EntertainmentActivity.class);
				   startActivity(intent);
				   break;
			   case R.id.lin5:
				   Intent intent1=new Intent(getActivity(),FoodShopListView.class);
                   startActivity(intent1);
				   break;
			   case R.id.lin8:
				   Intent intent2=new Intent(getActivity(), ShoppingActivity.class);
				   startActivity(intent2);
				   break;
			   
			}
		}
	  
	}//onclick
}
