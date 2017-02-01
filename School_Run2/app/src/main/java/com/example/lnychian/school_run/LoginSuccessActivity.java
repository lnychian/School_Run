package com.example.lnychian.school_run;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;


public class LoginSuccessActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        Explode explode = new Explode();
        explode.setDuration(500);
        getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(explode);
        Button exit = (Button) findViewById(R.id.button);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BmobUser.logOut();   //清除缓存用户对象
                BmobUser currentUser = BmobUser.getCurrentUser(); // 现在的currentUser是null了
                if(currentUser == null){
                    startActivity(new Intent(LoginSuccessActivity.this, LoginActivity.class));
                    finish();
                }else{
                    Toast.makeText(LoginSuccessActivity.this,"aaaaaaaaaa",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
