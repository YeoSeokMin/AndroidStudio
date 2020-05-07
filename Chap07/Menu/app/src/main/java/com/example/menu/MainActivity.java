package com.example.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout baseLayout;
    Button mybtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baseLayout = (LinearLayout)findViewById(R.id.baseLayout);
        mybtn1 = (Button)findViewById(R.id.mybtn1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);                //기본틀
        MenuInflater mInflater = getMenuInflater();     //making Inflater
        mInflater.inflate(R.menu.mainmenu,menu);        //점을 크게한다.(바람을 넣어준다:inflate)

        //return super.onCreateOptionsMenu(menu);         //기본틀만 보여줄때
        return true;                                    //기본틀이 아니라 내가 만든걸 보여줄거니까..false는 안만든다.
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.itemRed:
                baseLayout.setBackgroundColor(Color.RED);
                break;
            case R.id.itemGreen:
                baseLayout.setBackgroundColor(Color.GREEN);
                break;
            case R.id.itemBlue:
                baseLayout.setBackgroundColor(Color.BLUE);
                break;
            case R.id.itemBase:
                baseLayout.setBackgroundColor(Color.WHITE);
                break;
            case R.id.subRotate:
                mybtn1.setRotation(45);
                break;
            case R.id.subSize:
                mybtn1.setScaleX(2);        //X축방향으로 으로 2배 늘어난다.
                mybtn1.setScaleY(2);        //Y축방향으로 으로 2배 늘어난다.
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
