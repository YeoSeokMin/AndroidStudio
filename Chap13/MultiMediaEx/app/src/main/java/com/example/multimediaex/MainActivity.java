package com.example.multimediaex;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    

    Switch swPlay;
    //음악기능클래스
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swPlay = (Switch)findViewById(R.id.swPlay);
        mp = MediaPlayer.create(this,R.raw.da);

        swPlay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //3가지 방법이 있다.

                //3.1
                if (swPlay.isChecked()==true) {
                    mp.start();
                } else {
                    mp.stop();
                }
                /*
                3.2
                if(swPlay.isChecked()) {

                }
                3.3
                if(isChecked) {

                }
                */

            }
        });
    }
}
