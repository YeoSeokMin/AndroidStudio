package com.example.javamenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout baseLayout;
    Button mybtn1;

    static final int ITEM_RED=1,ITEM_GREEN=2,ITEM_BLUE=3,ITEM_WHITE=4;
    static final int ITEM_ROTETE=5,ITEM_SCALE=6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baseLayout = this.findViewById(R.id.baseLayout);
        mybtn1 = this.findViewById(R.id.mybtn1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        /*
        1.Inflater
        2.inflate
        3.xml
         */
        //그룹지정,아이디(int형태),순서(0이면 만든 순서대로 보여줘라),타이틀
        //그룹이 없으면 0, itemId가 0이면 값이 없다는 뜻임.
        menu.add(0,ITEM_RED,0,"배경색(RED)");
        menu.add(0,ITEM_GREEN,0,"배경색(GREEN)");
        menu.add(0,ITEM_BLUE,0,"배경색(BLUE)");
        menu.add(0,ITEM_WHITE,0,"배경색(WHITE)");

        SubMenu subMenu = menu.addSubMenu("버튼 변경>>");
        subMenu.add(0,ITEM_ROTETE,0,"버튼 45도 회전");
        subMenu.add(0,ITEM_SCALE,0,"버튼 2배 확대");

        //return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case ITEM_RED:
                baseLayout.setBackgroundColor(Color.RED);
                break;
            case ITEM_GREEN:
                baseLayout.setBackgroundColor(Color.GREEN);
                break;
            case ITEM_BLUE:
                baseLayout.setBackgroundColor(Color.BLUE);
                break;
            case ITEM_WHITE:
                baseLayout.setBackgroundColor(Color.WHITE);
                break;
            case ITEM_ROTETE:
                mybtn1.setRotation(45);
                break;
            case ITEM_SCALE:
                mybtn1.setScaleX(2);        //X축방향으로 으로 2배 늘어난다.
                mybtn1.setScaleY(2);        //Y축방향으로 으로 2배 늘어난다.
                break;

        }

        return super.onOptionsItemSelected(item);

    }
}
