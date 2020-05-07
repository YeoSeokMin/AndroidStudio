package com.example.basewidget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNewOpen = (Button)findViewById(R.id.btnNewOpen);
        Button btnTabhost = (Button)findViewById(R.id.btnTabhost);
        Button btn2 = (Button)findViewById(R.id.btn2);
        Button btn3 = (Button)findViewById(R.id.btn3);
        Button btn4 = (Button)findViewById(R.id.btn4);
        Button btn5 = (Button)findViewById(R.id.btn5);

        btn2.setText("누른버튼");
        //btn2.setBackgroundColor(Color.BLACK);

        btnNewOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                startActivity(intent);

            }
        });

        btnTabhost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast tMsg = Toast.makeText(getApplicationContext(),"안녕하세요",Toast.LENGTH_SHORT).show();

                        Toast.makeText(getApplicationContext(),"안녕하세요",Toast.LENGTH_SHORT).show();
                //tMsg.setGravity(10,10,20);
                //Toast.makeText(SecondActivity.class,"안녕하세요",Toast.LENGTH_SHORT).show();
                //MainActivity.this

            }
        });



    }
}
