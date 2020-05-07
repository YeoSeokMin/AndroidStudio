package com.example.serviceex2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

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
        startForegroundService();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mp=MediaPlayer.create(this,R.raw.anysong);
        mp.setLooping(true);
        mp.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mp.stop();

        super.onDestroy();
    }
    void startForegroundService(){
        Intent notiIntent = new Intent(this,MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this,0,notiIntent,0);
        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.noti);
        NotificationCompat.Builder builder;
        if(Build.VERSION.SDK_INT>=26){
            String channelID="musicServiceChannel";
            NotificationChannel channel = new NotificationChannel(channelID,"음악채널", NotificationManager.IMPORTANCE_DEFAULT);
            ((NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);
            builder = new NotificationCompat.Builder(this,channelID);
        }else{
            builder = new NotificationCompat.Builder(this);
        }
        builder.setSmallIcon(R.drawable.music)
                .setContent(remoteViews)
                .setContentIntent(pIntent);
        startForeground(1,builder.build());
    }
}
