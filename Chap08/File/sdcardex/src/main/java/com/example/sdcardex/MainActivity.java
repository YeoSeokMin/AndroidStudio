package com.example.sdcardex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.pm.ActivityInfoCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button btnSDcard,btnMkdir,btnDelete;
    //ImageView
    TextView tvResult;
    EditText edtResult;

    String sdPath;          //

    File myFolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSDcard = (Button)findViewById(R.id.btnSDcard);
        btnMkdir = this.findViewById(R.id.btnMkdir);
        btnDelete = this.findViewById(R.id.btnDelete);
        //tvResult = (TextView)findViewById(R.id.tvResult);
        edtResult = this.findViewById(R.id.edtResult);

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
        }


        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);
        //외부장치경로/절대경로
        //자기폰의 sdcard를 가져온다.

        sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        Toast.makeText(this, sdPath, Toast.LENGTH_SHORT).show();
        Log.d("MyApp",sdPath);
        myFolder = new File(sdPath+"/study");

        btnSDcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                1. SD카드라하더라고 권한을 가져와야 한다.
                    - Manifest.xml(전체 app에 관한 권한(permission), 정보에 대한 것을 가지고 있다.

                2.
                 */

                //접근방식이 다 다름(3가지) 이건 3번째 저장공간
                //SD카드 있는 분
                //FileInputStream fileis = new FileInputStream("/sdcard/3732-3633/Download/news.txt");
                //Emulater
                //FileInputStream fileis = new FileInputStream("/sdcard/news.txt");
                try {
                    FileInputStream fileis = new FileInputStream(sdPath+"/news.txt");
                    //FileInputStream fileis = new FileInputStream("/sdcard/news.txt");
                    byte[] txt = new byte[fileis.available()];
                    fileis.read(txt);
                    //tvResult.setText(new String(txt));
                    edtResult.setText(new String(txt));
                    fileis.close();

                } catch (IOException e) {
                    Toast.makeText(MainActivity.this, "파일을 읽을 수가 없습니다.", Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnMkdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myFolder.mkdir();
                Toast.makeText(MainActivity.this, sdPath+"에 폴더가 생성되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myFolder.delete();
                Toast.makeText(MainActivity.this, sdPath+"에 폴더가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
