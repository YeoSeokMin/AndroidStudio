package com.example.spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    Spinner sp1;
    final String[] movie = {"써니","완득이","괴물","라이오스타","비열한거리",
            "왕의남자","아이랜드","웰컴투동막골","헬보이","백투더피처"
    };
    final int[] movieID = {R.drawable.mov01,R.drawable.mov02,R.drawable.mov03,R.drawable.mov04,R.drawable.mov05,
            R.drawable.mov06,R.drawable.mov07,R.drawable.mov08,R.drawable.mov09,R.drawable.mov10
    };

    ArrayAdapter<String> adapter;
    ImageView ivMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp1 = (Spinner)findViewById(R.id.sp1);
        ivMovie = (ImageView)findViewById(R.id.ivMovie);

        //adapter가 넓고 좁게 보이는 차이.
        //adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,movie);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,movie);
        sp1.setAdapter(adapter);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ivMovie.setImageResource(movieID[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ivMovie.setImageResource(R.drawable.mario);
            }
        });

    }


}
