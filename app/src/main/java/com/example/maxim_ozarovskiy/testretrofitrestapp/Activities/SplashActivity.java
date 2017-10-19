package com.example.maxim_ozarovskiy.testretrofitrestapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.maxim_ozarovskiy.testretrofitrestapp.R;

/**
 * Created by Maxim_Ozarovskiy on 19.07.2017.
 */

public class SplashActivity extends AppCompatActivity {
    ImageView splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_activity);

        splash = (ImageView) findViewById(R.id.splash);
        Glide.with(this).load(R.raw.gif_splash).asGif().into(splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startMainActivity();
            }

            private void startMainActivity() {
                Intent intent = new Intent(getApplicationContext(), CityCheckListActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }

}
