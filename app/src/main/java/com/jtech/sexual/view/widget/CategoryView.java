package com.jtech.sexual.view.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.jtech.listener.OnItemClickListener;
import com.jtech.sexual.model.CategoryModel;
import com.jtech.sexual.view.adapter.CategoryAdapter;
import com.jtech.view.JRecyclerView;
import com.jtech.view.RecyclerHolder;

import java.util.List;

/**
 * 分类选择菜单
 * Created by JTech on 2017/1/17.
 */

public class CategoryView extends LinearLayout implements View.OnTouchListener {
    private static final int ANIMATION_DURATION = 350;

    private CategoryAdapter categoryAdapter;

    public CategoryView(Context context) {
        this(context, null);
    }

    public CategoryView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CategoryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化视图
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        //设置方向为横向布局
        setOrientation(LinearLayout.HORIZONTAL);
        //设置背景颜色（半透明黑色）
        setBackgroundColor(0xe0000000);
        //设置触摸监听
        setOnTouchListener(this);
        //具右
        setGravity(Gravity.CENTER_HORIZONTAL);
        //添加一级分类列表
        JRecyclerView categoryList = new JRecyclerView(getContext());
        categoryList.setLayoutManager(new LinearLayoutManager(getContext()));
        categoryAdapter = new CategoryAdapter(getContext());
        categoryList.setAdapter(categoryAdapter);
        addView(categoryList);
        categoryList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerHolder recyclerHolder, View view, int i) {
                //状态选择
                categoryAdapter.itemSelected(i);
            }
        });
    }

    /**
     * 判断是否显示
     *
     * @return
     */
    public boolean isShowing() {
        return getVisibility() == VISIBLE;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        hideCategoryMenu();
        return true;
    }

    /**
     * 设置分类监听
     *
     * @param onCategoryListener
     */
    public void setOnCategoryListener(CategoryAdapter.OnCategoryListener onCategoryListener) {
        categoryAdapter.setOnCategoryListener(onCategoryListener);
    }

    /**
     * 显示分类菜单
     *
     * @param categories
     */
    public void showCategoryMenu(final List<CategoryModel.SubCategories> categories) {
        ValueAnimator valueAnimator = ValueAnimator
                .ofFloat(0.3f, 1.0f)
                .setDuration(ANIMATION_DURATION);
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                //设置一级菜单数据
                categoryAdapter.setDatas(categories);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                setAlpha((float) valueAnimator.getAnimatedValue());
            }
        });
        valueAnimator.start();
    }

    /**
     * 隐藏分类菜单
     */
    public void hideCategoryMenu() {
        ValueAnimator valueAnimator = ValueAnimator
                .ofFloat(1.0f, 0.3f)
                .setDuration(ANIMATION_DURATION);
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                setVisibility(GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                setAlpha((float) valueAnimator.getAnimatedValue());
            }
        });
        valueAnimator.start();
    }
}