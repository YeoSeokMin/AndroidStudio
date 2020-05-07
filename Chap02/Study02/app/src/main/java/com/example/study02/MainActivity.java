package com.example.study02;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtInput;
    Button btnTxtShow,btnToast,btnTest;
    ImageButton imgBtnTest;

    RadioButton rdoOreo, rdoPie, rdoLeft,rdoRight;
    ImageView ivAndroid, ivOleo;
    String homeStr,sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar bar = getSupportActionBar();
        bar.setIcon(R.drawable.appleon);
        bar.setTitle("버튼을 배우자");
        bar.setDisplayShowHomeEnabled(true);


        edtInput = (EditText)findViewById(R.id.edtInput);
        btnTxtShow = (Button)findViewById(R.id.btnTxtShow);
        btnToast = (Button)findViewById(R.id.btnToast);

        rdoOreo = (RadioButton)findViewById(R.id.rdoOreo);
        rdoPie = (RadioButton)findViewById(R.id.rdoPie);
        rdoLeft = (RadioButton)findViewById(R.id.rdoLeft);
        rdoRight = (RadioButton)findViewById(R.id.rdoRight);
        ivAndroid = (ImageView) findViewById(R.id.ivAndroid);
        ivOleo = (ImageView)findViewById(R.id.ivOleo);

        imgBtnTest = (ImageButton) findViewById(R.id.imgBtnTest);
        imgBtnTest.setBackgroundResource(R.drawable.appleon);

        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(getApplicationContext(), edtInput.getText().toString(), Toast.LENGTH_SHORT).show(); //장소, 글자, 시간
                Toast.makeText(getApplicationContext(),edtInput.getText().toString(), Toast.LENGTH_SHORT).show(); //장소, 글자, 시간
//                Toast.makeText(getApplicationContext(),"결과="+num, Toast.LENGTH_SHORT).show(); //장소, 글자, 시간(잠깐보여줄까, 길게보여줄까)
                //Toast는 문자만 받는다.(toString())
            }
        });

        //LongClick
//        btnTxtShow.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Toast.makeText(getApplicationContext(), edtInput.getText(), Toast.LENGTH_SHORT).show(); //장소, 글자, 시간
//
//                return false;
//            }
//        });

        btnTxtShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeStr = edtInput.getText().toString();
                sub = homeStr.substring(0,7);
                if(!sub.equals("http://")) {
                  homeStr = "http://"+ homeStr;
                }

                //Activity를 통채로 연다(open)
                Intent mintent = new Intent(Intent.ACTION_VIEW, Uri.parse(homeStr));
                startActivity(mintent);
            }
        });

        imgBtnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgBtnTest.setBackgroundResource(R.drawable.appleoff);
            }
        });

        rdoOreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivAndroid.setImageResource(R.drawable.android);
            }
        });

        rdoPie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivAndroid.setImageResource(R.drawable.oleo);
            }
        });

        rdoLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivAndroid.setImageResource(R.drawable.android);
            }
        });

        rdoRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivAndroid.setImageResource(R.drawable.oleo);
            }
        });

    }
}
