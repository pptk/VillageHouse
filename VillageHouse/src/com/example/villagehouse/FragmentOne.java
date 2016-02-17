package com.example.villagehouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.oneactivity.Appointment;
import com.example.oneactivity.Monitor;
import com.example.oneactivity.NoticeSecondActivity;
import com.example.view.MyViewPagerByNotice;

/**
 * 医疗：紧急求救、上门问诊、预约挂号。 安保：远程监控、一键报警、智能锁。
 * 
 * @author pxh
 */
public class FragmentOne extends Fragment implements View.OnClickListener {
	private MyViewPagerByNotice noticeFramlayout;
	private View contentView;
	private LinearLayout appointmentLinearLayout, doorLinearLayout,
			monitorLinearLayout, lockLinearLayout;// 预约挂号、上门问诊、远程监控、智能锁
	private ImageView TelphoneImageView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		contentView = inflater.inflate(R.layout.fragment_1, container, false);
		return contentView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		initView();
		startNotice();// 开始轮播
		super.onActivityCreated(savedInstanceState);
	}

	// 开始轮播
	public void startNotice() {
		noticeFramlayout.StartCurrent();
	}

	// 停止轮播
	public void stopNotice() {
		noticeFramlayout.StopCurrent();
	}

	private void initView() {
		noticeFramlayout = (MyViewPagerByNotice) contentView
				.findViewById(R.id.notice);
		appointmentLinearLayout = (LinearLayout) contentView
				.findViewById(R.id.appointment);
		doorLinearLayout = (LinearLayout) contentView.findViewById(R.id.door);
		monitorLinearLayout = (LinearLayout) contentView
				.findViewById(R.id.monitor);
		lockLinearLayout = (LinearLayout) contentView.findViewById(R.id.lock);
		TelphoneImageView = (ImageView) contentView.findViewById(R.id.Telphone);

		appointmentLinearLayout.setOnClickListener(this);
		doorLinearLayout.setOnClickListener(this);
		monitorLinearLayout.setOnClickListener(this);
		lockLinearLayout.setOnClickListener(this);
		TelphoneImageView.setOnClickListener(this);

	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		// 预约挂号
		case R.id.appointment:
			Intent intent = new Intent(getActivity(), Appointment.class);
			startActivity(intent);
			break;
		// 上门服务
		case R.id.door:

			break;
		// 监控
		case R.id.monitor:
			Intent intent1 = new Intent(getActivity(), Monitor.class);
			startActivity(intent1);
			break;
		// 智能锁
		case R.id.lock:

			break;
		// 紧急电话
		case R.id.Telphone:
			break;
		}
	}
}
