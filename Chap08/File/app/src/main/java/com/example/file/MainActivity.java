package com.example.file;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button btnSave,btnRead;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = (Button)findViewById(R.id.btnSave);
        btnRead = (Button)findViewById(R.id.btnRead);
        tvResult = (TextView)findViewById(R.id.tvResult);

        //내장 메모리에 파일 저장
        //1.쓸 공간이 필요
        //2.파일 내임과 확장자
        //3.저장할 저장위치와 공간
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fileos = openFileOutput("test.txt", Context.MODE_PRIVATE);  //2020_01_20.txt로 할예정, 덮어씌우기(파일이름), apppend는 내용을 추가
                    String str = "이 내용이 저장됩니다.\n 감기 조심하세요.";
                    fileos.write(str.getBytes());   //byte형태로 저장
                    fileos.close();
                    showToast("test.txt가 저장되었습니다.");
                } catch (IOException e) {       //file 처리시에는 반드시 try catch를 사용해야 함.
                    //e.printStackTrace();
                    showToast("파일을 저장할 수 없습니다.");
                    //e.getMessage함수를 써라.
                }

            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fileis = openFileInput("test.txt");
                    byte txt[] = new byte[fileis.available()];  //정해진 크기만큼만 가져온다.
                    fileis.read(txt);
                    //방법1
                    String str = new String(txt);
                    //tvResult.setText(str);
                    //방법2
                    tvResult.setText(str);


                    fileis.close();
                } catch (IOException e) {
                    showToast("파일을 읽을 수가 없습니다.");
                    //e.printStackTrace();
                }
            }
        });
    }
    void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
