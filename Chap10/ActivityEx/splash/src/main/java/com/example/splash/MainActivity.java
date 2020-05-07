package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*splashActivity
        //2가지 방법이 있다.
        1. new 쉽다()
            단점:요즘은 폰이 빨라서 그렇지 폰이 느리면 메인이 약간 보였다가 splashActivity가 실행함.
        <1st>
        1.1버튼을 누르기전에 나와야 된다.

        2. 1의 단점을 보완하기 위해(메인화면이 잠깐 보일 수 있다.)
        처음부터 splash를 띄운다.
        복잡해서 순서가 중요하다.

        <순서>splashmethod2 Module참고
        2.1.mainfest.xml에서 intent-filter를 Splash Activity에 옮긴다.
        2.2.style.xml에서 SplashTemem를 하나 만든다.
        2.3.mainfest.xml에서 splash Activity의 테마를 만든 테마를 설정한다.
        2.4.Splash Activity에서 Thread로 3초 설정대기한 후에 intent로 MainActivity를 호출하고 나는 finish한다.
        */


        Intent mIntent = new Intent(getApplicationContext(),SplashActivity.class);
        startActivity(mIntent);



    }
}
