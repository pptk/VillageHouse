package com.example.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
/**
 * 
 * @author pxh
 * 自定义ViewPager.
 * setNoScroll方法用来传入参数、决定ViewPager屏不屏蔽滑动事件
 * myViewPager.setNoScroll(true);不滑动
 *
 */
public class MyViewPager extends ViewPager {
    private boolean noScroll = false;//默认的是false。允许滑动
 
    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }
 
    public MyViewPager(Context context) {
        super(context);
    }
    //set a boolean to change
    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }
 
    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }
 
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        /* return false;//super.onTouchEvent(arg0); */
    	//if false /return false/ allow scroll 
        if (noScroll)
            return false;
        else
            return super.onTouchEvent(arg0);
    }
 
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (noScroll)
            return false;
        else
            return super.onInterceptTouchEvent(arg0);
    }
 
    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }
 
    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }
 
}