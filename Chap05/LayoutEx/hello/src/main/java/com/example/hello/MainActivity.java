package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Only Java Source");

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout,params);

        //Making Button
        Button btnClick1 = new Button(this);
        btnClick1.setText("Push me");
        btnClick1.setBackgroundColor(Color.BLUE);
        layout.addView(btnClick1,params1);

        Button btnClick2 = new Button(this);
        btnClick2.setText("Don't Push me");
        btnClick1.setBackgroundColor(Color.GREEN);
        layout.addView(btnClick2,params1);

        btnClick1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Happy New Year", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
