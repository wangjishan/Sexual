package com.jtech.sexual.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jtech.adapter.RecyclerAdapter;
import com.jtech.sexual.R;
import com.jtech.sexual.model.PhotoModel;
import com.jtech.view.RecyclerHolder;
import com.jtechlib.Util.ImageUtils;

/**
 * 图片详情列表适配器
 * Created by JTech on 2017/1/17.
 */

public class PhotoDetailAdapter extends RecyclerAdapter<PhotoModel.Photo> {
    private int itemWidth;

    public PhotoDetailAdapter(Context context, int itemWidth) {
        super(context);
        this.itemWidth = itemWidth;
    }

    @Override
    protected View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        return layoutInflater.inflate(R.layout.view_photo_detail, viewGroup, false);
    }

    @Override
    protected void convert(RecyclerHolder holder, PhotoModel.Photo photo, int i) {
        //固定宽度
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.width = itemWidth;
        holder.itemView.setLayoutParams(layoutParams);
        //显示图片
        ImageUtils.showImage(getContext(), photo.getMiddle(), holder.getImageView(R.id.imageview_photo), 0, 0);
    }
}