package com.example.vibrateandsound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnSound = findViewById(R.id.btnSound);
        Button btnVibrate = findViewById(R.id.btnVibrate);

        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Uri noti = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//                Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(),noti);
//                ringtone.play();
                Context context;
                MediaPlayer mp=MediaPlayer.create(MainActivity.this,R.raw.bolt);
                mp.start();
            }
        });
        btnVibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vib = (Vibrator)getSystemService(VIBRATOR_SERVICE);
//                vib.vibrate(1000);
                vib.vibrate(new long[] {500,1000,300,700,999,111,222,333,444,555,666},-1);
            }
        });

    }
}
