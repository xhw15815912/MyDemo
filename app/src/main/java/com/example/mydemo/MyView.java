package com.example.mydemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 作者：夏洪武
 * 时间：2018
 * 邮箱：
 * 说明：
 */
public class MyView extends LinearLayout implements View.OnClickListener {
    TextView add,remove,num;
    int NUMBER=0;
    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context,  AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.add_remove_view, this);
        add=view.findViewById(R.id.add);
        remove=view.findViewById(R.id.remove);
        num=view.findViewById(R.id.num);
        add.setOnClickListener(this);
        remove.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.add:
              ++NUMBER;
              num.setText(NUMBER+"");
              mChangeInterFace.change(NUMBER);
              break;
          case R.id.remove:
              if(NUMBER>0){
                  NUMBER--;
                  num.setText(NUMBER+"");
                  mChangeInterFace.change(NUMBER);
              }
              
              break;
      }
    }

    public void setNum(int num1) {
        this.NUMBER=num1;
        num.setText(num1+"");
    }


    public interface ChangeInterFace{
        void change(int number);

    }
    public ChangeInterFace mChangeInterFace;
    public void mChangeInterFace(ChangeInterFace changeInterFace){
        mChangeInterFace=changeInterFace;
    }
}
