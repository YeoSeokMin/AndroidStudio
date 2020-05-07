package com.example.myexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button btnVibratorStart,btnVibratorStart2,btnVibratorStart3;
    Button btnVibratorStop;
    private Vibrator vibrator;
    long[] pattern = {100,1000,100,500,100};

    EditText edtAlarmResv;
    View dlgTimePickerView;
    TimePicker tPicker;

    Calendar car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        btnVibratorStart = (Button)findViewById(R.id.btnVibratorStart);
        btnVibratorStart2 = (Button)findViewById(R.id.btnVibratorStart2);
        btnVibratorStart3 = (Button)findViewById(R.id.btnVibratorStart3);
        btnVibratorStop = (Button)findViewById(R.id.btnVibratorStop);
        edtAlarmResv = (EditText)findViewById(R.id.edtAlarmResv);

        btnVibratorStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(1000);

            }
        });
        btnVibratorStart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MyExam","Vibrator Start Before");

                vibrator.vibrate(pattern,-1);
                Log.d("MyExam","Vibrator Start After");
            }
        });
        btnVibratorStart3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(new long[] {100,1000,100,500,100},0);
            }
        });

        btnVibratorStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MyExam","Vibrator Stop");
                vibrator.cancel();
            }
        });

        edtAlarmResv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlgTimePickerView = (View)View.inflate(MainActivity.this,R.layout.dlgtimepicker,null);
                tPicker = (TimePicker)dlgTimePickerView.findViewById(R.id.tPicker);

                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                //AlertDialog.Builder dlg = new AlertDialog.Builder(getApplicationContext());
                //dlg.setIcon(R.drawable.cold);
                dlg.setView(dlgTimePickerView);

                dlg.setTitle("알람설정");
                dlg.setNegativeButton("설정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int hour = tPicker.getCurrentHour();
                        int minute = tPicker.getCurrentMinute();
                        edtAlarmResv.setText(hour+"시"+minute+"분");
                        Toast.makeText(MainActivity.this, "알람이 "+hour+"시"+minute+"분에 설정되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.setPositiveButton("취소",null);

                dlg.setCancelable(false);
                //dlg.setView(dlg);

                dlg.show();
            }
        });
    }
}
