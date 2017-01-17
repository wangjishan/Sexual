package com.jtech.sexual.mvp.contract;

import com.jtechlib.contract.BaseContract;

import java.util.List;

/**
 * 大图浏览
 * Created by JTech on 2017/1/17.
 */
public interface GalleryContract {
    interface Presenter extends BaseContract.Presenter {
        List<String> getPhotoUrls();

        void setPhotoSize(int photoSize);

        int getPhotoSelect();
    }

    interface View extends BaseContract.View {
    }
}