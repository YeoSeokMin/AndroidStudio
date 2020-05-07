package com.example.mymemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    TextView tvFirst;
    Button btnOK;
    AlertDialog.Builder dlgDate;
    View dlgDateView,dlgTimeView;
    TextView tvDate1,tvDate2;
    DatePicker dlgDpMemo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvFirst = (TextView) findViewById(R.id.tvFirst);
        btnOK = (Button)findViewById(R.id.btnOK);

        Date date = Calendar.getInstance().getTime();

        SimpleDateFormat dfYMD = new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat dfHM = new SimpleDateFormat("HH:mm");
        final String todayY = dfYMD.format(date);
        final String todayH = dfHM.format(date);

        dlgDateView = (View)View.inflate(MainActivity.this,R.layout.dialog_time,null);
        tvDate1 = (TextView)dlgDateView.findViewById(R.id.tvDate1);
        tvDate2 = (TextView)dlgDateView.findViewById(R.id.tvDate2);

        dlgTimeView = (View)View.inflate(MainActivity.this,R.layout.dialog_time,null);
        //dlgDpMemo = (View) View.inflate(dlgTimeView,R.layout.dialog_date,null);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dlgDateView = (View)View.inflate(MainActivity.this,R.layout.dialog_time,null);
//                tvDate1 = (TextView)dlgDateView.findViewById(R.id.tvDate1);
//                tvDate2 = (TextView)dlgDateView.findViewById(R.id.tvDate2);

                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setView(dlgDateView);
                dlg.setNegativeButton("OK",null);
                dlg.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //다이얼로그죽이기;
                        //dlg.setOnDismissListener()
                    }
                });
                dlg.show();

            }
        });

        tvDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });









    }
}
