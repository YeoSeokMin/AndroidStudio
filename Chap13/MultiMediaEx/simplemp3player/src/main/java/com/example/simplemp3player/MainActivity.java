package com.example.simplemp3player;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //노래를 sd카드에 저장해서 프로젝트를 성공시킨다.

    String sdcardPath, selectMp3;
    MediaPlayer mp;
    ArrayList<String> mp3List;


    ListView listViewmp3;
    Button btnPlay, btnStop,btnPause;
    TextView tvmp3;
    ProgressBar pb1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewmp3 = (ListView) findViewById(R.id.listViewmp3);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnPause = (Button)findViewById(R.id.btnPause);
        tvmp3 = (TextView) findViewById(R.id.tvmp3);
        pb1 = (ProgressBar) findViewById(R.id.pb1);

        sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/music";
        //showToast(sdcardPath);

        //2번 체크한다. 바로 꺼지니까 여기서 해줘야 함.
        int pCheck = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        //check안되어 있으면.

        if (pCheck == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
        } else {
            sdCardProcess();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,mp3List);
        listViewmp3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listViewmp3.setAdapter(adapter);
        listViewmp3.setItemChecked(0,true);
        selectMp3 = mp3List.get(0);





        listViewmp3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectMp3 = mp3List.get(position);
            }
        });
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mp = new MediaPlayer();
                    mp.setDataSource(sdcardPath+"/"+selectMp3);
                    mp.prepare();
                    mp.start();
                    btnStop.setEnabled(true);
                    btnPause.setEnabled(true);
                    btnPlay.setEnabled(false);
                    tvmp3.setText("실행중인 음악:"+selectMp3);
                    pb1.setVisibility(v.VISIBLE);
                    //btnStop.setVisibility(v.INVISIBLE);


                } catch (IOException e) {
                    showToast("재생할 수 없는 음악파일입니다.");
                }
            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnPause.getText().toString().equals("일시정지")) {
                    mp.pause();
                    //tvmp3.setText("실행중인 음악 : "+selectMp3);
                    pb1.setVisibility(View.INVISIBLE);
                    btnPause.setText("이어듣기");
                } else {
                    mp.start();
                    pb1.setVisibility(View.VISIBLE);
                    btnPause.setText("일시정지");

                }


            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                mp.reset();
                tvmp3.setText("재생중인 음악이 없습니다.");
                pb1.setVisibility(View.INVISIBLE);

                btnPlay.setEnabled(true);
                btnPause.setEnabled(false);
                btnStop.setEnabled(false);
                //tvmp3.setText("실행중인 음악:");

                //btnStop.setVisibility(v.INVISIBLE);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    void sdCardProcess() {
        sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Music";
        //showToast(sdcardPath);
        mp3List = new ArrayList<String>();
        File listFiles[] = new File(sdcardPath).listFiles();        //선택한 경로에 있는 파일목록
        String fileName, extName;

        for(File file:listFiles) {
            fileName = file.getName();
            extName = fileName.substring(fileName.length()-3);
            if(extName.equals("mp3")) {
                mp3List.add(fileName);
            }
        }




    }

    void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
