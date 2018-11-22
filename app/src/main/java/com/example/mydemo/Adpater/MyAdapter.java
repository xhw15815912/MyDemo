package com.example.mydemo.Adpater;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mydemo.Bean.LookBean;
import com.example.mydemo.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

public class  MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private final DisplayImageOptions options;
    private FragmentActivity context;
    private List<LookBean.DataBean> list;

    public MyAdapter(FragmentActivity lokk) {
        this.context=lokk;
        this.list=new ArrayList<>();
        ImageLoaderConfiguration build = new ImageLoaderConfiguration.Builder(context)
                .build();
        ImageLoader.getInstance().init(build);
        options = new DisplayImageOptions.Builder()
                .build();
    }
    public void setData(List<LookBean.DataBean> data) {
        this.list.clear();
        this.list.addAll(data);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.look_item, null);
        ViewHolder holder = new ViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
         viewHolder.text.setText(list.get(i).getBulletin());
         ImageLoader.getInstance().displayImage(list.get(i).getPic_url(),viewHolder.img,options);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            text=itemView.findViewById(R.id.text);
        }
    }
}
