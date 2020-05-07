package com.example.mydiary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatePicker dPicker1;
    EditText edtDiary;
    Button btnSave;

    Calendar cal;
    String dDate;

    MyDBHelper myDBHelper;
    SQLiteDatabase sqlDB;
    Cursor cursor;
    String sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dPicker1 = (DatePicker)findViewById(R.id.dPicker1);
        edtDiary = (EditText)findViewById(R.id.edtDiary);
        btnSave = (Button)findViewById(R.id.btnSave);
        myDBHelper = new MyDBHelper(this);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        cal = Calendar.getInstance();
        //String date = sdf.format();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);
        String ccMonth = String.valueOf(cMonth);
        String ccDay = String.valueOf(cDay);
        if (String.valueOf(cMonth).length()==1) ccMonth = "0"+cMonth;
        if (String.valueOf(ccDay).length()==1) ccDay = "0"+cDay;
        dDate = cYear+"."+ccMonth+"."+ccDay;
        //Log.i("MyApp","dDate:"+dDate);

        String str = readDiary(dDate);
        //edtDiary.setText(str);

        dPicker1.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                String cmonthOfYear = String.valueOf(monthOfYear);
                String cdayOfMonth = String.valueOf(dayOfMonth);
                if (String.valueOf(monthOfYear).length()==1) cmonthOfYear = "0"+cmonthOfYear;
                if (String.valueOf(dayOfMonth).length()==1) cdayOfMonth = "0"+cdayOfMonth;

                dDate = year+"."+cmonthOfYear+"."+cdayOfMonth;
                String str = readDiary(dDate);
                //edtDiary.setText(str);
            }
        });



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myDBHelper.getWritableDatabase();
                if(btnSave.getText().toString().equals("새로 저장")) {
                    sql = "INSERT INTO myDiary VALUES('"+dDate+"','"+edtDiary.getText().toString()+"');";
                    Log.i("MyApp","if:"+sql);
                    sqlDB.execSQL(sql);
                    showToast("일기가 저장되었습니다.");

                } else {
                    sql = "UPDATE myDiary SET content = '"+edtDiary.getText().toString()+"' WHERE diaryDate = '"+dDate+"'); ";
                    Log.i("MyApp","else:"+sql);
                    sqlDB.execSQL(sql);
                    showToast("일기가 수정되었습니다.");
                }
                sqlDB.close();

                //sqlDB = myDBHelper.getWritableDatabase();

                //sql = "INSERT INTO myDiary VALUES(date,'"+edtDiary.getText().toString()+"');";
                //sql = "INSERT INTO myDiary VALUES('"+date+"')";
                //Log.i("MyApp",sql);
                //sqlDB.execSQL(sql);
                //sqlDB.close();
                //showToast("일기가 저장되었습니다.");
            }
        });

    }
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    String readDiary(String dDate) {
        String diaryStr = null;
        Toast.makeText(this, "aa", Toast.LENGTH_SHORT).show();
        sqlDB = myDBHelper.getReadableDatabase();
        sql = "SELECT * FROM myDiary WHERE diaryDate = '"+dDate+"';  ";
        Log.i("MyApp","select:"+sql);
        cursor = sqlDB.rawQuery(sql,null);

        if(cursor == null) {
            edtDiary.setHint("일기없음");
            btnSave.setText("새로 저장");
        } else if (cursor.moveToFirst()) {
            diaryStr = cursor.getString(1);
            btnSave.setText("수정하기");
        } else {
            //
            edtDiary.setHint("일기없음");
            btnSave.setText("새로 저장");

        }
        cursor.close();
        sqlDB.close();

        return diaryStr;
    }

    public class MyDBHelper extends SQLiteOpenHelper {
        public MyDBHelper(Context context) {
            super(context, "myDB.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            sql = "CREATE TABLE myDiary(diaryDate text PRIMARY KEY,content text)";
            db.execSQL(sql);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            sql = "DROP TABLE IF EXISTS myDiary";
            db.execSQL(sql);
        }
    }


}
