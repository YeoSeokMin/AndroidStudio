package com.example.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    final static String TAG = "MainActivity";
    Button btnNewAct,btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNewAct = (Button)findViewById(R.id.btnNewAct);
        btnFinish = (Button)findViewById(R.id.btnFinish);
        android.util.Log.i(TAG,"onCreate메서드 수행완료");

        btnNewAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                startActivity(intent);
            }
        });
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        android.util.Log.i("테스트","onStart메서드 수행완료");
    }

    @Override
    protected void onStop() {
        super.onStop();
        android.util.Log.i("테스트","onStop메서드 수행완료");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.util.Log.i("테스트","onDestroy메서드 수행완료");
    }

    @Override
    protected void onResume() {
        super.onResume();
        android.util.Log.i("테스트","onResume메서드 수행완료");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        android.util.Log.i("테스트","onRestart메서드 수행완료");
    }

    @Override
    protected void onPause() {
        super.onPause();
        android.util.Log.i("테스트","onPause메서드 수행완료");
    }

}
