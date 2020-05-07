package com.example.mybmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText edtHeight,edtWeight;
    Button btnBMI;
    TextView txtView;
    ImageView imgView;

    String height,weight;
    Double result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("비만도 체크");

        edtHeight = (EditText)findViewById(R.id.edtHeight);
        edtWeight = (EditText)findViewById(R.id.edtWeight);
        Button btnBMI = (Button)findViewById(R.id.btnBMI);
        TextView txtView = (TextView)findViewById(R.id.txtView);
        final ImageView imgView = (ImageView)findViewById(R.id.imgView);

        btnBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                height = edtHeight.getText().toString();
                weight = edtWeight.getText().toString();
                result = (Double.parseDouble(height)-Double.parseDouble(weight))*0.9;

                if((result+5) <= Double.parseDouble(weight)) {
                    Toast.makeText(getApplicationContext(), "비만", Toast.LENGTH_SHORT).show();
                    imgView.setImageResource(R.drawable.fail);

                } else if (Double.parseDouble(weight) <= (result-5)) {
                    Toast.makeText(getApplicationContext(), "마른체형", Toast.LENGTH_SHORT).show();
                    imgView.setImageResource(R.drawable.thin);
                } else {
                    Toast.makeText(getApplicationContext(), "정상", Toast.LENGTH_SHORT).show();
                    imgView.setImageResource(R.drawable.happy);
                }
            }
        });

        //170,60
        //정상몸무게(170-90)*0.9;
        //정상몸무게+5,정상몸무게-5 사이면 정상, 적으면 마른체형 많으면 비만, 꾸준히 유지하세요.






    }
}
