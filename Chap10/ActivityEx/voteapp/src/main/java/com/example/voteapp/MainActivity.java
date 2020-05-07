package com.example.voteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView ivPic[] = new ImageView[9];
    Integer ivPicID[] = {R.id.ivPic1,R.id.ivPic2,R.id.ivPic3,
                         R.id.ivPic4,R.id.ivPic5,R.id.ivPic6,
                         R.id.ivPic7,R.id.ivPic8,R.id.ivPic9 };
    int voteCount[] = new int[9];
    Button btnResult;
    String imgName[] = {"독서하는 소녀","꽃장식 모자 소녀","부채를 든 소녀",
                        "미래느낌 단 베르암","잠자는 소녀","테리스의 주 자녀",
                        "피아노레슨","피아노 앞의 소녀들","해변에서"};
    Button btnResult2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        ActionBar bar = getSupportActionBar();
        bar.setTitle("명화 선호도 투표");
        bar.setIcon(R.drawable.pici_icon);
        bar.setDisplayShowHomeEnabled(true);


        for(int i=0; i<ivPicID.length; i++) {
            ivPic[i] = (ImageView)findViewById(ivPicID[i]);
        }
        btnResult = (Button)findViewById(R.id.btnResult);
        btnResult2 = (Button)findViewById(R.id.btnResult2);

        //ClickListener9개
        for(int i=0; i<ivPicID.length; i++) {
            final int index;
            index = i;
            ivPic[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    voteCount[index]++;
                    Toast.makeText(MainActivity.this, imgName[index]+" : 총"+voteCount[index]+"표", Toast.LENGTH_SHORT).show();
                }
            });
        }

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(),VoteResultActivity.class);

                //무언가를 담아서 보낸것이다.
                //mIntent.putExtra("담을변수",값);
                mIntent.putExtra("VoteCount",voteCount);        //voteCount는 배열이더라도 변수명만 쓰면 알아서 배열로 알아본다.
                mIntent.putExtra("ImageName",imgName);
                startActivity(mIntent);
            }
        });
        btnResult2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(),ViewFlipperActivity.class); //여기만 바뀜

                //무언가를 담아서 보낸것이다.
                //mIntent.putExtra("담을변수",값);
                mIntent.putExtra("VoteCount",voteCount);        //voteCount는 배열이더라도 변수명만 쓰면 알아서 배열로 알아본다.
                mIntent.putExtra("ImageName",imgName);
                startActivity(mIntent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();

    }


}
