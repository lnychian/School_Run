package com.example.lnychian.school_run.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lnychian.school_run.LoginActivity;
import com.example.lnychian.school_run.R;
import com.example.lnychian.school_run.RegisterActivity;
import com.example.lnychian.school_run.RunActivity;

import butterknife.OnClick;

public class FragmentRun extends Fragment {
    TextView textView,bytime,bydistance,byfree;
    ImageView time;
    ImageView distance;
    ImageView free;
    public static FragmentRun newInstance(String text){
        FragmentRun fragmentCommon=new FragmentRun();
        Bundle bundle=new Bundle();
        bundle.putString("text",text);
        fragmentCommon.setArguments(bundle);
        return fragmentCommon;
    }
    @Nullable @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_run,container,false);
        textView= (TextView) view.findViewById(R.id.textView);
        bytime= (TextView) view.findViewById(R.id.bytime);
        bydistance= (TextView) view.findViewById(R.id.bydistance);
        byfree= (TextView) view.findViewById(R.id.byfree);
        time= (ImageView) view.findViewById(R.id.time);
        distance= (ImageView) view.findViewById(R.id.distance);
        free= (ImageView) view.findViewById(R.id.free);
        textView.setText(getArguments().getString("text"));
        free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"aaaaaaaaaaa",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getActivity(), RunActivity.class));
                getActivity().finish();
            }
        });
        return view;
    }
}
