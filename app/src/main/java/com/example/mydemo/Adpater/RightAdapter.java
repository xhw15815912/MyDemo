package com.example.mydemo.Adpater;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mydemo.Bean.RightBean;
import com.example.mydemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：夏洪武
 * 时间：2018
 * 邮箱：
 * 说明：
 */
public class RightAdapter extends RecyclerView.Adapter<RightAdapter.ViewHolder> {
    private final FragmentActivity context;
    private List<RightBean.DataBean> list;
    private MyAdpaterTwo adt;
    public RightAdapter(FragmentActivity activity) {
        this.context=activity;
        this.list=new ArrayList<>();

    }
    public void getData(List<RightBean.DataBean> data) {
        this.list.clear();
        this.list.addAll(data);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.right_iten, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
         viewHolder.text.setText(list.get(i).getName());
        List<RightBean.DataBean.ListBean> l = this.list.get(i).getList();
        adt=new MyAdpaterTwo(context, l);
         viewHolder.recy.setLayoutManager(new GridLayoutManager(context,3));
         viewHolder.recy.setAdapter(adt);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        RecyclerView recy;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text=itemView.findViewById(R.id.text);
            recy=itemView.findViewById(R.id.recy);
        }
    }
}
