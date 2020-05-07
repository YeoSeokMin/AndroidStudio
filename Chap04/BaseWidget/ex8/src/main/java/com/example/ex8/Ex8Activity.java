package com.example.ex8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Ex8Activity extends AppCompatActivity {

    EditText edtToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex8);
        setTitle("연습문제4-8");

        edtToast = (EditText) findViewById(R.id.edtToast);

        edtToast.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Toast.makeText(getApplicationContext(), edtToast.getText().toString(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });


    }
}
