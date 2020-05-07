package com.example.fourbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnNaver,btn119,btnGall,btnEnd

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNaver = (Button)findViewById(R.id.btnNaver);
        btn119 = (Button)findViewById(R.id.btn119);
        btnGall = (Button)findViewById(R.id.btnGallery);
        btnEnd = (Button)findViewById(R.id.btnEnd);

        btnNaver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mintent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.naver.com"));
                startActivities(mintent);
            }
        });

        btn119.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent makeintent = new Intent(Intent.ACTION_VIEW,Uri.parse("tel:/119\"));
                startActivities(makeintent);
            }
        });

        btnGall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent makeintent = new Intent();
                makeintent.setAction(Intent.ACTION_GET_CONTENT);
                makeintent.setType("image/*");
                startActivityForResult(makeintent,0);
            }
        });

        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }

    }

}
