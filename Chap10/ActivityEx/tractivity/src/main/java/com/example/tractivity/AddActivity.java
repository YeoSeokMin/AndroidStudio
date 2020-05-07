package com.example.tractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class AddActivity extends AppCompatActivity {
    Button btnReturn;
    int sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Intent gIntent = getIntent();
        //defaultValue: 값을 못받았을 경우에는 0으로 받아라.
        sum = gIntent.getIntExtra("Num1",0)+gIntent.getIntExtra("Num2",0);
        Log.i("MyApp",""+sum);
        btnReturn = (Button)findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rIntent = new Intent(getApplicationContext(),MainActivity.class);
                rIntent.putExtra("sum",sum);
                //setResult을 해야만 돌아가서 onActivityResult()로 가게됨.
                setResult(RESULT_OK,rIntent);
                finish();
            }
        });


    }
}
