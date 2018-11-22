package com.example.mydemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.mydemo.Bean.CarBean;
import com.example.mydemo.Bean.LookBean;
import com.example.mydemo.p.FenPresenter;

import java.util.HashMap;


public class ShopCar extends Fragment implements View.OnClickListener,FenPresenter.PInterFace {

    private ExpandableListView exd;
    private CheckBox ck;
    private TextView text;
    private Button button;
    private MyEXAdpater xad;
    private FenPresenter fen;
    String path="http://www.zhaoapi.cn/product/getCarts";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_car, container, false);
        initView(view);
        initData();
        HashMap<String, String> map = new HashMap<>();
        map.put("uid","71");
        fen.getData(path,map);

        ck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean x=xad.allCheck();
                xad.setALL(!x);
                xad.notifyDataSetChanged();
            }
        });
        return view;
    }

    private void refrsh() {

        int a= xad.all();
        button.setText(a+"");
        float money=xad.addMoney();
        text.setText(money+"");
    }

    private void initData() {
        xad.setMadInterface(new MyEXAdpater.MadInterface() {
            @Override
            public void GroupCk(int group) {
                //要先判断底下是全选中还是没选中
                Boolean s=xad.getSeleted(group);
                //如果是true变成false
                xad.setGroup(group,!s);
                xad.notifyDataSetChanged();
                refrsh();
            }

            @Override
            public void ChildCk(int group, int child) {
                 xad.setChild(group,child);
                 xad.notifyDataSetChanged();
                 refrsh();
            }

            @Override
            public void Num(int group, int child, int NUM) {
                xad.setNum(group,child,NUM);
                xad.notifyDataSetChanged();
                refrsh();
            }
        });
    }

    private void initView(View view) {
        exd = (ExpandableListView) view.findViewById(R.id.exd);
        ck = (CheckBox) view.findViewById(R.id.ck);
        text = (TextView) view.findViewById(R.id.text);
        button = (Button) view.findViewById(R.id.button);
        fen=new FenPresenter(this);
        button.setOnClickListener(this);
        xad=new MyEXAdpater(getActivity());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:

                break;
        }
    }

    @Override
    public void pass(LookBean path) {

    }

    @Override
    public void car(CarBean path) {
         xad.setData(path.getData());
         exd.setAdapter(xad);
        refrsh();
        for (int i=0;i<path.getData().size();i++){
            exd.expandGroup(i);
        }

    }
}
