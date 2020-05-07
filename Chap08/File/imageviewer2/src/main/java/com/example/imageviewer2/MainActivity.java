package com.example.imageviewer2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

        btnPrev = this.findViewById(R.id.btnPrev);
        btnNext = (Button)findViewById(R.id.btnNext);
        ivPicture = (MyImageView) findViewById(R.id.ivPicture);
        imgList = new ArrayList<File>();
        tvNum = (TextView)findViewById(R.id.tvNum);

        //퍼미션정보를 int에 담는다.
        int permissioncheck = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        //허용안한상태면...
        //if(permissioncheck != PackageManager.PERMISSION_GRANTED) {

        if(permissioncheck == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);
            //Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
            //sdcardProcess();
        }

        //ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);

        tvNum.setText((num+1)+"/"+imgFiles.length);

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num<=0) {
                    num = imgFiles.length-1;
                } else {
                    num--;
                }
                sdcardProcess();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(num>=imgFiles.length-1) {
                    num=0;
                } else {
                    num++;
                }
                sdcardProcess();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
        sdcardProcess();
    }

    void sdcardProcess() {
        //이미지 가져와라.

        sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        //Toast.makeText(this, ""+sdcardPath, Toast.LENGTH_SHORT).show();
        imgFiles = new File(sdcardPath+"/Pictures").listFiles();
        Log.i("MyApp",""+imgFiles);

        String fileName, extName;       //파일이름과 확장자
        for(File file:imgFiles) {
            fileName = file.getName();
            extName = fileName.substring(fileName.length()-3);

            if(extName.equals("png")||extName.equals("jpg")||extName.equals("gif")) {
                imgList.add(file);
            }
        }

        imgName = imgList.get(num).toString();
        ivPicture.imagePath = imgName;
        ivPicture.invalidate();
        tvNum.setText((num+1)+"/"+imgFiles.length);
    }
}