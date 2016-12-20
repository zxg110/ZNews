package com.for_futrue.zxg.znews.bean;

import java.io.Serializable;

/**
 * Created by zxg on 2016/3/21.
 */
public class Channel implements Serializable{

    public static final int CHANNEL_RECOMMEND = 0;
    public static final int CHANNEL_HEADLINE = 1;
    public static final int CHANNEL_AMUSEMENT = 2;
    public static final int CHANNEL_SPORTS = 3;
    public static final int CHANNEL_ECONOMICS = 4;
    public static final int CHANNEL_MILITARY = 6;
    public static final int CHANNEL_PHONE = 7;
    public static final int CHANNEL_TECHNOLOGY = 8;
    public static final int CHANNEL_GAME = 9;
    public static final int CHANNEL_DITIGAL = 10;
    public static final int CHANNEL_EDUCATION = 11;
    public static final int CHANNEL_JOKE = 12;
    public static final int CHANNEL_CAR = 13;
    public static final int CHANNEL_NBA= 14;
    public static final int CHANNEL_TRAVEL = 16;
    public static final int CHANNEL_MOM = 17;


    private Integer id;
    private String name;
    private Integer orderId;
    private Integer selected;
    private int desc;

    public int getDesc() {
        return desc;
    }

    public void setDesc(int desc) {
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Channel(Integer id, String name, Integer orderId, Integer selected, int desc) {
        this.id = id;
        this.name = name;
        this.orderId = orderId;
        this.selected = selected;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", orderId=" + orderId +
                ", selected=" + selected +
                ", desc='" + desc + '\'' +
                '}';
    }
}
