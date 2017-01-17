package com.jtech.sexual.cache;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.jtech.sexual.common.Constants;
import com.jtech.sexual.model.PhotoModel;
import com.jtechlib.cache.BaseCacheManager;

import java.util.List;

/**
 * 图片缓存管理
 * Created by JTech on 2017/1/17.
 */

public class PhotoCache extends BaseCacheManager {
    public static final int PHOTO_SIZE_BIG = 171;
    public static final int PHOTO_SIZE_MIDDLE = 335;
    public static final int PHOTO_SIZE_SMALL = 736;
    private static final int PHOTO_SAVE_TIME = 1000 * 60 * 60;
    private static final String KEY_PHOTO = "PHOTO";
    private static final String KEY_PHOTO_SIZE = "PHOTO_SIZE";
    private static PhotoCache INSTANCE;

    public PhotoCache(Context context) {
        super(context);
    }

    public static PhotoCache get(Context context) {
        if (null == INSTANCE) {
            INSTANCE = new PhotoCache(context);
        }
        return INSTANCE;
    }

    @Override
    public String getCacheName() {
        return Constants.KEY_CACHE_NAME;
    }

    /**
     * 存储图片集合
     *
     * @param photoList
     */
    public boolean putPhotoList(int type, List<PhotoModel.Detail> photoList) {
        return put(KEY_PHOTO + type, photoList, PHOTO_SAVE_TIME);
    }

    /**
     * 获取图片集合
     *
     * @return
     */
    public List<PhotoModel.Detail> getPhotoList(int type) {
        return getList(KEY_PHOTO + type, new TypeToken<List<PhotoModel.Detail>>() {
        }.getType());
    }

    /**
     * 存储图片尺寸
     *
     * @param photoSize
     */
    public void putPhotoSize(int photoSize) {
        put(KEY_PHOTO_SIZE, photoSize);
    }

    /**
     * 获取图片尺寸
     *
     * @return
     */
    public int getPhotoSize() {
        return getInt(KEY_PHOTO_SIZE, PHOTO_SIZE_MIDDLE);
    }
}