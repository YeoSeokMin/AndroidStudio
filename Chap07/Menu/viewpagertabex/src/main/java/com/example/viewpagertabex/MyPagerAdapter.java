package com.example.viewpagertabex;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {
    int tabnums;
    public MyPagerAdapter(FragmentManager fm,int tabnums) {
        super(fm);
        this.tabnums = tabnums;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Fragment1 frag1 = new Fragment1();
                return frag1;
            case 1:
                Fragment2 frag2 = new Fragment2();
                return frag2;
            case 2:
                Fragment3 frag3 = new Fragment3();
                return frag3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabnums;
    }
}


