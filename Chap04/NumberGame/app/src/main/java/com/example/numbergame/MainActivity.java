package com.example.numbergame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText edtNum;
    Button btnRandomNum,btnVerifyNum;
    TextView tvMsg;
    ImageView ivGame;
    int randomNum,edtNumber;
    String imsi;

    Random rand = new Random();
    int count =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNum = (EditText)findViewById(R.id.edtNum);
        btnRandomNum = (Button)findViewById(R.id.btnRandomNum);
        btnVerifyNum = (Button)findViewById(R.id.btnVerifyNum);
        tvMsg = (TextView) findViewById(R.id.tvMsg);
        ivGame = (ImageView)findViewById(R.id.ivGame);

        btnRandomNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                randomNum = rand.nextInt(100)+1;
                Toast.makeText(MainActivity.this, Integer.toString(randomNum), Toast.LENGTH_SHORT).show();
                btnRandomNum.setEnabled(false);
                tvMsg.setText("자!게임이 시작되었습니다.");
                btnVerifyNum.setEnabled(true);
            }
        });

        btnVerifyNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, edtNum.getText(), Toast.LENGTH_SHORT).show();
                edtNumber = Integer.parseInt(edtNum.getText().toString());
                ++count;

                if(edtNumber<1 || edtNumber>100) {
                    tvMsg.setText("숫자는 1과 100사이의 숫자만 가능합니다.");
                    edtNum.setText("");
                    return;
                }

                if(edtNumber > randomNum) {
                    tvMsg.setText("숫자가 큽니다.("+count+")번 실패");
                    ivGame.setImageResource(R.drawable.fail);
                } else if (edtNumber < randomNum) {
                    tvMsg.setText("숫자가 작아요("+count+")번 실패");
                    ivGame.setImageResource(R.drawable.fail);
                } else if (edtNumber == randomNum) {
                    tvMsg.setText(count+"번만에 정답");
                    ivGame.setImageResource(R.drawable.happy);
                    btnRandomNum.setEnabled(true);
                    btnVerifyNum.setEnabled(false);
                }
                edtNum.setText("");

            }
        });

    }
}
