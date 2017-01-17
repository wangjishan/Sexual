package com.jtech.sexual.view.activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jtech.listener.OnItemClickListener;
import com.jtech.listener.OnLoadListener;
import com.jtech.sexual.R;
import com.jtech.sexual.cache.CategoryCache;
import com.jtech.sexual.model.CategoryModel;
import com.jtech.sexual.model.PhotoModel;
import com.jtech.sexual.mvp.contract.MainContract;
import com.jtech.sexual.mvp.presenter.MainPresenter;
import com.jtech.sexual.util.ActivityJump;
import com.jtech.sexual.view.adapter.CategoryAdapter;
import com.jtech.sexual.view.adapter.FooterAdapter;
import com.jtech.sexual.view.adapter.PhotoAdapter;
import com.jtech.sexual.view.widget.CategoryView;
import com.jtech.sexual.view.widget.LoadingView;
import com.jtech.sexual.view.widget.RxCompat;
import com.jtech.view.JRecyclerView;
import com.jtech.view.RecyclerHolder;
import com.jtechlib.view.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.functions.Action1;

/**
 * 首页
 * Created by JTech on 2017/1/16.
 */
public class MainActivity extends BaseActivity implements MainContract.View, OnLoadListener, OnItemClickListener, PhotoAdapter.OnPhotoListener, CategoryAdapter.OnCategoryListener {
    @Bind(R.id.jrecyclerview)
    JRecyclerView jRecyclerView;
    @Bind(R.id.fab)
    FloatingActionButton floatingActionButton;
    @Bind(R.id.content)
    CoordinatorLayout content;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout toolbar;
    @Bind(R.id.loadingview)
    LoadingView loadingView;
    @Bind(R.id.category_menu)
    CategoryView categoryView;

    private MainContract.Presenter presenter;
    private PhotoAdapter photoAdapter;

    @Override
    protected void initVariables(Bundle bundle) {
        this.presenter = new MainPresenter(getActivity(), this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        //设置标题
        CategoryModel.Detail subCategories = CategoryCache.get(getActivity()).getCategorySelected();
        if (null != subCategories) {
            toolbar.setTitle(subCategories.getName());
        }
        //设置fab的点击事件
        RxCompat.clickThrottleFirst(floatingActionButton, new FABClick());
        //设置加载更多
        jRecyclerView.setLoadMore(true);
        //设置layoutmanager
        jRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置适配器
        photoAdapter = new PhotoAdapter(getActivity());
        jRecyclerView.setAdapter(photoAdapter, new FooterAdapter(getActivity()));
        //设置监听
        jRecyclerView.setOnItemClickListener(this);
        jRecyclerView.setOnLoadListener(this);
        photoAdapter.setOnPhotoListener(this);
        categoryView.setOnCategoryListener(this);
    }

    @Override
    protected void loadData() {
        loadingView.show();
        presenter.getPhotoList(photoAdapter.getPage(false), false);
    }

    @Override
    public void onItemClick(RecyclerHolder recyclerHolder, View view, final int position) {
        //还原状态
        photoAdapter.itemRevert();
        //状态选择
        photoAdapter.itemSelected(position);
    }

    @Override
    public void OnPhotoDetailClick(List<PhotoModel.Photo> photoList, int index) {
        //跳转到大图浏览的页面
        Bundle bundle = new Bundle();
        bundle.putInt(GalleryActivity.KEY_PHOTO_SELECT, index);
        bundle.putParcelableArrayList(GalleryActivity.KEY_PHOTO, new ArrayList<Parcelable>(photoList));
        ActivityJump
                .build(getActivity(), GalleryActivity.class)
                .addBundle(bundle)
                .jump();
    }

    @Override
    public void onCategoryClick(CategoryModel.Detail category) {
        //隐藏分类选择
        categoryView.hideCategoryMenu();
        //存储已选择的结果
        presenter.putSelectCategory(category);
        //更新标题
        toolbar.setTitle(category.getName());
        //刷新列表
        loadData();
    }

    @Override
    public void loadMore() {
        presenter.getPhotoList(photoAdapter.getPage(true), true);
    }

    @Override
    public void showCategoryMenu(List<CategoryModel.SubCategories> categories) {
        categoryView.showCategoryMenu(categories);
        loadingView.hide();
    }

    @Override
    public void getCategoryFail(String error) {
        Snackbar.make(content, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void getPhotoSuccess(List<PhotoModel.Detail> photoList, boolean loadMore) {
        if (photoList.size() > 0) {
            int startPosition = photoAdapter.getItemCount();
            photoAdapter.setDatas(photoList, loadMore);
            photoAdapter.notifyItemRangeChanged(startPosition, photoAdapter.getItemCount());
            jRecyclerView.setLoadCompleteState();
        } else {
            jRecyclerView.setLoadFinishState();
        }
        loadingView.hide();
    }

    @Override
    public void getPhotoFail(String error) {
        loadingView.hide();
        jRecyclerView.setLoadFailState();
        Snackbar.make(content, error, Snackbar.LENGTH_SHORT).show();
    }

    /**
     * fab的点击事件
     */
    private class FABClick implements Action1<View> {
        @Override
        public void call(View view) {
            if (!categoryView.isShowing()) {
                presenter.getCategory();
            } else {
                categoryView.hideCategoryMenu();
            }
        }
    }
}