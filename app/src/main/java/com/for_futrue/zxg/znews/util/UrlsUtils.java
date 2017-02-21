package com.for_futrue.zxg.znews.util;

import android.util.Log;

import com.for_futrue.zxg.znews.bean.Channel;
import com.for_futrue.zxg.znews.presenter.MainPresenter;

/**
 * Created by zxg on 2016/10/3.
 */
public class UrlsUtils {

    private static final String TAG = "UrlsUtil";
    private static int PAGE_SIZE = 20;

    private static final String HOST = "http://c.m.163.com/";
    private static final String END_URL = "/0-"+ PAGE_SIZE +".html";
    private static final String COMMON_URL = "nc/article/list/";

    private static final String DETAIL_URL = HOST +"nc/article/";

    private static final String END_DETAIL_URL = "/full.html";
    //推荐
    private static final String RECOMMEND_ID = "T1370583240249";
    //头条
    private static final String HEADLINE_ID = "T1348647909107";
    //娱乐
    private static final String AMUSEMENT_ID = "T1348648517839";
    //笑话
    private static final String JOKE_ID = "T1350383429665";
    //体育
    private static final String SPORTS_ID = "T1348649079062";
    //经济
    private static final String ECONOMICS_ID = "T1348648756099";
    //军事
    private static final String MILITARY_ID = "T1348648141035";
    //手机
    private static final String PHONE_ID = "T1348649654285";
    // nba
    public static final String NBA_ID = "T1348649145984";
    //科技
    private static final String TECHNOLOGY_ID = "T1348649580692";
    //游戏
    private static final String GAME_ID = "T1397016069906";
    //数码
    private static final String DITIGAL_ID = "T1348649776727";
    //教育
    private static final String EDUCATION_ID = "T1348654225495";
    //汽车
    private static final String CAR_ID = "T1348654060988";
    //旅游
    private static final String TRAVEL_ID = "T1348654204705";
    //亲子
    private static final String MOM_ID = "T1397116135282";
    private static final String UNDEFINED = "undefined";

    public static String getIDByChannel(int channelDesc){
        switch (channelDesc){
            case MainPresenter.CHANNEL_AMUSEMENT:
                return AMUSEMENT_ID;
            case MainPresenter.CHANNEL_CAR:
                return CAR_ID;
            case MainPresenter.CHANNEL_DITIGAL:
                return DITIGAL_ID;
            case MainPresenter.CHANNEL_ECONOMICS:
                return ECONOMICS_ID;
            case MainPresenter.CHANNEL_EDUCATION:
                return EDUCATION_ID;
            case MainPresenter.CHANNEL_JOKE:
                return JOKE_ID;
            case MainPresenter.CHANNEL_GAME:
                return GAME_ID;
            case MainPresenter.CHANNEL_HEADLINE:
                return HEADLINE_ID;
            case MainPresenter.CHANNEL_MILITARY:
                return MILITARY_ID;
            case MainPresenter.CHANNEL_PHONE:
                return PHONE_ID;
            case MainPresenter.CHANNEL_MOM:
                return MOM_ID;
            case MainPresenter.CHANNEL_SPORTS:
                return SPORTS_ID;
            case MainPresenter.CHANNEL_RECOMMEND:
                return RECOMMEND_ID;
            case MainPresenter.CHANNEL_TRAVEL:
                return TRAVEL_ID;
            case MainPresenter.CHANNEL_TECHNOLOGY:
                return TECHNOLOGY_ID;
            case MainPresenter.CHANNEL_NBA:
                return NBA_ID;
            default:
                return UNDEFINED;
        }
    }
    private static String getEndUrl(int index){
        int from = index*PAGE_SIZE;
        int to = from+PAGE_SIZE;
        if(index == 0){
            return "/"+from+"-"+ to +".html";
        }
        return "/"+to+"-"+ from +".html";
    }
    /*******************对外开放***********************/
    public static int getPageSize() {
        return PAGE_SIZE;
    }

    public static void setPageSize(int pageSize) {
        PAGE_SIZE = pageSize;
    }

    public static String getUrlByChannel(int channelDesc) {
        String channel_id = getIDByChannel(channelDesc);
        if (channel_id == UNDEFINED) {
            return null;
        }
        Log.i(TAG, "getUrlByChannel:" + HOST + COMMON_URL + channel_id + END_URL);
        return HOST + COMMON_URL + channel_id + END_URL;
    }

    public static String getUrlByChannel(int channelDesc,int pageIndex){
        String channel_id = getIDByChannel(channelDesc);

        return HOST + COMMON_URL + channel_id+ getEndUrl(pageIndex);

    }
    public static String getDetailUrl(String docId){
        return DETAIL_URL + docId+END_DETAIL_URL;
    }
    public static void main(String[] args){
        System.out.print("test:"+getUrlByChannel(1,2));
    }
}
