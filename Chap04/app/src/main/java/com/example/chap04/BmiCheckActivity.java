package com.example.chap04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BmiCheckActivity extends AppCompatActivity {

    EditText edtHeight,edtWeight;
    Button btnBMI;
    TextView txtView;
    ImageView imgView;

    Double height,weight;
    int sWeight;
    Double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_check);

        setTitle("비만도 체크");



        edtHeight = (EditText)findViewById(R.id.edtHeight);
        edtWeight = (EditText)findViewById(R.id.edtWeight);
        Button btnBMI = (Button)findViewById(R.id.btnBMI);
        TextView txtView = (TextView)findViewById(R.id.txtView);
        final ImageView imgView = (ImageView)findViewById(R.id.imgView);

        btnBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                height = Double.parseDouble(edtHeight.getText().toString());
                weight = Double.parseDouble(edtWeight.getText().toString());
                result = (height-weight)*0.9;
                sWeight = (int)((height-100)*0.9);

                if(weight >= (result+5)) {
                    Toast.makeText(getApplicationContext(), "비만", Toast.LENGTH_SHORT).show();
                    imgView.setImageResource(R.drawable.fail);

                } else if (weight <= (result-5)) {
                    Toast.makeText(getApplicationContext(), "정상", Toast.LENGTH_SHORT).show();
                    imgView.setImageResource(R.drawable.happy);
                } else {
                    Toast.makeText(getApplicationContext(), "비정상", Toast.LENGTH_SHORT).show();
                    imgView.setImageResource(R.drawable.thin);
                }
            }
        });

        //170,60
        //정상몸무게(170-100)*0.9;
        //정상몸무게+5,정상몸무게-5 사이면 정상, 적으면 마른체형 많으면 비만, 꾸준히 유지하세요.


    }
}
