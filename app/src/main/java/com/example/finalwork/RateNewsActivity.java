package com.example.finalwork;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class RateNewsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_news);

        //初始化界面组件
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager);

        //菜单标题
        String[] title = {"知乎日报", "新浪外汇", "网易财经"};

        List<Fragment> fragmentlist;
        fragmentlist = new ArrayList<>();
        fragmentlist.add(new FragmentOne());
        fragmentlist.add(new FragmentTwo());
        fragmentlist.add(new FragmentThree());
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragmentlist, title);
        //添加适配器
        viewPager.setAdapter(myFragmentPagerAdapter);
        //设置tablayou和viewpage关联
        tabLayout.setupWithViewPager(viewPager);
    }
}