package com.example.chap07ex042;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView ivOptionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("연습문제7-4");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        ivOptionMenu = (ImageView)findViewById(R.id.ivOptionMenu);
        switch (item.getItemId()) {
            case R.id.itemDog :
                ivOptionMenu.setImageResource(R.drawable.dog);
                break;
            case R.id.itemCat :
                ivOptionMenu.setImageResource(R.drawable.cat);
                break;
            case R.id.itemRabbit :
                ivOptionMenu.setImageResource(R.drawable.rabbit);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
