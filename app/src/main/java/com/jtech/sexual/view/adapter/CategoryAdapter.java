package com.jtech.sexual.view.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jtech.adapter.RecyclerAdapter;
import com.jtech.listener.OnItemClickListener;
import com.jtech.sexual.R;
import com.jtech.sexual.model.CategoryModel;
import com.jtech.view.JRecyclerView;
import com.jtech.view.RecyclerHolder;

/**
 * 以及分类列表适配器
 * Created by JTech on 2017/1/17.
 */

public class CategoryAdapter extends RecyclerAdapter<CategoryModel.SubCategories> {
    private OnCategoryListener onCategoryListener;

    public CategoryAdapter(Context context) {
        super(context);
    }

    public void setOnCategoryListener(OnCategoryListener onCategoryListener) {
        this.onCategoryListener = onCategoryListener;
    }

    /**
     * 设置item选中
     *
     * @param position
     */
    public void itemSelected(int position) {
        for (int i = 0; i < getItemCount(); i++) {
            getItem(i).setSelected(position == i ? (getItem(i).isSelected() ? false : true) : false);
        }
        notifyItemRangeChanged(0, getItemCount());
    }

    @Override
    protected View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        return layoutInflater.inflate(R.layout.view_category_card, viewGroup, false);
    }

    @Override
    protected void convert(RecyclerHolder holder, final CategoryModel.SubCategories model, int position) {
        //设置分类名称
        holder.setText(R.id.textview_category, model.getName());
        //显示或隐藏列表
        holder.setViewVisible(R.id.subcategory_list, model.isSelected());
        //如果选中则展开二级列表
        if (model.isSelected()) {
            JRecyclerView jRecyclerView = holder.getView(R.id.subcategory_list);
            jRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            final SubCategoryAdapter subCategoryAdapter = new SubCategoryAdapter(getContext());
            jRecyclerView.setAdapter(subCategoryAdapter);
            subCategoryAdapter.setDatas(model.getList());
            jRecyclerView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(RecyclerHolder recyclerHolder, View view, int i) {
                    if (null != onCategoryListener) {
                        onCategoryListener.onCategoryClick(subCategoryAdapter.getItem(i));
                    }
                }
            });
        }
    }

    /**
     * 分类列表点击事件
     */
    public interface OnCategoryListener {
        void onCategoryClick(CategoryModel.Detail category);
    }
}