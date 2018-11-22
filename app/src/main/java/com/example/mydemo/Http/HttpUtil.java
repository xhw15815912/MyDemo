package com.example.mydemo.Http;


import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：夏洪武
 * 时间：2018
 * 邮箱：
 * 说明：
 */
public class HttpUtil {

    private  Handler mHandler;
    private  OkHttpClient mHttpClient;
    private static HttpUtil sHttpUtil;
    private HttpUtil() {
        mHandler = new Handler(Looper.myLooper());
        mHttpClient = new OkHttpClient.Builder()
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .build();
    }
    public static HttpUtil getInstance(){
        if(sHttpUtil==null){
            synchronized (HttpUtil.class){
                            if( sHttpUtil== null){
                                return sHttpUtil= new HttpUtil();
                            }
                        }
        }
        return sHttpUtil;
    }





    public void setData(String path, final HttpInterFace httpInterFace) {
        Request build = new Request.Builder()
                .url(path)
                .build();
        mHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpInterFace.success(string);
                    }
                });
            }
        });
    }

    public void getData(String path1, HashMap<String,String> map, final HttpInterFace httpInterFace) {
        FormBody.Builder builder = new FormBody.Builder();
        if(map!=null){
            for (String key :map.keySet()){
                builder.add(key,map.get(key));
            }
        }

        FormBody build = builder.build();

        Request request = new Request.Builder()
                .post(build)
                .url(path1)
                .build();

        mHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpInterFace.success(string);
                    }
                });
            }
        });
    }

    public interface HttpInterFace{
        void success(String string);
    }
    public HttpInterFace mHttpInterFace;

    public HttpUtil(HttpInterFace mHttpInterFace) {
        this.mHttpInterFace = mHttpInterFace;
    }

}
