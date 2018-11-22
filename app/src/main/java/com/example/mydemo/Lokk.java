package com.example.mydemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydemo.Adpater.MyAdapter;
import com.example.mydemo.Bean.CarBean;
import com.example.mydemo.Bean.LookBean;
import com.example.mydemo.p.FenPresenter;

import java.util.HashMap;


public class Lokk extends Fragment implements FenPresenter.PInterFace {


    private RecyclerView recy;
    private MyAdapter mad;
    private FenPresenter fen;
    private String path="http://www.wanandroid.com/tools/mockapi/6523/restaurants_offset_0_limit_4";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lokk, container, false);
        initView(view);

        HashMap<String, String> map = new HashMap<>();
        fen.setData(path);

        recy.setAdapter(mad);
        recy.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        return view;
    }

    private void initView(View view) {
        mad=new MyAdapter(getActivity());
        recy = (RecyclerView) view.findViewById(R.id.recy);
        fen=new FenPresenter(this);
    }


    @Override
    public void pass(LookBean path) {
        mad.setData(path.getData());
    }

    @Override
    public void car(CarBean path) {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
       if(fen.mPInterFace!=null){
           fen.mPInterFace=null;
       }
       System.gc();

    }
}
