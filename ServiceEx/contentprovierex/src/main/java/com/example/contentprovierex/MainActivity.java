package com.example.contentprovierex;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button btnCall, btnAddress;
    EditText edtCallLog;
    String callstr;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddress=findViewById(R.id.btnAddress);
        btnCall=findViewById(R.id.btnCall);
        edtCallLog =findViewById(R.id.edtCallLog);
        getCallHistory();

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtCallLog.setText(callstr);
            }
        });
        btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtCallLog.setText(getAddress());
            }
        });
    }

    public void getCallHistory() {
        int pCheck = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG);
        if (pCheck == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_CALL_LOG}, MODE_PRIVATE);

        } else {
            String callSet[] = new String[]{CallLog.Calls.DATE, CallLog.Calls.TYPE,
                    CallLog.Calls.NUMBER, CallLog.Calls.DURATION};

            Cursor cursor = getContentResolver().query(CallLog.CONTENT_URI, callSet, null, null, null);
            if (cursor == null) {
                callstr = "통화기록없음";
            } else {
                StringBuffer callBuffer = new StringBuffer();
                callBuffer.append("날짜    구분    전화번호    통화시간\n");
                callBuffer.append("==================================\n");
                cursor.moveToFirst();//자료의 맨앞으로
                do {
                    long callDate = cursor.getLong(0);
                    SimpleDateFormat sfomat = new SimpleDateFormat("yyyy-MM-dd");
                    String data_str = sfomat.format(new Date(callDate));
                    callBuffer.append(data_str + "    ");
                    if (cursor.getInt(1) == CallLog.Calls.INCOMING_TYPE) {
                        callBuffer.append("수신    ");
                    } else {
                        callBuffer.append("발신    ");
                    }
                    callBuffer.append(cursor.getString(2));
                    callBuffer.append(cursor.getString(3) + "초\n");
                } while (cursor.moveToNext());
                cursor.close();
                callstr = callBuffer.toString();
            }
        }
    }
    public String getAddress(){
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,       // 조회할 프로젝션
                null,        // 조건 절
                null,    // 조건 절의 파라미터
                null);      // 정렬 방향
        String phoneNumber = cursor.toString();
        cursor.moveToFirst();
        do {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            str += "이름 " + name + "폰번호 " + phoneNumber + "\n";
        }while (cursor.moveToNext());
        cursor.close();
        return str;

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        getCallHistory();
    }
}
