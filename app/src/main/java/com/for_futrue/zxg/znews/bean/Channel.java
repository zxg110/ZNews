package com.for_futrue.zxg.znews.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by zxg on 2016/3/21.
 */
@DatabaseTable()
public class Channel implements Serializable{

    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField(columnName = "name")
    private String name;
    @DatabaseField(columnName = "orderId")
    private Integer orderId;
    @DatabaseField(columnName = "selected")
    private Integer selected;
    @DatabaseField(columnName = "desc")
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
