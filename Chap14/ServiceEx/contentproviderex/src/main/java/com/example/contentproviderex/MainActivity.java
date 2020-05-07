package com.example.contentproviderex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button btnCall,btnAddress;
    EditText edtCallLog;
    String callStr,addressStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCall = (Button)findViewById(R.id.btnCall);
        btnAddress = (Button)findViewById(R.id.btnAddress);
        edtCallLog = (EditText)findViewById(R.id.edtCallLog);
        getCallHistory();
        getAddress();

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtCallLog.setText(callStr);
            }
        });
        btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtCallLog.setText(addressStr);
            }
        });

    }
    public void getCallHistory() {
        int pCheck = ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_CALL_LOG);
        if(pCheck== PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.READ_CALL_LOG},100);

        } else {
            String callSet[] = new String[] {CallLog.Calls.DATE,CallLog.Calls.TYPE,CallLog.Calls.NUMBER,CallLog.Calls.DURATION};
            Cursor cursor = getContentResolver().query(CallLog.Calls.CONTENT_URI,callSet,null,null,null);
            if (cursor == null) {
                callStr = "통화기록 없음";
            } else {
                StringBuffer callBuffer = new StringBuffer();
                callBuffer.append("날짜                  구분      전화번호        통화시간\n");
                callBuffer.append("==================================\n");
                cursor.moveToFirst();
                do {
                    long callDate = cursor.getLong(0);
                    SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd");
                    String date_str = sformat.format(new Date(callDate));
                    callBuffer.append(date_str+"      ");
                    //callBuffer.append(cursor.getInt(1)+"    ");
                    if(cursor.getInt(1)==CallLog.Calls.INCOMING_TYPE) {
                        callBuffer.append("수신      ");
                    } else if(cursor.getInt(1)==CallLog.Calls.OUTGOING_TYPE){
                        callBuffer.append("발신      ");
                    } else {
                        callBuffer.append("부재중      ");
                    }
                    callBuffer.append(cursor.getString(2)+"    ");
                    callBuffer.append(cursor.getString(3)+"초\n");
                } while(cursor.moveToNext());
                cursor.close();
                callStr = callBuffer.toString();
            }
        }
    }

    public void getAddress() {
        int pCheck = ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS);

        if(pCheck== PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.READ_CONTACTS},200);

        } else {
            Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            String str = "";
            cursor.moveToFirst();
            do {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                str += "이름 : " + name + "폰번호 : " + phoneNumber + "\n";
            } while (cursor.moveToNext());
            cursor.close();
            addressStr = str;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100) {
            getCallHistory();
        } else if(requestCode==200){
            getAddress();
        }


    }
}
