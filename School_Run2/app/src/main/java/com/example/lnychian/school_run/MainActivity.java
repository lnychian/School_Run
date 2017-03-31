package com.example.lnychian.school_run;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.transition.Explode;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lnychian.school_run.Fragment.FragmentFour;
import com.example.lnychian.school_run.Fragment.FragmentOne;
import com.example.lnychian.school_run.Fragment.FragmentRun;
import com.example.lnychian.school_run.Fragment.FragmentThree;
import com.example.lnychian.school_run.Fragment.FragmentTwo;
import com.ycl.tabview.library.TabView;
import com.ycl.tabview.library.TabViewChild;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity {
    TabView tabView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabView= (TabView) findViewById(R.id.tabView);
        Explode explode = new Explode();
        explode.setDuration(500);
        getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(explode);

        List<TabViewChild> tabViewChildList=new ArrayList<>();
        TabViewChild tabViewChild01=new TabViewChild(R.drawable.tab01_sel,R.drawable.tab01_unsel,"首页",  FragmentOne.newInstance("首页"));
        TabViewChild tabViewChild02=new TabViewChild(R.drawable.tab02_sel,R.drawable.tab02_unsel,"功能",  FragmentTwo.newInstance("功能"));
        TabViewChild tabViewChild04=new TabViewChild(R.drawable.run,R.drawable.run,"开始跑步",  FragmentRun.newInstance("选择模式"));
        TabViewChild tabViewChild03=new TabViewChild(R.drawable.tab03_sel,R.drawable.tab03_unsel,"资讯",  FragmentThree.newInstance("资讯"));
        TabViewChild tabViewChild05=new TabViewChild(R.drawable.tab05_sel,R.drawable.tab05_unsel,"我的",  FragmentFour.newInstance("我的"));
        tabViewChildList.add(tabViewChild01);
        tabViewChildList.add(tabViewChild02);
        tabViewChildList.add(tabViewChild04);
        tabViewChildList.add(tabViewChild03);
        tabViewChildList.add(tabViewChild05);
        tabView.setTabViewDefaultPosition(0);
        tabView.setTabViewChild(tabViewChildList,getSupportFragmentManager());
        tabView.setOnTabChildClickListener(new TabView.OnTabChildClickListener() {
            @Override
            public void onTabChildClick(int  position, ImageView currentImageIcon, TextView currentTextView) {
//                if( position == 2)
//                {
//                    startActivity(new Intent(MainActivity.this, RunActivity.class));
//                }
            }
        });
    }
}
