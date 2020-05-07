package com.example.autotextview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView autoTv;
    MultiAutoCompleteTextView multiAutoTv;

    String words[] = {  "able", "apple", "application",
            "bear", "bit", "bik",
            "car", "cable"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        autoTv = this.findViewById(R.id.autoTv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,words);
        autoTv.setAdapter(adapter);

        multiAutoTv = this.findViewById(R.id.multiAutoTv);
        MultiAutoCompleteTextView.CommaTokenizer tokenizer = new MultiAutoCompleteTextView.CommaTokenizer();
        multiAutoTv.setTokenizer(tokenizer);
        multiAutoTv.setAdapter(adapter);



    }
}


/*
1.adapter 준비
    ArrayAdapter
    CustomAdapter
2.adapter 장착
 */