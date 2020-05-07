package com.example.chap07ex05;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnShowToast;
    Toast toast;
    View toastView;
    ImageView ivToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowToast = (Button)findViewById(R.id.btnShowToast);
        btnShowToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast = new Toast(getApplicationContext());

                toastView = (View)View.inflate(getApplicationContext(),R.layout.toast,null);
                ivToast = (ImageView)toastView.findViewById(R.id.imgView);
                ivToast.setImageResource(R.drawable.api90);

                Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
                int x = (int)(Math.random()*display.getWidth());
                int y = (int)(Math.random()*display.getHeight());

                toast.setGravity(Gravity.TOP | Gravity.LEFT,x,y);
                toast.setView(toastView);
                toast.show();
            }
        });
    }
}
