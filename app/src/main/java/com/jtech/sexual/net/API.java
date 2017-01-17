package com.jtech.sexual.net;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.jtech.sexual.common.Constants;
import com.jtechlib.net.BaseApi;

import java.util.Date;

import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 接口
 * Created by JTech on 2017/1/16.
 */

public class API extends BaseApi {
    public static final int REQUEST_SUCCESS = 0;
    private SexualApi sexualApi;
    private static API INSTANCE;

    public static API get() {
        if (null == INSTANCE) {
            INSTANCE = new API();
        }
        return INSTANCE;
    }

    /**
     * 获取接口对象
     *
     * @return
     */
    public SexualApi sexual() {
        if (null == sexualApi) {
            //实例化gson
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Date.class, new DateTypeAdapter())
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();
            //创建retrofit
            sexualApi = createRxApi(GsonConverterFactory.create(gson), Constants.BASE_URL, SexualApi.class);
        }
        return sexualApi;
    }
}