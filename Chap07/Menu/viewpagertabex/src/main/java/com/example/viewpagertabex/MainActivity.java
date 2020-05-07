package com.example.viewpagertabex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabs;
    ViewPager viewPager;
    Button btnConfirm;
    EditText fedtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabs = (TabLayout)findViewById(R.id.tabs);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(),3);
        viewPager.setAdapter(adapter);

        btnConfirm = (Button)findViewById(R.id.btnConfirm);
        fedtTitle = (EditText)findViewById(R.id.fedtTitle);


        tabs.addTab(tabs.newTab().setText("이미지회전"));
        tabs.addTab(tabs.newTab().setText("인사하기"));
        tabs.addTab(tabs.newTab().setText("동물선택"));

        //tabs동기화
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        //viewPager 동기화
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment1 frag1 = new Fragment1();
                Bundle bundle = new Bundle();
                bundle.putString("title",fedtTitle.getText().toString());
                frag1.setArguments(bundle);

            }
        });



    }


}
