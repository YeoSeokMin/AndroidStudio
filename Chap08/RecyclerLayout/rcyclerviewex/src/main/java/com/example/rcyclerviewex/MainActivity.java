package com.example.rcyclerviewex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final static String TAG = "MyRecyclerView";
    Button btnAdd;
    RecyclerView rv;

    LinearLayoutManager llMgr;

    List<Integer> count = null;
    int i = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        rv = (RecyclerView)findViewById(R.id.rv);

        llMgr = new LinearLayoutManager(this);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(llMgr);

        count = new ArrayList<>();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"i==>"+i);
                i++;
                Log.d(TAG,"i==>"+i);

                count.add(i);
                Log.d(TAG,"count==>"+count.toString());
                rv.setAdapter(new CounterAdapter(getApplication(),count,i));
            }
        });







    }
}
