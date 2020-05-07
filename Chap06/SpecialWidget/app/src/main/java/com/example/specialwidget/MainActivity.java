package com.example.specialwidget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Chronometer cmSchedule;
    RadioGroup rdoGroup;
    RadioButton rdoCal,rdoTime;
    FrameLayout frmLayout;
    DatePicker dpDate;
    TimePicker tmPicker;
    TextView tvResult;
    int cYear,cMonth,cDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cmSchedule = (Chronometer)findViewById(R.id.cmSchedule);
        rdoGroup = (RadioGroup)findViewById(R.id.rdoGroup);
        frmLayout = (FrameLayout)findViewById(R.id.frmLayout);
        rdoCal = (RadioButton)findViewById(R.id.rdoCal);
        rdoTime = (RadioButton)findViewById(R.id.rdoTime);
        dpDate = (DatePicker)findViewById(R.id.dpDate);
        tmPicker = (TimePicker)findViewById(R.id.tmPicker);
        tvResult = (TextView)findViewById(R.id.tvResult);


        cmSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmSchedule.setBase(SystemClock.elapsedRealtime());
                cmSchedule.start();
                cmSchedule.setTextColor(Color.RED);
                rdoGroup.setVisibility(View.VISIBLE);
            }
        });



        tvResult.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                cmSchedule.stop();
                String msg;
                msg = "예약일은 "+dpDate.getYear()+"년 "+(dpDate.getMonth()+1)+"월 "+dpDate.getDayOfMonth()+"일"+tmPicker.getCurrentHour()+"시 "+tmPicker.getCurrentMinute()+"분입니다.";
                tvResult.setText(msg);
                rdoGroup.setVisibility(View.INVISIBLE);
                frmLayout.setVisibility(View.INVISIBLE);
                cmSchedule.setTextColor(Color.BLACK);
                return false;
            }
        });

        rdoCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frmLayout.setVisibility(View.VISIBLE);

                dpDate.setVisibility(View.VISIBLE);
                tmPicker.setVisibility(View.INVISIBLE);
            }
        });

        rdoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpDate.setVisibility(View.INVISIBLE);
                tmPicker.setVisibility(View.VISIBLE);
            }
        });




    }
}
