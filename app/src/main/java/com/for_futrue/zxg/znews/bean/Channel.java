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
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
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

    public Channel(Integer id, String name, Integer orderId, Integer selected, String desc) {
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
