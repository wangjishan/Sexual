package com.jtech.sexual.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jtech.adapter.RecyclerAdapter;
import com.jtech.sexual.R;
import com.jtech.sexual.model.CategoryModel;
import com.jtech.view.RecyclerHolder;

/**
 * 二级分类列表适配器
 * Created by JTech on 2017/1/17.
 */

public class SubCategoryAdapter extends RecyclerAdapter<CategoryModel.Detail> {
    public SubCategoryAdapter(Context context) {
        super(context);
    }

    @Override
    protected View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        return layoutInflater.inflate(R.layout.view_subcategory_card, viewGroup, false);
    }

    @Override
    protected void convert(RecyclerHolder holder, CategoryModel.Detail model, int i) {
        //设置分类名称
        holder.setText(R.id.textview_category, model.getName());
    }
}