package com.example.mydemo;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mydemo.Bean.CarBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：夏洪武
 * 时间：2018
 * 邮箱：
 * 说明：
 */
class MyEXAdpater extends BaseExpandableListAdapter {

    private final FragmentActivity context;
    private final DisplayImageOptions options;
    private List<CarBean.DataBean> list;

    public MyEXAdpater(FragmentActivity activity) {
        this.context=activity;
        this.list=new ArrayList<>();
        ImageLoaderConfiguration build = new ImageLoaderConfiguration.Builder(activity)
                .build();
        ImageLoader.getInstance().init(build);
        options = new DisplayImageOptions.Builder()
                .build();
    }
    public void setData(List<CarBean.DataBean> data) {
        this.list.clear();
        this.list.addAll(data);
        notifyDataSetChanged();
    }
    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder gh;
        if(convertView==null){
            gh=new GroupHolder();
            View view = convertView = View.inflate(parent.getContext(), R.layout.item_group, null);
            gh.ck=view.findViewById(R.id.ck);
            gh.text=view.findViewById(R.id.text);
            convertView.setTag(gh);
        }else{
            gh= (GroupHolder) convertView.getTag();
        }
        gh.text.setText(list.get(groupPosition).getSellerName());
        gh.ck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                madInterface.GroupCk(groupPosition);
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder cl;
        if(convertView==null){
            cl=new ChildHolder();
            convertView = View.inflate(parent.getContext(), R.layout.item_child, null);
            cl.ck=convertView.findViewById(R.id.ck);
            cl.text1=convertView.findViewById(R.id.text1);
            cl.text2=convertView.findViewById(R.id.text2);
            cl.img=convertView.findViewById(R.id.img);
            cl.mv=convertView.findViewById(R.id.myView);
            convertView.setTag(cl);
        }else{
            cl= (ChildHolder) convertView.getTag();
        }
        cl.text1.setText(list.get(groupPosition).getList().get(childPosition).getTitle());
        cl.text2.setText(list.get(groupPosition).getList().get(childPosition).getPrice()+"");
        cl.mv.setNum(list.get(groupPosition).getList().get(childPosition).getNum());
        cl.ck.setChecked(list.get(groupPosition).getList().get(childPosition).getSelected()==1);
        String images = list.get(groupPosition).getList().get(childPosition).getImages();

        String[] split = images.split("\\|");
        ImageLoader.getInstance().displayImage(split[0],cl.img,options);
        cl.ck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                madInterface.ChildCk(groupPosition,childPosition);
            }
        });
        cl.mv.mChangeInterFace(new MyView.ChangeInterFace() {
            @Override
            public void change(int number) {
               madInterface.Num(groupPosition,childPosition,number);
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public void setNum(int group, int child, int num) {
        CarBean.DataBean.ListBean listBean = list.get(group).getList().get(child);
        listBean.setNum(num);
    }

    public float addMoney() {
        float n=0;
        for (int i=0;i<list.size();i++){
            List<CarBean.DataBean.ListBean> beans = list.get(i).getList();
            for (int j=0;j<beans.size();j++){
                if (beans.get(j).getSelected()==1){
                    int num = beans.get(j).getNum();
                    double price = beans.get(j).getPrice();
                    n+=num*price;
                }


            }

        }
        return n;
    }

    public void setGroup(int group, boolean b) {
        List<CarBean.DataBean.ListBean> list = this.list.get(group).getList();
        for (int i=0;i<list.size();i++){
            //获取所有的selected
            CarBean.DataBean.ListBean listBean = list.get(i);
            listBean.setSelected(b?1:0);
        }
    }

    public Boolean getSeleted(int group) {
        List<CarBean.DataBean.ListBean> list = this.list.get(group).getList();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getSelected()==0){
                return false;
            }
        }
        return true;
    }

    public void setChild(int group, int child) {
        CarBean.DataBean.ListBean listBean = list.get(group).getList().get(child);
        listBean.setSelected(listBean.getSelected()==0?1:0);
    }

    public int all() {
        int a=0;
        for (int i=0;i<list.size();i++){
            List<CarBean.DataBean.ListBean> list = this.list.get(i).getList();
            for (int j = 0; j< list.size(); j++){

                if(list.get(j).getSelected()==1){
                    a+=list.get(j).getNum();
                }
            }
        }
        return a;
    }

    public void setALL(boolean b) {
        for (int i=0;i<list.size();i++){
            List<CarBean.DataBean.ListBean> list = this.list.get(i).getList();
            for (int j = 0; j< list.size(); j++){
                  list.get(j).setSelected(b?1:0);
            }
        }
    }

    public boolean allCheck() {
        for (int i=0;i<list.size();i++){
            List<CarBean.DataBean.ListBean> list = this.list.get(i).getList();
            for (int j = 0; j< list.size(); j++){
                if(list.get(j).getSelected()==0){
                    return false;
                }
            }
        }
        return true;
    }

    class GroupHolder{
        TextView text;
        CheckBox ck;

}
class ChildHolder{
        TextView text1,text2;
        ImageView img;
        CheckBox ck;
        MyView mv;
}
public interface MadInterface{
     void GroupCk(int group);
     void ChildCk(int group,int child);
     void Num(int group,int child,int NUM);
}

public MadInterface madInterface;

    public void setMadInterface(MadInterface madInterface) {
        this.madInterface = madInterface;
    }
}
