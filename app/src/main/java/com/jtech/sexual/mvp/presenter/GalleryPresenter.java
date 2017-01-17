package com.jtech.sexual.mvp.presenter;

import android.content.Context;

import com.jtech.sexual.cache.PhotoCache;
import com.jtech.sexual.model.PhotoModel;
import com.jtech.sexual.mvp.contract.GalleryContract;

import java.util.ArrayList;
import java.util.List;

/**
 * 大图浏览
 * Created by JTech on 2017/1/17.
 */
public class GalleryPresenter implements GalleryContract.Presenter {
    private Context context;
    private GalleryContract.View view;
    private List<PhotoModel.Photo> photoList;
    private int photoSelect;

    public GalleryPresenter(Context context, GalleryContract.View view, List<PhotoModel.Photo> photoList, int photoSelect) {
        this.photoSelect = photoSelect;
        this.photoList = photoList;
        this.context = context;
        this.view = view;
    }

    @Override
    public List<String> getPhotoUrls() {
        int photoSize = PhotoCache.get(context).getPhotoSize();
        List<String> imageUrls = new ArrayList<>();
        for (PhotoModel.Photo photo : photoList) {
            switch (photoSize) {
                case PhotoCache.PHOTO_SIZE_SMALL://小
                    imageUrls.add(photo.getSmall());
                    break;
                case PhotoCache.PHOTO_SIZE_MIDDLE://中
                    imageUrls.add(photo.getMiddle());
                    break;
                case PhotoCache.PHOTO_SIZE_BIG://大
                    imageUrls.add(photo.getBig());
                    break;
            }
        }
        return imageUrls;
    }

    @Override
    public void setPhotoSize(int photoSize) {
        PhotoCache.get(context).putPhotoSize(photoSize);
    }

    @Override
    public int getPhotoSelect() {
        return photoSelect;
    }
}