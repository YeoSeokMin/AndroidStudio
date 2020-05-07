package com.example.splashmethod2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash);
        //ActionBar ab = getSupportActionBar();
        //ab.hide();
        //Thread는 try..catch를 요구한다.

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {      //Thread가 처리하는중간에 중단되면 이리로 온다.

        }
        Intent mIntent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(mIntent);
        finish();
    }
}
