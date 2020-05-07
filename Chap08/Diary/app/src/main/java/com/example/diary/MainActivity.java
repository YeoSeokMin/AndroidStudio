package com.example.diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatePicker dPicker1;
    EditText edtDiary;
    Button btnSave;

    String sdPath,fileName;
    Calendar cal;

    File myDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dPicker1 = (DatePicker)findViewById(R.id.dPicker1);
        edtDiary = (EditText) findViewById(R.id.edtDiary);
        btnSave = (Button) findViewById(R.id.btnSave);

        int pcheck = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(pcheck == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);
        else
            sdcardProcess();


        cal = Calendar.getInstance();
        final int cYear = cal.get(Calendar.YEAR);
        final int cMonth = cal.get(Calendar.MONTH);
        final int cDay = cal.get(Calendar.DAY_OF_MONTH);

        fileName = cYear+"_"+(cMonth+1)+"_"+cDay+".txt";

        String str = readDiary(sdPath+fileName);
        edtDiary.setText(str);

        dPicker1.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                fileName = year+"_"+monthOfYear+"_"+(dayOfMonth+1)+".txt";

                String str = readDiary(sdPath+fileName);
                edtDiary.setText(str);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    //FileOutputStream fileos = openFileOutput(fileName,Context.MODE_PRIVATE);
                    FileOutputStream fileos = new FileOutputStream(sdPath+fileName);
                    String str = edtDiary.getText().toString();
                    //내부에 저장할 때는 byte 단위로 저장해야 함.
                    fileos.write(str.getBytes());
                    fileos.close();

                    if(btnSave.getText().toString().equals("새로 저장")) {
                        showToast(fileName+"파일이 저장되었습니다.");
                        btnSave.setText("수정하기");
                    } else {
                        showToast(fileName+"파일이 수정되었습니다.");

                    }
                } catch (IOException e) {
                    //e.printStackTrace();
                    showToast(fileName+" 파일을 저장할 수 없습니다.");
                }
            }
        });
    }

    void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //일기를 읽어오는 method
    String readDiary(String fileName) {
        String diaryStr = null;
        try {
            //FileInputStream fileis = openFileInput(fileName);
            //sdCard니까 open이 아니라 new FileInputStream을 사용해야 함.
            FileInputStream fileis = new FileInputStream(fileName);
            byte[] txt = new byte[fileis.available()];
            fileis.read(txt);
            diaryStr = (new String(txt).trim());
            fileis.close();

            btnSave.setText("수정하기");
        } catch (IOException e) {
            //e.printStackTrace();
            edtDiary.setText("");
            edtDiary.setHint("일기 없음");
            btnSave.setText("새로 저장");
        }
        return diaryStr;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        sdcardProcess();
    }

    void sdcardProcess() {
        sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        sdPath += "/myDiary";
        myDir = new File(sdPath);
        if(!myDir.isDirectory()) {
            myDir.mkdir();
        }


    }
}
