package com.for_futrue.zxg.znews.util;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.internal.$Gson$Types;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Description : okHttp Network connection utility class
 * Created by zxg on 2016/4/6.
 */
public class OkHttpUtils {
    private static final String TAG = "okHttpUtils";

    private static OkHttpUtils mInstance;
    private  OkHttpClient mOkHttpClient;
    private Handler mDelivery;

    private OkHttpUtils(){
        mOkHttpClient = new OkHttpClient();
        mOkHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient.setWriteTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient.setReadTimeout(20,TimeUnit.SECONDS);
        // cookie enabled
        mOkHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
        mDelivery = new Handler(Looper.getMainLooper());
    }

    private synchronized static OkHttpUtils getInstance(){
        if(mInstance == null){
           mInstance = new OkHttpUtils();
        }
        return mInstance;
    }
    private void getRequest(String url,final ResultCallback callBack){
        final Request request = new Request.Builder().url(url).build();
    }
    private void postRequest(String url,final ResultCallback callBack,List<Param> params){
        Request request = buildPostRequest(url, params);

    }
    private Request buildPostRequest(String url,List<Param> params){
        FormEncodingBuilder builder = new FormEncodingBuilder();
        for(Param param :params ){
            builder.addEncoded(param.key,param.value);
        }
        RequestBody requestBody = builder.build();
        return new Request.Builder().url(url).post(requestBody).build();
    }
    private void dealRequest(final ResultCallback callBack,Request request){
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                try{
                    String str = response.body().string();
                    if(callBack.mType == String.class){
                        sendSuccessCallBack(callBack,str);
                    }else{
                        Object object = JsonUtils.deserialize(str, callBack.mType);
                        sendSuccessCallBack(callBack, object);
                    }
                }catch (final Exception e){
                    sendFailCallback(callBack, e);
                }
            }
        });
    }
    private void sendSuccessCallBack(final ResultCallback callback, final Object obj) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onSuccess(obj);
                }
            }
        });
    }
    private void sendFailCallback(final ResultCallback callback, final Exception e) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onFailure(e);
                }
            }
        });
    }




    /*********************对外开放接口******************************/

    /**
     * get request
     * @param url
     * @param callback
     */
    public static void get(String url,ResultCallback callback){
        getInstance().getRequest(url,callback);
    }

    /**
     * post request
     * @param url
     * @param callback
     * @param params
     */
    public static void post(String url,ResultCallback callback,List<Param> params){
        getInstance().postRequest(url,callback,params);
    }
    /**
     * http请求回调类,回调方法在UI线程中执行
     * @param <T>
     */
    public static abstract class ResultCallback<T>{
        Type mType;
        public ResultCallback(){
            mType = getSuperclassTypeParameter(getClass());
            Log.i("zxg","getClass: "+getClass());
            //getClass:NewsModelImpl$1 在NewsModelImpl 创建了ResultCallback的匿名内部类，所以类名为1
        }
        static Type getSuperclassTypeParameter(Class<?> subclass){
            //返回本类的父类，包含泛型参数信息
            Type superclass = subclass.getGenericSuperclass();
            Log.i("zxg","type superclass: "+superclass);
            // superclass:OkHttpUtils$ResultCallback<java.lang.String>
            if (superclass instanceof Class) {
                throw new RuntimeException("Missing type parameter.");
            }
            ParameterizedType parameterized = (ParameterizedType) superclass;
            Log.i("zxg", "parameterized:" + parameterized);
            // parameterized:OkHttpUtils$ResultCallback<java.lang.String>
            //parameterized.getActualTypeArguments()[0] ：返回泛型的类型：java.lang.String
            return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
        }
        /**
         * 请求成功回调
         * @param response
         */
        public abstract void onSuccess(T response);

        /**
         * 请求失败回调
         * @param e
         */
        public abstract void onFailure(Exception e);

    }
    public static class Param{
        String key;
        String value;
        public Param(){

        }
        public Param(String key,String value){
            this.key = key;
            this.value = value;
        }
    }
}
