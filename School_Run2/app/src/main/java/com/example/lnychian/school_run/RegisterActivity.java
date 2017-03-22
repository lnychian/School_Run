package com.example.lnychian.school_run;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lnychian.school_run.Data.MyUser;


import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {

    @InjectView(R.id.fab)
    FloatingActionButton fab;
    @InjectView(R.id.cv_add)
    CardView cvAdd;
    @InjectView(R.id.bt_nest)
    Button btNest;
    @InjectView(R.id.cd_btn)
    Button btCd;
    @InjectView(R.id.et_username)
    EditText etUsername;
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.et_repeatpassword)
    EditText etRepeatpasswordpassword;
    @InjectView(R.id.et_phone)
    EditText etPhone;
    @InjectView(R.id.et_Code)
    EditText etCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ShowEnterAnimation();
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateRevealClose();
            }
        });

    }
    @OnClick({R.id.cd_btn,R.id.bt_nest})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cd_btn:
                Explode explode = new Explode();
                explode.setDuration(500);
                getWindow().setExitTransition(explode);
                getWindow().setEnterTransition(explode);
                ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
                BmobUser bu = new BmobUser();
                bu.setUsername(etUsername.getText().toString());
                bu.setPassword(etPassword.getText().toString());
                bu.setMobilePhoneNumber(etPhone.getText().toString());//设置手机号码（必填）
                if (etUsername.getText().toString().equals(""))
                {
                    Toast.makeText(RegisterActivity.this,"请输入用户名",Toast.LENGTH_LONG).show();
                }
                else
                {
                    if(etPassword.getText().toString().equals(""))
                    {
                        Toast.makeText(RegisterActivity.this,"密码为空",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        if (etPhone.getText().toString().equals(""))
                        {
                            Toast.makeText(RegisterActivity.this,"请输入手机号",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            if(etPassword.getText().toString().equals(etRepeatpasswordpassword.getText().toString()))
                            {
                                BmobSMS.requestSMSCode(etPhone.getText().toString(),"JustRun", new QueryListener<Integer>() {

                                    @Override
                                    public void done(Integer smsId,BmobException ex) {
                                        if(ex==null){//验证码发送成功
                                            Log.i("smile", "短信id："+smsId);//用于查询本次短信发送详情
                                        }
                                    }
                                });
                            }
                            else
                            {
                                Toast.makeText(RegisterActivity.this,"密码不相同",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
                break;
            case R.id.bt_nest:
                BmobUser bu1 = new BmobUser();
                bu1.setUsername(etUsername.getText().toString());
                bu1.setPassword(etPassword.getText().toString());
                bu1.setMobilePhoneNumber(etPhone.getText().toString());
                bu1.signOrLogin(etCode.getText().toString(), new SaveListener<MyUser>() {
                    @Override
                    public void done(MyUser user,BmobException e) {
                        if(e==null){
                            Toast.makeText(RegisterActivity.this,"注册或登录成功",Toast.LENGTH_LONG).show();
                            Intent i2 = new Intent(RegisterActivity.this,MainActivity.class);
                            startActivity(i2);
                            finish();
                        }else{
                            Toast.makeText(RegisterActivity.this,"失败:" + e.getMessage(),Toast.LENGTH_LONG).show();
                            if(e.getErrorCode()==202)
                            {
                                Toast.makeText(RegisterActivity.this,"用户名已存在",Toast.LENGTH_LONG).show();
                            }
                            if(e.getErrorCode()==207)
                            {
                                Toast.makeText(RegisterActivity.this,"验证码错误",Toast.LENGTH_LONG).show();
                            }
                            if(e.getErrorCode()==209)
                            {
                                Toast.makeText(RegisterActivity.this,"手机号已被注册",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
                break;
        }
    }

    private void ShowEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
        getWindow().setSharedElementEnterTransition(transition);

        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                cvAdd.setVisibility(View.GONE);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }


        });
    }

    public void animateRevealShow() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth()/2,0, fab.getWidth() / 2, cvAdd.getHeight());
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                cvAdd.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    public void animateRevealClose() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd,cvAdd.getWidth()/2,0, cvAdd.getHeight(), fab.getWidth() / 2);
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                cvAdd.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                fab.setImageResource(R.drawable.plus);
                RegisterActivity.super.onBackPressed();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }
    @Override
    public void onBackPressed() {
        animateRevealClose();
    }
}
