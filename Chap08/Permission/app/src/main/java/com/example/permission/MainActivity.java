package com.example.permission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnPrev, btnNext;
    MyImageView ivPicture;
    TextView tvNum;

    File imgFiles[];     //SD카드에 이미지가 있는 폴더에 파일을 담을 배열;
    String imgName;     //하나의 이미지 이름;
    int num=0;          //이미지 배열의 인덱스
    String sdcardPath;
    ArrayList<File> imgList;        //동적배열
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permissioncheck = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(permissioncheck == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);
            Log.i("MyApp","permissioncheck:"+permissioncheck);
        } else {

            Log.i("MyApp","permissioncheck:"+permissioncheck);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.i("MyApp","requestCode"+requestCode);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);



    }


}


