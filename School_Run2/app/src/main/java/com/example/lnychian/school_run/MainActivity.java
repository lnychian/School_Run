package com.example.lnychian.school_run;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ycl.tabview.library.TabView;
import com.ycl.tabview.library.TabViewChild;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobUser;


public class MainActivity extends AppCompatActivity {
    TabView tabView;
    Button exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabView= (TabView) findViewById(R.id.tabView);
        exit = (Button) findViewById(R.id.Exit);
        Explode explode = new Explode();
        explode.setDuration(500);
        getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(explode);

        List<TabViewChild> tabViewChildList=new ArrayList<>();
        TabViewChild tabViewChild01=new TabViewChild(R.drawable.tab01_sel,R.drawable.tab01_unsel,"首页",  FragmentCommon.newInstance("首页"));
        TabViewChild tabViewChild02=new TabViewChild(R.drawable.tab02_sel,R.drawable.tab02_unsel,"功能",  FragmentCommon.newInstance("功能"));
        TabViewChild tabViewChild03=new TabViewChild(R.drawable.tab03_sel,R.drawable.tab03_unsel,"资讯",  FragmentCommon.newInstance("资讯"));
        TabViewChild tabViewChild05=new TabViewChild(R.drawable.tab05_sel,R.drawable.tab05_unsel,"我的",  FragmentCommon.newInstance("我的"));
        tabViewChildList.add(tabViewChild01);
        tabViewChildList.add(tabViewChild02);
        tabViewChildList.add(tabViewChild03);
        tabViewChildList.add(tabViewChild05);
        tabView.setTabViewDefaultPosition(0);
        tabView.setTabViewChild(tabViewChildList,getSupportFragmentManager());
        tabView.setOnTabChildClickListener(new TabView.OnTabChildClickListener() {
            @Override
            public void onTabChildClick(int  position, ImageView currentImageIcon, TextView currentTextView) {
                switch (position)
                {
                    case 0:
                    {
                        exit.setVisibility(View.GONE);
                        break;
                    }
                    case 1:
                    {
                        exit.setVisibility(View.GONE);
                        break;
                    }
                    case 2:
                    {
                        exit.setVisibility(View.GONE);
                        break;
                    }
                    case 3:
                    {
                        exit.setVisibility(View.VISIBLE);
                        break;
                    }
                }
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BmobUser.logOut();   //清除缓存用户对象
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
