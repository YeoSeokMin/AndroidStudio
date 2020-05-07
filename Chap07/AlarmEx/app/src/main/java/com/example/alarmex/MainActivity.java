package com.example.alarmex;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    Context context;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.context = this;

        //알림매니저 설정
        alarm_manager = (AlarmManager)getSystemService(ALARM_SERVICE);

        //타임피커 설정
        alarm_timepicker = findViewById(R.id.time_picker);

        //Calendar 객체 설정
        final Calendar calendar = Calendar.getInstance();

        //알림리시버 intent설정
        final Intent my_intent = new Intent(this.context,Alarm_Receiver.class);

        //알림시작버튼
        Button alarm_on = findViewById(R.id.btnStart);
        alarm_on.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                calendar.set(Calendar.HOUR_OF_DAY,alarm_timepicker.getHour());
                calendar.set(Calendar.MINUTE,alarm_timepicker.getMinute());

                //시간가져옴.
                int hour = alarm_timepicker.getHour();
                int minute = alarm_timepicker.getMinute();

                Toast.makeText(context, "Alarm예정"+hour+"시"+0., Toast.LENGTH_SHORT).show();

                //receiver에 string값 넘기기
                my_intent.putExtra("state","alarm on");

                pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, my_intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

                // 알람셋팅
                alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        pendingIntent);

            }
        });

        // 알람 정지 버튼
        Button alarm_off = findViewById(R.id.btnFinish);
        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Alarm 종료",Toast.LENGTH_SHORT).show();
                // 알람매니저 취소
                alarm_manager.cancel(pendingIntent);

                my_intent.putExtra("state","alarm off");

                // 알람취소
                sendBroadcast(my_intent);
            }
        });


    }
}
