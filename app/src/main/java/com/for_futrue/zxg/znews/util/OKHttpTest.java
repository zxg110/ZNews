package com.for_futrue.zxg.znews.util;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.Callback;

import java.io.IOException;

/**
 * Created by zxg on 2016/10/5.
 */
public class OKHttpTest {
    private final static OkHttpClient client = new OkHttpClient();
    public static void main(String[] args) throws Exception {
        enqueue();

        //OkHttpUtils.get("http://3g.163.com/touch/article/list/BAI67OGGwangning/0-10.html", resultCallback);
    }
    private static void enqueue(){
        Request request = new Request.Builder()
                .url("http://c.m.163.com/nc/article/list/T1397016069906/0-20.html")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                //NOT UI Thread
                if(response.isSuccessful()){
                    System.out.println(response.toString());
                    System.out.println(response.body().string());
                }
            }
        });
    }
}
