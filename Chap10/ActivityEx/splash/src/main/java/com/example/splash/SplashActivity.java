package com.example.splash;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ActionBar ab = getSupportActionBar();
        ab.hide();

        //핸들러는 java의 Thread와 비슷하다.
        Handler hd = new Handler();
        hd.postDelayed(new Runnable() {             //3초 delayed
            @Override
            public void run() {
                finish();
            }
        },3000);
    }
}
