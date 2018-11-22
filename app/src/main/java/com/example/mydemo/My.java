package com.example.mydemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.mydemo.Adpater.LeftAdpater;
import com.example.mydemo.Adpater.RightAdapter;
import com.example.mydemo.Bean.LeftBean;
import com.example.mydemo.Bean.RightBean;
import com.example.mydemo.Http.HttpUtil;
import com.google.gson.Gson;

import java.util.HashMap;


public class My extends Fragment {


    private RecyclerView recyone;
    private RecyclerView recytwo;
    private LeftAdpater lad;
    private RightAdapter rad;
    private String path="http://www.zhaoapi.cn/product/getCatagory";
    private String path1="http://www.zhaoapi.cn/product/getProductCatagory";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my, container, false);
        initView(view);
        HttpUtil.getInstance().setData(path, new HttpUtil.HttpInterFace() {
            @Override
            public void success(String string) {
                LeftBean bean = new Gson().fromJson(string, LeftBean.class);
                lad.setData(bean.getData());
            }
        });
        recyone.setAdapter(lad);

        HashMap<String, String> map = new HashMap<>();
        map.put("cid","1");
        HttpUtil.getInstance().getData(path1,map, new HttpUtil.HttpInterFace() {
            @Override
            public void success(String string) {
                RightBean bean = new Gson().fromJson(string, RightBean.class);
                rad.getData(bean.getData());
            }
        });
        recytwo.setAdapter(rad);
        recytwo.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayout.VERTICAL,false));

        recyone.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayout.VERTICAL,false));

        lad.LeftAdpater(new LeftAdpater.TextTouchInterFace() {
            @Override
            public void touch(int id) {
                HashMap<String, String> map = new HashMap<>();
                map.put("cid",id+"");
                HttpUtil.getInstance().getData(path1,map, new HttpUtil.HttpInterFace() {
                    @Override
                    public void success(String string) {
                        RightBean bean = new Gson().fromJson(string, RightBean.class);
                        rad.getData(bean.getData());
                    }
                });
                lad.notifyDataSetChanged();
            }
        });
        return view;
    }

    private void initView(View view) {
        recyone = (RecyclerView) view.findViewById(R.id.recyone);
        recytwo = (RecyclerView) view.findViewById(R.id.recytwo);
        lad=new LeftAdpater(getActivity());
        rad=new RightAdapter(getActivity());
    }


}
