package com.example.imageviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.PagerAdapter;

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
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnPrev, btnNext;
    ImageView ivPicture;

    File imgFiles[];     //SD카드에 이미지가 있는 폴더에 파일을 담을 배열;
    String imgName;     //하나의 이미지 이름;
    int num=0;          //이미지 배열의 인덱스
    String sdcardPath;
    ArrayList<File> imgList;        //동적배열

    //ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPrev = this.findViewById(R.id.btnPrev);
        btnNext = (Button)findViewById(R.id.btnNext);
        ivPicture = (ImageView)findViewById(R.id.ivPicture);
        imgList = new ArrayList<File>();

        //퍼미션정보를 int에 담는다.
        int permissioncheck = ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        //허용안한상태면...
        //if(permissioncheck != PackageManager.PERMISSION_GRANTED) {

        if(permissioncheck == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);
        } else {

            sdcardProcess();
        }

        //ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num<=0) {
                    //Toast.makeText(MainActivity.this, "첫번째 그림입니다.", Toast.LENGTH_SHORT).show();
                    num = imgList.size()-1;
                    sdcardProcess();

                    //Toast.makeText(MainActivity.this, ""+num, Toast.LENGTH_SHORT).show();
                } else {
                    num--;
                    imgName = imgList.get(num).toString();
                    Bitmap bm = BitmapFactory.decodeFile(imgName);
                    ivPicture.setImageBitmap(bm);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num>=imgList.size()-1) {
                    num=0;
                    sdcardProcess();

                    //추후에는 num=1로 셋팅하면????

                } else {
                    num++;
                    imgName = imgList.get(num).toString();
                    Bitmap bm = BitmapFactory.decodeFile(imgName);
                    ivPicture.setImageBitmap(bm);
                }

            }
        });
    }

    //Permission을 허용하면 나오는 메소드

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
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

            Log.e("MyApp",""+imgList);
        }

        imgName = imgList.get(num).toString();
        //비트맵으로 읽어와야하는데 바로 읽어오지 않고 그래픽기능의 클래스를 이용해야 함.
        Bitmap bm = BitmapFactory.decodeFile(imgName);
        ivPicture.setImageBitmap(bm);       //cf. setImageResource
    }
}
