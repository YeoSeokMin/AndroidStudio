package com.example.serviceex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

    //화면이 종료되어도 계속되는 음악 서비스
public class MainActivity extends AppCompatActivity {

    Button btnStart, btnStop;
    MediaPlayer mp;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*    캐스팅 시작   */
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);

        intent = new Intent(this,MusicService.class); //Intent로 액티비티 연결하는것처럼 .class 사용.

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);   //음악재생을 백그라운드(서비스)로 넘겨줌
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });
    }
}
