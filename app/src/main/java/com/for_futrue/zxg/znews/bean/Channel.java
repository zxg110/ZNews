package com.for_futrue.zxg.znews.bean;

import java.io.Serializable;

/**
 * Created by zxg on 2016/3/21.
 */
public class Channel implements Serializable{
    private Integer id;
    private String name;
    private Integer orderId;
    private Integer selected;

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

    public Channel(Integer id, Integer selected, Integer orderId, String name) {
        this.id = id;
        this.selected = selected;
        this.orderId = orderId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "selected=" + selected +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", orderId=" + orderId +
                '}';
    }
}
