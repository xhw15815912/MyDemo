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

public class OkHttpUtils {

    private OkHttpClient mOkHttpClient;
    private Handler mHandler;
    private static OkHttpUtils sokHttpUtils;

    private OkHttpUtils() {
        mHandler = new Handler(Looper.myLooper());
        mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .build();
    }
    public static OkHttpUtils getInstance(){
        if(sokHttpUtils == null){
            synchronized (OkHttpUtils.class){
                            if(sokHttpUtils == null){
                                return sokHttpUtils= new OkHttpUtils();
                            }
                        }
        }
        return sokHttpUtils;
    }




    public void setData(String path, final OkHttpInterface okHttpInterface) {
        Request build = new Request.Builder()
                .url(path)
                .build();
        mOkHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response !=null && response.isSuccessful()){
                    final String string = response.body().string();
                    if(okHttpInterface!=null){
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                              okHttpInterface.success(string);
                            }
                        });
                    }
                }
            }
        });
    }

    public void getData(String path, HashMap<String,String> map, final OkHttpInterface okHttpInterface) {
        FormBody.Builder builder = new FormBody.Builder();
        if(map!=null){
            for (String key:map.keySet()){
                builder.add(key,map.get(key));
            }
        }
        FormBody body = builder.build();
        Request request = new Request.Builder()
                .url(path)
                .post(body)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                      okHttpInterface.success(string);
                    }
                });
            }
        });

    }


    public interface OkHttpInterface {
        void success(String string);
    };

    public OkHttpInterface mOkHttpInterface;

    public OkHttpUtils(OkHttpInterface mOkHttpInterface) {
        this.mOkHttpInterface = mOkHttpInterface;
    }
}
