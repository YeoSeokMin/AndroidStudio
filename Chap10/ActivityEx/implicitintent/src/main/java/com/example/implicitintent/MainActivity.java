package com.example.implicitintent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.util.Log;
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
    Button btnDial,btnWeb,btnGoogle,btnSearch,btnSms,btnPhoto;
    Button btnVoice;
    Button btnQ;
    ImageView ivPet;
    TextView tvPetName;
    Integer imgID[] = {R.drawable.cat,R.drawable.dog,R.drawable.rabbit,R.drawable.horse};
    String petName[] = {"고양이","강아지","토끼","말"};
    Button btnImageSave;

    boolean sdCheck;
    String sdPath;
    File filePath;
    int reqWidth;
    int reqHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("암시적 인텐트 예제");
        btnDial = (Button)findViewById(R.id.btnDial);
        btnWeb = (Button)findViewById(R.id.btnWeb);
        btnGoogle = (Button)findViewById(R.id.btnGoogle);
        btnSearch = (Button)findViewById(R.id.btnSearch);
        btnSms = (Button)findViewById(R.id.btnSms);
        btnPhoto = (Button)findViewById(R.id.btnPhoto);
        //양방향 암시적인텐트
        btnVoice = (Button)findViewById(R.id.btnVoice);
        ivPet = (ImageView)findViewById(R.id.ivPet);
        tvPetName = (TextView)findViewById(R.id.tvPetName);

        btnQ = (Button)findViewById(R.id.btnQ);
        btnImageSave = (Button)findViewById(R.id.btnImageSave);


        reqWidth = getResources().getDimensionPixelSize(R.dimen.request_image_width);
        reqHeight = getResources().getDimensionPixelSize(R.dimen.request_image_height);
        android.util.Log.i("MyApp",""+reqWidth);
        android.util.Log.i("MyApp",""+reqHeight);

        int pCheck = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (pCheck == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
            sdCheck=false;
        } else {
            sdCheck=true;
        }

        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_DIAL,Uri.parse("tel:01051043350")));
            }
        });
        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.daum.net")));
            }
        });
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://maps.google.com/maps?q="+37.554264+","+126.913598)));
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://maps.google.com/maps?q="+37.295914+","+127.150861)));
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(Intent.ACTION_WEB_SEARCH);
                //Intent mIntent = new Intent(Intent.ACT);
                mIntent.putExtra(SearchManager.QUERY,"안드로이드");
                startActivity(mIntent);
            }
        });
        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(Intent.ACTION_SENDTO);
                mIntent.putExtra("sms_body","안녕하세요");
                mIntent.setData(Uri.parse("smsto:"+Uri.encode("010-5104-3350")));
                startActivity(mIntent);
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
                Intent mIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                //마이크에 대고 뭐라고 하면 말하는 걸 담아서 보내준다.
                mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                //말하는거에 Prompt를 달 수 있다.(타이틀) ex)원하는 것을 말해주세요
                mIntent.putExtra(RecognizerIntent.EXTRA_PROMPT,"원하는 것을 말해주세요");
                //0이상을 써야한다.왜냐하면 여러가지 호출이 있을 수 있으니까~~~
                startActivityForResult(mIntent,50);
            }
        });
        btnImageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //android.util.Log.i("MyApp","sdCheck:"+sdCheck); //true;
                if (sdCheck == true) {
                    try {
                        String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/myPhoto";
                        File dir = new File(sdPath);
                        if (!dir.exists()) {
                            dir.mkdir();
                        }
                        android.util.Log.i("MyApp","dir:"+dir); //true;
                        filePath = File.createTempFile("IMG",".jpg",dir);
                        if (!filePath.exists()) {
                            filePath.createNewFile();
                        }
                        Uri photoURI = FileProvider.getUriForFile(MainActivity.this,BuildConfig.APPLICATION_ID
                                +".provider",filePath);
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT,photoURI);
                        startActivityForResult(intent,40);
                    } catch (Exception e){
                        Toast.makeText(getApplicationContext(),"SD카드에 저장 할 수 없습니다.",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"접근 권한이 없어 SD카드를 사용 할 수 없습니다",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(Intent.ACTION_ALL_APPS,Uri.parse("http://www.daum.net"));
                startActivity(mIntent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == 50) {
            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String revdata = results.get(0); //위 ArrayList<>동적배열의 0번째 배열만 가져온다.
            //음성으로 말한 글씨가 들어있다.
            int i;
            for (i=0; i<imgID.length; i++) {
                if (petName[i].equals(revdata)) {
                    ivPet.setImageResource(imgID[i]);
                    break;
                }
            }
            if(i == imgID.length) {
                Toast.makeText(getApplicationContext(),"해당 사항이 없습니다.",Toast.LENGTH_SHORT).show();
                //ivPet.setImageResoutce(R.drawable.이미지 이름);
            }
        } else if (resultCode == RESULT_OK && requestCode == 30) {
            Bitmap bm = (Bitmap)data.getExtras().get("data");
            ivPet.setImageBitmap(bm);
        } else  if (resultCode == RESULT_OK && requestCode == 40) {
            if (filePath != null) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                try {
                    InputStream input = new FileInputStream(filePath);
                    BitmapFactory.decodeStream(input,null,options);
                    input.close();
                    input = null;
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"이미지가...없....어...",Toast.LENGTH_SHORT).show();
                }
                final int height = options.outHeight;
                final int width = options.outWidth;
                int inSize = 1;
                if (height > reqHeight|| width > reqWidth) {
                    final int heightRatio = Math.round((float)height/(float)reqHeight);
                    final int widthRatio = Math.round((float)width/(float)reqWidth);
                    inSize = heightRatio < widthRatio ? heightRatio:widthRatio;
                }
                BitmapFactory.Options imgOption = new BitmapFactory.Options();
                imgOption.inSampleSize = inSize;
                Bitmap bm = BitmapFactory.decodeFile(filePath.getAbsolutePath(),imgOption);
                ivPet.setImageBitmap(bm);
            }
        }
    }

}
