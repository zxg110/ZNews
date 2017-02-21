package com.for_futrue.zxg.znews.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by zxg on 2016/3/31.
 */
public class ZNewsApplication  extends Application{

    public static ZNewsApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(getApplicationContext());
        application = this;
    }

    public static Application getApplication(){
        if(application !=null){
            return  application;
        }
        return null;
    }
    /* init imageloader*/
    public static void initImageLoader(Context context){
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "imageloaderdemo/Cache");
        Log.i("zxg", "cacheDir:" + cacheDir);
        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(context);
        builder.threadPoolSize(3);
        builder.threadPriority(Thread.NORM_PRIORITY - 2);
        builder.denyCacheImageMultipleSizesInMemory();
        builder.discCacheFileNameGenerator(new Md5FileNameGenerator());
        builder.discCache(new UnlimitedDiscCache(cacheDir));
        builder.discCacheSize(10 * 1024 * 1024);
        builder.writeDebugLogs();
        builder.tasksProcessingOrder(QueueProcessingType.FIFO);
        ImageLoader.getInstance().init(builder.build());
    }
}
