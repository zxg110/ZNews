package com.for_futrue.zxg.znews.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zxg on 2016/3/30.
 */
public class News{
    private int id;
    private String title;
    private String key;
    private String address;
    private String publishTime;
    private String sourceUrl;
    private String imageUrl;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public News(int id, String title, String key, String address, String publishTime, String
            sourceUrl, String imageUrl, String type) {
        this.id = id;
        this.title = title;
        this.key = key;
        this.address = address;
        this.publishTime = publishTime;
        this.sourceUrl = sourceUrl;
        this.imageUrl = imageUrl;
        this.type = type;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", key='" + key + '\'' +
                ", address='" + address + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
    public News(){

    }

}
