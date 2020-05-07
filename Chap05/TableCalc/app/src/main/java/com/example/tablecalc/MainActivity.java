package com.example.tablecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtNum1, edtNum2;

    Button btn[] = new Button[10];
    Button btnAdd,btnSub,btnMul,btnDiv;
    TextView tvResult;
    String strNum1, strNum2;
    int result;
    Integer btnID[] = {R.id.btn0, R.id.btn1, R.id.btn3,R.id.btn4,
                        R.id.btn5, R.id.btn6, R.id.btn7,R.id.btn8,R.id.btn9 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNum1 = (EditText)findViewById(R.id.edtNum1);
        edtNum2 = (EditText)findViewById(R.id.edtNum2);

//        final index;
//        index = i;
        for(int i=0; i<btnID.length; i++) {
            btn[i] = (Button)findViewById(btnID[i]);
        }
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnSub = (Button)findViewById(R.id.btnSub);
        btnMul = (Button)findViewById(R.id.btnMul);
        btnDiv = (Button)findViewById(R.id.btnDiv);

        tvResult = (TextView)findViewById(R.id.tvResult);


        for(int i=0; i<btnID.length; i++) {
            final int index;
            index = i;

            btn[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(edtNum1.isFocused() == true) {
                        strNum1 = edtNum1.getText().toString()+btn[index].getText().toString();
                        edtNum1.setText(strNum1);
                    } else if (edtNum2.isFocused() == true) {
                        strNum2 = edtNum2.getText().toString()+btn[index].getText().toString();
                        edtNum2.setText(strNum2);
                    } else {
                        Toast.makeText(getApplicationContext(), "먼저 숫자입력난을 선택해 주세요", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    strNum1 = edtNum1.getText().toString();
                    strNum2 = edtNum2.getText().toString();

                    result = Integer.parseInt(strNum1) + Integer.parseInt(strNum2);
                    tvResult.setText("계산결과 :"+result);
                }
            });
            btnSub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    strNum1 = edtNum1.getText().toString();
                    strNum2 = edtNum2.getText().toString();

                    result = Integer.parseInt(strNum1) - Integer.parseInt(strNum2);
                    tvResult.setText("계산결과 :"+result);
                }
            });
            btnMul.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    strNum1 = edtNum1.getText().toString();
                    strNum2 = edtNum2.getText().toString();

                    result = Integer.parseInt(strNum1) * Integer.parseInt(strNum2);
                    tvResult.setText("계산결과 :"+result);
                }
            });
            btnDiv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    strNum1 = edtNum1.getText().toString();
                    strNum2 = edtNum2.getText().toString();

                    result = Integer.parseInt(strNum1) / Integer.parseInt(strNum2);
                    tvResult.setText("계산결과 :"+result);
                }
            });
        }

    }
}
