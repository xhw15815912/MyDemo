package com.example.mydemo.Adpater;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mydemo.Bean.LeftBean;
import com.example.mydemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：夏洪武
 * 时间：2018
 * 邮箱：
 * 说明：
 */
public class LeftAdpater extends RecyclerView.Adapter<LeftAdpater.ViewHolder> {
    private FragmentActivity context;
    private List<LeftBean.DataBean> list;

    public LeftAdpater(FragmentActivity activity) {
        this.context=activity;
        this.list=new ArrayList<>();
    }
    public void setData(List<LeftBean.DataBean> data) {
        this.list.clear();
        this.list.addAll(data);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.leftitem, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
         viewHolder.text.setText(list.get(i).getName());

         viewHolder.text.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 mTextTouchInterFace.touch(list.get(i).getCid());
             }
         });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text=itemView.findViewById(R.id.text);
        }
    }

    public interface TextTouchInterFace{
        void touch(int id);
    }
    public TextTouchInterFace mTextTouchInterFace;

    public void LeftAdpater(TextTouchInterFace textTouchInterFace) {

        mTextTouchInterFace = textTouchInterFace;
    }
}
