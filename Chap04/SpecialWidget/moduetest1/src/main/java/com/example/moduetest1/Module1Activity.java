package com.example.moduetest1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class Module1Activity extends AppCompatActivity {
    AutoCompleteTextView actv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module1);

        actv = (AutoCompleteTextView)findViewById(R.id.acTv);
        String[] arr = {"ab","abc","abcd","abcde"};
        //ArrayAdapter<String> adapter =




    }
}
