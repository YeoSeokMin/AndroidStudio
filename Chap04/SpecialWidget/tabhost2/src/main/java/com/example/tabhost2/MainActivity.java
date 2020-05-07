package com.example.tabhost2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
    TabHost tabHost;
    TabHost.TabSpec tabSpecSong,tabSpecSinger;


    Button tabSong,tabSinger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = getTabHost();

        tabSpecSong = tabHost.newTabSpec("SONG").setIndicator("음악별");
        tabSpecSong.setContent(R.id.tabSong);
        tabHost.addTab(tabSpecSong);

        tabSpecSinger = tabHost.newTabSpec("SINGER").setIndicator("가수별");
        tabSpecSinger.setContent(R.id.tabSinger);
        tabHost.addTab(tabSpecSinger);



    }
}
