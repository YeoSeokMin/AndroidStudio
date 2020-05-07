package com.example.chap04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnImgView,btnIvPicture,btnBmicheck;
    Button btnViewPager;
    Button btnExam04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnImgView = (Button)findViewById(R.id.btnImgView);
        btnIvPicture = this.findViewById(R.id.btnIvPicture);
        btnBmicheck = this.findViewById(R.id.btnBmicheck);
        btnViewPager = this.findViewById(R.id.btnViewPager);

        btnExam04 = this.findViewById(R.id.btnExam04);

        btnImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ImageViewActivity.class);
                startActivity(intent);
            }
        });
        btnIvPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ImageViewPictureActivity.class);
                startActivity(intent);
            }
        });
        btnBmicheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BmiCheckActivity.class);
                startActivity(intent);
            }
        });

        btnViewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ViewPagerActivity.class);
                startActivity(intent);
            }
        });

        btnExam04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Exam04Activity.class);
                startActivity(intent);
            }
        });

    }
}
