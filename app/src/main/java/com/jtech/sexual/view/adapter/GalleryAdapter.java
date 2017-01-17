package com.jtech.sexual.view.adapter;

import com.jtechlib.view.adapter.BasePagerAdapter;

import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 * 大图浏览的适配器
 * Created by JTech on 2017/1/17.
 */

public class GalleryAdapter extends BasePagerAdapter<PhotoView> {
    public GalleryAdapter(List<PhotoView> views) {
        super(views);
    }
}