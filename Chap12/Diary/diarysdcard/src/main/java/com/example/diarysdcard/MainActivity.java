package com.example.diarysdcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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
    Calendar cal;

    String filePath,fileName;
    final String myDirName = "/myDiary";
    File fileDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("연습문제12-1");

        dPicker1 = (DatePicker)findViewById(R.id.dPicker1);
        edtDiary = (EditText)findViewById(R.id.edtDiary);
        btnSave = (Button)findViewById(R.id.btnSave);

        cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);
        fileName = cYear+"_"+(cMonth+1)+"_"+cDay+".txt";
        //Log.i("MyAppA",fileName);



        //Folder 생성
        filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        fileDir = new File(filePath+myDirName);
        isMyFolder();

        String str = readDiary(filePath+myDirName,fileName);
        edtDiary.setText(str);
        dPicker1.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                fileName = year+"_"+(monthOfYear+1)+"_"+(dayOfMonth)+".txt";
                String str = readDiary(filePath+myDirName,fileName);
                edtDiary.setText(str);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeDiary(filePath,fileName);
            }
        });
    }

    void isMyFolder() {
        filePath += myDirName;
        fileDir = new File(filePath);

        if (!fileDir.isDirectory() == true)
            fileDir.mkdir();
    }



    String readDiary(String path,String fileName) {
        String diaryStr = null;
        String pathStr = path+"/"+fileName;
        try {
            FileInputStream fileis = new FileInputStream(pathStr);
            byte[] txt = new byte[fileis.available()];
            fileis.read(txt);
            diaryStr = (new String(txt).trim());
            btnSave.setText("수정하기");
            fileis.close();
        } catch (IOException e) {
            edtDiary.setText("");
            edtDiary.setHint("일기 없음");
            btnSave.setText("새로 저장");
        }

        return diaryStr;
    }

    void writeDiary(String path,String fileName) {
        Log.i("MyApp1",path);
        Log.i("MyApp2",fileName);
        String diaryStr = null;
        String pathStr = path + "/" + fileName;
        try {
            FileOutputStream fileos  = new FileOutputStream(pathStr);
            String str = edtDiary.getText().toString();
            fileos.write(str.getBytes());
            fileos.close();
        } catch (IOException e) {
            showToast("파일을 찾을 수 없습니다.");
        }

        showToast("저장하였습니다.");
    }

    void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }



}
