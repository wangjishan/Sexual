package com.jtech.sexual.view.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.jtech.sexual.R;
import com.jtech.sexual.model.PhotoModel;
import com.jtech.sexual.mvp.contract.GalleryContract;
import com.jtech.sexual.mvp.presenter.GalleryPresenter;
import com.jtech.sexual.view.adapter.GalleryAdapter;
import com.jtechlib.Util.ImageUtils;
import com.jtechlib.view.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 大图浏览
 * Created by JTech on 2017/1/17.
 */
public class GalleryActivity extends BaseActivity implements GalleryContract.View {
    public static final String KEY_PHOTO = "PHOTO";
    public static final String KEY_PHOTO_SELECT = "PHOTO_SELECT";

    @Bind(R.id.gallery)
    ViewPager viewPager;

    private GalleryContract.Presenter presenter;

    @Override
    protected void initVariables(Bundle bundle) {
        List<PhotoModel.Photo> photoList = bundle.getParcelableArrayList(KEY_PHOTO);
        int photoSelect = bundle.getInt(KEY_PHOTO_SELECT, 0);
        this.presenter = new GalleryPresenter(getActivity(), this, photoList, photoSelect);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_gallery);
    }

    @Override
    protected void loadData() {
        //设置适配器
        viewPager.setAdapter(new GalleryAdapter(getPhotos()));
        //设置默认选中
        viewPager.setCurrentItem(presenter.getPhotoSelect());
    }

    /**
     * 处理图片集合
     *
     * @return
     */
    public List<PhotoView> getPhotos() {
        List<PhotoView> photos = new ArrayList<>();
        for (String imageUrl : presenter.getPhotoUrls()) {
            PhotoView photoView = new PhotoView(getActivity());
            ImageUtils.showImage(getActivity(), imageUrl, photoView, 0, 0);
            photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    // TODO: 2017/1/17 可以在这里弹出个dialog，询问是否需要下载或者分享啥的
                }

                @Override
                public void onOutsidePhotoTap() {
                    onBackPressed();
                }
            });
            photos.add(photoView);
        }
        return photos;
    }
}