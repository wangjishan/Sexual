package com.jtech.sexual.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.jtechlib.model.BaseModel;

import java.util.List;

/**
 * 列表图片对象
 * Created by JTech on 2017/1/16.
 */

public class PhotoModel extends BaseModel {

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

        @SerializedName("ret_code")
        private int retCode;
        @SerializedName("pagebean")
        private Page pagebean;

        public int getRetCode() {
            return retCode;
        }

        public void setRetCode(int retCode) {
            this.retCode = retCode;
        }

        public Page getPagebean() {
            return pagebean;
        }

        public void setPagebean(Page pagebean) {
            this.pagebean = pagebean;
        }
    }

    public static class Page extends BaseModel {

        @SerializedName("allPages")
        private int allPages;
        @SerializedName("currentPage")
        private int currentPage;
        @SerializedName("allNum")
        private int allNum;
        @SerializedName("maxResult")
        private int maxResult;
        @SerializedName("contentlist")
        private List<Detail> contentlist;

        public int getAllPages() {
            return allPages;
        }

        public void setAllPages(int allPages) {
            this.allPages = allPages;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getAllNum() {
            return allNum;
        }

        public void setAllNum(int allNum) {
            this.allNum = allNum;
        }

        public int getMaxResult() {
            return maxResult;
        }

        public void setMaxResult(int maxResult) {
            this.maxResult = maxResult;
        }

        public List<Detail> getContentlist() {
            return contentlist;
        }

        public void setContentlist(List<Detail> contentlist) {
            this.contentlist = contentlist;
        }
    }

    public static class Detail extends BaseModel {
        @SerializedName("typeName")
        private String typeName;
        @SerializedName("title")
        private String title;
        @SerializedName("type")
        private int type;
        @SerializedName("itemId")
        private String itemId;
        @SerializedName("ct")
        private String ct;
        @SerializedName("list")
        private List<Photo> list;
        private boolean selected;

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public String getCt() {
            return ct;
        }

        public void setCt(String ct) {
            this.ct = ct;
        }

        public List<Photo> getList() {
            return list;
        }

        public void setList(List<Photo> list) {
            this.list = list;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }
    }

    public static class Photo extends BaseModel implements Parcelable {
        @SerializedName("big")
        private String big;
        @SerializedName("small")
        private String small;
        @SerializedName("middle")
        private String middle;

        public String getBig() {
            return big;
        }

        public void setBig(String big) {
            this.big = big;
        }

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getMiddle() {
            return middle;
        }

        public void setMiddle(String middle) {
            this.middle = middle;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(big);
            parcel.writeString(small);
            parcel.writeString(middle);
        }

        public static final Parcelable.Creator<Photo> CREATOR = new Creator<Photo>() {
            @Override
            public Photo[] newArray(int size) {
                return new Photo[size];
            }

            @Override
            public Photo createFromParcel(Parcel in) {
                return new Photo(in);
            }
        };

        public Photo(Parcel in) {
            big = in.readString();
            small = in.readString();
            middle = in.readString();
        }
    }
}