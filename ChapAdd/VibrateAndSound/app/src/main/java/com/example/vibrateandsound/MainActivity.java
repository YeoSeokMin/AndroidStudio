package com.example.vibrateandsound;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnSound,btnVibrate,btnVibrateStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSound = (Button)findViewById(R.id.btnSound);
        btnVibrate = (Button)findViewById(R.id.btnVibrate);
        btnVibrateStop = (Button)findViewById(R.id.btnVibrateStop);

        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                기본제공사운드
                Uri noti = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                //Uri noti = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(),noti);
                ringtone.play();

                 */
                //내사운드
                MediaPlayer mp = MediaPlayer.create(MainActivity.this,R.raw.mbc9);
                mp.start();
            }
        });
        btnVibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*일반Vibrate
                Vibrator vib = (Vibrator)getSystemService(VIBRATOR_SERVICE);
                vib.vibrate(1000);*/
                Vibrator vib = (Vibrator)getSystemService(VIBRATOR_SERVICE);
                vib.vibrate(new long[] {500,1000,300,700},0);
            }
        });
        btnVibrateStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vib = (Vibrator)getSystemService(VIBRATOR_SERVICE);
                vib.cancel();
            }
        });
    }
}
