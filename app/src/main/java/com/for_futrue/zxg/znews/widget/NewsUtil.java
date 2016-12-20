package com.for_futrue.zxg.znews.widget;

import android.util.Log;

import com.for_futrue.zxg.znews.bean.News;
import com.for_futrue.zxg.znews.util.JsonUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lidroid.xutils.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxg on 2016/12/17.
 */
public class NewsUtil {
    private final static String TAG = "NewsJsonUtils";

    /**
     * 将获取到的json转换为新闻列表对象
     * @param res
     * @param value
     * @return
     */
    public static List<News> readJsonNewsBeans(String res, String value) {
        List<News> beans = new ArrayList<News>();
        try {
            JsonParser parser = new JsonParser();
            JsonObject jsonObj = parser.parse(res).getAsJsonObject();
            JsonElement jsonElement = jsonObj.get(value);
            if(jsonElement == null) {
                return null;
            }
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (int i = 1; i < jsonArray.size(); i++) {
                Log.i("NewsUtil","Jsonsize"+jsonArray.size());
                JsonObject jo = jsonArray.get(i).getAsJsonObject();
//                if (jo.has("skipType") && "special".equals(jo.get("skipType").getAsString())) {
//                    continue;
//                }
//                if (jo.has("TAGS") && !jo.has("TAG")) {
//                    continue;
//                }

                if (!jo.has("imgextra")) {
                    News news = JsonUtils.deserialize(jo, News.class);
                    beans.add(news);
                }
            }
        } catch (Exception e) {
            Log.i(TAG,"exception"+e);
        }
        return beans;
    }

//    public static NewsDetailBean readJsonNewsDetailBeans(String res, String docId) {
//        NewsDetailBean newsDetailBean = null;
//        try {
//            JsonParser parser = new JsonParser();
//            JsonObject jsonObj = parser.parse(res).getAsJsonObject();
//            JsonElement jsonElement = jsonObj.get(docId);
//            if(jsonElement == null) {
//                return null;
//            }
//            newsDetailBean = JsonUtils.deserialize(jsonElement.getAsJsonObject(), NewsDetailBean.class);
//        } catch (Exception e) {
//            LogUtils.e(TAG, "readJsonNewsBeans error" , e);
//        }
//        return newsDetailBean;
//    }
}
