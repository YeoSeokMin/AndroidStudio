package com.example.diaryex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

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
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dPicker1 = (DatePicker)findViewById(R.id.dPicker1);
        edtDiary = (EditText)findViewById(R.id.edtDiary);
        btnSave = (Button)findViewById(R.id.btnSave);

        cal = Calendar.getInstance();

        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);
        fileName = cYear+"_"+(cMonth+1)+"_"+(cDay)+".txt";

        String str = readDiary(fileName);
        edtDiary.setText(str);

        dPicker1.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                fileName = year+"_"+monthOfYear+"_"+dayOfMonth+".txt";

                String str = readDiary(fileName);
                edtDiary.setText(str);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fileos = openFileOutput(fileName, Context.MODE_PRIVATE);
                    String str = edtDiary.getText().toString();
                    fileos.write(str.getBytes());
                    fileos.close();
                    showToast("저장됨");
                } catch (IOException e) {

                }
            }
        });
    }


    String readDiary(String fileName) {
        String diaryStr = null;
        try {
            FileInputStream fileis = openFileInput(fileName);
            byte[] txt  = new byte[fileis.available()];
            fileis.read(txt);
            fileis.close();
            diaryStr = (new String(txt).trim());
            btnSave.setText("수정 하기");

        } catch (IOException e) {
            edtDiary.setHint("일기 없음");
            btnSave.setText("새로 저장");
        }
        return diaryStr;
    }

    void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
