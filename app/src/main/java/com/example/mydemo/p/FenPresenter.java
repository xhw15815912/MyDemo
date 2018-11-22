package com.example.mydemo.p;

import com.example.mydemo.Bean.CarBean;
import com.example.mydemo.Bean.LookBean;
import com.example.mydemo.Http.OkHttpUtils;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * 作者：夏洪武
 * 时间：2018
 * 邮箱：
 * 说明：
 */
public class FenPresenter {


    public void setData(String path) {
        OkHttpUtils.getInstance().setData(path, new OkHttpUtils.OkHttpInterface() {
            @Override
            public void success(String string) {
                LookBean bean = new Gson().fromJson(string, LookBean.class);
                mPInterFace.pass(bean);

            }
        });
    }

    public void getData(String path, HashMap<String, String> map) {
        OkHttpUtils.getInstance().getData(path,map, new OkHttpUtils.OkHttpInterface() {
            @Override
            public void success(String string) {
                CarBean bean = new Gson().fromJson(string, CarBean.class);
                mPInterFace.car(bean);
            }
        });
    }

    public interface PInterFace{
       void pass(LookBean path);

        void car(CarBean path);
    }
   public PInterFace mPInterFace;

    public FenPresenter(PInterFace mpInterFace) {
        this.mPInterFace = mpInterFace;
    }
}
