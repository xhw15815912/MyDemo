package com.example.mydemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：夏洪武
 * 时间：2018
 * 邮箱：
 * 说明：
 */
public class Show extends AppCompatActivity {
    private ViewPager page;
    private RadioButton rad1;
    private RadioButton rad2;
    private RadioGroup rad;
    private List<Fragment> frag;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);
        initView();

        frag.add(new Lokk());
        frag.add(new My());
        frag.add(new ShopCar());
        page.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return frag.get(i);
            }

            @Override
            public int getCount() {
                return frag.size();
            }
        });
        rad.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rad1:
                        page.setCurrentItem(0);
                        break;
                    case R.id.rad2:
                        page.setCurrentItem(1);
                        break;
                    case R.id.rad3:
                        page.setCurrentItem(2);
                        break;
                }
            }
        });
    }

    private void initView() {
        page = (ViewPager) findViewById(R.id.page);
        rad1 = (RadioButton) findViewById(R.id.rad1);
        rad2 = (RadioButton) findViewById(R.id.rad2);
        rad = (RadioGroup) findViewById(R.id.rad);
        frag=new ArrayList<>();
    }
}
