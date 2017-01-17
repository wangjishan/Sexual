package com.jtech.sexual.mvp.presenter;

import android.content.Context;

import com.jtech.sexual.cache.CategoryCache;
import com.jtech.sexual.cache.PhotoCache;
import com.jtech.sexual.common.Constants;
import com.jtech.sexual.model.CategoryModel;
import com.jtech.sexual.model.PhotoModel;
import com.jtech.sexual.mvp.contract.MainContract;
import com.jtech.sexual.net.API;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 首页P类
 * Created by JTech on 2017/1/16.
 */
public class MainPresenter implements MainContract.Presenter {
    private Context context;
    private MainContract.View view;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    public MainPresenter(Context context, MainContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getCategory() {
        //如果本地存在分类集合的缓存，则直接返回
        List<CategoryModel.SubCategories> categories = CategoryCache.get(context).getCategoryList();
        if (null != categories && categories.size() > 0) {
            view.showCategoryMenu(categories);
            return;
        }
        //请求类别列表
        API.get()
                .sexual()
                .getCategory(Constants.APP_ID, Constants.APP_SECRET, getTimeTamp(), Constants.APP_SIGN_METHOD, Constants.APP_RES_GZIP)
                .subscribeOn(Schedulers.io())
                .map(new Func1<CategoryModel, CategoryModel>() {
                    @Override
                    public CategoryModel call(CategoryModel categoryModel) {
                        //如果请求成功，则缓存分类集合
                        if (categoryModel.getShowapiResCode() == API.REQUEST_SUCCESS) {
                            CategoryCache
                                    .get(context)
                                    .putCategoryList(categoryModel.getShowapiResBody().getList());
                        }
                        return categoryModel;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CategoryModel>() {
                    @Override
                    public void call(CategoryModel categoryModel) {
                        //成功则显示分类菜单
                        if (categoryModel.getShowapiResCode() == API.REQUEST_SUCCESS) {
                            view.showCategoryMenu(categoryModel.getShowapiResBody().getList());
                            return;
                        }
                        view.getCategoryFail(categoryModel.getShowapiResError());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.getCategoryFail(throwable.getMessage());
                    }
                });
    }

    @Override
    public void getPhotoList(int pageIndex, boolean loadMore) {
        //如果不存在已选择的分类, 则开始请求分类
        CategoryModel.Detail subCategories = CategoryCache.get(context).getCategorySelected();
        if (null == subCategories) {
            getCategory();
            return;
        }
        //获取图片列表
        getPhotoList(pageIndex, subCategories.getId(), loadMore);
    }

    @Override
    public void getPhotoList(int pageIndex, final int type, final boolean loadMore) {
        //下拉刷新的时候执行
        if (!loadMore) {
            //查找本地是否存在缓存
            List<PhotoModel.Detail> photoList = PhotoCache.get(context).getPhotoList(type);
            if (null != photoList && photoList.size() > 0) {
                view.getPhotoSuccess(photoList, loadMore);
                return;
            }
        }
        //请求图片列表
        API.get()
                .sexual()
                .getList(Constants.APP_ID, Constants.APP_SECRET, getTimeTamp(), Constants.APP_SIGN_METHOD, Constants.APP_RES_GZIP, type, pageIndex)
                .subscribeOn(Schedulers.io())
                .map(new Func1<PhotoModel, PhotoModel>() {
                    @Override
                    public PhotoModel call(PhotoModel photoModel) {
                        if (!loadMore && photoModel.getShowapiResCode() == API.REQUEST_SUCCESS) {
                            PhotoCache.get(context).putPhotoList(type, photoModel.getShowapiResBody().getPagebean().getContentlist());
                        }
                        return photoModel;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PhotoModel>() {
                    @Override
                    public void call(PhotoModel photoModel) {
                        if (photoModel.getShowapiResCode() == API.REQUEST_SUCCESS) {
                            view.getPhotoSuccess(photoModel.getShowapiResBody().getPagebean().getContentlist(), loadMore);
                            return;
                        }
                        view.getPhotoFail(photoModel.getShowapiResError());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.getPhotoFail(throwable.getMessage());
                    }
                });
    }

    @Override
    public void putSelectCategory(CategoryModel.Detail category) {
        CategoryCache.get(context).putCategorySelected(category);
    }

    /**
     * 获取时间戳
     *
     * @return
     */
    private String getTimeTamp() {
        return simpleDateFormat.format(new Date());
    }
}