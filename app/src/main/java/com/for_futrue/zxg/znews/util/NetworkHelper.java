package com.for_futrue.zxg.znews.util;

import android.net.Network;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

/**
 * Created by zxg on 2016/7/9.
 */
public class NetworkHelper {
    public final static String TAG = "NetworkHelper";

    private static NetworkHelper instatce;
    private Hashtable<Integer,NetworkAvailableCallback> callbackHashtable = new Hashtable<Integer,NetworkAvailableCallback>();

    public NetworkHelper(){

    }
    public interface NetworkAvailableCallback{
        void onNetworkAvailable();
    }
    public static NetworkHelper getInstatce(){
        if(instatce == null){
            synchronized (NetworkHelper.class){
                if(instatce == null){
                    instatce = new NetworkHelper();
                }
            }
        }
        return instatce;
    }

    public boolean containsCallBack(){
        return !callbackHashtable.isEmpty();
    }

    public void addCallBack(int hashCode,NetworkAvailableCallback availableCallback){
        if(availableCallback != null){
            callbackHashtable.put(hashCode,availableCallback);
        }
    }

    public void excuteCallBack(){
        Set<Integer> keySet = callbackHashtable.keySet();
        ArrayList<Integer> keyArrayList = new ArrayList<Integer>();
        NetworkAvailableCallback callBack;
        for(int hashCode:keySet){

        }
    }
}
