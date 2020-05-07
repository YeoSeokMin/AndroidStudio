package com.example.viewflipper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    Button btnPrev,btnNext;
    ViewFlipper vFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPrev = this.findViewById(R.id.Prev);
        btnNext = this.findViewById(R.id.Next);
        vFlipper = this.findViewById(R.id.vFlipper1);
        vFlipper.setFlipInterval(2000);

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vFlipper.startFlipping();

                Toast.makeText(MainActivity.this, "사진보기 시작", Toast.LENGTH_SHORT).show();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vFlipper.stopFlipping();
                Toast.makeText(MainActivity.this, "사진보기 정지", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
