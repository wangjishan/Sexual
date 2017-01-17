package com.jtech.sexual.view.adapter;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jtech.adapter.RecyclerAdapter;
import com.jtech.listener.OnItemClickListener;
import com.jtech.sexual.R;
import com.jtech.sexual.model.PhotoModel;
import com.jtech.view.JRecyclerView;
import com.jtech.view.RecyclerHolder;
import com.jtechlib.Util.DeviceUtils;
import com.jtechlib.Util.ImageUtils;

import java.util.List;

/**
 * 图片适配器
 * Created by JTech on 2017/1/16.
 */

public class PhotoAdapter extends RecyclerAdapter<PhotoModel.Detail> {
    private int selectedPosition = -1;
    private int itemHeight, itemHeightBig, screenWidth;
    private OnPhotoListener onPhotoListener;

    public PhotoAdapter(Activity context) {
        super(context);
        //计算普通item尺寸和大号item尺寸
        int screenHeight = DeviceUtils.getScreenHeight(context);
        this.screenWidth = DeviceUtils.getScreenWidth(context);
        this.itemHeight = screenHeight / 4;
        this.itemHeightBig = screenHeight / 5 * 3;
    }

    public void setOnPhotoListener(OnPhotoListener onPhotoListener) {
        this.onPhotoListener = onPhotoListener;
    }

    /**
     * item选中
     *
     * @param position
     */
    public void itemSelected(int position) {
        if (-1 == selectedPosition) {
            //设置新状态
            selectedPosition = position;
            getItem(position).setSelected(true);
            notifyItemChanged(position);
        }
    }

    /**
     * 将选中的内容复原
     */
    public void itemRevert() {
        if (-1 != selectedPosition) {
            getItem(selectedPosition).setSelected(false);
            notifyItemChanged(selectedPosition);
            selectedPosition = -1;
        }
    }

    @Override
    protected View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        return layoutInflater.inflate(R.layout.view_photo, viewGroup, false);
    }

    @Override
    protected void convert(RecyclerHolder holder, final PhotoModel.Detail model, int position) {
        //判断状态，选中或者未选中状态
        if (model.isSelected()) {
            //设置固定大小
            LinearLayout linearLayout = holder.getView(R.id.photo_detail);
            ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
            layoutParams.height = itemHeightBig;
            linearLayout.setLayoutParams(layoutParams);
            //设置文本描述
            holder.setText(R.id.photo_title, model.getTitle());
            //设置图片集合
            JRecyclerView jRecyclerView = holder.getView(R.id.photo_list);
            jRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            PhotoDetailAdapter photoDetailAdapter = new PhotoDetailAdapter(getContext(), screenWidth / 2);
            jRecyclerView.setAdapter(photoDetailAdapter);
            photoDetailAdapter.setDatas(model.getList());
            //隐藏图片集合容器
            holder.showView(R.id.photo_detail);
            //隐藏卡片视图
            holder.hideViewGone(R.id.cardview);
            //设置关闭按钮的点击事件
            holder.setClickListener(R.id.close, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemRevert();
                }
            });
            //设置图片item点击事件
            jRecyclerView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(RecyclerHolder recyclerHolder, View view, int i) {
                    if (null != onPhotoListener) {
                        onPhotoListener.OnPhotoDetailClick(model.getList(), i);
                    }
                }
            });
        } else {
            //获取背景图片
            ImageView imageView = holder.getImageView(R.id.imageview_photo);
            //设置图片的固定大小(四分之一的屏幕高度)
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.height = itemHeight;
            imageView.setLayoutParams(layoutParams);
            //显示图片
            ImageUtils.showImage(getContext(), model.getList().get(0).getMiddle(), imageView, 0, 0);
            //隐藏图片集合容器
            holder.hideViewGone(R.id.photo_detail);
            //显示卡片视图
            holder.showView(R.id.cardview);
            //入股
        }
    }

    /**
     * 图片监听
     */
    public interface OnPhotoListener {
        void OnPhotoDetailClick(List<PhotoModel.Photo> photoList, int index);
    }
}