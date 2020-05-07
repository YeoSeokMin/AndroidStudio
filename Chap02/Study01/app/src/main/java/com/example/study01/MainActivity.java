package com.example.study01;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnGo, btnCall, btnGallery,btnEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar bar = getSupportActionBar();
        bar.setTitle("네가지 버튼 기능");
        bar.setIcon(R.drawable.ic_title_bg);
        bar.setDisplayShowHomeEnabled(true);


        btnGo = (Button) findViewById(R.id.btnGo);
        btnCall = (Button) findViewById(R.id.btnCall);
        btnGallery = (Button) findViewById(R.id.btnGallery);
        btnEnd = (Button) findViewById(R.id.btnEnd);

        btnGo.setOnClickListener(new View.OnClickListener() {   //익명 클래스
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.naver.com");
                Intent mintent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(mintent);
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mintent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:/119"));
                startActivity(mintent);
            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mintent = new Intent(Intent.ACTION_GET_CONTENT);
                mintent.setType("image/*");
                startActivityForResult(mintent, 0);
            }
        });

        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
