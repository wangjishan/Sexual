package com.jtech.sexual.net;

import com.jtech.sexual.model.CategoryModel;
import com.jtech.sexual.model.PhotoModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 接口
 * Created by JTech on 2017/1/16.
 */

public interface SexualApi {
    /**
     * 获取类别
     *
     * @param appid
     * @param sign
     * @param timetamp
     * @param signMethod
     * @param resGzip
     * @return
     */
    @GET("/852-1")
    Observable<CategoryModel> getCategory(
            @Query("showapi_appid") String appid,
            @Query("showapi_sign") String sign,
            @Query("showapi_timestamp") String timetamp,
            @Query("showapi_sign_method") String signMethod,
            @Query("showapi_res_gzip") String resGzip);

    /**
     * 获取列表
     *
     * @param appid
     * @param sign
     * @param timetamp
     * @param signMethod
     * @param resGzip
     * @param type
     * @param page
     * @return
     */
    @GET("/852-2")
    Observable<PhotoModel> getList(
            @Query("showapi_appid") String appid,
            @Query("showapi_sign") String sign,
            @Query("showapi_timestamp") String timetamp,
            @Query("showapi_sign_method") String signMethod,
            @Query("showapi_res_gzip") String resGzip,
            @Query("type") int type,
            @Query("page") int page);
}