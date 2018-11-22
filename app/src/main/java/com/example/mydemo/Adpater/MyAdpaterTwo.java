package com.example.mydemo.Adpater;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mydemo.Bean.RightBean;
import com.example.mydemo.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 * 作者：夏洪武
 * 时间：2018
 * 邮箱：
 * 说明：
 */
class MyAdpaterTwo extends RecyclerView.Adapter<MyAdpaterTwo.ViewHolder> {
    private final FragmentActivity context;
    private final DisplayImageOptions options;
    private List<RightBean.DataBean.ListBean> list1;


    public MyAdpaterTwo(FragmentActivity activity, List<RightBean.DataBean.ListBean> list) {
        this.context=activity;
        this.list1=list;
        ImageLoaderConfiguration build = new ImageLoaderConfiguration.Builder(activity).build();
        ImageLoader.getInstance().init(build);
        options = new DisplayImageOptions.Builder().build();
    }

    @NonNull
    @Override
    public MyAdpaterTwo.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.two, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdpaterTwo.ViewHolder viewHolder, int i) {
             viewHolder.text.setText(list1.get(i).getName());

                 ImageLoader.getInstance().displayImage(list1.get(i).getIcon(),viewHolder.img,options);


    }

    @Override
    public int getItemCount() {
        return list1.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text=itemView.findViewById(R.id.text);
            img=itemView.findViewById(R.id.img);
        }
    }
}
