package com.example.simplecalc;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etNum1,etNum2;
    Button btnAdd,btnSub,btnMul,btnDiv,btnResult;
    TextView tvResult;
    String num1,num2;
    double result;
    Integer result2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar bar = getSupportActionBar();
        bar.setTitle("초간단 계산기");
        bar.setIcon(R.drawable.android);
        bar.setDisplayShowHomeEnabled(true);

        etNum1 = (EditText)findViewById(R.id.etNum1);
        etNum2 = (EditText)findViewById(R.id.etNum2);

        btnAdd = this.findViewById(R.id.btnAdd);
        btnSub = this.findViewById(R.id.btnSub);
        btnMul = this.findViewById(R.id.btnMul);
        btnDiv = this.findViewById(R.id.btnDiv);
        btnResult = this.findViewById(R.id.btnResult);

        tvResult = this.findViewById(R.id.tvResult);

        /*
        btnAdd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                num1 = etNum1.getText().toString();
                num2 = etNum2.getText().toString();
                result = Integer.parseInt(num1) + Integer.parseInt(num2);

                tvResult.setText("계산 결과 : "+result);

                return false;
            }
        });
        */

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = etNum1.getText().toString();
                num2 = etNum2.getText().toString();
                if(num1.trim().equals("") || num2.trim().equals("")) {
                    showToast("입력값이 비었습니다.");
                } else {
                    result = Double.parseDouble(num1) + Double.parseDouble(num2);
                    result = (int)(result*10)/10.0;
                    tvResult.setText("계산 결과 : "+result);
                }

                //Integer라고 하면
                //tvResult.setText("계산 결과 : "+result.toString());
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = etNum1.getText().toString();
                num2 = etNum2.getText().toString();
                if(num1.trim().equals("") || num2.trim().equals("")) {
                    showToast("입력값이 비었습니다.");
                } else {
                    result = Double.parseDouble(num1) - Double.parseDouble(num2);
                    result = (int)(result*10)/10.0;
                    tvResult.setText("계산 결과 : " + result);
                }
            }
        });
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = etNum1.getText().toString();
                num2 = etNum2.getText().toString();
                if(num1.trim().equals("") || num2.trim().equals("")) {
                    showToast("입력값이 비었습니다.");
                } else {
                    result = Double.parseDouble(num1) * Double.parseDouble(num2);
                    result = (int)(result*10)/10.0;
                    tvResult.setText("계산 결과 : " + result);
                }
            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = etNum1.getText().toString();
                num2 = etNum2.getText().toString();
                if(num1.trim().equals("") || num2.trim().equals("")) {
                    showToast("입력값이 비었습니다.");
                } else {
                    try {
                        result = Double.parseDouble(num1) / Double.parseDouble(num2);
                        result = (int)(result*10)/10.0;
                        result = (int)(result*10)/10.0;
                        tvResult.setText("계산 결과 : " + result);
                    } catch(java.lang.ArithmeticException e) {
                        //showToast(e.getMessage());
                        showToast("0으로 나누면 안됩니다.");
                    } catch (Exception e) {
                        showToast("개발자에게 문의바랍니다.");
                    }
                }
            }
        });
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = etNum1.getText().toString();
                num2 = etNum2.getText().toString();
                if(num1.trim().equals("") || num2.trim().equals("")) {
                    showToast("입력값이 비었습니다.");
                } else {
                    try {
                        result = Double.parseDouble(num1) % Double.parseDouble(num2);
                        result = (int)(result*10)/10.0;
                        tvResult.setText("계산 결과 : " + result);
                    } catch(java.lang.ArithmeticException e) {
                        showToast("0으로 나누면 안됩니다.");
                    } catch(Exception e) {
                        showToast("개발자에게 문의바랍니다.");
                    }
                }
            }
        });

    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
