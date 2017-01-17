package com.jtech.sexual.view.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import com.jtech.adapter.LoadFooterAdapter;
import com.jtech.sexual.R;
import com.jtech.view.RecyclerHolder;

/**
 * 加载更多的足部适配器
 * Created by JTech on 2017/1/17.
 */

public class FooterAdapter extends LoadFooterAdapter {
    private Context context;
    private int navigationBarHeight;

    public FooterAdapter(Activity activity) {
        this.context = activity;
        this.navigationBarHeight = getNavigationBarHeight(activity);
    }

    @Override
    public View getFooterView(LayoutInflater layoutInflater, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.load_footer, parent, false);
        //设置navigationBar的高度占位
        View placeHolde = view.findViewById(R.id.view_load_footer);
        ViewGroup.LayoutParams layoutParams = placeHolde.getLayoutParams();
        layoutParams.height = navigationBarHeight;
        placeHolde.setLayoutParams(layoutParams);
        return view;
    }

    @Override
    public void loadFailState(RecyclerHolder recyclerHolder) {
        //设置加载失败的文本
        recyclerHolder.setText(com.jtech.R.id.textview_load_footer, context.getResources().getString(com.jtech.R.string.load_more_state_fail));
        //隐藏progress
        recyclerHolder.hideViewInvisible(com.jtech.R.id.progressbar_load_footer);
    }

    @Override
    public void loadingState(RecyclerHolder recyclerHolder) {
        //清空文本
        recyclerHolder.setText(com.jtech.R.id.textview_load_footer, "");
        //显示porgress
        recyclerHolder.showView(com.jtech.R.id.progressbar_load_footer);
    }

    @Override
    public void noMoreDataState(RecyclerHolder recyclerHolder) {
        //设置无更多数据文本
        recyclerHolder.setText(com.jtech.R.id.textview_load_footer, context.getResources().getString(com.jtech.R.string.load_more_state_nomoredata));
        //隐藏progress
        recyclerHolder.hideViewInvisible(com.jtech.R.id.progressbar_load_footer);
    }

    @Override
    public void normalState(RecyclerHolder recyclerHolder) {
        //设置常态文本
        recyclerHolder.setText(com.jtech.R.id.textview_load_footer, "");
        //隐藏progress
        recyclerHolder.hideViewInvisible(com.jtech.R.id.progressbar_load_footer);
    }

    /**
     * 获取底部栏的高度
     *
     * @param activity
     * @return
     */
    private int getNavigationBarHeight(Activity activity) {
        if (checkDeviceHasNavigationBar(activity)) {
            Resources resources = activity.getResources();
            int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    /**
     * 检查是否存在navigationbar
     *
     * @param activity
     * @return
     */
    @SuppressLint("NewApi")
    public boolean checkDeviceHasNavigationBar(Activity activity) {
        //通过判断设备是否有返回键、菜单键(不是虚拟键,是手机屏幕外的按键)来确定是否有navigation bar
        boolean hasMenuKey = ViewConfiguration.get(activity)
                .hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap
                .deviceHasKey(KeyEvent.KEYCODE_BACK);
        if (!hasMenuKey && !hasBackKey) {
            // 做任何你需要做的,这个设备有一个导航栏
            return true;
        }
        return false;
    }
}