package com.for_futrue.zxg.znews.util;

import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import android.text.format.DateFormat;

/**
 * Created by zxg on 2016/7/9.
 */
public class LogHelper {
    //是否打印log
    public static final boolean DEBUG = true;
    //push服务Log标识，只做记录
    public static final String TAG = "ZNews";
    //把log写入文件
    public static final boolean WRITE_LOG_FILE = false;
    //字符串格式format
    private static final String LOG_FORMAT = "%1$s\n%2$s";
    //log文件地址
    private static final String LOG_FILE_PATH = Environment.getExternalStorageDirectory()+
            "/Android/data/com.for_future.zxg.znews/znews.log";
    //log文件大小上限，超过后，会先删除此文件，再创建
    private static final long LOG_FILE_MAX_SIZE = 10L * 1024 * 1024;
    private static LogHelper mLogHelper = null;
    //后台处理的工作线程
    private WorkHandler mWorkHandler;

    private LogHelper(){

    }
    public static boolean isLogEnable(){
        return DEBUG;
    }
    public static boolean isWriteLogToFileEnable(){
        return WRITE_LOG_FILE;
    }

    /**
     * 单例对象
     */
    public static LogHelper getInstance(){
        if(mLogHelper == null){
            synchronized (LogHelper.class){
                if(mLogHelper == null)
                    mLogHelper = new LogHelper();
            }
        }
        return mLogHelper;
    }

    /**
     * 对当前时间格式化
     * @return
     */
    public static String currentTimeToString(){
        return DateFormat.format("yyyy-MM-dd kk:mm:ss", System.currentTimeMillis()).toString();
    }

    private final class WorkHandler extends Handler{
        public WorkHandler(Looper looper){
            super(looper);
        }
        @Override
        public void handleMessage(Message msg){

        }
    }
    private  void initWorkHandler(){
        HandlerThread thread = new HandlerThread(LogHelper.TAG);
        //log线程为守护线程
        thread.setDaemon(true);
        thread.start();
        mWorkHandler = new WorkHandler(thread.getLooper());
    }
}
