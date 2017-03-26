package com.example.lnychian.school_run.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.example.lnychian.school_run.LoginActivity;
import com.example.lnychian.school_run.MainActivity;
import com.example.lnychian.school_run.R;

import cn.bmob.v3.BmobUser;

public class FragmentFour extends Fragment {
    TextView textView;
    Button exit;
    public static FragmentFour newInstance(String text){
        FragmentFour fragmentCommon=new FragmentFour();
        Bundle bundle=new Bundle();
        bundle.putString("text",text);
        fragmentCommon.setArguments(bundle);
        return fragmentCommon;
    }
    @Nullable @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_one,container,false);
        textView= (TextView) view.findViewById(R.id.textView);
        textView.setText(getArguments().getString("text"));
        exit = (Button) view.findViewById(R.id.Exit);
        return view;
//        exit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                BmobUser.logOut();   //清除缓存用户对象
//                Intent i = new Intent(MainActivity.this,LoginActivity.class);
//                startActivity(i);
//                finish();
//            }
//        });
    }

}
