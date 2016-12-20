package com.for_futrue.zxg.znews.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Animation.AnimationListener;

import com.for_futrue.zxg.znews.R;

/**
 * Created by zxg on 2016/10/3.
 */
public class WelcomeActivity extends Activity{
    private AlphaAnimation mStartAnim;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = View.inflate(this, R.layout.welcome,null);
        setContentView(view);
        showStartAnim();
        Log.i("zxg","onCreate()...");

    }
    private void showStartAnim(){
        AnimationSet animationSet = new AnimationSet(true);
        mStartAnim = new AlphaAnimation(0.1f,1.0f);
        mStartAnim.setDuration(3000);
        mStartAnim.setFillAfter(true);
        animationSet.addAnimation(mStartAnim);
        view.startAnimation(mStartAnim);
        mStartAnim.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i("zxg","start");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i("zxg", "end");
                //redirectTo();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



    }
    private void redirectTo() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        Log.i("zxg","finish");
    }
}
