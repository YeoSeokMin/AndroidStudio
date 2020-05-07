package com.example.actionbartabex;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    ActionBar.Tab[] petTab = new ActionBar.Tab[4];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



}
