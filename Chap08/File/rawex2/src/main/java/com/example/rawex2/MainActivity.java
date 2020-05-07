package com.example.rawex2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    TextView tvTitle, tvAnswer;
    Button btnResult;
    //RadioButton rdoBody,rdoHead,rdoTooth,rdoWash;

    RadioButton rdoShower[] = new RadioButton[4];
    Integer rdoShowerID[] = {R.id.rdoHead,R.id.rdoWash,R.id.rdoBody,R.id.rdoTooth};

    ImageView ivShow;
    Integer ivShowID[] =    {R.drawable.head,R.drawable.wash,R.drawable.body,R.drawable.tooth};


    String test[];
    String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("심리테스트");

        tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvAnswer = (TextView)findViewById(R.id.tvAnswer);

        for(int i=0; i<rdoShower.length;i++) {
            rdoShower[i] = (RadioButton)findViewById(rdoShowerID[i]);
        }

        ivShow = (ImageView)findViewById(R.id.ivShow);
        btnResult = (Button)findViewById(R.id.btnResult);

        InputStream inputS = getResources().openRawResource(R.raw.ptest);
        try {
            byte[] txt = new byte[inputS.available()];
            inputS.read(txt);
            String str = new String(txt);
            test = str.split("#");
            inputS.close();
            tvTitle.setText(test[0]);
        } catch(IOException e) {
            Toast.makeText(this, "파일 no", Toast.LENGTH_SHORT).show();

        }

        for(int i=1; i<test.length; i++) {      //0번째방에 타이틀을 넣었으니까 1부터 시작함.

            final int index;
            index = i;

            rdoShower[index-1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ivShow.setImageResource(ivShowID[index-1]);
                    answer = test[index];
                }
            });
        }

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer.equals("")) {
                    Toast.makeText(MainActivity.this, "심리테스트 설문항목을 선택해 주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    tvAnswer.setText(answer);
                }
            }
        });
    }
}
