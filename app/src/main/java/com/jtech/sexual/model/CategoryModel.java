package com.jtech.sexual.model;

import com.google.gson.annotations.SerializedName;
import com.jtechlib.model.BaseModel;

import java.util.List;

/**
 * 分类列表
 * Created by JTech on 2017/1/16.
 */

public class CategoryModel extends BaseModel {

    @SerializedName("showapi_res_code")
    private int showapiResCode;
    @SerializedName("showapi_res_error")
    private String showapiResError;
    @SerializedName("showapi_res_body")
    private Body showapiResBody;

    public int getShowapiResCode() {
        return showapiResCode;
    }

    public void setShowapiResCode(int showapiResCode) {
        this.showapiResCode = showapiResCode;
    }

    public String getShowapiResError() {
        return showapiResError;
    }

    public void setShowapiResError(String showapiResError) {
        this.showapiResError = showapiResError;
    }

    public Body getShowapiResBody() {
        return showapiResBody;
    }

    public void setShowapiResBody(Body showapiResBody) {
        this.showapiResBody = showapiResBody;
    }

    public static class Body extends BaseModel {
        /**
         * ret_code : 0
         * list : [{"name":"社会百态","list":[{"id":1001,"name":"社会新闻"},{"id":1002,"name":"国内新闻"},{"id":1003,"name":"环球动态"},{"id":1004,"name":"军事新闻"}]},{"name":"明星写真","list":[{"id":2001,"name":"中国明星"},{"id":2002,"name":"欧美明星"},{"id":2003,"name":"中国女明星"},{"id":2004,"name":"中国男明星"},{"id":2005,"name":"韩国明星"},{"id":2006,"name":"欧美女明星"},{"id":2007,"name":"欧美男明星"}]},{"name":"娱乐八卦","list":[{"id":3001,"name":"娱乐组图"},{"id":3002,"name":"八卦爆料"},{"id":3003,"name":"电影海报"},{"id":3004,"name":"影视剧照"},{"id":3005,"name":"活动现场"}]},{"name":"美女图片","list":[{"id":4001,"name":"清纯"},{"id":4002,"name":"气质"},{"id":4003,"name":"萌女"},{"id":4004,"name":"校花"},{"id":4005,"name":"婚纱"},{"id":4006,"name":"街拍"},{"id":4007,"name":"非主流"},{"id":4008,"name":"美腿"},{"id":4009,"name":"性感"},{"id":4010,"name":"车模"},{"id":4011,"name":"男色图片"},{"id":4012,"name":"模特美女"},{"id":4013,"name":"美女魅惑"},{"id":4014,"name":"日韩美女"}]},{"name":"时尚伊人","list":[{"id":5001,"name":"秀场"},{"id":5002,"name":"霓裳"},{"id":5003,"name":"鞋包"},{"id":5004,"name":"婚嫁"},{"id":5005,"name":"妆容"},{"id":5006,"name":"广告大片"}]},{"name":"生活趣味","list":[{"id":6001,"name":"居家"},{"id":6002,"name":"萌宠"},{"id":6003,"name":"美食图片"},{"id":6004,"name":"美丽风景"},{"id":6005,"name":"奇趣自然"},{"id":6006,"name":"植物花卉"}]},{"name":"猎奇图片","list":[{"id":7001,"name":"猎奇图片"},{"id":7002,"name":"世界溶洞奇观"},{"id":7003,"name":"3D博物馆"},{"id":7004,"name":"日本绳缚艺术"}]}]
         */

        @SerializedName("ret_code")
        private int retCode;
        @SerializedName("list")
        private List<SubCategories> list;

        public int getRetCode() {
            return retCode;
        }

        public void setRetCode(int retCode) {
            this.retCode = retCode;
        }

        public List<SubCategories> getList() {
            return list;
        }

        public void setList(List<SubCategories> list) {
            this.list = list;
        }
    }

    public static class SubCategories extends BaseModel {
        /**
         * name : 社会百态
         * list : [{"id":1001,"name":"社会新闻"},{"id":1002,"name":"国内新闻"},{"id":1003,"name":"环球动态"},{"id":1004,"name":"军事新闻"}]
         */

        @SerializedName("name")
        private String name;
        @SerializedName("list")
        private List<Detail> list;
        private boolean selected;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Detail> getList() {
            return list;
        }

        public void setList(List<Detail> list) {
            this.list = list;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }
    }

    public static class Detail extends BaseModel {
        /**
         * id : 1001
         * name : 社会新闻
         */

        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}