package com.example.ipintent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.speech.tts.Voice;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView ivPet;
    TextView tvPetName;
    Integer imgID[]={R.drawable.cat, R.drawable.dog, R.drawable.rabbit, R.drawable.mon, R.drawable.horse};
    String petName[] = {"고양이", "강아지", "토끼", "원숭이", "말"};
    boolean sdCheck;
    String sdPath;
    File filePath;
    int reqWidth;
    int reqHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnDial = this.findViewById(R.id.btnDial);
        Button btnWeb = this.findViewById(R.id.btnWeb);
        Button btnGoogle = this.findViewById(R.id.btnGoogle);
        Button btnSearch = this.findViewById(R.id.btnSearch);
        Button btnSms = this.findViewById(R.id.btnSms);
        Button btnPhoto = this.findViewById(R.id.btnPhoto);
        Button btnVoice = this.findViewById(R.id.btnVoice);
        Button btnImageSave = this.findViewById(R.id.btnImageSave);

        reqHeight=getResources().getDimensionPixelSize(R.dimen.);
        int pCheck = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (pCheck == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
            sdCheck=false;
        } else {
            sdCheck=true;
        }


        ivPet = this.findViewById(R.id.ivPet);
        tvPetName = this.findViewById(R.id.tvPetName);

        btnImageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:01041293716");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });

        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.fact119.com/a2/st13/yeo/portfolio.html");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://maps.google.com/maps?q="+37.450443+","+127.139718);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY,"안드로이드");
                startActivity(intent);
            }
        });

        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.putExtra("sms_body","안녕하세요?");
                intent.setData(Uri.parse("smsto:"+Uri.encode("010-4129-3716")));
                startActivity(intent);
            }
        });

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,30);
            }
        });

        btnVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"원하는 것을 말해보세요");
                startActivityForResult(intent,50);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (sdCheck==true){
            try{
                sdPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/myPhoto";
                File dir = new File(sdPath);
                if(!dir.exists()){
                    dir.mkdir();
                }
                filePath=File.createTempFile("IMG",".jpg",dir);
                if(filePath.exists()){
                    filePath.createNewFile();
                    Uri photoURI= FileProvider.getUriForFile(MainActivity.this,BuildConfig.APPLICATION_ID+".provider",filePath);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,photoURI);
                    startActivityForResult(intent,40);
                }
            }catch (Exception e){
                Toast.makeText(getApplicationContext(),"접근 권한이 없어 SD카드를 사용할 수 없습니다.",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),"접근 권한이 없어 SD카드를 사용할 수 없습니다.",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode==RESULT_OK && requestCode==50){
//            ArrayList<String> result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//            String revdata = result.get(0);
//            Uri uri = Uri.parse("https://terms.naver.com/search.nhn?query="+revdata+"&searchType=&dicType=&subject=");
//            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//            startActivity(intent);
            if(resultCode==RESULT_OK && requestCode==50){
                //resultCode != requestCode (조심)
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                String revdata = result.get(0);

                int i;

                for(i=0; i<petName.length; i++){
                    if(petName[i].equals(revdata)) {
                        ivPet.setImageResource(imgID[i]);
                        tvPetName.setText(imgID[i]+"의 사진입니다.");
                        break;
                    }
                }
                if (i==imgID.length){
                    ivPet.setImageResource(R.drawable.sad);
                    tvPetName.setText("준비한 자료가 없습니다.");
                } else if (resultCode==RESULT_OK && requestCode==30){
                    Bitmap bm = (Bitmap)data.getExtras().get("data");
                    ivPet.setImageBitmap(bm);
                } else if(resultCode==RESULT_OK && requestCode==40){
                    if(filePath != null){
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds=true;
                        try {
                            InputStream inputS = new FileInputStream(filePath);
                            BitmapFactory.decodeStream(inputS,null,options);
                            inputS.close();
                            inputS = null;
                        } catch (Exception e) {

                        }
                        final int height = options.outHeight;
                        final int width = options.outWidth;
                        int inSize=1;
                        if(height>reqHeight || width>reqWidth){
                            final int heightRatio=Math.round((float)height/(float)reqHeight);
                            final int WidthRatio=Math.round((float)width/(float)reqWidth);
                            inSize=heightRatio<widthRatio ? heightRatio:widthRatio;
                        }
                        BitmapFactory.Options imgOptions=new BitmapFactory.Options();
                        imgOptions.inSampleSize=inSize;
                        Bitmap bm = BitmapFactory.decodeFile(filePath.getAbsolutePath(),imgOptions);
                        ivPet.setImageBitmap(bm);
                    }
                }
//                if(revdata.equals("토끼")){
//                    ivPet.setImageResource(R.drawable.rabbit);
//                    tvPetName.setText("토끼입니다.");
//                } else if (revdata.equals("고양이")){
//                    ivPet.setImageResource(R.drawable.cat);
//                    tvPetName.setText("고양이입니다.");
//                } else if (revdata.equals("말")){
//                    ivPet.setImageResource(R.drawable.horse);
//                    tvPetName.setText("말입니다.");
//                } else if (revdata.equals("원숭이")){
//                    ivPet.setImageResource(R.drawable.mon);
//                    tvPetName.setText("원숭이입니다.");
//                } else if(revdata.equals("강아지")){
//                    ivPet.setImageResource(R.drawable.dog);
//                    tvPetName.setText("강아지입니다.");
//                } else {
//                    ivPet.setImageResource(R.drawable.sad);
//                    tvPetName.setText("정보가 없습니다ㅠㅠ.");
//                }

        }
    }
}
