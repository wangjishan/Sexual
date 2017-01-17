package com.jtech.sexual.mvp.contract;

import com.jtech.sexual.model.CategoryModel;
import com.jtech.sexual.model.PhotoModel;
import com.jtechlib.contract.BaseContract;

import java.util.List;

/**
 * 首页协议
 * Created by JTech on 2017/1/16.
 */
public interface MainContract {
    interface Presenter extends BaseContract.Presenter {
        void getCategory();

        void getPhotoList(int pageIndex, boolean loadMore);

        void getPhotoList(int pageIndex, int type, boolean loadMore);

        void putSelectCategory(CategoryModel.Detail category);
    }

    interface View extends BaseContract.View {
        void showCategoryMenu(List<CategoryModel.SubCategories> categories);

        void getCategoryFail(String error);

        void getPhotoSuccess(List<PhotoModel.Detail> photoList, boolean loadMore);

        void getPhotoFail(String error);
    }
}