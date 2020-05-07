package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.stream.DoubleStream;

public class MainActivity extends AppCompatActivity {

    Button btnNoti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* 캐스팅 시작 */
        btnNoti = findViewById(R.id.btnNoti);
        final NotificationManager notiManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT>=26){ //버전26 = 오레오(Oreo)
            notiManager.createNotificationChannel(new NotificationChannel("gongji","ch",NotificationManager.IMPORTANCE_DEFAULT));
            //채널 생성


        }

        btnNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap mLargeIcon = BitmapFactory.decodeResource(getResources(),R.drawable.luffy);
                //라지 아이콘을 사용하기 위해서는 그리는방식(Bitmap)사용.

                PendingIntent pIntent = PendingIntent.getActivity(MainActivity.this,0,
                        new Intent(getApplicationContext(),MainActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);

                    NotificationCompat.Builder builder;
                if(Build.VERSION.SDK_INT>=26) { //버전26 = 오레오(Oreo)
                    builder = new NotificationCompat.Builder(MainActivity.this,"gongji");
                }else{
                    builder = new NotificationCompat.Builder(MainActivity.this);
                }
                builder.setSmallIcon(R.drawable.zoro_small)

                //오레오 이후 부터는 채널이 항상 있어야함. 이전버전은 괜찮음.

                //builder 변수를 메서드체인으로 연속 사용
                /*  메서드 체인으로 noti 설정 시작(bulider)  */
                /*builder*/.setSmallIcon(R.drawable.zoro_small)
                /*builder*/.setContentTitle("공지사항")    //제목
                /*builder*/.setContentText("올바른 손씻기와 기침예절로 바이러스를 예방합시다.")   //내용
                /*builder*/.setLargeIcon(mLargeIcon)
                /*builder*/.setDefaults(Notification.DEFAULT_SOUND) //사운드, 진동, 불빛 등으로 설정가능
                /*builder*/.setAutoCancel(true) //Noti가 사라지게함
                .setContentIntent(pIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT); //앱의 중요도(생략 가능) 로우 하이 디폴트 등 있음.
                /* noti 설정 종료 */

                notiManager.notify(0,builder.build());//noti 생성

           }
        });
    }
}
