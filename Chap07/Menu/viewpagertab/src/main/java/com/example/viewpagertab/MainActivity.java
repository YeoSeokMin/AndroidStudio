package com.example.viewpagertab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabs;
    //TabLayout.TabLayoutOnPageChangeListener tabs;
    ViewPager viewPager;
    MyPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabs = (TabLayout)findViewById(R.id.tabs);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        tabs.addTab(tabs.newTab().setText("이미지회전"));
        tabs.addTab(tabs.newTab().setText("인사하기"));
        tabs.addTab(tabs.newTab().setText("동물선택"));

        adapter = new MyPagerAdapter(getSupportFragmentManager(),3);
        viewPager.setAdapter(adapter);

        //탭메뉴 싱크화
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        //viewPager.addOnAdapterChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));


        //뷰페이지 싱크화



    }

    public class MyPagerAdapter extends FragmentPagerAdapter {
        int numTabs;        //Tab의 개수를 담을 변수;



        public MyPagerAdapter(FragmentManager fm,int numTabs) {
            super(fm);
            this.numTabs = numTabs;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    fragment1 frag1 = new fragment1();
                    return frag1;
                case 1:
                    fragment2 frag2 = new fragment2();
                    return frag2;
                case 2:
                    fragment3 frag3 = new fragment3();
                    return frag3;
                default:
                    return null;
            }


        }


        //tab의 개수 return;
        @Override
        public int getCount() {
            return numTabs;
        }
    }
}
