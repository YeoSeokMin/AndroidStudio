package com.example.contextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ContextMenuActivity extends AppCompatActivity {
    LinearLayout baseLayout;
    Button btnBackground,btnChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu);

        baseLayout = this.findViewById(R.id.baseLayout);
        btnBackground = this.findViewById(R.id.btnBackground);
        btnChange = this.findViewById(R.id.btnChange);

        registerForContextMenu(btnBackground);
        registerForContextMenu(btnChange);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater mInflater = getMenuInflater();

        if(v==btnBackground) {
            menu.setHeaderTitle("배경색 변경");
            mInflater.inflate(R.menu.backchangemenu,menu);
        }
        if(v==btnChange) {
            mInflater.inflate(R.menu.rotatesize,menu);
        }


    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
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
            case R.id.itemWhite:
                baseLayout.setBackgroundColor(Color.WHITE);
                break;
            case R.id.itemRotate:
                btnChange.setRotation(45);
                break;
            case R.id.itemSize:
                btnChange.setScaleX(2);
                btnChange.setScaleX(2);
                break;
        }

        return super.onContextItemSelected(item);
    }
}
