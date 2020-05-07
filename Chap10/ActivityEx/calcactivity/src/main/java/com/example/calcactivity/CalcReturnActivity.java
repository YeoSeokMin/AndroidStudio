package com.example.calcactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class CalcReturnActivity extends AppCompatActivity {
    Button btnReturn;
    String symbol;
    double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_return);

        btnReturn = (Button)findViewById(R.id.btnReturn);

        Intent gIntent = getIntent();
        symbol = gIntent.getStringExtra("Symbol");

        Log.i("MyApp",""+gIntent.getDoubleExtra("Num1",0));
        switch (symbol) {
            case "+":
                result = gIntent.getDoubleExtra("Num1",0)+gIntent.getDoubleExtra("Num2",0);
                break;
            case "-":
                result = gIntent.getDoubleExtra("Num1",0)-gIntent.getDoubleExtra("Num2",0);
                break;
            case "*":
                result = gIntent.getDoubleExtra("Num1",0)*gIntent.getDoubleExtra("Num2",0);
                break;
            case "/":
                result = gIntent.getDoubleExtra("Num1",0)/gIntent.getDoubleExtra("Num2",0);
                break;
        }
        //Log.i("MyApp",""+result);


        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rIntent = new Intent(getApplicationContext(),MainActivity.class);
                rIntent.putExtra("result",result);
                setResult(RESULT_OK,rIntent);
                finish();

            }
        });
    }
}
