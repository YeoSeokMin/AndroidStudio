package com.example.serviceex;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MusicService extends Service {
    MediaPlayer mp;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override //액티비티에서 명령을 받으면 수행.
    public int onStartCommand(Intent intent, int flags, int startId) {
        mp=MediaPlayer.create(this,R.raw.anysong);
        mp.setLooping(true); //노래가 끝나고 자동으로 다시 재생 정지 누를때까지
        mp.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override // 서비스가 종료될때 수행.
    public void onDestroy() {
        mp.stop();

        super.onDestroy();
    }
}
