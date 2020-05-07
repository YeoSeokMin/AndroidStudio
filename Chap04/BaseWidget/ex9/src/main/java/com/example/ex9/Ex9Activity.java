package com.example.ex9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Ex9Activity extends AppCompatActivity {
    Button btnRotate;
    ImageView ivRotate;
    int turn=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex9);
        setTitle("연습문제4-9");

        btnRotate = (Button)findViewById(R.id.btnRotate);
        ivRotate = (ImageView)findViewById(R.id.ivRotate);

        /*btnRotate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ivRotate.setRotation(10);
                return false;
            }
        });*/




        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn -= 10;
                ivRotate.setRotation(turn);

            }
        });






    }
}
