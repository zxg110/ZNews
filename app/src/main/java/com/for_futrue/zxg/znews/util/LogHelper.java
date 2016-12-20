package com.for_futrue.zxg.znews.util;

import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import android.text.format.DateFormat;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

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
        if(isWriteLogToFileEnable()){
            initWorkHandler();;
        }
    }
    public static boolean isLogEnable(){
        return DEBUG;
    }

    public static boolean isWriteLogToFileEnable(){
        return WRITE_LOG_FILE;
    }

    public static void v(String message,Object... args){
        log(Log.VERBOSE,null,message,args);
    }

    public static void d(String message,Object... args){
        log(Log.DEBUG,null,message,args);
    }

    public static void i(String message,Object... args){
        log(Log.INFO,null,message,args);
    }

    public static void w(String message,Object... args){
        log(Log.WARN,null,message,args);
    }

    public static void e(Throwable ex){
        log(Log.ERROR,ex,null);
    }

    public static void e(String message,Object... args){
        log(Log.ERROR,null,message,args);
    }
    public static void e(Throwable ex,String message,Object... args){
        log(Log.ERROR,ex,message,args);
    }
    private static void log(int priority,Throwable ex,String message,Object...args){
        if(!isLogEnable() && !isWriteLogToFileEnable()) return;
        String log;
        if(args.length > 0){
            message = String.format(message,args);
        }
        if(ex == null){
            log = message;
        }else{
            String logMessage = message == null ? ex.getMessage():message;
            String logBody = Log.getStackTraceString(ex);
            log = String.format(LOG_FORMAT,logMessage,logBody);
        }
        if(isLogEnable()){
            Log.println(priority,TAG,log);
        }
        if(isWriteLogToFileEnable()){

        }
    }
    /**
     * 生成格式文件
     */
    private static void writeToFile(String logString){
        StringBuilder builder = new StringBuilder();
        builder.append("["+currentTimeToString()+"]");
        builder.append(logString);
        builder.append("\n");
        //打印线程处理
        Message.obtain(getInstance().mWorkHandler,1,builder.toString()).sendToTarget();
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
            writeLogToFile((String) msg.obj, LOG_FILE_PATH, LOG_FILE_MAX_SIZE);
        }
    }
    private  void initWorkHandler(){
        HandlerThread thread = new HandlerThread(LogHelper.TAG);
        //log线程为守护线程
        thread.setDaemon(true);
        thread.start();
        mWorkHandler = new WorkHandler(thread.getLooper());
    }

    /**
     * 把当前log写入到文件中
     * @param logString
     * @param filePath
     * @param maxSize
     */
    private void writeLogToFile(String logString,String filePath,long maxSize){
        try{
            File file = ensureFile(filePath,maxSize);
            if(file == null) return;

            FileOutputStream out = new FileOutputStream(file,true);
            OutputStreamWriter writer = new OutputStreamWriter(out);
            writer.write(logString);
            writer.flush();
            out.flush();
            writer.close();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 1、确认文件是否存在
     * 2、确认文件大小
     * @param filePath
     * @param maxSize
     * @return
     * @throws IOException
     */
    private File ensureFile(String filePath,long maxSize) throws IOException{
        File file = new File(filePath);
        File dir = file.getParentFile();
        if(dir != null && !dir.exists()){
            dir.mkdirs();
        }
        if(file.exists() && file.length() >= maxSize){
            file.delete();
        }
        if(!file.exists()){
            if(!file.createNewFile()){
                return null;
            }
        }
        return file;
    }

}
