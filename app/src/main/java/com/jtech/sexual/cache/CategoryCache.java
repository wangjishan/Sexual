package com.jtech.sexual.cache;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.reflect.TypeToken;
import com.jtech.sexual.common.Constants;
import com.jtech.sexual.model.CategoryModel;
import com.jtechlib.cache.BaseCacheManager;

import java.util.List;

/**
 * 分类缓存管理
 * Created by JTech on 2017/1/16.
 */

public class CategoryCache extends BaseCacheManager {
    private static final int CATEGORY_SAVE_TIME = 1000 * 60 * 60 * 24 * 7;
    private static final String KEY_CATEGORY = "CATEGORY";
    private static final String KEY_CATEGORY_SELECTED = "CATEGORY_SELECTED";

    private static CategoryCache INSTANCE;
    private List<CategoryModel.SubCategories> subCategories;
    private CategoryModel.Detail categorySelect;

    public CategoryCache(Context context) {
        super(context);
    }

    public static CategoryCache get(Context context) {
        if (null == INSTANCE) {
            INSTANCE = new CategoryCache(context);
        }
        return INSTANCE;
    }

    @Override
    public String getCacheName() {
        return Constants.KEY_CACHE_NAME;
    }

    /**
     * 存储分类集合
     *
     * @param subCategories
     */
    public boolean putCategoryList(@NonNull List<CategoryModel.SubCategories> subCategories) {
        this.subCategories = subCategories;
        return put(KEY_CATEGORY, subCategories, CATEGORY_SAVE_TIME);
    }

    /**
     * 存储已选择的分类
     *
     * @param categorySelect
     * @return
     */
    public void putCategorySelected(CategoryModel.Detail categorySelect) {
        this.categorySelect = categorySelect;
        put(KEY_CATEGORY_SELECTED, categorySelect);
    }

    /**
     * 获取已选择的分类
     *
     * @return
     */
    public CategoryModel.Detail getCategorySelected() {
        if (null == categorySelect) {
            categorySelect = getSerializable(KEY_CATEGORY_SELECTED);
        }
        return categorySelect;
    }

    /**
     * 获取分类集合
     *
     * @return
     */
    public List<CategoryModel.SubCategories> getCategoryList() {
        if (null == subCategories) {
            subCategories = getList(KEY_CATEGORY, new TypeToken<List<CategoryModel.SubCategories>>() {
            }.getType());
        }
        return subCategories;
    }
}