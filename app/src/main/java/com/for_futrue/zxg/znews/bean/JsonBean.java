package com.for_futrue.zxg.znews.bean;

import java.util.List;
import java.util.Map;

/**
 * Created by zxg on 2016/10/4.
 */
public class JsonBean {

    public Map<String,List<News>> artiList;

    public Map<String, List<News>> getArtiList() {
        return artiList;
    }

    public void setArtiList(Map<String, List<News>> artiList) {
        this.artiList = artiList;
    }

    @Override
    public String toString() {
        return "JsonBean{" +
                "artiList=" + artiList +
                '}';
    }
}
