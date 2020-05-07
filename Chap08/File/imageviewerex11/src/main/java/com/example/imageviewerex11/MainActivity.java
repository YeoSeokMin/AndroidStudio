package com.example.imageviewerex11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //퍼미션정보를 int에 담는다.
        int permissioncheck = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        //허용안한상태면...
        //if(permissioncheck != PackageManager.PERMISSION_GRANTED) {

        if(permissioncheck == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);
            //Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
            //sdcardProcess();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();

    }
}
